package com.amazon.testcases;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.amazon.utilities.ReadConfig;

public class BaseClass {

	ReadConfig readConfig = new ReadConfig();

	public String baseUrl = readConfig.getApplicationURL();
	public String userId = readConfig.getUserId();
	public String password = readConfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;

	@Parameters("browser")
	@BeforeClass
	public void setup(String br) {

		logger = Logger.getLogger(BaseClass.class);
		PropertyConfigurator.configure("log4j.properties");

		if (br.toLowerCase().equals("edge")) {
			driver = new EdgeDriver();
		} else if (br.toLowerCase().equals("chrome")) {
			driver = new ChromeDriver();
		} else {
			driver = new FirefoxDriver();
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseUrl);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	public void captureScreen(WebDriver driver, String tName) throws IOException {
		Date currDate = new Date();
		String tStamp = currDate.toString().replace(" ", "-").replace(":", "-");

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(".\\Screenshorts\\" + tStamp + "-" + tName + ".png");
		FileUtils.copyFile(source, destination);
	}

	public String randomeString(int size) {
		String generatedString = RandomStringUtils.randomAlphabetic(size);
		return generatedString;
	}
	
	public String randomNum(int size) {
		String randomNumeric = RandomStringUtils.randomNumeric(size);
		return randomNumeric;
	}
}
