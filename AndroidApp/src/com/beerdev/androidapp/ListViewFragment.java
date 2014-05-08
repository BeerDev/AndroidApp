package com.beerdev.androidapp;

import java.util.zip.Inflater;

import com.beerdev.androidapp.R.integer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
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
			 FragmentManagerActivity.setLayoutMargins(getActivity().findViewById(R.id.root_view), getActivity());
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
		 //Hide inputmethodmanager
		 InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
		if(imm.isActive()){
			imm.hideSoftInputFromWindow(getActivity().findViewById(R.id.root_view).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	 }
}