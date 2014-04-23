package com.beerdev.androidapp;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Sort {
	
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
}
