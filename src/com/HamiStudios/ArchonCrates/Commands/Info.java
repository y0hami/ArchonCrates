package com.HamiStudios.ArchonCrates.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Info {

	public static void run(CommandSender sender) {
		
		sender.sendMessage("");
		sender.sendMessage(ChatColor.GREEN + "" + ChatColor.UNDERLINE + "ArchonCrates Info Page: ");
		sender.sendMessage("");
		sender.sendMessage("   " + ChatColor.WHITE + "Author" + ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + "hammy2899");
		sender.sendMessage("   " + ChatColor.WHITE + "Version" + ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + Bukkit.getPluginManager().getPlugin("ArchonCrates").getDescription().getVersion());
		sender.sendMessage("   " + ChatColor.WHITE + "Bukkit Page" + ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + "\n   http://dev.bukkit.org/bukkit-plugins/ArchonCrates/");
		sender.sendMessage("   " + ChatColor.WHITE + "Author's Website" + ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + "http://www.HamiStudios.com");
		sender.sendMessage("");
		
	}
	
}
