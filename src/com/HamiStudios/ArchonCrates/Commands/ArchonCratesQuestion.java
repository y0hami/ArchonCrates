package com.HamiStudios.ArchonCrates.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ArchonCratesQuestion {

	public static void run(CommandSender sender) {
		sender.sendMessage(ChatColor.GREEN + "This server is running ArchonCrates! " + ChatColor.GRAY + "\nVersion: " + Bukkit.getServer().getPluginManager().getPlugin("ArchonCrates").getDescription().getVersion());
	}
	
}
