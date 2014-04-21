
package com.beerdev.androidapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A fragmentclass for each page in the viewpager
 * @author BeerDev
 *
 */
public class ScreenSlidePageFragment extends Fragment {
	/**
	 * Local productlist for each fragment in the viewpager
	 */
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
     * Imageview to show the beer.
     */
    ImageView ivBeer;
    
    /**
     * Textviewreference to update the name of the beer in this fragment.
     */
    TextView tvBeerName;

    /**
     * Textviewreference to update the price of the beer in this fragment.
     */
    TextView tvBeerPrice;

    /**
     * Textviewreference to update the size of the beer in this fragment.
     */
    TextView tvBeerSize;

    /**
     * Textviewreference to update the percent of the beer in this fragment.
     */
    TextView tvBeerPercent;

    /**
     * Textviewreference to update the info of the beer in this fragment.
     */
    TextView tvBeerInfo;
    
    /**
     * Creates a fragment of this class with information about current pagenumber
     * @param pageNumber - current pagenumber
     * @return fragment - ScreenSlidePageFragment with information about pagenumber
     */
	public static ScreenSlidePageFragment create(int pageNumber) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        Log.i("pageNumber", Integer.toString(pageNumber));
        return fragment;
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
                R.layout.fragment_swipe, container, false);

        prodFragList = (ArrayList<HashMap<String, String>>) SwipeViewActivity.getArrayList();
        
        tvBeerName = SwipeViewActivity.tvBeerName;
        tvBeerPrice = SwipeViewActivity.tvBeerPrice;
        tvBeerSize = SwipeViewActivity.tvBeerSize;
        tvBeerPercent = SwipeViewActivity.tvBeerPercent;
        tvBeerInfo = SwipeViewActivity.tvBeerInfo;
        Log.i("mPageNumber before text", Integer.toString(mPageNumber));
        
        tvBeerName.setText(prodFragList.get(mPageNumber).get("Artikelnamn"));
        tvBeerPrice.setText(prodFragList.get(mPageNumber).get("Utpris exkl moms")+" kr*");
        tvBeerSize.setText(prodFragList.get(mPageNumber).get("Storlek")+" ml*");
        tvBeerPercent.setText(prodFragList.get(mPageNumber).get("Alkoholhalt")+" %");
        tvBeerInfo.setText(prodFragList.get(mPageNumber).get("Info"));

        Log.i("mPageNumber after text", Integer.toString(mPageNumber));
        
        ivBeer = (ImageView) rootView.findViewById(R.id.imageViewDemo);
        
        //Loader image
        int loader = R.drawable.placeholder;
        String image_url = prodFragList.get(mPageNumber).get("URL");
        ImageLoader imgLoader = new ImageLoader(rootView.getContext());
        imgLoader.DisplayImage(image_url, loader, ivBeer);
        return rootView;
    }
}