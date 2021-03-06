package com.example.flipper_app.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flipper_app.MainActivity;
import com.example.flipper_app.R;
import com.example.flipper_app.model.Item;
import com.example.flipper_app.ui.ItemViewModel;
import com.example.flipper_app.ui.inventory.InventoryFragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.example.flipper_app.model.SoldItem;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {

    private List<Item> items = new ArrayList<>();
    private ItemViewModel itemViewModel;
    public static Item autofill;

    public ItemAdapter(ItemViewModel itemViewModel){
        this.itemViewModel = itemViewModel;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, final int position) {
        Item currentItem = items.get(position);
        File imgFile = new File(currentItem.getPicturePath());
        holder.itemNameTV.setText(currentItem.getName());
        String formattedInitPrice =  String.format("%.2f", currentItem.getInitialPrice());
        holder.itemInitialPriceTV.setText(formattedInitPrice);
        holder.itemQuantityTV.setText(String.valueOf(currentItem.getQuantity()));
        holder.itemPlatformTV.setText(currentItem.getPlatform());
        if(imgFile.exists()) {
            Bitmap imageBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            Bitmap rotatedBitmap = MainActivity.stageForRotation(imageBitmap, imgFile);
            holder.itemImageView.setImageBitmap(rotatedBitmap);
        }
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

    public void deleteItemAt(int position){
        items.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, items.size());
    }

    // Store item data to bind them to their views.
    class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView itemNameTV;
        private TextView itemInitialPriceTV;
        private TextView itemQuantityTV;
        private TextView itemPlatformTV;
        private ImageView itemImageView;
        private ImageButton soldButton;
        private ImageButton deleteButton;

        public ItemHolder(View itemView) {
            super(itemView);
            itemNameTV = itemView.findViewById(R.id.item_name);
            itemInitialPriceTV = itemView.findViewById(R.id.item_initial_price);
            itemQuantityTV = itemView.findViewById(R.id.item_quantity);
            itemPlatformTV = itemView.findViewById(R.id.item_platform);
            itemImageView = itemView.findViewById(R.id.item_pic);

            soldButton = itemView.findViewById(R.id.soldIcon);
            deleteButton = itemView.findViewById(R.id.deleteIcon);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Item item = items.get(getAdapterPosition());
                    deleteItemAt(getAdapterPosition());
                    itemViewModel.delete(item);
                }
            });

            soldButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Item item = items.get(getAdapterPosition());
            String itemName = item.getName();
            double initPrice = item.getInitialPrice();
            int quantity = item.getQuantity();
            String platform = item.getPlatform();
            String desc = item.getDesc();
            String picPath = item.getPicturePath();

            autofill = new Item(item.getId(), itemName, desc, initPrice, quantity, platform, picPath, true);
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.navigation_add_to_sold);
        }
    }
}

