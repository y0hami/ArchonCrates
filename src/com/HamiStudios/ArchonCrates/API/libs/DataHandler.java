package com.HamiStudios.ArchonCrates.API.libs;

import com.HamiStudios.ArchonCrates.API.Enums.Database;
import com.HamiStudios.ArchonCrates.API.Enums.DatabaseType;
import com.HamiStudios.ArchonCrates.API.Enums.Table;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataHandler {

	// Method to insert data into a remote MySQL server or a local SQLite database
	public static boolean insertData(DatabaseType databaseType, Database database, Table table, ArrayList<String> fieldNames, ArrayList<Object> data) {

		if(databaseType == DatabaseType.MYSQL) {
			// Insert into remote MySQL server

		} else if(databaseType == DatabaseType.SQLITE) {
			// Insert into local SQLite database

			try {
				// Create connection to the database
				Connection connection = DriverManager.getConnection("jdbc:sqlite:plugins/ArchonCrates/data/" + database.getName() + ".db");

				// Create statement
				Statement stmt = connection.createStatement();

				// Execute create statement (Only creates the table if it doesn't exist)
				stmt.execute(table.getCreateStatement());


				// Generate insert statement from the fields and data provided
				String insertQuery = "INSERT INTO " + table.getName() + " (";

				// For each field name add it to the fields list in the query
				for (String field : fieldNames) {
					insertQuery += field + ", ";
				}

				// Remove the trailing ", " and add the values key word
				insertQuery = insertQuery.substring(0, insertQuery.length()-2) + ") VALUES (";

				// For each data value add it to the data list in the query
				for (Object value : data) {
					insertQuery += value + ", ";
				}

				// Remove the trailing ", " and add the closing ");"
				insertQuery = insertQuery.substring(0, insertQuery.length()-2) + ");";

				// Create a new statement
				stmt = connection.createStatement();

				// Execute the statement and get return count
				int count = stmt.executeUpdate(insertQuery);

				// Close the connection
				connection.close();

				// If the count is grater then 0 it inserted
				if(count > 0) {
					// If inserted return true
					return true;
				} else {
					// If failed to insert return false
					return false;
				}
			} catch(SQLException e) {
				// If an error occurs announce it in the server console and disable the plugin
				Console console = new Console(Bukkit.getConsoleSender());
				console.error("&cError connecting to SQLite database '" + database.getName() + "'. Disabling the plugin.");
				console.space();
				Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin("ArchonCrates"));
				return false;
			}
		}

		return false;
	}

}
