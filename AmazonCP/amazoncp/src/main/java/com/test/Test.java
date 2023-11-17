package com.test;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

public class Test {
	public static void main(String[] args) throws InterruptedException {

		
		WebDriver driver = new EdgeDriver();

		
		driver.get("https://www.makemytrip.com/hotels");
	
		driver.findElement(By.className("makeFlex hrtlCenter font10 makeRelative lhUser userLoggedOut")).click();
		driver.findElement(By.id("username")).sendKeys("7893553778");
		
		driver.quit();
	}
}
