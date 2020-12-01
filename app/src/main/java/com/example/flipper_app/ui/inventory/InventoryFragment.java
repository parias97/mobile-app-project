package com.example.flipper_app.ui.inventory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flipper_app.R;
import com.example.flipper_app.adapter.ItemAdapter;
import com.example.flipper_app.model.Item;
import com.example.flipper_app.ui.ItemViewModel;
import com.example.flipper_app.ui.addItem.AddItemViewModel;

import java.util.List;

public class InventoryFragment extends Fragment {

    private ItemViewModel itemViewModel;
    private AddItemViewModel addItemViewModel;

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

        return root;
    }

    public void addToSoldTable(View view){
        Toast.makeText(getActivity(), "Adding to sold table", Toast.LENGTH_SHORT).show();
    }

    public static InventoryFragment newInstance(){
        return new InventoryFragment();
    }
}