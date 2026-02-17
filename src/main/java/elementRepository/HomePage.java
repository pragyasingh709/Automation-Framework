package elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	//Constructor
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(linkText="Contacts")
	private WebElement contactsLink;
		
	@FindBy(linkText="Organizations")
	private WebElement organizationLink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement administrator;
	
	@FindBy(linkText="Sign Out")
	private WebElement logout;

	public WebElement getContactsLink() {
		return contactsLink;
	}

	public WebElement getOrganizationLink() {
		return organizationLink;
	}

	public WebElement getAdministrator() {
		return administrator;
	}

	public WebElement getLogout() {
		return logout;
	}
	
	
}