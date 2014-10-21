package com.knewone.util;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;

@SuppressLint("NewApi")
public class ImageUtil {

	public static Bitmap toRoundCorner(Bitmap bitmap,Context mcontext) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		// final float roundPx = bitmap.getWidth();
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawOval(rectF, paint);
		// canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return zoomBitmap(output,mcontext);
	}
	
	/*
	 * 缩放图片
	 */
	public static Bitmap zoomBitmap(Bitmap target,Context mcontext) {
		Bitmap result = null;
		if (target.getByteCount() > 0) {
			int width = target.getWidth();
			int height = target.getHeight();
			Matrix matrix = new Matrix();
			float scaleWidth = ((float) CommonUtil.getScreenWidth(mcontext))
					/ width;
			float scaleHeight = ((float) CommonUtil.getScreenWidth(mcontext))
					/ height;
			matrix.postScale(scaleWidth, scaleHeight);
			result = Bitmap.createBitmap(target, 0, 0, width, height, matrix,
					true);
		}
		return result;
	}
	
}
