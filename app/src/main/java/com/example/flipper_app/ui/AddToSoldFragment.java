package com.example.flipper_app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.flipper_app.model.Item;
import com.example.flipper_app.R;
import com.example.flipper_app.ui.inventory.InventoryFragment;
import com.example.flipper_app.model.SoldItem;
import com.google.android.material.textfield.TextInputEditText;
import com.example.flipper_app.adapter.ItemAdapter;


public class AddToSoldFragment extends Fragment {

    SoldItem soldItem;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_to_sold, container, false);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextInputEditText itemNameET = getActivity().findViewById(R.id.itemName);
        TextInputEditText itemDescET = getActivity().findViewById(R.id.itemDesc);
        TextInputEditText itemInitPriceET = getActivity().findViewById(R.id.itemInitPrice);
        TextInputEditText itemQuantityET = getActivity().findViewById(R.id.itemQuantity);
        TextInputEditText itemPlatformET = getActivity().findViewById(R.id.itemPlatform);
        itemNameET.setText(ItemAdapter.autofill.getName());
        itemDescET.setText(ItemAdapter.autofill.getDesc());

        itemInitPriceET.setText(String.valueOf(ItemAdapter.autofill.getInitialPrice()));
        itemQuantityET.setText(String.valueOf(ItemAdapter.autofill.getQuantity()));
        itemPlatformET.setText(ItemAdapter.autofill.getPlatform());
    }

    public static AddToSoldFragment newInstance() {
        return new AddToSoldFragment();
    }


}
