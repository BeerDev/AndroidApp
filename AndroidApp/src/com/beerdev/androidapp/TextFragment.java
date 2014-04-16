package com.beerdev.androidapp;

/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MotionEventCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TextFragment extends Fragment {


	private ArrayList<HashMap<String, String>> prodFragList;	
	/**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";
    
    private int mPageNumber;
	
	public static TextFragment create(int pageNumber) {
        TextFragment fragment = new TextFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }
	
	public TextFragment(){
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.text_fragment2, container, false);
        

        prodFragList = (ArrayList<HashMap<String, String>>) ScreenSlidePagerActivity.getArrayList();
        
        //-----TEXT!--------
        //Getting TextView
        TextView textNamn= (TextView) rootView.findViewById(R.id.textBeerName);
        TextView textPrice= (TextView) rootView.findViewById(R.id.textBeerPrice);
        TextView textInfo= (TextView) rootView.findViewById(R.id.textBeerInfo);
        
        //Getting Objects from JSON
        String namn_text = prodFragList.get(mPageNumber).get("Artikelnamn");
        String pris_text = prodFragList.get(mPageNumber).get("Utpris exkl moms");
        String info_text = prodFragList.get(mPageNumber).get("Info");

        //Setting TextViews
        textNamn.setText(namn_text);
        textPrice.setText(pris_text+"kr*");
        textInfo.setText(info_text);
		
        return rootView;
    }
}