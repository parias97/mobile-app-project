package com.example.flipper_app.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flipper_app.R;
import com.example.flipper_app.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TabFragment extends Fragment {

    private ViewPager2 viewPager;
    private TabLayout tabs;

    public TabFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        // Get the view for the view pager and the tab layout
        viewPager = view.findViewById(R.id.viewpager_main);
        tabs = view.findViewById(R.id.tabs_main);

        /* Setup the tab layout with the view pager using the adapter. The adapter will
         * change the fragments that correspond to each tab.
         */
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity());
        viewPager.setAdapter(adapter);
        //tabs.setupWithViewPager(viewPager);
        new TabLayoutMediator(tabs, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        if(position == 0){
                            tab.setText("Inventory");
                        } else {
                            tab.setText("Sold");
                        }
                    }
                }).attach();
        return view;
    }

}