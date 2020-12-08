package com.example.flipper_app.ui.favorites;

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
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flipper_app.R;
import com.example.flipper_app.adapter.FavoriteItemAdapter;
import com.example.flipper_app.adapter.SoldItemAdapter;
import com.example.flipper_app.model.FavoriteItem;
import com.example.flipper_app.model.SoldItem;
import com.example.flipper_app.ui.ItemViewModel;
import com.example.flipper_app.ui.sold.SoldFragment;

import java.util.List;

public class FavoritesFragment extends Fragment {

    private FavoritesViewModel favoritesViewModel;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favoritesViewModel =
                new ViewModelProvider(this).get(FavoritesViewModel.class);
        root = inflater.inflate(R.layout.fragment_favorites, container, false);

        return root;
    }

    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup RecyclerView to hold items.
        final RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        final FavoriteItemAdapter adapter = new FavoriteItemAdapter();
        recyclerView.setAdapter(adapter);

        favoritesViewModel.getAllFavoriteItems().observe(getViewLifecycleOwner(), new Observer<List<FavoriteItem>>() {
            @Override
            public void onChanged(@Nullable List<FavoriteItem> favoriteItems) {
                adapter.setItems(favoriteItems);
            }
        });
    }
}