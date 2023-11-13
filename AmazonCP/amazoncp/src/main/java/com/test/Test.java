package com.test;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		ArrayList<String> products = new ArrayList<String>();
		
		products.add("tv");
		products.add("oven");
		products.add("ac");
		
		WebDriver driver = new EdgeDriver();
		Actions action = new Actions(driver);
		
		driver.get("https://www.amazon.com/");
		
		for (String product : products) {
			action.moveToElement(driver.findElement(By.id("twotabsearchtextbox"))).click().doubleClick().sendKeys(product).build().perform();
			driver.findElement(By.id("nav-search-submit-button")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[@class='s-pagination-item s-pagination-next s-pagination-button s-pagination-separator']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[@class='s-pagination-item s-pagination-next s-pagination-button s-pagination-separator']")).click();
//			driver.findElement(By.xpath("//span[@class='s-pagination-item s-pagination-previous s-pagination-disabled ']")).click();
		//a[@class='s-pagination-item s-pagination-previous s-pagination-button s-pagination-separator']
		}
	}
}
