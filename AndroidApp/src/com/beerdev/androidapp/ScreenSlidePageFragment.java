
package com.beerdev.androidapp;

import java.util.ArrayList;

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
import android.view.View.OnClickListener;
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
        
        
        ImageView ivCross = (ImageView) getActivity().findViewById(R.id.ivClearShoppList);
        ivCross.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				//MainActivity.shoppingProductList.clear();
				//MainActivity.shoppingProductList=new ArrayList<String>();
				MainActivity.shoppingSum=0;
				
				while(MainActivity.shoppingProductList.size() >0)
    			{
    				MainActivity.shoppingProductList.remove(0);    				
    			}
				if(MainActivity.shoppingSum!=0)
    			{
    				((TextView) getActivity().findViewById(R.id.tvLikesbeerPrice)).setText(Integer.toString(MainActivity.shoppingSum));
    				(getActivity().findViewById(R.id.llSwipeShoppingPanel)).setVisibility(View.VISIBLE);
    			}else{
    				((TextView) getActivity().findViewById(R.id.tvLikesbeerPrice)).setText("");
    				(getActivity().findViewById(R.id.llSwipeShoppingPanel)).setVisibility(View.INVISIBLE);
    			}
			}
        	
        });
        
        
    }
    
    @SuppressLint("NewApi") @Override
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
        		Drawable sDraw = getResources().getDrawable(R.drawable.price);
        		Bitmap bitmap = ((BitmapDrawable)fDraw).getBitmap();
        		Bitmap bitmap2 = ((BitmapDrawable)sDraw).getBitmap();
        		String beername= (String)SwipeViewFragment.tvBeerName.getText();
    			int beerprice=Integer.parseInt((String) MainActivity.productList.get(SwipeViewFragment.posi).get(MainActivity.TAG_PRICE));
    			
        		if(bitmap == bitmap2)
        		{
        			ivHeart.setBackground(getResources().getDrawable(R.drawable.price));
        			MainActivity.shoppingProductList.add(beername);
        			MainActivity.shoppingSum=MainActivity.shoppingSum+beerprice;
        		}
        /*		else{
        			ivHeart.setBackground(getResources().getDrawable(R.drawable.heart));
        			MainActivity.shoppingSum=MainActivity.shoppingSum-beerprice;
        			for(int j=0; j<3;j++){
        			for(int i=0; i < MainActivity.shoppingProductList.size();i++)
        			{
        				if(MainActivity.shoppingProductList.get(i).equals(beername))
        				{
        					MainActivity.shoppingProductList.remove(i);
        				}
        				
        			}
        			}

        		}
       	*/
        		if(MainActivity.shoppingSum!=0)
    			{
    				((TextView) getActivity().findViewById(R.id.tvLikesbeerPrice)).setText(Integer.toString(MainActivity.shoppingSum));
    				(getActivity().findViewById(R.id.llSwipeShoppingPanel)).setVisibility(View.VISIBLE);
    			}else{
    				((TextView) getActivity().findViewById(R.id.tvLikesbeerPrice)).setText("");
    				(getActivity().findViewById(R.id.llSwipeShoppingPanel)).setVisibility(View.INVISIBLE);
    			}
        	}});
  
        
        
        return rootView;
    }
    
  
}
