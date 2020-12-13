package com.example.flipper_app.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.flipper_app.model.Item;
import com.example.flipper_app.R;
import com.example.flipper_app.ui.addItem.AddItemViewModel;
import com.example.flipper_app.ui.inventory.InventoryFragment;
import com.example.flipper_app.model.SoldItem;
import com.example.flipper_app.ui.inventory.InventoryViewModel;
import com.example.flipper_app.ui.sold.SoldItemViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.example.flipper_app.adapter.ItemAdapter;

import java.io.File;


public class AddToSoldFragment extends Fragment {

    TextInputEditText itemNameET;
    TextInputEditText itemDescET;
    TextInputEditText itemInitPriceET;
    TextInputEditText itemSoldPriceET;
    TextInputEditText itemQuantityET;
    TextInputEditText itemPlatformET;
    ImageView itemImageView;
    private File imgFile;
    private Button saveButton;
    private Button cancelButton;
    private AddToSoldViewModel addToSoldViewModel;
    private ItemViewModel itemViewModel;
    private SoldItemViewModel soldItemViewModel;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_add_to_sold, container, false);
        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        addToSoldViewModel = new ViewModelProvider(this).get(AddToSoldViewModel.class);
        soldItemViewModel = new ViewModelProvider(this).get(SoldItemViewModel.class);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get the references to each view in fragment_add_to_sold.xml.
        itemNameET = getActivity().findViewById(R.id.itemName);
        itemDescET = getActivity().findViewById(R.id.itemDesc);
        itemInitPriceET = getActivity().findViewById(R.id.itemInitPrice);
        itemSoldPriceET = getActivity().findViewById(R.id.itemSoldPrice);
        itemQuantityET = getActivity().findViewById(R.id.itemQuantity);
        itemPlatformET = getActivity().findViewById(R.id.itemPlatform);
        itemImageView = getActivity().findViewById(R.id.itemPicPath);
        // Fill the views with values from the inventory item that was clicked.
        itemNameET.setText(ItemAdapter.autofill.getName());
        itemDescET.setText(ItemAdapter.autofill.getDesc());
        itemInitPriceET.setText(String.valueOf(ItemAdapter.autofill.getInitialPrice()));
        itemQuantityET.setText(String.valueOf(ItemAdapter.autofill.getQuantity()));
        itemPlatformET.setText(ItemAdapter.autofill.getPlatform());
        imgFile = new File(ItemAdapter.autofill.getPicturePath());

        if(imgFile.exists()) {
            Bitmap imageBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            itemImageView.setImageBitmap(imageBitmap);
        }

        saveButton = root.findViewById(R.id.saveButton);
        cancelButton = root.findViewById(R.id.cancelButton);

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

        addToSoldViewModel.getSaved().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("new data", s);
                /* When the item that has sold has been added to the sold database, delete
                 * the item from the inventory database.
                 */
                if(s == "true"){
                    if(Integer.parseInt(itemQuantityET.getText().toString()) >= ItemAdapter.autofill.getQuantity())
                        removeFromInventory();
                    else{
                        updateInventory();
                    }
                    insertItem();
                    addToSoldViewModel.setSaved("false");
                }
            }
        });
    }

    // Save all of the user input into variables to add to the addToSoldViewModel so we can access
    // these values where needed.
    private void saveButton(){
        String title = itemNameET.getText().toString();
        String desc = itemDescET.getText().toString();
        double initPrice = ItemAdapter.autofill.getInitialPrice();
        double soldPrice = Double.parseDouble(itemSoldPriceET.getText().toString());
        int itemQuantity = Integer.parseInt(itemQuantityET.getText().toString());
        String platform = itemPlatformET.getText().toString();
        String path = "";
        if(imgFile.exists()) {
            path = imgFile.getAbsolutePath();
        }

        addToSoldViewModel.setItemName(title);
        addToSoldViewModel.setDesc(desc);
        addToSoldViewModel.setInitPrice(initPrice);
        addToSoldViewModel.setSoldPrice(soldPrice);
        addToSoldViewModel.setQuantity(itemQuantity);
        addToSoldViewModel.setPlatform(platform);
        addToSoldViewModel.setPicPath(path);
        addToSoldViewModel.setSaved("true");
        getFragmentManager().popBackStackImmediate();
    }

    private void insertItem(){
        String title = addToSoldViewModel.getItemName();
        String desc = addToSoldViewModel.getDesc();
        double initPrice = addToSoldViewModel.getInitPrice();
        double soldPrice = addToSoldViewModel.getSoldPrice();
        int quantity = addToSoldViewModel.getQuantity();
        String platform = addToSoldViewModel.getPlatform();
        String picpath = addToSoldViewModel.getPicPath();

        SoldItem item = new SoldItem(title, desc, initPrice, soldPrice,  quantity, platform, picpath);
        soldItemViewModel.insert(item);
    }

    private void updateInventory(){
        itemViewModel.delete(ItemAdapter.autofill);
        itemViewModel.insert(new Item(ItemAdapter.autofill.getName(), ItemAdapter.autofill.getDesc(), ItemAdapter.autofill.getInitialPrice(), ItemAdapter.autofill.getQuantity() - Integer.parseInt(itemQuantityET.getText().toString()),ItemAdapter.autofill.getPlatform(), ItemAdapter.autofill.getPicturePath(), true));
    }

    private void removeFromInventory(){
        itemViewModel.delete(ItemAdapter.autofill);
    }

    public static AddToSoldFragment newInstance() {
        return new AddToSoldFragment();
    }

}
