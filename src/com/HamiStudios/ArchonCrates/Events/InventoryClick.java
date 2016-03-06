package com.HamiStudios.ArchonCrates.Events;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.HamiStudios.ArchonCrates.Main;
import com.HamiStudios.ArchonCrates.API.Objects.VirtualKey;
import com.HamiStudios.ArchonCrates.API.Objects.Exceptions.InvalidVirtualKeyInput;
import com.HamiStudios.ArchonCrates.Files.FileHandler;
import com.HamiStudios.ArchonCrates.Tasks.Crate;
import com.HamiStudios.ArchonCrates.Util.FileType;
import com.HamiStudios.ArchonCrates.Util.PlayerData;

public class InventoryClick implements Listener {

	public InventoryClick(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		ArrayList<String> crateTitles = new ArrayList<>();
		for(String s : FileHandler.getSection(FileType.CRATES, "Crates").getKeys(false)) {
			crateTitles.add(FileHandler.get(FileType.CRATES, "Crates." + s + ".title")+"");
		}
		crateTitles.add(ChatColor.translateAlternateColorCodes('&', ChatColor.GREEN + "Virtual Crate"));
		crateTitles.add(ChatColor.translateAlternateColorCodes('&', FileHandler.getFile(FileType.CUSTOM_GUI).getString("Virtual Keys GUI.title")));
		crateTitles.add(ChatColor.translateAlternateColorCodes('&', FileHandler.getFile(FileType.VIRTUAL_CRATES).getString("Virtual Crates.title")));
		crateTitles.add(ChatColor.translateAlternateColorCodes('&', FileHandler.getFile(FileType.CUSTOM_GUI).getString("Chance GUI.title")));
		
		if(event.getCurrentItem() == null) return;
		
		if(event.getInventory().getTitle().equals(ChatColor.translateAlternateColorCodes('&', FileHandler.getFile(FileType.CUSTOM_GUI).getString("Virtual Keys GUI.title")))) {
			ArrayList<VirtualKey> vkeys = new ArrayList<>();
			for(String s : FileHandler.getFile(FileType.VIRTUAL_KEYS).getConfigurationSection("Virtual Keys").getKeys(false))
				try {
					vkeys.add(new VirtualKey(s));
				} catch (InvalidVirtualKeyInput e) {
					e.log(s);
					e.writeToFile(s);
				}
			
			if(event.getCurrentItem().hasItemMeta()) {
				ItemStack itemClicked = event.getCurrentItem();
				VirtualKey vkey = null;
				
				for(VirtualKey k : vkeys) {
					if(itemClicked.getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', k.getDisplayName()))) {
						if(itemClicked.getTypeId() == k.getItemId()) {
							if(itemClicked.getDurability() == (short)k.getItemData()) {
								if(itemClicked.getItemMeta().getLore().get(0).startsWith(ChatColor.GRAY + "Keys: ")) {
									try {
										vkey = new VirtualKey(k.getVKeyType());
									} catch (InvalidVirtualKeyInput e) {
										e.log(k.getVKeyType());
										e.writeToFile(k.getVKeyType());
									}
									break;
								}
							}
						}
					}
 				}
				if(vkey != null) {
					Player player = (Player) event.getWhoClicked();
					if(PlayerData.getVKeyCount(player.getUniqueId().toString(), vkey.getVKeyType()) >= 1) {
						event.setCancelled(true);
						PlayerData.takeVKey(player.getUniqueId().toString(), vkey.getVKeyType(), 1);
						Crate crate = new Crate();
						crate.openVirtual(player, vkey.getVKeyType());
						return;
					}
					else{
						event.setCancelled(true);
						player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BASS, 1, 1);
						return;
					}
				}
			}
			
		}
		
		boolean stop = false;
		for(String s : crateTitles) {
			if(event.getInventory().getTitle().equals(ChatColor.translateAlternateColorCodes('&', s))) stop = true;
		}
		if(stop) event.setCancelled(true);
	}
	
}
