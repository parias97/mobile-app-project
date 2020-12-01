package com.example.flipper_app.ui.summary;

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

public class SummaryFragment extends Fragment {

    private SummaryViewModel summaryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        summaryViewModel =
                ViewModelProviders.of(this).get(SummaryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_summary, container, false);
        final TextView textView = root.findViewById(R.id.text_summary);
        summaryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }
}