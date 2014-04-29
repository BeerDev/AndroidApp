package com.beerdev.androidapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A LazyAdapter for showing thumbnails in the listview
 * @author BeerDev
 *
 */
public class SearchAdapter extends BaseAdapter implements Filterable{
    /**
     * Activity related to the LazyAdapter
     */
    private Activity activity;
	
	private ItemsFilter mFilter;

	public static ArrayList<HashMap<String, String>> searchData;
	public ArrayList<HashMap<String, String>> allSearchData;
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
	public SearchAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        searchData = (ArrayList<HashMap<String, String>>) d;
        allSearchData = (ArrayList<HashMap<String, String>>) searchData.clone();
        inflater = (LayoutInflater)activity.getLayoutInflater();
        imageLoader=new ImageLoader(activity.getApplicationContext());
        memoryCache=new MemoryCache();
        
    }

    public int getCount() {
        return searchData.size();
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
            vi = inflater.inflate(R.layout.list_item, null);
        
        // Getting relevant information
        TextView articleName = (TextView)vi.findViewById(R.id.listNamn); 
        TextView id = (TextView)vi.findViewById(R.id.listID);
        ImageView thumbnailImage = (ImageView)vi.findViewById(R.id.listImageURL);
        
        HashMap<String, String> productList = new HashMap<String, String>();
        productList = searchData.get(position);
        
            // Setting all values in listview
            int loader = R.drawable.placeholder;
            imageLoader.DisplayImage(productList.get(MainActivity.TAG_PATH),NO_SELECTION, thumbnailImage);
            articleName.setText(productList.get(MainActivity.TAG_NAME));
            id.setText(productList.get(MainActivity.TAG_ID));
           
        
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

        @Override
        protected FilterResults performFiltering(CharSequence s) {
        	
        	FilterResults filterResults = new FilterResults();
        	
        	if(s == null || s.length() == 0){
        		filterResults.values = allSearchData;
        		filterResults.count = allSearchData.size();
        	}
        	else{
                ArrayList<HashMap<String, String>> tmpAllData = allSearchData;
                ArrayList<HashMap<String, String>> tmpDataShown = searchData;
                tmpDataShown.clear();
                for (int i = 0; i < tmpAllData.size(); i++) {
                    if (tmpAllData.get(i).get(MainActivity.TAG_NAME)
                            .toLowerCase()
                            .contains(s.toString().toLowerCase())) {
                        tmpDataShown.add(tmpAllData.get(i));
                    }
                }
                filterResults.values = tmpDataShown;
                filterResults.count = tmpDataShown.size();
        	}
        	return filterResults;
        }

		@Override
        protected void publishResults(CharSequence prefix, FilterResults results) {
            searchData = (ArrayList<HashMap<String, String>>) results.values;
            MainActivity.productList = (ArrayList<HashMap<String, String>>) searchData.clone();
			if (results!=null && results.count > 0) {
                notifyDataSetChanged();
            } else {
            	notifyDataSetInvalidated();
            }
        }
    }
}