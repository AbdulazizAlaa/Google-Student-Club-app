package com.bluewasp.themonobly.Beans;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Lenovo on 1/11/2015.
 * Singleton class to handle volley network requests
 */
public class NetworkHelper {
    private static NetworkHelper mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mContext;

    //private constructor for the singleton class
    private NetworkHelper(Context ctx){
        mContext = ctx;
        mRequestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mRequestQueue, new LruBitmapCache(mContext));
    }

    //function to get instance of the static variable of singleton class
    public static NetworkHelper getInstance(Context ctx){
        if(mInstance == null){
            mInstance = new NetworkHelper(ctx);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            /*HttpStack stack = new HurlStack();
            Network network = new BasicNetwork(stack);
            
            Cache cache = new DiskBasedCache(mContext.getCacheDir(), 1024 * 1024);

            mRequestQueue = new RequestQueue(cache, network);*/
            mRequestQueue = Volley.newRequestQueue(mContext);
        }
        return mRequestQueue;
    }

    public <T> void addRequest(Request<T> request){
        getRequestQueue().add(request);
    }

    public ImageLoader getImageLoader(){
        return mImageLoader;
    }

}
