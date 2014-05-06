package com.beerdev.androidapp;


import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * A view to show information about the developmentteam and the application
 * @author BeerDev
 *
 */
public class AboutUsFragment extends Fragment {

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 ViewGroup aboutusView = (ViewGroup) inflater.inflate(R.layout.fragment_aboutus, container, false);
		 FragmentManagerActivity.menu.findItem(R.id.menu_filter).setVisible(false);
		 FragmentManagerActivity.menu.findItem(R.id.menu_search).setVisible(false);
		 getActivity().findViewById(R.id.search_container).setVisibility(View.INVISIBLE);
		 return aboutusView;
	}
}