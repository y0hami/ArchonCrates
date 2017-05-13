package com.HamiStudios.ArchonCrates;

import com.HamiStudios.ArchonCrates.API.Enums.Files;
import com.HamiStudios.ArchonCrates.API.libs.Console;
import com.HamiStudios.ArchonCrates.API.libs.Glow;
import com.HamiStudios.ArchonCrates.Commands.Commands;
import com.HamiStudios.ArchonCrates.Commands.TabCompleter;
import com.HamiStudios.ArchonCrates.Events.PlayerJoin;
import com.HamiStudios.ArchonCrates.Files.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		// Create instance of the server console
		Console console = new Console(this);

		console.space();
		console.space();

		// Announce the plugin is starting in the server console
		console.notice("&fStarting...");

		console.notice("&fArchonCrates Version: " + Bukkit.getPluginManager().getPlugin("ArchonCrates").getDescription().getVersion());


		console.space();
		console.space();


		// Check if "data" directory structure exists
		console.notice("&fChecking if data directory structure exists...");
		console.space();

		File dataFolder = new File("plugins/ArchonCrates/data");
		if(!dataFolder.exists()) {
			console.warning("&fData directory is missing, creating it...");
			// If it doesn't create it
			dataFolder.mkdirs();
			console.notice("&fSuccessfully created 'data' directory.");
		} else {
			console.notice("&fData directory exists.");
		}

		console.space();

		File playerDataFolder = new File("plugins/ArchonCrates/data/players");
		if(!playerDataFolder.exists()) {
			console.warning("&fPlayer Data directory is missing, creating it...");
			// If it doesn't create it
			playerDataFolder.mkdirs();
			console.notice("&fSuccessfully created 'data/players' directory.");
		} else {
			console.notice("&fPlayer Data directory exists.");
		}


		console.space();
		console.space();


		// Start checking if files exist
		console.notice("&fChecking configuration files...");
		
		// List of missing files
		ArrayList<Files> missingFiles = new ArrayList<>();
		
		// Total files
		int totalFiles = 6;
		
		// Check if crates.yml exists
		Crates cratesFile = new Crates();
		if(!cratesFile.exists()) {
			missingFiles.add(Files.CRATES);
		}
		
		// Check if keys.yml exists
		Keys keysFile = new Keys();
		if(!keysFile.exists()) {
			missingFiles.add(Files.KEYS);
		}
		
		// Check if prizes.yml exists
		Prizes prizesFile = new Prizes();
		if(!prizesFile.exists()) {
			missingFiles.add(Files.PRIZES);
		}
		
		// Check if language.yml exists
		Language languageFile = new Language();
		if(!languageFile.exists()) {
			missingFiles.add(Files.LANGUAGE);
		}

		// Check if data/crates.json exists
		CrateData crateData = new CrateData();
		if(!crateData.exists()) {
			missingFiles.add(Files.CRATE_DATA);
		}

		/* If any files are missing announce how many are missing out of the total of files
		 * and then announce the creation of the new files
		 */
		if(missingFiles.size() > 0) {
			// Announcement of how many files are missing
			console.warning("&fThere are &5" + missingFiles.size() + "/" + totalFiles + " &fmissing files.");
			console.space();
			console.notice("&fCreating missing files...");
			console.space();
			for (Files file : missingFiles) {
				// For each file which is missing announce its being created
				console.log("  &7- &fCreating &5" + file.getFileName(), false);
				
				// Find out what file is missing then execute the create method
				switch (file.getName()) {
					case "Crates":
						cratesFile.create();
						break;
					case "Keys":
						keysFile.create();
						break;
					case "Prizes":
						prizesFile.create();
						break;
					case "Language":
						languageFile.create();
						break;
					case "CrateData":
						crateData.create();
						break;
					default:
						break;
				}
				
				// Announce that the file has been created
				console.log("  &7- &fCreated &5" + file.getFileName(), false);
				console.space();
			}
			
			console.notice("&fAll &5" + missingFiles.size() + " &fmissing files have been created.");
		} else {
			console.notice("&fAll file checks where performed with no errors.");
		}
		console.space();
		console.space();

		console.notice("&fRegistering Commands...");

		// Register the commands
		this.getCommand("archoncrates").setExecutor(new Commands());
		this.getCommand("archoncrates").setTabCompleter(new TabCompleter());

		console.notice("&fCommands registered.");


		console.space();
		console.space();


		// Register Glow object
		Glow glow = new Glow(99);
		glow.registerGlow();
		
		// Register Events

		console.notice("&fRegistering events...");

		// Events
		new PlayerJoin(this);

		console.notice("&fEvents registered.");

		console.space();
		console.space();
	}

	@Override
	public void onDisable() {
		Console console = new Console(this);
		console.notice("&fShutting down...");
	}
	
}
