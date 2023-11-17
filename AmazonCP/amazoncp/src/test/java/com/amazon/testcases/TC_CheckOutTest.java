package com.amazon.testcases;

import org.testng.annotations.Test;

import com.amazon.pageobject.CheckOutPage;

public class TC_CheckOutTest extends BaseClass{

	String product = "camera";
	String quantity = "2";
	
	@Test
	public void testCheckOut() throws InterruptedException {
		
		CheckOutPage cp = new CheckOutPage(driver);
		
		signIn();
		cp.searchProduct(product);
		cp.clickSearch();
		cp.clickFirstProduct();
		cp.setQuantity(quantity);
		cp.clickAddToCart();
		cp.clearCart(cp.getCartSize());
		signOut();
	}
}
