package top.ten;

import java.util.ArrayList;
import java.util.List;

import top.ten.Place;
import top.ten.PlaceRequest;
import top.ten.PlacesList;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


public class catagories extends ListActivity
{
	Integer[] myImages;
	String listStrings[];
	float[] rate;
	float[][] gps;
	ListView list;
	ArrayList<String> listofnames;
	ArrayAdapter<String> adapt;
	ArrayList<Bitmap> bitmaplist;
	EditText newtext;
	ImageAdapter myAdapter;
	Dialog dialog;
	boolean isNameMenu = false;
	@Override
	public void onListItemClick(ListView l, View v, int pos, long id)
	{
		Intent newIntent = new Intent(v.getContext(), map.class);
		newIntent.putExtra("latit", gps[pos][0]);
		newIntent.putExtra("longit", gps[pos][1]);
		newIntent.putExtra("name", listStrings[pos]);
		startActivityForResult(newIntent, 0);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater themenu = getMenuInflater();
		themenu.inflate(R.layout.menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
		case R.id.menu_add:{
			dialog = new Dialog(this);
			dialog.setContentView(R.layout.dialog);
			dialog.setTitle("Add a new Catagory");
			newtext = (EditText)dialog.findViewById(R.id.newcatentry);
			Button yes = (Button)dialog.findViewById(R.id.yesButton);
			yes.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					Bitmap bitmapImage;
					bitmapImage = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.add);
					bitmaplist.add(bitmapImage);
					listofnames.add(newtext.getText().toString());
					myAdapter.notifyDataSetChanged();
					dialog.dismiss();
				}
				
			});
			dialog.show();
			return true;
		}
		default:return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.listmain);
		list = getListView();
		gps = new float[10][2];
		listofnames = new ArrayList<String>();
		listofnames.add("Bars");
		listofnames.add("Entertainment");
		listofnames.add("Fast Food");
		listofnames.add("Historical");
		/*
		 Steps:
		 1.) pull from bundle given in previous activity
		 2.) create a string from current places and activity: ie. "Top 10 " + Activity + "in" + Location
		 3.) run string into json search in google places api
		 4.) pull location, address, gps, and ratings from the json for the top ten results
		 5.) put into list
		 6.) if selection is picked then send bundle with that places items to map activity
		 */
		
		
		/*
		 Adding new feature:
		 1.)Change ImageAdapter to be dynamic - DONE
		 2.)Change the name list to be external file
		 3.)Add new activity to create a new gallery item
		 		1.)new activity will take user input
		 		2.)Add new "gallery item" to the gallery of catagories
		 		
		 */

		myImages = new Integer[]{R.drawable.bars,R.drawable.food,R.drawable.historical, R.drawable.entertainment};
		Gallery myGallery = (Gallery)findViewById(R.id.gallery1);
		bitmaplist = new ArrayList<Bitmap>();
		
		Bitmap bitmapImage;
		bitmapImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.bars);
		bitmaplist.add(bitmapImage);
		bitmapImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.entertainment);
		bitmaplist.add(bitmapImage);
		bitmapImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.food);
		bitmaplist.add(bitmapImage);
		bitmapImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.historical);
		bitmaplist.add(bitmapImage);
		/*bitmapImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.add);
		temp.add(bitmapImage);*/
		
		myAdapter = new ImageAdapter(this, bitmaplist);
		
		
		myGallery.setAdapter(myAdapter);
		myGallery.setSelection(1);
		
		myGallery.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
				for(int i = 0;i<10;i++)
				{
					listStrings[i] = "";
				}
				((BaseAdapter)list.getAdapter()).notifyDataSetChanged();
				SearchSrv newSearch= new SearchSrv();
				Bundle b = getIntent().getExtras();
				
				newSearch.execute(listofnames.get(arg2), b.getString("zipcode"));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			} 
          
		});
		
		
		listStrings = new String[]{"","","","","","","","","",""};
		rate = new float[]{0,0,0,0,0,0,0,0,0,0};
		listviewAdapter adapt = new listviewAdapter(this, listStrings, rate);

		list.setAdapter(adapt);
		
	}
	public class ImageAdapter extends BaseAdapter
	{
		private Context con;
		int imageBackground;
		ArrayList<Bitmap> imageList;
		
		public ImageAdapter(Context c, ArrayList<Bitmap> l)
		{
			con = c;
			TypedArray myArray = obtainStyledAttributes(R.styleable.Gallery1);
			imageBackground = myArray.getResourceId(R.styleable.Gallery1_android_galleryItemBackground, 1 );
			myArray.recycle();
			imageList = l;
		}
		public void addItem(Bitmap newBitmap)
		{
			imageList.add(newBitmap);
		}
		@Override
		public int getCount()
		{
			return imageList.size();
		}
		@Override
		public Object getItem(int arg0)
		{
			return imageList.get(arg0);
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
			iView.setImageBitmap(imageList.get(arg0));
			//iView.setImageResource(myImages[arg0]);
			iView.setScaleType(ImageView.ScaleType.FIT_XY);
			iView.setLayoutParams(new Gallery.LayoutParams(300,240));
			iView.setBackgroundColor(0xFF5BA2F1);
			iView.setBackgroundResource(imageBackground);
			TextView text = new TextView(con);
			
			String thename = listofnames.get(arg0);
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
	
	
	
	private class SearchSrv extends AsyncTask<String, String, PlacesList>{
		@Override
		protected PlacesList doInBackground(String...strings) {
			// TODO Auto-generated method stub
			PlacesList pl = null;
			try {
					pl = new PlaceRequest().performSearch(strings[0],strings[1], getApplicationContext());
					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//Toast toast = Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG);
			}
			return pl;
		}
    
		@Override
		protected void onPostExecute(PlacesList result) {
			// TODO Auto-generated method stub
			String text = "Result \n";
			String listitems[] = {"","","","","","","","","","","","","","","","","","","",""};
			float ratings[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
			float gpstemp[][] = new float[20][2];
				if (result!=null){
					int i = 0;
					for(Place place: result.results){
						listitems[i] = place.name;
						ratings[i] = place.rating;
						gpstemp[i][0] = place.geometry.location.lat;
						gpstemp[i][1] = place.geometry.location.lng;
						i++;
					}
					boolean swapped=true;
					int j = 0;
					float temp;
					String temps;
					while(swapped)
					{
						swapped=false;
						for(i = 19;i>0+j;i--)
						{
							if(ratings[i] > ratings[i-1])
							{
								temp = ratings[i];
								ratings[i] = ratings[i-1];
								ratings[i-1] = temp;
								
								temps = listitems[i];
								listitems[i] = listitems[i-1];
								listitems[i-1] = temps;
								
								temp = gpstemp[i][0];
								gpstemp[i][0] = gpstemp[i-1][0];
								gpstemp[i-1][0] = temp;
								
								temp = gpstemp[i][1];
								gpstemp[i][1] = gpstemp[i-1][1];
								gpstemp[i-1][1] = temp;
								
								swapped=true;
							}
						}
						j++;
					}
					for(i=0;i<10;i++)
					{
						listStrings[i] = listitems[i];
						rate[i] = ratings[i];
						gps[i] = gpstemp[i];
					}
					((BaseAdapter)list.getAdapter()).notifyDataSetChanged();
					
				}
				setProgressBarIndeterminateVisibility(false);
     }
    }
	
}
