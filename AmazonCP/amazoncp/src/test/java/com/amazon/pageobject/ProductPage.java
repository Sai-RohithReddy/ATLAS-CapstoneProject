package com.amazon.pageobject;

import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends SearchPage{

	public ProductPage(WebDriver rDriver) {
		super(rDriver);
	}

	@FindBy(xpath = "//select[@id='quantity']")
	private WebElement quantity;
	
	@FindBy(id = "add-to-cart-button")
	private WebElement addToCart;
	
	@FindBy(id = "buy-now-button")
	private WebElement buyNow;
	
	@FindBy(id = "nav-cart")
	private WebElement cartButton;
	
	@FindBy(id = "nav-cart-count")
	private WebElement cartCount;
	
	@FindBy(xpath = "//input[@value='Delete']")
	private WebElement clearCartButton;
	
	public void setQuantity(String qty) {
		Select dropDown = new Select(quantity);
		dropDown.selectByValue(qty);
	}
	
	public void clickAddToCart() {
		addToCart.click();
	}
	
	public void clickBuNow() {
		buyNow.click();
	}
	
	public void clickCart() {
		cartButton.click();
	}
	
	public int getCartSize() {
		
		String countStr = cartCount.getText();
		int count = Integer.parseInt(countStr);
		
		return count;
		
	}
	
	public void clearCart(int cartSize) {
		
		for (int i = 0; i < cartSize; i++) {
			clearCartButton.click();
		}
		
	}
	
}
