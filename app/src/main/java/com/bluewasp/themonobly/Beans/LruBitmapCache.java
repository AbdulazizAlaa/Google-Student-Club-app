package com.bluewasp.themonobly.Beans;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.DisplayMetrics;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by Lenovo on 1/11/2015.
 */
public class LruBitmapCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {
    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    public LruBitmapCache(int maxSize) {
        super(maxSize);
    }

    public LruBitmapCache(Context ctx){
        super(getCacheSize(ctx));
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes() * value.getHeight();
    }

    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url, bitmap);
    }

    //Returns a cache size equal to approximately 3 screens worth of images
    public static int getCacheSize(Context ctx){
        final DisplayMetrics metric = ctx.getResources().getDisplayMetrics();

        final int screenWidth = metric.widthPixels;
        final int screenHeight = metric.heightPixels;

        // 4 bytes for every pixel
        final int screenBytes = screenWidth * screenHeight * 4;

        return screenBytes * 3;
    }

}
