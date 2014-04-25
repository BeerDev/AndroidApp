package com.beerdev.androidapp;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewActivity extends ListActivity{
	private ListView lv;
	private View greenView;
	private int greenHeight;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		TextView tv = (TextView) findViewById(R.id.editText);
		lv = getListView();
		// Getting adapter by passing xml data ArrayList
        LazyAdapter adapter=new LazyAdapter(ListViewActivity.this, MainActivity.productList);        
        setListAdapter(adapter);
        
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
	                AnimatorSet set = new AnimatorSet();
	                set.playTogether(
	                    // animate from off-screen in to screen 
	                    ObjectAnimator.ofFloat(lv, "translationY",  0, 50f),
	                    ObjectAnimator.ofFloat(tv, "alpha", 0, 0.25f, 1)
	                    // add other animations if you wish
	                );
	                set.setDuration(3000).start();
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
			MainActivity.isOnline = true;
			Log.d("onPauseList", "isOnline true");
		}
		else{
			MainActivity.isOnline = false;
			Log.d("onPauseList", "isOnline false");
		}
	}
	@Override
	protected void onResume(){
		super.onResume();
		if(MainActivity.isOnline == NetworkStateReceiver.netCheckChange){
			Log.i("NETWORK", "is the SAME after resume in List.");
		}
		else{
			Log.i("NETWORK", "is NOT the SAME after resume in List.");
		}
	}



}
