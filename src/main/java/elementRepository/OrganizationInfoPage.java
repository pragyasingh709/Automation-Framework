package elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {

	//Constructor
		public OrganizationInfoPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
			
		}
		
		@FindBy(xpath="//span[@class='dvHeaderText']")
		private WebElement OrganizationInfo;

		public WebElement getOrganizationInfo() {
			return OrganizationInfo;
		}
}