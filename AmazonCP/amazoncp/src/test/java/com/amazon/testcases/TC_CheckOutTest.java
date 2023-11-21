package com.amazon.testcases;

import org.testng.annotations.Test;

import com.amazon.pageobject.CheckOutPage;

public class TC_CheckOutTest extends BaseClass{

	String product = "oven";
	String quantity = "1";
	
	String dummyFirstName = randomeString(5);
	String dummyLastName = randomeString(4);
	String dummyFullName = dummyFirstName + " " + dummyLastName;
	
	@Test
	public void testCheckOut() throws InterruptedException {
		
		CheckOutPage cp = new CheckOutPage(driver);
		
		signIn();
		
		cp.searchProduct(product);
		String parent = driver.getWindowHandle();
		cp.clickSearch();
		cp.clickFirstProduct();
		
		for (String child : driver.getWindowHandles()) {
			driver.switchTo().window(child);
		}
		
		cp.setQuantity(quantity);
		cp.clickBuNow();
		
		Thread.sleep(5000);
		
		cp.clickChangeAddress();
		cp.clickAddNewAddress();
		cp.setFullName(dummyFullName);
		
		cp.clickDiscartNewAddress();

//		signOut();
	}
}
