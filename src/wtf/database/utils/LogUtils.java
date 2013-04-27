package wtf.database.utils;

import android.util.Log;

public class LogUtils {
	public static class CekEksekTime {
		private long startnow;
		private long endnow;

		public void setStart() {
			startnow = System.nanoTime();

		}

		public void setEnd() {
			endnow = System.nanoTime();
			Log.d("MYTAG", "Execution time: " + (endnow - startnow) + " ms");
		}
	}
}
