package com.HamiStudios.ArchonCrates.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.HamiStudios.ArchonCrates.Util.UpdateChecker;

public class CheckUpdates {

	public static void run(CommandSender sender) {
		sender.sendMessage(" ");
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &a&nArchonCrates Update Check:"));
		sender.sendMessage(" ");
		UpdateChecker updateChecker = new UpdateChecker("http://dev.bukkit.org/bukkit-plugins/archoncrates/files.rss");
		
		String currentVersion = Bukkit.getPluginManager().getPlugin("ArchonCrates").getDescription().getVersion();
		
		if(updateChecker.isBetaVersion()) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &cYou are running a BETA version of ArchonCrates! This may \n" + "     cause errors. BE WARNED!"));
		}
		if(!(updateChecker.isBetaVersion()) && updateChecker.needsUpdated()) {
			String newVersion = updateChecker.getLatestVersion();
			String newLink = updateChecker.getLatestLink();
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &cThere is a new version of ArchonCrates out! (" + newVersion + ")"));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &7You can download it from here: " + newLink));
		}
		if(!(updateChecker.isBetaVersion()) && !(updateChecker.needsUpdated())) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &aYou are running the newest version of ArchonCrates! (" + currentVersion + ")"));
		}
		sender.sendMessage(" ");
	}
	
}
