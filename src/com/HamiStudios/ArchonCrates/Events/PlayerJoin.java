package com.HamiStudios.ArchonCrates.Events;

import com.HamiStudios.ArchonCrates.API.Enums.PlayerDataType;
import com.HamiStudios.ArchonCrates.API.Objects.ACPlayer;
import com.HamiStudios.ArchonCrates.Files.PlayerData;
import com.HamiStudios.ArchonCrates.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

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
		// If it doesn't exist, create it (New players)
		if(!PlayerData.playerExists(new ACPlayer(event.getPlayer()))) {
			// Creates the players data file
			PlayerData.create(new ACPlayer(event.getPlayer()));
		} else {
			// If it exists, check for name changes

			// Checks for if the username has changed
			String passedName = (String) PlayerData.get(new ACPlayer(event.getPlayer()), PlayerDataType.USERNAME);

			if(!passedName.equalsIgnoreCase(event.getPlayer().getName())) {
				// Creates a new instance of the ACPlayer
				ACPlayer player = new ACPlayer(event.getPlayer());

				// Sets current usernames
				PlayerData.addPassedName(player, passedName);
			}
		}
	}

}