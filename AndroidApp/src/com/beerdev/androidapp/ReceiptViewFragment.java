package com.beerdev.androidapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ReceiptViewFragment extends ListFragment{
	public static FSmallArrayAdapter fastScrollAdapter;
	public static ListView lv;
	
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
        lv = getListView();
        // Initially there is no data 
        setEmptyText("No Data Here");
        
		LinearLayout pay =(LinearLayout) getActivity().findViewById(R.id.llReceiptLastPay);
		pay.setVisibility(View.VISIBLE);
		
		
		// Getting adapter by passing xml data ArrayList
        fastScrollAdapter=new FSmallArrayAdapter(getActivity(), MainActivity.completeShoppingList); 
        setListAdapter(fastScrollAdapter);        
        
        TextView totSumText= (TextView) getActivity().findViewById(R.id.tvReceiptLastPayText);
        totSumText.setText("Totala Summan: "+ Integer.toString(MainActivity.shoppingSum)+" kr");
        
        if(FragmentManagerActivity.fastScrollEnabled){
            getListView().setFastScrollEnabled(true);
            getListView().setFastScrollAlwaysVisible(true);
        }
        else{
            getListView().setFastScrollEnabled(false);	
            getListView().setFastScrollAlwaysVisible(false);
        }
       
        
        //----

        
        //----
        
    }
	 @SuppressLint("NewApi")
	@Override
     public void onListItemClick(ListView l, View v, int position, long id) {
		// Insert desired behavior here.
	//	 if(clicked == 1){
	//		 clicked = 1;
		 
		 getActivity()
		 	.getIntent()
		 	.putExtra("position", position);
	
		 MainActivity.shoppingSum = MainActivity.shoppingSum - Integer.parseInt(MainActivity.completeShoppingList.get(position).get(MainActivity.TAG_PRICE));
		 MainActivity.completeShoppingList.remove(position);	
		 MainActivity.shoppingProductList.remove(position); 
		 
		 getActivity()
			.getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.root_container, new ReceiptViewFragment(), "receiptFrag")
			.addToBackStack("receiptFrag")
			.commit();
			
		 //Hide inputmethodmanager
		 InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
		if(imm.isActive()){
			imm.hideSoftInputFromWindow(getActivity().findViewById(R.id.root_view).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	//	clicked=1;
	 }
}
