package top.ten;

import android.app.ListActivity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


public class catagories extends ListActivity
{
	Integer[] myImages;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listmain);
		ListView list = getListView();
		
		
		/*
		 Steps:
		 1.) pull from bundle given in previous activity
		 2.) create a string from current places and activity: ie. "Top 10 " + Activity + "in" + Location
		 3.) run string into json search in google places api
		 4.) pull location, address, gps, and ratings from the json for the top ten results
		 5.) put into list
		 6.) if selection is picked then send bundle with that places items to map activity
		 */
		
		
		
		myImages = new Integer[]{R.drawable.bars,R.drawable.food,R.drawable.historical, R.drawable.entertainment};
		Gallery myGallery = (Gallery)findViewById(R.id.gallery1);
		myGallery.setAdapter(new ImageAdapter(this));
		String listStrings[] = {"item1","item2","item3","item4","item5","item6","item7","item8","item9","item10"};
		list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listStrings));
	}
	public class ImageAdapter extends BaseAdapter
	{
		private Context con;
		int imageBackground;
		public ImageAdapter(Context c)
		{
			con = c;
			TypedArray myArray = obtainStyledAttributes(R.styleable.Gallery1);
			imageBackground = myArray.getResourceId(R.styleable.Gallery1_android_galleryItemBackground, 1 );
			myArray.recycle();
		}
		@Override
		public int getCount()
		{
			return myImages.length;
		}
		@Override
		public Object getItem(int arg0)
		{
			return arg0;
		}
		@Override
		public long getItemId(int arg0)
		{
			return arg0;
		}
		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2)
		{
			LinearLayout theGallery = new LinearLayout(getApplicationContext());
			theGallery.setOrientation(LinearLayout.VERTICAL);
			
			ImageView iView = new ImageView(con);
			iView.setImageResource(myImages[arg0]);
			iView.setScaleType(ImageView.ScaleType.FIT_XY);
			iView.setLayoutParams(new Gallery.LayoutParams(300,240));
			iView.setBackgroundColor(0xFF5BA2F1);
			
			TextView text = new TextView(con);
			String[] names = {"Bars","Food","Historical","Entertainment"};
			
			String thename = names[arg0];
			text.setText(thename);
			text.setGravity(Gravity.CENTER);
			text.setTextColor(0xFFb80000);
			text.setTextSize(20);
			text.setTypeface(null, Typeface.BOLD);
			theGallery.addView(iView);
			theGallery.addView(text);
			
			return theGallery;
			
		}
	}
	
}
