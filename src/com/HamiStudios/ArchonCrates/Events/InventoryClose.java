package com.HamiStudios.ArchonCrates.Events;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import com.HamiStudios.ArchonCrates.Main;
import com.HamiStudios.ArchonCrates.Files.Data;
import com.HamiStudios.ArchonCrates.Files.FileHandler;
import com.HamiStudios.ArchonCrates.Util.FileType;

public class InventoryClose implements Listener {

	private Main main;
	
	public InventoryClose(Main main) {
		this.main = main;
		this.main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onInventoryClose(final InventoryCloseEvent event) {
		ArrayList<String> crateTitles = new ArrayList<>();
		for(String s : FileHandler.getSection(FileType.CRATES, "Crates").getKeys(false)) {
			crateTitles.add(FileHandler.get(FileType.CRATES, "Crates." + s + ".title")+"");
		}
		crateTitles.add(ChatColor.translateAlternateColorCodes('&', ChatColor.GREEN + "Virtual Crate"));
		crateTitles.add(ChatColor.translateAlternateColorCodes('&', FileHandler.getFile(FileType.VIRTUAL_CRATES).getString("Virtual Crates.title")));
		
		boolean stop = false;
		for(String s : crateTitles) {
			if(event.getInventory().getTitle().equals(ChatColor.translateAlternateColorCodes('&', s))) {
				Data data = new Data();
				if(data.getFile().contains("data.inCrate." + event.getPlayer().getUniqueId())) {
					if(data.getFile().getBoolean("data.inCrate." + event.getPlayer().getUniqueId())) stop = true;
				}
			}
		}
		if(stop) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
				@Override
				public void run() {
					event.getPlayer().openInventory(event.getInventory());
				}
			}, 1);
		}
	}
	
}
