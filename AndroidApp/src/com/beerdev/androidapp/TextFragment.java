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
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
        TextView textBeerNameInfo = (TextView) rootView.findViewById(R.id.swipeBeerNameInfo);
        TextView textBeerPriceInfo = (TextView) rootView.findViewById(R.id.swipeBeerPriceInfo);
        TextView textBeerSizeInfo = (TextView) rootView.findViewById(R.id.swipeBeerSizeInfo);
        TextView textBeerPercentInfo = (TextView) rootView.findViewById(R.id.swipeBeerPercentInfo);
        TextView textBeerInfoInfo = (TextView) rootView.findViewById(R.id.swipeBeerInfoInfo);
        
        //Getting Objects from JSON
        String nameText = prodFragList.get(mPageNumber).get("Artikelnamn");
        String priceText = prodFragList.get(mPageNumber).get("Utpris exkl moms");
        String sizeText = prodFragList.get(mPageNumber).get("Storlek");
        String percentText = prodFragList.get(mPageNumber).get("Alkoholhalt");
        String infoText = prodFragList.get(mPageNumber).get("Info");

        //Setting TextViews
        textBeerNameInfo.setText(nameText);
        textBeerPriceInfo.setText(priceText+"kr*");
        textBeerSizeInfo.setText(sizeText+" ml");
        textBeerPercentInfo.setText(percentText+"%");
        textBeerInfoInfo.setText(infoText);

        final LinearLayout relL2 = (LinearLayout) getParentFragment().getView().findViewById(R.id.text_layout);

        rootView.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getChildFragmentManager()
	        	.beginTransaction()
	        	.setCustomAnimations(R.animator.slide_in_bottom, R.animator.slide_out_bottom)
	        	.remove(getFragmentManager().findFragmentByTag("textFragment"))
	        	.commit();
				Handler handlerTimer = new Handler();
		        handlerTimer.postDelayed(new Runnable(){

					@Override
					public void run() {

						relL2.setVisibility(View.VISIBLE);
					}
		        }, getResources().getInteger(android.R.integer.config_mediumAnimTime));
			}
        	
        });
		
        return rootView;
    }

}