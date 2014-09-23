package com.beerdev.androidapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelSlideListener;

public class SwipeViewFragment extends ListFragment {

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
    
    public static TextView tvBeerBrew;

    public static TextView tvBeerType;
    
    public static int posi;
    
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

    public static ListAdapter SwipeShoppingAdapter;
	public static ListView lv;

	FSmallArrayAdapter arrayAdapter;
    

    EditText editsearch;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final ViewGroup swipeView = (ViewGroup) inflater.inflate(R.layout.fragment_swipe, container, false);
		 //setHasOptionsMenu(false);
		 if(!(FragmentManagerActivity.menu == null)){
			 if(!(FragmentManagerActivity.menu.findItem(R.id.menu_filter).isVisible())){
				 if(FragmentManagerActivity.searchView.isIconified()){
					 FragmentManagerActivity.menu.findItem(R.id.menu_filter).setVisible(true); 
				 }
				 FragmentManagerActivity.menu.findItem(R.id.menu_search).setVisible(true);
			 }
			 getActivity().findViewById(R.id.search_container).setVisibility(View.INVISIBLE); 
			 FragmentManagerActivity.setLayoutMargins(getActivity().findViewById(R.id.root_view), getActivity());
		 }

	     final SlidingUpPanelLayout mLayout = (SlidingUpPanelLayout) swipeView.findViewById(R.id.sliding_layout);
	        
	     
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
	    	        android.R.layout.simple_list_item_1, MainActivity.shoppingProductList);
	    	    setListAdapter(adapter);
	 
	    	    //---
	   
	    //	    arrayAdapter = new FSmallArrayAdapter(getActivity(),R.layout.fragment_swipe,MainActivity.productList.get(0));
	    //	    setListAdapter(arrayAdapter);
	       
	    	    
	    	    //--
	     
        Bundle extras = getActivity().getIntent().getExtras();
        mLayout.setPanelSlideListener(new PanelSlideListener() {
        	ImageView ivArrow = (ImageView) swipeView.findViewById(R.id.ivUpPanelArrow);
            LinearLayout llUpPanel = (LinearLayout) swipeView.findViewById(R.id.llSwipeSlidingUpPanel);
            
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
            	
            }

            @Override
            public void onPanelExpanded(View panel) {
                ivArrow.setBackgroundResource(R.drawable.downn);
            }

            @Override
            public void onPanelCollapsed(View panel) {
                ivArrow.setBackgroundResource(R.drawable.upn);
            }

            @Override
            public void onPanelAnchored(View panel) {

            }

        });
        
        
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
            	posi= position;
                /*Update textviews from here because the minimum length of viewpager is 2
                 *this is only for position 0.*/
                tvBeerName = (TextView) swipeView.findViewById(R.id.beerName);
                tvBeerPrice = (TextView) swipeView.findViewById(R.id.beerPrice);
                tvBeerSize = (TextView) swipeView.findViewById(R.id.beerSize);
                tvBeerPercent = (TextView) swipeView.findViewById(R.id.beerPercent);
                tvBeerInfo = (TextView) swipeView.findViewById(R.id.beerInfo);
                tvBeerType = (TextView) swipeView.findViewById(R.id.beerType);
                tvBeerBrew= (TextView) swipeView.findViewById(R.id.beerBrew);
                
                tvBeerName.setText(MainActivity.productList.get(position).get(MainActivity.TAG_NAME));
                tvBeerPrice.setText(MainActivity.productList.get(position).get(MainActivity.TAG_PRICE)+" kr*");
                tvBeerSize.setText(MainActivity.productList.get(position).get(MainActivity.TAG_SIZE)+" ml");
                tvBeerPercent.setText(MainActivity.productList.get(position).get(MainActivity.TAG_PERC)+" %");
                tvBeerInfo.setText(MainActivity.productList.get(position).get(MainActivity.TAG_INFO));
                tvBeerType.setText(MainActivity.productList.get(position).get(MainActivity.TAG_TYPE));
                tvBeerBrew.setText(MainActivity.productList.get(position).get(MainActivity.TAG_BREW));
            	
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
