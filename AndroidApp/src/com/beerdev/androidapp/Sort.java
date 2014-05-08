package com.beerdev.androidapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

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
	
	private static final String TAG_CAT = "Kategori";
	
	private static final String TAG_BREW = "Bryggeri";
	
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
	
	public static void Filter(String word, String tag) throws JSONException{
		MainActivity.productList.clear();
		// Getting JSON Array node
		//filterProductList=new ArrayList<HashMap<String, String>>();
		// looping through All products
		for (int i = 0; i < MainActivity.completeProductList.size();i++) 
		{
			String c = MainActivity.completeProductList.get(i).get(tag);

			if(c.toLowerCase().contains(word.toLowerCase()))
			{			
				//ListViewActivity.searchList.remove(i);

				String id = MainActivity.completeProductList.get(i).get(TAG_ID);
				String name = MainActivity.completeProductList.get(i).get(TAG_NAME);
				String path = MainActivity.completeProductList.get(i).get(TAG_PATH);
				String pris = MainActivity.completeProductList.get(i).get(TAG_PRIS);
				String info = MainActivity.completeProductList.get(i).get(TAG_INFO);
				String size = MainActivity.completeProductList.get(i).get(TAG_SIZE);
				String percent = MainActivity.completeProductList.get(i).get(TAG_PERC);
				String cat = MainActivity.completeProductList.get(i).get(TAG_CAT);
				String brew = MainActivity.completeProductList.get(i).get(TAG_BREW);

				HashMap<String, String> product = new HashMap<String, String>();

				// Adding each child node to HashMap key => value
				product.put(TAG_ID, id);
				product.put(TAG_NAME, name);
				product.put(TAG_PATH, path);
				product.put(TAG_PRIS, pris);
				product.put(TAG_INFO, info);
				product.put(TAG_SIZE, size);
				product.put(TAG_PERC, percent);
				product.put(TAG_CAT, cat);
				product.put(TAG_BREW, brew);
				
				// adding contact to contact list
				MainActivity.productList.add(product);
			}
			
		} 
		return;
	}
}
