package com.beerdev.androidapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
 
public class SearchActivity extends Activity {
 
    private TextView txtQuery;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
        Log.i("onCREATE", "SEARCH");
    }
 
    @Override
    protected void onNewIntent(Intent intent) {
    	super.onNewIntent(intent);
        setIntent(intent);

        Log.i("onCREATE", "SEARCH");
        handleIntent(intent);
    }
 
    /**
     * Handling intent data
     */
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
        	Log.i("ACTION SEARCH", intent.getStringExtra(SearchManager.QUERY));
        }
 
    }
}