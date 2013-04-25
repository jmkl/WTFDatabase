package wtf.database;

import android.graphics.Bitmap;

public class Data {
	int _id;
	String nama;
	String serialnumber;
	Bitmap bmp;

	public Data() {
	}

	public Bitmap getBmp() {
		return bmp;
	}

	public void setBmp(Bitmap bmp) {
		this.bmp = bmp;
	}

	public Data(int id, String nama, String sn,Bitmap object) {
		this._id = id;
		this.nama = nama;
		this.serialnumber = sn;
		this.bmp=(Bitmap) object;
	}

	public Data(String nama, String sn,Bitmap bmp) {

		this.nama = nama;
		this.serialnumber = sn;
		this.bmp=(Bitmap) bmp;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

}
