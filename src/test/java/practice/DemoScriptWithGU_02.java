package practice;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import genricUtility.ExcelFileUtility;
import genricUtility.PropertyFileUtility;

public class DemoScriptWithGU_02 {

	public static void main(String[] args) throws IOException {
		
		//To Read Data From PropertyFile 
		
				PropertyFileUtility putil = new PropertyFileUtility(); 
				String BROWSER = putil.toReadDataFromPropertyFile("browser");
				String URL = putil.toReadDataFromPropertyFile("url");
				String USERNAME = putil.toReadDataFromPropertyFile("username");
				String PASSWORD = putil.toReadDataFromPropertyFile("password");
				
				//To Read data from excel file
				
				ExcelFileUtility eutil = new ExcelFileUtility();
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
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
				
				driver.get(URL);
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
				
				//Step 3 : Navigate to contacts link
				driver.findElement(By.linkText("Organizations")).click();
				Random rf = new Random();
				int accno = rf.nextInt(1000);
		//Step 4 : click on create contacts look up image
				driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
				
		//Step 5 : create contact with mandatory feilds
				driver.findElement(By.name("accountname")).sendKeys(ORGANIZATIONNAME+accno);
				
		//Step 6 : Save and Verify
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		        String accountname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(accountname.contains(ORGANIZATIONNAME+accno)) {
				System.out.println(accountname+".....Passed");
				}else {
					System.out.println(accountname+".....Failed");
				}
				//driver.switchTo().defaultContent();
		//Step 7 : Logout Of Application
				driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
				WebElement logOut = driver.findElement(By.linkText("Sign Out"));
				Actions action = new Actions(driver);
				action.moveToElement(logOut).perform();
				
		//Step 8 : Close Browser
				driver.quit();

	}

}
