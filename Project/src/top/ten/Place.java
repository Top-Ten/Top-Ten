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
	
	@Override
	public String toString() {
		return name + " - " + id + " - " + reference + " - " + rating;
	}
}