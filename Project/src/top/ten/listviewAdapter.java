package top.ten;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

public class listviewAdapter extends ArrayAdapter<String>
{
	private final Context context;
	private final String[] values;
	private final float[] ratings;
	public listviewAdapter(Context context, String[] value, float[] ratings) {
		super(context, R.layout.row, value);
		this.ratings = ratings;
		this.context = context;
		this.values = value;
		// TODO Auto-generated constructor stub
	}
	@Override
	public View getView(int pos, View conView, ViewGroup parent)
	{
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.row, parent, false);
		TextView text = (TextView)rowView.findViewById(R.id.name);
		ProgressBar prog = (ProgressBar)rowView.findViewById(R.id.progressBar1);
		text.setText(values[pos]);
		prog.setProgress((int)(ratings[pos]*20));
		return rowView;
	}
	
}
