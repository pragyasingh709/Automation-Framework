package elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {

	//Constructor
	public OrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//img[@alt='Create Organization...']")
	private WebElement Organizationicon;
	
	public WebElement getOrganizationicon() {
		return Organizationicon;
	}
	public void setOrganizationicon(WebElement organizationicon) {
		Organizationicon = organizationicon;
	}
}
