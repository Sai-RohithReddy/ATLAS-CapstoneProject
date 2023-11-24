package com.amazon.testcases;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.amazon.pageobject.ProductPage;
import com.amazon.pageobject.SearchPage;

public class TC_CartFunctionalityTest extends BaseClass {

	// Helper function to provide orders list
	public Map<String, String> orderList() {

		Map<String, String> product = new HashMap<>();

		product.put("oven", "2");
		product.put("tv", "1");
		product.put("freezer", "1");

		return product;
	}

	@Test
	public void testAddToCart() throws IOException, InterruptedException {

		ProductPage pp = new ProductPage(driver);
		Map<String, String> ol = orderList();

		for (Map.Entry<String, String> entry : ol.entrySet()) {

			String product = entry.getKey();
			String count = entry.getValue();

			pp.searchProduct(product);
			String parent = driver.getWindowHandle();
			pp.clickSearch();

			try {
				pp.clickFirstProduct();
			} catch (Exception e) {
				pp.clickFirstProductT2();
			}

			Thread.sleep(2000);

			for (String child : driver.getWindowHandles()) {
				driver.switchTo().window(child);
			}

			try {
				pp.setQuantity(count);
			} catch (Exception e) {
				logger.warn("Quantity option is not available");
			}

			Thread.sleep(2000);
			pp.clickAddToCart();
			logger.info(count + " " + product + " added to cart successfully");

			driver.close();
			driver.switchTo().window(parent);
		}
	}

	@Test()
	public void testAddToCartUs() throws IOException {

		ProductPage pp = new ProductPage(driver);
		Map<String, String> ol = orderList();

		driver.get(baseUrlUs);

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

	// Helper function to check if the product is available for delivery
	public boolean isProductAvailable() {

		boolean notOutOfStock = !driver.getPageSource().contains("Temporarily out of stock.");
		boolean available = !driver.getPageSource().contains("Currently unavailable.");
		boolean shipping = !driver.getPageSource().contains(
				"This item cannot be shipped to your selected delivery location. Please choose a different delivery location.");
		boolean inStock = driver.getPageSource().contains("In Stock");

		boolean isAvailable = inStock && available && shipping && notOutOfStock;

		return isAvailable;
	}

	// Function is used to set ZIP code in home page
	public void setTempZip() throws InterruptedException {
		SearchPage sp = new SearchPage(driver);

		sp.clickDeliveryTo();
		Thread.sleep(1000);
		sp.setZip(tempZip);
		sp.clickSubmitZip();
		Thread.sleep(1000);
		sp.submitDeliveryTo();
	}
}
