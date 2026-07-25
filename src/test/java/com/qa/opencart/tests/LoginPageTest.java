package com.qa.opencart.tests;

import java.util.concurrent.TimeoutException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import static com.qa.opencart.constants.AppConstants.HOME_PAGE_TITLE;
import static com.qa.opencart.constants.AppConstants.LOGIN_PAGE_FRACTION_URL;
import static com.qa.opencart.constants.AppConstants.LOGIN_PAGE_TITLE;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

//@Listeners({ExtentReportListener.class, AnnotationTransformer.class})
@Feature("F 50: Open Cart - Login Feature")
@Epic("Epic 100: design pages for open cart application")
@Story("US 101: implement login page for open cart application")
public class LoginPageTest extends BaseTest{

	@Description("checking open cart login page title......")
	@Severity(SeverityLevel.MINOR)
	@Owner("Dibyansu")
    @Test
    public void loginPageTitleTest() throws TimeoutException{
		//System.out.println("-----Starting the TCs-----");
        String actTitle = loginPage.getLoginPageTitle();
		ChainTestListener.log("Checking LoginPage Title" + actTitle);
        Assert.assertEquals(actTitle, LOGIN_PAGE_TITLE);
		//System.out.println("-----Ending the TCs-----");
    } 

	@Description("checking open cart login page url...")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Dibyansu")
    @Test
	public void loginPageURLTest() throws TimeoutException {
		String actURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(LOGIN_PAGE_FRACTION_URL));   
	}

	@Description("checking open cart login page has forgot pwd link...")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("Dibyansu")
    @Test
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}

	@Description("check user is able to login with valid user credentials...")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("Dibyansu")
    @Test(priority=Short.MAX_VALUE)
	public void doLoginTest() throws TimeoutException {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccPageTitle(), HOME_PAGE_TITLE); //act_username = sahu@xyz.com //act_password = Chiku
	}

	@Test(enabled = false, description = "WIP -- forgot pwd check")
	public void forgotPwd() {
		System.out.println("forgot pwd ");
	}

}
