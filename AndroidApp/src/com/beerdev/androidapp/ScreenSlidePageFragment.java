
package com.beerdev.androidapp;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
     * Creates a fragment of this class with information about current pagenumber
     * @param pageNumber - current pagenumber
     * @return fragment - ScreenSlidePageFragment with information about pagenumber
     */
	public static ScreenSlidePageFragment create(int pageNumber) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        Log.i("create pageNumber", Integer.toString(pageNumber));
        return fragment;
    }
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments() != null ? getArguments().getInt(ARG_PAGE) : 0;
        
        Log.i("onCreate mPageNumber", Integer.toString(mPageNumber));
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.childfragment_swipe, container, false); 
        
        ivBeer = (ImageView) rootView.findViewById(R.id.ivSwipeImage);

        //Loader image
        int loader = R.drawable.placeholder;
        String image_url = MainActivity.productList.get(mPageNumber).get("URL");
        ImageLoader imgLoader = new ImageLoader(rootView.getContext());
        imgLoader.DisplayImage(image_url, loader, ivBeer);

        
        final ImageView ivHeart = (ImageView) rootView.findViewById(R.id.ivSwipeHeart);
        ivHeart.setOnClickListener(new View.OnClickListener() {
        	@SuppressLint("NewApi") public void onClick(View v){
        		//Drawable bg = ivHeart.getBackground();
        		//Comparing Drawables
        		
        		Drawable fDraw = ivHeart.getBackground();
        		Drawable sDraw = getResources().getDrawable(R.drawable.heart);
        		Bitmap bitmap = ((BitmapDrawable)fDraw).getBitmap();
        		Bitmap bitmap2 = ((BitmapDrawable)sDraw).getBitmap();
        		if(bitmap == bitmap2)
        		{
        			ivHeart.setBackground(getResources().getDrawable(R.drawable.heart_ifylld));
        			String beername=(String) SwipeViewFragment.tvBeerName.getText();
        			String beerprice=(String) SwipeViewFragment.tvBeerPrice.getText();
        			((TextView) getActivity().findViewById(R.id.tvLikesbeerName)).setText(beername);
        			((TextView) getActivity().findViewById(R.id.tvLikesbeerPrice)).setText(beerprice);
        		}
        		else{
        			ivHeart.setBackground(getResources().getDrawable(R.drawable.heart));
        			
        			((TextView) getActivity().findViewById(R.id.tvLikesbeerName)).setText("");
        			((TextView) getActivity().findViewById(R.id.tvLikesbeerPrice)).setText("");
        		}
        	}});
        
        return rootView;
    }
}
