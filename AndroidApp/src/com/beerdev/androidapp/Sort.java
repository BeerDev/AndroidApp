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
	
	public static void Filter(String word) throws JSONException{
		
		// Getting JSON Array node
		filterProductList=new ArrayList<HashMap<String, String>>();
		// looping through All products
		for (int i = 0; i < MainActivity.productList.size();) {
			String c = MainActivity.productList.get(i).get(TAG_NAME);
			
			if(!c.toLowerCase().contains(word.toLowerCase()))
			{			
				MainActivity.productList.remove(i);			
			}
			else{
				i++;
			}
		} 
		return;
	}
	
}
