package com.HamiStudios.ArchonCrates.Events;

import com.HamiStudios.ArchonCrates.API.Enums.Menu;
import com.HamiStudios.ArchonCrates.API.Menus.CreateMenu;
import com.HamiStudios.ArchonCrates.Main;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryEvents implements Listener {

	// Creates an instance of the Main class
	private Main main;

	public InventoryEvents(Main main) {
		// Sets the instance of the Main class
		this.main = main;

		// Registers the event
		this.main.getServer().getPluginManager().registerEvents(this, this.main);
	}

	@EventHandler
	public void onInventoryEvent(InventoryClickEvent event) {

		// Check if the inventory in which is clicked is a ArchonCrates menu

		if(ChatColor.stripColor(event.getInventory().getTitle()).equalsIgnoreCase(Menu.CRATE_TYPE.getTitle())) {
			// The inventory is the crate type menu

			CreateMenu createMenu = new CreateMenu();
			createMenu.event(Menu.CRATE_TYPE, event);

			event.setCancelled(true);

		} else if (ChatColor.stripColor(event.getInventory().getTitle()).equalsIgnoreCase(Menu.CREATE_CRATE.getTitle())) {
			// The inventory is the create menu

			CreateMenu createMenu = new CreateMenu();
			createMenu.event(Menu.CREATE_CRATE, event);

			event.setCancelled(true);
		}
	}

}
