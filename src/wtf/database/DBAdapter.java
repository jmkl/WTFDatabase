package wtf.database;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DBAdapter extends BaseAdapter{
	private List<Data> data;
	private Context context;
public DBAdapter(Context c,List<Data> data){
	this.data=data;
	this.context=c;
}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int pos) {
		// TODO Auto-generated method stub
		return data.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		// TODO Auto-generated method stub
		return pos;
	}

	@Override
	public View getView(int pos, View conV, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v= conV;
		if(v==null){
			v = LayoutInflater.from(context).inflate(R.layout.data, null);
		}
		ImageView iv = (ImageView) v.findViewById(R.id.imageView1);
		TextView tv1 =(TextView)v.findViewById(R.id.textView1);
		TextView tv2 =(TextView)v.findViewById(R.id.textView2);
		Data d = data.get(pos);
		tv1.setText(d.getNama());
		tv2.setText(d.getSerialnumber());
		
		return v;
	}

}
