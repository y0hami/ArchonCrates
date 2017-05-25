package com.HamiStudios.ArchonCrates.Events;

import com.HamiStudios.ArchonCrates.API.Objects.ACPlayer;
import com.HamiStudios.ArchonCrates.Files.PlayerData;
import com.HamiStudios.ArchonCrates.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {

	// Creates an instance of the Main class
	private Main main;

	public PlayerLeave(Main main) {
		// Sets the instance of the Main class
		this.main = main;

		// Registers the event
		this.main.getServer().getPluginManager().registerEvents(this, this.main);
	}

	// On Player Leave
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		// Get instance of ACPlayer
		ACPlayer player = new ACPlayer(event.getPlayer());

		// If it doesn't exist, create it (New players)
		if(!PlayerData.playerExists(player)) {
			// Creates the players data file
			PlayerData.create(player);
		}


		if(this.main.getOperationsManager().isCrateRoller(player)) {
			this.main.getOperationsManager().removeCrateRoller(player);
		}
		if(this.main.getOperationsManager().isKeyGiver(player)) {
			this.main.getOperationsManager().removeKeyGiver(player);
		}
		if(this.main.getOperationsManager().isVirtualKeySelector(player)) {
			this.main.getOperationsManager().removeVirtualKeySelector(player);
		}
	}

}
