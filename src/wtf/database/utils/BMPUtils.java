package wtf.database.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;

public class BMPUtils {
	public static byte[] serializeObject(Bitmap o) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf;
		try {
			o.compress(Bitmap.CompressFormat.PNG, 100, bos);
			buf = bos.toByteArray();
		} finally {
			// Closing bos for the mighty Garbage Collector
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return buf;
	}

	/* Deserialize Object */
	public static Bitmap deserializeObject(byte[] b) {
		Bitmap object = BitmapFactory.decodeByteArray(b, 0, b.length);
		return object;
	}

	public static Bitmap cropBitmap(Bitmap bitmap) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),	bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		// canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
//		canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
//				bitmap.getWidth() / 2, paint);
		canvas.drawRect(0, 0, bitmap.getWidth(), bitmap.getHeight(), paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		// Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);
		// return _bmp;
		return output;
	}
	 public static Bitmap createOutline(Bitmap src) {
	        Paint p = new Paint();
	        p.setColor(Color.GREEN);
	        p.setMaskFilter(new BlurMaskFilter(3, Blur.OUTER));
	        return src.extractAlpha(p, null);
	    }
}
