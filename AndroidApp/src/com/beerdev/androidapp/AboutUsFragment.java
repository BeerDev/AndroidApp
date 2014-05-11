package com.beerdev.androidapp;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * A view to show information about the developmentteam and the application
 * @author BeerDev
 *
 */
public class AboutUsFragment extends Fragment {

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 ViewGroup aboutusView = (ViewGroup) inflater.inflate(R.layout.fragment_aboutus, container, false);
		 //Remove items from menubar
		 FragmentManagerActivity.menu.findItem(R.id.menu_filter).setVisible(false);
		 FragmentManagerActivity.menu.findItem(R.id.menu_search).setVisible(false);
		 FragmentManagerActivity.menu.findItem(R.id.menu_close_search).setVisible(false);
		 
		 //Remove searchcontainer
		 getActivity().findViewById(R.id.search_container).setVisibility(View.INVISIBLE);
		 
		 ImageButton ibFacebook = (ImageButton) aboutusView.findViewById(R.id.ibFacebook);
			
		 ibFacebook.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent facebookIntent = getOpenFacebookIntent(getActivity());
				startActivity(facebookIntent);
			}
			 
		 });
			
		 return aboutusView;
	}
	public static Intent getOpenFacebookIntent(Context context) {

		   try {
		    context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
		    return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/229588483907154"));
		   } catch (Exception e) {
		    return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/beerdev"));
		   }
	}
}