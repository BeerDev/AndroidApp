package com.beerdev.androidapp;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class ListViewFragment extends ListFragment{
	private ListView lv;
	public static LazyAdapter adapter;
	
	@Override 
	public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        // Initially there is no data 
        setEmptyText("No Data Here");
		// Getting adapter by passing xml data ArrayList
        adapter=new LazyAdapter(getActivity(), MainActivity.productList);      
        setListAdapter(adapter);
	}
	 @Override
     public void onListItemClick(ListView l, View v, int position, long id) {
		// Insert desired behavior here.
         Log.i("DataListFragment", "Item clicked: " + id);
	 }
}