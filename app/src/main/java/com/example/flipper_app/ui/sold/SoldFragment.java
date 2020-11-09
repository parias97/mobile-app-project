package com.example.flipper_app.ui.sold;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.flipper_app.R;

public class SoldFragment extends Fragment {

    private SoldViewModel soldViewModel;

    public static SoldFragment newInstance() {
        return new SoldFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        soldViewModel =
                ViewModelProviders.of(this).get(SoldViewModel.class);
        View root = inflater.inflate(R.layout.fragment_sold, container, false);
        /*final TextView textView = root.findViewById(R.id.text_search);
        soldViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        soldViewModel = new ViewModelProvider(this).get(SoldViewModel.class);
    }

}