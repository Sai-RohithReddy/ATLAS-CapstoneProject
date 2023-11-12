package com.amazon.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver lDriver;

	public LoginPage(WebDriver rDriver) {
		lDriver = rDriver;
		PageFactory.initElements(rDriver, this);
	}

	@FindBy(id = "nav-link-accountList")
	WebElement signIn;

	@FindBy(id = "ap_email")
	WebElement emailText;

	@FindBy(id = "continue")
	WebElement cntnu;

	@FindBy(id = "ap_password")
	WebElement pswd;

	@FindBy(id = "signInSubmit")
	WebElement submit;

	@FindBy(xpath = "//a[@id='nav-link-accountList']/div/span")
	WebElement confText;
	
	@FindBy(id = "nav-item-signout")
	WebElement signOutText;
	
//	@FindBy(xpath = "//div[@class='a-alert-content']/ul/li/span")
//	WebElement problemText;

	public void clickSignIn() {
		signIn.click();
	}

	public void setEmailORPhone(String val) {
		emailText.sendKeys(val);
	}

	public void continueSignIn() {
		cntnu.click();
	}

	public void setPassword(String p) {
		pswd.sendKeys(p);
	}

	public void submitSignIn() {
		submit.click();
	}

	public String getSignInConfirmation() {
		String text = confText.getText();
		return text;
	}
	
//	public String getProblemText() {
//		String text = problemText.getText();
//		return text;
//	}
	
	public void SignOut() throws InterruptedException {
		Actions action = new Actions(lDriver);
		action.moveToElement(signIn).build().perform();
		Thread.sleep(2000);
		signOutText.click();
	}
}
