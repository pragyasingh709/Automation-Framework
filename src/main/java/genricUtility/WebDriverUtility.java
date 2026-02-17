package genricUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class consists of methods such as maximize
 * ,minimize,implicitWait,explicit wait handle dropdown , handle frames ,handle
 * mouse actions, to handle popups,to take screenshot and to switch between
 * windows
 * 
 */
public class WebDriverUtility {
	/**
	 * This method is used to maximize the webpage
	 * 
	 * @param driver
	 */
	public void toMaximize(WebDriver driver) {
		driver.manage().window().maximize();
	}

	/**
	 * This method is used to minimize the webpage
	 * 
	 * @param driver
	 */
	public void toMinimize(WebDriver driver) {
		driver.manage().window().minimize();
	}

	/**
	 * This method will wait until the element gets loaded to webpage(implicit wait)
	 * 
	 * @param driver
	 */
	public void waitForElement(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	/**
	 * This method will wait until the element gets loaded to webpage(explicit wait)
	 * 
	 * @param driver
	 * @param element
	 */
	public void waitForElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * This method is used to handle Dropdown using indx
	 * 
	 * @param element
	 * @param index
	 */
	public void toHandleDropdown(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	/**
	 * This method is used to handle Dropdown using value
	 * 
	 * @param element
	 * @param value
	 */
	public void toHandleDropdown(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	/**
	 * This method is used to handle Dropdown using visibletext
	 * 
	 * @param text
	 * @param element
	 */
	public void toHandleDropdown(String text, WebElement element) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	/**
	 * This method is used to handle frame using index
	 * 
	 * @param driver
	 * @param index
	 */
	public void toHandleFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	/**
	 * This method is used to handle frame using name or id
	 * 
	 * @param driver
	 * @param name_id
	 */
	public void toHandleFrame(WebDriver driver, String name_id) {
		driver.switchTo().frame(name_id);
	}

	/**
	 * This method is used to handle frame using WebElement
	 * 
	 * @param driver
	 * @param element
	 */
	public void toHandleFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}

	/**
	 * This method is used to Switch control from frame using defaultContent
	 * 
	 * @param driver
	 */
	public void toSwitchBackFromFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	/**
	 * This method is used to Perform Double click action
	 * 
	 * @param driver
	 * @param element
	 */
	public void toDubleClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}

	/**
	 * This method is used to Perform right click action
	 * 
	 * @param driver
	 * @param element
	 */
	public void toRightClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}

	/**
	 * This method is used to Perform MouseHover action
	 * 
	 * @param driver
	 * @param logOutEle
	 */
	public void toMouseHover(WebDriver driver, WebElement logOutEle) {
		Actions action = new Actions(driver);
		action.moveToElement(logOutEle).perform();
	}

	/**
	 * This method is used to Perform Drag and Drop provided driver and 2 elements
	 * 
	 * @param driver
	 * @param src
	 * @param target
	 */
	public void toDragandDrop(WebDriver driver, WebElement src, WebElement target) {
		Actions action = new Actions(driver);
		action.dragAndDrop(src, target).perform();
	}

	/**
	 * This method is used to Handle Popup By Accept
	 * 
	 * @param driver
	 */
	public void toHandleAlertPopupByAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	/**
	 * This method is used to Handle Popup By dismiss
	 * 
	 * @param driver
	 */
	public void toHandleAlertPopupByDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	/**
	 * This method is used to Handle Alert Popup And Accept it
	 * 
	 * @param driver
	 * @return
	 */
	public String toCaptureMessageInAlertPopupAndAccept(WebDriver driver) {
		Alert alertPopup = driver.switchTo().alert();
		String alertMessage = alertPopup.getText();
		alertPopup.accept();// ok button
		return alertMessage;
	}

	/**
	 * This method is used to perform scroll action provided driver
	 * 
	 * @param driver
	 */
	public void toScroll(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 500)");
	}

	/**
	 * This method is used to take screenshot provided driver and screenshotname
	 * 
	 * @param driver
	 * @param screenshotname
	 * @throws IOException
	 */
	public void toTakeScreenShot(WebDriver driver, String screenshotname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File src = new File("./errorShots/" + screenshotname + ".png");
		FileHandler.copy(temp, src);
	}
	
     public void toSwitchWindow(WebDriver drive , String partialtitle) {
	
	//Step 1 : Capture allIds
	Set<String> allIds =drive.getWindowHandles();
	//Step 2 : Traverse to every window and capture title
	for(String id : allIds) {
	 @Nullable
	String title = drive.switchTo().window(id).getTitle();
	 // Step 3: compare title captured with partialtitla given
	 if(title.contains(partialtitle)) {
		 break;
	   }
     }
  }
}