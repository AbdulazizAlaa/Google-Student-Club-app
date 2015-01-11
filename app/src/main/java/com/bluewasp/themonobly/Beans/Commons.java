package com.bluewasp.themonobly.Beans;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

/**
 * Created by Lenovo on 1/11/2015.
 */
public class Commons {

    public static String createImageFileFromBitmap(Context mContext, String fileName, Bitmap img){
        try{
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            img.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
            FileOutputStream file =  mContext.openFileOutput(fileName, Context.MODE_PRIVATE);
            file.write(bytes.toByteArray());
            file.close();
        }catch(Exception e){

        }
        return fileName;
    }

}
