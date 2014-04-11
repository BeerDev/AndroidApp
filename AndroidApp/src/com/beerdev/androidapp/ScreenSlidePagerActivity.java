package com.beerdev.androidapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class ScreenSlidePagerActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static int NUM_PAGES;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private static ViewPager mPager;


	private static final String DEBUG_TAG = "MOTION";
    /**
     * The arraylist including all the products.
     */
    private static ArrayList<HashMap<String, String>> prodList;
    
    private static int currentPage;
    
    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);
        
        Intent intent = getIntent();
        
        //Gets the arraylist including all the products
        prodList =(ArrayList<HashMap<String,String>>) intent.getSerializableExtra("contactList");
        
        //Define the number of viewpages that shall be included in the scrollview
        NUM_PAGES = prodList.size();
        
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        /*
        mPager.findViewById(R.layout.activity_screen_slide)
        	.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.i(DEBUG_TAG, "MOTION");
				return true;
			}
        	
        });*/
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * The method returns an arraylist which includes product with its information.
     * @return prodList
     */
    public final static ArrayList<HashMap<String, String>> getArrayList() {
        return prodList;
    }
    
    public static int getCurrentItem(){
    	return mPager.getCurrentItem();
    }
    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
        	ScreenSlidePageFragment myFragment = new ScreenSlidePageFragment();
            Bundle data = new Bundle();
            data.putInt("current_page", position);
            myFragment.setArguments(data);
            return myFragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

    }
}