package api_tests;


import java.util.HashMap;
import java.util.Map;
// static import of a class where the members are static
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.RestApiUtils;
import utils.TestDataReader;
import org.json.JSONObject;

public class Login_Webservice_Tests {
	
	String baseurl = "http://crater.primetech-apps.com/api";
	
	Response response;
	RestApiUtils apiutils;
	String token;
	int item_id;
	
	@Test
//	public void testcase1_happypath_webservice_call() {
	public void login() {
		String endpoint = "/v1/auth/login";
		String usermail = TestDataReader.getProperty("email");
		String userpassword = TestDataReader.getProperty("password");
		
		JSONObject requestBody = new JSONObject(); 
		requestBody.put("username", usermail);
		requestBody.put("password", userpassword);
		requestBody.put("device_name", "mobile_app");
		
//		put headers into map
		Map<String, Object> requestHeaders = new HashMap<>();
		requestHeaders.put("Content-Type", "application/json");
		requestHeaders.put("Accept", "application/json");
		requestHeaders.put("company", "1");
		
		response = 
			 given()
				.headers(requestHeaders)	
				.body(requestBody.toString())
			.when()
				.post(baseurl + endpoint);

		response
			.then().statusCode(200).contentType("application/json");
		
		//get the token and assign to string
		token = response.path("token");
		assertEquals(response.path("type").toString(), "Bearer");
//		Assert.assertFalse(response.path("token").toString().isEmpty());
	}
	
	@Test
	public void testcase2_sending_request_to_incorrect_endpoint() {
		String endpoint = "/v1/auth/login/incorrectendpoint";
		
		JSONObject requestBody = new JSONObject(); 
		requestBody.put("username", "entityadmin@primetechschool.com");
		requestBody.put("password", "primetech@school");
		requestBody.put("device_name", "mobile_app");
		
		response = 
			given()
				.body(requestBody.toString())
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.header("company", "1")
			.when()
				.post(baseurl + endpoint);
		
		response
			.then()
				.statusCode(404)
				.contentType(ContentType.JSON);
		
//		Assert.assertEquals(response.path("type").toString(), "Bearer");
//		Assert.assertFalse(response.path("token").toString().isEmpty());
	}
	
	@Test
	public void testcase3_submit_request_without_proper_request_body() {
		String endpoint = "/v1/auth/login";
		
		response = 
			 given()
				.accept("application/json")
				.contentType("application/json")
				.header("Company","1")
				.body("{}")
			.when()
				.post(baseurl + endpoint);
		
		response
			.then()
				.statusCode(422)
				.contentType("application/json");
	}
	
	@Test
	public void testcase4_submit_request_body_with_invalid_username_and_password() {
		//BUG(defect in AC) - we should use GET method instead of POST, to get 405 Status Code  
		String endpoint = "/v1/auth/login";
		
		JSONObject requestBody = new JSONObject(); 
		requestBody.put("username", "123");
		requestBody.put("password", "1");
		requestBody.put("device_name", "mobile_app");
		
		response = 
			given()
				.accept("application/json")
				.contentType("application/json")
				.header("Company","1")
				.body(requestBody.toString())
			.when()
				.post(baseurl + endpoint);
		
		response
			.then()
				.statusCode(405)
				.contentType("application/json");
	}
}
