package com.HamiStudios.ArchonCrates.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.HamiStudios.ArchonCrates.Main;
import com.HamiStudios.ArchonCrates.Files.FileHandler;
import com.HamiStudios.ArchonCrates.Util.FileType;
import com.HamiStudios.ArchonCrates.Util.LanguageType;
import com.HamiStudios.ArchonCrates.Util.PlayerData;
import com.HamiStudios.ArchonCrates.Util.UpdateChecker;

public class OnJoin implements Listener {

	private Main main;
	public OnJoin(Main main) {
		this.main = main;
		this.main.getServer().getPluginManager().registerEvents(this, this.main);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		PlayerData.addName(event.getPlayer());
		if(event.getPlayer().isOp()) {
			if(FileHandler.getFile(FileType.CONFIG).getBoolean("Check for updates")) {
				event.getPlayer().sendMessage(" ");
				UpdateChecker updateChecker = new UpdateChecker("http://dev.bukkit.org/bukkit-plugins/archoncrates/files.rss");
				String currentVersion = Bukkit.getPluginManager().getPlugin("ArchonCrates").getDescription().getVersion();
				
				if(updateChecker.isBetaVersion()) {
					event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + ChatColor.RED + "You are running a BETA version of ArchonCrates! This may cause errors. BE WARNED!");
				}
				if(!(updateChecker.isBetaVersion()) && updateChecker.needsUpdated()) {
					String newVersion = updateChecker.getLatestVersion();
					String newLink = updateChecker.getLatestLink();
					event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + ChatColor.RED + "There is a new version of ArchonCrates out! (" + newVersion + ")");
					event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + ChatColor.GRAY + "You can download it from here: " + newLink);
				}
				if(!(updateChecker.isBetaVersion()) && !(updateChecker.needsUpdated())) {
					event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + ChatColor.GREEN + "You are running the newest version of ArchonCrates! (" + currentVersion + ")");
				}
				event.getPlayer().sendMessage(" ");
			}
		}
	}
	
}