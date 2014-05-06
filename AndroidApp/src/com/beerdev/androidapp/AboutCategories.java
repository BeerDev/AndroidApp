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
		 ViewGroup kistanView = (ViewGroup) inflater.inflate(R.layout.fragment_categories, container, false);
		 
		 		View aleButton = kistanView.findViewById(R.id.aleHeadTextContent);
		 		View lagerButton = kistanView.findViewById(R.id.lagerHeadTextContent);
		 		View pilsnerButton = kistanView.findViewById(R.id.pilsnerHeadTextContent);
		 		View bitterButton = kistanView.findViewById(R.id.bitterHeadTextContent);
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
				// TODO Auto-generated method stub
			
				try {
					Sort.Filter("ale", "Kategori");
				getActivity()
    			.getSupportFragmentManager()
    			.beginTransaction()
    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
    			.addToBackStack("listFrag")
    			.commit();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			 
		 });
		
		 lagerButton.setOnClickListener(new OnClickListener(){
			 
			 @Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				
					try {
						Sort.Filter("lager", "Kategori");
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				 
			 });
		 
		 pilsnerButton.setOnClickListener(new OnClickListener(){
			 
			 @Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				
					try {
						Sort.Filter("pilsner", "Kategori");
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				 
			 });
		 bitterButton.setOnClickListener(new OnClickListener(){
			 
			 @Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				
					try {
						Sort.Filter("bitter", "Kategori");
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				 
			 });
		 
		 ipaButton.setOnClickListener(new OnClickListener(){
			 
			 @Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				
					try {
						Sort.Filter("ipa", "Kategori");
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				 
			 });
		 
		 impIpaButton.setOnClickListener(new OnClickListener(){
			 
			 @Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				
					try {
						Sort.Filter("imperial/doubleipa", "Kategori");	
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				 
			 });
		 
		 stoutButton.setOnClickListener(new OnClickListener(){
			 
			 @Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				
					try {
						Sort.Filter("stout", "Kategori");	
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				 
			 });
		 
		 impStoutButton.setOnClickListener(new OnClickListener(){
			 
			 @Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				
					try {
						Sort.Filter("imperial stout", "Kategori");	
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				 
			 });
		 
		 porterButton.setOnClickListener(new OnClickListener(){
			 
			 @Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				
					try {
						Sort.Filter("porter", "Kategori");	
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				 
			 });
		 
		 veteButton.setOnClickListener(new OnClickListener(){
			 
			 @Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				
					try {
						Sort.Filter("veteöl", "Kategori");	
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				 
			 });
		 
		 trappistButton.setOnClickListener(new OnClickListener(){
			 
			 @Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				
					try {
						Sort.Filter("trappist/abbey", "Kategori");	
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				 
			 });
		 
		 lambikButton.setOnClickListener(new OnClickListener(){
			 
			 @Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				
					try {
						Sort.Filter("lambik/fruktöl", "Kategori");	
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				 
			 });
		 
		 barleyButton.setOnClickListener(new OnClickListener(){
			 
			 @Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				
					try {
						Sort.Filter("barley wine", "Kategori");	
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				 
			 });
		 
		 saisonButton.setOnClickListener(new OnClickListener(){
			 
			 @Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				
					try {
						Sort.Filter("saison", "Kategori");	
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				 
			 });
		 
		 surButton.setOnClickListener(new OnClickListener(){
			 
			 @Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				
					try {
						Sort.Filter("suröl", "Kategori");	
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				 
			 });
		 
		 mjodButton.setOnClickListener(new OnClickListener(){
			 
			 @Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				
					try {
						Sort.Filter("mjöd", "Kategori");	
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				 
			 });
		 
		 specButton.setOnClickListener(new OnClickListener(){
			 
			 @Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				
					try {
						Sort.Filter("specialöl", "Kategori");	
					getActivity()
	    			.getSupportFragmentManager()
	    			.beginTransaction()
	    			.replace(R.id.root_container, new ListViewFragment(), "listFrag")
	    			.addToBackStack("listFrag")
	    			.commit();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				 
			 });
		 
		 
		 
		 /*SOLVE WITH FRAGMENTS
		Intent intent = getIntent();
		productList =(ArrayList<HashMap<String,String>>) intent.getSerializableExtra("productList");*/
		 return kistanView;
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}