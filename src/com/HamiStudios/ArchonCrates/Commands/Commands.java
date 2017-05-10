package com.HamiStudios.ArchonCrates.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.HamiStudios.ArchonCrates.API.Enums.LanguageType;
import com.HamiStudios.ArchonCrates.API.Enums.Permissions;
import com.HamiStudios.ArchonCrates.API.libs.ACSender;
import com.HamiStudios.ArchonCrates.API.libs.LanguageManager;
import com.HamiStudios.ArchonCrates.Files.Crates;

public class Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender commandSender, Command cmd, String commandLabel, String[] args) {

		ACSender sender = new ACSender(commandSender);
		
		// Sender entered /archoncrates
		if(cmd.getName().equalsIgnoreCase("archoncrates")) {
			
			if(args.length == 0) {
				// No Arguments Provided
				if(sender.hasPermission(Permissions.COMMAND_HELP.value())) {
					sender.sendSpace();
					sender.sendMessage("&7====[ &5ArchonCrates Help &7]====");
					sender.sendMessage("&f/archoncrates help <command>");
					sender.sendSpace();
					sender.sendMessage("&5Commands:");
					sender.sendMessage("  &7- &fcreate");
					sender.sendMessage("  &7- &fkey");
					sender.sendMessage("  &7- &flist");
					sender.sendMessage("  &7- &fcrates");
					sender.sendSpace();
					sender.sendSpace();
				} else {
					sender.sendMessage(LanguageManager.get(LanguageType.PREFIX) + LanguageManager.get(LanguageType.NO_PERMISSION));
				}
				
			} else if(args.length == 1) {
				// 1 Argument Provided
			} else if(args.length == 2) {
				// 2 Arguments Provided
					
				switch (args[0].toLowerCase()) {
				case "help":
					switch (args[1].toLowerCase()) {
						case "create":
							sender.sendSpace();
							sender.sendMessage("&7====[ &5Create Help &7]====");
							sender.sendSpace();
							sender.sendMessage("");
							break;
						case "key":
							break;
						case "list":
							break;
						case "crates":
							break;
						default:
							break;
					}
					break;

				default:
					break;
				}
				
			} else if(args.length == 3) {
				// 3 Arguments Provided
				switch (args[0].toLowerCase()) {
					case "create":
						if(args[1].equalsIgnoreCase("physical") || args[1].equalsIgnoreCase("p")) {
							
							
							
						} else if(args[1].equalsIgnoreCase("virtual") || args[1].equalsIgnoreCase("v")) {
							
						} else {
							sender.sendMessage(LanguageManager.get(LanguageType.PREFIX) + LanguageManager.get(LanguageType.COMMANDS_CREATE_INVALID_TYPE));
						}
						break;
	
					default:
						break;
				}
			} else {
				sender.sendMessage(LanguageManager.get(LanguageType.PREFIX) + LanguageManager.get(LanguageType.INVALID_COMMAND));
			}
			
			return true;
		}
		
		return false;
	}

}
