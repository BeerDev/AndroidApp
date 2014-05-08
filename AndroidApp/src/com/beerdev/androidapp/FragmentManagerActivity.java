package com.beerdev.androidapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.json.JSONException;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.SearchView.OnCloseListener;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

import com.beerdev.androidapp.ShakeDetector.OnShakeListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class FragmentManagerActivity extends SlidingFragmentActivity implements OnQueryTextListener, OnClickListener, OnCloseListener {
	public static SlidingMenu sm;
	public static Menu menu = null;
	public static boolean mToggleChecked = true;
	public static SearchView searchView;
	private MenuItem filter;
	public static Button nameButton, catButton;
	public static String tagToggleButton, searchText ="";

	// The following are used for the shake detection
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private ShakeDetector mShakeDetector;

	public static Context globalContext = null;

	@Override 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// ShakeDetector initialization
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mShakeDetector = new ShakeDetector();
		mShakeDetector.setOnShakeListener(new OnShakeListener() {

			public void onShake(int count) {
				if (((SwipeViewFragment) getSupportFragmentManager().findFragmentByTag("swipeFrag")).isVisible()){

					Random random = new Random();
					int min=0;
					int max=SwipeViewFragment.NUM_PAGES-1;
					int randomNumber = random.nextInt(max);
					
					SwipeViewFragment.pageChangeListener.onPageSelected(randomNumber);
					SwipeViewFragment.mPager.setCurrentItem(randomNumber);
					System.out.println (randomNumber);
					Log.i(null, "hola");	

				}
				else {
					Log.i(null, "neeej");
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
		this.menu = menu;
		getMenuInflater().inflate(R.menu.navigation_menu, menu);
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		searchView = (SearchView) menu.findItem(R.id.menu_search)
				.getActionView();
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));
		searchView.setOnQueryTextListener(this);
		searchView.setOnSearchClickListener(this);
		searchView.setOnCloseListener(this);
		filter = menu.findItem(R.id.menu_filter);
		searchView.setIconifiedByDefault(true);
		//searchView.setIconified(true);
		return super.onCreateOptionsMenu(menu);        
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_navigation:
			toggle();
			break;
		case R.id.menu_filter_sortName:
			Sort.sortAlphabetic();
			updateViewsAfterFilter();
			break;
		case R.id.menu_filter_sortPrice:
			Sort.sortPrice();
			updateViewsAfterFilter();
			break;
		}
		return true;
	}
	@Override
	public boolean onQueryTextSubmit(String query) {
		findViewById(R.id.search_container).setVisibility(View.INVISIBLE);
		InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

		return false;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		findViewById(R.id.search_container).setVisibility(View.VISIBLE);
		setLayoutMargins();

		InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
		if(!imm.isActive()){
			imm.toggleSoftInput(0, InputMethodManager.SHOW_IMPLICIT);
		}
		searchText = newText;
		search();
		return false;
	}
	@Override
	public void onClick(View v) {

		findViewById(R.id.search_container).setVisibility(View.VISIBLE);
		setLayoutMargins();
		filter.setVisible(false);
	}
	@Override
	public boolean onClose() {
		findViewById(R.id.search_container).setVisibility(View.INVISIBLE);
		setLayoutMargins();
		SwipeViewFragment.mPager.setSwipeable(true); 
		filter.setVisible(true);
		filter.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
		return false;
	}
	private void search(){
		try {
			ArrayList<HashMap<String,String>> tempProductList = (ArrayList<HashMap<String, String>>) MainActivity.productList.clone();

			if(mToggleChecked){
				tagToggleButton = "Artikelnamn";
			}
			else{
				tagToggleButton = "Kategori";
			}

			Sort.Filter(searchText, tagToggleButton);
			Log.i("INFO", Integer.toString(MainActivity.productList.size()));
			if(MainActivity.productList.size()==0 && ((SwipeViewFragment) getSupportFragmentManager().findFragmentByTag("swipeFrag")).isVisible()){
				MainActivity.productList = (ArrayList<HashMap<String, String>>) tempProductList.clone();

				Toast.makeText(this, "Inga resultat", Toast.LENGTH_SHORT).show();
			}
			updateViewsAfterFilter();
			//ListViewFragment.adapter.notifyDataSetChanged();
		} catch (JSONException e) {
			e.printStackTrace();
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
			Log.d("onResumeSwipe", "isOnline true");
		}else{
			Log.d("onResumeSwipe", "isOnline false");
		}

		mSensorManager.registerListener(mShakeDetector, mAccelerometer,    SensorManager.SENSOR_DELAY_UI);
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
	private void updateViewsAfterFilter(){
		if(SwipeViewFragment.NUM_PAGES == 1 && ((SwipeViewFragment) getSupportFragmentManager().findFragmentByTag("swipeFrag")).isVisible()){

			SwipeViewFragment.mPager.setSwipeable(false);
			SwipeViewFragment.pageChangeListener.onPageSelected(0);
			SwipeViewFragment.mPager.setCurrentItem(0);
			//Set Image when there is only one Beer showing
			ImageView ivBeer = (ImageView) findViewById(R.id.imageViewDemo);
			String image_url = MainActivity.productList.get(0).get("URL");
			ImageLoader imgLoader = new ImageLoader(this);
			imgLoader.DisplayImageIcon(image_url, ivBeer);			

		}
		else if (((SwipeViewFragment) getSupportFragmentManager().findFragmentByTag("swipeFrag")).isVisible()){
			SwipeViewFragment.mPager.setSwipeable(true);
			SwipeViewFragment.NUM_PAGES = MainActivity.productList.size();
			SwipeViewFragment.mPager.getAdapter().notifyDataSetChanged();
			SwipeViewFragment.pageChangeListener.onPageSelected(0);
			SwipeViewFragment.mPager.setCurrentItem(0);
		}

		if(((ListViewFragment) getSupportFragmentManager().findFragmentByTag("listFrag")).isVisible()){
			ListViewFragment.adapter.notifyDataSetChanged();
		}
	}
	private void setLayoutMargins(){
		if(((ListViewFragment) getSupportFragmentManager().findFragmentByTag("listFrag")).isVisible()){
			FrameLayout.LayoutParams relLay = (FrameLayout.LayoutParams) findViewById(R.id.root_container).getLayoutParams();
			int dpValue = 50; // margin in dips
			float d = getResources().getDisplayMetrics().density;
			int margin = (int)(dpValue * d); 
			Log.i("actionbarSize", Integer.toString(margin));
			relLay.setMargins(0, margin, 0, 0);
			findViewById(R.id.root_container).setLayoutParams(relLay);
		}
		else if(((SwipeViewFragment) getSupportFragmentManager().findFragmentByTag("swipeFrag")).isVisible()){
			FrameLayout.LayoutParams relLay = (FrameLayout.LayoutParams) findViewById(R.id.root_container).getLayoutParams();
			relLay.setMargins(0, 0, 0, 0);
			findViewById(R.id.root_container).setLayoutParams(relLay);
		}
	}
}
