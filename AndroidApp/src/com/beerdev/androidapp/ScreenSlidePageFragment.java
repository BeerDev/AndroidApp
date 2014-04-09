package com.beerdev.androidapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ScreenSlidePageFragment extends Fragment {
	
	private static final String KEY_POSITION = "position";
	
	static ScreenSlidePageFragment newInstance(int position) {
	    ScreenSlidePageFragment frag = new ScreenSlidePageFragment();
	    Bundle args = new Bundle();

	    args.putInt(KEY_POSITION, position);
	    frag.setArguments(args);

	    return(frag);
	}
	
	private ArrayList<HashMap<String, String>> prodFragList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);
        
        prodFragList = (ArrayList<HashMap<String, String>>) ScreenSlidePagerActivity.getArrayList();
        
        //Loader image
        int loader = R.drawable.ic_launcher;
        
        // Imageview to show
        ImageView image = (ImageView) rootView.findViewById(R.id.image2);

        int position = getArguments().getInt(KEY_POSITION, -1);
 
        // Image url
        String image_url = prodFragList.get(position).get("URL");
         
        // ImageLoader class instance
        ImageLoader imgLoader = new ImageLoader(rootView.getContext());
        
        imgLoader.DisplayImage(image_url, loader, image);
        
        return rootView;
    }
}