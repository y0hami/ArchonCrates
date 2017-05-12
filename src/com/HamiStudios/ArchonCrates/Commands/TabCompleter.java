package com.HamiStudios.ArchonCrates.Commands;

import com.HamiStudios.ArchonCrates.API.Enums.Permissions;
import com.HamiStudios.ArchonCrates.API.Objects.Crate;
import com.HamiStudios.ArchonCrates.API.libs.ACSender;
import com.HamiStudios.ArchonCrates.API.libs.Fetcher;
import com.HamiStudios.ArchonCrates.API.libs.TabCompletionHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class TabCompleter  implements org.bukkit.command.TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender commandSender, Command command, String commandLabel, String[] args) {

		// Create instance of ACSender
		ACSender sender = new ACSender(commandSender);

		// Check if the command sender has permission to use ArchonCrate commands
		if(sender.hasPermission(Permissions.COMMAND_USE.value())) {

			// Check if the command entered is an ArchonCrate command
			if(command.getName().equalsIgnoreCase("archoncrates")) {

				// If arguments length is 0 or 1
				if(args.length == 0 || args.length == 1) {

					// Return an array of all commands
					return TabCompletionHelper.getCompletionList(args, new String[]{"create", "key", "list", "crates"}, false);

				} else if(args.length == 2) { // If the arguments length is 2

					// Switch through all commands and match to return the right command arguments
					switch (args[0]) {
						case "create": // Match the create command

							return TabCompletionHelper.getCompletionList(args, new String[]{"physical", "virtual"}, false);

						case "key": // Match the key command

							// Return List Of Completions

							break;
						case "list": // Match the list command

							// Return List Of Completions

							break;
						case "crates": // Match the crates command

							// Return List Of Completions

							break;
					}

				} else if(args.length == 3) { // If the arguments length is 3

					// Switch through all the commands to return the right command arguments
					switch (args[0]) {
						case "create": // Match the create command

							if(args[1].equalsIgnoreCase("physical") || args[1].equalsIgnoreCase("p")) {
								// If the create command has the type argument "physical" or "p"

								// Create array of Crate IDs
								ArrayList<String> crates = new ArrayList<>();

								// For each crate from all crates
								for (Crate crate : Fetcher.getCrates()) {

									// Add the IDs to the crates array
									crates.add(crate.getID());
								}

								// Return a list of all crates matching the currently typed ID
								return TabCompletionHelper.getCompletionList(args, crates, false);

							} else if(args[1].equalsIgnoreCase("virtual") || args[1].equalsIgnoreCase("v")) {
								// If the create command has a type of "virtual" or "v"



							}

						case "key": // Match the key command

							// Return List Of Completions

							break;
						case "list": // Match the list command

							// Return List Of Completions

							break;
						case "crates": // Match the crates command

							// Return List Of Completions

							break;
					}

				}
			}
		}

		return null;
	}
}
