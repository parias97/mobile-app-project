package com.example.flipper_app.ui.sold;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.flipper_app.R;
import com.example.flipper_app.adapter.ItemAdapter;
import com.example.flipper_app.adapter.SoldItemAdapter;
import com.example.flipper_app.model.Item;
import com.example.flipper_app.model.SoldItem;
import com.example.flipper_app.ui.ItemViewModel;
import com.example.flipper_app.ui.inventory.InventoryFragment;

import java.util.List;

public class SoldFragment extends Fragment {

    private ItemViewModel soldItemViewModel;
    private View root;

    public static SoldFragment newInstance() {
        return new SoldFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_sold, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup RecyclerView to hold items.
        final RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        final SoldItemAdapter adapter = new SoldItemAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        soldItemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);

        soldItemViewModel.getAllSoldItems().observe(getViewLifecycleOwner(), new Observer<List<SoldItem>>() {
            @Override
            public void onChanged(@Nullable List<SoldItem> soldItems) {
                adapter.setItems(soldItems);
            }
        });

    }
}