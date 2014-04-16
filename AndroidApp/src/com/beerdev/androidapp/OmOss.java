package com.beerdev.androidapp;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class OmOss extends Activity {
	
	private static ArrayList<HashMap<String, String>> contactList;


	@SuppressWarnings("unchecked")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.omss);	
		
		Intent intent = getIntent();
        contactList =(ArrayList<HashMap<String,String>>) intent.getSerializableExtra("contactList");
        
		
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
						ScreenSlidePagerActivity.class);
				
				//Sending BildID and ContactList to ScreenSlidePagerActivity
				in.putExtra("BildID", 0);
				in.putExtra("contactList", contactList);
				startActivity(in);
	            break;
	        case R.id.navOmOss:
				Intent omoss = new Intent(getApplicationContext(),
						OmOss.class);
				
				//Sending BildID and ContactList to ScreenSlidePagerActivity
				omoss.putExtra("BildID", 0);
				omoss.putExtra("contactList", contactList);
	        	startActivity(omoss);
	            break;
	        }
	        return true;
	    }
	
	
	/**
	 * Async task class to get json by making HTTP call
	 * */
}