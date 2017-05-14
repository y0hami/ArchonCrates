package com.HamiStudios.ArchonCrates.Events;

import com.HamiStudios.ArchonCrates.API.Enums.LanguageType;
import com.HamiStudios.ArchonCrates.API.Enums.Permissions;
import com.HamiStudios.ArchonCrates.API.Objects.ACPlayer;
import com.HamiStudios.ArchonCrates.API.Objects.Crate;
import com.HamiStudios.ArchonCrates.API.libs.LanguageManager;
import com.HamiStudios.ArchonCrates.Files.CrateData;
import com.HamiStudios.ArchonCrates.Main;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

	// Creates an instance of the Main class
	private Main main;

	public BlockBreak(Main main) {
		// Sets the instance of the Main class
		this.main = main;

		// Registers the event
		this.main.getServer().getPluginManager().registerEvents(this, this.main);
	}

	// On Block Placed
	@EventHandler
	public void onBlockBlockBreak(BlockBreakEvent event) {

		// Create instance of ACPlayer
		ACPlayer player = new ACPlayer(event.getPlayer());

		// Create instance of CrateData and the broken block
		CrateData crateData = new CrateData();
		Block block = event.getBlock();

		// Get the crate at the block location
		String crateID = crateData.getCrate(block.getX(), block.getY(), block.getZ(), block.getWorld());

		// If the block is a crate
		if(crateID != null) {

			// If the crate is a virtual crate
			if(crateID.equalsIgnoreCase("VIRTUAL_CRATE")) {

				// Check if the player has the permission to create crates (If they can create them they can remove them)
				if(player.hasPermission(Permissions.CREATE_CRATE.value())) {

					// And the player is sneaking
					if (player.getPlayer().isSneaking()) {
						// Remove the crate
						crateData.removeCrate(block.getX(), block.getY(), block.getZ(), block.getWorld());

						block.setType(Material.AIR);

						// Tell the player the crate is removed
						player.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.EVENT_CRATE_REMOVED));
					} else {
						// Tell the player they need to sneak to remove crates
						player.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.EVENT_CRATE_SNEAK_TO_REMOVE));
					}

				}

				// If the block is a crate stop the player from breaking it
				event.setCancelled(true);

			} else {

				// Create an instance of the crate
				Crate crate = new Crate(crateID);

				// If the crate is valid
				if(crate.valid()) {

					// Check if the player has the permission to create crates (If they can create them they can remove them)
					if(player.hasPermission(Permissions.CREATE_CRATE.value())) {

						// And the player is sneaking
						if (player.getPlayer().isSneaking()) {
							// Remove the crate
							crateData.removeCrate(block.getX(), block.getY(), block.getZ(), block.getWorld());

							block.setType(Material.AIR);

							// Tell the player the crate is removed
							player.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.EVENT_CRATE_REMOVED));
						} else {
							// Tell the player they need to sneak to remove crates
							player.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.EVENT_CRATE_SNEAK_TO_REMOVE));
						}

					}

					// If the block is a crate stop the player from breaking it
					event.setCancelled(true);

				}

			}

		}

	}

}
