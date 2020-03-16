package com.amazon.pageactions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.amazon.helper.ScreenCaptureUtility;
import com.amazon.pageobjects.AmazonHomePageObjects;
import com.amazonflipkart.utils.BrowserUtilties;

public class AmazonSearchActions extends AmazonHomePageObjects {

	static final Logger logger = Logger.getLogger(AmazonSearchActions.class);
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
	 * Description: search for an item provided
	 */
	
	public void searchForItem(String item, WebDriver driver)
			throws Exception {

		try {
			logger.info("Checking for the Search Bar of Amazon Home Page");
			Reporter
			.log("Checking for the Search Bar of Amazon Home Page");

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
	 * Description - Return the price of the elemnt
	 */
	public String returnPrice(String item, String url ,WebDriver driver)
			throws Exception {

		String finalXpath = null;

		//Fetch the xpath having the href to the item
		WebElement requiredElement = driver.findElement(By.xpath("//a//span[contains(text(),"+"'"+item+"')]/parent::a"));
		
		//Concatenate the href with new Xpath to get customized xpath and then retrieve the price
		finalXpath = "//a[@href='"+requiredElement.getAttribute("href").toString().replaceAll(url, "/")+"']"+"//span[@class='a-price']//span[@class='a-offscreen']";
		
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].innerHTML",driver.findElement(By.xpath(finalXpath)));
        
	}


}
