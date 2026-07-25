package com.qa.opencart.tests;

import java.util.List;
import java.util.concurrent.TimeoutException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import static com.qa.opencart.constants.AppConstants.HOME_PAGE_FRACTION_URL;
import static com.qa.opencart.constants.AppConstants.HOME_PAGE_TITLE;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Feature("F 60: Open Cart - Login Feature")
@Epic("Epic 200: design pages for open cart application")
@Story("US 201: implement Accounts page for open cart application")
public class AccountsPageTest extends BaseTest {

    //BT --> BC
    @BeforeClass
    public void accPageSetup() throws TimeoutException{
        accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    @Description("checking open cart Acc page title...")
	@Severity(SeverityLevel.MINOR)
	@Owner("Dibyansu")
    @Test
    public void accPageTitleTest() throws TimeoutException {
		Assert.assertEquals(accPage.getAccPageTitle(), HOME_PAGE_TITLE) ;
	}

    @Description("checking open cart acc page url ...")
	@Severity(SeverityLevel.MINOR)
	@Owner("Dibyansu")
    @Test
	public void accPageURLTest() throws TimeoutException {
		Assert.assertTrue(accPage.getAccPageURL().contains(HOME_PAGE_FRACTION_URL));
	}

    @Description("checking open cart acc page headers...")
	@Severity(SeverityLevel.MINOR)
	@Owner("Dibyansu")
    @Test
	public void accPageHeadersTest() {
		List<String> actHeaderList = accPage.getAccPageHeaders();
		Assert.assertEquals(actHeaderList, AppConstants.expectedAccPageHeadersList);
	}
}
