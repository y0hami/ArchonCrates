package com.HamiStudios.ArchonCrates.Commands;

import org.bukkit.command.CommandSender;

import com.HamiStudios.ArchonCrates.Files.FileHandler;
import com.HamiStudios.ArchonCrates.Util.FileType;
import com.HamiStudios.ArchonCrates.Util.LanguageType;
import com.HamiStudios.ArchonCrates.Util.ReloadType;

public class Reload {

	public static void run(ReloadType type, CommandSender sender) {
		type.reload();
		
		if(type.equals(ReloadType.ALL)) FileHandler.reloadAll();
		if(type.equals(ReloadType.CRATE_LOOT)) FileHandler.reload(FileType.CRATE_LOOT);
		if(type.equals(ReloadType.CRATES)) FileHandler.reload(FileType.CRATES);
		if(type.equals(ReloadType.KEYS)) FileHandler.reload(FileType.KEYS);
		if(type.equals(ReloadType.DATA)) FileHandler.reload(FileType.DATA);
		if(type.equals(ReloadType.LOCATIONS)) FileHandler.reload(FileType.LOCATIONS);
		if(type.equals(ReloadType.MOB_DROPS)) FileHandler.reload(FileType.MOB_DROP);
		if(type.equals(ReloadType.LANGUAGE)) FileHandler.reload(FileType.LANGUAGE);
		if(type.equals(ReloadType.PERMISSIONS)) FileHandler.reload(FileType.PERMISSIONS);
		if(type.equals(ReloadType.PLAYER_LOG)) FileHandler.reload(FileType.PLAYER_LOG);
		if(type.equals(ReloadType.PRIZE_LOG)) FileHandler.reload(FileType.PRIZE_LOG);
		
		sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_RELOAD_DONE.toString(true));
	}
	
	public static boolean contains(String value) {
		if(value.toUpperCase().equals("ALL") || value.toUpperCase().equals("CONFIG") || value.toUpperCase().equals("CRATE_LOOT") || value.toUpperCase().equals("KEYS")
				 || value.toUpperCase().equals("CRATES") || value.toUpperCase().equals("DATA") || value.toUpperCase().equals("LOCATIONS") || value.toUpperCase().equals("MOB_DROPS")
				 || value.toUpperCase().equals("LANGUAGE") || value.toUpperCase().equals("PERMISSIONS") || value.toUpperCase().equals("PLAYER_LOG") || value.toUpperCase().equals("PRIZE_LOG")
				 || value.toUpperCase().equals("BUY_SIGN") || value.toUpperCase().equals("CRATE LOOT") || value.toUpperCase().equals("LOCATION") || value.toUpperCase().equals("MOB DROPS")
				 || value.toUpperCase().equals("MOB DROP") || value.toUpperCase().equals("PLAYER LOG") || value.toUpperCase().equals("PRIZE LOG") || value.toUpperCase().equals("BUY SIGN") 
				 || value.toUpperCase().equals("CUSTOM_GUI")) {
			return true;
		}
		return false;
	}
	
	public static ReloadType getReloadType(String value) {
		if(value.toUpperCase().equalsIgnoreCase("ALL")) {
			return ReloadType.ALL;
		}
		if(value.toUpperCase().equalsIgnoreCase("CONFIG")) {
			return ReloadType.CONFIG;
		}
		if(value.toUpperCase().equalsIgnoreCase("CRATE_LOOT")) {
			return ReloadType.CRATE_LOOT;
		}
		if(value.toUpperCase().equalsIgnoreCase("CRATE LOOT")) {
			return ReloadType.CRATE_LOOT;
		}
		if(value.toUpperCase().equalsIgnoreCase("KEYS")) {
			return ReloadType.KEYS;
		}
		if(value.toUpperCase().equalsIgnoreCase("CRATES")) {
			return ReloadType.CRATES;
		}
		if(value.toUpperCase().equalsIgnoreCase("DATA")) {
			return ReloadType.DATA;
		}
		if(value.toUpperCase().equalsIgnoreCase("LOCATIONS")) {
			return ReloadType.LOCATIONS;
		}
		if(value.toUpperCase().equalsIgnoreCase("LOCATION")) {
			return ReloadType.LOCATIONS;
		}
		if(value.toUpperCase().equalsIgnoreCase("MOB_DROPS")) {
			return ReloadType.MOB_DROPS;
		}
		if(value.toUpperCase().equalsIgnoreCase("MOB DROPS")) {
			return ReloadType.MOB_DROPS;
		}
		if(value.toUpperCase().equalsIgnoreCase("MOB DROP")) {
			return ReloadType.MOB_DROPS;
		}
		if(value.toUpperCase().equalsIgnoreCase("LANGUAGE")) {
			return ReloadType.LANGUAGE;
		}
		if(value.toUpperCase().equalsIgnoreCase("PERMISSIONS")) {
			return ReloadType.PERMISSIONS;
		}
		if(value.toUpperCase().equalsIgnoreCase("PLAYER_LOG")) {
			return ReloadType.PLAYER_LOG;
		}
		if(value.toUpperCase().equalsIgnoreCase("PLAYER LOG")) {
			return ReloadType.PLAYER_LOG;
		}
		if(value.toUpperCase().equalsIgnoreCase("PRIZE_LOG")) {
			return ReloadType.PRIZE_LOG;
		}
		if(value.toUpperCase().equalsIgnoreCase("PRIZE LOG")) {
			return ReloadType.PRIZE_LOG;
		}
		if(value.toUpperCase().equalsIgnoreCase("BUY_SIGN")) {
			return ReloadType.BUY_SIGN;
		}
		if(value.toUpperCase().equalsIgnoreCase("BUY SIGN")) {
			return ReloadType.BUY_SIGN;
		}
		if(value.toUpperCase().equalsIgnoreCase("CUSTOM_GUI")) {
			return ReloadType.CUSTOM_GUI;
		}
		return null;
	}
	
}
