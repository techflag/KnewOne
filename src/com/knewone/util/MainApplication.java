package com.knewone.util;

import com.knewone.cache.ImageCacheManager;
import com.knewone.cache.ImageCacheManager.CacheType;
import com.knewone.dao.DaoMaster;
import com.knewone.dao.DaoMaster.OpenHelper;
import com.knewone.dao.DaoSession;
import com.knewone.request.RequestManager;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap.CompressFormat;


public class MainApplication extends Application {
	
	private static int DISK_IMAGECACHE_SIZE = 1024*1024*10;
	private static CompressFormat DISK_IMAGECACHE_COMPRESS_FORMAT = CompressFormat.PNG;
	private static int DISK_IMAGECACHE_QUALITY = 100;  //PNG is lossless so quality is ignored but must be provided
	
	private static DaoMaster daoMaster;  
    private static DaoSession daoSession; 
	
	
	@Override
	public void onCreate() {
		super.onCreate();

		init();
	}

	private void init() {
		RequestManager.init(this);
		createImageCache();
	}
	
	private void createImageCache(){
		ImageCacheManager.getInstance().init(this,
				this.getPackageCodePath()
				, DISK_IMAGECACHE_SIZE
				, DISK_IMAGECACHE_COMPRESS_FORMAT
				, DISK_IMAGECACHE_QUALITY
				, CacheType.MEMORY);
	}
	
	 /** 
     * 取得DaoMaster 
     *  
     * @param context 
     * @return 
     */  
    public static DaoMaster getDaoMaster(Context context) {  
        if (daoMaster == null) {  
            OpenHelper helper = new DaoMaster.DevOpenHelper(context,Constants.KNEWONE_DB_NAME, null);  
            daoMaster = new DaoMaster(helper.getWritableDatabase());  
        }  
        return daoMaster;  
    }  
      
    public static DaoSession getDaoSession(Context context) {  
        if (daoSession == null) {  
            if (daoMaster == null) {  
                daoMaster = getDaoMaster(context);  
            }  
            daoSession = daoMaster.newSession();  
        }  
        return daoSession;  
    }  
	

}
