package com.beerdev.androidapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MotionEventCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class ScreenSlidePageFragment extends Fragment {


	private ArrayList<HashMap<String, String>> prodFragList;
	
	
	/**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static ScreenSlidePageFragment create(int pageNumber) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ScreenSlidePageFragment() {
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
                R.layout.fragment_screen_slide_page, container, false);
        prodFragList = (ArrayList<HashMap<String, String>>) ScreenSlidePagerActivity.getArrayList();
     // Imageview to show
        ImageView image = (ImageView) rootView.findViewById(R.id.slideImageView);
      //-----TEXT!--------
        //Getting TextView
        final TextView textnamn= (TextView) rootView.findViewById(R.id.slideBeerName);
        final TextView textPrice= (TextView) rootView.findViewById(R.id.slideBeerPrice);
        final TextView textInfo= (TextView) rootView.findViewById(R.id.slideBeerInfo);
        
        //Getting Objects from JSON
        String Pris_text="first";
        String Namn_text = prodFragList.get(mPageNumber).get("Artikelnamn");
        Pris_text = prodFragList.get(mPageNumber).get("Utpris exkl moms");
        String Info_text = prodFragList.get(mPageNumber).get("Info");

        //Setting TextViews
        textnamn.setText(Namn_text);
        textPrice.setText(Pris_text+"kr*");
        textInfo.setText(Info_text);

    	image.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				int action = MotionEventCompat.getActionMasked(event);
				switch(action){
					case(MotionEvent.ACTION_UP):
						  textnamn.setVisibility(View.INVISIBLE);
						  textInfo.setVisibility(View.INVISIBLE);
					  	  textPrice.setVisibility(View.INVISIBLE);
			              Fragment textFragment = TextFragment.create(mPageNumber);
			              getChildFragmentManager()
			              		.beginTransaction()
			              		.addToBackStack(null)
			              		.setCustomAnimations(R.anim.abc_slide_in_bottom, R.anim.abc_fade_out)
			              		.replace(R.id.beer_container, textFragment, "textFragment").commit();
				}
				return true;
			}
          });
        //Loader image
        int loader = R.drawable.ic_launcher;

        
        //Loading and displaying image
        String image_url = prodFragList.get(mPageNumber).get("URL");
        
        ImageLoader imgLoader = new ImageLoader(rootView.getContext());
        imgLoader.DisplayImage(image_url, loader, image);
        
        return rootView;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }

}