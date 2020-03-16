package com.flipkart.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class FlipkartHomePageObjects {

	@FindBy(how=How.XPATH, using = "//input[@title='Search for products, brands and more']")
	public WebElement searchTextBox;
	
	@FindBy(how=How.XPATH, using = "//button[@type='submit']")
	public WebElement searchIcon;
	
	@FindBy(how=How.XPATH, using = "//button[contains(text(),'✕')]")
	public static WebElement popUpCloseButton;

	@FindBy(how=How.XPATH, using ="//div[contains(text(),'iPhone XR') and contains(text(),'Yellow') and contains(text(),'64 GB')]//parent::*//parent::*//div[contains(text(),'₹')]")
	public static WebElement iPhonePrice;
}
