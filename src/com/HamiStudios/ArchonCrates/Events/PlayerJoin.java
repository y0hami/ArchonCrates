package com.HamiStudios.ArchonCrates.Events;

import com.HamiStudios.ArchonCrates.API.Enums.PlayerDataType;
import com.HamiStudios.ArchonCrates.API.Exceptions.DataFileException;
import com.HamiStudios.ArchonCrates.API.Objects.ACPlayer;
import com.HamiStudios.ArchonCrates.API.libs.Console;
import com.HamiStudios.ArchonCrates.Files.PlayerData;
import com.HamiStudios.ArchonCrates.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;

public class PlayerJoin implements Listener {

	// Creates an instance of the Main class
	private Main main;

	public PlayerJoin(Main main) {
		// Sets the instance of the Main class
		this.main = main;

		// Registers the event
		this.main.getServer().getPluginManager().registerEvents(this, this.main);
	}

	// On Player Join
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		// Creates an instance of the console
		Console console = new Console(this.main);

		// Gets the player data file
		PlayerData playerData = new PlayerData(event.getPlayer());

		// If it doesn't exist, create it (New players)
		if(!playerData.exists()) {
			// Creates the players data file
			playerData.create();
		} else {
			// If it exists, check for name changes
			try {
				// Checks for if the username has changed
				if(!playerData.get(PlayerDataType.USERNAME).toString().equalsIgnoreCase(event.getPlayer().getName())) {
					// Creates a new instance of the ACPlayer
					ACPlayer player = new ACPlayer(event.getPlayer());
					// Sets current usernames
					player.setPassedNames((ArrayList<String>) playerData.get(PlayerDataType.PASSED_NAMES));
					// Adds the new username
					player.addToPassedUsernames(playerData.get(PlayerDataType.USERNAME).toString());

					// Creates a new instance of the player data
					playerData = new PlayerData(player);

					// Recreates the file to set the new data
					playerData.create();
				}
			} catch(DataFileException e) { }
		}

	}

}