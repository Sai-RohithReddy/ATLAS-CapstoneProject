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

import com.amazon.pageobject.LoginPage;
import com.amazon.utilities.ReadConfig;

public class BaseClass {

	static ReadConfig readConfig = new ReadConfig();

	public static String baseUrlUs = readConfig.getApplicationURLUs();
	public static String baseUrlIn = readConfig.getApplicationURLIn();
	public static String userId = readConfig.getUserId();
	public static String password = readConfig.getPassword();
	public static String tempZip = readConfig.getZip();
	public static String mySqlUser = readConfig.getMySqlUser();
	public static String mySqlPassword = readConfig.getMySqlPassword();
	public static String mySqlDB = readConfig.getDatabaseName();

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
		driver.get(baseUrlIn);
		driver.manage().window().maximize();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	// Function is used to capture screen shorts
	public void captureScreen(WebDriver driver, String tName) throws IOException {
		Date currDate = new Date();
		String tStamp = currDate.toString().replace(" ", "-").replace(":", "-");

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(".\\Screenshorts\\" + tStamp + "-" + tName + ".png");
		FileUtils.copyFile(source, destination);
	}

	// Function is used to generate random String based on size provided
	public String randomeString(int size) {
		String generatedString = RandomStringUtils.randomAlphabetic(size);
		return generatedString;
	}

	// Function is used to generate random Numbers based on size provided
	public String randomNum(int size) {
		String randomNumeric = RandomStringUtils.randomNumeric(size);
		return randomNumeric;
	}

	// Function is used to Sign In to amazon account
	public void signIn() {
		LoginPage lp = new LoginPage(driver);
		lp.clickSignIn();
		lp.setEmailORPhone(userId);
		lp.continueSignIn();
		lp.setPassword(password);
		lp.submitSignIn();
	}

	// Function is used to Sign Out from amazon account
	public void signOut() throws InterruptedException {
		LoginPage lp = new LoginPage(driver);
		lp.SignOut();
	}
}
