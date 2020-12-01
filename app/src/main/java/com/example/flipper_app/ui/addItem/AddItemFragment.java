package com.example.flipper_app.ui.addItem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.flipper_app.R;
import com.example.flipper_app.ui.ItemViewModel;

public class AddItemFragment extends Fragment {

    public static final String EXTRA_TITLE =
            "com.example.flipper_app.ui.addItem.EXTRA_TITLE";
    public static final String EXTRA_INITPRICE =
            "com.example.flipper_app.ui.addItem.EXTRA_INITPRICE";
    public static final String EXTRA_QUANTITY =
            "com.example.flipper_app.ui.addItem.EXTRA_QUANTITY";
    public static final String EXTRA_PLATFORM =
            "com.example.flipper_app.ui.addItem.EXTRA_PLATFORM";
    public static final String EXTRA_PICPATH =
            "com.example.flipper_app.ui.addItem.EXTRA_PICPATH";

    private ItemViewModel itemViewModel;
    private AddItemViewModel addItemViewModel;
    private EditText itemNameET;
    private EditText itemInitPriceET;
    private EditText itemQuantityET;
    private EditText itemPlatformET;
    private EditText itemPicPathET;
    private Button saveButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        addItemViewModel = new ViewModelProvider(this).get(AddItemViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_item, container, false);
        itemNameET = root.findViewById(R.id.itemName);
        itemInitPriceET = root.findViewById(R.id.itemInitPrice);
        itemQuantityET = root.findViewById(R.id.itemQuantity);
        itemPlatformET = root.findViewById(R.id.itemPlatform);
        itemPicPathET = root.findViewById(R.id.itemPicpath);
        saveButton = root.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveButton();
            }
        });

        /*addItemViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    private void saveButton(){
        String title = itemNameET.getText().toString();
        double initPrice = Double.parseDouble(itemInitPriceET.getText().toString());
        int itemQuantity = Integer.parseInt(itemQuantityET.getText().toString());
        String platform = itemPlatformET.getText().toString();
        String path = itemPicPathET.getText().toString();

        addItemViewModel.setItemName(title);
        addItemViewModel.setInitPrice(initPrice);
        addItemViewModel.setQuantity(itemQuantity);
        addItemViewModel.setPlatform(platform);
        addItemViewModel.setPlatform(path);

        Log.d("inside saveButton", "true");
        addItemViewModel.setSaved("true");

        /*Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_INITPRICE, initPrice);
        data.putExtra(EXTRA_QUANTITY, itemQuantity);
        data.putExtra(EXTRA_PLATFORM, platform);
        data.putExtra(EXTRA_PICPATH, path);

        getActivity().setResult(getActivity().RESULT_OK, data);
        getActivity().finish();*/
    }
}