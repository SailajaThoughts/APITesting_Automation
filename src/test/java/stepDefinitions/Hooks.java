package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{	
		
		StepDefinition m=new StepDefinition();
		if(StepDefinition.placeId==null) {
		m.add_place_payload_with("agar", "French", "Spain");
		m.user_calls_with_http_request("AddPlaceAPI", "POST");
		m.verify_that_place_id_created_maps_to_got_using("agar", "getPlaceAPI");
		}
	}
}
