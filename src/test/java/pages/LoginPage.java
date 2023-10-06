package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class LoginPage {
	public LoginPage() {
		PageFactory.initElements(Driver.getDriver(), this);

	}
	@FindBy (partialLinkText = "Payments")
	public WebElement payments_link;
	
	@FindBy (xpath = "//button[text()=' Add Payment']")
	public WebElement payments_addpayment_button;
	
	@FindBy (xpath = "//h3[text()='Payments']")
	public WebElement payments_payment_header;
	
//	@FindBy(xpath = "//p[text()='Copyright @ Crater Invoice, Inc. 2023']")
//	public WebElement login_page_footerText;

//	@FindBy (xpath = "//p[contains(text(), 'Copyright @')]")
//	public WebElement login_page_copyright_text;
	
//	@FindBy (xpath = "//p[@class][1]")
	@FindBy (xpath = "//p[@class='mb-3']")
	public WebElement login_page_copyright_text;

	@FindBy (xpath = "//div[text()='Email ']")
	public WebElement login_page_email_label;
	
	@FindBy (css = "input[name=email]")
	public WebElement login_page_email_box;
	
	@FindBy (xpath = "//input[@name='email']")
	public WebElement login_page_newemail_box;
	
	@FindBy (xpath = "//div[text()='Password ']")
	public WebElement login_page_password_label;
	
	@FindBy (css = "input[name=password]")
	public WebElement login_page_password_box;
	
	@FindBy (linkText = "Forgot Password?")
	public WebElement login_page_forgotPassword_link;
	
	@FindBy (xpath = "//button[text()='Login']")
	public WebElement login_page_login_btn;
	
	@FindBy (xpath = "//h1[contains(text(), 'Simple Invoicing')]")
	public WebElement login_page_simple_invoicing_text;
	
	@FindBy (xpath = "//p[contains(text(), 'Crater helps you')]")
	public WebElement login_page_craterHelpsYou_text;
	
	
	@FindBy (xpath = "//span[text()='Field is required']")
	public List<WebElement> login_page_fieldRequired_Messages;
	
	@FindBy (xpath = "//p[text()='These credentials do not match our records.']")
	public WebElement login_page_invalidLogin_message;
	
	@FindBy (xpath = "(//p[text()='These credentials do not match our records.'] | //span[text()='Field is required'])")
	public List<WebElement> login_page_all_error_messages;
	
	@FindBy (xpath="//div[contains(@class, 'fixed')]//p[1]")
	public WebElement login_page_popup_titlemessage;
	
	@FindBy (xpath="//div[contains(@class, 'fixed')]//p[2]")
	public WebElement login_page_popup_bodymessage;
	
	@FindBy (xpath="//div[contains(@class, 'fixed')]//p")
	public List<WebElement> login_page_popup_messages;
	
	@FindBy (xpath = "//form[@id='loginForm']/div[1]/div")
	public WebElement login_page_email_errormessage_block;
	
	@FindBy (xpath = "//form[@id='loginForm']/div[2]/div")
	public WebElement login_page_password_errormessage_block;
	

}
