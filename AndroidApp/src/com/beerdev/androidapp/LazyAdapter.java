package com.beerdev.androidapp;

import java.util.ArrayList;
import java.util.HashMap;

import com.beerdev.androidapp.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A LazyAdapter for showing thumbnails in the listview
 * @author BeerDev
 *
 */
public class LazyAdapter extends BaseAdapter {
    /**
     * Activity related to the LazyAdapter
     */
    private Activity activity;
    
    /**
     * Data of the products
     */
    private ArrayList<HashMap<String, String>> productsData;
    
    /**
     * Layoutinflater
     */
    private static LayoutInflater inflater=null;
    
    /**
     * Reference to the imageloader
     */
    public ImageLoader imageLoader; 
    
    public LazyAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        productsData=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
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
            vi = inflater.inflate(R.layout.list_item, null);
        
        // Getting relevant information
        TextView articleName = (TextView)vi.findViewById(R.id.listNamn); 
        TextView id = (TextView)vi.findViewById(R.id.listID);
        ImageView thumbnailImage = (ImageView)vi.findViewById(R.id.listImageURL);
        
        HashMap<String, String> productList = new HashMap<String, String>();
        productList = productsData.get(position);
        
        // Setting all values in listview
        int loader = R.drawable.placeholder;
        articleName.setText(productList.get(MainActivity.TAG_NAME));
        id.setText(productList.get(MainActivity.TAG_ID));
        imageLoader.DisplayImage(productList.get(MainActivity.TAG_PATH),loader, thumbnailImage);
        return vi;
    }
}