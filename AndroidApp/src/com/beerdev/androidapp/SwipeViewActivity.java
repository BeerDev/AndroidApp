package com.beerdev.androidapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nineoldandroids.view.animation.AnimatorProxy;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelSlideListener;

public class SwipeViewActivity extends FragmentActivity {
    private static final String TAG = "DemoActivity";

    /**
     * A textview to show the name of the beer
     */
    public static TextView tvBeerName;
    
    /**
     * A textview to show the price of the beer
     */
    public static TextView tvBeerPrice;
    
    /**
     * A textview to show the size of the beer
     */
    public static TextView tvBeerSize;
    
    /**
     * A textview to show the percent of the beer
     */
    public static TextView tvBeerPercent;
    
    /**
     * A textview to show the description of the beer
     */
    public static TextView tvBeerInfo;
    
    public static final String SAVED_STATE_ACTION_BAR_HIDDEN = "saved_state_action_bar_hidden";
    
    /**
     * The number of pages to show in the viewpager.
     */
    private static int NUM_PAGES;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    public static ViewPager mPager;

    /**
     * An integer that holds the current page of viewpager, is used in ScreenSlidePageFragment to update textviews.
     */
    public static int mCurrentPageActivity;
    
    private static ArrayList<HashMap<String, String>> productList;
    
    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_swipe);

        final SlidingUpPanelLayout layout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        layout.setPanelSlideListener(new PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i(TAG, "onPanelSlide, offset " + slideOffset);
                setActionBarTranslation(layout.getCurrentParalaxOffset());
            }

            @Override
            public void onPanelExpanded(View panel) {
                Log.i(TAG, "onPanelExpanded");

            }

            @Override
            public void onPanelCollapsed(View panel) {
                Log.i(TAG, "onPanelCollapsed");

            }

            @Override
            public void onPanelAnchored(View panel) {
                Log.i(TAG, "onPanelAnchored");

            }
        });

        boolean actionBarHidden = savedInstanceState != null ?
                savedInstanceState.getBoolean(SAVED_STATE_ACTION_BAR_HIDDEN, false): false;
        if (actionBarHidden) {
            getActionBar().hide();
        }
        Intent intent = getIntent();

        int pos = intent.getIntExtra("BildID", 0);
        Log.i("POS", Integer.toString(pos));
        //Gets the arraylist including all the products
        productList =(ArrayList<HashMap<String,String>>) intent.getSerializableExtra("productList");
        
        //Define the number of viewpages that shall be included in the scrollview
        NUM_PAGES = productList.size();

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        OnPageChangeListener pageChangeListener = new OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int arg0) { }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) { }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                case 0: 
                	mCurrentPageActivity = 0;

                    /*Update textviews from here because the minimum length of viewpager is 2
                     *this is only for position 0.*/
                    tvBeerName = (TextView) findViewById(R.id.beerName);
                    tvBeerPrice = (TextView) findViewById(R.id.beerPrice);
                    tvBeerSize = (TextView) findViewById(R.id.beerSize);
                    tvBeerPercent = (TextView) findViewById(R.id.beerPercent);
                    tvBeerInfo = (TextView) findViewById(R.id.beerInfo);
                    
                    tvBeerName.setText(productList.get(0).get("Artikelnamn"));
                    tvBeerPrice.setText(productList.get(0).get("Utpris exkl moms")+" kr*");
                    tvBeerSize.setText(productList.get(0).get("Storlek")+" ml*");
                    tvBeerPercent.setText(productList.get(0).get("Alkoholhalt")+" %");
                    tvBeerInfo.setText(productList.get(0).get("Info"));
                	break;

                default:
                	mCurrentPageActivity = position;
                	break;
                }
            }
        };
        mPager.setOnPageChangeListener(pageChangeListener);
        pageChangeListener.onPageSelected(0);
        mPager.setCurrentItem(pos);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_STATE_ACTION_BAR_HIDDEN, !getActionBar().isShowing());
    }

    /**
     * A method to create menu-
     * @return true - to create menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    		case R.id.navListVy:
    	        	startActivity(new Intent(this, MainActivity.class));
    	            break;
    	        case R.id.navScrollvy:
    	        	// Starting single contact activity
    				Intent in = new Intent(getApplicationContext(),
    						SwipeViewActivity.class);
    				
    				//Sending BildID and ContactList to ScreenSlidePagerActivity
    				in.putExtra("BildID", 0);
    				in.putExtra("productList", productList);
    				startActivity(in);
    	            break;
    	        case R.id.navOmOss:
    				Intent omoss = new Intent(getApplicationContext(),
    						OmOss.class);
    				
    				//Sending BildID and ContactList to ScreenSlidePagerActivity
    				omoss.putExtra("BildID", 0);
    				omoss.putExtra("productList", productList);
    	        	startActivity(omoss);
    	            break;
    	        }
    	        return true;
    	        }
    	 //---------MENU END---------------
    
    /**
     * The method returns an arraylist which includes product with its information.
     * @return prodList
     */
    public final static ArrayList<HashMap<String, String>> getArrayList() {
        return productList;
    }
    
    /**
     * A method related to the SlidingUpPanel that makes the actionbar slideup when the panel is sliding up.
     * @param y - number of pixels that the actionbar will slide
     */
    @SuppressLint("NewApi")
    public void setActionBarTranslation(float y) {
        // Figure out the actionbar height
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
        }
        // A hack to add the translation to the action bar
        ViewGroup content = ((ViewGroup) findViewById(android.R.id.content).getParent());
        int children = content.getChildCount();
        for (int i = 0; i < children; i++) {
            View child = content.getChildAt(i);
            if (child.getId() != android.R.id.content) {
                if (y <= -actionBarHeight) {
                    child.setVisibility(View.GONE);
                } else {
                    child.setVisibility(View.VISIBLE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        child.setTranslationY(y);
                    } else {
                        AnimatorProxy.wrap(child).setTranslationY(y);
                    }
                }
            }
        }
    }
    /**
     * A private PagerAdapter class, related to the viewpager
     * @author BeerDev
     *
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * @return Returns a new fragment for the viewpager.
         */
        @Override
        public Fragment getItem(int position) {
            return ScreenSlidePageFragment.create(position);
        }
        
        /**
         * Gets the number of pages for the viewpager.
         * @return NUM_PAGES - Number of pages for the viewpager
         */
        @Override
        public int getCount() {
            return NUM_PAGES;
        }

    }
}