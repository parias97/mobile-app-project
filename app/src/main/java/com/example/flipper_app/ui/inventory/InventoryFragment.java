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
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class InventoryFragment extends Fragment {

    public static final int ADD_ITEM_REQUEST = 1;
    private ItemViewModel itemViewModel;
    //private InventoryViewModel inventoryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*inventoryViewModel =
                new ViewModelProvider(this).get(InventoryViewModel.class);*/
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

        return root;
    }


    @Override
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
    }

    public static InventoryFragment newInstance(){
        return new InventoryFragment();
    }
}