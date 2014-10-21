package com.knewone.activity;

import java.util.ArrayList;
import java.util.List;

import com.knewone.adapter.ThingsAdapter;
import com.knewone.dao.Things;
import com.knewone.manager.BeanManager;
import com.knewone.manager.ThingsBean;
import com.knewone.manager.ThingsBean.Author;
import com.knewone.request.RequestManager;
import com.knewone.util.CommonUtil;
import com.knewone.util.DbService;
import com.knewone.util.XListView;
import com.knewone.util.XListView.IXListViewListener;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends BaseActivity implements IXListViewListener {

	private final String TAG = getClass().getSimpleName();

	private Context mContext;
	private XListView thingsListView;
	private ArrayList<ThingsBean> mThingsList;
	private ThingsAdapter thingsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = MainActivity.this;
		initView();
		initListView();

	}

	private void initView() {

		mThingsList = new ArrayList<ThingsBean>();

		thingsListView = (XListView) findViewById(R.id.thingslist);
		thingsListView.setPullLoadEnable(true);
		thingsListView.setXListViewListener(this);
		thingsListView.setPullRefreshEnable(true);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	private void initListView() {
		List<Things> thingsList = DbService.getInstance(mContext)
				.loadAllthings();
		for (Things things : thingsList) {
			ThingsBean thingsBean = new ThingsBean();
			thingsBean.setTitle(things.getTitle());
			thingsBean.setSubtitle(things.getSubtitle());
			thingsBean.setCover_url(things.getCover_url());
			thingsBean.setFanciers_count(things.getFanciers_count());
			Author author = new Author();
			author.setAvatar_url(things.getAuthor_avatar());
			author.setName(things.getAuthor_name());
			thingsBean.setAuthor(author);
			mThingsList.add(thingsBean);
		}
		thingsAdapter = new ThingsAdapter(mContext, mThingsList);
		thingsListView.setAdapter(thingsAdapter);
	}

	private void LoadDataFromServer() {
		BeanManager.ThingsRequest(thingsAdapter,thingsListView, mContext);
		thingsListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			}
		});
	}

	@Override
	public void onRefresh() {
		Log.i("thingsdata", "response:onRefresh");
		LoadDataFromServer();
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
		RequestManager.getRequestQueue().cancelAll(this);
	}

}
