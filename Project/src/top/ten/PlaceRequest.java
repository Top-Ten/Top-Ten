package top.ten;

import java.io.IOException;
import java.util.List;

import top.ten.PlacesList;
import android.app.Application;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.widget.Toast;

import com.google.api.client.googleapis.GoogleHeaders;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.http.json.JsonHttpParser;
import com.google.api.client.json.jackson.JacksonFactory;


public class PlaceRequest {

		// Create our transport.
		private static HttpTransport transport;

		// Fill in the API key you want to use.
		private static final String API_KEY = "AIzaSyCKhWIWxdr0c6rRDd8tE8f9F_REbgdV6Qc";
		private static final String LOG_KEY = "GGPlace";
		// The different Places API endpoints.
		private static final String PLACES_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/search/json?";
		//private static final String PLACES_AUTOCOMPLETE_URL = "https://maps.googleapis.com/maps/api/place/autocomplete/json?";
		//private static final String PLACES_DETAILS_URL = "https://maps.googleapis.com/maps/api/place/details/json?";

		//private static final boolean PRINT_AS_STRING = true;

		// Moscone Center, Howard Street, San Francisco, CA, United States
		double latitude = 33.199568;
		double longitude = -97.140094;

		@SuppressWarnings("deprecation")
		public PlacesList performSearch(String name, String add,Context c) throws Exception {
		try {
				Geocoder geocoder= new Geocoder(c);
			 	List<Address> addresses = geocoder.getFromLocationName(add, 1);
		            if (addresses.size() > 0) {
		            	latitude =addresses.get(0).getLatitude();
		                longitude =addresses.get(0).getLongitude();
		            }    
				//Log.v(LOG_KEY, "Start Search");
				GenericUrl reqUrl = new GenericUrl(PLACES_SEARCH_URL);
				reqUrl.put("key", API_KEY);
				reqUrl.put("location", latitude + "," + longitude);
				reqUrl.put("radius", 5000);
				reqUrl.put("keyword", name);
				reqUrl.put("rankby", "prominence");
				reqUrl.put("sensor", "false");
				
				//Log.v(LOG_KEY, "url= " + reqUrl);
				transport = new ApacheHttpTransport();
				HttpRequestFactory httpRequestFactory = createRequestFactory(transport);
				HttpRequest request = httpRequestFactory.buildGetRequest(reqUrl);





					//Log.v(LOG_KEY, request.execute().parseAsString());
					
					PlacesList places = request.execute().parseAs(PlacesList.class);
					//Log.v(LOG_KEY, "STATUS = " + places.status);
					for (Place place : places.results) {
						Log.v(LOG_KEY, place.name);

					}
					return places;

		} catch (HttpResponseException e) {
				throw e;
		}
		catch (IOException e) {
				// TODO: handle exception
				throw e;
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public static HttpRequestFactory createRequestFactory(final HttpTransport transport) {

			return transport.createRequestFactory(new HttpRequestInitializer() {
			public void initialize(HttpRequest request) {
			GoogleHeaders headers = new GoogleHeaders();
			headers.setApplicationName("Google-Places-DemoApp");
			request.setHeaders(headers);
			JsonHttpParser parser = new JsonHttpParser(new JacksonFactory()) ;

			//JsonHttpParser.builder(new JacksonFactory());
			//parser.jsonFactory = new JacksonFactory();
			request.addParser(parser);
			}
		});
	}
}	

