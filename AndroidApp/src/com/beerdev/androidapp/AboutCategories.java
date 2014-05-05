package com.beerdev.androidapp;

//testar
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A view to show information about the developmentteam and the application
 * @author BeerDev
 *
 */
public class AboutCategories extends Fragment {
 
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 ViewGroup kistanView = (ViewGroup) inflater.inflate(R.layout.fragment_categories, container, false);
		 
		 /*SOLVE WITH FRAGMENTS
		Intent intent = getIntent();
		productList =(ArrayList<HashMap<String,String>>) intent.getSerializableExtra("productList");*/
		 return kistanView;
	}
}