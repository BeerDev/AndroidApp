package com.beerdev.androidapp;

//testar
import java.util.ArrayList;
import java.util.HashMap;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.SearchView.OnCloseListener;
import android.widget.SearchView.OnQueryTextListener;

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
		 //*******Functions For MENU*********
	       
		ImageView imgGalleri = (ImageView) findViewById(R.id.imageGalleri);
		imgGalleri.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Starting single contact activity
				Intent intentSwipe = new Intent(getApplicationContext(),
						SwipeViewActivity.class);
				//Sending BildID and productList to SwipeViewActivity
				intentSwipe.putExtra("BildID", 0);
				startActivity(intentSwipe);
			}
		});
	ImageView imgLista = (ImageView) findViewById(R.id.imageLista);
	imgLista.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// Starting single contact activity
			Intent intentList = new Intent(getApplicationContext(),
					ListViewActivity.class);
			//Sending BildID and productList to SwipeViewActivity
			intentList.putExtra("BildID", 0);
			startActivity(intentList);
		}
	});
	
	ImageView imgOltyper = (ImageView) findViewById(R.id.imageOltyper);
	imgOltyper.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// Starting single contact activity
			Intent intentList = new Intent(getApplicationContext(),
					Kategori.class);
			//Sending BildID and productList to SwipeViewActivity
			intentList.putExtra("BildID", 0);
			startActivity(intentList);
		}
	});
	
	ImageView imgKistan= (ImageView) findViewById(R.id.imageKistan);
	imgKistan.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// Starting single contact activity
			Intent intentKistan = new Intent(getApplicationContext(),
					OmKistan.class);
			//Sending BildID and productList to SwipeViewActivity
			intentKistan.putExtra("BildID", 0);
			startActivity(intentKistan);
		}
	});
	ImageView imgUtvecklare = (ImageView) findViewById(R.id.imageUtvecklare);
	imgUtvecklare.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// Starting single contact activity
			Intent intentUtvecklare = new Intent(getApplicationContext(),
					OmOss.class);
			//Sending BildID and productList to SwipeViewActivity
			intentUtvecklare.putExtra("BildID", 0);
			startActivity(intentUtvecklare);
		}
	});
        	
        	
        	
        //*******END OF MENU*********

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
    
    /* (non-Javadoc)
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    		case R.id.menu2:
    			View v=findViewById(R.id.main_layout);
    			final View MenuLayout= findViewById(R.id.menu_layout);
				final View MainLayout = findViewById(R.id.main_layout);
				final int menuWidth = MenuLayout.getWidth();
    			if(MenuLayout.getAlpha()<1){
    				AnimatorSet set = new AnimatorSet();
    				set.playTogether(
    						ObjectAnimator.ofFloat(MainLayout, "translationX",  0, -menuWidth),
    				        ObjectAnimator.ofFloat(MenuLayout, "translationX",  menuWidth, 0),
    				        ObjectAnimator.ofFloat(MenuLayout, "alpha", 0, 0.25f, 1)
    				        // add other animations if you wish
    				    );
    				    //set.setStartDelay(500);
    				    set.setDuration(200).start();
    				
    				
    			}
    			else{    				
    				AnimatorSet set = new AnimatorSet();
    				set.playTogether(
    						ObjectAnimator.ofFloat(MainLayout, "translationX",  -menuWidth, 0),
    				        ObjectAnimator.ofFloat(MenuLayout, "translationX",  0, menuWidth),
    				        ObjectAnimator.ofFloat(MenuLayout, "alpha", 1, 0.25f, 0)
    				        // add other animations if you wish
    				    );
    				    //set.setStartDelay(500);
    				    set.setDuration(200).start();
    			}
    			
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
	    
}