package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogriddb", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//read
	public String readPayment()  
	{   
		String output = ""; 
	
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border=\"1\">"
					+ "		<tr>"
					+ "			<th>Payment ID</th>"
					+ "			<th>Account ID</th>"
					+ "			<th>Amount</th>"
					+ "			<th>Method Of Payment</th>"
					+ "			<th>Date of Payment</th>"
					+ "		</tr>";
	 
			String query = "SELECT * FROM payments";
			Statement stat = con.createStatement();
			ResultSet rSet = stat.executeQuery(query);
	 
			// iterate through the rows in the result set    
			while(rSet.next()) {
				String paymentID = Integer.toString(rSet.getInt("paymentID"));
				String accountID = Integer.toString(rSet.getInt("accountID"));
				String amount = Double.toString(rSet.getDouble("amount"));
				String payMethod = rSet.getString("payMethod");
				String payDate = rSet.getString("payDate");

				// Add into the HTML table 
				output += "<tr><td><input id='hidpaymentIDUpdate' name='hidpaymentIDUpdate' type='hidden' value='" + paymentID + "'>" + accountID + "</td>";
				output += 	"<td>" + amount +  "</td>";
				output += 	"<td>" + payMethod +  "</td>";
				output += 	"<td>" + payDate +  "</td>";
				output += 	"</tr>";

				// buttons     
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-paymentID='" + paymentID + "'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-paymentID='" + paymentID + "'>" + "</td></tr>"; 
		
			}
			con.close(); 
	 
			// Complete the HTML table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the Payment.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
	//insert payment data
	public String insertPayment(String accountID, String amount, String payMethod, String payDate)  
	{   
		String output = ""; 
	 
		try
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to database";
			} 
	 
			// create a prepared statement 
			String query = "INSERT INTO payments (`paymentID`, `accountID`,`amount`, `payMethod`, `payDate` )\"\r\n"
					+ "							+ \"VALUES (?,?,?,?,?)"; 
	 
	 
			PreparedStatement prepStat = con.prepareStatement(query);
	 
			// binding values    
			prepStat.setInt(1, 0);
			prepStat.setInt(2, Integer.parseInt(accountID));
			prepStat.setDouble(3, Double.parseDouble(amount));
			prepStat.setString(4, payMethod);
			prepStat.setString(5, payDate);
			
			// execute the statement    
			prepStat.execute();    
			con.close(); 
	   
			String newPayment = readPayment(); 
//			output =  "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";   
			output = "Payment Inserted Successfully!";

		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the New Payment.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	}
	
	//update
	
	public String updatePayment(String paymentID, String accountID, String amount, String payMethod, String payDate)    
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to database.";
			} 
	 
			// create a prepared statement    
			String query = "UPDATE payments SET accountID=?, amount=?, payMethod=?, payDate=? where paymentID=?"; 
	 
			PreparedStatement prepStat = con.prepareStatement(query);
			
			//binding values
			prepStat.setInt(1, Integer.parseInt(accountID));
			prepStat.setDouble(2, Double.parseDouble(amount));
			prepStat.setString(3, payMethod);
			prepStat.setString(4, payDate);
			prepStat.setInt(5, Integer.parseInt(ID));
			
			 // execute the statement
			prepStat.execute();    
			con.close(); 
	 
			String newPayment = readPayment();    
			output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the Payment.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	} 
	
	//delete
	public String deletePayment(String paymentID)   
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 
			} 
	 
			// create a prepared statement    
			String query = "DELETE FROM payments WHERE paymentID=?";  
	 
			PreparedStatement prepStat = con.prepareStatement(query); 
	 
			// binding values    
			prepStat.setInt(1, Integer.parseInt(paymentID)); 
	 
			// execute the statement    
			prepStat.execute();    
			con.close(); 
	 
			String newPayment = readPayment();  
			    
			output = "{\"status\":\"success\", \"data\": \"" +  newPayment + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the payment.\"}";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
}
