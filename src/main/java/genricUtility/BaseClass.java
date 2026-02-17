package genricUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import elementRepository.HomePage;
import elementRepository.LoginPage;

public class BaseClass {
	PropertyFileUtility putil = new PropertyFileUtility(); 
	WebDriverUtility wutil = new WebDriverUtility();
	public WebDriver driver;
	public static WebDriver sdriver; //Listners
	
	@BeforeSuite(groups={"smoke","regression"})
	public void beforeSuitConfig() {
		System.out.println("...DataBase connection estabhlished...");
	
	}
	//@Parameters("browser")
	//@BeforeTest
	@BeforeClass(groups={"smoke","regression"}) //Launch
	public void beforeClassConfig(/*String BROWSER*/) throws IOException {
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String URL = putil.toReadDataFromPropertyFile("url");
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		}else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		}else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		sdriver=driver; // Listeners
		
		System.out.println("Browser got launched");
		wutil.toMaximize(driver);
		System.out.println("Browser got maximize");
		wutil.waitForElement(driver);
		driver.get(URL);
		
	}
	@BeforeMethod(groups={"smoke","regression"})// Login
	public void beforeMethodConfig() throws IOException {
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		LoginPage lp = new LoginPage(driver);
		lp.getUserTextfield().sendKeys(USERNAME);
		lp.getPasswordTextfield().sendKeys(PASSWORD);
		lp.getLoginButton().click();
		System.out.println("Browser got loged in to vtiger");
		
	}
	@AfterMethod(groups={"smoke","regression"})
	public void afterMethodConfic() {
	HomePage hp =new HomePage(driver);
	wutil.toMouseHover(driver, hp.getAdministrator());
	hp.getLogout().click();
	System.out.println("Sucessfully logged out from vtiger");
	
}
	@AfterClass(groups={"smoke","regression"})
	public void afterClassConfig() {
		System.out.println("Browser got clossed sucessfuly");
		driver.quit();
		
	}
	
	@AfterSuite(groups="smoke")
	public void afterSuitConfig() {
		System.out.println("...DataBase connection disconected...");
	}
}
