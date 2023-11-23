package com.amazon.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver lDriver;

	public RegisterPage(WebDriver rDriver) {
		lDriver = rDriver;
		PageFactory.initElements(rDriver, this);
	}

	@FindBy(xpath = "//a[@id='nav-link-accountList']")
	private WebElement register;

	@FindBy(id = "createAccountSubmit")
	private WebElement newUser;

	@FindBy(id = "ap_customer_name")
	private WebElement newUserName;

	@FindBy(id = "ap_email")
	private WebElement newUserEmail;

	@FindBy(id = "ap_password")
	private WebElement newUserPassword;

	@FindBy(id = "ap_password_check")
	private WebElement newUserPasswordCheck;

	@FindBy(id = "continue")
	private WebElement continueNewRegistration;

	@FindBy(xpath = "//div[@class='a-section a-spacing-extra-large']/span/span/span/span[2]")
	private WebElement phoneText;

	@FindBy(xpath = "//div[@class='a-section a-spacing-extra-large']/span/span/span/span[3]")
	private WebElement emailText;

	public void clickRegister() {
		register.click();
	}

	public void createNewAccount() {
		newUser.click();
	}

	public void setNewUserName(String newUName) {
		newUserName.sendKeys(newUName);
	}

	public void setNewUserEmailOrMobile(String eOrM) {
		newUserEmail.sendKeys(eOrM);
	}

	public void setNewUserPassword(String pswd) {
		newUserPassword.sendKeys(pswd);
	}

	public void setNewUserPasswordCheck(String pswdCheck) {
		newUserPasswordCheck.sendKeys(pswdCheck);
	}

	public void continueNURegistration() {
		continueNewRegistration.click();
	}

	public String getEmailText() {
		String text = emailText.getText();
		return text;
	}

	public String getPhoneText() {
		String text = phoneText.getText();
		return text;
	}
}
