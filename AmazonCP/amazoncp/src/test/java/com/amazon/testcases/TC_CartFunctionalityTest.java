package com.amazon.testcases;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.amazon.pageobject.ProductPage;

public class TC_CartFunctionalityTest extends BaseClass{
	
	public Map<String, String> orderList() {
		
		Map<String, String> product = new HashMap<>();
		
		product.put("camera", "2");
		product.put("oven", "2");
		product.put("tv", "1");
		product.put("freezer", "1");
		
		return product;
	}
	
	@Test
	public void testAddToCart() throws IOException {
		
		ProductPage pp = new ProductPage(driver);
		Map<String, String> ol = orderList();
		
		for (Map.Entry<String, String> entry : ol.entrySet()) {
			
			String product = entry.getKey();
			String count = entry.getValue();
			
			pp.searchProduct(product);
			pp.clickSearch();
			pp.clickFirstProduct();
			if (isProductAvailable()) {
				pp.setQuantity(count);
				pp.clickAddToCart();
				logger.info(count + " " + product + " added to cart successfully");
			} else {
				String scName = "testAddToCart-" + product;
				captureScreen(driver, scName);
				logger.info(product + " is currently unavailable!");
			}
		}
	}
	
	@Test
	public void testRemoveFromCart() {
		
		ProductPage pp = new ProductPage(driver);
		int cartSize = pp.getCartSize();
		
		if (cartSize > 0) {
			
			pp.clickCart();
			pp.clearCart(cartSize);
			logger.info("Cart is empty");
			
		} else {
			logger.info("There are not items in cart");
		}
		
		
	}
 	
	public boolean isProductAvailable() {
		
		boolean inStock = !driver.getPageSource().contains("Temporarily out of stock.");
		boolean available =  !driver.getPageSource().contains("Currently unavailable.");
		boolean shipping = !driver.getPageSource().contains("This item cannot be shipped to your selected delivery location. Please choose a different delivery location.");
		
		boolean isAvailable = inStock && available && shipping;
		
		return isAvailable;
	}
}
