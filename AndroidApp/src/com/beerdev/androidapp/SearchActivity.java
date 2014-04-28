package com.beerdev.androidapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class SearchActivity extends Activity{
	
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actionbar_search);
        
        final SearchAdapter searchAdapter=new SearchAdapter(SearchActivity.this, MainActivity.completeProductList);      
        
    	final AutoCompleteTextView textView =  (AutoCompleteTextView) findViewById(R.id.search_box);
	 
	    textView.setThreshold(1);
	    textView.setAdapter(searchAdapter);
	    searchAdapter.notifyDataSetChanged();
	    textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	 
	        @Override
	    	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	            // do something when the user clicks
	        	// getting values from selected ListItem			
				String ID = ((TextView) view.findViewById(R.id.listID)).getText().toString();

				// Starting single contact activity
				Intent in = new Intent(getApplicationContext(),
						SwipeViewActivity.class);
				MainActivity.productList = (ArrayList<HashMap<String, String>>) SearchAdapter.searchData.clone();
				
				//Sending BildID and ContactList to SwipeViewActivity
				in.putExtra("BildID", position);
				startActivity(in);
	        }
	    });
	    textView.setOnTouchListener(new OnTouchListener() {
	        @Override
	        public boolean onTouch(View v, MotionEvent event) {
	            final int DRAWABLE_LEFT = 0;
	            final int DRAWABLE_TOP = 1;
	            final int DRAWABLE_RIGHT = 2;
	            final int DRAWABLE_BOTTOM = 3;

	            if(event.getAction() == MotionEvent.ACTION_UP) {
	                if(event.getX() >= (textView.getRight() - textView.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
	        			textView.setText("");
	        			searchAdapter.notifyDataSetChanged();
	                }
	            }
	            return false;
	        }
	    });
	    textView.addTextChangedListener(new TextWatcher() {         
	        @Override
	        public void onTextChanged(CharSequence s, int start, int before, int count) {
	        	searchAdapter.getFilter().filter(s);
	        }

	        @Override
	        public void beforeTextChanged(CharSequence s, int start, int count,
	                int after) {                

	        }

	        @Override
	        public void afterTextChanged(Editable s) {

	        }
	    });
	    textView.requestFocus();
	    textView.setFocusableInTouchMode(true);
	    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(textView, InputMethodManager.SHOW_FORCED);
	}
}
