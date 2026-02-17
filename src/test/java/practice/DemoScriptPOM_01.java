package practice;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import elementRepository.ContactInfoPage;
import elementRepository.ContactPage;
import elementRepository.CreateContactPage;
import elementRepository.HomePage;
import elementRepository.LoginPage;
import genricUtility.ExcelFileUtility;
import genricUtility.PropertyFileUtility;
import genricUtility.WebDriverUtility;

public class DemoScriptPOM_01 {

	public static void main(String[] args) throws IOException {
		
		
		PropertyFileUtility putil = new PropertyFileUtility(); 
		ExcelFileUtility eutil = new ExcelFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();
		
		//To Read Data From PropertyFile 
		
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String URL = putil.toReadDataFromPropertyFile("url");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		
		//To Read data from excel file
		
		String LASTNAME = eutil.toReadDataFromExcelFile("contacts", 1, 2);
		
		//Test Script
				//Launch browser
				WebDriver driver=null;
				if(BROWSER.equals("chrome")) {
					driver = new ChromeDriver();
				}else if (BROWSER.equals("edge")) {
					driver = new EdgeDriver();
				}else if (BROWSER.equals("firefox")) {
					driver = new FirefoxDriver();
				}
				wutil.toMaximize(driver);
				wutil.waitForElement(driver);
				
				//Step 2 : Login to application with valid credential
				driver.get(URL);
				LoginPage lp = new LoginPage(driver);
				lp.getUserTextfield().sendKeys(USERNAME);
				lp.getPasswordTextfield().sendKeys(PASSWORD);
				lp.getLoginButton().click();
				
				//Step 3 : Navigate to contacts link

				HomePage hp=new HomePage(driver);
				hp.getContactsLink().click();
				
				//Step 4 : click on create contacts look up image
				ContactPage cp = new ContactPage(driver);
				cp.getcreatcontactIcon().click();
				
				//Step 5 : create contact with mandatory feilds
				CreateContactPage ccp = new CreateContactPage(driver);
				ccp.getLastnameTextfield().sendKeys(LASTNAME);;
				
				//Step 6 : Save and Verify
				ccp.getsaveButton().click();
				ContactInfoPage cip = new ContactInfoPage(driver);
			    String lastname = cip.getContactInfo().getText();
			    if(lastname.contains(LASTNAME)) {
                 System.out.println(lastname+"....Passed");
			    }else {
			    	System.out.println(lastname+"....Failed");
                		 
			    }
			  //Step 7 : Logout Of Application
			   wutil.toMouseHover(driver, hp.getAdministrator());
			   hp.getLogout();
			   
			 //Step 8 : Close Browser
			   driver.quit();
				

	}

}
