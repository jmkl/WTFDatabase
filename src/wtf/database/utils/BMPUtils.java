package wtf.database.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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
		Bitmap 	object = BitmapFactory.decodeByteArray(b, 0, b.length);		

		return object;
	}
}
