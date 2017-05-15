package com.HamiStudios.ArchonCrates.API.Menus;

import com.HamiStudios.ArchonCrates.API.Enums.LanguageType;
import com.HamiStudios.ArchonCrates.API.Enums.Menu;
import com.HamiStudios.ArchonCrates.API.Enums.Permissions;
import com.HamiStudios.ArchonCrates.API.Objects.ACPlayer;
import com.HamiStudios.ArchonCrates.API.Objects.ItemLore;
import com.HamiStudios.ArchonCrates.API.Objects.Key;
import com.HamiStudios.ArchonCrates.API.Operations.GiveKeyOperation;
import com.HamiStudios.ArchonCrates.API.libs.Fetcher;
import com.HamiStudios.ArchonCrates.API.libs.Glow;
import com.HamiStudios.ArchonCrates.API.libs.ItemBuilder;
import com.HamiStudios.ArchonCrates.API.libs.LanguageManager;
import com.HamiStudios.ArchonCrates.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class KeyMenu {

	// Create instances of the main class, key menu Inventory's and an array of keys
	private Main main;
	private Inventory keyMenu;
	private Inventory virtualKeyMenu;
	private Inventory keyTypeMenu;
	private ArrayList<Key> keys;
	private ArrayList<Key> virtualKeys;


	// KeyMenu constructor
	public KeyMenu(Main main) {
		this.main = main;

		// Physical Key Menu

		// Create an array of keys
		this.keys = Fetcher.getKeys();

		// Decides on the minimum size of the inventory menu
		int invSize = (this.keys.size()+8)/9*9;
		// If its larger then the max inventory size, set it to the max inventory size
		if(invSize > 54) { invSize = 54; }

		// Creates the Inventory instance
		this.keyMenu = Bukkit.createInventory(null, invSize, ChatColor.translateAlternateColorCodes('&', "&5" + Menu.GIVE_KEY.getTitle()));

		// For all the keys in the keys array
		for (Key key : this.keys) {
			// Create a replica of the key and add it to the inventory menu
			ItemBuilder keyItem = new ItemBuilder()
					.setMaterial(Material.getMaterial(key.getItemID()))
					.setName(key.getName())
					.setData((short) key.getItemData())
					.setLore(new ItemLore()
							.add("")
							.add("&7Click to give")
							.add("&7a &f" + key.getID() + " &7key")
							.build());

			if(key.glow()) {
				keyItem.addEnchantment(new Glow(99), 1, true);
			}

			this.keyMenu.addItem(keyItem.build());
		}



		// Virtual Key Menu

		// Create an array of keys
		this.virtualKeys = Fetcher.getVirtualKeys();

		// Decides on the minimum size of the inventory menu
		invSize = (this.virtualKeys.size()+8)/9*9;
		// If its larger then the max inventory size, set it to the max inventory size
		if(invSize > 54) { invSize = 54; }

		// Creates the Inventory instance
		this.virtualKeyMenu = Bukkit.createInventory(null, invSize, ChatColor.translateAlternateColorCodes('&', "&5" + Menu.GIVE_VIRTUAL_KEY.getTitle()));

		// For all the keys in the virtual keys array
		for (Key key : this.virtualKeys) {
			// Create a replica of the key and add it to the inventory menu
			ItemBuilder keyItem = new ItemBuilder()
					.setMaterial(Material.getMaterial(key.getItemID()))
					.setName(key.getName())
					.setData((short) key.getItemData())
					.setLore(new ItemLore()
							.add("")
							.add("&7Click to give")
							.add("&7a &f" + key.getID() + " &7key")
							.build());

			if(key.glow()) {
				keyItem.addEnchantment(new Glow(99), 1, true);
			}

			this.virtualKeyMenu.addItem(keyItem.build());
		}



		// Key Type Selector Menu

		// Creates the Inventory instances
		this.keyTypeMenu = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&5" + Menu.KEY_TYPE.getTitle()));

		// Adds the Physical key item
		this.keyTypeMenu.setItem(12, new ItemBuilder()
					.setMaterial(Material.TRIPWIRE_HOOK)
					.setName("&5Physical Key")
					.addEnchantment(new Glow(99), 1, true)
					.setLore(new ItemLore()
							.add("&7Physical key players")
							.add("&7use to open crates")
							.build()
					)
				.build()
		);

		// Adds the Virtual key item
		this.keyTypeMenu.setItem(14, new ItemBuilder()
				.setMaterial(Material.NETHER_STAR)
				.setName("&5Virtual Crate")
				.setLore(new ItemLore()
						.add("&7Key which players can")
						.add("&7use in virtual crates")
						.build()
				)
				.build()
		);

		// Creates a reference for the slots
		int slots = 27;
		// For every slot
		while (slots != 0) {
			// Apart from 13 (12) and 15 (14)
			if(slots == 13 || slots == 15) { slots--; continue; }

			// Add a black glass pane
			this.keyTypeMenu.setItem(slots-1, new ItemBuilder()
					.setMaterial(Material.STAINED_GLASS_PANE)
					.setData((short) 15)
					.setName("&f")
					.build()
			);
			slots--;
		}

	}

	// Run the functionality for when a player clicks on a crate
	public void event(Menu menu, InventoryClickEvent event) {

		// Get the operation
		GiveKeyOperation operation = this.main.getOperationsManager().getGiveKeyOperation(new ACPlayer((Player) event.getWhoClicked()));

		switch (menu) {
			case KEY_TYPE:

				if(event.getCurrentItem().getType() == Material.TRIPWIRE_HOOK) {

					if(operation != null) {
						this.openMenu((Player) event.getWhoClicked(), Menu.GIVE_KEY);
						this.main.getOperationsManager().addKeyGiver(new ACPlayer((Player) event.getWhoClicked()), operation.getGiveTo(), operation.giveAll(), operation.getAmount());
					}

				} else if(event.getCurrentItem().getType() == Material.NETHER_STAR) {

					if(operation != null) {
						this.openMenu((Player) event.getWhoClicked(), Menu.GIVE_VIRTUAL_KEY);
						this.main.getOperationsManager().addKeyGiver(new ACPlayer((Player) event.getWhoClicked()), operation.getGiveTo(), operation.giveAll(), operation.getAmount());
					}

				}

				break;
			case GIVE_KEY:

				// If the operation is null the player
				if(operation != null) {
					if(operation.giveAll()) {

						// The operation is a give all

						// Check if the player has permission to give keys to all players
						if(event.getWhoClicked().hasPermission(Permissions.COMMAND_KEY_ALL.value())) {

							// If the slot the player clicks is a key array index
							if(event.getSlot() < Fetcher.getKeys().size()) {

								// Get the key at the index of the slot they player clicks
								Key key = Fetcher.getKeys().get(event.getSlot());

								// Create a Bukkit Player instance of the player that clicked the slot
								Player player = (Player) event.getWhoClicked();

								// For all the online players add a key to there inventory
								for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {

									ItemBuilder keyItem = new ItemBuilder()
											.setMaterial(Material.getMaterial(key.getItemID()))
											.setName(key.getName())
											.setData((short) key.getItemData())
											.setLore(key.getLore())
											.setAmount(operation.getAmount());

									if(key.glow()) {
										keyItem.addEnchantment(new Glow(99), 1, true);
									}

									onlinePlayer.getInventory().addItem(keyItem.build());

								}

								// Send the player a message so they know the key has been added to their inventory
								player.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.EVENT_KEY_GIVEN_ALL)
										.replaceAll("<key>", key.getID())
										.replaceAll("<amount>", operation.getAmount() + ""));

								// Close the crate selection menu
								player.closeInventory();
							}

						} else {
							// No permission
							event.getWhoClicked().sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.EVENT_KEY_NO_PERMISSION));
							event.getWhoClicked().closeInventory();
						}

					} else {

						// Give key to the specified player

						// Check if the player has permission to give keys to players
						if(event.getWhoClicked().hasPermission(Permissions.COMMAND_KEY_PLAYER.value())) {

							// If the slot the player clicks is a key array index
							if(event.getSlot() < Fetcher.getKeys().size()) {

								// Get the key at the index of the slot they player clicks
								Key key = Fetcher.getKeys().get(event.getSlot());

								// Create a Bukkit Player instance of the player that clicked the slot
								Player player = (Player) event.getWhoClicked();


								ItemBuilder keyItem = new ItemBuilder()
										.setMaterial(Material.getMaterial(key.getItemID()))
										.setName(key.getName())
										.setData((short) key.getItemData())
										.setLore(key.getLore())
										.setAmount(operation.getAmount());

								if(key.glow()) {
									keyItem.addEnchantment(new Glow(99), 1, true);
								}

								operation.getGiveTo().getInventory().addItem(keyItem.build());


								// Send the player a message so they know the key has been added to their inventory
								player.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.EVENT_KEY_GIVEN_PLAYER)
										.replaceAll("<key>", key.getID())
										.replaceAll("<amount>", operation.getAmount() + "")
										.replaceAll("<player>", operation.getGiveTo().getName()));

								// Close the key selection menu
								player.closeInventory();
							}

						} else {
							// No permission
							event.getWhoClicked().sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.EVENT_KEY_NO_PERMISSION));
							event.getWhoClicked().closeInventory();
						}

					}
				}

				break;
			case GIVE_VIRTUAL_KEY:

				// If the operation is null the player
				if(operation != null) {
					if(operation.giveAll()) {

						// The operation is a give all

						// Check if the player has permission to give keys to all players
						if(event.getWhoClicked().hasPermission(Permissions.COMMAND_KEY_ALL.value())) {

							// If the slot the player clicks is a key array index
							if(event.getSlot() < Fetcher.getKeys().size()) {

								// Get the key at the index of the slot they player clicks
								Key key = Fetcher.getKeys().get(event.getSlot());

								// Create a Bukkit Player instance of the player that clicked the slot
								Player player = (Player) event.getWhoClicked();

								// For all the online players add a key to there inventory
								for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {

									ACPlayer acPlayer = new ACPlayer(onlinePlayer);
									acPlayer.addVirtualKey(key, operation.getAmount());

								}

								// Send the player a message so they know the key has been added to their inventory
								player.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.EVENT_KEY_GIVEN_ALL)
										.replaceAll("<key>", key.getID())
										.replaceAll("<amount>", operation.getAmount() + ""));

								// Close the crate selection menu
								player.closeInventory();
							}

						} else {
							// No permission
							event.getWhoClicked().sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.EVENT_KEY_NO_PERMISSION));
							event.getWhoClicked().closeInventory();
						}

					} else {

						// Give key to the specified player

						// Check if the player has permission to give keys to players
						if(event.getWhoClicked().hasPermission(Permissions.COMMAND_KEY_PLAYER.value())) {

							// If the slot the player clicks is a key array index
							if(event.getSlot() < Fetcher.getKeys().size()) {

								// Get the key at the index of the slot they player clicks
								Key key = Fetcher.getKeys().get(event.getSlot());

								// Create a Bukkit Player instance of the player that clicked the slot
								Player player = (Player) event.getWhoClicked();
								ACPlayer acPlayer = new ACPlayer(player);

								acPlayer.addVirtualKey(key, operation.getAmount());

								// Send the player a message so they know the key has been added to their inventory
								player.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.EVENT_KEY_GIVEN_PLAYER)
										.replaceAll("<key>", key.getID())
										.replaceAll("<amount>", operation.getAmount() + "")
										.replaceAll("<player>", operation.getGiveTo().getName()));

								// Close the key selection menu
								player.closeInventory();
							}

						} else {
							// No permission
							event.getWhoClicked().sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.EVENT_KEY_NO_PERMISSION));
							event.getWhoClicked().closeInventory();
						}

					}
				}

				break;
		}

