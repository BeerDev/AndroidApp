package com.beerdev.androidapp;

import org.json.JSONException;

import android.app.ActionBar;
import android.app.ListActivity;
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
import android.widget.TextView;

public class ListViewActivity extends ListActivity{
	private ListView lv;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		// Getting adapter by passing xml data ArrayList
        final LazyAdapter adapter=new LazyAdapter(ListViewActivity.this, MainActivity.completeProductList);      
        final LazyAdapter searchAdapter=new LazyAdapter(ListViewActivity.this, MainActivity.completeProductList);      
        setListAdapter(adapter);
        	
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
		ActionBar actionBar = getActionBar(); // you can use ABS or the non-bc ActionBar
	    actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_USE_LOGO | ActionBar.DISPLAY_SHOW_HOME
	            | ActionBar.DISPLAY_HOME_AS_UP); // what's mainly important here is DISPLAY_SHOW_CUSTOM. the rest is optional
	    
	    LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    // inflate the view that we created before
	    View v = inflater.inflate(R.layout.actionbar_search, null);
	    AutoCompleteTextView textView =  (AutoCompleteTextView) v.findViewById(R.id.search_box);
	 
	    textView.setAdapter(searchAdapter);
	 
	    textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	 
	        @Override
	    	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	            // do something when the user clicks
	        }
	    });
	    actionBar.setCustomView(v);
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