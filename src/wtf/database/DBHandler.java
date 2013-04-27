package wtf.database;

import java.util.ArrayList;

import org.apache.http.util.EntityUtils;

import wtf.database.utils.BMPUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {
	String TAG = getClass().getSimpleName();
	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "serialnumber.db";
	// Contacts table name
	private static final String TABLE_SN = "keygen";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAMA = "name";
	private static final String KEY_SN = "serial_number";
	private static final String KEY_BMP = "bmp";

	public DBHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.e(TAG, "OnCreate DB");
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_SN + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAMA + " TEXT,"
				+ KEY_SN + " TEXT," + KEY_BMP + " BLOB" + ")";
		db.execSQL(CREATE_CONTACTS_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.e(TAG, "OnUpgrade DB");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SN);
		onCreate(db);

	}

	void addData(Data data) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAMA, data.getNama());
		cv.put(KEY_SN, data.getSerialnumber());
		cv.put(KEY_BMP, BMPUtils.serializeObject(data.getBmp()));

		db.insert(TABLE_SN, null, cv);
		db.close();
	}

	public Data getData(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_SN, new String[] { KEY_ID, KEY_NAMA,
				KEY_SN }, KEY_ID + "=?", new String[] { String.valueOf(id) },
				null, null, null, null);

		if (cursor != null)
			cursor.moveToFirst();

		Data data = new Data(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2),
				BMPUtils.deserializeObject(cursor.getBlob(3)));
		cursor.close();
		return data;
	}

	public ArrayList<Data> getAllData() {
		ArrayList<Data> datalist = new ArrayList<Data>();
		String selectQuery = "SELECT * FROM " + TABLE_SN;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);
		if (c.moveToFirst()) {
			do {
				Data data = new Data();
				data.set_id(Integer.parseInt(c.getString(0)));
				data.setNama(c.getString(1));
				data.setSerialnumber(c.getString(2));
				data.setBmp(BMPUtils.deserializeObject(c.getBlob(3)));
				datalist.add(data);
			} while (c.moveToNext());
		}
		c.close();
		return datalist;

	}

	public int getDataCount() {
		String countQuery = "SELECT  * FROM " + TABLE_SN;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();
		db.close();
		// return count
		return cursor.getCount();
	}

	public void updateData(long rowId, String nama, String sn) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues args = new ContentValues();
		args.put(KEY_NAMA, nama);
		args.put(KEY_SN, sn);
		db.update(TABLE_SN, args, KEY_ID+"=" + rowId, null);
		db.close();

	}

	public void deleteData(Data data) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_SN, KEY_ID + " =?",
				new String[] { String.valueOf(data.get_id()) });
		db.close();
	}

}
