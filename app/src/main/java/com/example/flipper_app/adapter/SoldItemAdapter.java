package com.example.flipper_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flipper_app.R;
import com.example.flipper_app.model.FavoriteItem;
import com.example.flipper_app.model.Item;
import com.example.flipper_app.model.SoldItem;
import com.example.flipper_app.ui.ItemViewModel;
import com.example.flipper_app.ui.favorites.FavoritesViewModel;

import java.util.ArrayList;
import java.util.List;

public class SoldItemAdapter extends RecyclerView.Adapter<SoldItemAdapter.SoldItemHolder> {

    private List<SoldItem> items = new ArrayList<>();
    FavoritesViewModel favoriteItemViewModel;

    public SoldItemAdapter(FragmentActivity activity){
        activity.getApplicationContext();
        favoriteItemViewModel = new ViewModelProvider(activity).get(FavoritesViewModel.class);
    }


    @NonNull
    @Override
    public SoldItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.solditem_cardview, parent, false);
        return new SoldItemHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull SoldItemHolder holder, int position) {
        SoldItem currentItem = items.get(position);
        holder.itemNameTV.setText(currentItem.getName());
        holder.itemInitialPriceTV.setText(String.valueOf(currentItem.getInitialPrice()));
        holder.itemSoldPriceTV.setText(String.valueOf(currentItem.getSoldPrice()));
        holder.itemQuantityTV.setText(String.valueOf(currentItem.getQuantity()));
        holder.itemPlatformTV.setText(currentItem.getPlatform());
        // find image via path
    }

    // Get number of items in list.
    @Override
    public int getItemCount() {
        return items.size();
    }
    public void setItems(List<SoldItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    // Store item data to bind them to their views.
    class SoldItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView itemNameTV;
        private TextView itemInitialPriceTV;
        private TextView itemSoldPriceTV;
        private TextView itemQuantityTV;
        private TextView itemPlatformTV;
        private ImageButton favoriteButton;

        public SoldItemHolder(View itemView) {
            super(itemView);
            itemNameTV = itemView.findViewById(R.id.soldItem_name);
            itemInitialPriceTV = itemView.findViewById(R.id.soldItem_initial_price);
            itemSoldPriceTV = itemView.findViewById(R.id.soldItem_sold_price);
            itemQuantityTV = itemView.findViewById(R.id.soldItem_quantity);
            itemPlatformTV = itemView.findViewById(R.id.soldItem_platform);
            favoriteButton = itemView.findViewById(R.id.favoriteIcon);
            favoriteButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            SoldItem item = items.get(getAdapterPosition());
            String itemName = item.getName();
            double initPrice = item.getInitialPrice();
            double soldPrice = item.getSoldPrice();

            FavoriteItem favoriteItem = new FavoriteItem(itemName, initPrice, soldPrice);
            favoriteItemViewModel.insert(favoriteItem);

        }
    }
}
