package com.knewone.util;

import java.util.List;

import com.knewone.dao.DaoSession;
import com.knewone.dao.Things;
import com.knewone.dao.ThingsDao;

import android.content.Context;
import android.util.Log;

public class DbService {

	private static final String TAG = DbService.class.getSimpleName();
	private static DbService instance;
	private static Context appContext;
	private DaoSession mDaoSession;
	private ThingsDao thingsDao;
	
	
	private DbService() {
	}

	public static DbService getInstance(Context context) {
		if (instance == null) {
			instance = new DbService();
			if (appContext == null){
				appContext = context.getApplicationContext();
			}
			instance.mDaoSession = MainApplication.getDaoSession(context);
			instance.thingsDao = instance.mDaoSession.getThingsDao();
		}
		return instance;
	}
	
	
	public Things loadthings(long id) {
		return thingsDao.load(id);
	}
	
	public List<Things> loadAllthings(){
		return thingsDao.loadAll();
	}
	
	/**
	 * query list with where clause
	 * ex: begin_date_time >= ? AND end_date_time <= ?
	 * @param where where clause, include 'where' word
	 * @param params query parameters
	 * @return
	 */
	
	public List<Things> querythings(String where, String... params){
		return thingsDao.queryRaw(where, params);
	}
	
	
	/**
	 * insert or update things
	 * @param things
	 * @return insert or update things id
	 */
	public long savethings(Things things){
		return thingsDao.insertOrReplace(things);
	}
	
	
	/**
	 * insert or update thingsList use transaction
	 * @param list
	 */
	public void saveThingsLists(final List<Things> list){
	    	if(list == null || list.isEmpty()){
			     return;
		    }
		    thingsDao.getSession().runInTx(new Runnable() {
			@Override
			public void run() {
				for(int i=0; i<list.size(); i++){
					Things things = list.get(i);
					thingsDao.insertOrReplace(things);
				}
			}
		});
		
	}
	
	/**
	 * delete all things
	 */
	public void deleteAllthings(){
		thingsDao.deleteAll();
	}
	
	/**
	 * delete things by id
	 * @param id
	 */
	public void deletethings(long id){
		thingsDao.deleteByKey(id);
		Log.i(TAG, "delete");
	}
	
	public void deletethings(Things things){
		thingsDao.delete(things);
	}

}
