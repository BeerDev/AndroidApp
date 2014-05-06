package com.beerdev.androidapp;

import org.json.JSONException;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

/**
 * A view to show information about the developmentteam and the application
 * @author BeerDev
 *
 */
public class AboutCategories extends Fragment {
 

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 final ViewGroup kistanView = (ViewGroup) inflater.inflate(R.layout.fragment_categories, container, false);

		 		View aleButton = kistanView.findViewById(R.id.aleHeadTextContent);
		 		View lagerButton = kistanView.findViewById(R.id.lagerHeadTextContent);
		 		View pilsnerButton = kistanView.findViewById(R.id.PilsnerHeadTextContent);
		 		View bitterButton = kistanView.findViewById(R.id.BitterHeadTextContent);
		 		View ipaButton = kistanView.findViewById(R.id.ipaHeadTextContent);
		 		View impIpaButton = kistanView.findViewById(R.id.impIpaHeadTextContent);
		 		View stoutButton = kistanView.findViewById(R.id.stoutHeadTextContent);
		 		View impStoutButton = kistanView.findViewById(R.id.impStoutHeadTextContent);
		 		View porterButton = kistanView.findViewById(R.id.porterHeadTextContent);
		 		View veteButton = kistanView.findViewById(R.id.veteHeadTextContent);
		 		View trappistButton = kistanView.findViewById(R.id.trappistHeadTextContent);
		 		View lambikButton = kistanView.findViewById(R.id.lambikHeadTextContent);
		 		View barleyButton = kistanView.findViewById(R.id.barleyHeadTextContent);
		 		View saisonButton = kistanView.findViewById(R.id.saisonHeadTextContent);
		 		View surButton = kistanView.findViewById(R.id.surHeadTextContent);
		 		View mjodButton = kistanView.findViewById(R.id.mjodHeadTextContent);
		 		View specButton = kistanView.findViewById(R.id.specHeadTextContent);


		 aleButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				FragmentManagerActivity.mToggleChecked=false;
				FragmentManagerActivity.setToggleButton();
				FragmentManagerActivity.searchView.setQuery("ale", false);
				FragmentManagerActivity.searchView.setIconified(false);
				
