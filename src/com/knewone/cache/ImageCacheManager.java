package com.knewone.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.knewone.request.RequestManager;

public class ImageCacheManager{

	public enum CacheType {
		DISK
		, MEMORY
	}
	
	private static ImageCacheManager mInstance;
	
	private ImageLoader mImageLoader;

	private ImageCache mImageCache;
	
	public static ImageCacheManager getInstance(){
		if(mInstance == null)
			mInstance = new ImageCacheManager();
		
		return mInstance;
	}
	
	public void init(Context context, String uniqueName, int cacheSize, CompressFormat compressFormat, int quality, CacheType type){
		switch (type) {
		case DISK:
			mImageCache= new DiskLruImageCache(context, uniqueName, cacheSize, compressFormat, quality);
			break;
		case MEMORY:
			mImageCache = new BitmapLruImageCache(cacheSize);
		default:
			mImageCache = new BitmapLruImageCache(cacheSize);
			break;
		}
		
		mImageLoader = new ImageLoader(RequestManager.getRequestQueue(), mImageCache);
	}
	
	public Bitmap getBitmap(String url) {
		try {
			return mImageCache.getBitmap(createKey(url));
		} catch (NullPointerException e) {
			throw new IllegalStateException("Disk Cache Not initialized");
		}
	}

	public void putBitmap(String url, Bitmap bitmap) {
		try {
			mImageCache.putBitmap(createKey(url), bitmap);
		} catch (NullPointerException e) {
			throw new IllegalStateException("Disk Cache Not initialized");
		}
	}
	
	
	/**
	 * 	Executes and image load
	 * @param url
	 * 		location of image
	 * @param listener
	 * 		Listener for completion
	 */
	public void getImage(String url, ImageListener listener){
		mImageLoader.get(url, listener);
	}

	/**
	 * @return
	 * 		instance of the image loader
	 */
	public ImageLoader getImageLoader() {
		return mImageLoader;
	}
	
	/**
	 * Creates a unique cache key based on a url value
	 * @param url
	 * 		url to be used in key creation
	 * @return
	 * 		cache key value
	 */
	private String createKey(String url){
		return String.valueOf(url.hashCode());
	}
	
	
}

