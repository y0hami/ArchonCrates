package com.HamiStudios.ArchonCrates;

import com.HamiStudios.ArchonCrates.API.Enums.Files;
import com.HamiStudios.ArchonCrates.API.Libs.Console;
import com.HamiStudios.ArchonCrates.API.Libs.Glow;
import com.HamiStudios.ArchonCrates.API.Libs.OperationsManager;
import com.HamiStudios.ArchonCrates.Commands.Commands;
import com.HamiStudios.ArchonCrates.Commands.TabCompleter;
import com.HamiStudios.ArchonCrates.Events.*;
import com.HamiStudios.ArchonCrates.Files.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;

public class Main extends JavaPlugin {

	// Instance of the OperationsManager
	private OperationsManager operationsManager;


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

		// Start checking if files exist
		console.notice("&fChecking configuration files...");
		
		// List of missing files
		ArrayList<Files> missingFiles = new ArrayList<>();
		
		// Total files
		int totalFiles = 9;
		
		// Check if crates.yml exists
		Crates cratesFile = new Crates();
		if(!cratesFile.exists()) {
			missingFiles.add(Files.CRATES);
		}

		// Check if virtual crates.yml exists
		VirtualCrates virtualCrates = new VirtualCrates();
		if(!virtualCrates.exists()) {
			missingFiles.add(Files.VIRTUAL_CRATES);
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

		// Check if data/crates.db exists
		if(!CrateData.exists()) {
			missingFiles.add(Files.CRATE_DATA);
		}

		// Check if data/players.db exists
		if(!PlayerData.exists()) {
			missingFiles.add(Files.PLAYER_DATA);
		}

		// Check if vc layout.json exists
		if(!VCLayout.exists()) {
			missingFiles.add(Files.VIRTUAL_CRATE_LAYOUT);
		}

		// Check if key drops.yml exists
		KeyDrop keyDrop = new KeyDrop();
		if(!keyDrop.exists()) {
			missingFiles.add(Files.KEY_DROPS);
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
				switch (file) {
					case CRATES:
						cratesFile.create();
						break;
					case VIRTUAL_CRATES:
						virtualCrates.create();
						break;
					case KEYS:
						keysFile.create();
						break;
					case PRIZES:
						prizesFile.create();
						break;
					case LANGUAGE:
						languageFile.create();
						break;
					case CRATE_DATA:
						CrateData.createFile();
						break;
					case PLAYER_DATA:
						PlayerData.createFile();
						break;
					case VIRTUAL_CRATE_LAYOUT:
						VCLayout.createFile();
						break;
					case KEY_DROPS:
						keyDrop.create();
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
		this.getCommand("archoncrates").setExecutor(new Commands(this));
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
		new PlayerLeave(this);
		new BlockPlace(this);
		new BlockBreak(this);
		new BlockClick(this);
		new InventoryEvents(this);
		new PlayerInteraction(this);
		new MobDeath(this);

		console.notice("&fEvents registered.");


		console.space();
		console.space();


		console.notice("&fStarting operation manager...");
		this.operationsManager = new OperationsManager();
		console.notice("&fOperation manager started.");


		console.space();
		console.space();
	}

	@Override
	public void onDisable() {
		Console console = new Console(this);
		console.notice("&fShutting down...");
	}


	// Get the OperationsManager
	public OperationsManager getOperationsManager() { return this.operationsManager; }
	
}
