package com.beerdev.androidapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

public class FragmentManagerActivity extends FragmentActivity{
	public static boolean menuShowing = false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragmentactivity_root);
		FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();

        // Add the first Fragment to the container.
        ft.replace(R.id.root_container, new SwipeViewFragment(), "swipeFrag");
        ft.addToBackStack("swipeFrag");
        ft.commit();
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
	    			FragmentManager fm = getSupportFragmentManager();
	    			FragmentTransaction ft1 = fm.beginTransaction();
	    			
	    			if(!menuShowing){
	    				// Show the second Fragment.

	    		        ft1.replace(R.id.root_container, new MenuFragment());
		    			ft1.addToBackStack(null);
		    			menuShowing = true;
	    			}
	    			else{
	    				// Show the first Fragment by popping the back stack!
	    		        fm.popBackStack();
	    				menuShowing = false;
	    			}

		    		ft1.commit();
	    			break;
	    			
	    		}
	    		return true;
	    	}
}
