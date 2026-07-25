package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.qa.opencart.constants.AppConstants.DEFAULT_TIMEOUT;
import static com.qa.opencart.constants.AppConstants.HOME_PAGE_FRACTION_URL;
import static com.qa.opencart.constants.AppConstants.HOME_PAGE_TITLE;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage {

    private WebDriver driver;
	private ElementUtil eleUtil;

	private static final Logger log = LogManager.getLogger(AccountsPage.class);

	private final By headers = By.cssSelector("div#content > h2");
	private final By search = By.name("search");
	private final By searchIcon = By.cssSelector("div#search button");
	

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}


	@Step("getting acc page title")
    public String getAccPageTitle() throws TimeoutException {
        
        String title = eleUtil.waitFotTitleIs(HOME_PAGE_TITLE, DEFAULT_TIMEOUT);
		return title;
    }

	@Step("getting acc page url")
	public String getAccPageURL() throws TimeoutException {
		String url = eleUtil.waitForURLContains(HOME_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		return url;
	}

	@Step("getting acc page headers")
	public List<String> getAccPageHeaders() {
		List<WebElement> headerList = eleUtil.getElements(headers);
		List<String> headerValList = new ArrayList<String>();
		for (WebElement e : headerList) {
			String text = e.getText();
			headerValList.add(text);
		}
		return headerValList;
	}

	@Step("perform search: {0}")
	public SearchResultsPage doSearch(String searchKey) {
		//System.out.println("search key: " + searchKey);
		log.info("search key: " + searchKey);

		eleUtil.doSendKeys(search, searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);
	}

}
