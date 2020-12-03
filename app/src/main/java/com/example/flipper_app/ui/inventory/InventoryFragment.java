package com.example.flipper_app.ui.inventory;

import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

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
import com.example.flipper_app.ui.summary.SummaryFragment;

import java.util.List;

public class InventoryFragment extends Fragment {

    private ItemViewModel itemViewModel;
    private AddItemViewModel addItemViewModel;
    private ImageButton soldButton;
    private View item;
    private ItemAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addItemViewModel =
                new ViewModelProvider(this).get(AddItemViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_inventory, container, false);
        item = inflater.inflate(R.layout.item, container, false);

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        insertNestedFragment();
        // Setup RecyclerView to hold items.
        final RecyclerView recyclerView = getView().findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        adapter = new ItemAdapter();
        recyclerView.setAdapter(adapter);

        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);

        itemViewModel.getAllItems().observe(getViewLifecycleOwner(), new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable List<Item> items) {
                adapter.setItems(items);
            }
        });
    }

    private void insertNestedFragment() {
        Fragment childFragment = new SummaryFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_summary, childFragment).commit();
    }

    public static InventoryFragment newInstance() {
        return new InventoryFragment();
    }

    public void addToSoldTable(View v) {
        //Item item = adapter.getClickedItem();
        //Log.d("test:", item.getName());
    }
}
