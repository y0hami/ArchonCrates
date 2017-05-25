package com.HamiStudios.ArchonCrates.Events;

import com.HamiStudios.ArchonCrates.API.Enums.LanguageType;
import com.HamiStudios.ArchonCrates.API.Enums.Permissions;
import com.HamiStudios.ArchonCrates.API.Libs.Fetcher;
import com.HamiStudios.ArchonCrates.API.Libs.Glow;
import com.HamiStudios.ArchonCrates.API.Libs.ItemBuilder;
import com.HamiStudios.ArchonCrates.API.Libs.LanguageManager;
import com.HamiStudios.ArchonCrates.API.Menus.VirtualCrateMenu;
import com.HamiStudios.ArchonCrates.API.Objects.ACPlayer;
import com.HamiStudios.ArchonCrates.API.Objects.Crate;
import com.HamiStudios.ArchonCrates.API.Objects.Key;
import com.HamiStudios.ArchonCrates.API.Objects.Prize;
import com.HamiStudios.ArchonCrates.API.Operations.CrateRoll;
import com.HamiStudios.ArchonCrates.API.Operations.VirtualKeySelector;
import com.HamiStudios.ArchonCrates.Files.CrateData;
import com.HamiStudios.ArchonCrates.Main;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

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

					if (crateID != null) {


						event.setCancelled(true);

						// Create instance of ACPlayer
						ACPlayer player = new ACPlayer(event.getPlayer());

						// Check if its a virtual crate
						if(crateID.equals("VIRTUAL_CRATE")) {

							// Virtual Crate

							// Add the player to the operation manager
							this.main.getOperationsManager().addVirtualKeySelector(new VirtualKeySelector(player, event.getClickedBlock()));

							// Open the virtual key selector menu
							VirtualCrateMenu virtualCrateMenu = new VirtualCrateMenu(player);
							player.getPlayer().openInventory(virtualCrateMenu.getMenu());


						} else {

							// Physical Crate Checks

							if(event.getItem() != null) {
								if(!event.getItem().equals(Material.AIR)) {
									if(event.getItem().hasItemMeta()){
										if(event.getItem().getItemMeta().hasLore()) {

											// Make a null instance to check the correct key against
											Key usedKey = null;

											// An array of all keys
											ArrayList<Key> keys = Fetcher.getKeys();

											// For all the keys, check if the item used to click on the crate is a matching valid key
											for (Key key : keys) {

												// Creates the key instance as its key item
												ItemBuilder keyItem = new ItemBuilder()
														.setMaterial(Material.getMaterial(key.getItemID()))
														.setName(key.getName())
														.setData((short) key.getItemData())
														.setLore(key.getLore())
														.setAmount(1);

												if (key.glow()) {
													keyItem.addEnchantment(new Glow(99), 1, true);
												}

												ItemStack keyItemStack = keyItem.build();

												// Creates the item used to click as a new item instance
												ItemBuilder clickItemBuilder = new ItemBuilder()
														.setMaterial(event.getItem().getType())
														.setName(event.getItem().getItemMeta().getDisplayName())
														.setLore((ArrayList<String>) event.getItem().getItemMeta().getLore())
														.setData(event.getItem().getDurability())
														.setAmount(1);

												if(event.getItem().getEnchantments().containsKey(new Glow(99))) {
													clickItemBuilder.addEnchantment(new Glow(99), 1, true);
												}

												ItemStack clickItem = clickItemBuilder.build();

												// If the key item is equal to the item used to click its a key
												if (clickItem.equals(keyItemStack)) {
													usedKey = key;
												}

											}

											// If the item used to click is a key
											if (usedKey != null) {

												// If the crate is a virtual crate
												if (!crateID.equals("VIRTUAL_CRATE")) {

													// Physical Crate

													if(player.hasPermission(Permissions.OPEN_PHYSICAL_CRATE.value())) {

														// Create instance of Crate
														Crate crate = new Crate(crateID);

														int prizeSafe = 0;
														for (Prize prize : crate.getPrizes()) {
															if(prize.usePermission()) {
																if(!player.hasPermission(prize.getPermission())) {
																	prizeSafe++;
																}
															}
														}

														if(prizeSafe == crate.getPrizes().size()) {

															// Player has no permissions to any prizes
															player.sendMessage(LanguageManager.get(LanguageType.PREFIX) + LanguageManager.get(LanguageType.ERROR_CANT_OPEN_CRATE));

														} else {

															// Loop through all the keys the crate allows and check if the key used is a correct key
															boolean correctKey = false;
															for (Key key : crate.getKeys()) { if(key.getID().equals(usedKey.getID())) { correctKey = true; } }

															if(correctKey) {
																ItemStack clickedItem = event.getPlayer().getInventory().getItem(event.getPlayer().getInventory().getHeldItemSlot());

																// The player used the correct key, remove 1 or the key from their inventory
																if(clickedItem.getAmount() == 1) {
																	event.getPlayer().getInventory().setItem(
																			event.getPlayer().getInventory().getHeldItemSlot(),
																			new ItemBuilder()
																					.setMaterial(Material.AIR)
																					.build());
																}

																else { event.getItem().setAmount(clickedItem.getAmount()-1); }

																// Create a new Crate Roll and Roll the crate for the player
																CrateRoll crateRoll = new CrateRoll(this.main);
																crateRoll.roll(player, crate, usedKey, event.getClickedBlock());
															} else {
																// The player used an invalid key
																player.sendMessage(LanguageManager.get(LanguageType.PREFIX) + crate.getInvalidKeyMessage());
															}

														}

													} else {
														// Player doesn't have permission to open physical crates
														player.sendMessage(LanguageManager.get(LanguageType.PREFIX) + LanguageManager.get(LanguageType.ERROR_CANT_OPEN_CRATE));
													}

												}

											} else {
												player.sendMessage(LanguageManager.get(LanguageType.PREFIX) + LanguageManager.get(LanguageType.EVENT_CRATE_MUST_USE_KEY));
											}
										} else {
											player.sendMessage(LanguageManager.get(LanguageType.PREFIX) + LanguageManager.get(LanguageType.EVENT_CRATE_MUST_USE_KEY));
										}
									} else {
										player.sendMessage(LanguageManager.get(LanguageType.PREFIX) + LanguageManager.get(LanguageType.EVENT_CRATE_MUST_USE_KEY));
									}
								} else {
									player.sendMessage(LanguageManager.get(LanguageType.PREFIX) + LanguageManager.get(LanguageType.EVENT_CRATE_MUST_USE_KEY));
								}
							} else {
								player.sendMessage(LanguageManager.get(LanguageType.PREFIX) + LanguageManager.get(LanguageType.EVENT_CRATE_MUST_USE_KEY));
							}

						}

					}

				}

			}
		}

	}

}
