package com.amazon.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazon.pageobject.RegisterPage;

public class TC_RegisterTest extends BaseClass {

	String tempPassword = randomeString(4) + randomNum(4);
	String tempUserName = randomeString(8);
	String tempEmail = tempUserName + "@gmail.com";
	String tempMobileNumber = randomNum(10);
	
	@BeforeMethod
	public void invokeBaseURL() {
		driver.get(baseUrl);
	}

	@Test(enabled = true)
	public void testNewRegistrationWithEmail() throws IOException, InterruptedException {

		RegisterPage hp = new RegisterPage(driver);
		logger.info("URL is opened");

		hp.clickRegister();
		logger.info("Registration page is opened");
		hp.createNewAccount();
		hp.setNewUserName(tempUserName);
		logger.info(tempUserName + " is entered");
		hp.setNewUserEmailOrMobile(tempEmail);
		logger.info(tempEmail + " is entered");
		hp.setNewUserPassword(tempPassword);
		logger.info(tempPassword + " is entered");
		hp.setNewUserPasswordCheck(tempPassword);
		logger.info(tempPassword + " is entered for confirmation");

		Thread.sleep(5000);

		if (hp.getEmailText().equals("Verify email")) {
			Assert.assertTrue(true);
			logger.info("Registration with email Succeeded");
		} else {
			captureScreen(driver, "testNewRegistrationWithEmail");
			logger.warn("testNewRegistrationWithEmail failed");
			Assert.assertTrue(false);
		}

	}

	@Test(enabled = true)
	public void testNewRegistrationWithMobileNumber() throws IOException, InterruptedException {

		RegisterPage hp = new RegisterPage(driver);
		logger.info("URL is opened");

		hp.clickRegister();
		logger.info("Registration page is opened");
		hp.createNewAccount();
		hp.setNewUserName(tempUserName);
		logger.info(tempUserName + " is entered");
		hp.setNewUserEmailOrMobile(tempMobileNumber);
		logger.info(tempMobileNumber + " is entered");
		hp.setNewUserPassword(tempPassword);
		logger.info(tempPassword + " is entered");
		hp.setNewUserPasswordCheck(tempPassword);
		logger.info(tempPassword + " is entered for confirmation");

		Thread.sleep(5000);

		if (hp.getPhoneText().equals("Verify mobile number")) {
			Assert.assertTrue(true);
			logger.info("Registration with email Succeeded");
		} else {
			captureScreen(driver, "testNewRegistrationWithMobileNumber");
			logger.warn("testNewRegistrationWithMobileNumber failed");
			Assert.assertTrue(false);
		}

	}
}
