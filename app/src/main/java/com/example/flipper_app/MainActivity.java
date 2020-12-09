package com.example.flipper_app;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.flipper_app.ui.AddToSoldFragment;
import com.example.flipper_app.ui.inventory.InventoryFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.flipper_app.model.Item;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;
import com.example.flipper_app.adapter.ViewPagerAdapter;
import com.google.android.material.internal.NavigationMenu;

public class MainActivity extends AppCompatActivity {

    AppBarConfiguration appBarConfiguration;
    BottomNavigationView navView;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);

        /**
         * Find the navController (controls which views to display in NavHost) within NavHost
         * (fragment container).
         **/
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        /**
         * Instantiate appBarConfiguration to define which fragments are the top-level fragments.
         * This will allow us to go up when viewing non-top-level fragments.
         **/
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        clearSelectedMenuItems(navView);
        // Navigate up to the top-level destination
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void clearSelectedMenuItems(BottomNavigationView navView){
        Menu menu = navView.getMenu();
        MenuItem item;
        int menuItems = menu.size();

        Log.d("clearing", "true");

        for(int i = 0; i < menuItems; i++){
            item = menu.getItem(i);
            if(item.isChecked()) {
                item.setChecked(false);
            }
        }

    }
}