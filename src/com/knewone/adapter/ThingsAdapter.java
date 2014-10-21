package com.knewone.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.NetworkImageView;
import com.knewone.activity.R;
import com.knewone.cache.BitmapCache;
import com.knewone.cache.DiskLruImageCache;
import com.knewone.cache.ImageCacheManager;
import com.knewone.manager.BeanManager;
import com.knewone.manager.ThingsBean;
import com.knewone.request.RequestManager;
import com.knewone.util.RoundedImageView;

public class ThingsAdapter extends BaseAdapter  {


	private Context mContext;
	private ArrayList<ThingsBean> mThingsList;
	private ImageLoader imageLoader = null; 
	
	
	public ThingsAdapter(Context context, ArrayList<ThingsBean> thingsList) {
		
		//ArrayList<ThingsBean> thingsList = 
		this.mContext = context;
		mThingsList = thingsList;
	}
	
	@Override
	public int getCount() {
		if (mThingsList != null) {
			return mThingsList.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (mThingsList != null && position > 0 && position < mThingsList.size()) {
			return mThingsList.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.things, null);
			viewHolder = new ViewHolder();
			viewHolder.mThings_title = (TextView) convertView
					.findViewById(R.id.things_title);
			viewHolder.mThings_subtitle = (TextView) convertView
					.findViewById(R.id.things_subtitle);
			
			viewHolder.mAuthor_name = (TextView) convertView
					.findViewById(R.id.author_name);
			
			viewHolder.mFanciers_count = (TextView) convertView
					.findViewById(R.id.fanciers_count);
			
			viewHolder.thing_image = (NetworkImageView) convertView
					.findViewById(R.id.thing_image);
			
			viewHolder.mAuthor_avatar = (RoundedImageView) convertView
					.findViewById(R.id.author_avatar);
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		ThingsBean things = mThingsList.get(position);

		viewHolder.mThings_title.setText(things.getTitle()) ;	
		viewHolder.mThings_subtitle.setText(things.getSubtitle());
		viewHolder.mAuthor_name.setText(things.getAuthor().getName());
		viewHolder.mFanciers_count.setText(things.getFanciers_count());
		//viewHolder.mAuthor_avatar.setImageUrl(things.getAuthor().getAvatar_url(), ImageCacheManager.getInstance().getImageLoader());
		
        BeanManager.RoundImage(viewHolder.mAuthor_avatar, things.getAuthor().getAvatar_url()) ;
		
//        imageLoader = new ImageLoader(RequestManager.getRequestQueue(),new DiskLruImageCache()));  
//        imageLoader.get("http://www.baidu.com/img/baidu_sylogo1.gif",  
//                ImageLoader.getImageListener(viewHolder.mAuthor_avatar,  
//                        R.drawable.ic_launcher, R.drawable.ic_launcher)); 
        
//        ImageLoader imageLoader = new ImageLoader(RequestManager.getRequestQueue(), new BitmapCache());
//        ImageListener listener = ImageLoader.getImageListener(viewHolder.mAuthor_avatar,R.drawable.ic_launcher, R.drawable.ic_launcher);
//        imageLoader.get(things.getAuthor().getAvatar_url(), listener);
        Log.i("thingsdata","url-->:"+ things.getAuthor().getAvatar_url());
        
		viewHolder.thing_image.setImageUrl(things.getCover_url(), ImageCacheManager.getInstance().getImageLoader());
		return convertView;
	}

	static class ViewHolder {
		TextView mThings_title;
		TextView mThings_subtitle;
		NetworkImageView thing_image;
		
		RoundedImageView mAuthor_avatar;
		TextView mAuthor_name;
		TextView mFanciers_count;
		
		String id;
		
		
	}
	
	/**
	 * 
	 * @param thingsList
	 * @param dataFrom  head下拉刷新加载,foot底部上拉加载
	 */
	public void add(ArrayList<ThingsBean> thingsList,String dataFrom)
	{
		
		if("head".equals(dataFrom)){
			if(!thingsList.isEmpty()){
				mThingsList.clear();
				mThingsList.addAll(thingsList);
				Log.i("thingsdata", "mThingsList:" +mThingsList.size());
			}
		}else if("foot".equals(dataFrom)){
			if(!thingsList.isEmpty()){
				mThingsList.addAll(thingsList);
			}
			
//			for (int i = 0; i < thingsList.size(); i++) {
//				ThingsBean things = thingsList.get(i);
//				mThingsList.add(things);
//			}
			
		}
		notifyDataSetChanged();
		
	}
	
}
