package com.beerdev.androidapp;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

public class ListViewFragment extends ListFragment{
	private ListView lv;
	public static LazyAdapter adapter;
	
	@Override 
	public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(!(FragmentManagerActivity.menu == null)){
			 if(!(FragmentManagerActivity.menu.findItem(R.id.menu_filter).isVisible())){
				 if(FragmentManagerActivity.searchView.isIconified()){
					 FragmentManagerActivity.menu.findItem(R.id.menu_filter).setVisible(true); 
				 }
				 FragmentManagerActivity.menu.findItem(R.id.menu_search).setVisible(true);
			 }
			 getActivity().findViewById(R.id.search_container).setVisibility(View.INVISIBLE); 
		 }
        // Initially there is no data 
        setEmptyText("No Data Here");
		// Getting adapter by passing xml data ArrayList
        adapter=new LazyAdapter(getActivity(), MainActivity.productList);      
        setListAdapter(adapter);
	}
	 @Override
     public void onListItemClick(ListView l, View v, int position, long id) {
		// Insert desired behavior here.
		 getActivity()
		 	.getIntent()
		 	.putExtra("position", position);
		 
		 getActivity()
			.getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.root_container, new SwipeViewFragment(), "swipeFrag")
			.addToBackStack("swipeFrag")
			.commit();
	 }
}