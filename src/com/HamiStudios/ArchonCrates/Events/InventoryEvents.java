package com.HamiStudios.ArchonCrates.Events;

import com.HamiStudios.ArchonCrates.API.Enums.Menu;
import com.HamiStudios.ArchonCrates.API.Menus.CratesMenu;
import com.HamiStudios.ArchonCrates.API.Menus.CreateMenu;
import com.HamiStudios.ArchonCrates.API.Menus.KeyMenu;
import com.HamiStudios.ArchonCrates.API.Objects.ACPlayer;
import com.HamiStudios.ArchonCrates.API.Operations.CrateRoll;
import com.HamiStudios.ArchonCrates.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

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
	public void onInventoryEvent(final InventoryClickEvent event) {

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
		} else if (ChatColor.stripColor(event.getInventory().getTitle()).equalsIgnoreCase(Menu.KEY_TYPE.getTitle())) {
			// The inventory is the key type menu

			KeyMenu keyMenu = new KeyMenu(this.main);
			keyMenu.event(Menu.KEY_TYPE, event);

			event.setCancelled(true);
		} else if (ChatColor.stripColor(event.getInventory().getTitle()).equalsIgnoreCase(Menu.GIVE_KEY.getTitle())) {
			// The inventory is the key menu

			KeyMenu keyMenu = new KeyMenu(this.main);
			keyMenu.event(Menu.GIVE_KEY, event);

			event.setCancelled(true);
		} else if (ChatColor.stripColor(event.getInventory().getTitle()).equalsIgnoreCase(Menu.GIVE_VIRTUAL_KEY.getTitle())) {
			// The inventory is the virtual key menu

			KeyMenu keyMenu = new KeyMenu(this.main);
			keyMenu.event(Menu.GIVE_VIRTUAL_KEY, event);

			event.setCancelled(true);
		} else if (ChatColor.stripColor(event.getInventory().getTitle()).equalsIgnoreCase(Menu.CRATES_WORLDS.getTitle())) {
			// The inventory is the crates world menu

			CratesMenu cratesMenu = new CratesMenu();
			cratesMenu.event(Menu.CRATES_WORLDS, event);

			event.setCancelled(true);
		} else if (ChatColor.stripColor(event.getInventory().getTitle()).equalsIgnoreCase(Menu.CRATES_SELECTOR.getTitle())) {
			// The inventory is the crates selector menu

			CratesMenu cratesMenu = new CratesMenu();
			cratesMenu.event(Menu.CRATES_SELECTOR, event);

			event.setCancelled(true);
		} else {
			// If its a crate menu
			if(this.main.getOperationsManager().isCrateRoller(new ACPlayer((Player) event.getWhoClicked()))) {
				// Once closed, reopen it (Prevent the player from closing the menu)
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onInventoryClose(final InventoryCloseEvent event) {

		// Check if the inventory in which is clicked is a ArchonCrates menu

		Player player = (Player) event.getPlayer();

		if (ChatColor.stripColor(event.getInventory().getTitle()).equalsIgnoreCase(Menu.KEY_TYPE.getTitle())) {
			// The inventory is the key menu

			if(this.main.getOperationsManager().isKeyGiver(new ACPlayer(player))) {
				this.main.getOperationsManager().removeKeyGiver(new ACPlayer(player));
			}

		} else if (ChatColor.stripColor(event.getInventory().getTitle()).equalsIgnoreCase(Menu.GIVE_KEY.getTitle())) {
			// The inventory is the key menu

			if(this.main.getOperationsManager().isKeyGiver(new ACPlayer(player))) {
				this.main.getOperationsManager().removeKeyGiver(new ACPlayer(player));
			}

		} else if (ChatColor.stripColor(event.getInventory().getTitle()).equalsIgnoreCase(Menu.GIVE_VIRTUAL_KEY.getTitle())) {
			// The inventory is the virtual key menu

			if(this.main.getOperationsManager().isKeyGiver(new ACPlayer(player))) {
				this.main.getOperationsManager().removeKeyGiver(new ACPlayer(player));
			}

		} else {
			// If its a crate menu
			ACPlayer acplayer = new ACPlayer((Player) event.getPlayer());

			if(this.main.getOperationsManager().isCrateRoller(acplayer)) {
				CrateRoll crateRoller = this.main.getOperationsManager().getCrateRoller(acplayer);

				// If the crate roller can't exit the inventory
				if(!crateRoller.canExit()) {
					// Once closed, reopen it (Prevent the player from closing the menu)
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
						@Override
						public void run() {
							event.getPlayer().openInventory(event.getInventory());
						}
					}, 1);
				} else {
					// If they are crate roller but they can close there menu, remove them from crate rollers
					this.main.getOperationsManager().getCrateRoller(acplayer).cancelAutoClose();
					this.main.getOperationsManager().removeCrateRoller(acplayer);
				}
			}
		}

	}

}
