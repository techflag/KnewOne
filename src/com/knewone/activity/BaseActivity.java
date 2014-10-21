/*
 * Created by Storm Zhang, Feb 11, 2014.
 */

package com.knewone.activity;

import android.app.Activity;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;
import com.knewone.util.CrashHandler;

public class BaseActivity extends SherlockActivity {
	protected Activity activity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activity = this;
		CrashHandler crashHandler = CrashHandler.getInstance();
		crashHandler.init(getApplicationContext());
	}

	@Override
	public void onStop() {
		super.onStop();
	}

}
