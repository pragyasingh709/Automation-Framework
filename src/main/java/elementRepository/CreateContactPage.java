package elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPage {
	
	//Constructor
		public CreateContactPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
		@FindAll({@FindBy(name="lastname"),@FindBy(xpath="//input[@type='text']")})
		private WebElement lastnameTextfield;
		
		@FindBy(xpath="//input[@title='Save [Alt+S]']")
		private WebElement saveButton;
		
		@FindBy(xpath="//img[@alt='Select']")
		private WebElement contactOrg;
		
		@FindBy(linkText="RamRaj1")
		private WebElement newWinOrg;

		public WebElement getNewWinOrg() {
			return newWinOrg;
		}

		public void setNewWinOrg(WebElement newWinOrg) {
			this.newWinOrg = newWinOrg;
		}

		public WebElement getContactOrg() {
			return contactOrg;
		}

		public void setContactOrg(WebElement contactOrg) {
			this.contactOrg = contactOrg;
		}

		public WebElement getLastnameTextfield() {
			return lastnameTextfield;
		}

		public WebElement getsaveButton() {
			return saveButton;
		}

}
