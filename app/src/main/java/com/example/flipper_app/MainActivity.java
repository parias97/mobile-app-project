package com.example.flipper_app;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;



public class MainActivity extends AppCompatActivity {

    AppBarConfiguration appBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

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

        NavDestination currDest = navController.getCurrentDestination();
        // to do: call startActivityForResult() when the user has selected the AddItem
        // menu item. Once the user has saved the item in the AddItem fragment, display
        // the item in the inventory list.

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        // Navigate up to the top-level destination
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


}