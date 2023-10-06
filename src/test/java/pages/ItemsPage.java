package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class ItemsPage {
	public ItemsPage() {
		PageFactory.initElements(Driver.getDriver(), this);	
	}
	@FindBy (xpath = "//button[text()=' Add Item']")
    public WebElement items_page_addItem_btn;
    
    @FindBy (xpath = "//h3[text()='Items']")
    public WebElement items_page_item_headerText;
    
    @FindBy (xpath = "//a[text()=' Items']")
    public WebElement items_tab1;
    
    @FindBy (xpath = "//a[text()='Items']")
    public WebElement items_tab2;
    
    @FindBy (xpath = "//ol[contains(@class, 'flex flex-wrap')]")
    public WebElement items_home_items_tab;
    
    @FindBy (xpath = "/html/body/div/main/div/div/div[4]/div[2]/div/div/div/table")
    public WebElement items_table;
   
    
    @FindBy (tagName = "p")
    public WebElement items_page_showing_pagination;

    @FindBy (xpath = "//nav[@aria-label='Pagination']")
    public WebElement items_pagination;

    @FindBy (xpath = "/html/body/div/main/div/div/div[4]/div[2]/div/div/div/div[2]/div[2]/div[2]/nav/a[2]")
    public WebElement items_page_number_1;

   @FindBy (xpath = "/html/body/div/main/div/div/div[4]/div[2]/div/div/div/div[2]/div[2]/div[1]/p/span[1]")
    public WebElement items_page_number1_pagination;
   
   @FindBy (xpath = "//nav/a[1]")
   public WebElement items_pagination_back_arrow;
   
   @FindBy (xpath = "//nav/a[contains(@class,'bg-primary-50')]")
   public WebElement items_pagination_current_page;

   @FindBy (xpath = "//nav/a[last()-1]")
   public WebElement items_pagination_last_page_button;
   
   @FindBy (xpath = "//nav/a[last()]")
   public WebElement items_pagination_last_arrow;
   
   
   @FindBy (xpath = "/html/body/div/main/div/div/form/div/div/div/div[1]/div/div/input")
   public WebElement items_nameField;
   
   @FindBy (xpath = "//table/thead/tr/th[2]")
   public WebElement items_name_column;
   
   @FindBy (xpath = "//table/thead/tr/th[3]")
   public WebElement items_unit_column;
   
   @FindBy (xpath = "//table/thead/tr/th[4]")
   public WebElement items_price_column;
   
   @FindBy (xpath = "//table/thead/tr/th[5]")
   public WebElement items_added_on_column;
   
   @FindBy (xpath = "(//form/div/div/div/div)[1]//span[contains(@class,'block')]")
	public List<WebElement> items_name_error;
   
//   @FindBy (xpath = "//textarea[contains(@name,'description')]")
   @FindBy (xpath = "(//form/div/div/div/div)[4]//span[contains(@class,'block')]")
 	public List<WebElement> items_description_error;
   

   @FindBy (className = "bg-multiselect-caret")
   public WebElement items_unitField;

   @FindBy (className = "v-money3")
   public WebElement items_priceField;
   
   @FindBy (xpath = "//th[text()='Added On ']")
   public WebElement items_added_on;
   
   
    
    @FindBy (xpath = "//h3[text()='New Item']")
    public WebElement items_Input_page_newItem_text;
    
//    @FindBy (xpath = "//div[@class='relative rounded-md shadow-sm font-base']/input")
    @FindBy (xpath = "(//form//input)[1]")
    public WebElement items_input_page_name_box;
    
    @FindBy (xpath = "(//form//input)[2]")
    public WebElement items_input_page_price_box;
    

    @FindBy (xpath = "(//form//input)[3]")
    public WebElement items_input_page_unit_dropdown;
    
    @FindBy (xpath = "//form//textarea")
    public WebElement items_input_page_description;
    
//    @FindBy (xpath = "//div[text()='Description ']")
//    public WebElement items_description_text;
    
    
//    @FindBy (className = "transition-transform")
//    public WebElement items_unit_dropdown;
    
    @FindBy (xpath = "(//ul[contains(@class, 'list-none')])[2]")
    public WebElement items_list_dropdown;
    
    @FindBy (xpath = "//span[text()='pc']")
    public WebElement items_input_page_unit_pc_option;
    
    @FindBy (xpath = "//button[text()=' Save Item']")
    public WebElement items_page_saveItem_btn;
    
    @FindBy (xpath= "//p[contains(text(), 'Success!')]")
    public WebElement items_page_item_created_popup;
  
    @FindBy (xpath = "//button[text()='Filter ']")
    public WebElement items_page_filter_btn;
    
    @FindBy (xpath = "//input[@name='name']")
    public WebElement items_page_filter_name_box;
    
    @FindBy (xpath = "(//button[contains(@id, 'headlessui-menu-button')])[3]")
    public WebElement items_page_3dot_menu;
    
    @FindBy (xpath = "//a[text()=' Edit']")
    public WebElement items_page_3dot_edit_option;
    
    @FindBy (xpath = "//a[text()=' Delete']")
    public WebElement items_page_3dot_delete_option;
    
    @FindBy (xpath = "//h3[text()='Are you sure?']")
    public WebElement items_Input_delete_youSure_text;
    
    @FindBy (xpath = "//button[text()='Ok']")
    public WebElement items_page_delete_ok_btn;
    
    @FindBy (xpath = "//button[text()='Cancel']")
    public WebElement items_page_delete_cancel_btn;
    
    @FindBy (xpath = "//span[text()='No Results Found']")
    public WebElement items_Input_noResultFound_text;	
    
    @FindBy (xpath = "//h3[text()='Edit Item']")
    public WebElement items_page_edit_item_headerText;
    
    @FindBy (xpath = "//button[text()=' Update Item']")
    public WebElement items_page_update_item_btn;
   
    
}
