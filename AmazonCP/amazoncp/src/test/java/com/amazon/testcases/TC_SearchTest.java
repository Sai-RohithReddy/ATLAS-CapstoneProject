package com.amazon.testcases;

import java.util.ArrayList;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazon.pageobject.SearchPage;

public class TC_SearchTest extends BaseClass{
	
	public ArrayList<String> getProducts() {
		ArrayList<String> products = new ArrayList<String>();
		
		products.add("tv");
		products.add("oven");
		products.add("ac");
		
		return products;
	}
	
	@Test
	public void testSearchProduct() throws InterruptedException {
		
		ArrayList<String> products = getProducts();
		SearchPage sp = new SearchPage(driver);
		
		boolean flag = false;
		
		for (String product : products) {
			
			flag = false;
			
			sp.searchProduct(product);
			String parent = driver.getWindowHandle();
			sp.clickSearch();
			sp.clickNextPage();
			sp.clickPreviousPage();
			sp.clickFirstProduct();
			
			flag = true;
			logger.info("Search operation succeeded with product name: " + product.toUpperCase());
			
			Thread.sleep(2000);
			
			for (String child : driver.getWindowHandles()) {
				driver.switchTo().window(child);
			}
			
			driver.close();
			driver.switchTo().window(parent);
		}
		
		Assert.assertTrue(flag);
	}
}
