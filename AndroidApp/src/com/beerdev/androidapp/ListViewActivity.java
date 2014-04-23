package com.beerdev.androidapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewActivity extends ListActivity{
	private ListView lv;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
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
    


}
