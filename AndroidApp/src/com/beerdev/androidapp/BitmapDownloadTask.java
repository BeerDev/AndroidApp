package com.beerdev.androidapp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

class BitmapDownloadTask extends AsyncTask<Void, Void, Void> {
	
	private ProgressDialog pDialog;

	FileCache fileCache = new FileCache(FragmentManagerActivity.globalContext);
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();			
		// Showing progress dialog
		pDialog = new ProgressDialog(FragmentManagerActivity.globalContext);

	}

	@Override
	protected Void doInBackground(Void... arg0) {
		// looping through All products
		for (int i = 0; i < MainActivity.completeProductList.size(); i++) {
			String url = MainActivity.completeProductList.get(i).get(MainActivity.TAG_PATH);
			
			//Check if file exist
			File f = null;
		   	f=fileCache.getFile(url);

		   	if(f==null)
		   	{
		   	 try {
		            URL imageUrl = new URL(url);
		            HttpURLConnection conn = (HttpURLConnection)imageUrl.openConnection();
		            conn.setConnectTimeout(30000);
		            conn.setReadTimeout(30000);
		            conn.setInstanceFollowRedirects(true);
		            InputStream is=conn.getInputStream();
		            OutputStream os = new FileOutputStream(f);
		            Utils.CopyStream(is, os);
		            os.close();
		        } catch (Exception ex){
		           ex.printStackTrace();

		        }
		   	}
			
		}
	
		return null;
	}
	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);			
		// Showing progress dialog
		//pDialog = new ProgressDialog(FragmentManagerActivity.globalContext);
		Log.i("FINNISH WITH DOWNLOAD","DOWNLOAD COMPLETE_-----");
		//Toast.makeText(this, "No internet connection..", Toast.LENGTH_LONG).show();

	}

}