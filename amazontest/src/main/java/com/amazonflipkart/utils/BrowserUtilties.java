package com.amazonflipkart.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserUtilties {


	private static final long PAGELOAD_MAX_TIMEOUT = 15;

	public void idealwait() throws InterruptedException
	{
		Thread.sleep(8000);
	}	
	/**
	 * @author tausif.sayed.basha
	 * @param elementToClick
	 * @param driver
	 * @throws Exception
	 */
	public void waitForPageToLoad(WebElement elementToClick, WebDriver driver)
			throws Exception {
		elementToClick = new WebDriverWait(driver, PAGELOAD_MAX_TIMEOUT)
				.until(ExpectedConditions.visibilityOf(elementToClick));
	}

	/**
	 * @author tausif.sayed.basha
	 * @param textBox
	 * @param value
	 * @param driver
	 * @throws Exception
	 */
	public void enterValueTextBox(WebElement textBox, String value,WebDriver driver) throws Exception
	{
		waitForPageToLoad(textBox, driver);
		textBox.isEnabled();
		textBox.click();
		textBox.clear();
		textBox.sendKeys(value);
	}
	
	/**
	 * @author tausif.sayed.basha
	 * @param elementToClick
	 * @param driver
	 * @throws Exception
	 */
	public void clickElement(WebElement elementToClick, WebDriver driver) throws Exception
	{

		elementToClick.isEnabled();
		elementToClick.click();

	}
}
