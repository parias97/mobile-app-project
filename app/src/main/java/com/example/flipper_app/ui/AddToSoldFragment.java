package com.example.flipper_app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.flipper_app.R;
import com.example.flipper_app.ui.inventory.InventoryFragment;

public class AddToSoldFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_to_sold, container, false);

        return root;
    }

    public static AddToSoldFragment newInstance() {
        return new AddToSoldFragment();
    }


}
