package com.amazon.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AmazonHomePageObjects {

	@FindBy(how=How.XPATH, using = "//*[@id='twotabsearchtextbox']")
	public static WebElement searchTextBox;
	
	@FindBy(how=How.CSS, using = "#nav-search > form > div.nav-right > div > input")
	public static WebElement searchIcon;
	
}
