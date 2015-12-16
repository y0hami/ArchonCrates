package com.HamiStudios.ArchonCrates.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ListFiles {

	public static void run(CommandSender sender) {		
		sender.sendMessage("");
		sender.sendMessage(ChatColor.GREEN + "" + ChatColor.UNDERLINE + "ArchonCrates File Handling: ");
		sender.sendMessage("");
		sender.sendMessage("   " + ChatColor.WHITE + "CONFIG" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Main configuration file");
		sender.sendMessage("   " + ChatColor.WHITE + "CRATE_LOOT" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Prize configuration file");
		sender.sendMessage("   " + ChatColor.WHITE + "KEYS" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Key configuration file");
		sender.sendMessage("   " + ChatColor.WHITE + "CRATES" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Crate configuration file");
		sender.sendMessage("   " + ChatColor.WHITE + "LOCATIONS" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Sign & Crate location file");
		sender.sendMessage("   " + ChatColor.WHITE + "DATA" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Data storage file");
		sender.sendMessage("   " + ChatColor.WHITE + "MOB_DROPS" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Mob Drop configuration");
		sender.sendMessage("   " + ChatColor.WHITE + "LANGUAGE" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Language configuration file");
		sender.sendMessage("   " + ChatColor.WHITE + "PERMISSIONS" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Permissions configuration file");
		sender.sendMessage("   " + ChatColor.WHITE + "PLAYER_LOG" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Player prize logs");
		sender.sendMessage("   " + ChatColor.WHITE + "PRIZE_LOG" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Prize logs");
		sender.sendMessage("   " + ChatColor.WHITE + "BUY_SIGN" + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "Buy sign configuration file");
		sender.sendMessage("");
	}
	
}
