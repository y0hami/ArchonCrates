package com.HamiStudios.ArchonCrates.Events;

import com.HamiStudios.ArchonCrates.API.Libs.Fetcher;
import com.HamiStudios.ArchonCrates.API.Libs.Glow;
import com.HamiStudios.ArchonCrates.API.Libs.ItemBuilder;
import com.HamiStudios.ArchonCrates.API.Objects.Key;
import com.HamiStudios.ArchonCrates.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class PlayerInteraction implements Listener {

	// Creates an instance of the Main class
	private Main main;

	public PlayerInteraction(Main main) {
		// Sets the instance of the Main class
		this.main = main;

		// Registers the event
		this.main.getServer().getPluginManager().registerEvents(this, this.main);
	}

	// Left & Right Click Events
	@EventHandler
	public void OnInteraction(PlayerInteractEvent event) {

		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {

			if(event.getItem() != null) {
				if(event.getItem().hasItemMeta()) {
					if(event.getItem().getItemMeta().hasLore()) {

						for (Key key : Fetcher.getKeys()) {
							if(event.getItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', key.getName()))) {

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

								// If the key item is equal to the clicked item its a key
								if (clickItem.equals(keyItemStack)) {
									event.setCancelled(true);
									break;
								}

							}
						}

					}
				}
			}

		}

	}

	// Eat Event
	@EventHandler
	public void OnEat(PlayerItemConsumeEvent event) {
		if(event.getItem() != null) {
			if(event.getItem().hasItemMeta()) {
				if(event.getItem().getItemMeta().hasLore()) {

					for (Key key : Fetcher.getKeys()) {
						if(event.getItem().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', key.getName()))) {

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

							// Creates the item eaten as a new item instance
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

							// If the key item is equal to the item eaten its a key
							if (clickItem.equals(keyItemStack)) {
								event.setCancelled(true);
								break;
							}

						}
					}

				}
			}
		}
	}

}
