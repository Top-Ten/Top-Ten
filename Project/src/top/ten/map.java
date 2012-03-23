package top.ten;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

//import android.content.Context;
import android.location.Location;  
import android.location.LocationListener;  
//import android.location.LocationManager;  
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import android.util.Log;
//import android.app.Activity;
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
        
    //    LocationManager myLocator = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
   //     myLocator.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,1,this);  
        
 //       Location location = myLocator.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        
        myMap = (MapView) findViewById(R.id.mapView);
        myMap.displayZoomControls(true);
        myMap.setBuiltInZoomControls(true);
        
        double latitude = 40.8;
        double longitude = -96.666;
        
        final List<Overlay> mapOverlays = myMap.getOverlays();  
        Drawable drawable = this.getResources().getDrawable(R.drawable.androidmarker);  
        final HItemizedOverlay itemizedOverlay = new HItemizedOverlay(drawable, this);  
        
        myGeo = new GeoPoint ((int)(latitude*1E6), (int)(longitude*1E6));
        OverlayItem mypoint = new OverlayItem(myGeo, "Test", "This is a test point"); 
        
        itemizedOverlay.addOverlay(mypoint);  
        mapOverlays.add(itemizedOverlay);  
        
        myControl = myMap.getController();
        myControl.animateTo(myGeo);
        myControl.setZoom(13);
        
        myLocation = new MyLocationOverlay(this, myMap);
        myMap.getOverlays().add(myLocation);
        myLocation.enableMyLocation();
        myLocation.runOnFirstFix(new Runnable(){
        	public void run() {
                OverlayItem mypoint2 = new OverlayItem(myLocation.getMyLocation(), "Here", "You are here...");
                itemizedOverlay.addOverlay(mypoint2);
                mapOverlays.add(itemizedOverlay);
        		//myControl.animateTo(myLocation.getMyLocation());
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