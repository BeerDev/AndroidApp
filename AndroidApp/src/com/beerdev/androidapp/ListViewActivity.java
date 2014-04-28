package com.beerdev.androidapp;

import org.json.JSONException;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.beerdev.androidapp.ClearableAutoCompleteTextView.OnClearListener;

public class ListViewActivity extends ListActivity{
	private ListView lv;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		ActionBar actionBar = getActionBar(); // you can use ABS or the non-bc ActionBar
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_USE_LOGO | ActionBar.DISPLAY_SHOW_HOME
		       | ActionBar.DISPLAY_HOME_AS_UP); // what's mainly important here is DISPLAY_SHOW_CUSTOM. the rest is optional
	 
		LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// inflate the view that we created before
		View v = inflater.inflate(R.layout.actionbar_search, null);
		// the view that contains the search "magnifier" icon
		final ImageView searchIcon = (ImageView) v.findViewById(R.id.searchIcon);
		// the view that contains the new clearable autocomplete text view
		final ClearableAutoCompleteTextView searchBox =  (ClearableAutoCompleteTextView) v.findViewById(R.id.search_box);
		
		// start with the text view hidden in the action bar
		searchBox.setVisibility(View.INVISIBLE);
		searchIcon.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toggleSearch(false);
			}
		});
		
		searchBox.setOnClearListener(new OnClearListener() {
			
			@Override
			public void onClear() {
				toggleSearch(true);
			}
		});
		
		searchBox.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	 
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// handle clicks on search resaults here	
			}
			
		});
		
		actionBar.setCustomView(v);
		
		Button knappSearch = (Button) findViewById(R.id.searchStart);
		Button knappClear = (Button) findViewById(R.id.searchClear);
		 
		knappSearch.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					EditText searchFor = (EditText) findViewById(R.id.searchText);
					String searching = searchFor.getText().toString();
					Log.d("--EDITTEXT---", searching);
					Sort.Filter(searching);
					((BaseAdapter) lv.getAdapter()).notifyDataSetChanged();

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

		knappClear.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {

				Intent in = new Intent(getApplicationContext(),
						MainActivity.class);

				startActivity(in);
				//((BaseAdapter) lv.getAdapter()).notifyDataSetChanged();				
			}
		});


		lv = getListView();
		
		// Getting adapter by passing xml data ArrayList
        final LazyAdapter adapter=new LazyAdapter(ListViewActivity.this, MainActivity.productList);      
        setListAdapter(adapter);
        EditText myFilter = (EditText) findViewById(R.id.searchText);
		  myFilter.addTextChangedListener(new TextWatcher() {
		 
		  public void afterTextChanged(Editable s) {
		  }
		 
		  public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		  }
		 
		  public void onTextChanged(CharSequence s, int start, int before, int count) {
			  adapter.getFilter().filter(s.toString());
		  }
		  });
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
	}
	// this toggles between the visibility of the search icon and the search box
	// to show search icon - reset = true
	// to show search box - reset = false
	protected void toggleSearch(boolean reset) {
		ClearableAutoCompleteTextView searchBox = (ClearableAutoCompleteTextView) findViewById(R.id.search_box);
		ImageView searchIcon = (ImageView) findViewById(R.id.searchIcon);
		if (reset) {
			// hide search box and show search icon
			searchBox.setText("");
			searchBox.setVisibility(View.GONE);
			searchIcon.setVisibility(View.VISIBLE);
			// hide the keyboard
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(searchBox.getWindowToken(), 0);
		} else {
			// hide search icon and show search box
			searchIcon.setVisibility(View.GONE);
			searchBox.setVisibility(View.VISIBLE);
			searchBox.requestFocus();
			// show the keyboard
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.showSoftInput(searchBox, InputMethodManager.SHOW_IMPLICIT);
		}
		
	}
	/**
     * A method to create menu-
     * @return true - to create menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        
        return super.onCreateOptionsMenu(menu);
    }
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    		
    		case R.id.searchIcon:

    			  //final Interpolator accelerator = new AccelerateInterpolator();
    			/*ObjectAnimator anim = ObjectAnimator.ofFloat(this, "translationY", 0,android.R.attr.actionBarSize); 
    			//anim.setInterpolator(accelerator);
    			anim.setDuration(3000);
    			anim.addListener(new AnimatorListenerAdapter() {
    			      
    			      @Override
    			      public void onAnimationStart(Animator anim){
    			     	  findViewById(R.id.SearchBar).setVisibility(View.VISIBLE);
    	    			   
    			      }
    			    });
    			  anim.start();*/

		     	  findViewById(R.id.SearchBar).setVisibility(View.VISIBLE);
				((BaseAdapter) lv.getAdapter()).notifyDataSetChanged();
				break;	
    		case R.id.sortAlphab:
    			Sort.sortAlphabetic();
    			((BaseAdapter) lv.getAdapter()).notifyDataSetChanged();
    			break;
    		case R.id.sortPrice:
    			Sort.sortPrice();
    			((BaseAdapter) lv.getAdapter()).notifyDataSetChanged();
    			break;
    		case R.id.navListVy:
	    			// Starting single contact activity
					Intent intentList = new Intent(getApplicationContext(),
							ListViewActivity.class);
					//Sending BildID and productList to ListViewActivity
					intentList.putExtra("BildID", 0);
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


}