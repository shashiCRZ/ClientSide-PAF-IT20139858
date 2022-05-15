package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Electricityboard {
	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogridclient", "root", "");

			// For testing
			System.out.print("Successfully connected");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public String readElectricityboard() {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Electricity Board Type</th>" + "<th>Branch Code</th><th>Location</th>"
					+ "<th>Contact Number</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from electricityboard";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {

				String electricityboardID = Integer.toString(rs.getInt("electricityboardID"));
				String etype = rs.getString("etype");
				String branchCode = rs.getString("branchCode");
				String location = rs.getString("location");
				String contactnumber = Integer.toString(rs.getInt("contactnumber"));

				// Add into the html table

				output += "<tr><td><input id='hidelectricityboardIDUpdate' name='hidelectricityboardIDUpdate' type='hidden' value='"
						+ electricityboardID + "'>" + etype + "</td>";

				output += "<td>" + branchCode + "</td>";
				output += "<td>" + location + "</td>";
				output += "<td>" + contactnumber + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-electricityboardID='"
						+ electricityboardID + "'>" + "</td></tr>";

			}

			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Electricityboard Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// Insert Electricityboard
	public String insertElectricityboard(String etype, String branchCode, String location,
			String contactnumber) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into electricityboard (`electricityboardID`,`etype`,`branchCode`,`location`,`contactnumber`)"
					+ " values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, etype);
			preparedStmt.setString(3, branchCode);
			preparedStmt.setString(4, location);
			preparedStmt.setString(5, contactnumber);

			// execute the statement
			preparedStmt.execute();
			con.close();

			// Create JSON Object to show successful msg.
			String newElectricityboard = readElectricityboard();
			output = "{\"status\":\"success\", \"data\": \"" + newElectricityboard + "\"}";
		} catch (Exception e) {
			// Create JSON Object to show Error msg.
			output = "{\"status\":\"error\", \"data\": \"Error while Inserting Electricityboard.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// Update Electricityboard
	public String updateElectricityboard(String electricityboardID, String etype, String branchCode, String location,
			String contactnumber) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE electricityboard SET etype=?,branchCode=?,location=?,contactnumber=? WHERE electricityboardID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, etype);
			preparedStmt.setString(2, branchCode);
			preparedStmt.setString(3, location);
			preparedStmt.setInt(4, Integer.parseInt(contactnumber));
			preparedStmt.setInt(5, Integer.parseInt(electricityboardID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			// create JSON object to show successful msg
			String newElectricityboard = readElectricityboard();
			output = "{\"status\":\"success\", \"data\": \"" + newElectricityboard + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while Updating Electricityboard Details.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	//Delete 

	public String deleteElectricityboard(String electricityboardID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "DELETE FROM electricityboard WHERE electricityboardID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(electricityboardID));
			// execute the statement
			preparedStmt.execute();
			con.close();

			// create JSON Object
			String newElectricityboard = readElectricityboard();
			output = "{\"status\":\"success\", \"data\": \"" + newElectricityboard + "\"}";
		} catch (Exception e) {
			// Create JSON object
			output = "{\"status\":\"error\", \"data\": \"Error while Deleting Electricityboard.\"}";
			System.err.println(e.getMessage());

		}

		return output;
	}

}
