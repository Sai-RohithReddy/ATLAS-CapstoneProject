package com.amazon.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends SearchPage{

	public ProductPage(WebDriver rDriver) {
		super(rDriver);
	}

	@FindBy(xpath = "//select[@id='quantity']")
	WebElement quantity;
	
	@FindBy(id = "submit.add-to-cart")
	WebElement addToCart;
	
	@FindBy(id = "submit.buy-now")
	WebElement buyNow;
}
