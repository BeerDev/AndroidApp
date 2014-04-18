package com.beerdev.androidapp;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * A view to show information about the developmentteam and the application
 * @author BeerDev
 *
 */
public class OmOss extends Activity {
	
	/**
	 * Information about the products
	 */
	private static ArrayList<HashMap<String, String>> productList;


	@SuppressWarnings("unchecked")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.omss);	
		
		Intent intent = getIntent();
        productList =(ArrayList<HashMap<String,String>>) intent.getSerializableExtra("productList");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.navigation_menu, menu);
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
				
				//Sending BildID and productList to SwipeViewActivity
				in.putExtra("BildID", 0);
				in.putExtra("productList", productList);
				startActivity(in);
	            break;
	        case R.id.navOmOss:
				Intent omoss = new Intent(getApplicationContext(),
						OmOss.class);
				
				//Sending BildID and ContactList to OmOssActivity
				omoss.putExtra("BildID", 0);
				omoss.putExtra("productList", productList);
	        	startActivity(omoss);
	            break;
	        }
	        return true;
	    }
}