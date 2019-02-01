package com.advantech.mobile.fmregister.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;

import com.advantech.mobile.fmregister.R;

/**
 * Created by ADVANTECH CONSULTING on 4/23/2018.
 */

public class RegisterAssociationViewPager extends AppCompatActivity {

    public boolean clearText;
    /*the number op pager to show in this demo*/
    private static final int NUM_PAGES = 3;

    //tab layout
    private TabLayout mTabLayout;

    //toolbar
    private Toolbar toolbar;




    /*The pager widget yto handle animation and allows sweiping horizontally to access previous and
    next wizard*/
    private ViewPager viewPager;


    /*the pager adapter wich provides pager to the view*/
    private PagerAdapter pagerAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.association_register_viewpager);

        ////Action Bar
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setTitle(R.string.assoc_reg);
       // String title = actionBar.getTitle().toString();




        mTabLayout = (TabLayout) findViewById(R.id.nav_assoc_tabs);
        mTabLayout.addTab(mTabLayout.newTab().setText(this.getString(R.string.assoc_details)));
        mTabLayout.addTab(mTabLayout.newTab().setText(this.getString(R.string.assoc_details2)));
        mTabLayout.addTab(mTabLayout.newTab().setText(this.getString(R.string.review)));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //instantiate view pager adapter
        viewPager = (ViewPager) findViewById(R.id.register_assoc_viewpager);

        pagerAdapter = new ScreenSlidePageAdapter(getSupportFragmentManager(), NUM_PAGES);
        viewPager.setAdapter(pagerAdapter);



//THis method  deactivates the swap on the view pager
viewPager.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return true;
    }
});

    }
    //This Method will be called when user clicks "Next' or previous Button -- for the tabbed fragment only
    public void setCurrentItem(int item, boolean smoothScroll){

        viewPager.setCurrentItem(item,smoothScroll);
    }

    //This method highlights the active tab when user is navigating with the view pager
    public void activateTab(){
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            //if user is in the first page,allow system to handle the back button
            super.onBackPressed();
        } else {
            //otherwise select th eprevious page
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }

    }

    /**
     * A simple pager adapter that represents 3ScreenSlidePageFragment objects, in
     * sequence.
     */

    private class ScreenSlidePageAdapter extends FragmentStatePagerAdapter {
        int NUM_PAGES;

        public ScreenSlidePageAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.NUM_PAGES = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new AssociationDemoFragment();
                case 1:
                    return new AssociationRegionFragment();

                case  2:
                    return new ReviewAssociationData();

                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }


    }
}




