package com.beerdev.androidapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A view to show information about the developmentteam and the application
 * @author BeerDev
 *
 */
public class AboutReceiptActivity extends Activity {
	public static FSmallReceiptAdapter ReceiptAdapter;
	private HashMap<String, String> artikel;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.receipt_finnal);
	
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 Date date = new Date();
		 
		 TextView totPris = (TextView) this.findViewById(R.id.receiptTotPrisKistan);
		 TextView datum = (TextView) this.findViewById(R.id.receiptDatumKistan);
		 
		  datum.setText(dateFormat.format(date));
		  totPris.setText(Integer.toString(MainActivity.shoppingSum)+" kr ");
	
		  
		  //Lägger till Artiklar i ListView
		 // ReceiptAdapter = new FSmallReceiptAdapter(this, names,price);
		  ArrayList<String> names = new ArrayList<String>();
		  ArrayList<String> price = new ArrayList<String>();
		  
		  for(int i=0; i<MainActivity.completeShoppingList.size();i++){
			 artikel = MainActivity.completeShoppingList.get(i);
			 names.add(artikel.get(MainActivity.TAG_NAME)+ "           " + artikel.get(MainActivity.TAG_PRICE)+" kr" );
			 price.add(artikel.get(MainActivity.TAG_PRICE)+" kr");
		  }
		  
		  ReceiptAdapter = new FSmallReceiptAdapter(this, names,price);

		  
		  //Fix adapter for Artikelnamn och Pris
		  ArrayAdapter<String> adapterName = new ArrayAdapter<String>(this,
		            android.R.layout.simple_list_item_1, android.R.id.text1, names);
		  ArrayAdapter<String> adapterPrice = new ArrayAdapter<String>(this,
		            android.R.layout.simple_list_item_1, android.R.id.text1, price);
		  //Define what listViews
		  ListView listViewName = (ListView) this.findViewById(R.id.lvReceiptListViewName);
		  ListView listViewPrice = (ListView) this.findViewById(R.id.lvReceiptListViewPrice);
		  //Put adapter in the ListView
		  listViewName.setAdapter(adapterName);
		  listViewPrice.setAdapter(adapterPrice);
		
	}
	
/*		public void onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 ViewGroup kistanView = (ViewGroup) inflater.inflate(R.layout.receipt_finnal, container, false);
		 //Remove items from menubar
	//	 FragmentManagerActivity.menu.findItem(R.id.menu_filter).setVisible(false);
	//	 FragmentManagerActivity.menu.findItem(R.id.menu_search).setVisible(false);
	//	 FragmentManagerActivity.menu.findItem(R.id.menu_close_search).setVisible(false);
		 
		 //Remove searchcontainer
		 //getActivity().findViewById(R.id.search_container).setVisibility(View.INVISIBLE);
		 
		 //get Date
	 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 Date date = new Date();
		 
		 TextView totPris = (TextView) kistanView.findViewById(R.id.receiptTotPrisKistan);
		 TextView datum = (TextView) kistanView.findViewById(R.id.receiptDatumKistan);
		 
		  datum.setText(dateFormat.format(date));
		  totPris.setText(Integer.toString(MainActivity.shoppingSum)+" kr ");
		
		  
		  List<String> countryList = new ArrayList<String>();
		  countryList.add("Aruba");
		  countryList.add("Anguilla");
		  countryList.add("Netherlands Antilles");
		  countryList.add("Antigua and Barbuda"); 
		
		  
		  
		  ReceiptAdapter = new FSmallReceiptAdapter(this, MainActivity.completeShoppingList); 
		  String[] values = new String[] { "Elemento 1", 
                  "Elemento 2",
                  "Elemento 3",
                  "Elemento 4", 
                  "Elemento 5", 
                  "Elemento 6", 
                  "Elemento 7", 
                  "Elemento 8" 
                 };
		  ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		            android.R.layout.simple_list_item_1, android.R.id.text1, countryList);;
		  ListView listView = (ListView) this.findViewById(R.id.lvReceiptListView);
		//  listView.setAdapter(ReceiptAdapter);
		
		  
			// HashMap<String, String> item = MainActivity.completeShoppingList.get(i);
			 
	}
	*/
}