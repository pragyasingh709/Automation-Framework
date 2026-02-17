package practice;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import elementRepository.CreateOrganizationPage;
import elementRepository.HomePage;
import elementRepository.LoginPage;
import elementRepository.OrganizationInfoPage;
import elementRepository.OrganizationPage;
import genricUtility.ExcelFileUtility;
import genricUtility.PropertyFileUtility;
import genricUtility.WebDriverUtility;

public class DemoScriptPOM_02 {
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
		
		String ORGANIZATIONNAME = eutil.toReadDataFromExcelFile("organiszation", 1, 2);
		
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
				
				//Step 3 : Navigate to organization link

				HomePage hp=new HomePage(driver);
				hp.getOrganizationLink().click();
				Random rf = new Random();
				int accno = rf.nextInt(1000);
				
				//Step 4 : click on create organization look up image
				OrganizationPage op = new OrganizationPage(driver);
				op.getOrganizationicon().click();
				
				//Step 5 : create organization with mandatory feilds
				CreateOrganizationPage cop=new CreateOrganizationPage(driver);
				cop.getOrganzationnameTextfield().sendKeys(ORGANIZATIONNAME+accno);
				
				//Step 6 : Save and Verify
				cop.getSavebutton().click();
				OrganizationInfoPage oip = new OrganizationInfoPage(driver);
				String Organzationname = oip.getOrganizationInfo().getText();
				if(Organzationname.contains(ORGANIZATIONNAME+accno)) {
				System.out.println(Organzationname+".....Passed");
				}else {
					System.out.println(Organzationname+".....Failed");
				}
				 //Step 7 : Logout Of Application
				   wutil.toMouseHover(driver, hp.getAdministrator());
				   hp.getLogout();
				   
				 //Step 8 : Close Browser
				   driver.quit();
					

				
}
}