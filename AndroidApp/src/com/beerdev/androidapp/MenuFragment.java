package com.beerdev.androidapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MenuFragment extends Fragment implements OnClickListener{
	
    
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 ViewGroup menuView = (ViewGroup) inflater.inflate(R.layout.fragment_menu, container, false);
        
		//*******Functions For MENU*********
	       
		ImageView imgGalleri = (ImageView) menuView.findViewById(R.id.imageGalleri);
		ImageView imgLista = (ImageView) menuView.findViewById(R.id.imageLista);
		ImageView imgOltyper = (ImageView) menuView.findViewById(R.id.imageOltyper);
		ImageView imgKistan= (ImageView) menuView.findViewById(R.id.imageKistan);
		ImageView imgUtvecklare = (ImageView) menuView.findViewById(R.id.imageUtvecklare);
		
		imgGalleri.setOnClickListener(this);
		imgLista.setOnClickListener(this);
		imgOltyper.setOnClickListener(this);
		imgKistan.setOnClickListener(this);
		imgUtvecklare.setOnClickListener(this);

		return menuView;
    }
	

	@Override
    public void onClick(View v) {
        switch (v.getId()) {
        	case R.id.imageGalleri:
        		getActivity()
        			.getSupportFragmentManager()
        			.beginTransaction()
        			.replace(R.id.root_container, new SwipeViewFragment(), "swipeFrag")
	    			.addToBackStack("swipeFrag")
        			.commit();

        		FragmentManagerActivity.sm.toggle();
        		break;
        	case R.id.imageLista:
        		getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();

        		FragmentManagerActivity.sm.toggle();
                break;
        	case R.id.imageOltyper:
        		getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new SwipeViewFragment(), "swipeFrag")
	    			.addToBackStack("swipeFrag")
	    			.commit();

        		FragmentManagerActivity.sm.toggle();
                break;
        	case R.id.imageKistan:
        		getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new AboutKistanFragment(), "kistanFrag")
	    			.addToBackStack("kistanFrag")
	    			.commit();
        		FragmentManagerActivity.sm.toggle();
                break;
        	case R.id.imageUtvecklare:
        		getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new AboutUsFragment(), "usFrag")
	    			.addToBackStack("usFrag")
	    			.commit();

        		FragmentManagerActivity.sm.toggle();
                break;
        }
    }

}