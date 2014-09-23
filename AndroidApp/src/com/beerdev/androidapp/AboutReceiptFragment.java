package com.beerdev.androidapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
public class AboutReceiptFragment extends Fragment {
	public static FSmallReceiptAdapter ReceiptAdapter;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 ViewGroup kistanView = (ViewGroup) inflater.inflate(R.layout.receipt_finnal, container, false);
		 //Remove items from menubar
		 FragmentManagerActivity.menu.findItem(R.id.menu_filter).setVisible(false);
		 FragmentManagerActivity.menu.findItem(R.id.menu_search).setVisible(false);
		 FragmentManagerActivity.menu.findItem(R.id.menu_close_search).setVisible(false);
		 
		 //Remove searchcontainer
		 getActivity().findViewById(R.id.search_container).setVisibility(View.INVISIBLE);
		 
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
		
		  
		  
		 // ReceiptAdapter = new FSmallReceiptAdapter(getActivity(), MainActivity.completeShoppingList); 
		  String[] values = new String[] { "Elemento 1", 
                  "Elemento 2",
                  "Elemento 3",
                  "Elemento 4", 
                  "Elemento 5", 
                  "Elemento 6", 
                  "Elemento 7", 
                  "Elemento 8" 
                 };
		  ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
		            android.R.layout.simple_list_item_1, android.R.id.text1, countryList);;
		  ListView listView = (ListView) getActivity().findViewById(R.id.lvReceiptListViewName);
		//  listView.setAdapter(ReceiptAdapter);
		
		  
			// HashMap<String, String> item = MainActivity.completeShoppingList.get(i);
			 
		
		 
		 return kistanView;
	}
}