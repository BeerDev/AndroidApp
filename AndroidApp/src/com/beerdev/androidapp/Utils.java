package com.beerdev.androidapp;


import java.io.InputStream;
import java.io.OutputStream;
/**
 * 
 * The code have been taken from the Android Hive JSON parsing project  
 * @author BeerDev
 *
 */
public class Utils {
    public static void CopyStream(InputStream is, OutputStream os)
    {
        final int buffer_size=8012;
        try
        {
            byte[] bytes=new byte[buffer_size];
            for(;;)
            {
              int count=is.read(bytes, 0, buffer_size);
              if(count==-1)
                  break;
              os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }
}