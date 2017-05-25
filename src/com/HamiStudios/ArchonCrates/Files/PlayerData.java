package com.HamiStudios.ArchonCrates.Files;

import com.HamiStudios.ArchonCrates.API.Enums.Database;
import com.HamiStudios.ArchonCrates.API.Enums.PlayerDataType;
import com.HamiStudios.ArchonCrates.API.Enums.Tables;
import com.HamiStudios.ArchonCrates.API.Libs.DatabaseHandler;
import com.HamiStudios.ArchonCrates.API.Objects.ACPlayer;
import com.HamiStudios.ArchonCrates.API.Objects.Key;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayerData {

	// Create a new player in the players.db database
	public static boolean create(ACPlayer player) {
		return DatabaseHandler.execute(Database.PLAYERS_DATA, Tables.PLAYERS_DATA, "INSERT INTO `players`" +
				"(`UUID`, `CURRENT_NAME`)" +
				"VALUES" +
				"('" + player.getUUID() + "', '" + player.getName() + "');");
	}

	// Get a player from the players.db database
	public static Object get(ACPlayer player, PlayerDataType playerDataType) {
		ArrayList<HashMap<String, Object>> results;

		switch (playerDataType) {
			case USERNAME:
				results = DatabaseHandler.executeQuery(Database.PLAYERS_DATA, Tables.PLAYERS_DATA,
						"SELECT * FROM `players` WHERE `UUID`='" + player.getUUID() + "'", new String[]{"CURRENT_NAME"});

				if(results.size() == 1) {
					return results.get(0).get("CURRENT_NAME");
				}
				break;
			case UUID:
				results = DatabaseHandler.executeQuery(Database.PLAYERS_DATA, Tables.PLAYERS_DATA,
						"SELECT * FROM `players` WHERE `CURRENT_NAME`='" + player.getName() + "'", new String[]{"UUID"});

				if(results.size() == 1) {
					return results.get(0).get("UUID");
				}
				break;
			case PASSED_NAMES:
				ArrayList<String> passedNames = new ArrayList<>();

				results = DatabaseHandler.executeQuery(Database.PLAYERS_DATA, Tables.PLAYERS_NAMES,
						"SELECT * FROM `passedNames` WHERE `UUID`='" + player.getUUID() + "'", new String[]{"NAME"});

				for (HashMap<String, Object> row : results) {
					passedNames.add((String) row.get("NAME"));
				}

				return passedNames;
			case VIRTUAL_KEYS:
				HashMap<String, Integer> keys = new HashMap<>();

				results = DatabaseHandler.executeQuery(Database.PLAYERS_DATA, Tables.KEYS_DATA,
						"SELECT * FROM `keys` WHERE `UUID`='" + player.getUUID() + "'", new String[]{"KEY_ID", "AMOUNT"});

				for (HashMap<String, Object> row : results) {
					keys.put((String) row.get("KEY_ID"), (Integer) row.get("AMOUNT"));
				}

				return keys;
		}

		return null;
	}

	// Add a new name to the passed names
	public static void addPassedName(ACPlayer player, String passedName) {
		DatabaseHandler.execute(Database.PLAYERS_DATA, Tables.PLAYERS_NAMES, "INSERT INTO `passedNames`" +
				"(`UUID`, `NAME`)" +
				"VALUES" +
				"('" + player.getUUID() + "', '" + passedName + "');");

		DatabaseHandler.execute(Database.PLAYERS_DATA, Tables.PLAYERS_DATA, "UPDATE `players`" +
				" SET `CURRENT_NAME`='" + player.getName() + "';");
	}

	// Add a new virtual key to the players.db
	public static void addVirtualKey(ACPlayer player, Key key, int amount) {
		HashMap<String, Integer> keys = (HashMap<String, Integer>) get(player, PlayerDataType.VIRTUAL_KEYS);

		if(keys.get(key.getID()) != null) {
			DatabaseHandler.execute(Database.PLAYERS_DATA, Tables.KEYS_DATA, "UPDATE `keys`" +
					"SET `AMOUNT`=" + (keys.get(key.getID())+amount) + " WHERE `UUID`='" + player.getUUID() + "' AND `KEY_ID`='" + key.getID() + "';");
		} else {
			DatabaseHandler.execute(Database.PLAYERS_DATA, Tables.KEYS_DATA, "INSERT INTO `keys`" +
					"(`UUID`, `KEY_ID`, `AMOUNT`)" +
					"VALUES" +
					"('" + player.getUUID() + "', '" + key.getID() + "', " + amount + ");");
		}
	}

	// Add a new virtual key to the players.db
	public static void removeVirtualKey(ACPlayer player, Key key, int amount) {
		HashMap<String, Integer> keys = (HashMap<String, Integer>) get(player, PlayerDataType.VIRTUAL_KEYS);

		if(keys.get(key.getID()) != null) {
			if(keys.get(key.getID()) <= 0) {
				DatabaseHandler.execute(Database.PLAYERS_DATA, Tables.KEYS_DATA, "UPDATE `keys`" +
						"SET `AMOUNT`=0 WHERE `UUID`='" + player.getUUID() + "' AND `KEY_ID`='" + key.getID() + "';");
			} else {
				DatabaseHandler.execute(Database.PLAYERS_DATA, Tables.KEYS_DATA, "UPDATE `keys`" +
						"SET `AMOUNT`=" + (keys.get(key.getID())-amount) + " WHERE `UUID`='" + player.getUUID() + "' AND `KEY_ID`='" + key.getID() + "';");
			}
		}
	}

	// Check if a player exists
	public static boolean playerExists(ACPlayer player) {
		ArrayList<HashMap<String, Object>> results = DatabaseHandler.executeQuery(Database.PLAYERS_DATA, Tables.PLAYERS_DATA,
				"SELECT * FROM `players` WHERE `UUID`='" + player.getUUID() + "'", new String[]{"ID"});

		return (results.size() > 0);
	}

	// Create the database file
	public static void createFile() {
		DatabaseHandler.createDatabase(Database.PLAYERS_DATA);

		DatabaseHandler.createTable(Database.PLAYERS_DATA, Tables.PLAYERS_DATA);
		DatabaseHandler.createTable(Database.PLAYERS_DATA, Tables.KEYS_DATA);
		DatabaseHandler.createTable(Database.PLAYERS_DATA, Tables.WIN_DATA);
	}

	// Check if the database file exists
	public static boolean exists() { return new File("plugins/ArchonCrates/data/players.db").exists(); }

}
