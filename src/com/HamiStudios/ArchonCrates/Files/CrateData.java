package com.HamiStudios.ArchonCrates.Files;

import com.HamiStudios.ArchonCrates.API.Enums.Database;
import com.HamiStudios.ArchonCrates.API.Enums.Tables;
import com.HamiStudios.ArchonCrates.API.Libs.DatabaseHandler;
import com.HamiStudios.ArchonCrates.API.Objects.ACPlayer;
import com.HamiStudios.ArchonCrates.API.Objects.Crate;
import com.HamiStudios.ArchonCrates.API.Objects.VirtualCrate;
import org.bukkit.World;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class CrateData {

	// Create a new crate in the crate.db database
	public static boolean create(Crate crate, int x, int y, int z, World world, ACPlayer player) {
		return DatabaseHandler.execute(Database.CRATES_DATA, Tables.CRATES_DATA, "INSERT INTO `crates`" +
				"(`CRATE_TYPE`, `X`, `Y`, `Z`, `WORLD`, `CREATOR`)" +
				"VALUES" +
				"('" + crate.getID() + "', '" + x + "', '" + y + "', '" + z + "', '" + world.getName() + "', '" + player.getUUID() + "');");
	}

	// Create a new virtual crate in the crate.db database
	public static boolean create(VirtualCrate crate, int x, int y, int z, World world, ACPlayer player) {
		return DatabaseHandler.execute(Database.CRATES_DATA, Tables.CRATES_DATA, "INSERT INTO `crates`" +
				"(`CRATE_TYPE`, `X`, `Y`, `Z`, `WORLD`, `CREATOR`)" +
				"VALUES" +
				"('VIRTUAL_CRATE', '" + x + "', '" + y + "', '" + z + "', '" + world.getName() + "', '" + player.getUUID() + "');");
	}

	// Removes a crate from the crate.db database
	public static boolean remove(int x, int y, int z, World world) {
		return DatabaseHandler.execute(Database.CRATES_DATA, Tables.CRATES_DATA, "DELETE FROM `crates` WHERE `X`='" + x + "' AND `Y`='" + y + "' AND `Z`='" + z + "' AND `WORLD`='" + world.getName() + "'");
	}

	// Get a crate from the crate.db database
	public static String get(int x, int y, int z, World world) {
		ArrayList<HashMap<String, Object>>  results = DatabaseHandler.executeQuery(Database.CRATES_DATA, Tables.CRATES_DATA, "SELECT * FROM `crates` WHERE `X`='" + x + "' AND `Y`='" + y + "' AND `Z`='" + z + "' AND `WORLD`='" + world.getName() + "'", new String[]{"CRATE_TYPE"});

		if(results.size() == 0) { return (String) results.get(0).get("CRATE_TYPE"); }

		return null;
	}

	// Create the database file
	public static void createFile() {
		DatabaseHandler.createDatabase(Database.CRATES_DATA);
		DatabaseHandler.createTable(Database.CRATES_DATA, Tables.CRATES_DATA);
	}

	// Check if the database file exists
	public static boolean exists() { return new File("plugins/ArchonCrates/data/crates.db").exists(); }

}
