package com.HamiStudios.ArchonCrates.Commands;

import com.HamiStudios.ArchonCrates.API.Enums.LanguageType;
import com.HamiStudios.ArchonCrates.API.Enums.Permissions;
import com.HamiStudios.ArchonCrates.API.Enums.UpdateState;
import com.HamiStudios.ArchonCrates.API.Libs.Console;
import com.HamiStudios.ArchonCrates.API.Libs.LanguageManager;
import com.HamiStudios.ArchonCrates.API.Libs.UpdateChecker;
import com.HamiStudios.ArchonCrates.API.Objects.ACSender;
import com.HamiStudios.ArchonCrates.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Commands implements CommandExecutor {

	// Instance of main class
	private Main main;

	// Commands Constructor
	public Commands(Main main) {
		this.main = main;
	}


	@Override
	public boolean onCommand(CommandSender commandSender, Command cmd, String commandLabel, String[] args) {

		// Create instance of ACSender
		ACSender sender = new ACSender(commandSender);


		// Checks if the command sender has the permission to use ArchonCrate commands
		if(sender.hasPermission(Permissions.COMMAND_USE.value())) {

			// Sender entered /archoncrates
			if(cmd.getName().equalsIgnoreCase("archoncrates")) {

				if (args.length == 0) {
					// No Arguments Provided & check for help command permissions
					if (sender.hasPermission(Permissions.COMMAND_HELP.value())) {
						HelpCommand helpCommand = new HelpCommand();
						helpCommand.displayHelp(sender);
					} else {
						// If no permissions send ERROR_NO_PERMISSION message
						sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.ERROR_NO_PERMISSION));
					}

				} else if (args.length == 1) {
					// 1 Argument Provided

					// Switch through all 1 argument length commands
					switch (args[0]) {
						case "help": // Match help command

							// Display help page for help command (lel)
							HelpCommand helpCommand = new HelpCommand();
							helpCommand.displayHelp(sender);

							break;
						case "create": // Match create command

							// Run the create command
							CreateCommand createCommand = new CreateCommand();
							createCommand.execCommand(args, sender);

							break;
						case "key": // Match key command

							// Run the key command
							KeyCommand keyCommand = new KeyCommand(this.main);
							keyCommand.execCommand(new String[]{}, sender);

							break;
						case "crates":

							// Run the crates command
							CratesCommand cratesCommand = new CratesCommand();
							cratesCommand.execCommand(new String[]{}, sender);

							break;
						default:
							// If no command is matched display ERROR_INVALID_COMMAND message
							sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.ERROR_INVALID_COMMAND));
							break;
					}
				} else if (args.length == 2) {
					// 2 Arguments Provided

					// Switch through all 2 argument length commands
					switch (args[0].toLowerCase()) {
						case "help": // Match help command

							// Run the help command
							HelpCommand helpCommand = new HelpCommand();
							helpCommand.execCommand(new String[]{args[1]}, sender);

							break;
						case "key": // Match key command

							// Run the key command
							KeyCommand keyCommand = new KeyCommand(this.main);
							keyCommand.execCommand(new String[]{args[1], "1"}, sender);

							break;
						default:
							// If no command matched display ERROR_INVALID_COMMAND message
							sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.ERROR_INVALID_COMMAND));
							break;
					}

				} else if (args.length == 3) {
					// 3 Arguments Provided

					// Switch through all 3 argument length commands
					switch (args[0].toLowerCase()) {
						case "key": // Match key command

							// Run the key command
							KeyCommand keyCommand = new KeyCommand(this.main);
							keyCommand.execCommand(new String[]{args[1], args[2]}, sender);

							break;
						default:
							// If no command matched display ERROR_INVALID_COMMAND message
							sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.ERROR_INVALID_COMMAND));
							break;
					}
				} else if (args.length == 4) {
					// 4 Arguments Provided

					// Switch through all 4 argument length commands
					switch (args[0].toLowerCase()) {
						case "key": // Match key command

							// Run the key command
							KeyCommand keyCommand = new KeyCommand(this.main);
							keyCommand.execCommand(new String[]{args[1], args[2], args[3]}, sender);

							break;
						default:
							// If no command matched display ERROR_INVALID_COMMAND message
							sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.ERROR_INVALID_COMMAND));
							break;
					}
				} else if (args.length == 5) {
					// 5 Arguments Provided

					// Switch through all 5 argument length commands
					switch (args[0].toLowerCase()) {
						case "key": // Match key command

							// Run the key command
							KeyCommand keyCommand = new KeyCommand(this.main);
							keyCommand.execCommand(new String[]{args[1], args[2], args[3], args[4]}, sender);

							break;
						default:
							// If no command matched display ERROR_INVALID_COMMAND message
							sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.ERROR_INVALID_COMMAND));
							break;
					}
				} else {
					// If arguments length is larger then 3 there is no commands so display ERROR_INVALID_COMMAND message
					sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.ERROR_INVALID_COMMAND));
				}

				return true;
			} else if(cmd.getName().equalsIgnoreCase("acupdate")) {

				// Sender entered acupdate
				if(sender.isConsole()) {

					Console console = new Console((ConsoleCommandSender) sender.getSender());
					String currentVersion = Bukkit.getPluginManager().getPlugin("ArchonCrates").getDescription().getVersion();
					UpdateChecker updateChecker = new UpdateChecker();

					UpdateState updateState = updateChecker.check(currentVersion);

					if(updateState.equals(UpdateState.UPDATE)) {

						// Download and Install
						console.log("&fUpdate available, downloading the leatest update...");
						try {

							String jarName = new java.io.File(Main.class.getProtectionDomain()
									.getCodeSource()
									.getLocation()
									.getPath())
									.getName();

							File jar = new File("plugins/" + jarName);
							jar.delete();

							URL website = new URL(updateChecker.getDownloadURL());
							ReadableByteChannel rbc = Channels.newChannel(website.openStream());
							FileOutputStream fos = new FileOutputStream("plugins/" + jarName);
							fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

							console.log("&fDownloaded, reloading the plugin...");
							Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin("ArchonCrates"));
							Bukkit.getPluginManager().loadPlugin(new File("plugins/" + jarName));
							Bukkit.getPluginManager().enablePlugin(Bukkit.getPluginManager().getPlugin("ArchonCrates"));
							console.log("&fPlugin reloaded & updated to version: &5" + updateChecker.getNewVersion());
							console.space();
							console.space();

						} catch(Exception e) {
							console.log("&fThere was an error updating ArchonCrates, please contact our support team. https://support.hamistudios.com");
//							e.printStackTrace();
						}

					} else if(updateState.equals(UpdateState.NO_UPDATE)) {
						// No Update
						console.log("&fThere is currently no updates available for ArchonCrates. You are running the latest version.");
					} else {
						// Error Updating
						console.log("&fThere was an error checking for updates, please report this to our support team. https://support.hamistudios.com");
					}

				} else {
					sender.sendMessage(LanguageManager.getPrefix() + "&fYou can only run this command via the server console.");
				}
				return true;

			}

		} else {
			// Send the player a message to say they don't have permission
			sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.ERROR_NO_PERMISSION));
			return true;
		}

		return false;
	}

}
