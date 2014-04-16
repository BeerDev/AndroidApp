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

public class LazyAdapter extends BaseAdapter {
    
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    
    public LazyAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
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

     //   TextView title = (TextView)vi.findViewById(R.id.title); // title
        TextView Artikelnamn = (TextView)vi.findViewById(R.id.listNamn); // artist name
        TextView ID = (TextView)vi.findViewById(R.id.listID); // duration
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.listImageURL); // thumb image
        
        HashMap<String, String> contactList = new HashMap<String, String>();
        contactList = data.get(position);
        
        // Setting all values in listview
      //  title.setText(song.get(CustomizedListView.KEY_TITLE));
        int loader = R.drawable.ic_launcher;
        Artikelnamn.setText(contactList.get(MainActivity.TAG_NAME));
        ID.setText(contactList.get(MainActivity.TAG_ID));
        imageLoader.DisplayImage(contactList.get(MainActivity.TAG_PATH),loader, thumb_image);
        return vi;
    }
}