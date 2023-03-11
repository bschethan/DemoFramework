/**
 * 
 */
package amazonTest;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import OR.HomePage;
import OR.ProductLandingPage;
import commonActions.Constants;
import commonActions.UIActions;
import commonActions.UIActionsImp;

/**
 * @author sureng
 *
 */
public class MyTest {
	
	 public UIActions uiActions = new UIActionsImp(); 
	 public WebDriver driver;
	 public String resultFolder;
	 public ExtentReports report;
	 public ExtentTest test;
	 
	
	@BeforeClass
	public void testSetup() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(Constants.PROPLocation));
			resultFolder =uiActions.createResultFolder(Constants.results);
			report = uiActions.ExtentReport(resultFolder);
			test = uiActions.ExtentTest(report, "AmazonTest");
			driver = uiActions.driverInitilization(driver,prop.getProperty("Browser"));
			driver.get(prop.getProperty("ApplicationURL"));
			test.log(LogStatus.PASS, "Driver launched and Navigated to "+prop.getProperty("ApplicationURL"));
			test.log(LogStatus.PASS, test.addScreenCapture(uiActions.Screenshot(driver, resultFolder)));
		} catch (Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.toString());
		}
	}
	
	@Test
	public void Homepage_NavigationActions() {
		try {
		uiActions.click(driver, HomePage.lbl_all);
		test.log(LogStatus.INFO, "Clicked ALL link in Homepage");
		uiActions.wait(driver,HomePage.lnk_tv_electronics, Constants.timeout);
		uiActions.click(driver, HomePage.lnk_tv_electronics);
		test.log(LogStatus.INFO, "Clicked TV,Electronics link under shop by department");
		uiActions.wait(driver,HomePage.lnk_television, Constants.timeout);
		uiActions.click(driver, HomePage.lnk_television);
		test.log(LogStatus.INFO, "Clicked Television under TV,Electronics");
		test.log(LogStatus.PASS, "Clicked on Television");
		test.log(LogStatus.PASS, test.addScreenCapture(uiActions.Screenshot(driver, resultFolder)));
		}catch(Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.toString());
		}
	}
	@Test(dependsOnMethods = "Homepage_NavigationActions")
	public void ProductLandingPage() {
		try {
			uiActions.scrolltoElement(driver, ProductLandingPage.txt_samsung);
			uiActions.wait(driver,ProductLandingPage.txt_samsung, Constants.timeout);
			uiActions.click(driver, ProductLandingPage.txt_samsung);
			test.log(LogStatus.PASS, "Clicked on Samsung Checkbox");
			test.log(LogStatus.PASS, test.addScreenCapture(uiActions.Screenshot(driver, resultFolder)));
			uiActions.selectList(driver, ProductLandingPage.lst_sortby, Constants.high2low);
			uiActions.wait(driver,ProductLandingPage.tbl_searchresult, Constants.timeout);
			List<WebElement> serchList = uiActions.getLocator_List(driver, ProductLandingPage.tbl_searchresult);
			serchList.get(1).click();
			String parentWindow = uiActions.windowSwitch(driver);
			test.log(LogStatus.PASS, "Clicked on Second largest samsung television");
			test.log(LogStatus.PASS, test.addScreenCapture(uiActions.Screenshot(driver, resultFolder)));
			uiActions.scrolltoElement(driver, ProductLandingPage.lbl_abouthisItem);
			assertEquals(uiActions.getLocator(driver, ProductLandingPage.lbl_abouthisItem).getText().trim(), "About this item");
			driver.close();
			driver.switchTo().window(parentWindow);
		}catch(Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.toString());
		}
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			test.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
			String screenshotPath = uiActions.Screenshot(driver,resultFolder);
			test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath));
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}
	}
	@AfterClass
	public void teardonw() {
		report.endTest(test);
		report.flush();
		driver.quit();
	}

}

