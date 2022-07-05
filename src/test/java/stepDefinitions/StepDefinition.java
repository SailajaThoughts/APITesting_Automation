package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Location;
import pojo.SetPlace;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {
	RequestSpecification res;
	ResponseSpecification responseSpec;
	Response response;
	static String placeId;

	TestDataBuild data=new TestDataBuild();
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		// Building request and creating an object for it.
		res = given().spec(requestSpecification()).body(data.addPlacePayLoad(name,language,address));
	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethod) {
	   
	
		//invoking constructor of APIREsource enum with value of 
		//resource i.e., addPLaceAPI
		//So, value of AddPlacEAPI will be extracted 
		//It will fall into constructor resource keyword
		//This resource needs to be returned
		System.out.println(resource);
		APIResources resourceAPI= APIResources.valueOf(resource);
		//resourceAPI.getResource();
		// creating a response specification object
		System.out.println(resourceAPI.getResource());
		responseSpec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		// Passing request and response specification object together
		if(httpMethod.equalsIgnoreCase("POST")) 
		response = res.when().post(resourceAPI.getResource());
		else if (httpMethod.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource());
		
	}
	@Then("then the API call is successful with status code {int}")
	public void then_the_api_call_is_successful_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(),200);		
		
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		
		assertEquals(getJsonPath(response,key),value);
	}
	
	@Then("verify that place_id created maps to {string} got using {string}")
	public void verify_that_place_id_created_maps_to_got_using(String expectedName, String resource) throws IOException {
	   //request spec 
		placeId=getJsonPath(response,"place_id");
		res = given().spec(requestSpecification())
				.queryParam("place_id",placeId);
		user_calls_with_http_request(resource,"GET");
		String actualName=getJsonPath(response,"name");
		assertEquals(actualName,expectedName);
		
	}
	//placeId is set to static becuase placeId needs to be used in
	//second scenario 
	@Given("DeletePlace payload")
	public void delete_place_payload() throws IOException {
		res = given().spec(requestSpecification()).body(data.deletePlacePayLoad(placeId));
		
	}

}
