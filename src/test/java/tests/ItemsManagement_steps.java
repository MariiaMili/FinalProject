package tests;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import java.time.Duration;
import org.apache.commons.lang3.RandomStringUtils;
import pages.DashboardPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import utils.BrowserUtils;
import pages.LoginPage;
import pages.ItemsPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.Driver;
import java.util.List;
import utils.TestDataReader;

public class ItemsManagement_steps {

	BrowserUtils utils = new BrowserUtils();
	LoginPage loginpage = new LoginPage();
	ItemsPage item_page = new ItemsPage();
	DashboardPage dashboard = new DashboardPage();
	static String itemName;

	SoftAssert softAssert = new SoftAssert();

//	@Before
//	public void openWebDriverBeforeTest() {
//		Driver.getDriver().get(TestDataReader.getProperty("appurl"));
//		Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
//		Driver.getDriver().manage().window().maximize();
//	}
//	
//	@After()
//	public void closeWebDriverAfterTest() {
////		Driver.quitDriver();
//	}

	@Given("As an entity user, I am logged in")
	public void as_an_entity_user_i_am_logged_in() {

		utils.waitForElementToBeVisible(loginpage.login_page_newemail_box);

		utils.sendkeysWithActionsClass(loginpage.login_page_newemail_box, TestDataReader.getProperty("email"));
		utils.sendkeysWithActionsClass(loginpage.login_page_password_box, TestDataReader.getProperty("password"));
		loginpage.login_page_login_btn.click();
	}

	@Given("I navigate to Items tab")
	public void i_navigate_to_items_tab() {
		item_page.items_tab1.click();

		utils.waitForElementToBeVisible(item_page.items_page_item_headerText);
		Assert.assertTrue(item_page.items_page_item_headerText.isDisplayed());
	}

	@When("I click on +Add Item button")
	public void i_click_on_add_item_button() {
		item_page.items_page_addItem_btn.click();
	}

	@Then("I should be on the page with Name, Price and Unit dropdown")
	public void i_should_be_on_the_page_with_name_price_and_unit_dropdown() {
		utils.waitForElementToBeVisible(item_page.items_Input_page_newItem_text);
		assertTrue(item_page.items_Input_page_newItem_text.isDisplayed());
		assertTrue(item_page.items_input_page_name_box.isDisplayed());
		assertTrue(item_page.items_input_page_price_box.isDisplayed());
		assertTrue(item_page.items_input_page_unit_dropdown.isDisplayed());
	}

	@Then("I confirm that the unit dropdown has {int} options")
	public void i_confirm_that_the_unit_dropdown_has_options(Integer dropdownoptions) {
		item_page.items_input_page_unit_dropdown.click();

		utils.waitForElementToBeVisible(item_page.items_list_dropdown);
		List<WebElement> options = item_page.items_list_dropdown.findElements(By.tagName("li"));
		Integer actualNumbersOfoptions = options.size();
		Integer expectedNumberOfOptions = dropdownoptions;
		if (actualNumbersOfoptions.equals(expectedNumberOfOptions)) {
			System.out.println("Dropdown has the expected number of options: " + expectedNumberOfOptions);
		} else {
			System.out.println("Dropdown does not have the expected number of options. Actual: "
					+ actualNumbersOfoptions + ", Expected: " + expectedNumberOfOptions);
		}
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualNumbersOfoptions, expectedNumberOfOptions,
				"Dropdown does not have the expected number of options. Actual: " + actualNumbersOfoptions
						+ ", Expected: " + expectedNumberOfOptions);
	}

	@Then("I confirm that Description is displayed")
	public void i_confirm_that_description_is_displayed() {
		assertTrue(item_page.items_input_page_description.isDisplayed());
	}

	@When("I provide item name {string}, price {string}, unit {string}, and description {string}")
	public void i_provide_item_name_price_unit_and_description(String itemname, String itemprice, String unit,
			String itemdescription) {
		itemName = itemname + utils.randomNumber();
		item_page.items_input_page_name_box.sendKeys(itemName);
		item_page.items_input_page_price_box.sendKeys(itemprice);
		item_page.items_input_page_unit_dropdown.click();
		utils.waitForElementToBeVisible(item_page.items_input_page_unit_pc_option);
		item_page.items_input_page_unit_pc_option.click();

		item_page.items_input_page_description.sendKeys(itemdescription);
	}

	@When("I click Save Item button")
	public void i_click_save_item_button() {
		item_page.items_page_saveItem_btn.click();
	}

	@Then("I should see New Item is added to the Item list table")
	public void i_should_see_new_item_is_added_to_the_item_list_table() {
		utils.waitForElementToBeVisible(item_page.items_page_item_headerText);
		Assert.assertTrue(item_page.items_page_item_headerText.isDisplayed());
		utils.waitForElementToBeVisible(item_page.items_page_filter_btn);
		item_page.items_page_filter_btn.click();
		utils.waitForElementToBeVisible(item_page.items_page_filter_name_box);
		item_page.items_page_filter_name_box.sendKeys(itemName);
		utils.waitUntilElementVisibleWithLocator(By.xpath("//a[contains(text(), '" + itemName + "')]"));
		Assert.assertTrue(
				Driver.getDriver().findElement(By.xpath("//a[contains(text(), '" + itemName + "')]")).isDisplayed());
	}

	@Then("I delete the item")
	public void i_delete_the_item() throws InterruptedException {
		Thread.sleep(2000);
		utils.waitForElementToBeVisible(item_page.items_page_3dot_menu);
		item_page.items_page_3dot_menu.click();
		utils.waitForElementToBeVisible(item_page.items_page_3dot_delete_option);
		item_page.items_page_3dot_delete_option.click();
		utils.waitForElementToBeVisible(item_page.items_Input_delete_youSure_text);
		Assert.assertTrue(item_page.items_Input_delete_youSure_text.isDisplayed());
		item_page.items_page_delete_ok_btn.click();
	}

	// verify name field - start
	@When("I enter {int} characters Name")
	public void i_enter_characters_name(Integer length) {
		String itemname = RandomStringUtils.random(length, true, true);
		System.out.println(itemname);
		utils.sendkeysWithActionsClass(item_page.items_input_page_name_box, itemname);
	}

	@Then("I should see name {string} if it exist")
	public void i_should_see_name_if_it_exist(String errormsg) {
		boolean iserrorfound = item_page.items_name_error.size() > 0;
		boolean iserrorexpected = !errormsg.isEmpty();
		assertEquals(iserrorfound, iserrorexpected, "show error name");

		if (iserrorfound) {
			assertEquals(item_page.items_name_error.get(0).getText(), errormsg);
		}
	}

	// verify description field - start
	@When("I enter {int} characters Description")
	public void i_enter_characters_description(Integer length) {
		String itemdescription = RandomStringUtils.random(length, true, true);
		System.out.println(itemdescription);
		utils.sendkeysWithActionsClass(item_page.items_input_page_name_box, itemdescription);
	}

	@Then("I should see description {string} if it exist")
	public void i_should_see_description_if_it_exist(String errormsg) {
		boolean iserrorfound = item_page.items_description_error.size() > 0;
		boolean iserrorexpected = !errormsg.isEmpty();
		assertEquals(iserrorfound, iserrorexpected, "show error name");

		if (iserrorfound) {
			assertEquals(item_page.items_name_error.get(0).getText(), errormsg);
		}

	}
}
