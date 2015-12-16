package com.HamiStudios.ArchonCrates.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.HamiStudios.ArchonCrates.Files.FileHandler;
import com.HamiStudios.ArchonCrates.Util.FileType;
import com.HamiStudios.ArchonCrates.Util.LanguageType;
import com.HamiStudios.ArchonCrates.Util.PlayerData;

public class PrizeHistory {

	public static void run(CommandSender sender, String playername) {
		String uuid = PlayerData.getUUID(playername);
		if(uuid != null) {
			String name = PlayerData.getName(uuid);
			sender.sendMessage("");
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&nArchonCrates History: "));
			sender.sendMessage("");
			
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "   &e" + name + "&7: "));

			if(FileHandler.getFile(FileType.PLAYER_LOG).contains("logs." + uuid)) {
				for(String s : FileHandler.getFile(FileType.PLAYER_LOG).getConfigurationSection("logs." + uuid).getKeys(false)) {
					int won = FileHandler.getFile(FileType.PLAYER_LOG).getInt("logs." + uuid + "." + s +".winAmount");
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &f" + s + " &8- &7Won &e" + won + " &7time(s)"));
				}
			}
			else {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &cThis player has no prize history"));
			}
			
			sender.sendMessage("");
		}
		else{
			sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_HISTORY_INVALID_PLAYER.toString(true));
		}
	}
	
}
