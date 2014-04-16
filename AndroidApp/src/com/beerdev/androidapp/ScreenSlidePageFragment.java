package com.beerdev.androidapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;


public class ScreenSlidePageFragment extends Fragment {


	private ArrayList<HashMap<String, String>> prodFragList;
	private int mShortAnimationDuration;
	
	private ImageView imageView;
	private TextView textViewName;
	private TextView textViewPrice;
	private TextView textViewInfo;
	
	private GestureDetector mGestureDetector;

	 private Animation slideRight, slideLeft;
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
                R.layout.swipe_fragment_layout, container, false);
        
   
        //FrameLayout myLayout = (FrameLayout) rootView.findViewById(R.id.text_frame_layout);
        //final FrameLayout fl = (FrameLayout) rootView.findViewById(R.id.frame_layout_text);
        
        prodFragList = (ArrayList<HashMap<String, String>>) ScreenSlidePagerActivity.getArrayList();
        
        ImageView imageView = (ImageView) rootView.findViewById(R.id.slideImageView);
          
        //Loader image
        int loader = R.drawable.ic_launcher;
        String image_url = prodFragList.get(mPageNumber).get("URL");
        ImageLoader imgLoader = new ImageLoader(rootView.getContext());
        imgLoader.DisplayImage(image_url, loader, imageView);
        
        
        //-----TEXT!--------
        //Getting TextView
        TextView textNamn= (TextView) rootView.findViewById(R.id.slideBeerName);
        TextView textPrice= (TextView) rootView.findViewById(R.id.slideBeerPrice);
        TextView textInfo= (TextView) rootView.findViewById(R.id.slideBeerInfo);
        
        //Getting Objects from JSON
        String namn_text = prodFragList.get(mPageNumber).get("Artikelnamn");
        String pris_text = prodFragList.get(mPageNumber).get("Utpris exkl moms");
        String info_text = prodFragList.get(mPageNumber).get("Info");

        //Setting TextViews
        textNamn.setText(namn_text);
        textPrice.setText(pris_text+"kr*");
        textInfo.setText(info_text);
        
        textNamn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		        getChildFragmentManager()
		        	.beginTransaction()
		        	.setCustomAnimations(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_bottom)
		        	.add(R.id.content, TextFragment.create(mPageNumber), "textFragment")
		        	.addToBackStack(null)
		        	.commit();
			}
        	
        });
        
        return rootView;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }

}