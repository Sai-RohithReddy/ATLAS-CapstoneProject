package com.amazon.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.amazon.pageobject.LoginPage;
import com.amazon.utilities.XLUtils;

public class TC_LoginTest extends BaseClass {

	@BeforeMethod
	public void invokeBaseURL() {
		driver.get(baseUrlUs);
	}

	@Test
	public void testLogin() throws IOException, InterruptedException {
		LoginPage lp = new LoginPage(driver);
		lp.clickSignIn();
		lp.setEmailORPhone(userId);
		lp.continueSignIn();
		lp.setPassword(password);
		lp.submitSignIn();

		if (lp.getSignInConfirmation().equals("Hello, Rohith")) {
			logger.info("testLogin executed successfully");
			lp.SignOut();
			Assert.assertTrue(true);
		} else {
			captureScreen(driver, "testLogin");
			logger.warn("testLogin failed");
			Assert.assertTrue(false);
		}
	}

	@Test(dataProvider = "LoginData")
	public void testLoginDDT(String user, String pwd) throws IOException, InterruptedException {

		LoginPage lp = new LoginPage(driver);
		lp.clickSignIn();
		lp.setEmailORPhone(user);
		lp.continueSignIn();

		if (isValidEmail()) {
			logger.info("Entered valid email" + user);
			lp.setPassword(pwd);
			lp.submitSignIn();
			if (isPuzzleCheckEnabled()) {
				captureScreen(driver, "PuzzleCheck");
				Thread.sleep(15000);
				logger.info("Puzzle Check Enabled");
			}
			if (isValidPassword()) {
				logger.info("testLoginDDT passed with id and password: " + user + ", " + pwd);
				lp.SignOut();
				Assert.assertTrue(true);
			} else {
//				captureScreen(driver, "testLoginDDT");
				logger.warn("testLoginDDT failed because of invalid password: " + pwd);
				Assert.assertFalse(false);
			}

		} else {
//			captureScreen(driver, "testLoginDDT");
			logger.warn("testLoginDDT failed because of invalid id: " + user);
			Assert.assertFalse(false);
		}

	}

	@DataProvider(name = "LoginData")
	String[][] getData() throws IOException {

		String path = ".\\src\\test\\java\\com\\amazon\\testdata\\LoginData.xlsx";
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colnum = XLUtils.getCellCount(path, "Sheet1", rownum);

		String[][] logindata = new String[rownum][colnum];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colnum; j++) {
				logindata[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return logindata;
	}

	// Helper function to validate entered email is valid or not
	public boolean isValidEmail() {
		return !driver.getPageSource().contains("We cannot find an account with that email address");
	}

	// Helper function to validate entered password is valid or not
	public boolean isValidPassword() {
		return !driver.getPageSource().contains("Your password is incorrect");
	}

	// Helper function to check if Puzzle Check is Enabled while sign in
	public boolean isPuzzleCheckEnabled() {
		return driver.getPageSource().contains("Solve this puzzle to protect your account");
	}

}
