package com.beerdev.androidapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

/**
 * A LazyAdapter for showing thumbnails in the listview
 * @author BeerDev
 *
 */
public class FSmallReceiptAdapter extends BaseAdapter {
    /**
     * Activity related to the LazyAdapter
     */
    private Activity activity;
	private int layout;
    
	public ArrayList<String> productsData;
	public ArrayList<String> allProductsData;
	
	 
    /**
     * Layoutinflater
     */
    private static LayoutInflater inflater=null;

    @SuppressWarnings("unchecked")
	public FSmallReceiptAdapter(Activity a, ArrayList<String> d1, ArrayList<String> d2) {
        activity = a;
        productsData = (ArrayList<String>) d1;
       allProductsData = (ArrayList<String>) d2;
        inflater = (LayoutInflater)activity.getLayoutInflater();
        
               
    }

   
	public int getCount() {
        return productsData.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.receipt_list_elem, parent);
        
        // Getting relevant information
        TextView productName = (TextView)vi.findViewById(R.id.tvListReceiptName); 
        TextView productPrice = (TextView)vi.findViewById(R.id.tvListReceiptPrice); 
        
    //    HashMap<String, String> product = new HashMap<String, String>();
    //    product = productsData.get(position);
        
        // Setting all values in listview
        productName.setText(productsData.get(0));  
        productPrice.setText(allProductsData.get(0));
        
        return vi;
    }
      
}