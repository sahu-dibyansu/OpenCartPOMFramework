package com.qa.opencart.base;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

import io.qameta.allure.Description;

//@Listeners(ChainTestListener.class)
public class BaseTest {

    //BaseTest is only for the precondition and postcondition
    WebDriver driver;
    protected Properties prop;
	
	DriverFactory df;

    protected LoginPage loginPage;
    protected AccountsPage accPage;
    protected SearchResultsPage searchResultsPage; 
    protected ProductInfoPage productInfoPage;
    protected RegisterPage registerPage;

     private static final Logger log = LogManager.getLogger(BaseTest.class);

    //Before all the testcases
    @Description("init the driver and properties")
    @Parameters({"browser", "browserversion", "testname"})
    @BeforeTest
    public void  setup(@Optional String browserName, @Optional String browserVersion, @Optional String testname){
        df = new DriverFactory();
        prop = df.initProp();

        //if browserName is passed from the .xml file
        if (browserName != null && !browserName.isBlank()) {
			prop.setProperty("browser", browserName);
            prop.setProperty("browserversion", browserVersion);
			prop.setProperty("testname", testname);

		}

        driver = df.initDriver(prop); //calling the initDriver(); method by passing the prop reference.
        loginPage = new LoginPage(driver); //123
    }

    // @BeforeMethod
    // public void beforeMethod(ITestContext  result){
    //     LogUtil.info("-----starting test cases-----" + result.getName());

    // }

    @AfterMethod //will be running after each @test method
	public void attachScreenshot(ITestResult result) {
		if(!result.isSuccess()) {//only for failure test cases -- true
            log.info("-----Screenshot is taken-----");
            ChainTestListener.embed(DriverFactory.getScreenshotFile(),"image/png");
		}
		
        // LogUtil.info("-----ending test cases-------");
		//ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");//for both pass and fail  TCs
	}
    
    @Description("closing the browser..")
    @AfterTest 
    public void tearDown(){

        if (driver != null) {
            driver.quit();
            log.info("-----Closing the browser------");
        }
    }

}
