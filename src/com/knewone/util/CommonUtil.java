package com.knewone.util;


import java.text.SimpleDateFormat;

import com.knewone.activity.R;



import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

public class CommonUtil {
	public static boolean hasSDCard() {
		String status = Environment.getExternalStorageState();
		if (!status.equals(Environment.MEDIA_MOUNTED)) {
			return false;
		}
		return true;
	}

	public static String getRootFilePath() {
		if (hasSDCard()) {
			return Environment.getExternalStorageDirectory().getAbsolutePath()
					+ "/";
		} else {
			return Environment.getDataDirectory().getAbsolutePath() + "/data/";
		}
	}

	public static boolean checkNetState(Context context) {
		boolean netstate = false;
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						netstate = true;
						break;
					}
				}
			}
		}
		return netstate;
	}

	public static void showToask(Context context, String tip) {
		Toast.makeText(context, tip, Toast.LENGTH_SHORT).show();
	}

	@SuppressWarnings("deprecation")
	public static int getScreenWidth(Context context) {
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		return display.getWidth();
	}

	@SuppressWarnings("deprecation")
	public static int getScreenHeight(Context context) {
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		return display.getHeight();
	}

	public static String trim(String text) {
		String result = "";
		result = text.replaceAll("\\r", "").replaceAll("\\n", "")
				.replaceAll("\\s", "").replaceAll("\\t", "")
				.replaceAll("&nbsp;", "");
		return result;
	}
	
	
	public static String getNowTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ly_time = sdf.format(new java.util.Date());
		return ly_time;
	}

	public static String howTimeAgo(Context context, long t) {
		String msg = "";
		long nowTime = System.currentTimeMillis();
		long time = (nowTime - t) / (60 * 1000);
		if (time > 0 && time < 60) {
			msg = time + context.getString(R.string.minuteago);
		} else if (time == 0) {
			msg = context.getString(R.string.at_now);
		}
		time = (nowTime - t) / (60 * 1000 * 60);
		if (time > 0 && time < 24) {
			msg = time + context.getString(R.string.hourago);
		}
		time = (nowTime - t) / (60 * 1000 * 60 * 24);
		if (time > 0) {
			msg = time + context.getString(R.string.dayago);
		}
		return msg;
	}

}
