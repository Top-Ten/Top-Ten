package top.ten;

import com.google.api.client.util.Key;
public class Place {
	@Key
	public String id;

	@Key
	public String name;

	@Key
	public String reference;
	
	@Key
	public float rating;
	
	@Key
	public Geometry geometry;
	
	public static class Geometry
	{
		@Key
		public Location location;
	}
	
	public static class Location
	{
		@Key
		public float lat;
		@Key
		public float lng;
	}
	@Override
	public String toString() {
		return name + " - " + id + " - " + reference + " - " + rating + " - " + geometry.location.lat + " - " + geometry.location.lng;
	}
}