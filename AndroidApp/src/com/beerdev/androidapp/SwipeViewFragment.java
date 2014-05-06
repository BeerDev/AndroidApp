package com.beerdev.androidapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class SwipeViewFragment extends Fragment {
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
    public static int NUM_PAGES;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    public static CustomViewPager mPager;

    /**
     * An integer that holds the current page of viewpager, is used in ScreenSlidePageFragment to update textviews.
     */
    public static int mCurrentPageActivity;
    
    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;
    public static OnPageChangeListener pageChangeListener;


    private boolean isChangedStat = false;
    EditText editsearch;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 final ViewGroup swipeView = (ViewGroup) inflater.inflate(R.layout.fragment_swipe, container, false);
		 //setHasOptionsMenu(false);
		 if(!(FragmentManagerActivity.menu == null)){
			 FragmentManagerActivity.menu.findItem(R.id.menu_filter).setVisible(true);
			 FragmentManagerActivity.menu.findItem(R.id.menu_search).setVisible(true);

			 getActivity().findViewById(R.id.search_container).setVisibility(View.INVISIBLE); 
		 }
	     final SlidingUpPanelLayout layout = (SlidingUpPanelLayout) swipeView.findViewById(R.id.sliding_layout);
        Bundle extras = getActivity().getIntent().getExtras();

        int pos = extras.getInt("position", 0);        
        //Define the number of viewpages that shall be included in the scrollview
        NUM_PAGES = MainActivity.productList.size();

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (CustomViewPager) swipeView.findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getChildFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        pageChangeListener = new OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int arg0) { }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) { }

            @Override
            public void onPageSelected(int position) {

                /*Update textviews from here because the minimum length of viewpager is 2
                 *this is only for position 0.*/
                tvBeerName = (TextView) swipeView.findViewById(R.id.beerName);
                tvBeerPrice = (TextView) swipeView.findViewById(R.id.beerPrice);
                tvBeerSize = (TextView) swipeView.findViewById(R.id.beerSize);
                tvBeerPercent = (TextView) swipeView.findViewById(R.id.beerPercent);
                tvBeerInfo = (TextView) swipeView.findViewById(R.id.beerInfo);
                
                tvBeerName.setText(MainActivity.productList.get(position).get("Artikelnamn"));
                tvBeerPrice.setText(MainActivity.productList.get(position).get("Utpris exkl moms")+" kr*");
                tvBeerSize.setText(MainActivity.productList.get(position).get("Storlek")+" ml*");
                tvBeerPercent.setText(MainActivity.productList.get(position).get("Alkoholhalt")+" %");
                tvBeerInfo.setText(MainActivity.productList.get(position).get("Info"));
            	
            }
        };
        mPager.setOnPageChangeListener(pageChangeListener);
        pageChangeListener.onPageSelected(0);
        mPager.setCurrentItem(pos);	    
        return swipeView;
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
        
        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
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