package top.ten;

import java.io.IOException;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

import android.location.Address;
import android.location.Geocoder;

import android.location.Location;  
import android.location.LocationListener;  
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import android.util.Log;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class map extends MapActivity implements LocationListener{
    /** Called when the activity is first created. */
	
	MapController myControl;
	GeoPoint myGeo;
	MapView myMap;
	Overlay myOverlay;
	private MyLocationOverlay myLocation;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        
        Geocoder geocoder = new Geocoder(this);
       
        myMap = (MapView) findViewById(R.id.mapView);
        myMap.displayZoomControls(true);
        myMap.setBuiltInZoomControls(true);
        
        //Get passed information from activity calls
        Bundle extras = getIntent().getExtras();
        //assign extra values to variables
        int latitude = (int)(extras.getFloat("latit")*1000000);
        int longitude = (int)(extras.getFloat("longit")*1000000);
        
        
        final List<Overlay> mapOverlays = myMap.getOverlays();  
        Drawable drawable = this.getResources().getDrawable(R.drawable.androidmarker);  
        final HItemizedOverlay itemizedOverlay = new HItemizedOverlay(drawable, this);  
        /*
        //Get lat longs from given location or address and apply them to new geopoint
        try {
        	//gives string with name of location or address (called "test" atm) to the geocoder to get lat longs
            List<Address> addresses = geocoder.getFromLocationName(
                extras.getString("test"), 5);
            if (addresses.size() > 0) {
            	int lat = (int)(addresses.get(0).getLatitude()*1000000);
                int lng = (int)(addresses.get(0).getLongitude()*1000000);
                myGeo = new GeoPoint(lat,lng);
            }    
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        myGeo = new GeoPoint(latitude, longitude);
        //puts geopoint on map with added dialogue popup on click
        OverlayItem mypoint = new OverlayItem(myGeo, "Test", "This is a test point"); 
        
        itemizedOverlay.addOverlay(mypoint);  
        mapOverlays.add(itemizedOverlay);  
        
        //moves map view to geo location and sets zoom level
        myControl = myMap.getController();
        myControl.animateTo(myGeo);
        myControl.setZoom(15);
        
        //sets gps point if a ping is received
        myLocation = new MyLocationOverlay(this, myMap);
        myMap.getOverlays().add(myLocation);
        myLocation.enableMyLocation();
        myLocation.runOnFirstFix(new Runnable(){
        	public void run() {
               
        		}
        	});
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLocationChanged(Location location) {
		String lat = String.valueOf(location.getLatitude());
		 
        String lon = String.valueOf(location.getLongitude());
 
        Log.e("GPS", "location changed: lat="+lat+", lon="+lon);
	}

	@Override
	public void onProviderDisabled(String provider) {
		Log.e("GPS", "provider disabled " + provider);
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		Log.e("GPS", "provider enabled " + provider);
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		Log.e("GPS", "status changed to " + provider + " [" + status + "]");
		
	}
}