package com.HamiStudios.ArchonCrates.API.Menus;

import com.HamiStudios.ArchonCrates.API.Enums.LanguageType;
import com.HamiStudios.ArchonCrates.API.Enums.Menu;
import com.HamiStudios.ArchonCrates.API.Enums.Permissions;
import com.HamiStudios.ArchonCrates.API.Libs.ItemBuilder;
import com.HamiStudios.ArchonCrates.API.Libs.LanguageManager;
import com.HamiStudios.ArchonCrates.API.Objects.ACPlayer;
import com.HamiStudios.ArchonCrates.API.Objects.Crate;
import com.HamiStudios.ArchonCrates.API.Objects.ItemLore;
import com.HamiStudios.ArchonCrates.API.Objects.VirtualCrate;
import com.HamiStudios.ArchonCrates.Files.CrateData;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

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


		ArrayList<HashMap<String, Object>> crates = CrateData.getCrates(world);

		// Decides on the minimum size of the inventory menu
		int invSize = (crates.size()+8)/9*9;
		// If its larger then the max inventory size, set it to the max inventory size
		if(invSize > 54) { invSize = 54; }

		// Creates the Inventory instances
		this.crateSelectorMenu = Bukkit.createInventory(null, invSize, ChatColor.translateAlternateColorCodes('&', "&5" + Menu.CRATES_SELECTOR.getTitle()));

		for (HashMap<String, Object> crateRow : crates) {
			if(((String) crateRow.get("CRATE_TYPE")).equalsIgnoreCase("VIRTUAL_CRATE")) {
				VirtualCrate crate = new VirtualCrate();

				// Adds the crate item
				this.crateSelectorMenu.addItem(new ItemBuilder()
						.setMaterial(Material.getMaterial(crate.getBlockID()))
						.setName(crate.getTitle())
						.setData((short) crate.getBlockData())
						.setLore(new ItemLore()
								.add("&7Crate ID: &5VIRTUAL_CRATE")
								.add(" ")
								.add("&7X: &f" + crateRow.get("X"))
								.add("&7Y: &f" + crateRow.get("Y"))
								.add("&7Z: &f" + crateRow.get("Z"))
								.add("&7World: &f" + crateRow.get("WORLD"))
								.build()
						)
						.build()
				);
			} else {
				Crate crate = new Crate((String) crateRow.get("CRATE_TYPE"));

				// Adds the crate item
				this.crateSelectorMenu.addItem(new ItemBuilder()
						.setMaterial(Material.getMaterial(crate.getBlockID()))
						.setName(crate.getTitle())
						.setData((short) crate.getBlockData())
						.setLore(new ItemLore()
								.add("&7Crate ID: &f" + crate.getID())
								.add(" ")
								.add("&7X: &f" + crateRow.get("X"))
								.add("&7Y: &f" + crateRow.get("Y"))
								.add("&7Z: &f" + crateRow.get("Z"))
								.add("&7World: &f" + crateRow.get("WORLD"))
								.build()
						)
						.build()
				);
			}
		}

	}

	// Run the functionality for when a player clicks on a crate
	public void event(Menu menu, InventoryClickEvent event) {

		// Switch through the menus
		switch (menu) {
			case CRATES_WORLDS: // Matches CRATES_WORLDS menu

				// If the slot the player clicks is a world array index
				if(event.getSlot() < Bukkit.getWorlds().size()) {

					// Get the world at the index of the slot they player clicks
					World world = Bukkit.getWorlds().get(event.getSlot());

					// Create a Bukkit Player instance of the player that clicked the slot
					Player player = (Player) event.getWhoClicked();

					// Close the crate selection menu
					player.closeInventory();

					if(player.hasPermission(Permissions.COMMAND_CRATES.value())) {
						buildCrateMenu(world);
						openMenu(player, Menu.CRATES_SELECTOR);
					} else {
						// Send the player a message to say they don't have permission
						player.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.ERROR_NO_PERMISSION));
					}

				}

				break;
			case CRATES_SELECTOR: // Matches CRATES_SELECTOR menu

				// Test if the player is clicking a crate
				if(event.getCurrentItem() != null) {

					ItemStack item = event.getCurrentItem();

					if(!item.getType().equals(Material.AIR)) {

						// Test if it has ItemMeta
						if(item.hasItemMeta()) {

							// Check if it has a lore
							if(item.getItemMeta().hasLore()) {

								// Check if the lore is the crate lore size (6 long)

								if(item.getItemMeta().getLore().size() == 6) {

									// Get the cords & world name from the item lore
									String x = ChatColor.stripColor(item.getItemMeta().getLore().get(2)).replace("X: ", "");
									String y = ChatColor.stripColor(item.getItemMeta().getLore().get(3)).replace("Y: ", "");
									String z = ChatColor.stripColor(item.getItemMeta().getLore().get(4)).replace("Z: ", "");
									String worldName = ChatColor.stripColor(item.getItemMeta().getLore().get(5)).replace("World: ", "");

									// Get an instance of the ACPlayer
									ACPlayer player = new ACPlayer((Player) event.getWhoClicked());


									// Check if the world still exits
									if(Bukkit.getWorld(worldName) != null) {
										Location location = new Location(Bukkit.getWorld(worldName), Double.parseDouble(x)+0.5, Double.parseDouble(y)+1, Double.parseDouble(z)+0.5);
										player.getPlayer().teleport(location);
									} else {
										// If the world doesn't exist send the player a message
										player.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.EVENT_CRATES_NO_WORLD));
									}

								}

							}

						}

					}
				}

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
			case CRATES_SELECTOR: // If its the CRATES_SELECTOR menu

				// Open the CRATES_SELECTOR menu for the player
				player.openInventory(this.crateSelectorMenu);

		}
	}

}
