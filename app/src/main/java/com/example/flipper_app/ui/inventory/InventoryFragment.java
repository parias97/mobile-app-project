package com.example.flipper_app.ui.inventory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.flipper_app.R;
import com.example.flipper_app.ui.inventory.InventoryViewModel;

public class InventoryFragment extends Fragment {

    private InventoryViewModel inventoryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        inventoryViewModel =
                ViewModelProviders.of(this).get(InventoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_inventory, container, false);
        final TextView textView = root.findViewById(R.id.text_inventory);
        inventoryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }
}