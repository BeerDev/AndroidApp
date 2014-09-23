
package com.beerdev.androidapp;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
				MainActivity.completeShoppingList.clear();
				
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
        
       TextView pay = ((TextView) getActivity().findViewById(R.id.tvLikesbeerBuyNow));
       pay.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View v) {
			//we add produkter, date, totSum
		/*	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			
			JSONObject obj = new JSONObject();
			JSONArray list = new JSONArray();
			JSONArray head = new JSONArray();
			try {
				for(int i=0; i < MainActivity.shoppingProductList.size();i++)
    			{
					String item =MainActivity.shoppingProductList.get(i).toString();
    					list.put(item);
    			}
				
			//	head.add(obj);
			//	obj.put("produkter", list);
				
				obj.put("produkter", list);
				obj.put("date", dateFormat.format(date));
				obj.put("totSum", MainActivity.shoppingSum);
			 try {	
				InputStream inputStream = getResources().getAssets().open("receipt.json");
				File jsonFilePath = createFileFromInputStream(inputStream);
				FileWriter jsonFileWriter = new FileWriter(jsonFilePath);           
				jsonFileWriter.write(obj.toString());
				jsonFileWriter.flush();
				jsonFileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
			}
				            
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			
			getActivity()
			.getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.root_container, new ReceiptViewFragment(), "ReceiptFrag")
			.addToBackStack("ReceiptFrag")
			.commit();
			
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
        	@Override
			@SuppressLint("NewApi") public void onClick(View v){
        		//Drawable bg = ivHeart.getBackground();
        		//Comparing Drawables
        		
        		Drawable fDraw = ivHeart.getBackground();
        		Drawable sDraw = getResources().getDrawable(R.drawable.price);
        		Bitmap bitmap = ((BitmapDrawable)fDraw).getBitmap();
        		Bitmap bitmap2 = ((BitmapDrawable)sDraw).getBitmap();
        		String beername= (String)SwipeViewFragment.tvBeerName.getText();
    			int beerprice=Integer.parseInt(MainActivity.productList.get(SwipeViewFragment.posi).get(MainActivity.TAG_PRICE));
    			
        		if(bitmap == bitmap2)
        		{
        			ivHeart.setBackground(getResources().getDrawable(R.drawable.price));
        		//	MainActivity.completeShoppingList.add(MainActivity.completeProductList.get(SwipeViewFragment.posi));
        			addToShoppingList();
        			
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
    
    public void addToShoppingList(){
    	
    	HashMap<String, String> c = MainActivity.completeProductList.get(SwipeViewFragment.posi);

		String id = c.get(MainActivity.TAG_ID);
		String name = c.get(MainActivity.TAG_NAME);
		String path = c.get(MainActivity.TAG_PATH);
		String pris = c.get(MainActivity.TAG_PRICE);
		String info = c.get(MainActivity.TAG_INFO);
		String size = c.get(MainActivity.TAG_SIZE);
		String percent = c.get(MainActivity.TAG_PERC);
		String type = c.get(MainActivity.TAG_TYPE);
		String brew = c.get(MainActivity.TAG_BREW);
		String barcode = c.get(MainActivity.TAG_BARCODE);
		
		// tmp hashmap for single contact
		HashMap<String, String> addBeer = new HashMap<String, String>();

		// Adding each child node to HashMap key => value
		addBeer.put(MainActivity.TAG_ID, id);
		addBeer.put(MainActivity.TAG_NAME, name);
		addBeer.put(MainActivity.TAG_PATH, path);
		addBeer.put(MainActivity.TAG_PRICE, pris);
		addBeer.put(MainActivity.TAG_INFO, info);
		addBeer.put(MainActivity.TAG_SIZE, size);
		addBeer.put(MainActivity.TAG_PERC, percent);
		addBeer.put(MainActivity.TAG_TYPE, type);
		addBeer.put(MainActivity.TAG_BREW, brew);
		addBeer.put(MainActivity.TAG_BARCODE, barcode);

		// adding contact to contact list
		MainActivity.completeShoppingList.add(addBeer);
    	
    }
    
    private File createFileFromInputStream(InputStream inputStream) {

    	   try{
    	      File f = new File("Receipt.json");
    	      OutputStream outputStream = new FileOutputStream(f);
    	      byte buffer[] = new byte[1024];
    	      int length = 0;

    	      while((length=inputStream.read(buffer)) > 0) {
    	        outputStream.write(buffer,0,length);
    	      }

    	      outputStream.close();
    	      inputStream.close();

    	      return f;
    	   }catch (IOException e) {
    	         //Logging exception
    	   }

    	return null;
    	}
    
}
