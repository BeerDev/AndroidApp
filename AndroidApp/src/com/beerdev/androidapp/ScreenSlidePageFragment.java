package com.beerdev.androidapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;


public class ScreenSlidePageFragment extends Fragment {


	private ArrayList<HashMap<String, String>> prodFragList;
	
	private static int mCurrentPage;
	private static final String DEBUG_TAG = "MOTION";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        /** Getting the arguments to the Bundle object */
        Bundle data = getArguments();
 
        /** Getting integer data of the key current_page from the bundle */
        mCurrentPage = data.getInt("current_page", 0);
 
    }
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);
        
        prodFragList = (ArrayList<HashMap<String, String>>) ScreenSlidePagerActivity.getArrayList();
        
        //Loader image
        int loader = R.drawable.ic_launcher;

        // Imageview to show
        ImageView image = (ImageView) rootView.findViewById(R.id.slideImageView);
        
        // Image url
        String image_url = prodFragList.get(mCurrentPage).get("URL");
        Log.i("URL", image_url);
        Log.i("POSITION", Integer.toString(mCurrentPage));
        
        // ImageLoader class instance
        ImageLoader imgLoader = new ImageLoader(rootView.getContext());
        
        imgLoader.DisplayImage(image_url, loader, image);
        
        return rootView;
    }
}