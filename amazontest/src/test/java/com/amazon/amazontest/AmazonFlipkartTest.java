package com.amazon.amazontest;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.amazon.helper.BrowserInstantiator;
import com.amazon.pageactions.AmazonSearchActions;
import com.amazon.pageactions.FlipkartSearchActions;

public class AmazonFlipkartTest {

	public static WebDriver driver = null;
	static final Logger logger = Logger.getLogger(AmazonFlipkartTest.class);
	String amazonPrice = null;
	String flipkartPrice = null;
	String amazonFinalPrice = null;
	String flipkartFinalPrice = null;
	
	//Data provider for amazon
	@DataProvider(name = "TestDataAmazon")
	public Object[][] amazonProviderMethod() {
		return new Object[][] { { "iPhone XR (64GB) - Yellow" ,"https://www.amazon.in/"}};
	}
	
	//Data provider for flipkart
	@DataProvider(name = "TestDataFlipkart")
	public Object[][] flipKartProviderMethod() {
		return new Object[][] { { "iPhone XR (64GB) - Yellow" ,"https://www.flipkart.com/"}};
	}

	@BeforeMethod(groups = { "AmazonFlipKartTest" })
	public void DriverInstantiation() throws Exception {
		driver = BrowserInstantiator.returnWebDriver();
	}

	@Test(dataProvider = "TestDataAmazon", groups = { "AmazonTest" })
	public void runAmazonTest(String mobileName,String url) throws Throwable {

		AmazonSearchActions amazonSearchActions = PageFactory
				.initElements(driver, AmazonSearchActions.class);

		//load the url
		amazonSearchActions
		.loadURL(url, driver);

		//Call the search item logic
		amazonSearchActions.searchForItem(mobileName, driver);

		//Call the return price logic
		amazonPrice = amazonSearchActions.returnPrice(mobileName, url, driver);
		
		driver.close();
		
		amazonFinalPrice = amazonPrice.replace(amazonPrice.charAt(0), ' ').trim();
		
		logger.info("Final AmazonPrice is-->" +amazonFinalPrice);
		

	}

	@Test(dataProvider = "TestDataFlipkart", groups = { "FlipKartTest" })
	public void runFlipkartTest(String mobileName,String url) throws Throwable {

		// Consumer Portal Instances
		FlipkartSearchActions flipkartSearchActions = PageFactory
				.initElements(driver, FlipkartSearchActions.class);

		//load the url
		flipkartSearchActions
		.loadURL(url, driver);

		//Call the search item logic
		flipkartSearchActions.searchForItem(mobileName, driver);
		
		//Call the return price logic
		flipkartPrice = flipkartSearchActions.returnPrice(driver);
		
		driver.close();
		
		flipkartFinalPrice = flipkartPrice.replace(flipkartPrice.charAt(0), ' ').trim();
		
		logger.info("Final FlipkartPrice is-->" +flipkartFinalPrice);
	}

	@AfterClass(groups={"AmazonTest","FlipKartTest"})
	public void driverShutDownAndCompare()
	{
		driver.quit();
		//Use number format as the values returned will be , seperated eg 55,000
		NumberFormat ukFormat = NumberFormat.getNumberInstance(Locale.US);
		//Comparison
		try {
			if(ukFormat.parse(amazonFinalPrice).intValue()>ukFormat.parse(flipkartFinalPrice).intValue())
			{
				logger.info("Please purchase from flipkart as amazon price is higher ");
			}
			else if(ukFormat.parse(flipkartFinalPrice).intValue()>ukFormat.parse(amazonFinalPrice).intValue())
			{
				logger.info("Please purchase from amazon as flipkart price is higher ");
			}
			else {
				logger.info("Both Prices are same, Please decide yourself ");
			}
			
		} catch (ParseException e) {
			logger.error("No prices retrieved");
		}
	}
}
