package com.beerdev.androidapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import org.json.JSONException;

import android.util.Log;

public class Sort {	
	public static ArrayList<HashMap<String, String>> filterProductList;
	
	public static void sortAlphabetic(){
		Collections.sort( MainActivity.productList, new Comparator<HashMap<String,String>>(){
            public int compare( HashMap<String,String> one, HashMap<String,String> two ) {
                return one.get(MainActivity.TAG_NAME).compareTo(two.get(MainActivity.TAG_NAME));
            }
        });
	}
	public static void sortPrice(){
		Collections.sort( MainActivity.productList, new Comparator<HashMap<String,String>>(){
            public int compare( HashMap<String,String> one, HashMap<String,String> two ) {
                return Integer.parseInt(one.get(MainActivity.TAG_PRICE)) - (Integer.parseInt(two.get(MainActivity.TAG_PRICE)));
            }
        });
	}
	
	public static void Filter(String word, String tag) throws JSONException{
		MainActivity.productList.clear();
		// Getting JSON Array node
		//filterProductList=new ArrayList<HashMap<String, String>>();
		// looping through All products
		if(tag=="Artikelnamn"){
			for (int i = 0; i < MainActivity.completeProductList.size();i++) 
			{
				String c = MainActivity.completeProductList.get(i).get(tag);
	
				if(c.toLowerCase().contains(word.toLowerCase()))
				{			
					//ListViewActivity.searchList.remove(i);
	
					String id = MainActivity.completeProductList.get(i).get(MainActivity.TAG_ID);
					String name = MainActivity.completeProductList.get(i).get(MainActivity.TAG_NAME);
					String path = MainActivity.completeProductList.get(i).get(MainActivity.TAG_PATH);
					String pris = MainActivity.completeProductList.get(i).get(MainActivity.TAG_PRICE);
					String info = MainActivity.completeProductList.get(i).get(MainActivity.TAG_INFO);
					String size = MainActivity.completeProductList.get(i).get(MainActivity.TAG_SIZE);
					String percent = MainActivity.completeProductList.get(i).get(MainActivity.TAG_PERC);
					String type = MainActivity.completeProductList.get(i).get(MainActivity.TAG_TYPE);
					String brew = MainActivity.completeProductList.get(i).get(MainActivity.TAG_BREW);
					
					HashMap<String, String> product = new HashMap<String, String>();
	
					// Adding each child node to HashMap key => value
					product.put(MainActivity.TAG_ID, id);
					product.put(MainActivity.TAG_NAME, name);
					product.put(MainActivity.TAG_PATH, path);
					product.put(MainActivity.TAG_PRICE, pris);
					product.put(MainActivity.TAG_INFO, info);
					product.put(MainActivity.TAG_SIZE, size);
					product.put(MainActivity.TAG_PERC, percent);
					product.put(MainActivity.TAG_TYPE, type);
					product.put(MainActivity.TAG_BREW, brew);
					// adding contact to contact list
					MainActivity.productList.add(product);
				}
			} 
		}
		
		else if(tag=="Kategori"){
			for (int i = 0; i < MainActivity.completeProductList.size();i++) 
			{
				String c = MainActivity.completeProductList.get(i).get(tag);
	
				if(c.toLowerCase().startsWith(word.toLowerCase()))
				{			
					String id = MainActivity.completeProductList.get(i).get(MainActivity.TAG_ID);
					String name = MainActivity.completeProductList.get(i).get(MainActivity.TAG_NAME);
					String path = MainActivity.completeProductList.get(i).get(MainActivity.TAG_PATH);
					String pris = MainActivity.completeProductList.get(i).get(MainActivity.TAG_PRICE);
					String info = MainActivity.completeProductList.get(i).get(MainActivity.TAG_INFO);
					String size = MainActivity.completeProductList.get(i).get(MainActivity.TAG_SIZE);
					String percent = MainActivity.completeProductList.get(i).get(MainActivity.TAG_PERC);
					String type = MainActivity.completeProductList.get(i).get(MainActivity.TAG_TYPE);
					String brew = MainActivity.completeProductList.get(i).get(MainActivity.TAG_BREW);
					
					HashMap<String, String> product = new HashMap<String, String>();
	
					// Adding each child node to HashMap key => value
					product.put(MainActivity.TAG_ID, id);
					product.put(MainActivity.TAG_NAME, name);
					product.put(MainActivity.TAG_PATH, path);
					product.put(MainActivity.TAG_PRICE, pris);
					product.put(MainActivity.TAG_INFO, info);
					product.put(MainActivity.TAG_SIZE, size);
					product.put(MainActivity.TAG_PERC, percent);
					product.put(MainActivity.TAG_TYPE, type);
					product.put(MainActivity.TAG_BREW, brew);
					// adding contact to contact list
					MainActivity.productList.add(product);
				}
			} 
		}
		
		return;
	}
}
