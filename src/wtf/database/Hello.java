package wtf.database;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Hello extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hello);

		DBHandler db = new DBHandler(this);

		db.addData(new Data("0101", "2"));

		List<Data> contacts = db.getAllData();

		for (Data cn : contacts) {
			String log = "Id: " + cn.get_id() + " ,Name: " + cn.getNama()
					+ " ,Phone: " + cn.getSerialnumber();
			// Writing Contacts to log
			Log.d("Name: ", log);
			
			db.deleteData(cn);

		}
		
		
	}

}
