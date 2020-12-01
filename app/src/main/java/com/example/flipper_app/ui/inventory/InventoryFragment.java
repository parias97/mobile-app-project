package com.example.flipper_app.ui.inventory;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flipper_app.R;
import com.example.flipper_app.adapter.ItemAdapter;
import com.example.flipper_app.model.Item;
import com.example.flipper_app.ui.ItemViewModel;
import com.example.flipper_app.ui.addItem.AddItemFragment;
import com.example.flipper_app.ui.addItem.AddItemViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class InventoryFragment extends Fragment {

    public static final int ADD_ITEM_REQUEST = 1;
    private ItemViewModel itemViewModel;
    private AddItemViewModel addItemViewModel;
    //private InventoryViewModel inventoryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         addItemViewModel =
                new ViewModelProvider(this).get(AddItemViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_inventory, container, false);

        // Setup RecyclerView to hold items.
        final RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        final ItemAdapter adapter = new ItemAdapter();
        recyclerView.setAdapter(adapter);



        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);

        itemViewModel.getAllItems().observe(getViewLifecycleOwner(), new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable List<Item> items) {
                adapter.setItems(items);
            }
        });

        // inventory needs to know when the item has been saved
        /*addItemViewModel.getSaved().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("new data", s);
                if(s == "true"){
                    Log.d("item name", addItemViewModel.getItemName());
                }
                if(addItemViewModel.getItemName() != null) {
                    Log.d("item name", addItemViewModel.getItemName());
                } else {
                    Log.d("isNull", "true");
                }
            }
        });*/

        /*if(addItemViewModel.getItemName() != null) {
            Log.d("item name", addItemViewModel.getItemName());
        } else {
            Log.d("item name", "null");
        }*/

        return root;
    }
    // GOAL: figure out how to save data in one fragment and display it in another?
    // HOW?

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            String title = data.getStringExtra(AddItemFragment.EXTRA_TITLE);
            double initPrice = data.getDoubleExtra(AddItemFragment.EXTRA_INITPRICE, 0);
            int quantity = data.getIntExtra(AddItemFragment.EXTRA_QUANTITY, 1);
            String platform = data.getStringExtra(AddItemFragment.EXTRA_PLATFORM);
            String picpath = data.getStringExtra(AddItemFragment.EXTRA_PICPATH);

            Item item = new Item(title, initPrice, quantity, platform, picpath);
            itemViewModel.insert(item);
            Toast.makeText(this.getContext(), "Item saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.getContext(), "Item not saved", Toast.LENGTH_SHORT).show();
        }
    }*/

    public static InventoryFragment newInstance(){
        return new InventoryFragment();
    }
}