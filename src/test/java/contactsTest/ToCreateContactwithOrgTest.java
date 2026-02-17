package contactsTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import elementRepository.ContactInfoPage;
import elementRepository.ContactPage;
import elementRepository.CreateContactPage;
import elementRepository.HomePage;
import genricUtility.BaseClass;
import genricUtility.ExcelFileUtility;
import genricUtility.WebDriverUtility;

public class ToCreateContactwithOrgTest extends BaseClass{
	@Test(groups="smoke")
	public void toCreateContactwithOrg_005() throws EncryptedDocumentException, IOException {
		HomePage hp=new HomePage(driver);
		hp.getContactsLink().click();
		ContactPage cp = new ContactPage(driver);
		cp.getcreatcontactIcon().click();
		ExcelFileUtility eutil = new ExcelFileUtility();
		String LASTNAME = eutil.toReadDataFromExcelFile("contacts", 1, 2);
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.getLastnameTextfield().sendKeys(LASTNAME);;
		String ORGANIZATIONNAME = eutil.toReadDataFromExcelFile("organiszation", 1, 2);
		WebDriverUtility wutil = new WebDriverUtility();
		ccp.getContactOrg().click();
		String parentWindow = driver.getWindowHandle();
        wutil.toSwitchWindow(driver, parentWindow) ; 
        ccp.getNewWinOrg().click();;
        driver.switchTo().window(parentWindow);
		ccp.getsaveButton().click();
		ContactInfoPage cip = new ContactInfoPage(driver);
	    String lastname = cip.getContactInfo().getText();
	    Assert.assertTrue(lastname.contains(LASTNAME));
	  }
}