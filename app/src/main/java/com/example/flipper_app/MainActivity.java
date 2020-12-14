package com.example.flipper_app;

import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.File;
import java.io.IOException;

import static com.example.flipper_app.ui.addItem.AddItemFragment.rotateImage;

public class MainActivity extends AppCompatActivity {

    AppBarConfiguration appBarConfiguration;
    BottomNavigationView navView;
    public static double profit = 0;
    public static int sold = 0;
    public static int listed = 0;


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

    // Using source AS-IS. This code provides a way of changing the orientation of an
    // image once captured.
    // Source: https://stackoverflow.com/questions/14066038/why-does-an-image-captured-using-camera-intent-gets-rotated-on-some-devices-on-a
    public static Bitmap stageForRotation(Bitmap image, File imageFile){
        ExifInterface exif = null;

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                exif = new ExifInterface(imageFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
        Bitmap rotatedBitmap = null;

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                rotatedBitmap = rotateImage(image, 90);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                rotatedBitmap = rotateImage(image, 180);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                rotatedBitmap = rotateImage(image, 270);
                break;
            default:
                rotatedBitmap = image;
                break;
        }
        return rotatedBitmap;
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