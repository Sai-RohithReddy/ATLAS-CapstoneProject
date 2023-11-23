package com.amazon.testcases;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.amazon.pageobject.CheckOutPage;
import com.amazon.utilities.DBUtils;

public class TC_CheckOutTest extends BaseClass {

	String product = "football";
	String quantity = "5";

	String dummyFirstName = randomeString(5);
	String dummyLastName = randomeString(4);
	String dummyFullName = dummyFirstName + " " + dummyLastName;
	String dummyMobileNumber = randomNum(10);
	String dummyAddressLine1 = randomeString(20);
	String dummyAddressLine2 = randomeString(15);
	String dummyLandmark = randomeString(10);

	@Test
	public void testCheckOut() throws InterruptedException, IOException, SQLException {

		CheckOutPage cp = new CheckOutPage(driver);

		signIn();

		cp.searchProduct(product);
		String parent = driver.getWindowHandle();
		cp.clickSearch();

		try {
			cp.clickFirstProduct();
		} catch (Exception e) {
			cp.clickFirstProductT2();
		}

		for (String child : driver.getWindowHandles()) {
			driver.switchTo().window(child);
		}

		try {
			cp.setQuantity(quantity);
		} catch (Exception e) {
			logger.warn("Quantity option is not available");
		}

		cp.clickBuNow();

		Thread.sleep(5000);

		cp.clickChangeAddress();
		cp.clickAddNewAddress();
		cp.setFullName(dummyFullName);
		cp.setMobileNumber(dummyMobileNumber);
		cp.setPostalCode();
		cp.setAddressLine1(dummyAddressLine1);
		cp.setAddressLine2(dummyAddressLine2);
		cp.setLandmark(dummyLandmark);

		captureScreen(driver, "NewAddress");
		cp.clickDiscartNewAddress();

		logger.info("New Address Entered Successfully");

		Thread.sleep(5000);

		cp.clickUseThisAddress();

		Thread.sleep(9000);

		if (driver.getPageSource().contains("Choose special delivery options")) {
			cp.clickDelivaryOption();
			logger.info("Delivery Option Selected");
		}

		Thread.sleep(5000);

		cp.clickNetBanking();
		cp.setNetBanking();
		cp.clickUseThisPaymentMethod();

		logger.info("Payment Details Entered Successfully");
		Thread.sleep(15000);

		Assert.assertTrue(isOrderPlaced());
		logger.info("Product name: " + product + " Quantity: " + quantity + " Placed Successfully");

		updateOrdersTable(product, "rohith", quantity);
		logger.info("Order table is updated with Product name: " + product + ", Quantity: " + quantity);

		driver.close();
		driver.switchTo().window(parent);
		signOut();
	}

	public boolean isOrderPlaced() {
		return driver.getPageSource().contains("Place Your Order and Pay");
	}

	public void updateOrdersTable(String product, String customer, String order_quantity) throws SQLException {

		Connection conn = DBUtils.getDBConnection();
		Statement stmt = conn.createStatement();

//		String productIdQuery = "SELECT product_id FROM products WHERE product_name = \"" + product + "\"";
//		String productPriseQuery = "SELECT product_prise FROM products WHERE product_name = \"" + product + "\"";
//		String customerIdQuery = "SELECT customer_id FROM customers WHERE first_name = \"" + customer + "\"";

//		int productId = -1;
//		int customerId = -1;
//		double productPrise = -1;

//		ResultSet productIdQueryRs = stmt.executeQuery(productIdQuery);
//
//		while (productIdQueryRs.next()) {
//			productId = productIdQueryRs.getInt("product_id");
//		}

//		ResultSet productPriseQueryRs = stmt.executeQuery(productPriseQuery);
//
//		while (productPriseQueryRs.next()) {
//			productPrise = productPriseQueryRs.getInt("product_prise");
//		}

//		ResultSet customerIdQueryRs = stmt.executeQuery(customerIdQuery);
//
//		while (customerIdQueryRs.next()) {
//			customerId = customerIdQueryRs.getInt("customer_id");
//		}

		int productId = getProductID(stmt, product);
		int customerId = getCustomerID(stmt, customer);
		double productPrise = getProductPrise(stmt, product);

		double total_amount = productPrise * Integer.parseInt(order_quantity);

		if (productId != -1 && productPrise != -1 && customerId != -1) {

			String insertQuery = "INSERT INTO orders(product_id, customer_id, order_quantity, total_amount) VALUES ("
					+ productId + ", " + customerId + ", " + order_quantity + ", " + total_amount + ")";

			stmt.execute(insertQuery);
		}

		conn.close();
	}

	// Helper functions to get Product Id from products table
	public int getProductID(Statement stmt, String pName) throws SQLException {

		int productId = -1;

		String productIdQuery = "SELECT product_id FROM products WHERE product_name = \"" + pName + "\"";

		ResultSet productIdQueryRs = stmt.executeQuery(productIdQuery);

		while (productIdQueryRs.next()) {
			productId = productIdQueryRs.getInt("product_id");
		}

		return productId;
	}

	// Helper function to get Customer Id from customer table
	public int getCustomerID(Statement stmt, String cName) throws SQLException {

		int customerId = -1;

		String customerIdQuery = "SELECT customer_id FROM customers WHERE first_name = \"" + cName + "\"";

		ResultSet customerIdQueryRs = stmt.executeQuery(customerIdQuery);

		while (customerIdQueryRs.next()) {
			customerId = customerIdQueryRs.getInt("customer_id");
		}

		return customerId;
	}

	// Helper function to get Product prose for products table
	public double getProductPrise(Statement stmt, String pName) throws SQLException {

		double productPrise = -1;

		String productPriseQuery = "SELECT product_prise FROM products WHERE product_name = \"" + pName + "\"";

		ResultSet productPriseQueryRs = stmt.executeQuery(productPriseQuery);

		while (productPriseQueryRs.next()) {
			productPrise = productPriseQueryRs.getInt("product_prise");
		}

		return productPrise;
	}

}
