package com.beerdev.androidapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.SearchView.OnCloseListener;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

import com.beerdev.androidapp.ShakeDetector.OnShakeListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class FragmentManagerActivity extends SlidingFragmentActivity implements OnQueryTextListener, OnClickListener, OnCloseListener {
	public static SlidingMenu sm;
	public static Menu menu = null;
	public static boolean mToggleChecked = true;
	public static SearchView searchView;
	private MenuItem navigation, filter, cross, searchItem;
	public static Button nameButton, catButton;
	public static String tagToggleButton, searchText ="";
	
	public static boolean fastScrollEnabled = true;
	public static boolean categorySearchShowInput = true;
	// The following are used for the shake detection
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private ShakeDetector mShakeDetector;

	public static Context globalContext = null;
	
	public static ArrayList<HashMap<String,String>> tempProductList=null; 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
	        MainActivity.productList = (ArrayList<HashMap<String,String>>) savedInstanceState.getSerializable("productList"); 
	    } 
		// ShakeDetector initialization
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mShakeDetector = new ShakeDetector();
		mShakeDetector.setOnShakeListener(new OnShakeListener() {
			@Override
			public void onShake(int count) {
				if (((SwipeViewFragment) getSupportFragmentManager().findFragmentByTag("swipeFrag")).isVisible()&&MainActivity.productList.size()>1){

					Random random = new Random();
					int max=MainActivity.productList.size()-1;
					int randomNumber = random.nextInt(max);
					if(MainActivity.productList.size() == 0){
						MainActivity.productList = (ArrayList<HashMap<String, String>>) tempProductList.clone();
						ImageView ivBeer = (ImageView) findViewById(R.id.ivSwipeImage);
						String image_url = MainActivity.productList.get(0).get("URL");
						ImageLoader imgLoader = new ImageLoader(globalContext);
						imgLoader.DisplayImage(image_url, BaseAdapter.NO_SELECTION,ivBeer);
						SwipeViewFragment.mPager.setSwipeable(false);
					}
					else if(SwipeViewFragment.NUM_PAGES == 1) 
					{
						//Set Image when there is only one Beer showing		
						ImageView ivBeer = (ImageView) findViewById(R.id.ivSwipeImage);
						String image_url = MainActivity.productList.get(0).get("URL");
						ImageLoader imgLoader = new ImageLoader(globalContext);
						imgLoader.DisplayImage(image_url, BaseAdapter.NO_SELECTION,ivBeer);
						
						SwipeViewFragment.mPager.setSwipeable(false);
						SwipeViewFragment.pageChangeListener.onPageSelected(0);
						SwipeViewFragment.mPager.setCurrentItem(0);
					}
					else{
						SwipeViewFragment.mPager.setSwipeable(true);
						SwipeViewFragment.NUM_PAGES = MainActivity.productList.size();
						SwipeViewFragment.mPager.getAdapter().notifyDataSetChanged();
						SwipeViewFragment.pageChangeListener.onPageSelected(randomNumber);
						SwipeViewFragment.mPager.setCurrentItem(randomNumber);
					}
					if(MainActivity.productList.size()>2){
						SwipeViewFragment.mPager.setSwipeable(true);
						
					}
				}
				// handleShakeEvent(count);
			}
		});

		FragmentManagerActivity.globalContext = this;
		// set the Above View
		// set the Behind View
		setContentView(R.layout.fragmentactivity_root);

		FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
		SwipeViewFragment mSwipeFrag = new SwipeViewFragment();
		ListViewFragment mListFrag = new ListViewFragment();
		t.replace(R.id.root_container, mSwipeFrag, "swipeFrag");
		t.add(mListFrag, "listFrag");
		t.hide(mListFrag);
		t.commit();
		

		setBehindContentView(R.layout.menu_frame);
		FragmentTransaction t2 = this.getSupportFragmentManager().beginTransaction();
		MenuFragment mFrag2 = new MenuFragment();
		t2.replace(R.id.menu_frame, mFrag2);
		t2.commit();

		nameButton = (Button) findViewById(R.id.searchbutton_on);
		catButton = (Button) findViewById(R.id.searchbutton_off);
		
		nameButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				FragmentManagerActivity.mToggleChecked=true;
				setToggleButton();
				search();

			}
		});
		
		catButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				FragmentManagerActivity.mToggleChecked=false;
				setToggleButton();
				search();
			}
		});

		// customize the SlidingMenu
		sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.showSecondaryMenu();
		sm.setMode(SlidingMenu.RIGHT);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		getActionBar().setDisplayHomeAsUpEnabled(false);
		getActionBar().setDisplayShowHomeEnabled(false);
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
		FragmentManagerActivity.menu = menu;
		getMenuInflater().inflate(R.menu.navigation_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.menu_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);
        searchView.setOnSearchClickListener(this);
        searchView.setOnCloseListener(this);
        searchView.setIconifiedByDefault(true);
        
        navigation = menu.findItem(R.id.menu_navigation);
        filter = menu.findItem(R.id.menu_filter);
        cross = menu.findItem(R.id.menu_close_search);
        searchItem = menu.findItem(R.id.menu_search);
       
        navigation.setVisible(true);
        navigation.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        filter.setVisible(true);
        filter.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        cross.setVisible(false);
        searchItem.setVisible(true);
        searchItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        
        return super.onCreateOptionsMenu(menu);        
    }

	@Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	    	switch (item.getItemId()) {
	    		case R.id.menu_navigation:
	    			toggle();
	    			
	    			 //Hide inputmethodmanager
	    			 InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
	    			if(imm.isActive()){
	    				imm.hideSoftInputFromWindow(findViewById(R.id.root_view).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	    			}
	    			break;
	    		case R.id.menu_filter_sortName:
	    			//FragmentManagerActivity.fastScrollEnabled = true;
	    			Sort.sortAlphabetic();
	    			ListViewFragment listFragName = (ListViewFragment) getSupportFragmentManager().findFragmentByTag("listFrag"); 
	    			if(listFragName.isVisible()){
	    		        listFragName.getListView().setFastScrollEnabled(true);
	    		        listFragName.getListView().setFastScrollAlwaysVisible(true);
	    		        ListViewFragment.fastScrollAdapter.notifyDataSetChanged();
	    			}
	    			else if(((SwipeViewFragment) getSupportFragmentManager().findFragmentByTag("swipeFrag")).isVisible())
	    			{
	    				SwipeViewFragment.pageChangeListener.onPageSelected(0);
				        SwipeViewFragment.mPager.setCurrentItem(0);
	    				SwipeViewFragment.mPager.getAdapter().notifyDataSetChanged();
	    			}
	    			break;
	    		case R.id.menu_filter_sortPrice:
	    			//FragmentManagerActivity.fastScrollEnabled = false;
	    			Sort.sortPrice();
	    			ListViewFragment listFragPrice = (ListViewFragment) getSupportFragmentManager().findFragmentByTag("listFrag"); 
	    			if(listFragPrice.isVisible()){
	    		        listFragPrice.getListView().setFastScrollEnabled(true);
	    		        listFragPrice.getListView().setFastScrollAlwaysVisible(true);
	    		        ListViewFragment.fastScrollAdapter = new FastScrollAdapter(this, MainActivity.productList);
	    		        ListViewFragment.lv.setAdapter(ListViewFragment.fastScrollAdapter);
	    		        ListViewFragment.fastScrollAdapter.notifyDataSetChanged();
	    			}
	    			else if(((SwipeViewFragment) getSupportFragmentManager().findFragmentByTag("swipeFrag")).isVisible())
	    			{
	    				SwipeViewFragment.pageChangeListener.onPageSelected(0);
				        SwipeViewFragment.mPager.setCurrentItem(0);
	    				SwipeViewFragment.mPager.getAdapter().notifyDataSetChanged();
	    				
	    			}
	    			break;
	    		case R.id.menu_close_search:
	    			searchView.setQuery("", true);
	    			MainActivity.productList = (ArrayList<HashMap<String, String>>) MainActivity.completeProductList.clone();
	    			if(((SwipeViewFragment) getSupportFragmentManager().findFragmentByTag("swipeFrag")).isVisible())
	    			{
	    				SwipeViewFragment.pageChangeListener.onPageSelected(0);
				        SwipeViewFragment.mPager.setCurrentItem(0);
	    				SwipeViewFragment.mPager.getAdapter().notifyDataSetChanged();
	    				
	    			}
	    		  	ListViewFragment.fastScrollAdapter.notifyDataSetChanged();
				     
	    			cross.setVisible(false);
	    			searchView.setIconified(true);
	    			searchItem.setVisible(true);
	    			navigation.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
	    			 InputMethodManager imm2 = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
		    			if(imm2.isActive()){
		    				imm2.hideSoftInputFromWindow(findViewById(R.id.root_view).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		    			}
	    			break;
	    		}
	    		return true;
	    	}
	 @Override
		public boolean onQueryTextSubmit(String query) {
			findViewById(R.id.search_container).setVisibility(View.INVISIBLE);
			setLayoutMargins(findViewById(R.id.root_view), this);
			InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
		    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
		    cross.setVisible(true);
		    filter.setVisible(true);
		    searchItem.setVisible(false);
		    navigation.setVisible(true);
			navigation.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
			return false;
		}

		@Override
		public boolean onQueryTextChange(String newText) {
			findViewById(R.id.search_container).setVisibility(View.VISIBLE);
			setLayoutMargins(findViewById(R.id.root_view), this);
			InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
		    if(!imm.isActive() && categorySearchShowInput){
				imm.toggleSoftInput(0, InputMethodManager.SHOW_IMPLICIT);
		    }
		    searchText = newText;
		   // SwipeViewFragment.NUM_PAGES = MainActivity.productList.size();
		    search();
			return false;
		}
		@Override
		public void onClick(View v) {
			findViewById(R.id.search_container).setVisibility(View.VISIBLE);
			setLayoutMargins(findViewById(R.id.root_view), this);
			filter.setVisible(true); 
			filter.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
			navigation.setVisible(false);
			//navigation.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		}
		@Override
		public boolean onClose() {
			MainActivity.productList=(ArrayList<HashMap<String, String>>)MainActivity.completeProductList.clone();
			findViewById(R.id.search_container).setVisibility(View.INVISIBLE);
			setLayoutMargins(findViewById(R.id.root_view), this);	
			SwipeViewFragment.mPager.setSwipeable(true); 
			InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
		    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
		    navigation.setVisible(true);
	        navigation.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
	        filter.setVisible(true);
	        filter.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
	        cross.setVisible(false);
	        searchItem.setVisible(true);
	        searchItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		    return false;
		}
	@SuppressWarnings("unchecked")
	private void search(){
		
		try {
			tempProductList = (ArrayList<HashMap<String, String>>) MainActivity.productList.clone();

			if(mToggleChecked){
				tagToggleButton = "Artikelnamn";
			}
			else{
				tagToggleButton = "Kategori";
			}

			Sort.Filter(searchText, tagToggleButton);
			
			updateViewsAfterFilter();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	private void updateViewsAfterFilter(){
		
		if(((SwipeViewFragment) getSupportFragmentManager().findFragmentByTag("swipeFrag")).isVisible()){
			if(MainActivity.productList.size() == 0){
				MainActivity.productList = (ArrayList<HashMap<String, String>>) tempProductList.clone();
				ImageView ivBeer = (ImageView) findViewById(R.id.ivSwipeImage);
				String image_url = MainActivity.productList.get(0).get("URL");
				ImageLoader imgLoader = new ImageLoader(this);
				imgLoader.DisplayImage(image_url, BaseAdapter.NO_SELECTION,ivBeer);
				SwipeViewFragment.mPager.setSwipeable(false);
			}
			else if(SwipeViewFragment.NUM_PAGES == 1) 
			{
				//Set Image when there is only one Beer showing		
				ImageView ivBeer = (ImageView) findViewById(R.id.ivSwipeImage);
				String image_url = MainActivity.productList.get(0).get("URL");
				ImageLoader imgLoader = new ImageLoader(this);
				imgLoader.DisplayImage(image_url, BaseAdapter.NO_SELECTION,ivBeer);
				
				SwipeViewFragment.mPager.setSwipeable(false);
				SwipeViewFragment.pageChangeListener.onPageSelected(0);
				SwipeViewFragment.mPager.setCurrentItem(0);

				SwipeViewFragment.NUM_PAGES = MainActivity.productList.size();
			}
			else{
				SwipeViewFragment.mPager.setSwipeable(true);
				SwipeViewFragment.NUM_PAGES = MainActivity.productList.size();
				SwipeViewFragment.mPager.getAdapter().notifyDataSetChanged();
				SwipeViewFragment.pageChangeListener.onPageSelected(0);
				SwipeViewFragment.mPager.setCurrentItem(0);
			}
		}
		if(((ListViewFragment) getSupportFragmentManager().findFragmentByTag("listFrag")).isVisible()){
			ListViewFragment.fastScrollAdapter.notifyDataSetChanged();
		}
	}
	public static void setLayoutMargins(View rootView, FragmentActivity activity){
			FrameLayout.LayoutParams relLay = (FrameLayout.LayoutParams) rootView.findViewById(R.id.root_container).getLayoutParams();
			if(((ListViewFragment) activity.getSupportFragmentManager().findFragmentByTag("listFrag")).isVisible() 
					&& (rootView.findViewById(R.id.search_container).getVisibility() == View.VISIBLE)){
				int dpValue = 50; // margin in dips
				float d = activity.getResources().getDisplayMetrics().density;
				int margin = (int)(dpValue * d); 
				relLay.setMargins(0, margin, 0, 0);
				rootView.findViewById(R.id.root_container).setLayoutParams(relLay);
			}
			else{
				relLay.setMargins(0, 0, 0, 0);
				rootView.findViewById(R.id.root_container).setLayoutParams(relLay);
			}
	}

	public static void setToggleButton(){
		int white=FragmentManagerActivity.globalContext.getResources().getColor(R.color.white);
		int halfTrans = FragmentManagerActivity.globalContext.getResources().getColor(R.color.halfTrans);
		if(mToggleChecked){
			nameButton.setBackgroundResource(R.drawable.button_selected);
			nameButton.setTextColor(halfTrans);
			catButton.setBackgroundResource(R.drawable.button_unselected);
			catButton.setTextColor(white);
		}
		else{
			nameButton.setBackgroundResource(R.drawable.button_unselected);
			nameButton.setTextColor(white);
			catButton.setBackgroundResource(R.drawable.button_selected);
			catButton.setTextColor(halfTrans);
		}
	}
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		Boolean foundProduct = false;
		if(resultCode == RESULT_OK){
			
			  if (scanResult != null) {
				  
				  for(int i = 0; i < MainActivity.completeProductList.size();i++){
					  String barcode = MainActivity.completeProductList.get(i).get(MainActivity.TAG_BARCODE);
					  if(barcode.compareTo(scanResult.getContents())==0){
						  MainActivity.productList = (ArrayList<HashMap<String, String>>) MainActivity.completeProductList.clone();
		        			FragmentManagerActivity.searchView.setQuery("", false);
		        			FragmentManagerActivity.searchView.setIconified(true);	
							SwipeViewFragment.pageChangeListener.onPageSelected(i);
							SwipeViewFragment.mPager.setCurrentItem(i);
							
							foundProduct = true;
						break;
					  }
				  }
				  
				  if(!foundProduct){
					  Toast.makeText(this, "Produkten finns ej i sortimentet!", Toast.LENGTH_LONG).show();				
				  }	
			  }
		}
		else if(resultCode == RESULT_CANCELED){
			  Toast.makeText(this, "Ingen produkt blev skannad!", Toast.LENGTH_LONG).show();	  	
		}
	}
	@Override
	protected void onPause(){
		super.onPause();
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info != null && info.isConnectedOrConnecting()) {
			MainActivity.wasOnline = true;
		}else{
			MainActivity.wasOnline = false;
		}
		mSensorManager.unregisterListener(mShakeDetector);
	}
	@Override
	protected void onResume(){
		super.onResume();
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info != null && info.isConnectedOrConnecting()) {
			if(MainActivity.wasOnline == false){
				Intent in = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(in);
			}
		}
		mSensorManager.registerListener(mShakeDetector, mAccelerometer,    SensorManager.SENSOR_DELAY_UI);
	}

	@Override
	  protected void onStop() {
	    super.onStop();
	  }

	  @Override
	  protected void onDestroy() {
	    super.onDestroy();
	  }

	@Override
	  protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		if (savedInstanceState != null) {
	        MainActivity.productList = (ArrayList<HashMap<String,String>>) savedInstanceState.getSerializable("productList"); 
	    }
	  }

	  @Override
	  protected void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    outState.putSerializable("productList", MainActivity.productList);
	  }
}
