package com.example.flipper_app.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.flipper_app.ui.addItem.AddItemFragment;
import com.example.flipper_app.ui.inventory.InventoryFragment;
import com.example.flipper_app.ui.sold.SoldFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private static final int NUM_OF_TABS = 2;

    public ViewPagerAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    /*@NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            return new InventoryFragment();
        } else {
            return new SoldFragment();
        }
    }

    // Returns the number of tabs to create.
    @Override
    public int getCount() {
        return 2;
    }

    // Returns the name of each tab.
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return "Inventory";
        } else {
            return "Sold";
        }
    }*/

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){
            return InventoryFragment.newInstance();
        } else {
            return SoldFragment.newInstance();
        }
    }

    @Override
    public int getItemCount() {
        return NUM_OF_TABS;
    }
}
