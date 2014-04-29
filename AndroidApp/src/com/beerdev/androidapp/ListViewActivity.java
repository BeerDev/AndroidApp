package com.beerdev.androidapp;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewActivity extends ListActivity{
	
	public static ArrayList<HashMap<String, String>> searchList;
	private ListView lv;
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		searchList=(ArrayList<HashMap<String, String>>) MainActivity.productList.clone();
		
		
///-------------------------------------------------------------------
		
		final EditText edt = (EditText) findViewById(R.id.searchText);
		edt.addTextChangedListener(new TextWatcher()
		{  
		@Override 
		public void afterTextChanged(Editable s) {
		    // TODO Auto-generated method stub
		   // CharSequence cs=convert(edt.getText().toString());
		   //         edt.setText(cs);
		   try {         
			 EditText searchFor = (EditText) findViewById(R.id.searchText);
			 String searching = searchFor.getText().toString();
			 Log.d("--EDITTEXT---", searching);
			 searchList.clear();
			 
			 Sort.Filter(searching);
			 ((BaseAdapter) lv.getAdapter()).notifyDataSetChanged();
		 } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
		        int after) {
		    // TODO Auto-generated method stub


		}
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		    // TODO Auto-generated method stub

		}
		});
//-----------------------------------------------------
		
		
		
		
		
		Button knappSearch = (Button) findViewById(R.id.searchStart);
		Button knappClear = (Button) findViewById(R.id.searchClear);
		
		knappSearch.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try 
				{
					EditText searchFor = (EditText) findViewById(R.id.searchText);
					String searching = searchFor.getText().toString();
					Log.d("--EDITTEXT---", searching);
					searchList.clear();
					Sort.Filter(searching);
					((BaseAdapter) lv.getAdapter()).notifyDataSetChanged();
					
				} 
				catch (JSONException e) 
				{
							// TODO Auto-generated catch block
							e.printStackTrace();
						
				}
			
			}
			
		});
		
		knappClear.setOnClickListener(new OnClickListener() {			
			@SuppressWarnings("unchecked")
			@Override
			public void onClick(View arg0) {
				findViewById(R.id.SearchBar).setVisibility(View.GONE);
				((BaseAdapter) lv.getAdapter()).notifyDataSetChanged();
				searchList=(ArrayList<HashMap<String, String>>) MainActivity.productList.clone();
				((BaseAdapter) lv.getAdapter()).notifyDataSetChanged();				
			}
		});
		
		
		lv = getListView();
		// Getting adapter by passing xml data ArrayList
        LazyAdapter adapter=new LazyAdapter(ListViewActivity.this, searchList);        
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
	private CharSequence convert(String string) {
		// TODO Auto-generated method stub
		return null;
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
    		
    		case R.id.searchIcon:
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
    


}
