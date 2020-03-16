package com.amazon.helper;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserInstantiator {

	private static final Logger logger = Logger.getLogger(BrowserInstantiator.class);

	/**
	 * Used webdriver manager to download the driver for chrome
	 * @return
	 */
	public static WebDriver returnWebDriver() {
		WebDriver driver= null;
		try {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			logger.info("ChromeDriver Invoked");
			driver.manage().window().maximize();
			logger.info("Browser maximised");
		} catch (Exception e) {
			logger.error("Exception in getWebDriver", e);
		}
		return driver;
	}
}
