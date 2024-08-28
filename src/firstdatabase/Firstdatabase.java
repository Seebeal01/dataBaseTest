package firstdatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mysql.cj.protocol.Resultset;

public class Firstdatabase {

//	WebDriver drivere = new ChromeDriver();
	Connection con;
	Statement stmt;
	ResultSet rs;
	Random rand = new Random();
	int randomnumber = rand.nextInt(500);


	@BeforeTest
	public void mystub() throws SQLException {

		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "Seebeal 182001");

	}

	@Test(priority = 1)
	public void addToDataBase() throws SQLException {
	
		System.out.println(randomnumber);
		String queryData = "insert into customers (customerNumber,customerName,contactLastName,contactFirstName,phone,addressLine1,addressLine2,city,state,postalCode,country,salesRepEmployeeNumber,creditLimit)VALUES ("
				+ randomnumber
				+ " , ' Tech Innovations ', 'Smith', 'Alice', '212-555-1234', '123 Main Street', 'Suite 200', 'New York', 'NY', '10001', 'USA', 1621, 50000.00)";
		stmt = con.createStatement();

		int rowInserted = stmt.executeUpdate(queryData);

		System.out.println(rowInserted);

	}

	@Test(priority = 2)
	public void updateData() throws SQLException {
		String queryData = "update customers set contactFirstName ='seebeal' where customerNumber = " + randomnumber;

		stmt = con.createStatement();

		int NumberOfRowUpdated = stmt.executeUpdate(queryData);

		System.out.println(NumberOfRowUpdated);

	}

	@Test(priority = 3)
	public void databace() throws SQLException {
		stmt = con.createStatement();
		rs = stmt.executeQuery("Select * FROM customers where customerNumber=103");

		while (rs.next()) {
			int customerNumberInTheDataBase = rs.getInt("customerNumber");
			String customerNameInTheDataBase = rs.getNString("customerName");
			System.out.println(customerNumberInTheDataBase);
			System.out.println(customerNameInTheDataBase);

		}
	}
	
	@Test(priority = 4)

	public void DeleteData() throws SQLException {
		String queryData = "delete from customers where customerNumber = "+randomnumber;

		stmt = con.createStatement();

		int numberofRowdeleted = stmt.executeUpdate(queryData);

		System.out.println(numberofRowdeleted);
	}


}
