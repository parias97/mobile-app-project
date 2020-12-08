package com.example.flipper_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flipper_app.R;
import com.example.flipper_app.model.FavoriteItem;
import com.example.flipper_app.model.Item;
import com.example.flipper_app.model.SoldItem;

import java.util.ArrayList;
import java.util.List;

public class FavoriteItemAdapter extends RecyclerView.Adapter<FavoriteItemAdapter.FavoriteItemHolder> {

    private List<FavoriteItem> items = new ArrayList<>();

    @NonNull
    @Override
    public FavoriteItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favoriteitem_cardview, parent, false);
        return new FavoriteItemHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull FavoriteItemHolder holder, int position) {
        FavoriteItem currentItem = items.get(position);
        holder.itemNameTV.setText(currentItem.getName());
        holder.itemInitialPriceTV.setText(String.valueOf(currentItem.getInitialPrice()));
        holder.itemSoldPriceTV.setText(String.valueOf(currentItem.getSoldPrice()));

        // find image via path
    }

    // Get number of items in list.
    @Override
    public int getItemCount() {
        return items.size();
    }
    public void setItems(List<FavoriteItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    // Store item data to bind them to their views.
    class FavoriteItemHolder extends RecyclerView.ViewHolder {
        private TextView itemNameTV;
        private TextView itemInitialPriceTV;
        private TextView itemSoldPriceTV;

        public FavoriteItemHolder(View itemView) {
            super(itemView);
            itemNameTV = itemView.findViewById(R.id.favoriteItem_name);
            itemInitialPriceTV = itemView.findViewById(R.id.favoriteItem_initial_price);
            itemSoldPriceTV = itemView.findViewById(R.id.favoriteItem_sold_price);
        }
    }
}
