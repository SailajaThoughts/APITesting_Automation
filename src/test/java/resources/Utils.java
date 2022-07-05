package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public  class Utils {
	//Here this variable insntance is not created again and again 
	//It retains the details of first testcase
	//if we dont put  static new instance is created and it is null
	public static RequestSpecification req;
	public RequestSpecification requestSpecification() throws IOException {
		
		//here we are avoiding running multiple times as this code is common
		//to all  scenarios
		//So, once it went inside it will no more be null. So just return 
		//whatever is present
		//By just adding addFilter we can log everything
		if(req==null) {
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
		
		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
				.addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return req;
		}
		return req;
	}
	public static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\nsail\\REST_Assured\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
		
	}
	
	public String getJsonPath(Response response,String key) {
		String resp=response.asString();
		JsonPath js=new JsonPath(resp);
		return js.get(key).toString();
		
	}

	
}
