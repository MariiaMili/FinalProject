package tests;

import io.cucumber.java.en.When;
import pages.ItemsPage;
import pages.LoginPage;
import io.cucumber.java.en.Then;
import utils.BrowserUtils;
import utils.Driver;
import utils.TestDataReader;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.*;
import static utils.Driver.driver;

public class UI_Components {
	BrowserUtils utils = new BrowserUtils();
	LoginPage loginpage = new LoginPage();
	ItemsPage item_page = new ItemsPage();
	
	String current_page_number = "1";

//Login UI -start
	@When("I view the page title")
	public void i_view_the_page_title() {
		String pageTitle = driver.getTitle();
		assertTrue(pageTitle.contains("Crater"));
	}

	@When("I view the email text box placed underneath the title")
	public void i_view_the_email_text_box_placed_underneath_the_title() {
		utils.waitForElementToBeVisible(loginpage.login_page_email_box);
		assertTrue(loginpage.login_page_email_box.isDisplayed());
	}

	@Then("I verify email text box has label {string}")
	public void i_verify_email_text_box_has_label(String email) {
		assertTrue(loginpage.login_page_email_label.isDisplayed());
	}

	@Then("I view the password Text box placed underneath the Email text box")
	public void i_view_the_password_text_box_placed_underneath_the_email_text_box() {
		assertTrue(loginpage.login_page_password_box.isDisplayed());
	}

	@Then("I verify ithe password Text box has label {string}")
	public void i_verify_ithe_password_text_box_has_label(String password) {
		assertTrue(loginpage.login_page_forgotPassword_link.isDisplayed());
	}

	@Then("I view the forgot password link placed underneath the password text box")
	public void i_view_the_forgot_password_link_placed_underneath_the_password_text_box() {
		assertEquals("Forgot Password?", loginpage.login_page_forgotPassword_link.getText());
	}

	@Then("I verify a link has title {string}")
	public void i_verify_a_link_has_title(String forgotpassword) {
		assertTrue(loginpage.login_page_login_btn.isDisplayed());
	}

	@Then("I view the Login button placed underneath the forgot password link")
	public void i_view_the_login_button_placed_underneath_the_forgot_password_link() {
		assertTrue(loginpage.login_page_login_btn.isDisplayed());
	}

	@Then("I verify a button is labeled {string}")
	public void i_verify_a_button_is_labeled(String login) {
		assertEquals(loginpage.login_page_login_btn.getText(), login);
	}

	@Then("I verify the footer has a text {string}.")
	public void i_verify_the_footer_has_a_text(String copyright) {
//		utils.waitForElementToBeVisible(loginpage.login_page_copyright_text);
		System.out.println(loginpage.login_page_copyright_text.getText());
		assertEquals(copyright,loginpage.login_page_copyright_text.getText());
	}

	@Then("I view the main Heading to the right of the page")
	public void i_view_the_main_heading_to_the_right_of_the_page() {
		assertTrue(loginpage.login_page_simple_invoicing_text.isDisplayed());
	}

	@Then("I see the main Heading located to the right with the text {string}")
	public void i_see_the_main_heading_located_to_the_right_with_the_text(String heading1) {
		assertEquals("Simple Invoicing for Individuals Small Businesses",
				loginpage.login_page_simple_invoicing_text.getText());
	}

	@Then("I view secondary Heading")
	public void i_view_secondary_heading() {
		assertTrue(loginpage.login_page_craterHelpsYou_text.isDisplayed());
	}

	@Then("I verify the second Heading has a text {string}")
	public void i_verify_the_second_heading_has_a_text(String heading2) {
		assertEquals("Crater helps you track expenses, record payments & generate beautiful invoices & estimates.",
				loginpage.login_page_craterHelpsYou_text.getText());
	}
	// Login UI -end

	// Items UI -start
	@Then("I view the menu navigation path as {string} placed under Items")
	public void i_view_the_menu_navigation_path_as_placed_under_items(String homeitems) {
		assertTrue(item_page.items_page_item_headerText.isDisplayed());
	}

	@Then("I view a secondary button titled {string} with a filter icon")
	public void i_view_a_secondary_button_titled_with_a_filter_icon(String filter) {
		utils.waitForElementToBeVisible(item_page.items_page_filter_btn);
		assertTrue(item_page.items_page_filter_btn.isDisplayed());
	}

	@Then("I should see a primary button titled {string}")
	public void i_should_see_a_primary_button_titled(String additem) {
		assertTrue(item_page.items_page_addItem_btn.isDisplayed());
	}

	@Then("I view a table with columns: {string},{string},{string},{string},{string}")
	public void i_view_a_table_with_columns(String allceckbox, String name, String unit, String price, String addedon) {
		utils.waitForElementToBeVisible(item_page.items_table);
		assertTrue(item_page.items_table.isDisplayed());
		assertEquals(name, item_page.items_name_column.getText());
		assertEquals(unit, item_page.items_unit_column.getText());
		assertEquals(price, item_page.items_price_column.getText());
		assertEquals(addedon, item_page.items_added_on_column.getText());
	}

	@Then("I should see a link icon showing three dots")
	public void i_should_see_a_link_icon_showing_three_dots() {
		assertTrue(item_page.items_page_3dot_menu.isDisplayed());
	}

	@When("I click on the three dots link")
	public void i_click_on_the_three_dots_link() {
		item_page.items_page_3dot_menu.click();
	}

	@Then("I should see options:{string} and {string}")
	public void i_should_see_options_and(String edit, String delete) {
		utils.waitForElementToBeVisible(item_page.items_page_3dot_edit_option);
		assertTrue(item_page.items_page_3dot_edit_option.isDisplayed());
		assertTrue(item_page.items_page_3dot_delete_option.isDisplayed());
	}

	@Then("I should see pagination text {string}")
	public void i_should_see_pagination_text(String numofresults) {
		assertTrue(item_page.items_page_showing_pagination.isDisplayed());
	}

	@Then("I should see pagination navigation with controls")
	public void i_should_see_pagination_navigation_with_controls() {
		assertTrue(item_page.items_pagination.isDisplayed());
	}

	@Then("I should see number indicating the page the user is currently on and the next upcoming page")
	public void i_should_see_number_indicating_the_page_the_user_is_currently_on_and_the_next_upcoming_page() {
		String currentPageNumber = item_page.items_pagination_current_page.getText();
		System.out.println("current page: " + currentPageNumber);
		assertEquals(current_page_number, currentPageNumber);
		//ADD assert of NEXT UPCOMING PAGE 2-3
	}

	@Then("I should see left arrow is disabled")
	public void i_should_see_left_arrow_is_disabled() {
		assertTrue(item_page.items_pagination_back_arrow.getAttribute("class").contains("disabled"));
	}

	@Then("I click on the last page button")
	public void i_click_on_the_last_page_button() {
		item_page.items_pagination_last_page_button.click();
	}

	@Then("I should see right arrow is disabled")
	public void i_should_see_right_arrow_is_disabled() {

		utils.waitForElementToBeVisible(item_page.items_pagination_last_arrow);
		assertTrue(item_page.items_pagination_last_arrow.getAttribute("class").contains("disabled"));
	}
}
