package com.test;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Test {
	public static void main(String[] args) throws InterruptedException {

		
		WebDriver driver = new EdgeDriver();
		Actions action = new Actions(driver);
		          
		
		driver.get("https://www.amazon.in/Samsung-inches-Crystal-Ultra-UA43AUE65AKXXL/dp/B0B15CPR37/ref=sr_1_1_sspa?crid=1UG9TDP3WJNNK&keywords=tv&qid=1700547070&sprefix=tv%2Caps%2C223&sr=8-1-spons&sp_csd=d2lkZ2V0TmFtZT1zcF9hdGY&th=1");
		Thread.sleep(2000);
		driver.findElement(By.id("nav-link-accountList")).click();
		driver.findElement(By.id("ap_email")).sendKeys("rohithamazoncp@gmail.com");
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("ap_password")).sendKeys("amazonCP");
		driver.findElement(By.id("signInSubmit")).click();
		
		Thread.sleep(2000);
		List<WebElement> findElements = driver.findElements(By.xpath("//select[@id='quantity']"));
		System.out.println(findElements.size());
//		WebElement temp = findElements.get(1);
		Select select = new Select(findElements.get(0));
		select.selectByValue("2");
//		driver.quit();
	}
}
