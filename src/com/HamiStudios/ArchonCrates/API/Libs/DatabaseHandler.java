package com.HamiStudios.ArchonCrates.API.Libs;

import com.HamiStudios.ArchonCrates.API.Enums.Database;
import com.HamiStudios.ArchonCrates.API.Enums.Tables;
import org.bukkit.Bukkit;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHandler {

	// Execute a query and get a boolean of table modification back
	public static boolean execute(Database database, Tables table, String query) {

		try {
			// Get database connection
			Connection connection = DriverManager.getConnection("jdbc:sqlite:plugins/ArchonCrates/data/" + database.getDatabaseName() + ".db");

			// Create the table if it doesn't exists
			Statement statement = connection.createStatement();
			statement.execute(table.getCreateStatement());

			// Insert the crate into the DB
			statement = connection.createStatement();

			// Executes the query
			boolean inserted = statement.execute(query);

			// Closes the connection
			connection.close();

			// Returns the right value
			if(inserted) { return true; }
			return false;

		} catch(SQLException e) {
			e.printStackTrace();

			// Post error in console if any SQL errors occur
			Console console = new Console(Bukkit.getConsoleSender());
			console.space();
			console.error("&cThere was an error reading a database file.");
			console.log("                      &7File Path: /data/" + database.getDatabaseName() + ".db", false);
			console.space();
		}

		return false;
	}

	// Execute a query and get results back
	public static ArrayList<HashMap<String, Object>> executeQuery(Database database, Tables table, String query, String[] columns) {

		try {
			// Get database connection
			Connection connection = DriverManager.getConnection("jdbc:sqlite:plugins/ArchonCrates/data/" + database.getDatabaseName() + ".db");

			// Create the table if it doesn't exists
			Statement statement = connection.createStatement();
			statement.execute(table.getCreateStatement());

			// Insert the crate into the DB
			statement = connection.createStatement();

			// Executes the query
			ResultSet results = statement.executeQuery(query);

			ArrayList<HashMap<String, Object>> rows = new ArrayList<>();

			while(results.next()) {
				HashMap<String, Object> row = new HashMap<>();

				for (String column : columns) {
					row.put(column, results.getObject(column));
				}

				rows.add(row);
			}

			// Closes the connection
			connection.close();

			// Returns the results
			return rows;

		} catch(SQLException e) {
			// Post error in console if any SQL errors occur
			Console console = new Console(Bukkit.getConsoleSender());
			console.space();
			console.error("&cThere was an error reading a database file.");
			console.log("                      &7File Path: /data/" + database.getDatabaseName() + ".db", false);
			console.space();
		}

		return null;
	}

	public static boolean createDatabase(Database database) {
		try {
			// Get database connection
			Connection connection = DriverManager.getConnection("jdbc:sqlite:plugins/ArchonCrates/data/" + database.getDatabaseName() + ".db");

			// Closes the connection
			connection.close();

			// Returns the results
			return true;

		} catch(SQLException e) {
			// Post error in console if any SQL errors occur
			Console console = new Console(Bukkit.getConsoleSender());
			console.space();
			console.error("&cThere was an error reading a database file.");
			console.log("                      &7File Path: /data/" + database.getDatabaseName() + ".db", false);
			console.space();
		}

		return false;
	}

	public static boolean createTable(Database database, Tables table) {
		try {
			// Get database connection
			Connection connection = DriverManager.getConnection("jdbc:sqlite:plugins/ArchonCrates/data/" + database.getDatabaseName() + ".db");

			// Create the table if it doesn't exists
			Statement statement = connection.createStatement();
			statement.execute(table.getCreateStatement());

			// Closes the connection
			connection.close();

			// Returns the results
			return true;

		} catch(SQLException e) {
			// Post error in console if any SQL errors occur
			Console console = new Console(Bukkit.getConsoleSender());
			console.space();
			console.error("&cThere was an error reading a database file.");
			console.log("                      &7File Path: /data/" + database.getDatabaseName() + ".db", false);
			console.space();
		}

		return false;
	}

}
