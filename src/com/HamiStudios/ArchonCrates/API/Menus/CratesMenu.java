package com.HamiStudios.ArchonCrates.API.Menus;

import com.HamiStudios.ArchonCrates.API.Enums.Menu;
import com.HamiStudios.ArchonCrates.API.Libs.Fetcher;
import com.HamiStudios.ArchonCrates.API.Libs.ItemBuilder;
import com.HamiStudios.ArchonCrates.API.Objects.Crate;
import com.HamiStudios.ArchonCrates.API.Objects.ItemLore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class CratesMenu {

	// Create instance of the crate menu Inventory and an array of crates
	private Inventory worldsMenu;
	private Inventory crateSelectorMenu;
	private ArrayList<Crate> crates;
	private ArrayList<World> worlds;

	// CratesMenu constructor
	public CratesMenu() {

		// Worlds Menu


		// Create an array of crates
		this.worlds = (ArrayList<World>) Bukkit.getWorlds();

		// Decides on the minimum size of the inventory menu
		int invSize = (this.worlds.size()+8)/9*9;
		// If its larger then the max inventory size, set it to the max inventory size
		if(invSize > 54) { invSize = 54; }

		// Creates the Inventory instance
		this.worldsMenu = Bukkit.createInventory(null, invSize, ChatColor.translateAlternateColorCodes('&', "&5" + Menu.CRATES_WORLDS.getTitle()));

		// For all the worlds in the world array
		for (World world : this.worlds) {
			ItemBuilder itemBuilder = new ItemBuilder();

			switch (world.getEnvironment()) {
				case NORMAL:
					itemBuilder.setMaterial(Material.GRASS)
						.setName("&2" + world.getName());
					break;
				case NETHER:
					itemBuilder.setMaterial(Material.NETHERRACK)
							.setName("&4" + world.getName());
					break;
				case THE_END:
					itemBuilder.setMaterial(Material.ENDER_STONE)
							.setName("&f" + world.getName());
			}

			itemBuilder.setData((short) 0)
					.setLore(new ItemLore()
							.add("")
							.add("&7Click to explore")
							.add("&7crates in that world")
							.build());

			this.worldsMenu.addItem(itemBuilder.build());
		}

	}

	public void buildCrateMenu(World world) {

		// Crate Selector Menu


		this.crates = Fetcher.getCrates();


		// Creates the Inventory instances
		this.crateSelectorMenu = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', "&5" + Menu.CRATES_SELECTOR.getTitle()));

		// Adds the Physical crate item
		this.crateSelectorMenu.setItem(12, new ItemBuilder()
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
		this.crateSelectorMenu.setItem(14, new ItemBuilder()
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
			this.crateSelectorMenu.setItem(slots-1, new ItemBuilder()
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
			case CRATES_WORLDS: // Matches CRATES_WORLDS menu

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

				break;
		}

	}

	// Opens the given menu to the given player
	public void openMenu(Player player, Menu menu) {
		// Switch through all the menus
		switch (menu) {
			case CRATES_WORLDS: // If its the CRATES_WORLDS menu

				// Open the CRATES_WORLDS menu for the player
				player.openInventory(this.worldsMenu);

				break;
		}
	}

}
