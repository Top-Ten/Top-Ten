package top.ten;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

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
				//creates intent to map class
				Intent newIntent = new Intent(v.getContext(), map.class);
				//creates string named extras to send to the map class
				newIntent.putExtra("latit", 30.0);
				newIntent.putExtra("longit", 50.0);
				newIntent.putExtra("test", "Louvre");
				startActivityForResult(newIntent, 0);
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
