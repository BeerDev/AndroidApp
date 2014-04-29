package com.beerdev.androidapp;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONException;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.SearchView.OnCloseListener;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import android.widget.Toast;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class SwipeViewActivity extends FragmentActivity implements OnQueryTextListener {
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
    
    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;
    public OnPageChangeListener pageChangeListener;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
        
        final SlidingUpPanelLayout layout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        
        Intent intent = getIntent();

        int pos = intent.getIntExtra("BildID", 0);
        
        //Define the number of viewpages that shall be included in the scrollview
        NUM_PAGES = MainActivity.productList.size();

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
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
                tvBeerName = (TextView) findViewById(R.id.beerName);
                tvBeerPrice = (TextView) findViewById(R.id.beerPrice);
                tvBeerSize = (TextView) findViewById(R.id.beerSize);
                tvBeerPercent = (TextView) findViewById(R.id.beerPercent);
                tvBeerInfo = (TextView) findViewById(R.id.beerInfo);
                
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

    }

    /**
     * A method to create menu-
     * @return true - to create menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView  searchView = (SearchView) menu.findItem(R.id.search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(new OnCloseListener() {
            @Override
            public boolean onClose() {
                System.out.println("Testing. 1, 2, 3...");
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);        
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    		case R.id.menu2:
    			Intent intent = new Intent(this, MenuActivity.class);
    			startActivity(intent);
    			overridePendingTransition(R.animator.slide_in_right,R.animator.slide_out_left);
    			break;
	    	case R.id.sortAlphab:
				Sort.sortAlphabetic();
				Intent intentSortAlpha = new Intent(getApplicationContext(),
						SwipeViewActivity.class);
				//Sending BildID and productList to SwipeViewActivity
				intentSortAlpha.putExtra("BildID", 0);
				startActivity(intentSortAlpha);
	            
				break;
			case R.id.sortPrice:
				Sort.sortPrice();
				Intent intentSortPrice = new Intent(getApplicationContext(),
						SwipeViewActivity.class);
				//Sending BildID and productList to SwipeViewActivity
				intentSortPrice.putExtra("BildID", 0);
				startActivity(intentSortPrice);
	            
				break;
    		case R.id.navListVy:
	    			// Starting single contact activity
					Intent intentList = new Intent(getApplicationContext(),
							ListViewActivity.class);
					//Sending BildID and productList to ListViewActivity
					intentList.putExtra("BildID", 0);

					intentList.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
					intentList.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(intentList);
    	            break;
    	        case R.id.navScrollvy:
    	        	// Starting single contact activity
    				Intent intentSwipe = new Intent(getApplicationContext(),
    						SwipeViewActivity.class);
    				//Sending BildID and productList to SwipeViewActivity
    				intentSwipe.putExtra("BildID", 0);
    				startActivity(intentSwipe);
    	            break;
    	        case R.id.navOmOss:
    				Intent intentOmoss = new Intent(getApplicationContext(),
    						OmOss.class);
    				//Sending BildID and productList to OmOssActivity
    				intentOmoss.putExtra("BildID", 0);
    	        	startActivity(intentOmoss);
    	            break;
    	        case R.id.navOmKistan:
    				Intent intentOmKistan = new Intent(getApplicationContext(),
    						OmKistan.class);
    				//Sending BildID and productList to OmOssActivity
    				intentOmKistan.putExtra("BildID", 0);
    	        	startActivity(intentOmKistan);
    	            break;
    		}
    		return true;
    	}
    	 //---------MENU END---------------
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_STATE_ACTION_BAR_HIDDEN, !getActionBar().isShowing());
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

    @Override
    protected void onPause(){
    	super.onPause();
    	ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info != null && info.isConnectedOrConnecting()) {
			MainActivity.wasOnline = true;
			Log.d("onPauseSwipe", "wasOnline true");
		}else{
			MainActivity.wasOnline = false;
			Log.d("onPauseSwipe", "wasOnline false");
		}
    }
	@Override
	protected void onResume(){
		super.onResume();
		mPager.getAdapter().notifyDataSetChanged();
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info != null && info.isConnectedOrConnecting()) {
			if(MainActivity.wasOnline == false){
				Intent in = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(in);
			}
			Log.d("onResumeSwipe", "isOnline true");
		}else{
			Log.d("onResumeSwipe", "isOnline false");
		}
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		Log.i("Query", "Query");
		return false;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
			try {
				ArrayList<HashMap<String,String>> tempProductList = (ArrayList<HashMap<String, String>>) MainActivity.productList.clone();
				Sort.Filter(newText);
				Log.i("INFO", Integer.toString(MainActivity.productList.size()));
				if(MainActivity.productList.size()==0){
					MainActivity.productList = (ArrayList<HashMap<String, String>>) tempProductList.clone();

					Toast.makeText(this, "Inga resultat", Toast.LENGTH_SHORT).show();
				}
				mPager.getAdapter().notifyDataSetChanged();

				pageChangeListener.onPageSelected(0);
		        mPager.setCurrentItem(0);	 
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return false;
	}

}