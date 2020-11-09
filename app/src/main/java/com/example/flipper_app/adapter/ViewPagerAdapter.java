package com.example.flipper_app.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.flipper_app.ui.inventory.InventoryFragment;
import com.example.flipper_app.ui.sold.SoldFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
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
    }
}
