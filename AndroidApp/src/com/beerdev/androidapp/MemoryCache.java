package com.beerdev.androidapp;

import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import android.graphics.Bitmap;
 
/**
 * 
 * The code have been taken from the Android Hive JSON parsing project
 * @author BeerDev
 *
 */
public class MemoryCache {
    private Map<String, SoftReference<Bitmap>> cache=Collections.synchronizedMap(new HashMap<String, SoftReference<Bitmap>>());
  
    public Bitmap get(String id){
        if(!cache.containsKey(id))
            return null;
        SoftReference<Bitmap> ref=cache.get(id);
        return ref.get();
    }
  
    public void put(String id, Bitmap bitmap){
    	if(ImageLoader.thumb==1)
        cache.put(id+"thumb", new SoftReference<Bitmap>(bitmap));
    	else
    		cache.put(id, new SoftReference<Bitmap>(bitmap));
    }
  
    public void clear() {
        cache.clear();
    }
}
