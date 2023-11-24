package com.amazon.testcases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.amazon.utilities.DBUtils;

public class TC_DataBaseTest {
	
 	@Test
	public void testMaxPriceQuery() throws SQLException {
 		
 		Connection conn = DBUtils.getDBConnection();
 		Statement stmt = conn.createStatement();
 		
 		String res = getMaxPriceProduct(stmt);
 		Assert.assertEquals(res, "tv");
 		
	}
 	
 	@Test
 	public void testMostSoldOutProductQuery() throws SQLException{
 		
 		Connection conn = DBUtils.getDBConnection();
 		Statement stmt = conn.createStatement();
 		
 		String res = getMaxSoldProduct(stmt);
 		Assert.assertEquals(res, "football");
 		
 	}
 	
 	@Test(enabled = false)
 	public void testDisplayOrderList() throws SQLException {
 		
 		Connection conn = DBUtils.getDBConnection();
 		Statement stmt = conn.createStatement();
 		
 		displayOrderList(stmt);
 		
 		conn.close();
 		
 	}
	
 	// Function is used to execute query which fetches total order list from amazondb data base
	public static void displayOrderList(Statement stmt) throws SQLException {
		
		String query = "SELECT orders.order_id, customers.first_name, products.product_name, products.product_prise, orders.order_quantity, orders.total_amount \r\n"
				+ "	FROM customers \r\n"
				+ "    LEFT JOIN orders \r\n"
				+ "    ON customers.customer_id = orders.customer_id\r\n"
				+ "    LEFT JOIN products\r\n"
				+ "    ON products.product_id = orders.product_id";
		
		ResultSet res = stmt.executeQuery(query);
		
		System.out.println("order_id, first_name, product_name, product_prise, order_quantity, total_amount");
		System.out.println("-------------------------------------------------------------------------------");
		while (res.next()) {
			
			int oId = res.getInt("order_id");
			String fName = res.getString("first_name");
			String pName = res.getString("product_name");
			double productPrise = res.getDouble("product_prise");
			int orderQuantity = res.getInt("order_quantity");
			double totalAmount = res.getDouble("total_amount");
			System.out.println(oId + ", " + fName + ", " + pName + ", " + productPrise + ", " + orderQuantity + ", " + totalAmount);
		}
		System.out.println("-------------------------------------------------------------------------------\n");
	}
	
	// Function is used to execute query which fetches product which has highest price in products table
	public static String getMaxPriceProduct(Statement stmt) throws SQLException {
		
		String query = "SELECT * FROM products\r\n"
				+ "	WHERE product_prise = (\r\n"
				+ "	SELECT MAX(product_prise) FROM products)";
		
		ResultSet res = stmt.executeQuery(query);
		String productName = "";
		
//		System.out.println("product_id, product_name, product_prise");
//		System.out.println("---------------------------------------");
		while (res.next()) {
			
			int pId = res.getInt("product_id");
			String pName = res.getNString("product_name");
			productName = pName;
			double productPrise = res.getDouble("product_prise");
			
//			System.out.println(pId + ", " + pName + ", " + productPrise);
		}
//		System.out.println("---------------------------------------\n");
		
		return productName;
	}
	
	// Function is used to execute query which fetches maximum sold product from amazondb data base
	public static String getMaxSoldProduct(Statement stmt) throws SQLException {
		
		String query = "SELECT product_name, SUM(order_quantity) AS total_sold\r\n"
				+ "	FROM products\r\n"
				+ "    LEFT JOIN orders\r\n"
				+ "    ON products.product_id = orders.product_id\r\n"
				+ "    GROUP BY products.product_name\r\n"
				+ "    ORDER BY total_sold DESC\r\n"
				+ "    LIMIT 1";
		
		ResultSet res = stmt.executeQuery(query);
		String productName = "";
		
//		System.out.println("product_name, total_sold");
//		System.out.println("------------------------");
		while (res.next()) {
			
			String pName = res.getNString("product_name");
			productName = pName;
			int tSold = res.getInt("total_sold");
			
//			System.out.println(pName + ", " + tSold);
		}
//		System.out.println("------------------------\n");
		
		return productName;
	}
}
