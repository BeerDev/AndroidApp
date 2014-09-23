package com.beerdev.androidapp;

import java.util.ArrayList;
import java.util.HashMap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;

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
		ImageView imgBarcode = (ImageView) menuView.findViewById(R.id.imageBarcode);

		TextView txtProfile = (TextView) menuView.findViewById(R.id.menuTextProfile);
		TextView shoppList = (TextView) menuView.findViewById(R.id.menuTestLink);
		TextView receipt = (TextView) menuView.findViewById(R.id.menuReceiptLink);

		
		imgGalleri.setOnClickListener(this);
		imgLista.setOnClickListener(this);
		imgOltyper.setOnClickListener(this);
		imgKistan.setOnClickListener(this);
		imgUtvecklare.setOnClickListener(this);
		imgBarcode.setOnClickListener(this);
		txtProfile.setOnClickListener(this);
		shoppList.setOnClickListener(this);
		receipt.setOnClickListener(this);
		
		return menuView;
    }
	

	@Override
    public void onClick(View v) {
		LinearLayout pay =(LinearLayout) getActivity().findViewById(R.id.llReceiptLastPay);
        switch (v.getId()) {
        	case R.id.imageGalleri:
        		pay.setVisibility(View.GONE);
        		
        		if(MainActivity.productList.isEmpty()){
        			MainActivity.productList = (ArrayList<HashMap<String, String>>) MainActivity.completeProductList.clone();
        			FragmentManagerActivity.searchView.setQuery("", false);
        			FragmentManagerActivity.searchView.setIconified(true);	
        			ListViewFragment.fastScrollAdapter.notifyDataSetChanged();
        			/*getActivity().findViewById(R.id.search_container).setVisibility(View.INVISIBLE);
        			FragmentManagerActivity.setLayoutMargins(getActivity().findViewById(R.id.root_view), getActivity());
        			SwipeViewFragment.mPager.setSwipeable(true); 
        			FragmentManagerActivity.menu.getItem(R.id.menu_filter).setVisible(true);
        			FragmentManagerActivity.menu.getItem(R.id.menu_filter).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        			InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        		    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        			*/
        			
        		}
        		getActivity()
        			.getSupportFragmentManager()
        			.beginTransaction()
        			.replace(R.id.root_container, new SwipeViewFragment(), "swipeFrag")
	    			.addToBackStack("swipeFrag")
        			.commit();

        		FragmentManagerActivity.sm.toggle();
        		break;
        	case R.id.imageLista:
        		pay.setVisibility(View.GONE);
        		getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();

        		FragmentManagerActivity.sm.toggle();
                break;
        	case R.id.imageOltyper:
        		pay.setVisibility(View.GONE);
        		getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new AboutCategories(), "categoriesFrag")
	    			.addToBackStack("swipeFrag")
	    			.commit();

        		FragmentManagerActivity.sm.toggle();
                break;
        	case R.id.imageKistan:
        		pay.setVisibility(View.GONE);
        		getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new AboutKistanFragment(), "kistanFrag")
	    			.addToBackStack("kistanFrag")
	    			.commit();
        		FragmentManagerActivity.sm.toggle();
                break;
        	case R.id.imageUtvecklare:
        		pay.setVisibility(View.GONE);
        		getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new AboutUsFragment(), "usFrag")
	    			.addToBackStack("usFrag")
	    			.commit();

        		FragmentManagerActivity.sm.toggle();
                break;
        	case R.id.imageBarcode:
        		pay.setVisibility(View.GONE);
        		//MainActivity.productList = (ArrayList<HashMap<String, String>>) MainActivity.completeProductList.clone();
        		if(MainActivity.productList.isEmpty()){
      			  Toast.makeText(getActivity(), "Finns inga produkter att scanna!", Toast.LENGTH_LONG).show();
        		}
        		else{
            		IntentIntegrator integrator = new IntentIntegrator(getActivity());
    				integrator.initiateScan();
            		getActivity()
    	    			.getSupportFragmentManager()
    	    			.beginTransaction()
    	    			.replace(R.id.root_container, new SwipeViewFragment(), "swipeFrag")
    	    			.addToBackStack("swipeFrag")
    	    			.commit();

            		FragmentManagerActivity.sm.toggle();
        		}
        		break;

        	case R.id.menuTextProfile:
        		getActivity()
    			.getSupportFragmentManager()
    			.beginTransaction()
    			.replace(R.id.root_container, new AboutProfileFragment(), "profileFrag")
    			.addToBackStack("profileFrag")
    			.commit();
    		FragmentManagerActivity.sm.toggle();
            break;
        		
        	case R.id.menuTestLink:
        		
        		getActivity()
    			.getSupportFragmentManager()
    			.beginTransaction()
    			.replace(R.id.root_container, new ReceiptViewFragment(), "receiptFrag")
    			.addToBackStack("receiptFrag")
    			.commit();

    		FragmentManagerActivity.sm.toggle();
            break;
            
            case R.id.menuReceiptLink:
        		
            	pay.setVisibility(View.GONE);
        		getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new AboutReceiptFragment(), "receiptFrag")
	    			.addToBackStack("receiptFrag")
	    			.commit();

    		FragmentManagerActivity.sm.toggle();
            break;
        }
    }

}
