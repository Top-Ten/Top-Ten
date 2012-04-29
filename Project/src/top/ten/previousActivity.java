package top.ten;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.ListActivity;
import android.os.Bundle;
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
    String address[];
    ArrayAdapter<String> adapter;
    ListView listView;
    @Override
	public void onCreate(Bundle savedInstanceState) 
	    {
		  super.onCreate(savedInstanceState);
	      setContentView(R.layout.list);
	      listView = getListView();
	      list = new String[]{"","","","","","","","","",""};
	      lat = new float[10];
	      longi = new float[10];
	      address = new String[10];
	      addplace("Whataburger", "1717 blah dr", (float)33.2, (float)33.3, (float)4.0);
	      addplace("JAck", "1717 blah dr", (float)33.2, (float)33.3, (float)4.0);
	      addplace("What", "1717 blah dr", (float)33.2, (float)33.3, (float)4.0);
	      addplace("place", "1717 blah dr", (float)33.2, (float)33.3, (float)4.0);
		  readText();
	      adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
	    	        list);
	      listView.setAdapter(adapter);
	        
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
	    		br.write(address);
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
	    			address[i] = br.readLine();
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



