package top.ten;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class previousActivity extends ListActivity {
    String list[];
    float lat[];
    float longi[];
    ArrayAdapter<String> adapter;
    ListView listView;
    @Override
	public void onListItemClick(ListView l, View v, int pos, long id)
	{
		Intent newIntent = new Intent(v.getContext(), map.class);
		newIntent.putExtra("latit", lat[pos]);
		newIntent.putExtra("longit", longi[pos]);
		newIntent.putExtra("name", list[pos]);
		startActivityForResult(newIntent, 0);
	}
    
    @Override
	public void onCreate(Bundle savedInstanceState) 
	    {
		  super.onCreate(savedInstanceState);
	      setContentView(R.layout.list);
	      listView = getListView();
	      list = new String[]{"","","","","","","","","",""};
	      lat = new float[10];
	      longi = new float[10];
	      adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
	    	        list);
	      listView.setAdapter(adapter);
	      readText();
	    }
	
	 public void addplace(String name, String address, float lat, float lon, float rating)
	    {
	    	try{
	    	BufferedWriter br = new BufferedWriter(new OutputStreamWriter(openFileOutput("dummytext", this.MODE_APPEND)));
	    	String str;
	    	String[] list = new String[10];
	    	
	    		br.write(name);
	    		br.write("\n");
	    		br.write(Float.toString(lat));
	    		br.write("\n");
	    		br.write(Float.toString(lon));
	    		br.write("\n");

	    		
	    			
	       	
	    		
	    		br.close();
	    		
	    		   	
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	readText();
	    }
	    private String readText(){
	    	try{
	    	BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("dummytext")));
	    	
	    	
	    	String str;
	    	
	    	
	    	
	    		int i =0;
	    		while((str = br.readLine())!=null){
	    			list[i]=str;
	    			lat[i] = new Float(br.readLine());    				
	    			longi[i] = new Float(br.readLine());
	    			i++;
	       		}
	    		
	    		br.close();
	    		return str;
	    		
	    	}
	    	catch (IOException e){
	    		
	    	}
	    	((BaseAdapter)listView.getAdapter()).notifyDataSetChanged();
	    	return null;
	    }
	}



