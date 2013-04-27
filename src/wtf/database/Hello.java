package wtf.database;

import java.util.ArrayList;

import wtf.database.utils.LogUtils.CekEksekTime;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class Hello extends Activity implements OnClickListener,OnItemClickListener {
	private Button add;
	private ListView lv;
	private DBAdapter adapter;
	ArrayList<Data> datas;
	DBHandler db;
	Bitmap tes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hello);
		CekEksekTime cek = new CekEksekTime();
		cek.setStart();
		tes = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		db = new DBHandler(this);
		add = (Button)findViewById(R.id.button1);
		lv = (ListView)findViewById(R.id.listdata);
		datas = db.getAllData();
		adapter = new DBAdapter(this, datas);
		lv.setAdapter(adapter);
		add.setOnClickListener(this);
		lv.setOnItemClickListener(this);
		cek.setEnd();
		
		
		
	}

	@Override
	public void onItemClick(AdapterView<?> paramAdapterView, View paramView,
			int pos, long paramLong) {
		int i =1;
		i++;
		//db.deleteData(datas.get(pos));
		db.updateData(datas.get(pos).get_id(),""+i,"newdata"+i);
		datas.clear();
		datas.addAll(db.getAllData());
		adapter.notifyDataSetChanged();
		
		
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.button1){
			db.addData(new Data(SystemClock.currentThreadTimeMillis()+" =", "dua",tes));
			datas.clear();
			datas.addAll(db.getAllData());
			adapter.notifyDataSetChanged();
		}
		
	}

	

}
