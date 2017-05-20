package com.HamiStudios.ArchonCrates.Events;

import com.HamiStudios.ArchonCrates.API.Objects.ACPlayer;
import com.HamiStudios.ArchonCrates.API.Objects.Crate;
import com.HamiStudios.ArchonCrates.API.Objects.VirtualCrate;
import com.HamiStudios.ArchonCrates.Files.CrateData;
import com.HamiStudios.ArchonCrates.Main;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockClick implements Listener {

	// Creates an instance of the Main class
	private Main main;

	public BlockClick(Main main) {
		// Sets the instance of the Main class
		this.main = main;

		// Registers the event
		this.main.getServer().getPluginManager().registerEvents(this, this.main);
	}

	// On Block Click
	@EventHandler
	public void onBlockClick(PlayerInteractEvent event) {

		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(event.getClickedBlock() != null) {

				if(!event.getClickedBlock().equals(Material.AIR)) {

					Block block = event.getClickedBlock();

					String crateID = CrateData.get(block.getX(), block.getY(), block.getZ(), block.getWorld());

					if(crateID != null) {

						// Create instance of ACPlayer
						ACPlayer player = new ACPlayer(event.getPlayer());
						event.setCancelled(true);

						if(crateID.equals("VIRTUAL_CRATE")) {

							// Virtual Crate

							// Create instance of Virtual Crate
							VirtualCrate virtualCrate = new VirtualCrate();

							player.sendMessage(virtualCrate.getTitle());

						} else {

							// Physical Crate

							// Create instance of Crate
							Crate crate = new Crate(crateID);

							player.sendMessage(crate.getTitle());

						}

					}

				}

			}
		}

	}

}
