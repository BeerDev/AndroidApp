package com.beerdev.androidapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A view to show information about the developmentteam and the application
 * @author BeerDev
 *
 */
public class AboutProfileFragment extends Fragment {
 
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 final ViewGroup profilView = (ViewGroup) inflater.inflate(R.layout.fragment_aboutprofile, container, false);
		 //Remove items from menubar
		 FragmentManagerActivity.menu.findItem(R.id.menu_filter).setVisible(false);
		 FragmentManagerActivity.menu.findItem(R.id.menu_search).setVisible(false);
		 FragmentManagerActivity.menu.findItem(R.id.menu_close_search).setVisible(false);
		 
		 ImageView profilePic = (ImageView) profilView.findViewById(R.id.ivProfilePic);
		 
		 //Remove searchcontainer
		 getActivity().findViewById(R.id.search_container).setVisibility(View.INVISIBLE);
		 
		 View history = profilView.findViewById(R.id.tvProfilHistory);
		 View settings = profilView.findViewById(R.id.tvProfilSettings);
		 View credit = profilView.findViewById(R.id.tvProfilAddCredit);
		 //For Settings
		 final View PName= profilView.findViewById(R.id.tvProfilNamn);
		 final View PEdit= profilView.findViewById(R.id.etProfileChangeName);
		 final View PButton= profilView.findViewById(R.id.bvProfilSetNameOK);
		 
		 credit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainActivity.currentCredit=MainActivity.currentCredit + 50;
				FragmentManagerActivity.menu.findItem(R.id.menu_credit).setTitle(
						"CREDIT = "+Integer.toString(MainActivity.currentCredit));
			}
			 
		 });
		 settings.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				
				if(PName.getVisibility()==View.VISIBLE){
					PName.setVisibility(View.GONE);
					PEdit.setVisibility(View.VISIBLE);
					PButton.setVisibility(View.VISIBLE);
				}else{
					PName.setVisibility(View.VISIBLE);
					PEdit.setVisibility(View.GONE);
					PButton.setVisibility(View.GONE);
				}
				
				
			}
			 
		 });
	
		 PButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				((TextView) PName).setText(((TextView) PEdit).getText().toString());
				

				if(PName.getVisibility()==View.VISIBLE){
					PName.setVisibility(View.GONE);
					PEdit.setVisibility(View.VISIBLE);
					PButton.setVisibility(View.VISIBLE);
				}else{
					PName.setVisibility(View.VISIBLE);
					PEdit.setVisibility(View.GONE);
					PButton.setVisibility(View.GONE);
				}
				//Remove searchcontainer
				 getActivity().findViewById(R.id.search_container).setVisibility(View.INVISIBLE);
			}
			 
		 });
		 
		 return profilView;
	}
}