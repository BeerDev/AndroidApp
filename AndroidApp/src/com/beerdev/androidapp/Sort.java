package com.beerdev.androidapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Sort {
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
	
	public static ArrayList<HashMap<String, String>> filterProductList;
	
	public static void sortAlphabetic(){
		Collections.sort( MainActivity.productList, new Comparator<HashMap<String,String>>(){
            public int compare( HashMap<String,String> one, HashMap<String,String> two ) {
                return one.get("Artikelnamn").compareTo(two.get("Artikelnamn"));
            }
        });
	}
	public static void sortPrice(){
		Collections.sort( MainActivity.productList, new Comparator<HashMap<String,String>>(){
            public int compare( HashMap<String,String> one, HashMap<String,String> two ) {
                return Integer.parseInt(one.get("Utpris exkl moms")) - (Integer.parseInt(two.get("Utpris exkl moms")));
            }
        });
	}
	@SuppressWarnings("unused")
	public static void Filter(String word){
		
		try{
			JSONObject jsonObj = new JSONObject(MainActivity.jsonStr);
			
			// Getting JSON Array node
			JSONArray products = jsonObj.getJSONArray("Produkter");
			HashMap<String, String> filterProducts = new HashMap<String, String>();
			// looping through All products
			MainActivity.productList.clear();
			for (int i = 0; i < products.length(); i++) {
				JSONObject c = products.getJSONObject(i);
				
				if(c.getString(TAG_NAME).contains(word))
				{			
				String id = c.getString(TAG_ID);
				String name = c.getString(TAG_NAME);
				String path = c.getString(TAG_PATH);
				String pris = c.getString(TAG_PRIS);
				String info = c.getString(TAG_INFO);
				String size = c.getString(TAG_SIZE);
				String percent = c.getString(TAG_PERC);
				
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
				MainActivity.productList.add(contact);
			}
		} 
			//MainActivity.productList=filterProductList;
			}
			catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
}
