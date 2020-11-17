package com.example.flipper_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flipper_app.R;
import com.example.flipper_app.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {

    private List<Item> items = new ArrayList<>();

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ItemHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Item currentItem = items.get(position);
        holder.itemNameTV.setText(currentItem.getName());
        holder.itemInitialPriceTV.setText(String.valueOf(currentItem.getInitialPrice()));
        holder.itemQuantityTV.setText(String.valueOf(currentItem.getQuantity()));
        holder.itemPlatformTV.setText(currentItem.getPlatform());
        // find image via path
    }

    // Get number of items in list.
    @Override
    public int getItemCount() {
        return items.size();
    }
    public void setItems(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    // Store item data to bind them to their views.
    class ItemHolder extends RecyclerView.ViewHolder {
        private TextView itemNameTV;
        private TextView itemInitialPriceTV;
        private TextView itemQuantityTV;
        private TextView itemPlatformTV;

        public ItemHolder(View itemView) {
            super(itemView);
            itemNameTV = itemView.findViewById(R.id.item_name);
            itemInitialPriceTV = itemView.findViewById(R.id.item_initial_price);
            itemQuantityTV = itemView.findViewById(R.id.item_quantity);
            itemPlatformTV = itemView.findViewById(R.id.item_platform);
        }
    }
}
