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
	 * Show if no internet connection
	 */
	private ProgressDialog netDialog;

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
	public static final String TAG_PRICE = "Utpris exkl moms";

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
	 * JSON Node for finding percent of beer
	 */
	public static final String TAG_CAT = "Kategori";
	
	public static final String TAG_BREW = "Bryggeri";

	/**
	 * Products JSONArray
	 */
	JSONArray products = null;
	JSONArray productsOff = null;
	

	public static boolean wasOnline;
	public boolean downloadFinished;
	/**
	 * Hashmap for the products
	 */
	public static ArrayList<HashMap<String, String>> productList;
	
	public static ArrayList<HashMap<String, String>> completeProductList;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		productList = new ArrayList<HashMap<String, String>>();
		//getproductList = new ArrayList<HashMap<String, String>>();
		
		CheckingNetwork();	
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

		}

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
						String pris = c.getString(TAG_PRICE);
						String info = c.getString(TAG_INFO);
						String size = c.getString(TAG_SIZE);
						String percent = c.getString(TAG_PERC);
						String cat = c.getString(TAG_CAT);
						String brew = c.getString(TAG_BREW);
						
						// tmp hashmap for single contact
						HashMap<String, String> product = new HashMap<String, String>();

						// Adding each child node to HashMap key => value
						product.put(TAG_ID, id);
						product.put(TAG_NAME, name);
						product.put(TAG_PATH, path);
						product.put(TAG_PRICE, pris);
						product.put(TAG_INFO, info);
						product.put(TAG_SIZE, size);
						product.put(TAG_PERC, percent);
						product.put(TAG_CAT, cat);
						product.put(TAG_BREW, brew);

						// adding contact to contact list
						productList.add(product);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				downloadFinished = true;
			} else {
				Log.i("ServiceHandler", "Couldn't get any data from the url");

			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if(downloadFinished == true){
				Sort.sortAlphabetic();

				completeProductList = (ArrayList<HashMap<String,String>>) productList.clone();
				Intent in = new Intent(getApplicationContext(),
						FragmentManagerActivity.class);
				in.putExtra("position", 0);
				in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
				in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(in);
				finish();
			}
			if (pDialog.isShowing())
				pDialog.dismiss();
		}
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
				Sort.sortAlphabetic();
			}
			Log.d("onResumeMain", "isOnline true");
		}else{
			Log.d("onResumeMain", "isOnline false");
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
			// Calling async task to get json
			wasOnline = true;
			downloadFinished = false;
			new GetProducts().execute();

		}
		else if(!isOnline())
		{
			Toast.makeText(this, "No internet connection..", Toast.LENGTH_LONG).show();
			offlineMode();	
			wasOnline = false;		
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
			String pris = Offc.getString(TAG_PRICE);
			String info = Offc.getString(TAG_INFO);
			String size = Offc.getString(TAG_SIZE);
			String percent = Offc.getString(TAG_PERC);
			String cat = Offc.getString(TAG_CAT);
			String brew = Offc.getString(TAG_BREW);

	      //Add your values in your `ArrayList` as below:
			HashMap<String, String> hashlist = new HashMap<String,String>();

			hashlist.put(TAG_ID, id);
			hashlist.put(TAG_NAME, name);
			hashlist.put(TAG_PATH, path);
			hashlist.put(TAG_PRICE, pris);
			hashlist.put(TAG_INFO, info);
			hashlist.put(TAG_SIZE, size);
			hashlist.put(TAG_PERC, percent);
			hashlist.put(TAG_CAT, cat);
			hashlist.put(TAG_BREW, brew);

	     productList.add(hashlist);
	     //getproductList.add(hashlist);
	      }
		} catch (JSONException e) {
			e.printStackTrace();
		}
		completeProductList = (ArrayList<HashMap<String,String>>) productList.clone();
		Intent in = new Intent(getApplicationContext(),
				FragmentManagerActivity.class);
		in.putExtra("position", 0);
		in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
		in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(in);
		finish();
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
}
