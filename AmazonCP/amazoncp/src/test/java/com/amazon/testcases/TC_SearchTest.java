package com.amazon.testcases;

import java.util.ArrayList;

import org.testng.Assert;
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
	public void testSearchProduct() {
		
		ArrayList<String> products = getProducts();
		SearchPage sp = new SearchPage(driver);
		
		boolean flag = false;
		
		for (String product : products) {
			
			flag = false;
			
			sp.searchProduct(product);
			sp.clickSearch();
			sp.clickNextPage();
			sp.clickPreviousPage();
			sp.clickFirstProduct();
			
			flag = true;
			logger.info("Search operation succeeded with product name: " + product.toUpperCase());
		}
		
		Assert.assertTrue(flag);
	}
}
