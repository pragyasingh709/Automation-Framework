package organizationTest;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import elementRepository.CreateOrganizationPage;
import elementRepository.HomePage;
import elementRepository.OrganizationInfoPage;
import elementRepository.OrganizationPage;
import genricUtility.BaseClass;
import genricUtility.ExcelFileUtility;

public class ToCreateOrgWithIndustryTest extends BaseClass {
@Test(groups="regression")	
public void toCreateOrgWithIndustry_003() throws EncryptedDocumentException, IOException {
			HomePage hp=new HomePage(driver);
			hp.getOrganizationLink().click();
			Random rf = new Random();
			int accno = rf.nextInt(1000);
			OrganizationPage op = new OrganizationPage(driver);
			op.getOrganizationicon().click();
			ExcelFileUtility eutil = new ExcelFileUtility();
			String ORGANIZATIONNAME = eutil.toReadDataFromExcelFile("organiszation", 1, 2);
			CreateOrganizationPage cop = new CreateOrganizationPage(driver);
			cop.getOrganzationnameTextfield().sendKeys(ORGANIZATIONNAME+accno);
			cop.getIndustryDropdown().sendKeys("Chemicals");
			cop.getSavebutton().click();
			OrganizationInfoPage oip = new OrganizationInfoPage(driver);
			String organiszation = oip.getOrganizationInfo().getText();
			Assert.assertTrue(organiszation.contains(ORGANIZATIONNAME));
	     }
	}