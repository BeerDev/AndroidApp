package com.beerdev.androidapp;

import java.io.File;
import android.content.Context;
/**
 * A class for caching files
 * 
 * The code have been taken from the Android Hive JSON parsing project
 * @author BeerDev
 *
 */
public class FileCache {
  
	/**
	 * Path to the place where the files is caching
	 */
    private File cacheDir;
    private File cacheDirThumb;
    
    /**
     * Initialize the cacheDirectory
     * @param context
     */
    public FileCache(Context context){
        //Find the dir to save cached images
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDir=new File(android.os.Environment.getExternalStorageDirectory(),"BeerDevImages");
        else
            cacheDir=context.getCacheDir();
        if(!cacheDir.exists())
            cacheDir.mkdirs();
        
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDirThumb=new File(android.os.Environment.getExternalStorageDirectory(),"BeerDevImages");
        else
            cacheDirThumb=context.getCacheDir();
        if(!cacheDirThumb.exists())
            cacheDirThumb.mkdirs();
    }
  
    /**
     * A method to get file to cache
     * @param url - path to file that is going to be cached
     * @return f - the file that has been cached
     */
    public File getFile(String url){
        String filename=String.valueOf(url.hashCode());
        File f = new File(cacheDir, filename);
        return f;
  
    }
    
    public File getFileThumb(String url){
        String filename=String.valueOf(url.hashCode());
        File f = new File(cacheDirThumb, filename);
        return f;
  
    }
    /**
     * Clears all the files in the cacheDir
     */
    public void clear(){
        File[] files=cacheDir.listFiles();
        if(files==null)
            return;
        for(File f:files)
            f.delete();
    }
  
}