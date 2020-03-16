package com.amazon.pageactions;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.amazon.helper.ScreenCaptureUtility;
import com.amazonflipkart.utils.BrowserUtilties;
import com.flipkart.pageobjects.FlipkartHomePageObjects;

public class FlipkartSearchActions extends FlipkartHomePageObjects{
	static final Logger logger = Logger.getLogger(FlipkartSearchActions.class);
	BrowserUtilties browserUtility = new BrowserUtilties();

	
	public void loadURL(String url, WebDriver driver)
			throws Exception {
		driver.get(url);
		browserUtility.idealwait();
	}
	/**
	 * @author tausif.sayed.basha
	 * @param item
	 * @param driver
	 * @throws Exception
	 */
	public void searchForItem(String item, WebDriver driver)
			throws Exception {

		try {
			logger.info("Checking for the Search Bar of Flipkart Home Page");
			Reporter
			.log("Checking for the Search Bar of Flipkart Home Page");

			
			//Wait for page to load
			browserUtility.waitForPageToLoad(popUpCloseButton, driver);
			
			//Click on popup close button
			browserUtility.clickElement(popUpCloseButton,driver);
			
			//Wait for search bar to load
			browserUtility.waitForPageToLoad(searchTextBox, driver);

			//Enter the value
			browserUtility.enterValueTextBox(searchTextBox,item,driver);

			//Click on search icon
			browserUtility.clickElement(searchIcon,driver);

			logger.info("Clicked on search icon with required text");
			Reporter
			.log("Clicked on search icon with required text");

			ScreenCaptureUtility.setScreenshotAll(driver, "Search for an Item",
					"searchForItem");
		} catch (Exception e) {
		}

	}
	
	/**
	 * @author tausif.sayed.basha
	 * @param item
	 * @param url
	 * @param driver
	 * @return
	 * @throws Exception
	 */
	public String returnPrice(WebDriver driver)
			throws Exception {
		
		//Here customized logic can be used , but for now used the page's xpath from the page objects
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].innerHTML",iPhonePrice);
        
	}

}
