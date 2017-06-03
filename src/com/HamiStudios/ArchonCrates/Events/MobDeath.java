package com.HamiStudios.ArchonCrates.Events;

import com.HamiStudios.ArchonCrates.API.Enums.Permissions;
import com.HamiStudios.ArchonCrates.API.Exceptions.NoValueException;
import com.HamiStudios.ArchonCrates.API.Libs.Glow;
import com.HamiStudios.ArchonCrates.API.Libs.ItemBuilder;
import com.HamiStudios.ArchonCrates.API.Objects.Key;
import com.HamiStudios.ArchonCrates.Files.KeyDrop;
import com.HamiStudios.ArchonCrates.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.ArrayList;
import java.util.Random;

public class MobDeath implements Listener {

	// Creates an instance of the Main class
	private Main main;

	public MobDeath(Main main) {
		// Sets the instance of the Main class
		this.main = main;

		// Registers the event
		this.main.getServer().getPluginManager().registerEvents(this, this.main);
	}

	// On Mob Death
	@EventHandler
	public void mobDeath(EntityDeathEvent event) {
		if(!(event.getEntity() instanceof Player)) {
			if(event.getEntity().getKiller() != null) {

				KeyDrop keyDrop = new KeyDrop();

				try {
					if(((boolean) keyDrop.get("Mobs.enabled"))) {

						if(event.getEntity().getKiller().hasPermission(Permissions.MOB_DROP_KEY.value())) {

							for (String mobType : keyDrop.getFileConfiguration().getConfigurationSection("Mobs.drops").getKeys(false)) {
								if(mobType.equals(event.getEntity().getType().toString())) {

									// Make an array for all the keys the entity can drop
									ArrayList<Key> keys = new ArrayList<>();

									// Create an instance of Random
									Random random = new Random();

									// Create the drop equalizer
									double dropEqualizer = 0 + (10) * random.nextDouble();

									// For all the keys for the entity in the file
									for (String keyID : keyDrop.getFileConfiguration().getConfigurationSection("Mobs.drops." + mobType).getKeys(false)) {
										Key key = new Key(keyID);
										// If the key is valid
										if(key.valid()) {
											// If the key chance of dropping is more or equal to the dropEqualizer
											if((double) keyDrop.get("Mobs.drops." + mobType + "." + keyID) >= dropEqualizer) {
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

										// Drop the key at the entity location
										event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), keyItem.build());
									}

									break;
								}
							}

						}

					}
				} catch(NoValueException e) { }

			}
		}
	}

}
