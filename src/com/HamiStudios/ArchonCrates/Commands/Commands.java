package com.HamiStudios.ArchonCrates.Commands;

import com.HamiStudios.ArchonCrates.API.Enums.LanguageType;
import com.HamiStudios.ArchonCrates.API.Enums.Permissions;
import com.HamiStudios.ArchonCrates.API.libs.ACSender;
import com.HamiStudios.ArchonCrates.API.libs.LanguageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender commandSender, Command cmd, String commandLabel, String[] args) {

		// Create instance of ACSender
		ACSender sender = new ACSender(commandSender);

		// Checks if the command sender has the permission to use ArchonCrate commands
		if(sender.hasPermission(Permissions.COMMAND_USE.value())) {

			// Sender entered /archoncrates
			if(cmd.getName().equalsIgnoreCase("archoncrates")) {

				if(args.length == 0) {
					// No Arguments Provided & check for help command permissions
					if(sender.hasPermission(Permissions.COMMAND_HELP.value())) {
						HelpCommand helpCommand = new HelpCommand();
						helpCommand.displayHelp(sender);
					} else {
						// If no permissions send NO_PERMISSION message
						sender.sendMessage(LanguageManager.get(LanguageType.PREFIX) + LanguageManager.get(LanguageType.NO_PERMISSION));
					}

				} else if(args.length == 1) {
					// 1 Argument Provided

					// Check if command was "help"
					if(args[0].equalsIgnoreCase("help")) {
						// Display help page for help command (lel)
						HelpCommand helpCommand = new HelpCommand();
						helpCommand.displayHelp(sender);
					} else {
						// If the command is not "help" display INVALID_COMMAND message
						sender.sendMessage(LanguageManager.get(LanguageType.PREFIX) + LanguageManager.get(LanguageType.INVALID_COMMAND));
					}
				} else if(args.length == 2) {
					// 2 Arguments Provided

					// Switch through all 2 argument length commands
					switch (args[0].toLowerCase()) {
						case "help": // Match help command

							// Run the help command
							HelpCommand helpCommand = new HelpCommand();
							helpCommand.execCommand(new String[]{args[1]}, sender);

							break;
						default:
							// If no command matched display INVALID_COMMAND message
							sender.sendMessage(LanguageManager.get(LanguageType.PREFIX) + LanguageManager.get(LanguageType.INVALID_COMMAND));
							break;
					}

				} else if(args.length == 3) {
					// 3 Arguments Provided

					// Switch through all 3 argument length commands
					switch (args[0].toLowerCase()) {
						case "create": // Match create command

							// Run the create command
							CreateCommand createCommand = new CreateCommand();
							createCommand.execCommand(new String[]{args[1], args[2]}, sender);

							break;
						default:
							// If no command matched display INVALID_COMMAND message
							sender.sendMessage(LanguageManager.get(LanguageType.PREFIX) + LanguageManager.get(LanguageType.INVALID_COMMAND));
							break;
					}
				} else {
					// If arguments length is larger then 3 there is no commands so display INVALID_COMMAND message
					sender.sendMessage(LanguageManager.get(LanguageType.PREFIX) + LanguageManager.get(LanguageType.INVALID_COMMAND));
				}

				return true;
			}
		}

		return false;
	}

}
