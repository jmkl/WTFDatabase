package wtf.database;

public class Data {
	int _id;
	String nama;
	String serialnumber;

	public Data() {
	}

	public Data(int id, String nama, String sn) {
		this._id = id;
		this.nama = nama;
		this.serialnumber = sn;
	}

	public Data(String nama, String sn) {

		this.nama = nama;
		this.serialnumber = sn;
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
