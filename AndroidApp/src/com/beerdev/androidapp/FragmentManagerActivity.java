package com.beerdev.androidapp;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class FragmentManagerActivity extends SlidingFragmentActivity {
	public static SlidingMenu sm;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// set the Above View
		// set the Behind View
			setContentView(R.layout.fragmentactivity_root);
			
			FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
			SwipeViewFragment mFrag = new SwipeViewFragment();
			t.replace(R.id.root_container, mFrag);
			t.commit();
			
				setBehindContentView(R.layout.menu_frame);
				FragmentTransaction t2 = this.getSupportFragmentManager().beginTransaction();
				MenuFragment mFrag2 = new MenuFragment();
				t2.replace(R.id.menu_frame, mFrag2);
				t2.commit();

				// customize the SlidingMenu
				sm = getSlidingMenu();
				sm.setShadowWidthRes(R.dimen.shadow_width);
				sm.setShadowDrawable(R.drawable.shadow);
				sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
				sm.setFadeDegree(0.35f);
				sm.showSecondaryMenu();
				sm.setMode(SlidingMenu.RIGHT);
				sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
				getActionBar().setDisplayHomeAsUpEnabled(false);
				getActionBar().setDisplayShowHomeEnabled(false);
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        
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
}
