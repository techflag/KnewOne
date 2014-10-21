package com.knewone.manager;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.knewone.activity.R;
import com.knewone.adapter.ThingsAdapter;
import com.knewone.cache.BitmapCache;
import com.knewone.cache.ImageCacheManager;
import com.knewone.dao.Things;
import com.knewone.request.RequestManager;
import com.knewone.util.CommonUtil;
import com.knewone.util.DbService;
import com.knewone.util.RoundedImageView;
import com.knewone.util.XListView;

public class BeanManager {

	private final String TAG = "thingsdata";
	private static BeanManager mInstance;
	private static ImageLoader mImageLoader; 

	public static BeanManager getInstance() {
		if (mInstance == null) {
			mInstance = new BeanManager();
		}

		return mInstance;
	}

	public static void ThingsRequest(final ThingsAdapter adapter,final XListView thingsListView,
			final Context mContext) {
		
		Log.i("thingsdata", "response:ThingsRequest");
		
		StringRequest stringRequest = new StringRequest(Request.Method.GET,
				"http://knewone.com/api/v1/things.json",
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						Gson gson = new Gson();
						Type listType = new TypeToken<ArrayList<ThingsBean>>() {
						}.getType();
						ArrayList<ThingsBean> thingsList = gson.fromJson(
								response, listType);
						
						adapter.add(thingsList,"head");
						onLoad(thingsListView);
						
						saveThingsList(thingsList, mContext);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.i("thingsdata", "error:" + error.getMessage(),
								error);
					}
				});
		RequestManager.getRequestQueue().add(stringRequest);

	}

	private static void saveThingsList(ArrayList<ThingsBean> thingsList,
			Context mContext) {
		List<ThingsBean> mthingList = null;
		if (thingsList.size() > 0) {
			DbService.getInstance(mContext).deleteAllthings();
		}

		if (thingsList.size() >= 10) {
			mthingList = thingsList.subList(0, 9);
		} else {
			mthingList = thingsList.subList(0, thingsList.size());
		}
		for (ThingsBean bean : mthingList) {
			Things things = new Things();
			things.setTitle(bean.getTitle());
			things.setSubtitle(bean.getSubtitle());
			things.setThingsid(bean.getId());
			things.setCover_url(bean.getCover_url());
			things.setFanciers_count(bean.getFanciers_count());
			things.setAuthor_id(bean.getAuthor().getId());
			things.setAuthor_avatar(bean.getAuthor().getAvatar_url());
			things.setAuthor_name(bean.getAuthor().getName());
			DbService.getInstance(mContext).savethings(things);
		}
	}
	
	private static void onLoad(XListView thingsListView) {
		thingsListView.stopRefresh();
		thingsListView.stopLoadMore();
		thingsListView.setRefreshTime(CommonUtil.getNowTime());
	}
	
	
	
	public static void RoundImage(RoundedImageView mImageView,String url){
     ImageLoader imageLoader = new ImageLoader(RequestManager.getRequestQueue(), new BitmapCache());
      ImageListener listener = ImageLoader.getImageListener(mImageView,R.drawable.picture_man, R.drawable.picture_man);
     imageLoader.get(url, listener);
	}
	
}
