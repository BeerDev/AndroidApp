package com.beerdev.androidapp;

import com.beerdev.androidapp.*;
import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.support.v4.app.Fragment;


public class ScreenSlidePageFragment extends Fragment {


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
        ImageView image = (ImageView) rootView.findViewById(R.id.slideImageView);

        // Image url
        String image_url = prodFragList.get(0).get("URL");
         
        // ImageLoader class instance
        ImageLoader imgLoader = new ImageLoader(rootView.getContext());
        
        imgLoader.DisplayImage(image_url, loader, image);
        
        return rootView;
    }
}