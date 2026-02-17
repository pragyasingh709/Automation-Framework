package practice;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Set;

public class DemoScriptTC_05 {

    public static void main(String[] args) {

    	//Step 1 : Launch Browser
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		
//Step 2 : Login to application with valid credential
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
		
		
//Step 3 : Navigate to contacts link
		driver.findElement(By.linkText("Contacts")).click();
		
//Step 4 : click on create contacts look up image
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
//Step 5 : create contact with mandatory feilds
		driver.findElement(By.name("lastname")).sendKeys("Raj");
// Step 6 :	select the organization from organization look up image	
		
		 driver.findElement(By.xpath("//img[@alt='Select']")).click();

// WINDOW HANDLING
	        String parentWindow = driver.getWindowHandle();
	        Set<String> allWindows = driver.getWindowHandles();

	        for (String window : allWindows) {
	            if (!window.equals(parentWindow)) {
	                driver.switchTo().window(window);
	                break;
	            }
	        }
// Select Organization
	        driver.findElement(By.linkText("RamRaj1")).click();

	        // Switch back to Parent window
	        driver.switchTo().window(parentWindow);
		
//Step 6 : Save and Verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
        String lastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(lastname.contains("Raj")) {
		System.out.println(lastname+".....Passed");
		}else {
			System.out.println(lastname+".....Failed");
		}
//Step 7 : Logout Of Application
		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		WebElement logOut = driver.findElement(By.linkText("Sign Out"));
		Actions action = new Actions(driver);
		action.moveToElement(logOut).perform();
		
//Step 8 : Close Browser
		driver.quit();
    }
}