//		// Switch through the menus
//		switch (menu) {
//			case CREATE_CRATE: // Matches CREATE_CRATE menu
//
//				// If the slot the player clicks is a crate array index
//				if(event.getSlot() < Fetcher.getCrates().size()) {
//
//					// Get the crate at the index of the slot they player clicks
//					Crate crate = Fetcher.getCrates().get(event.getSlot());
//
//					// Create a Bukkit Player instance of the player that clicked the slot
//					Player player = (Player) event.getWhoClicked();
//
//					// Close the crate selection menu
//					player.closeInventory();
//
//					// If that player has permission to create a crate
//					if(player.hasPermission(Permissions.CREATE_CRATE.value())) {
//
//						// Add the crate create item to the players inventory
//						player.getInventory().addItem(new ItemBuilder()
//								.setMaterial(Material.getMaterial(crate.getBlockID()))
//								.setName(crate.getTitle())
//								.setData((short) crate.getBlockData())
//								.setLore(new ItemLore()
//										.add("&7Place down to create")
//										.add("&7a new crate")
//										.add("")
//										.add("&7Crate ID:")
//										.add("&f" + crate.getID())
//										.build())
//								.build()
//						);
//
//						// Send the player a message so they know the crate has been added to their inventory
//						player.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.COMMAND_CREATE_ADDED_TO_INV));
//					} else {
//						// Send the player a message to say they don't have permission
//						player.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.ERROR_NO_PERMISSION));
//					}
//				}
//
//				break;
//			case CRATE_TYPE: // Matches CRATE_TYPE menu
//
//				// If the item clicked is the physical option
//				if(event.getCurrentItem().getType() == Material.CHEST) {
//
//					// Open the physical crates picker menu
//					event.getWhoClicked().openInventory(this.cratesMenu);
//
//				} else if(event.getCurrentItem().getType() == Material.END_CRYSTAL) {
//
//					// Give the player a virtual crate to place
//
//					// Create a Bukkit Player instance of the player that clicked the slot
//					Player player = (Player) event.getWhoClicked();
//
//					// Close the crate selection menu
//					player.closeInventory();
//
//					if(player.hasPermission(Permissions.CREATE_CRATE.value())) {
//						// Get an instance of the virtual crate
//						VirtualCrate crate = new VirtualCrate();
//
//						// Add the virtual crate item to the players inventory
//						player.getInventory().addItem(new ItemBuilder()
//								.setMaterial(Material.getMaterial(crate.getBlockID()))
//								.setName(crate.getTitle())
//								.setData((short) crate.getBlockData())
//								.setLore(new ItemLore()
//										.add("&7Place down to create")
//										.add("&7a new virtual crate")
//										.build())
//								.build()
//						);
//
//						// Send the player a message so they know the crate has been added to their inventory
//						player.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.COMMAND_CREATE_ADDED_TO_INV));
//					} else {
//						// Send the player a message to say they don't have permission
//						player.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.ERROR_NO_PERMISSION));
//					}
//				}
//
//				break;
//		}

	}

	// Opens the given menu to the given player
	public void openMenu(Player player, Menu menu) {
		// Switch through all the menus
		switch (menu) {
			case GIVE_KEY: // If its the GIVE_KEY menu

				// Open the GIVE_KEY menu for the player
				player.openInventory(this.keyMenu);

				break;
			case GIVE_VIRTUAL_KEY: // If its the GIVE_VIRTUAL_KEY menu

				// Open the GIVE_VIRTUAL_KEY menu for the player
				player.openInventory(this.virtualKeyMenu);

				break;
			case KEY_TYPE: // If its a KEY_TYPE menu

				// Open the KEY_TYPE menu for the player
				player.openInventory(this.keyTypeMenu);

				break;
		}
	}

}