				getActivity()
    			.getSupportFragmentManager()
    			.beginTransaction()
    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
    			.addToBackStack("listFrag")
    			.commit();
			}

		 });

		 lagerButton.setOnClickListener(new OnClickListener(){

					public void onClick(View arg0) {
						FragmentManagerActivity.mToggleChecked=false;
						FragmentManagerActivity.setToggleButton();
						FragmentManagerActivity.searchView.setQuery("lager", false);
						FragmentManagerActivity.searchView.setIconified(false);
						
						getActivity()
		    			.getSupportFragmentManager()
		    			.beginTransaction()
		    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
		    			.addToBackStack("listFrag")
		    			.commit();
				 
				}

			 });

		 pilsnerButton.setOnClickListener(new OnClickListener(){

			 @Override
				public void onClick(View arg0) {
					FragmentManagerActivity.mToggleChecked=false;
					FragmentManagerActivity.setToggleButton();
					FragmentManagerActivity.searchView.setQuery("pilsner", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();

			 }
			 });
		 bitterButton.setOnClickListener(new OnClickListener(){
			 @Override
				public void onClick(View arg0) {
					FragmentManagerActivity.mToggleChecked=false;
					FragmentManagerActivity.setToggleButton();
					FragmentManagerActivity.searchView.setQuery("bitter", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
				}
		 	});

		 ipaButton.setOnClickListener(new OnClickListener(){
			 @Override
				public void onClick(View arg0) {
					FragmentManagerActivity.mToggleChecked=false;
					FragmentManagerActivity.setToggleButton();
					FragmentManagerActivity.searchView.setQuery("ipa", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					}
			 });

		 impIpaButton.setOnClickListener(new OnClickListener(){
			 @Override
				public void onClick(View arg0) {
					FragmentManagerActivity.mToggleChecked=false;
					FragmentManagerActivity.setToggleButton();
					FragmentManagerActivity.searchView.setQuery("imperial/doubleipa", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					}
			 });

		 stoutButton.setOnClickListener(new OnClickListener(){
			 @Override
				public void onClick(View arg0) {
					FragmentManagerActivity.mToggleChecked=false;
					FragmentManagerActivity.setToggleButton();
					FragmentManagerActivity.searchView.setQuery("stout", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					}
			 });

		 impStoutButton.setOnClickListener(new OnClickListener(){
			 @Override
				public void onClick(View arg0) {
					FragmentManagerActivity.mToggleChecked=false;
					FragmentManagerActivity.setToggleButton();
					FragmentManagerActivity.searchView.setQuery("imperial stout", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					}
			 });

		 porterButton.setOnClickListener(new OnClickListener(){
			 @Override
				public void onClick(View arg0) {
					FragmentManagerActivity.mToggleChecked=false;
					FragmentManagerActivity.setToggleButton();
					FragmentManagerActivity.searchView.setQuery("porter", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					}
			 });

		 veteButton.setOnClickListener(new OnClickListener(){
			 @Override
				public void onClick(View arg0) {
					FragmentManagerActivity.mToggleChecked=false;
					FragmentManagerActivity.setToggleButton();
					FragmentManagerActivity.searchView.setQuery("veteöl", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					}
			 });

		 trappistButton.setOnClickListener(new OnClickListener(){
			 @Override
				public void onClick(View arg0) {
					FragmentManagerActivity.mToggleChecked=false;
					FragmentManagerActivity.setToggleButton();
					FragmentManagerActivity.searchView.setQuery("trappist/abbey", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					}
			 });

		 lambikButton.setOnClickListener(new OnClickListener(){
			 @Override
				public void onClick(View arg0) {
					FragmentManagerActivity.mToggleChecked=false;
					FragmentManagerActivity.setToggleButton();
					FragmentManagerActivity.searchView.setQuery("lambik/fruktöl", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					}
			 });
			
		 barleyButton.setOnClickListener(new OnClickListener(){
			 @Override
				public void onClick(View arg0) {
					FragmentManagerActivity.mToggleChecked=false;
					FragmentManagerActivity.setToggleButton();
					FragmentManagerActivity.searchView.setQuery("barley wine", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					}
			 });

		 saisonButton.setOnClickListener(new OnClickListener(){
			 @Override
				public void onClick(View arg0) {
					FragmentManagerActivity.mToggleChecked=false;
					FragmentManagerActivity.setToggleButton();
					FragmentManagerActivity.searchView.setQuery("saison", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					}
			 });
		 surButton.setOnClickListener(new OnClickListener(){
			 @Override
				public void onClick(View arg0) {
					FragmentManagerActivity.mToggleChecked=false;
					FragmentManagerActivity.setToggleButton();
					FragmentManagerActivity.searchView.setQuery("suröl", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					}
			 });

		 mjodButton.setOnClickListener(new OnClickListener(){
			 @Override
				public void onClick(View arg0) {
					FragmentManagerActivity.mToggleChecked=false;
					FragmentManagerActivity.setToggleButton();
					FragmentManagerActivity.searchView.setQuery("mjöd", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					}
			 });

		 specButton.setOnClickListener(new OnClickListener(){
			 @Override
				public void onClick(View arg0) {
					FragmentManagerActivity.mToggleChecked=false;
					FragmentManagerActivity.setToggleButton();
					FragmentManagerActivity.searchView.setQuery("specialöl", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					}
			 });


		 /*SOLVE WITH FRAGMENTS
		Intent intent = getIntent();
		productList =(ArrayList<HashMap<String,String>>) intent.getSerializableExtra("productList");*/
		 return kistanView;
	}
}