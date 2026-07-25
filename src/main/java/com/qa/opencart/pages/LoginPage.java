package com.qa.opencart.pages;

import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.qa.opencart.constants.AppConstants.DEFAULT_TIMEOUT;
import static com.qa.opencart.constants.AppConstants.LOGIN_PAGE_FRACTION_URL;
import static com.qa.opencart.constants.AppConstants.LOGIN_PAGE_TITLE;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

    private WebDriver driver; //123
    private ElementUtil eleUtil;

    // 1. private By locators: OR
	private final By email = By.id("input-email");
	private final By password = By.id("input-password");
	private final By loginBtn = By.xpath("//input[@value='Login']");
	private final By forgotPwdLink = By.linkText("Forgotten Password");
	private final By registerLink = By.linkText("Register");

    // 2. public page constr...
	public LoginPage(WebDriver driver) {
		this.driver = driver; // 123
		eleUtil = new ElementUtil(driver);
	}

    // 3. public page actions/methods
	@Step("getting login page title")
	public String getLoginPageTitle() throws TimeoutException {
		String title = eleUtil.waitFotTitleIs(LOGIN_PAGE_TITLE, DEFAULT_TIMEOUT);
		System.out.println("login page title: " + title);
		return title;
	}

	@Step("getting login page url")
    public String getLoginPageURL() throws TimeoutException {
		String url = eleUtil.waitForURLContains(LOGIN_PAGE_FRACTION_URL, DEFAULT_TIMEOUT); 
		System.out.println("login page url: " + url);
		return url;
	}

	@Step("checking forgot pwd link exist")
    public boolean isForgotPwdLinkExist() {
		return eleUtil.isElementDisplayed(forgotPwdLink);
	}

	@Step("login with valid username: {0} and password: {1}")
    public AccountsPage doLogin(String username, String pwd) throws TimeoutException {

		System.out.println("user credentials: " + username + ":" + pwd);
        eleUtil.waitForElementVisible(email, DEFAULT_TIMEOUT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		// driver.findElement(password).sendKeys(pwd);
		// driver.findElement(email).sendKeys(username);
		// driver.findElement(loginBtn).click();
		// String title = eleUtil.waitFotTitleIs(HOME_PAGE_TITLE, DEFAULT_TIMEOUT);
		// System.out.println("Accounts page title:" + title);
		return new AccountsPage(driver);
    }

	@Step("navigating to the registeration page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.clickWhenReady(registerLink, DEFAULT_TIMEOUT);
		return new RegisterPage(driver);
	}
}
