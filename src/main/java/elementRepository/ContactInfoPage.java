package elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	
	//Constructor
			public ContactInfoPage(WebDriver driver) {
				PageFactory.initElements(driver, this);
			}
			
			@FindBy(xpath="//span[@class='dvHeaderText']")
			private WebElement contactInfo;

			public void setContactInfo(WebElement contactInfo) {
				this.contactInfo = contactInfo;
			}

			public WebElement getContactInfo() {
				return contactInfo;
			}
}
