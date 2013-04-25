package wtf.database;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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
		tes = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		db = new DBHandler(this);
		add = (Button)findViewById(R.id.button1);
		lv = (ListView)findViewById(R.id.listdata);
		datas = db.getAllData();
		adapter = new DBAdapter(this, datas);
		lv.setAdapter(adapter);
		add.setOnClickListener(this);
		lv.setOnItemClickListener(this);
		
		
		
	}

	@Override
	public void onItemClick(AdapterView<?> paramAdapterView, View paramView,
			int pos, long paramLong) {
		db.deleteData(datas.get(pos));
		datas.clear();
		datas.addAll(db.getAllData());
		adapter.notifyDataSetChanged();
		
		
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.button1){
			db.addData(new Data("satu", "dua",tes));
			datas.clear();
			datas.addAll(db.getAllData());
			adapter.notifyDataSetChanged();
		}
		
	}

	

}
