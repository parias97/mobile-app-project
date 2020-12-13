package com.example.flipper_app.ui.inventory;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flipper_app.MainActivity;
import com.example.flipper_app.R;
import com.example.flipper_app.adapter.ItemAdapter;
import com.example.flipper_app.model.Item;
import com.example.flipper_app.ui.ItemViewModel;
import com.example.flipper_app.ui.addItem.AddItemViewModel;
import com.example.flipper_app.ui.sold.SoldItemViewModel;

import java.util.List;

public class InventoryFragment extends Fragment {

    private ItemViewModel itemViewModel;
    private AddItemViewModel addItemViewModel;
    private InventoryViewModel inventoryViewModel;
    private SoldItemViewModel soldItemViewModel;
    private static TextView profit;
    private static TextView listed;
    private static TextView sold;
    private TextView emptyInventoryTV;
    private ItemAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_inventory, container, false);

        inventoryViewModel =
                new ViewModelProvider(this).get(InventoryViewModel.class);
        addItemViewModel =
                new ViewModelProvider(this).get(AddItemViewModel.class);
        soldItemViewModel =
                new ViewModelProvider(this).get(SoldItemViewModel.class);
        profit = root.findViewById(R.id.profit);
        listed = root.findViewById(R.id.listed);
        sold = root.findViewById(R.id.sold);
        emptyInventoryTV = root.findViewById(R.id.emptyInventoryTV);

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);

        // Setup RecyclerView to hold items.
        final RecyclerView recyclerView = getView().findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);
        adapter = new ItemAdapter(itemViewModel);
        recyclerView.setAdapter(adapter);

        itemViewModel.getCount();
        soldItemViewModel.getCount();
        String formattedProfit = String.format("%.2f", MainActivity.profit);
        profit.setText("$" + formattedProfit);
        sold.setText(String.valueOf(MainActivity.sold));
        listed.setText(String.valueOf(MainActivity.listed));

        // Get all items in inventory database. If there are any changes to any of those items,
        // the observer notifies itemViewModel.
        itemViewModel.getAllItems().observe(getViewLifecycleOwner(), new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable List<Item> items) {
                adapter.setItems(items);
                if(adapter.getItemCount() == 0){
                    emptyInventoryTV.setVisibility(View.VISIBLE);
                } else {
                    emptyInventoryTV.setVisibility(View.GONE);
                }
                itemViewModel.getCount();
            }
        });
    }

    public static void update(){
        String formattedProfit = String.format("%.2f", MainActivity.profit);
        profit.setText("$" + formattedProfit);
        listed.setText(String.valueOf(MainActivity.listed));
        sold.setText(String.valueOf(MainActivity.sold));
    }

    public static InventoryFragment newInstance() {
        return new InventoryFragment();
    }

}
