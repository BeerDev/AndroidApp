package com.beerdev.androidapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;



public class Sort extends Activity{

	//public static ArrayList<HashMap<String, String>> prodList = MainActivity.productList;
	
	public static void sortprice()
	{

		for(int i=0; i<MainActivity.productList.size();i++)
		{
			
			int pris = Integer.parseInt(MainActivity.productList.get(i).get("Utpris exkl moms"));
			int pris2 = Integer.parseInt(MainActivity.productList.get(i+1).get("Utpris exkl moms"));
			HashMap<String, String> temp = MainActivity.productList.get(i+1);
			
			if(pris < pris2)
			{
					MainActivity.productList.set(i+1, MainActivity.productList.get(i));
					MainActivity.productList.set(i, temp);
			}
		}
		return;
	}
}
