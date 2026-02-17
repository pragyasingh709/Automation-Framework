package elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrganizationPage {
	
	//Constructor
		public CreateOrganizationPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}

	
	@FindBy(name="accountname")
	private WebElement organzationnameTextfield;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement savebutton;
	
	@FindBy(name="industry")
	private WebElement industryDropdown;
	
	@FindBy(name="accounttype")
	private WebElement accounttypeDropdown;

	public WebElement getAccounttypeDropdown() {
		return accounttypeDropdown;
	}

	public void setAccounttypeDropdown(WebElement accounttypeDropdown) {
		this.accounttypeDropdown = accounttypeDropdown;
	}

	public WebElement getIndustryDropdown() {
		return industryDropdown;
	}

	public WebElement getOrganzationnameTextfield() {
		return organzationnameTextfield;
	}

	public WebElement getSavebutton() {
		return savebutton;
	}
}
