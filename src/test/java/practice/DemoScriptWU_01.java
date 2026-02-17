package practice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genricUtility.ExcelFileUtility;
import genricUtility.PropertyFileUtility;
import genricUtility.WebDriverUtility;

public class DemoScriptWU_01 {

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
				
				
				driver.get(URL);
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
				
				
		//Step 3 : Navigate to contacts link
				driver.findElement(By.linkText("Contacts")).click();
				
		//Step 4 : click on create contacts look up image
				driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
				
		//Step 5 : create contact with mandatory feilds
				driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
				
		//Step 6 : Save and Verify
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		        String lastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(lastname.contains(LASTNAME)) {
				System.out.println(lastname+".....Passed");
				}else {
					System.out.println(lastname+".....Failed");
				}
		//Step 7 : Logout Of Application
				 WebElement logOutEle = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				wutil.toMouseHover(driver, logOutEle);
				WebElement logOut = driver.findElement(By.linkText("Sign Out"));
				
				//Step 8 : Close Browser
				driver.quit();
	}

}
