package com.example.flipper_app.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flipper_app.R;
import com.example.flipper_app.model.FavoriteItem;
import com.example.flipper_app.model.Item;
import com.example.flipper_app.model.SoldItem;
import com.example.flipper_app.ui.favorites.FavoritesViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FavoriteItemAdapter extends RecyclerView.Adapter<FavoriteItemAdapter.FavoriteItemHolder> {

    private List<FavoriteItem> items = new ArrayList<>();
    private FavoritesViewModel favoritesViewModel;
    private File imgFile;

    public FavoriteItemAdapter(FavoritesViewModel favoritesViewModel){
        this.favoritesViewModel = favoritesViewModel;
    }

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
        // Check if the selected item has an image.
        if(currentItem.getPicPath() != null){
            imgFile = new File(currentItem.getPicPath());
        }
        // Check if the image file exists before attempting to display the image.
        if(imgFile != null) {
            Bitmap imageBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            holder.itemImageView.setImageBitmap(imageBitmap);
        }
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

    public void deleteItemAt(int position){
        items.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, items.size());
    }

    // Store item data to bind them to their views.
    class FavoriteItemHolder extends RecyclerView.ViewHolder {
        private TextView itemNameTV;
        private TextView itemInitialPriceTV;
        private TextView itemSoldPriceTV;
        private ImageView itemImageView;
        private ImageButton deleteButton;
        private ImageButton relistButton;


        public FavoriteItemHolder(View itemView) {
            super(itemView);
            itemNameTV = itemView.findViewById(R.id.favoriteItem_name);
            itemInitialPriceTV = itemView.findViewById(R.id.favoriteItem_initial_price);
            itemSoldPriceTV = itemView.findViewById(R.id.favoriteItem_sold_price);
            itemImageView = itemView.findViewById(R.id.favoriteItem_pic);
            deleteButton = itemView.findViewById(R.id.deleteIcon);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FavoriteItem item = items.get(getAdapterPosition());
                    deleteItemAt(getAdapterPosition());
                    favoritesViewModel.delete(item);
                }
            });

            // Allow user to relist a favorite item.
            relistButton = itemView.findViewById(R.id.relistIcon);
            relistButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(R.id.navigation_add_item);
                }
            });
        }
    }
}
