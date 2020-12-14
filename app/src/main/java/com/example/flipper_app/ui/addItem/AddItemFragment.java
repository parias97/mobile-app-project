package com.example.flipper_app.ui.addItem;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.flipper_app.MainActivity;
import com.example.flipper_app.R;
import com.example.flipper_app.model.Item;
import com.example.flipper_app.ui.ItemViewModel;

import java.io.File;
import java.io.IOException;

public class AddItemFragment extends Fragment {

    private static final int CAPTURE_IMAGE_REQUEST_CODE = 200;
    private ItemViewModel itemViewModel;
    private AddItemViewModel addItemViewModel;
    private Item item;
    private EditText itemNameET;
    private EditText itemDescET;
    private EditText itemInitPriceET;
    private EditText itemQuantityET;
    private EditText itemPlatformET;
    private Button saveButton;
    private Button cancelButton;
    private Button addImageButton;
    private ImageView imageHolder;
    private File imageFile;
    public String imageFileName;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_item, container, false);
        itemNameET = root.findViewById(R.id.itemName);
        itemDescET = root.findViewById(R.id.itemDesc);
        itemInitPriceET = root.findViewById(R.id.itemInitPrice);
        itemQuantityET = root.findViewById(R.id.itemQuantity);
        itemPlatformET = root.findViewById(R.id.itemPlatform);
        saveButton = root.findViewById(R.id.saveButton);
        cancelButton = root.findViewById(R.id.cancelButton);
        addImageButton = root.findViewById(R.id.addImageButton);
        imageHolder = root.findViewById(R.id.itemPicPath);

        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        addItemViewModel = new ViewModelProvider(this).get(AddItemViewModel.class);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveButton();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });

        addItemViewModel.getSaved().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("new data", s);
                if(s == "true"){
                    insertItem();
                    addItemViewModel.setSaved("false");
                }
            }
        });

        // Source: https://guides.codepath.com/android/Accessing-the-Camera-and-Stored-Media
        addImageButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                imageFileName =  String.valueOf(System.currentTimeMillis()) + ".jpg";
                imageFile = getImageFileUri( imageFileName);
                Uri fileProvider = FileProvider.getUriForFile(getActivity(), "com.example.flipper_app.fileprovider", imageFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);
                startActivityForResult(cameraIntent, CAPTURE_IMAGE_REQUEST_CODE);
            }
        });

        return root;
    }

    private void saveButton(){
        String title = itemNameET.getText().toString();
        String desc = itemDescET.getText().toString();
        double initPrice = Double.parseDouble(itemInitPriceET.getText().toString());
        int itemQuantity = Integer.parseInt(itemQuantityET.getText().toString());
        String platform = itemPlatformET.getText().toString();
        String path = "";
        if(imageFile != null) {
            path = imageFile.getAbsolutePath();
        }

        addItemViewModel.setItemName(title);
        addItemViewModel.setDesc(desc);
        addItemViewModel.setInitPrice(initPrice);
        addItemViewModel.setQuantity(itemQuantity);
        addItemViewModel.setPlatform(platform);
        addItemViewModel.setPicPath(path);
        addItemViewModel.setSaved("true");

        getFragmentManager().popBackStackImmediate();
    }

    private void insertItem(){
        String title = addItemViewModel.getItemName();
        String desc = addItemViewModel.getDesc();
        double initPrice = addItemViewModel.getInitPrice();
        int quantity = addItemViewModel.getQuantity();
        String platform = addItemViewModel.getPlatform();
        String picpath = addItemViewModel.getPicPath();

        item = new Item(title, desc, initPrice, quantity, platform, picpath, false);
        itemViewModel.insert(item);
    }

    // Returns the File for a photo stored on disk given the fileName
    public File getImageFileUri(String fileName) {
        // Get safe storage directory for photos. `getExternalFilesDir` is used on Context to access
        // package-specific directories.
        File mediaStorageDir = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "Flipper");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
            Log.d("Flipper", "failed to create directory");
        }

        // Return the file target for the image based on filename
        File file = new File(mediaStorageDir.getPath() + File.separator + fileName);

        //Log.d("file", file.getAbsolutePath());

        return file;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap image = BitmapFactory.decodeFile(imageFile.getAbsolutePath());

        // Return rotated image.
        Bitmap rotatedBitmap = MainActivity.stageForRotation(image, imageFile);

        imageHolder.setImageBitmap(rotatedBitmap);
    }

    //Source: https://stackoverflow.com/questions/14066038/why-does-an-image-captured-using-camera-intent-gets-rotated-on-some-devices-on-a
    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }

}