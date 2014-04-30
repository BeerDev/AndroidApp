package com.beerdev.androidapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MenuActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		ImageView imgGalleri = (ImageView) findViewById(R.id.imageGalleri);
		imgGalleri.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Starting single contact activity
				Intent intentSwipe = new Intent(getApplicationContext(),
						SwipeViewActivity.class);
				//Sending BildID and productList to SwipeViewActivity
				intentSwipe.putExtra("BildID", 0);
				startActivity(intentSwipe);
			}
		});
	ImageView imgLista = (ImageView) findViewById(R.id.imageLista);
	imgGalleri.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// Starting single contact activity
			Intent intentList = new Intent(getApplicationContext(),
					ListViewActivity.class);
			//Sending BildID and productList to SwipeViewActivity
			intentList.putExtra("BildID", 0);
			startActivity(intentList);
		}
	});
	/*
	ImageView imgOltyper = (ImageView) findViewById(R.id.imageOltyper);
	imgGalleri.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// Starting single contact activity
			Intent intentList = new Intent(getApplicationContext(),
					OmKista.class);
			//Sending BildID and productList to SwipeViewActivity
			intentList.putExtra("BildID", 0);
			startActivity(intentList);
		}
	});
	*/
	ImageView imgKistan= (ImageView) findViewById(R.id.imageKistan);
	imgGalleri.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// Starting single contact activity
			Intent intentKistan = new Intent(getApplicationContext(),
					OmKistan.class);
			//Sending BildID and productList to SwipeViewActivity
			intentKistan.putExtra("BildID", 0);
			startActivity(intentKistan);
		}
	});
	ImageView imgUtvecklare = (ImageView) findViewById(R.id.imageUtvecklare);
	imgGalleri.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// Starting single contact activity
			Intent intentUtvecklare = new Intent(getApplicationContext(),
					OmOss.class);
			//Sending BildID and productList to SwipeViewActivity
			intentUtvecklare.putExtra("BildID", 0);
			startActivity(intentUtvecklare);
		}
	});
	}
}
