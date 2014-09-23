package com.beerdev.androidapp;

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
		 ViewGroup categoryView = (ViewGroup) inflater.inflate(R.layout.fragment_categories, container, false);
		 //Remove items from menubar
		 FragmentManagerActivity.menu.findItem(R.id.menu_filter).setVisible(false);
		 FragmentManagerActivity.menu.findItem(R.id.menu_search).setVisible(false);
		 FragmentManagerActivity.menu.findItem(R.id.menu_close_search).setVisible(false);
		 
		 //Remove searchcontainer
		 getActivity().findViewById(R.id.search_container).setVisibility(View.INVISIBLE);
		 
		 		View aleButton = categoryView.findViewById(R.id.aleHeadTextContent);
		 		View lagerButton = categoryView.findViewById(R.id.lagerHeadTextContent);
		 		View pilsnerButton = categoryView.findViewById(R.id.PilsnerHeadTextContent);
		 		View bitterButton = categoryView.findViewById(R.id.BitterHeadTextContent);
		 		View ipaButton = categoryView.findViewById(R.id.ipaHeadTextContent);
		 		View impIpaButton = categoryView.findViewById(R.id.impIpaHeadTextContent);
		 		View stoutButton = categoryView.findViewById(R.id.stoutHeadTextContent);
		 		View impStoutButton = categoryView.findViewById(R.id.impStoutHeadTextContent);
		 		View porterButton = categoryView.findViewById(R.id.porterHeadTextContent);
		 		View veteButton = categoryView.findViewById(R.id.veteHeadTextContent);
		 		View trappistButton = categoryView.findViewById(R.id.trappistHeadTextContent);
		 		View lambikButton = categoryView.findViewById(R.id.lambikHeadTextContent);
		 		View barleyButton = categoryView.findViewById(R.id.barleyHeadTextContent);
		 		View saisonButton = categoryView.findViewById(R.id.saisonHeadTextContent);
		 		View surButton = categoryView.findViewById(R.id.surHeadTextContent);
		 		View mjodButton = categoryView.findViewById(R.id.mjodHeadTextContent);
		 		View specButton = categoryView.findViewById(R.id.specHeadTextContent);


		 aleButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				FragmentManagerActivity.mToggleChecked=false;
				FragmentManagerActivity.setToggleButton();
				FragmentManagerActivity.searchView.setQuery("Ale", false);
				FragmentManagerActivity.searchView.setIconified(false);
				
			menuBarFix();
				
				getActivity()
    			.getSupportFragmentManager()
    			.beginTransaction()
    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
    			.addToBackStack("listFrag")
    			.commit();
			}
		 });

		 lagerButton.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View arg0) {
						FragmentManagerActivity.mToggleChecked=false;
						FragmentManagerActivity.setToggleButton();
						FragmentManagerActivity.searchView.setQuery("Lager", false);
						FragmentManagerActivity.searchView.setIconified(false);
						
						menuBarFix();
							
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
					FragmentManagerActivity.searchView.setQuery("Pilsner", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					menuBarFix();
						
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
					FragmentManagerActivity.searchView.setQuery("Bitter", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					menuBarFix();
						
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
					FragmentManagerActivity.searchView.setQuery("IPA", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					menuBarFix();
						
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
					FragmentManagerActivity.searchView.setQuery("Imperial/Double IPA", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					menuBarFix();
						
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
					FragmentManagerActivity.searchView.setQuery("Stout", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					menuBarFix();
						
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					 //Hide inputmethodmanager
					FragmentManagerActivity.categorySearchShowInput = false;
			 }
		 });

		 impStoutButton.setOnClickListener(new OnClickListener(){
			 @Override
				public void onClick(View arg0) {
					FragmentManagerActivity.mToggleChecked=false;
					FragmentManagerActivity.setToggleButton();
					FragmentManagerActivity.searchView.setQuery("Imperial Stout", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					menuBarFix();
						
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
					FragmentManagerActivity.searchView.setQuery("Porter", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					menuBarFix();
						
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
					FragmentManagerActivity.searchView.setQuery("Veteöl", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					menuBarFix();
						
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
					FragmentManagerActivity.searchView.setQuery("Trappist/Abbey", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					menuBarFix();
						
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
					FragmentManagerActivity.searchView.setQuery("Lambik/Fruktöl", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					menuBarFix();
						
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
					FragmentManagerActivity.searchView.setQuery("Barley Wine", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					menuBarFix();
						
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
					FragmentManagerActivity.searchView.setQuery("Saison", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					menuBarFix();
						
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
					FragmentManagerActivity.searchView.setQuery("Suröl", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					menuBarFix();
						
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
					FragmentManagerActivity.searchView.setQuery("Mjöd", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					menuBarFix();
						
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
					FragmentManagerActivity.searchView.setQuery("Specialöl", false);
					FragmentManagerActivity.searchView.setIconified(false);
					
					menuBarFix();
						
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					}
			 });

		 return categoryView;
	}
	private void menuBarFix(){
		//Fixing Menu bar
		FragmentManagerActivity.menu.findItem(R.id.menu_filter).setVisible(true);
		 FragmentManagerActivity.menu.findItem(R.id.menu_search).setVisible(true);
		 FragmentManagerActivity.menu.findItem(R.id.menu_close_search).setVisible(false);
	}
}
