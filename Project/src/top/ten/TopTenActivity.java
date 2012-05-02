package top.ten;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TopTenActivity extends Activity 
{
	
	Button prevButton;
    Button topButton;
    Button settingsButton;
    Button exitButton;
    
	public OnClickListener myListener = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			if(v==prevButton)
			{
				Intent newIntent = new Intent(v.getContext(), previousActivity.class);
				startActivity(newIntent);
			}
			if(v==topButton)
			{
				Intent newIntent = new Intent(v.getContext(), entryForm.class);
				startActivityForResult(newIntent, 0);
			}
			if(v==exitButton)
			{
				finish();
			}
			if(v==settingsButton)
			{
			}
		}
	};
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ImageView image = (ImageView) findViewById(R.id.icon);
        
        
        
        prevButton = (Button)findViewById(R.id.prevPlaces);
        topButton = (Button)findViewById(R.id.topTen);
        settingsButton = (Button)findViewById(R.id.mySettings);
        exitButton = (Button)findViewById(R.id.theExit);
        prevButton.setOnClickListener(myListener);
        topButton.setOnClickListener(myListener);
        settingsButton.setOnClickListener(myListener);
        exitButton.setOnClickListener(myListener); 
    }
}
