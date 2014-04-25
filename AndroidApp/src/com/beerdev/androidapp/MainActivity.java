package com.beerdev.androidapp;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
	
	/**
	 * Products JSONArray
	 */
	JSONArray products = null;
	
	public static boolean isOnline = false;
	public static boolean wasOnline = false;
	/**
	 * Hashmap for the products
	 */
	public static ArrayList<HashMap<String, String>> productList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		productList = new ArrayList<HashMap<String, String>>();
		if(isOnline()){
			Log.i("ONLINE", "ONLINE");
			// Calling async task to get json
			new GetProducts().execute();
			
		}
		else{
			 Toast.makeText(this, "Vänligen kolla din nätverksanslutning och försök igen..", Toast.LENGTH_LONG).show();
			 Intent in = new Intent(getApplicationContext(),ListViewActivity.class);
			 startActivity(in);
		}

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
			pDialog.setMessage("Laddar...");
			pDialog.show();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
			
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
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
							
							// adding contact to contact list
							productList.add(contact);
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					Log.e("ServiceHandler", "Couldn't get any data from the url");
					
				}

				Sort.sortAlphabetic();
				
				return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing()){
				pDialog.dismiss();
			}
			Intent in = new Intent(getApplicationContext(),
					SwipeViewActivity.class);
			//in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(in);
		
		}
	}
	public boolean isOnline(){
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info != null && info.isConnectedOrConnecting()) {
			isOnline = true;
		    return true;
		}
		isOnline = false;
		return false;
	}

    @Override
    protected void onPause(){
    	super.onPause();
    	ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info != null && info.isConnectedOrConnecting()) {
			wasOnline = true;
			Log.d("onPauseMain", "wasOnline true");
		}else{
			wasOnline = false;
			Log.d("onPauseMain", "wasOnline false");
		}
    }
	@Override
	protected void onResume(){
		super.onResume();
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info != null && info.isConnectedOrConnecting()) {
			if(wasOnline == false){
				new GetProducts().execute();
			}
			Log.d("onResumeMain", "isOnline true");
		}else{
			Log.d("onResumeMain", "isOnline false");
		}
	}
}
