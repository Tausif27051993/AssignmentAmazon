package com.amazon.amazontest;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.amazon.helper.BrowserInstantiator;

public class ClubMahindraTest {

	public static WebDriver driver = null;
	static final Logger logger = Logger.getLogger(AmazonFlipkartTest.class);
	String amazonPrice = null;
	String flipkartPrice = null;
	String amazonFinalPrice = null;
	String flipkartFinalPrice = null;

	//Data provider for mahindra
	@DataProvider(name = "TestDataMahindra")
	public Object[][] amazonProviderMethod() {
		return new Object[][] { { "Some Title" ,"No Comments because the place is awesome"}};
	}

	@BeforeMethod(groups = { "MahindraTest" })
	public void DriverInstantiation() throws Exception {
		driver = BrowserInstantiator.returnWebDriver();
	}

	@Test(dataProvider = "TestDataMahindra", groups = { "MahindraTest" })
	public void runAmazonTest(String title,String comments) throws Throwable {

		driver.get("https://www.tripadvisor.in/");
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@title='Search']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@title='Search']")).sendKeys("Club Mahindra");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@data-test-attribute='typeahead-results']//a[1]")).click();
		Thread.sleep(5000);
		js.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//*[@id=\"REVIEWS\"]/div/span[3]/span[1]")));
		Thread.sleep(5000);
		WebElement elem = driver.findElement(By.xpath("//a[@class='ui_button primary' and contains(text(),'Write a review')]"));
		Thread.sleep(5000);
		elem.click();
		Thread.sleep(5000);
		for (String winHandle : driver.getWindowHandles()) { //Gets the new window handle
			driver.switchTo().window(winHandle);        // switch focus of WebDriver to the next found window handle (that's your newly opened window)              
		}
		Thread.sleep(5000);
		//Select select = new Select(driver.findElement(By.xpath("//label[@class='visuallyHidden' and contains(text(),'Rooms')]//following-sibling::span")));
		//js.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//label[@class='visuallyHidden' and contains(text(),'Rooms')]//following-sibling::span")));
		Thread.sleep(5000);
		
		WebElement demo1 =  driver.findElement(By.xpath("//div[@id='DQ_RATINGS']//div[@class='ratingBubbleTable']//div[@class='dq_allTravelers questionRow stripedRow'][1]//div[1]"));
		WebElement demo2 =  driver.findElement(By.xpath("//div[@id='DQ_RATINGS']//div[@class='ratingBubbleTable']//div[@class='dq_allTravelers questionRow '][1]//div[1]"));
		WebElement demo3 =  driver.findElement(By.xpath("//div[@id='DQ_RATINGS']//div[@class='ratingBubbleTable']//div[@class='dq_allTravelers questionRow stripedRow'][2]//div[1]"));
		
		System.out.println(demo1.getText());
		System.out.println(demo2.getText());
		System.out.println(demo3.getText());
	   
		WebElement requiredElementTop = driver.findElement(By.xpath("//div[@class='easyClear bigRatingParent']//following-sibling::span[@id='bubble_rating']"));
		WebElement requiredElement = driver.findElement(By.xpath("//label[@class='visuallyHidden' and contains(text(),'"+demo1.getText()+"'"+")]//following-sibling::span"));
		WebElement requiredElement1 = driver.findElement(By.xpath("//label[@class='visuallyHidden' and contains(text(),'"+demo2.getText()+"'"+")]//following-sibling::span"));
		WebElement requiredElement2 = driver.findElement(By.xpath("//label[@class='visuallyHidden' and contains(text(),'"+demo3.getText()+"'"+")]//following-sibling::span"));
		Thread.sleep(3000);
		int logoW = requiredElement.getSize().getWidth();        
		System.out.println("Logo logoWidth : "+logoW+" pixels");
		int logoX = requiredElement.getLocation().getX();
		System.out.println("Logo X : "+logoX+" pixels");
		System.out.println(requiredElement.getLocation().getY());
		int temp =24;
			for(int i=0;i<5;i++) {
			new Actions(driver).moveToElement(requiredElementTop,temp,0).click().build().perform();
			temp=temp+24;
		}
		temp=24;
		driver.findElement(By.xpath("//input[@id='ReviewTitle']")).sendKeys(title);
		driver.findElement(By.xpath("//textarea[@id='ReviewText']")).sendKeys(comments+comments+comments+comments+comments+comments+comments);
		js.executeScript("arguments[0].scrollIntoView(true);",requiredElement);

		for(int i=0;i<5;i++) {
		new Actions(driver).moveToElement(requiredElement,temp,0).click().build().perform();
		temp=temp+24;
		}
		temp=24;
		for(int i=0;i<5;i++) {
			new Actions(driver).moveToElement(requiredElement1,temp,0).click().build().perform();
			temp=temp+24;
			}
		temp=24;
		for(int i=0;i<5;i++) {
			new Actions(driver).moveToElement(requiredElement2,temp,0).click().build().perform();
			temp=temp+24;
			}
		temp=24;
		driver.findElement(By.cssSelector("#noFraud")).click();
		driver.switchTo().defaultContent();
		driver.close();
		driver.quit();
	}


}
