package com.amazon.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckOutPage extends ProductPage{

	public CheckOutPage(WebDriver rDriver) {
		super(rDriver);
		
	}

	@FindBy(id = "addressChangeLinkId")
	WebElement changeAddress;
	
	@FindBy(id = "add-new-address-popover-link")
	WebElement addNewAddress;
	
	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressFullName']")
	WebElement fullName;
	
	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressPhoneNumber']")
	WebElement mobileNumber;
	
	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressPostalCode']")
	WebElement postalCode;
	
	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressLine1']")
	WebElement addressLine1;
	
	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressLine2']")
	WebElement addressLine2;
	
	@FindBy(xpath = "//input[@id='address-ui-widgets-landmark']")
	WebElement landmark;
	
	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressCity']")
	WebElement city;
	
	@FindBy(xpath = "//select[@id='address-ui-widgets-enterAddressStateOrRegion-dropdown-nativeId']")
	WebElement state;
	
	@FindBy(xpath = "//input[@id='address-ui-widgets-use-as-my-default']")
	WebElement defaultAddress;
	
	@FindBy(xpath = "//input[@aria-labelledby='address-ui-widgets-form-submit-button-announce']")
	WebElement submitNewAddress;
	
	@FindBy(xpath = "//input[@data-testid='Address_selectShipToThisAddress']")
	WebElement useThisAddress;
	
	@FindBy(xpath = "//span[@data-action='a-tooltip-button-blocker']/span/span/input")
	WebElement deliveryOption;
	
	@FindBy(xpath = "//input[@id='pp-JeEBqJ-96']")
	WebElement creditOrDebit;
	
	@FindBy(xpath = "//a[@id='pp-JeEBqJ-99']")
	WebElement enterCardDetails;
	
	@FindBy(xpath = "//input[@id='pp-cY72jF-16']")
	WebElement cartNumber;
	
	@FindBy(xpath = "//input[@id='pp-cY72jF-18']")
	WebElement nickName;
	
	@FindBy(xpath = "//select[@id='pp-cY72jF-19']")
	WebElement expiryDate;
	
	@FindBy(xpath = "//select[@id='pp-cY72jF-21']")
	WebElement expityYear;
	
	@FindBy(xpath = "//input[@aria-labelledby='pp-cY72jF-26-announce']")
	WebElement submitCardDetails;
	
	@FindBy(xpath = "//button[@data-action='a-popover-close']")
	WebElement discartNewAddress;
	
	public void clickChangeAddress() {
		changeAddress.click();
	}
	
	public void clickAddNewAddress() {
		addNewAddress.click();
	}
	
	public void setFullName(String fName) {
		fullName.sendKeys(fName);
	}
	
	public void clickDiscartNewAddress() {
		discartNewAddress.click();
	}
}
