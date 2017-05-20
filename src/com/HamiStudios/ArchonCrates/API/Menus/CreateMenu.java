package com.HamiStudios.ArchonCrates.API.Menus;

import com.HamiStudios.ArchonCrates.API.Enums.LanguageType;
import com.HamiStudios.ArchonCrates.API.Enums.Menu;
import com.HamiStudios.ArchonCrates.API.Enums.Permissions;
import com.HamiStudios.ArchonCrates.API.Objects.Crate;
import com.HamiStudios.ArchonCrates.API.Objects.ItemLore;
import com.HamiStudios.ArchonCrates.API.Objects.VirtualCrate;
import com.HamiStudios.ArchonCrates.API.Libs.Fetcher;
import com.HamiStudios.ArchonCrates.API.Libs.ItemBuilder;
import com.HamiStudios.ArchonCrates.API.Libs.LanguageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class CreateMenu {

	// Create instance of the crate menu Inventory and an array of crates
	private Inventory cratesMenu;
	private Inventory crateTypeMenu;
	private ArrayList<Crate> crates;

	// CreateMenu constructor
	public CreateMenu() {

		// Physical Crate Menu


		// Create an array of crates
		this.crates = Fetcher.getCrates();

		// Decides on the minimum size of the inventory menu
		int invSize = (this.crates.size()+8)/9*9;
		// If its larger then the max inventory size, set it to the max inventory size
		if(invSize > 54) { invSize = 54; }

		// Creates the Inventory instance
		this.cratesMenu = Bukkit.createInventory(null, invSize, ChatColor.translateAlternateColorCodes('&', "&5" + Menu.CREATE_CRATE.getTitle()));

		// For all the crates in the crates array
		for (Crate crate : this.crates) {
			// Create a replica of the crate and add it to the inventory menu
			this.cratesMenu.addItem(new ItemBuilder()
					.setMaterial(Material.getMaterial(crate.getBlockID()))
					.setName(crate.getTitle())
					.setData((short) crate.getBlockData())
					.setLore(new ItemLore()
							.add("")
							.add("&7Click to create")
							.add("&7a &f" + crate.getID() + " &7crate")
						.build())
					.build()
			);
		}


		// Crate Type Selector Menu


		// Creates the Inventory instances
		this.crateTypeMenu = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&5" + Menu.CRATE_TYPE.getTitle()));

		// Adds the Physical crate item
		this.crateTypeMenu.setItem(12, new ItemBuilder()
					.setMaterial(Material.CHEST)
					.setName("&5Physical Crate")
					.setLore(new ItemLore()
							.add("&7Physical crates players")
							.add("&7interact with")
							.build()
					)
				.build()
		);

		// Adds the Virtual crate item
		this.crateTypeMenu.setItem(14, new ItemBuilder()
				.setMaterial(Material.END_CRYSTAL)
				.setName("&5Virtual Crate")
				.setLore(new ItemLore()
						.add("&7Crates where players")
						.add("&7can use any key")
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
			this.crateTypeMenu.setItem(slots-1, new ItemBuilder()
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

		// Switch through the menus
		switch (menu) {
			case CREATE_CRATE: // Matches CREATE_CRATE menu

				// If the slot the player clicks is a crate array index
				if(event.getSlot() < Fetcher.getCrates().size()) {

					// Get the crate at the index of the slot they player clicks
					Crate crate = Fetcher.getCrates().get(event.getSlot());

					// Create a Bukkit Player instance of the player that clicked the slot
					Player player = (Player) event.getWhoClicked();

					// Close the crate selection menu
					player.closeInventory();

					// If that player has permission to create a crate
					if(player.hasPermission(Permissions.CREATE_CRATE.value())) {

						// Add the crate create item to the players inventory
						player.getInventory().addItem(new ItemBuilder()
								.setMaterial(Material.getMaterial(crate.getBlockID()))
								.setName(crate.getTitle())
								.setData((short) crate.getBlockData())
								.setLore(new ItemLore()
										.add("&7Place down to create")
										.add("&7a new crate")
										.add("")
										.add("&7Crate ID:")
										.add("&f" + crate.getID())
										.build())
								.build()
						);

						// Send the player a message so they know the crate has been added to their inventory
						player.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.COMMAND_CREATE_ADDED_TO_INV));
					} else {
						// Send the player a message to say they don't have permission
						player.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.ERROR_NO_PERMISSION));
					}
				}

				break;
			case CRATE_TYPE: // Matches CRATE_TYPE menu

				// If the item clicked is the physical option
				if(event.getCurrentItem().getType() == Material.CHEST) {

					// Open the physical crates picker menu
					event.getWhoClicked().openInventory(this.cratesMenu);

				} else if(event.getCurrentItem().getType() == Material.END_CRYSTAL) {

					// Give the player a virtual crate to place

					// Create a Bukkit Player instance of the player that clicked the slot
					Player player = (Player) event.getWhoClicked();

					// Close the crate selection menu
					player.closeInventory();

					if(player.hasPermission(Permissions.CREATE_CRATE.value())) {
						// Get an instance of the virtual crate
						VirtualCrate crate = new VirtualCrate();

						// Add the virtual crate item to the players inventory
						player.getInventory().addItem(new ItemBuilder()
								.setMaterial(Material.getMaterial(crate.getBlockID()))
								.setName(crate.getTitle())
								.setData((short) crate.getBlockData())
								.setLore(new ItemLore()
										.add("&7Place down to create")
										.add("&7a new virtual crate")
										.build())
								.build()
						);

						// Send the player a message so they know the crate has been added to their inventory
						player.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.COMMAND_CREATE_ADDED_TO_INV));
					} else {
						// Send the player a message to say they don't have permission
						player.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.ERROR_NO_PERMISSION));
					}
				}

				break;
		}

	}

	// Opens the given menu to the given player
	public void openMenu(Player player, Menu menu) {
		// Switch through all the menus
		switch (menu) {
			case CREATE_CRATE: // If its the CREATE_CRATE menu

				// Open the CREATE_CRATE menu for the player
				player.openInventory(this.cratesMenu);

				break;
			case CRATE_TYPE: // If its a CRATE_TYPE menu

				// Open the CRATE_TYPE menu for the player
				player.openInventory(this.crateTypeMenu);

				break;
		}
	}

}
