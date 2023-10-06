package tests;

import java.time.Duration;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import utils.BrowserUtils;
import utils.Driver;
import utils.TestDataReader;
import pages.DashboardPage;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

public class LoginManagement_steps {

	BrowserUtils utils = new BrowserUtils();
	LoginPage loginpage = new LoginPage();
	DashboardPage dashboard = new DashboardPage();
	SoftAssert softAssert = new SoftAssert();

	String error_message_span = "span[contains(@class,'text-red-500')]";
	
//valid credential login test - start
	@Given("As a user, I am on the login page")
	public void as_a_user_i_am_on_the_login_page() {
//		Driver.getDriver().get(TestDataReader.getProperty("appurl"));
//		Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//		Driver.getDriver().manage().window().maximize();
		utils.waitForElementToBeVisible(loginpage.login_page_email_box);
	}

	@When("I enter valid usermail {string} and valid password {string}")
	public void i_enter_valid_usermail_and_valid_password(String usermail, String password) {
		utils.sendkeysWithActionsClass(loginpage.login_page_email_box, TestDataReader.getProperty("email"));
		utils.sendkeysWithActionsClass(loginpage.login_page_password_box, TestDataReader.getProperty("password"));
	}

	@When("I click on login button")
	public void i_click_on_login_button() throws InterruptedException {
		loginpage.login_page_login_btn.click();
	}

	@Then("I should see the {string}")
	public void i_should_see_the(String message) {

		utils.waitForElementToBeVisible(loginpage.login_page_popup_titlemessage);
		
		assertEquals(loginpage.login_page_popup_titlemessage.getText(), "Success!");
		assertEquals(loginpage.login_page_popup_bodymessage.getText(), message);
	}

	@Then("I should be directed to the Dashboard page.")
	public void i_should_be_directed_to_the_dashboard_page() {
		utils.waitUntilElementVisibleWithLocator(By.xpath("//span[text()='Amount Due']"));
		assertTrue(dashboard.dashboard_tab.isDisplayed());
	}
	// valid credential login test - end

	// invalid email login attempt - start
	@When("I enter invalid usermail {string} and password {string}")
	public void i_enter_invalid_usermail_and_password(String usermail, String password) {
		utils.sendkeysWithActionsClass(loginpage.login_page_email_box, usermail);
		utils.sendkeysWithActionsClass(loginpage.login_page_password_box, password);
	}

	@Then("I should see the usermail error message {string} if it exist")
	public void i_should_see_the_usermail_error_message_if_it_exist(String usermailerror) {
		List<WebElement> email_error_messages = loginpage.login_page_email_errormessage_block.findElements(By.xpath(error_message_span));
		boolean iserrorfound = email_error_messages.size() > 0;
		boolean iserrorexpected = !usermailerror.isEmpty();
		assertEquals(iserrorfound, iserrorexpected);
		
		if (iserrorfound) {
			assertEquals(email_error_messages.get(0).getText(), usermailerror);
		} 
	}

	@Then("I should see the password error message {string} if it exist")
	public void i_should_see_the_password_error_message_if_it_exist(String passworderror) {
		List<WebElement> password_error_messages = loginpage.login_page_password_errormessage_block.findElements(By.xpath(error_message_span));
		boolean iserrorfound = password_error_messages.size() > 0;
		boolean iserrorexpected = !passworderror.isEmpty();
		assertEquals(iserrorfound, iserrorexpected);
		
		if (iserrorfound) {
			assertEquals(password_error_messages.get(0).getText(), passworderror);
		} 
	}

	@Then("I should see the popup error message {string} if it exist")
	public void i_should_see_the_popup_error_message_if_it_exist(String popuperror) {
		boolean iserrorfound = loginpage.login_page_popup_messages.size() > 0;
		boolean iserrorexpected = !popuperror.isEmpty();
//		assertEquals(iserrorfound, iserrorexpected);
		
		softAssert.assertEquals(iserrorfound,iserrorexpected,"show popup error message");
		
		if (iserrorfound) {
			assertEquals(loginpage.login_page_popup_titlemessage.getText(), "Error");
			assertEquals(loginpage.login_page_popup_bodymessage.getText(), popuperror);
		}
	}
	
	@Then("I should not be logged in")
	public void i_should_not_be_logged_in() {
		Assert.assertTrue(loginpage.login_page_login_btn.isDisplayed());
	}
}
