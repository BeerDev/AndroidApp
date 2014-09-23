package com.beerdev.androidapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import android.app.Activity;
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
public class FSmallArrayAdapter extends BaseAdapter implements Filterable{
    /**
     * Activity related to the LazyAdapter
     */
    private Activity activity;
	private ItemsFilter mFilter;
	
	public ArrayList<HashMap<String, String>> productsData;
	public ArrayList<HashMap<String, String>> allProductsData;
    /**
     * Layoutinflater
     */
    private static LayoutInflater inflater=null;
    
    /**
     * Reference to the imageloader
     */
    public ImageLoader imageLoader; 
    MemoryCache memoryCache;
    
    @SuppressWarnings("unchecked")
	public FSmallArrayAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        productsData = (ArrayList<HashMap<String, String>>) d;
        allProductsData = (ArrayList<HashMap<String, String>>) productsData.clone();
        inflater = (LayoutInflater)activity.getLayoutInflater();
        imageLoader=new ImageLoader(activity.getApplicationContext());
        memoryCache=new MemoryCache();
               
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
            vi = inflater.inflate(R.layout.receipt_item, null);
        
        // Getting relevant information
        TextView productName = (TextView)vi.findViewById(R.id.listName); 
        TextView productCat = (TextView)vi.findViewById(R.id.listCat); 
        TextView productPrice = (TextView)vi.findViewById(R.id.listPrice);
        ImageView thumbnailImage = (ImageView)vi.findViewById(R.id.listImageURL);        
        
        HashMap<String, String> product = new HashMap<String, String>();
        product = productsData.get(position);
        
        // Setting all values in listview
        imageLoader.DisplayImageIcon(product.get(MainActivity.TAG_PATH),NO_SELECTION, thumbnailImage);
        productName.setText(product.get(MainActivity.TAG_NAME));
        productCat.setText(product.get(MainActivity.TAG_TYPE));
        productPrice.setText(product.get(MainActivity.TAG_PRICE) + " kr*");
            
       if(position==MainActivity.completeShoppingList.size()-1){
        	TextView TotSum = (TextView)vi.findViewById(R.id.listPriceSum);
        	TotSum.setVisibility(View.VISIBLE);
        	TotSum.setText(Integer.toString(MainActivity.shoppingSum));
        }
        
        return vi;
    }
    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new ItemsFilter();
        }
        return mFilter;
    }

    private class ItemsFilter extends Filter {
        @SuppressWarnings("unchecked")
        @Override
        public String convertResultToString(Object resultValue) {
            return ((HashMap<String, String>) (resultValue))
                    .get(MainActivity.TAG_NAME);
        }

        @Override
        protected FilterResults performFiltering(CharSequence s) {

            if (s != null) {
                ArrayList<HashMap<String, String>> tmpAllData = allProductsData;
                ArrayList<HashMap<String, String>> tmpDataShown = productsData;
                tmpDataShown.clear();
                for (int i = 0; i < tmpAllData.size(); i++) {
                    if (tmpAllData.get(i).get(MainActivity.TAG_NAME)
                            .toLowerCase()
                            .startsWith(s.toString().toLowerCase())) {
                        tmpDataShown.add(tmpAllData.get(i));
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = tmpDataShown;
                filterResults.count = tmpDataShown.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @SuppressWarnings("unchecked")
        protected void publishResults(CharSequence prefix, FilterResults results) {
            productsData = (ArrayList<HashMap<String, String>>) results.values;
            if (results.count > 0) {
            	MainActivity.productList=(ArrayList<HashMap<String, String>>) productsData.clone();
                notifyDataSetChanged();
            } else {
            	notifyDataSetInvalidated();
            }
        }
    }
   
}