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
		  
		  //create an ArrayAdaptar from the String Array
		  ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
		    R.layout.receipt_list_elem, countryList);
		  ListView listView = (ListView) getActivity().findViewById(R.id.receiptListView);
		  // Assign adapter to ListView
		  listView.setAdapter(dataAdapter);
		  
		  
		  
			// HashMap<String, String> item = MainActivity.completeShoppingList.get(i);
			 
		
		 
		 return kistanView;
	}
}