package com.HamiStudios.ArchonCrates;

import com.HamiStudios.ArchonCrates.API.Enums.Files;
import com.HamiStudios.ArchonCrates.API.libs.Console;
import com.HamiStudios.ArchonCrates.API.libs.Glow;
import com.HamiStudios.ArchonCrates.Commands.Commands;
import com.HamiStudios.ArchonCrates.Commands.TabCompleter;
import com.HamiStudios.ArchonCrates.Files.Crates;
import com.HamiStudios.ArchonCrates.Files.Keys;
import com.HamiStudios.ArchonCrates.Files.Language;
import com.HamiStudios.ArchonCrates.Files.Prizes;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		// Create instance of the server console
		Console console = new Console(this);
		
		// Announce the plugin is starting in the server console
		console.notice("&fStarting...");

		console.notice("&fArchonCrates Version: " + Bukkit.getPluginManager().getPlugin("ArchonCrates").getDescription().getVersion());

		console.space();
		
		// Start checking if files exist
		console.notice("&fChecking files...");
		
		// List of missing files
		ArrayList<Files> missingFiles = new ArrayList<>();
		
		// Total files
		int totalFiles = 4;
		
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


		// Check if "data" directory exists
		File dataFolder = new File("plugins/ArchonCrates/data");
		if(!dataFolder.exists()) {
			// If it doesn't create it
			dataFolder.mkdirs();
		}


		// Register the commands
		this.getCommand("archoncrates").setExecutor(new Commands());
		this.getCommand("archoncrates").setTabCompleter(new TabCompleter());
		
		// Register Glow object
		Glow glow = new Glow(99);
		glow.registerGlow();
		
		// Register Events

		// Events
	}

	@Override
	public void onDisable() {
		Console console = new Console(this);
		console.notice("&fShutting down...");
	}
	
}
