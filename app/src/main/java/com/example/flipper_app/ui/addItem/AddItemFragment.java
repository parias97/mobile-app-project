package com.example.flipper_app.ui.addItem;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.flipper_app.R;
import com.example.flipper_app.model.Item;
import com.example.flipper_app.ui.ItemViewModel;
import com.example.flipper_app.ui.sold.SoldItemViewModel;

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
    private SoldItemViewModel soldItemViewModel;
    private AddItemViewModel addItemViewModel;
    private EditText itemNameET;
    private EditText itemDescET;
    private EditText itemInitPriceET;
    private EditText itemQuantityET;
    private EditText itemPlatformET;
    private EditText itemPicPathET;
    private Button saveButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        soldItemViewModel = new ViewModelProvider(this).get(SoldItemViewModel.class);
        addItemViewModel = new ViewModelProvider(this).get(AddItemViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_item, container, false);
        itemNameET = root.findViewById(R.id.itemName);
        itemDescET = root.findViewById(R.id.itemDesc);
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

        return root;
    }

    private void saveButton(){
        String title = itemNameET.getText().toString();
        String desc = itemDescET.getText().toString();
        double initPrice = Double.parseDouble(itemInitPriceET.getText().toString());
        int itemQuantity = Integer.parseInt(itemQuantityET.getText().toString());
        String platform = itemPlatformET.getText().toString();
        String path = itemPicPathET.getText().toString();

        addItemViewModel.setItemName(title);
        addItemViewModel.setDesc(desc);
        addItemViewModel.setInitPrice(initPrice);
        addItemViewModel.setQuantity(itemQuantity);
        addItemViewModel.setPlatform(platform);
        addItemViewModel.setPicPath(path);
        addItemViewModel.setSaved("true");
    }

    private void insertItem(){
        String title = addItemViewModel.getItemName();
        String desc = addItemViewModel.getDesc();
        double initPrice = addItemViewModel.getInitPrice();
        int quantity = addItemViewModel.getQuantity();
        String platform = addItemViewModel.getPlatform();
        String picpath = addItemViewModel.getPicPath();

        Item item = new Item(title, desc, initPrice, quantity, platform, picpath, false);
        itemViewModel.insert(item);
    }
}