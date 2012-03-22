package top.ten;

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
