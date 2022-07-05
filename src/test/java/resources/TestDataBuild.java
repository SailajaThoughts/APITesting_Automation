package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Location;
import pojo.SetPlace;

public class TestDataBuild {

	public SetPlace addPlacePayLoad(String name,String language,String address) {
		SetPlace p = new SetPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setName(name);
		p.setPhone_number("(+51) 344 567 7767");
		p.setWebsite("https:rahulshettyacademy.com");
		List<String> t = new ArrayList<String>();
		t.add("shoe park");
		t.add("park");
		p.setTypes(t);
		Location l = new Location();
		l.setLat(-78.9546);
		l.setLng(67.90);
		p.setLocation(l);
		return p;

	}
	public String deletePlacePayLoad(String placeId) 
	{
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}

}
