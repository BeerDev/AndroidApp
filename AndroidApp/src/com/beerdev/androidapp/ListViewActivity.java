package com.beerdev.androidapp;

import org.json.JSONException;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActionBar;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

public class ListViewActivity extends ListActivity implements OnQueryTextListener{
	private ListView lv;
	public static LazyAdapter adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		// Getting adapter by passing xml data ArrayList
        adapter=new LazyAdapter(ListViewActivity.this, MainActivity.productList);      
        setListAdapter(adapter);

		lv = getListView();
		// Listview on item click listener
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem			
				String ID = ((TextView) view.findViewById(R.id.listID)).getText().toString();

				// Starting single contact activity
				Intent in = new Intent(getApplicationContext(),
						SwipeViewActivity.class);

				//Sending BildID and ContactList to SwipeViewActivity
				in.putExtra("BildID", position);
				startActivity(in);

			}
		});
		
		
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
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView  searchView = (SearchView) menu.findItem(R.id.search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);;
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

    @Override
    protected void onPause(){
    	super.onPause();
    	ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info != null && info.isConnectedOrConnecting()) {
			MainActivity.wasOnline = true;
			Log.d("onPauseList", "wasOnline true");
		}else{
			MainActivity.wasOnline = false;
			Log.d("onPauseList", "wasOnline false");
		}
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
			Log.d("onResumeList", "isOnline true");
		}else{
			Log.d("onResumeMain", "isOnline false");
		}
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		adapter.getFilter().filter(newText);
		return false;
	}


}