package api_tests;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.TestDataReader;

public class Logout_Webservice_Tests {
	String baseurl = "http://crater.primetech-apps.com/api";
	Response response;
	String token;
	
	@Test
	public void login() {
		String endpoint = "/v1/auth/login";

//		String payload = "{\"username\": \"entityadmin@primetechschool.com\",\n"
//				+ "\"password\": \"primetech@school\",\n"
//				+ "\"device_name\":\"mobile_app\"}";
		
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
	public void logout() {
		String endpoint = "/v1/auth/logout";
		
		response = RestAssured
			.given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.header("Company","1")
				.header("Authorization", "Bearer " + token)
			.when()
				.post(baseurl + endpoint);
		
		response
			.then()
				.statusCode(200)
				.contentType(ContentType.JSON);
		
		Assert.assertEquals(response.path("success").toString(), "true");
		response.prettyPrint();
	}
	@Test 
	public void incorrectlogout() {
		String endpoint = "/v1/auth/logout";
		
		response = RestAssured
			.given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.header("Company","1")
				.header("Authorization", "Bearer " + "invalidtoken")
			.when()
				.post(baseurl + endpoint);
		
		response
			.then()
				.statusCode(401)
				.contentType(ContentType.JSON);
		
		Assert.assertEquals(response.path("message").toString(), "Unauthenticated.");
		response.prettyPrint();
	}
	
	@Test
	public void incorrecendpointlogout() {
		String endpoint = "/v1/auth/logout/incorrect";
		
		response = RestAssured
			.given()
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.header("Company","1")
				.header("Authorization", "Bearer " + "invalidtoken")
			.when()
				.post(baseurl + endpoint);
		
		response
			.then()
				.statusCode(404)
				.contentType(ContentType.JSON);
	}
}
