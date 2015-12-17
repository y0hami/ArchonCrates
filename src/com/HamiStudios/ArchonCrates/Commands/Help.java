package com.HamiStudios.ArchonCrates.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Help {

	public static void run(CommandSender sender) {
		sender.sendMessage("");
		sender.sendMessage(ChatColor.GREEN + "" + ChatColor.UNDERLINE + "ArchonCrates Help Page: ");
		sender.sendMessage("");
		sender.sendMessage("   " + ChatColor.WHITE + "/archoncrates" + ChatColor.DARK_GRAY + "\n" + "   " + "  " + " - " + ChatColor.GRAY + "Shows the help page");
		sender.sendMessage("   " + ChatColor.WHITE + "/archoncrates key <player|all> <keyType> <amount>" + ChatColor.DARK_GRAY + "\n" + "   " + "  " + " - " + ChatColor.GRAY + "Give a player/all players a key");
		sender.sendMessage("   " + ChatColor.WHITE + "/archoncrates vkey <player|all> <vkeyType> <amount>" + ChatColor.DARK_GRAY + "\n" + "   " + "  " + " - " + ChatColor.GRAY + "Give a player/all players a virtual key");
		sender.sendMessage("   " + ChatColor.WHITE + "/archoncrates vkeyr <player> <vkeyType> <amount>" + ChatColor.DARK_GRAY + "\n" + "   " + "  " + " - " + ChatColor.GRAY + "Remove a virtual key from a player");
		sender.sendMessage("   " + ChatColor.WHITE + "/archoncrates create <crateType>" + ChatColor.DARK_GRAY + "\n" + "   " + "  " + " - " + ChatColor.GRAY + "Gives you the crate for you to place");
		sender.sendMessage("   " + ChatColor.WHITE + "/archoncrates createv <vcrateType>" + ChatColor.DARK_GRAY + "\n" + "   " + "  " + " - " + ChatColor.GRAY + "Gives you the virtual crate for you to place");
		sender.sendMessage("   " + ChatColor.WHITE + "/archoncrates remove" + ChatColor.DARK_GRAY + "\n" + "   " + "  " + " - " + ChatColor.GRAY + "Allows you to remove a crate");
		sender.sendMessage("   " + ChatColor.WHITE + "/archoncrates info" + ChatColor.DARK_GRAY + "\n" + "   " + "  " + " - " + ChatColor.GRAY + "Shows you the plugin info");
		sender.sendMessage("   " + ChatColor.WHITE + "/archoncrates keys" + ChatColor.DARK_GRAY + "\n" + "   " + "  " + " - " + ChatColor.GRAY + "Shows you a list of all the keys");
		sender.sendMessage("   " + ChatColor.WHITE + "/archoncrates vkeys" + ChatColor.DARK_GRAY + "\n" + "   " + "  " + " - " + ChatColor.GRAY + "Shows you a list of all the virtual keys");
		sender.sendMessage("   " + ChatColor.WHITE + "/archoncrates crates" + ChatColor.DARK_GRAY + "\n" + "   " + "  " + " - " + ChatColor.GRAY + "Shows you a list of all the crates");
		sender.sendMessage("   " + ChatColor.WHITE + "/archoncrates crate <player> <crateType> <keyType>" + ChatColor.DARK_GRAY + "\n" + "   " + "  " + " - " + ChatColor.GRAY + "Open a virtual crate");
		sender.sendMessage("   " + ChatColor.WHITE + "/archoncrates reload <fileType|all>" + ChatColor.DARK_GRAY + "\n" + "   " + "  " + " - " + ChatColor.GRAY + "Reloads the file/all files");
		sender.sendMessage("   " + ChatColor.WHITE + "/archoncrates files" + ChatColor.DARK_GRAY + "\n" + "   " + "  " + " - " + ChatColor.GRAY + "Shows a list of all the files");
		sender.sendMessage("   " + ChatColor.WHITE + "/archoncrates update" + ChatColor.DARK_GRAY + "\n" + "   " + "  " + " - " + ChatColor.GRAY + "Checks for plugin updates");
		sender.sendMessage("   " + ChatColor.WHITE + "/archoncrates history <player>" + ChatColor.DARK_GRAY + "\n" + "   " + "  " + " - " + ChatColor.GRAY + "Shows a list of prizes which that player has won");
		sender.sendMessage("   " + ChatColor.WHITE + "/archoncrates nvouch <player> <keyType> <amount> <virtual>" + ChatColor.DARK_GRAY + "\n" + "   " + "  " + " - " + ChatColor.GRAY + "Gives the player you enter a voucher");
		sender.sendMessage("");
	}
	
}