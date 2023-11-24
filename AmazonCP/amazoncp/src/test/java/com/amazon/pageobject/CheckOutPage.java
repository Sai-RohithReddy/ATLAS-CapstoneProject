package com.amazon.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CheckOutPage extends ProductPage {

	public CheckOutPage(WebDriver rDriver) {
		super(rDriver);

	}

	@FindBy(id = "addressChangeLinkId")
	private WebElement changeAddress;

	@FindBy(id = "add-new-address-popover-link")
	private WebElement addNewAddress;

	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressFullName']")
	private WebElement fullName;

	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressPhoneNumber']")
	private WebElement mobileNumber;

	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressPostalCode']")
	private WebElement postalCode;

	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressLine1']")
	private WebElement addressLine1;

	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressLine2']")
	private WebElement addressLine2;

	@FindBy(xpath = "//input[@id='address-ui-widgets-landmark']")
	private WebElement landmark;

	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressCity']")
	private WebElement city;

	@FindBy(xpath = "//select[@id='address-ui-widgets-enterAddressStateOrRegion-dropdown-nativeId']")
	private WebElement state;

	@FindBy(xpath = "//input[@id='address-ui-widgets-use-as-my-default']")
	private WebElement defaultAddress;

	@FindBy(xpath = "//input[@aria-labelledby='address-ui-widgets-form-submit-button-announce']")
	private WebElement submitNewAddress;

	@FindBy(xpath = "//input[@data-testid='Address_selectShipToThisAddress']")
	private WebElement useThisAddress;

	@FindBy(xpath = "//span[@id='orderSummaryPrimaryActionBtn']/span/input")
	private WebElement deliveryOption;

	@FindBy(xpath = "//input[@name='ppw-instrumentRowSelection']")
	private WebElement creditOrDebit;

	@FindBy(xpath = "//a[@class='a-link-emphasis pmts-add-cc-default-trigger-link']")
	private WebElement enterCardDetails;

	@FindBy(xpath = "//input[@name='addCreditCardNumber']")
	private WebElement cardNumber;

	@FindBy(xpath = "//input[@name='ppw-accountHolderName']")
	private WebElement nickName;

	@FindBy(xpath = "//select[@name='ppw-expirationDate_month']")
	private WebElement expiryDate;

	@FindBy(xpath = "//select[@name='ppw-expirationDate_year']")
	private WebElement expityYear;

	@FindBy(xpath = "//input[@name='ppw-widgetEvent:AddCreditCardEvent']")
	private WebElement submitCardDetails;

	@FindBy(xpath = "//button[@name='ppw-widgetEvent:CancelAddCreditCardEvent']")
	private WebElement discartCardDetails;

	@FindBy(xpath = "//button[@data-action='a-popover-close']")
	private WebElement discartNewAddress;

	@FindBy(xpath = "//div[@class='a-box-inner']/div/div[3]/div/div/div/div/span/div/label/input")
	private WebElement netBanking;

	@FindBy(xpath = "//select[@name='ppw-bankSelection_dropdown']")
	private WebElement netBankingOptions;

	@FindBy(xpath = "//input[@aria-labelledby='orderSummaryPrimaryActionBtn-announce']")
	private WebElement useThisPaymentMethod;

	@FindBy(xpath = "//span[@class='place-order-btn']/span/span/span/input")
	private WebElement placeYourOrder;

	public void clickChangeAddress() {
		changeAddress.click();
	}

	public void clickAddNewAddress() {
		addNewAddress.click();
	}

	public void setFullName(String fName) {
		fullName.sendKeys(fName);
	}

	public void setMobileNumber(String mNum) {
		mobileNumber.sendKeys(mNum);
	}

	public void setPostalCode() {
		postalCode.sendKeys("500035");
	}

	public void setPostalCode(String pCode) {
		postalCode.sendKeys(pCode);
	}

	public void setAddressLine1(String a1) {
		addressLine1.sendKeys(a1);
	}

	public void setAddressLine2(String a2) {
		addressLine2.sendKeys(a2);
	}

	public void setLandmark(String lMark) {
		landmark.sendKeys(lMark);
	}

	public void setCity(String cty) {
		city.sendKeys(cty);
	}

	public void setState() {
		Select select = new Select(state);
		select.selectByIndex(0);
	}

	public void clickUseThisAddress() {
		useThisAddress.click();
	}

	public void clickCreditCard() {
		creditOrDebit.click();
	}

	public void clickEnterCardDetails() {
		enterCardDetails.click();
	}

	public void setCardNumber(String cNumber) {
		cardNumber.sendKeys(cNumber);
	}

	public void setNickName(String nName) {
		nickName.clear();
		nickName.sendKeys(nName);
	}

	public void clickDiscartNewAddress() {
		discartNewAddress.click();
	}

	public void setExpiryDate() {
		Select select = new Select(expiryDate);
		select.selectByIndex(15);
	}

	public void setExpiryYear() {
		Select select = new Select(expityYear);
		select.selectByIndex(5);
	}

	public void setExpiryDate(String eDate) {
		Select select = new Select(expiryDate);
		select.selectByValue(eDate);
	}

	public void setExpiryYear(String eYear) {
		Select select = new Select(expityYear);
		select.selectByValue(eYear);
	}

	public void clickSubmitCardDetails() {
		submitCardDetails.click();
	}

	public void clickDiscartCardDetails() {
		discartCardDetails.click();
	}

	public void clickDelivaryOption() {
		deliveryOption.click();
	}

	public void clickNetBanking() {
		netBanking.click();
	}

	public void setNetBanking() {
		Select select = new Select(netBankingOptions);
		select.selectByVisibleText("ICICI Bank");

		lDriver.findElement(By.linkText("ICICI Bank")).click();
	}

	public void clickUseThisPaymentMethod() {
		useThisPaymentMethod.click();
	}

	public void clickPlaceYourOrder() {
		placeYourOrder.click();
	}
}
