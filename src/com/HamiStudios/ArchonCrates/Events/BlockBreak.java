package com.HamiStudios.ArchonCrates.Events;

import com.HamiStudios.ArchonCrates.API.Enums.Files;
import com.HamiStudios.ArchonCrates.API.Enums.LanguageType;
import com.HamiStudios.ArchonCrates.API.Enums.Permissions;
import com.HamiStudios.ArchonCrates.API.Events.OnCrateRemoved;
import com.HamiStudios.ArchonCrates.API.Exceptions.InvalidBlockDropFormat;
import com.HamiStudios.ArchonCrates.API.Exceptions.NoValueException;
import com.HamiStudios.ArchonCrates.API.Libs.Glow;
import com.HamiStudios.ArchonCrates.API.Libs.ItemBuilder;
import com.HamiStudios.ArchonCrates.API.Libs.LanguageManager;
import com.HamiStudios.ArchonCrates.API.Objects.ACPlayer;
import com.HamiStudios.ArchonCrates.API.Objects.Crate;
import com.HamiStudios.ArchonCrates.API.Objects.Key;
import com.HamiStudios.ArchonCrates.API.Objects.VirtualCrate;
import com.HamiStudios.ArchonCrates.Files.CrateData;
import com.HamiStudios.ArchonCrates.Files.KeyDrop;
import com.HamiStudios.ArchonCrates.Main;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.Random;

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
		Block block = event.getBlock();

		// Get the crate at the block location
		String crateID = CrateData.get(block.getX(), block.getY(), block.getZ(), block.getWorld());

		// If the block is a crate
		if(crateID != null) {

			// If the crate is a virtual crate
			if(crateID.equalsIgnoreCase("VIRTUAL_CRATE")) {

				// Check if the player has the permission to create crates (If they can create them they can remove them)
				if(player.hasPermission(Permissions.CREATE_CRATE.value())) {

					// And the player is sneaking
					if (player.getPlayer().isSneaking()) {
						// Remove the crate
						CrateData.remove(block.getX(), block.getY(), block.getZ(), block.getWorld());

						block.setType(Material.AIR);

						// Tell the player the crate is removed
						player.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.EVENT_CRATE_REMOVED));

						// Call CrateRemove Event
						this.main.getServer().getPluginManager().callEvent(new OnCrateRemoved(player, new VirtualCrate()));

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
							CrateData.remove(block.getX(), block.getY(), block.getZ(), block.getWorld());

							block.setType(Material.AIR);

							// Tell the player the crate is removed
							player.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.EVENT_CRATE_REMOVED));

							// Call CrateRemove Event
							this.main.getServer().getPluginManager().callEvent(new OnCrateRemoved(player, new VirtualCrate()));

						} else {
							// Tell the player they need to sneak to remove crates
							player.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.EVENT_CRATE_SNEAK_TO_REMOVE));
						}

					}

					// If the block is a crate stop the player from breaking it
					event.setCancelled(true);

				}

			}

		} else {

			// Make instance of KeyDrop file
			KeyDrop keyDrop = new KeyDrop();
			try {
				// Check if key block drops are enabled
				if((boolean) keyDrop.get("Blocks.enabled")) {

					if(event.getPlayer().hasPermission(Permissions.BLOCK_DROP_KEY.value())) {
						// If key block drops are enabled

						// For all blocks
						for (String blockID : keyDrop.getFileConfiguration().getConfigurationSection("Blocks.drops").getKeys(false)) {


							try {
								// Get the ID and Data for the block in the file
								int id = Integer.parseInt(blockID.split(":")[0]);
								int data = 0;
								if(blockID.split(":").length == 2) {
									data = Integer.parseInt(blockID.split(":")[1]);
								}

								// If the block broken is not null or AIR
								if(event.getBlock() != null && event.getBlock().getType() != Material.AIR) {

									// Check if the block broken is the block in the file
									if(Material.getMaterial(id) == event.getBlock().getType()) {
										if(data == event.getBlock().getState().getData().toItemStack().getDurability()) {

											// Make an array for all the keys the block can drop
											ArrayList<Key> keys = new ArrayList<>();

											// Create an instance of Random
											Random random = new Random();

											// Create the drop equalizer
											double dropEqualizer = 0 + (10) * random.nextDouble();

											// For all the keys for the block in the file
											for (String keyID : keyDrop.getFileConfiguration().getConfigurationSection("Blocks.drops." + blockID).getKeys(false)) {
												Key key = new Key(keyID);
												// If the key is valid
												if(key.valid()) {
													// If the key chance of dropping is more or equal to the dropEqualizer
													if((double) keyDrop.get("Blocks.drops." + blockID + "." + keyID) >= dropEqualizer) {
														// Add the key to the droppable keys
														keys.add(key);
													}
												}
											}

											// If the key array has keys
											if(keys.size() > 0) {
												// Get a random key from the array
												Key key = keys.get(random.nextInt((keys.size()-1) + 1));

												// Build the key as an Item
												ItemBuilder keyItem = new ItemBuilder()
														.setMaterial(Material.getMaterial(key.getItemID()))
														.setName(key.getName())
														.setData((short) key.getItemData())
														.setLore(key.getLore())
														.setAmount(1);

												if(key.glow()) {
													keyItem.addEnchantment(new Glow(99), 1, true);
												}

												// Drop the key at the block
												event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), keyItem.build());
											}

											break;

										}

									}

								}

							} catch(NumberFormatException e) {
								new InvalidBlockDropFormat(blockID, Files.KEY_DROPS);
							}

						}
					}

				}
			} catch (NoValueException e) { }

		}

	}

}
