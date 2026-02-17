package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoScriptTC_01 {

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
				driver.findElement(By.name("lastname")).sendKeys("Richa");
				
	//Step 6 : Save and Verify
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		        String lastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(lastname.contains("Richa")) {
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
