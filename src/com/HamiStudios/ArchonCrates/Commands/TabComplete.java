package com.HamiStudios.ArchonCrates.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.HamiStudios.ArchonCrates.Files.FileHandler;
import com.HamiStudios.ArchonCrates.Util.ACPermission;
import com.HamiStudios.ArchonCrates.Util.FileType;
import com.HamiStudios.ArchonCrates.Util.TabCompletionHelper;

public class TabComplete implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String commandlabel, String[] args) {
		
		if(sender.hasPermission(ACPermission.COMPLETE_TAB.toString())) {

			if(cmd.getName().equalsIgnoreCase("archoncrates")) {
				
				if(args.length == 0 || args.length == 1) {
					return TabCompletionHelper.getPossibleCompletionsForGivenArgs(args, new String[] 
							{"key", "vkey", "vkeyr", "create", "createv", "remove", "info", "keys", "vkeys", "crates", "crate", "reload", "files", "update", "history", "nvouch"}, false);
				}
				else if(args.length == 2) {
					if(args[0].equalsIgnoreCase("key")) {
						return TabCompletionHelper.getPossibleCompletionsForGivenArgs(args, new String[] {"all"}, true);
					}
					if(args[0].equalsIgnoreCase("vkey")) {
						return TabCompletionHelper.getPossibleCompletionsForGivenArgs(args, new String[] {"all"}, true);
					}
					if(args[0].equalsIgnoreCase("create")) {
						ArrayList<String> crateTypes = new ArrayList<>();
						for(String s : FileHandler.getSection(FileType.CRATES, "Crates").getKeys(false)) crateTypes.add(s);
						return TabCompletionHelper.getPossibleCompletionsForGivenArgs(args, crateTypes, false);
					}
					if(args[0].equalsIgnoreCase("crate")) {
						return TabCompletionHelper.getOnlinePlayerNames(args);
					}
					if(args[0].equalsIgnoreCase("reload")) {
						return TabCompletionHelper.getPossibleCompletionsForGivenArgs(args, new String[] {"CONFIG" , "CRATE_LOOT" , "KEYS" , "VIRTUAL_KEY", "CRATES" , "LOCATIONS" , "DATA" , "MOB_DROPS" , "LANGUAGE" , "PERMISSIONS" , "PLAYER_LOG" , "PRIZE_LOG" , "BUY_SIGN" , "CUSTOM_GUI", "ALL"}, false);
					}
					if(args[0].equalsIgnoreCase("history")) {
						return TabCompletionHelper.getOnlinePlayerNames(args);
					}
					if(args[0].equalsIgnoreCase("nvouch")) {
						return TabCompletionHelper.getOnlinePlayerNames(args);
					}
				}
				else if(args.length == 3) {
					if(args[0].equalsIgnoreCase("key")) {
						ArrayList<String> keyTypes = new ArrayList<>();
						for(String s : FileHandler.getSection(FileType.KEYS, "Keys").getKeys(false)) keyTypes.add(s);
						return TabCompletionHelper.getPossibleCompletionsForGivenArgs(args, keyTypes, false);
					}
					if(args[0].equalsIgnoreCase("vkey")) {
						ArrayList<String> keyTypes = new ArrayList<>();
						for(String s : FileHandler.getSection(FileType.VIRTUAL_KEYS, "Virtual Keys").getKeys(false)) keyTypes.add(s);
						return TabCompletionHelper.getPossibleCompletionsForGivenArgs(args, keyTypes, false);
					}
					if(args[0].equalsIgnoreCase("vkeyr")) {
						ArrayList<String> keyTypes = new ArrayList<>();
						for(String s : FileHandler.getSection(FileType.VIRTUAL_KEYS, "Virtual Keys").getKeys(false)) keyTypes.add(s);
						return TabCompletionHelper.getPossibleCompletionsForGivenArgs(args, keyTypes, false);
					}
					if(args[0].equalsIgnoreCase("crates")) {
						ArrayList<String> crateTypes = new ArrayList<>();
						for(String s : FileHandler.getSection(FileType.CRATES, "Crates").getKeys(false)) crateTypes.add(s);
						return TabCompletionHelper.getPossibleCompletionsForGivenArgs(args, crateTypes, false);
					}
					if(args[0].equalsIgnoreCase("crate")) {
						ArrayList<String> crateTypes = new ArrayList<>();
						for(String s : FileHandler.getSection(FileType.CRATES, "Crates").getKeys(false)) crateTypes.add(s);
						return TabCompletionHelper.getPossibleCompletionsForGivenArgs(args, crateTypes, false);
					}
					if(args[0].equalsIgnoreCase("nvouch")) {
						ArrayList<String> keyTypes = new ArrayList<>();
						for(String s : FileHandler.getSection(FileType.KEYS, "Keys").getKeys(false)) keyTypes.add(s);
						for(String s : FileHandler.getSection(FileType.VIRTUAL_KEYS, "Virtual Keys").getKeys(false)) keyTypes.add(s);
						return TabCompletionHelper.getPossibleCompletionsForGivenArgs(args, keyTypes, false);
					}
				}
				else if(args.length == 4) {
					if(args[0].equalsIgnoreCase("crate")) {
						ArrayList<String> keyTypes = new ArrayList<>();
						for(String s : FileHandler.getSection(FileType.KEYS, "Keys").getKeys(false)) keyTypes.add(s);
						return TabCompletionHelper.getPossibleCompletionsForGivenArgs(args, keyTypes, false);
					}
				}
				else if(args.length == 5) {
					if(args[0].equalsIgnoreCase("nvouch")) {
						return TabCompletionHelper.getPossibleCompletionsForGivenArgs(args, new String[] {"true", "false", "yes", "no", "y", "n"}, false);
					}
				}
			
			}
		}
		
		return null;
	}
		
}
