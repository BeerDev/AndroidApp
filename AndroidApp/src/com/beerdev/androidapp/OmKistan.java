package com.beerdev.androidapp;

//testar
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * A view to show information about the developmentteam and the application
 * @author BeerDev
 *
 */
public class OmKistan extends Activity {
 
	/**
	* Information about the products
	*/
	private static ArrayList<HashMap<String, String>> productList;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.omkistan);
		 
		Intent intent = getIntent();
		productList =(ArrayList<HashMap<String,String>>) intent.getSerializableExtra("productList");
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
	    		// Starting single contact activity
				Intent intentList = new Intent(getApplicationContext(),
				ListViewActivity.class);
				//Sending BildID and productList to ListViewActivity
				intentList.putExtra("BildID", 0);
				intentList.putExtra("productList", productList);
				startActivity(intentList);
				break;
	    	case R.id.navScrollvy:
	            // Starting single contact activity
			    Intent intentSwipe = new Intent(getApplicationContext(),
			    SwipeViewActivity.class);
			    //Sending BildID and productList to SwipeViewActivity
			    intentSwipe.putExtra("BildID", 0);
			    intentSwipe.putExtra("productList", productList);
			    startActivity(intentSwipe);
			    break;
	    	case R.id.navOmOss:
			    Intent intentOmoss = new Intent(getApplicationContext(),
			    OmOss.class);
			    //Sending BildID and productList to OmOssActivity
			    intentOmoss.putExtra("BildID", 0);
			    intentOmoss.putExtra("productList", productList);
			    startActivity(intentOmoss);
			    break;
	    	case R.id.navOmKistan:
	    		Intent intentOmKistan = new Intent(getApplicationContext(),
	    				OmOss.class);
	    		//Sending BildID and productList to OmOssActivity
			    intentOmKistan.putExtra("BildID", 0);
			    intentOmKistan.putExtra("productList", productList);
			    startActivity(intentOmKistan);
			    break;
	    }
	    return true;
	}
	    //---------MENU END---------------
	    
}