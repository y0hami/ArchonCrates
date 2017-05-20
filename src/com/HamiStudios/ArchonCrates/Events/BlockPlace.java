package com.HamiStudios.ArchonCrates.Events;

import com.HamiStudios.ArchonCrates.API.Enums.LanguageType;
import com.HamiStudios.ArchonCrates.API.Enums.Permissions;
import com.HamiStudios.ArchonCrates.API.Objects.ACPlayer;
import com.HamiStudios.ArchonCrates.API.Objects.Crate;
import com.HamiStudios.ArchonCrates.API.Objects.VirtualCrate;
import com.HamiStudios.ArchonCrates.API.Libs.LanguageManager;
import com.HamiStudios.ArchonCrates.Files.CrateData;
import com.HamiStudios.ArchonCrates.Main;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class BlockPlace implements Listener {

	// Creates an instance of the Main class
	private Main main;

	public BlockPlace(Main main) {
		// Sets the instance of the Main class
		this.main = main;

		// Registers the event
		this.main.getServer().getPluginManager().registerEvents(this, this.main);
	}

	// On Block Placed
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {

		// Create instance of ACPlayer
		ACPlayer player = new ACPlayer(event.getPlayer());

		// Check if the player has the permission to create crates
		if(player.hasPermission(Permissions.CREATE_CRATE.value())) {

			ItemStack item = event.getItemInHand();
			if(item.hasItemMeta() && item.getItemMeta().hasLore()) {
				if(item.getItemMeta().getLore().size() == 5) {
					Crate crate = new Crate(ChatColor.stripColor(item.getItemMeta().getLore().get(4)));
					if(crate.valid()) {

						Block block = event.getBlock();

						CrateData.create(crate, block.getX(), block.getY(), block.getZ(), block.getWorld(), player);

						player.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.EVENT_CRATE_CREATED).replaceAll("<crate>", crate.getID()));

					}
				} else if(item.getItemMeta().getLore().size() == 2) {
					if(ChatColor.stripColor(item.getItemMeta().getLore().get(0)).equalsIgnoreCase("Place down to create")
							&& ChatColor.stripColor(item.getItemMeta().getLore().get(1)).equalsIgnoreCase("a new virtual crate")) {

						Block block = event.getBlock();

						CrateData.create(new VirtualCrate(), block.getX(), block.getY(), block.getZ(), block.getWorld(), player);

						player.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.EVENT_CRATE_CREATED).replaceAll("<crate>", "Virtual"));

					}
				}
			}

		}

	}

}
