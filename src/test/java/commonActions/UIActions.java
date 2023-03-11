/**
 * 
 */
package commonActions;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

/**
 * @author sureng
 *
 */
public interface UIActions {
	
	
	/**
	 * This Method will initialize the webdriver with respect to the String browser
	 * @param browser - represents a type of Browser
	 * @param driver - Web Driver
	 */
	public WebDriver driverInitilization(WebDriver driver,String browser) ;
	
	
	/**
	 * This Method will return the webElement with respect to the locator.
	 * @param locator - is String parameter and the format for locator is locatorType|Property ("|" is a delimiter)
	 * @param driver - Web Driver
	 * @return
	 */
	public WebElement getLocator(WebDriver driver,String locator);
	
	/**
	 * This Method will return the List of webElement with respect to the locator.
	 * @param locator - is String parameter and the format for locator is locatorType|Property ("|" is a delimiter)
	 * @param driver - Web Driver
	 * @return
	 */
	public List<WebElement> getLocator_List(WebDriver driver,String locator);
	
	/**
	 * This Method will do the Web UI click operation with respect to the locator.
	 * @param locator is String parameter and the format for locator is locatorType|Property ("|" is a delimiter)
	 * @param driver - Web Driver
	 * @return
	 */
	public void click(WebDriver driver,String locator);
	
	
	/**
	 * This Method will help user to enter the text into Web UI with respect to the locator.
	 * @param locator is String parameter and the format for locator is locatorType|Property ("|" is a delimiter)
	 * @param value value which needs to be entered into the UI
	 * @param driver - Web Driver
	 * @return
	 */
	public void sendKeys(WebDriver driver,String locator,String value);
	
	/**
	 * This Method helps to wait the script until the certain conditions satisfies or until the timeout.
	 * @param locator locator is String parameter and the format for locator is locatorType|Property ("|" is a delimiter)
	 * @param timeout - this is Maximum time up to which script can wait for the element to load
	 * @param driver - Web Driver
	 * @return
	 */
	public void wait(WebDriver driver,String locator,long timeout);
	
	/**
	 * this Method will help us to take the screenshot and save the screen prints to the provided location 
	 * @param fileName - location to save the screen print 
	 * @param driver - Web Driver
	 * @return
	 */
	public String Screenshot(WebDriver driver,String fileName);
	
	/**
	 * THis method will load the property file 
	 * @param filePath - location of the property File
	 * @param driver - Web Driver 
	 * @return
	 */
	public void propertyLoad(String filePath);
	
	/**
	 * This Method will perform click operation using Action class
	 * @param locator locator is String parameter and the format for locator is locatorType|Property ("|" is a delimiter)
	 */
	public void clickByAction(WebDriver driver,String locator);
	
	/**
	 * This method will create a runtime folder for the screenshot and for the extent report 
	 * @param parentLocation - location of the result folder 
	 * @param driver - Web Driver 
	 * @return  the current result location to store the screen prints
	 */
	public String createResultFolder(String parentLocation);
	
	/**
	 * This method will create Extent report object
	 * @param ResutlLocation - Extent report location
	 * @return returns ExtentReports 
	 */
	public ExtentReports ExtentReport(String ResutlLocation);
	
	/**
	 * This Method will return the Test of extent report
	 * @param report- @see public ExtentReports ExtentReport(String ResutlLocation)
	 * @param testName - String testNanme
	 * @return
	 */
	public ExtentTest ExtentTest(ExtentReports report,String testName);
	
	/**
	 * This method is used to select the list box with a visible text.
	 * @param driver - Web Driver 
	 * @param locator locator is String parameter and the format for locator is locatorType|Property ("|" is a delimiter)
	 * @param value - Visible text need to be selected from drop down
	 */
	public void selectList(WebDriver driver,String locator,String value);
	
	/**
	 * THis method will help to switch latest window which is newly opened
	 * @param driver - WebDriver
	 */
	public String windowSwitch(WebDriver driver);
	
	/**
	 * This method will scroll to view the element
	 * @param driver webDriver
	 * @param locator locator is String parameter and the format for locator is locatorType|Property ("|" is a delimiter)
	 */
	public void scrolltoElement(WebDriver driver, String locator);

}
