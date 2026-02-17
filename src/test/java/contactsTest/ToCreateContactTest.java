package contactsTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import elementRepository.ContactInfoPage;
import elementRepository.ContactPage;
import elementRepository.CreateContactPage;
import elementRepository.HomePage;
import genricUtility.BaseClass;
import genricUtility.ExcelFileUtility;


@Listeners(genricUtility.ListenersImplementation.class)
public class ToCreateContactTest extends BaseClass {
	@Test(groups="smoke")
	public void toCreateContac_001() throws EncryptedDocumentException, IOException {
	HomePage hp=new HomePage(driver);
	hp.getContactsLink().click();
	ContactPage cp = new ContactPage(driver);
	cp.getcreatcontactIcon().click();
	ExcelFileUtility eutil = new ExcelFileUtility();
	String LASTNAME = eutil.toReadDataFromExcelFile("contacts", 1, 2);
	CreateContactPage ccp = new CreateContactPage(driver);
	ccp.getLastnameTextfield().sendKeys(LASTNAME);;
	ccp.getsaveButton().click();
	//Assert.fail();//fail
	ContactInfoPage cip = new ContactInfoPage(driver);
    String lastname = cip.getContactInfo().getText();
   Assert.assertTrue(lastname.contains(LASTNAME));
    		 
    }	
  }