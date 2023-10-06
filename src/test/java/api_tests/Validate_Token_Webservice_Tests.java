package api_tests;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.TestDataReader;

public class Validate_Token_Webservice_Tests {
	String baseurl = "http://crater.primetech-apps.com/api";
	Response response;
	String token;
	
	@Test
	public void login() {
		String endpoint = "/v1/auth/login";

		String usermail = TestDataReader.getProperty("email");
		String userpassword = TestDataReader.getProperty("password");

		JSONObject requestBody = new JSONObject(); 
		requestBody.put("username", usermail);
		requestBody.put("password", userpassword);
		requestBody.put("device_name", "mobile_app");
		
		response = RestAssured
			.given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.header("Company","1")
				.body(requestBody.toString())
			.when()
				.post(baseurl + endpoint);

		token = response.path("token").toString();
	}

	@Test (dependsOnMethods = "login")
	public void tokenHappypath() {
		String endpoint = "/v1/auth/check";
		
		response = RestAssured
			.given()
				.header("Company","1")
				.header("Authorization", "Bearer " + token)
			.when()
				.get(baseurl + endpoint);
		
		response
			.then()
				.statusCode(200)
				.contentType(ContentType.HTML);
		
		Assert.assertEquals(response.asString(), "1");
	}
	
	@Test
	public void incorrectAuthorization() {
		String endpoint = "/v1/auth/check";
		
		response = RestAssured
			.given()
				.header("Company","1")
				.header("Authorization", "Bearer invalidtoken")
				.accept(ContentType.JSON)
				
			.when()
				.get(baseurl + endpoint);
		
		response
			.then()
				.statusCode(401)
				.contentType(ContentType.JSON);
		
		Assert.assertEquals(response.path("message").toString(), "Unauthenticated.");
	}
	
	@Test (dependsOnMethods = "login")
	public void incorrectEndpoint() {
		String endpoint = "/v1/auth/check/incorrectendpoint";
		
		response = RestAssured
			.given()
				.header("Company","1")
				.header("Authorization", "Bearer " + token)
			.when()
				.get(baseurl + endpoint);
		
		response
			.then()
				.statusCode(404);
	}
}