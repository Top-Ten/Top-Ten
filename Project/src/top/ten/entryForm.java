package top.ten;

//import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class entryForm extends Activity 
{
	EditText zipCode;
	EditText cityState;
	Button accept;
	Button useCurrent;
	public boolean checkOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if(netInfo != null && netInfo.isConnectedOrConnecting()){
			return true;
		}
		return false;
	}
	
	public OnClickListener textListener = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			if(v==accept)
			{
				if(checkOnline()){
					Bundle newBundle = new Bundle();
					newBundle.clear();
					if(zipCode.getText().length()==5)
					{
						String theZip = zipCode.getText().toString();
						newBundle.putString("zipcode" , theZip);
					}
					else if(zipCode.getText().length()!=0)
					{
						//show error msg for not right amount of numbers in zip
					}
					else if(cityState.getText().length()!=0)
					{
						String theCity = cityState.getText().toString();
						newBundle.putString("zipcode" , theCity);
					}
					
					if(newBundle.isEmpty())
					{
						//error message
					}
					else
					{
						Intent anewIntent = new Intent(v.getContext(), catagories.class);
						anewIntent.putExtras(newBundle);
						startActivityForResult(anewIntent, 0);
					}
					
				}
				else
				{
					Toast.makeText(getApplicationContext(), "You are not connected to the internet.", Toast.LENGTH_LONG).show();
				}
			}
			else if(v==useCurrent)
			{
				if(checkOnline())
				{
					//check the gps/area function of the phone
					Toast.makeText(getApplicationContext(), "You are not connected to the internet.", Toast.LENGTH_LONG).show();
				}
			}
		}
	};
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);
        zipCode = (EditText)findViewById(R.id.editText1);
        cityState = (EditText)findViewById(R.id.autoCompleteTextView1);
        accept = (Button)findViewById(R.id.entryDone);
        accept.setOnClickListener(textListener);
        useCurrent = (Button)findViewById(R.id.current);
        useCurrent.setOnClickListener(textListener);
    }
	
	
	
	
	
}
