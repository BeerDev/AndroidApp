package com.beerdev.androidapp;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SearchView;
import android.widget.SearchView.OnCloseListener;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class FragmentManagerActivity extends SlidingFragmentActivity implements OnQueryTextListener, OnClickListener, OnCloseListener {
	public static SlidingMenu sm;
	private boolean mToggleChecked = false;
	private SearchView searchView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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

				ToggleButton tb = (ToggleButton) findViewById(R.id.choose_search);
				tb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

				        if(isChecked)
				        {
				            Log.d("alarmCheck","ALARM SET TO TRUE");
				            mToggleChecked = true;
				        }
				        else
				        {
				            Log.d("alarmCheck","ALARM SET TO FALSE");
				            mToggleChecked = false;
				        }
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
				//setSlidingActionBarEnabled(false);
				getActionBar().setDisplayHomeAsUpEnabled(false);
				getActionBar().setDisplayShowHomeEnabled(false);
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.menu_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);
        searchView.setOnSearchClickListener(this);
        searchView.setOnCloseListener(this);
        //searchView.setIconified(true);
        return super.onCreateOptionsMenu(menu);        
    }
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	    	switch (item.getItemId()) {
	    		case R.id.menu_navigation:
	    			toggle();
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
			InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
		    if(!imm.isActive()){
				imm.toggleSoftInput(0, InputMethodManager.SHOW_IMPLICIT);
		    }
				try {
					ArrayList<HashMap<String,String>> tempProductList = (ArrayList<HashMap<String, String>>) MainActivity.productList.clone();
					String cat;
					if(mToggleChecked){
						cat = "Kategori";
					}
					else{
						cat = "Artikelnamn";
					}
					
					Sort.Filter(newText, cat);
					Log.i("INFO", Integer.toString(MainActivity.productList.size()));
					if(MainActivity.productList.size()==0){
						MainActivity.productList = (ArrayList<HashMap<String, String>>) tempProductList.clone();

						Toast.makeText(this, "Inga resultat", Toast.LENGTH_SHORT).show();
					}
					if(MainActivity.productList.size() == 1){
						SwipeViewFragment.mPager.setSwipeable(false);
						SwipeViewFragment.pageChangeListener.onPageSelected(0);
				        SwipeViewFragment.mPager.setCurrentItem(0);
					}
					else{
						SwipeViewFragment.mPager.setSwipeable(true);
						SwipeViewFragment.mPager.getAdapter().notifyDataSetChanged();
						SwipeViewFragment.pageChangeListener.onPageSelected(0);
				        SwipeViewFragment.mPager.setCurrentItem(0);
				    }
			        if(((ListViewFragment) getSupportFragmentManager().findFragmentByTag("listFrag")).isVisible()){
			        	ListViewFragment.adapter.notifyDataSetChanged();
			        }
			        	//ListViewFragment.adapter.notifyDataSetChanged();
				} catch (JSONException e) {
					e.printStackTrace();
				}

			return false;
		}
		@Override
		public void onClick(View v) {
			findViewById(R.id.search_container).setVisibility(View.VISIBLE);
		}
		@Override
		public boolean onClose() {
			findViewById(R.id.search_container).setVisibility(View.INVISIBLE);
			InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
		    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
			return false;
		}
}
