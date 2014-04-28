package com.beerdev.androidapp;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * The mainactivity for the application, creates backgroundthreads for downloading JSON information and sends the infromation tho other activities.
 * @author BeerDev
 *
 */
public class MainActivity extends Activity {
	/**
	 * pDialog for showing progress dialog
	 */
	private ProgressDialog pDialog;
	
	/**
	 * URL to get products JSON
	 */
	private static String url = "http://www.beerdev.tk/sortimentA.json";
	
	/**
	 * JSON Node for finding products
	 */
	private static final String TAG_Produkter="Produkter";
	
	public static String jsonStr;
	
	/**
	 * JSON Node for finding id.
	 */
	public static final String TAG_ID = "id";
	
	/**
	 * JSON Node for finding name of article
	 */
	public static final String TAG_NAME = "Artikelnamn";
	
	/**
	 * JSON Node for finding url
	 */
	public static final String TAG_PATH = "URL";
	
	/**
	 * JSON Node for finding price
	 */
	private static final String TAG_PRIS = "Utpris exkl moms";
	
	/**
	 * JSON Node for finding description of beer
	 */
	private static final String TAG_INFO = "Info";
	
	/**
	 * JSON Node for finding size of beer
	 */
	private static final String TAG_SIZE = "Storlek";
	
	/**
	 * JSON Node for finding percent of beer
	 */
	private static final String TAG_PERC = "Alkoholhalt";
	
	private static final String TAG_KATE = "Kategori";
	
	private static final String TAG_BREW = "Bryggeri";
	
	
	/**
	 * Products JSONArray
	 */
	JSONArray products = null;
	JSONArray productsOff = null;

	/**
	 * Hashmap for the products
	 */
	public static ArrayList<HashMap<String, String>> productList;
	
		
	
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		productList = new ArrayList<HashMap<String, String>>();
		CheckingNetwork();	
		ListViewActivity.searchList=(ArrayList<HashMap<String, String>>) MainActivity.productList.clone();

	}
	
	
	
	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class GetProducts extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();			
			// Showing progress dialog
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Loading...");
			pDialog.setCancelable(false);
			pDialog.show();
			
		}

		@SuppressWarnings("unchecked")
		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
			Log.d("Response: ", "> " + jsonStr);

			if (jsonStr != null) {
				try {
					JSONObject jsonObj = new JSONObject(jsonStr);
					
					// Getting JSON Array node
					products = jsonObj.getJSONArray(TAG_Produkter);
					
					// looping through All products
					for (int i = 0; i < products.length(); i++) {
						JSONObject c = products.getJSONObject(i);
						
						String id = c.getString(TAG_ID);
						String name = c.getString(TAG_NAME);
						String path = c.getString(TAG_PATH);
						String pris = c.getString(TAG_PRIS);
						String info = c.getString(TAG_INFO);
						String size = c.getString(TAG_SIZE);
						String percent = c.getString(TAG_PERC);
						String kategori = c.getString(TAG_KATE);
						String brewery = c.getString(TAG_BREW);
						
						// tmp hashmap for single contact
						HashMap<String, String> contact = new HashMap<String, String>();

						// Adding each child node to HashMap key => value
						contact.put(TAG_ID, id);
						contact.put(TAG_NAME, name);
						contact.put(TAG_PATH, path);
						contact.put(TAG_PRIS, pris);
						contact.put(TAG_INFO, info);
						contact.put(TAG_SIZE, size);
						contact.put(TAG_PERC, percent);
						contact.put(TAG_KATE, kategori);
						contact.put(TAG_BREW, brewery);
						
						// adding contact to contact list
						productList.add(contact);

					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
			} else {
				Log.i("ServiceHandler", "Couldn't get any data from the url");
													
			}
		
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			
			Sort.sortAlphabetic();
			ListViewActivity.searchList=(ArrayList<HashMap<String, String>>) MainActivity.productList.clone();
			Intent in = new Intent(getApplicationContext(),
					SwipeViewActivity.class);
			startActivity(in);
			// Dismiss the progress dialog
				if (pDialog.isShowing())
				pDialog.dismiss();
		
		}
	}
	
	
	public boolean isOnline(){
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info != null && info.isConnectedOrConnecting()) {
		    return true;
		}
		return false;
	}
	
	public void CheckingNetwork(){
		
		if(isOnline()){
			Log.i("ONLINE", "ONLINE");		
			Toast.makeText(this, "Online mode..", Toast.LENGTH_LONG).show();

			// Calling async task to get json
			new GetProducts().execute();
			
		}
		else if(!isOnline())
		{
			Toast.makeText(this, "No internet connection..", Toast.LENGTH_LONG).show();
			offlineMode();	
			Sort.sortAlphabetic();
				Intent in = new Intent(getApplicationContext(),
						SwipeViewActivity.class);
				startActivity(in);
		}
	}

	
public void offlineMode(){
		//JSONObject obj;
		try {
			JSONObject obj = new JSONObject(loadJSONFromAsset());
		
	    productsOff = obj.getJSONArray("Produkter");
	   // ArrayList<HashMap<String, String>> productList= new ArrayList<HashMap<String, String>>();
	
	    
	    for (int i = 0; i < productsOff.length(); i++) 
	      {
	       JSONObject Offc = productsOff.getJSONObject(i);
	        String id = Offc.getString(TAG_ID);
			String name = Offc.getString(TAG_NAME);
			String path = Offc.getString(TAG_PATH);
			String pris = Offc.getString(TAG_PRIS);
			String info = Offc.getString(TAG_INFO);
			String size = Offc.getString(TAG_SIZE);
			String percent = Offc.getString(TAG_PERC);
			String kategori = Offc.getString(TAG_KATE);
	
	      //Add your values in your `ArrayList` as below:
			HashMap<String, String> hashlist = new HashMap<String,String>();
			
			hashlist.put(TAG_ID, id);
			hashlist.put(TAG_NAME, name);
			hashlist.put(TAG_PATH, path);
			hashlist.put(TAG_PRIS, pris);
			hashlist.put(TAG_INFO, info);
			hashlist.put(TAG_SIZE, size);
			hashlist.put(TAG_PERC, percent);
			hashlist.put(TAG_KATE, kategori);
	
	     productList.add(hashlist);	     
	      }
		} catch (JSONException e) {
			e.printStackTrace();
		}
}
	
	public String loadJSONFromAsset() 
	{
	    String json = null;
	    	    try {

	        InputStream is = getResources().getAssets().open("JsonAndroidOffline.json");

	        int size = is.available();
 
	        byte[] buffer = new byte[size];

	        is.read(buffer);

	        is.close();

	        json = new String(buffer, "UTF-8");
	        return json;

	    } catch (IOException ex) {
	        ex.printStackTrace();
	        return null;
	    }
	    
	}
	
	//-------Menu---------
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
