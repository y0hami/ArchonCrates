package com.HamiStudios.ArchonCrates.Commands;

import com.HamiStudios.ArchonCrates.API.Enums.LanguageType;
import com.HamiStudios.ArchonCrates.API.Enums.Menu;
import com.HamiStudios.ArchonCrates.API.Enums.Permissions;
import com.HamiStudios.ArchonCrates.API.Menus.KeyMenu;
import com.HamiStudios.ArchonCrates.API.Objects.ACPlayer;
import com.HamiStudios.ArchonCrates.API.Objects.ACSender;
import com.HamiStudios.ArchonCrates.API.libs.HelpPageBuilder;
import com.HamiStudios.ArchonCrates.API.libs.LanguageManager;
import com.HamiStudios.ArchonCrates.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class KeyCommand implements Command {

	private Main main;

	public KeyCommand(Main main) {
		this.main = main;
	}


	@Override
	public void displayHelp(ACSender sender) {

		ArrayList<String> playerArgs = new ArrayList<>();
		playerArgs.add("Player name");
		playerArgs.add("all");

		ArrayList<String> amountArgs = new ArrayList<>();
		amountArgs.add("Amount of keys to give (no input will give 1)");

		new HelpPageBuilder("key")
				.setCommandDescription("This command allows you to give keys to one or all players.")
				.addArgument("player", false, playerArgs)
				.addArgument("amount", true, amountArgs)
				.setHelpURL("commands/key")
				.setExample("hammy2899 3")
				.send(sender);
	}

	@Override
	public void execCommand(String[] args, ACSender sender) {
		Player player = (Player) sender.getSender();

		// Give key to all players
		if(args[0].equalsIgnoreCase("all")) {

			// Check if the sender has permission to give keys to all players
			if(player.hasPermission(Permissions.COMMAND_KEY_ALL.value())) {

				// If they have permission
				// Check if the amount they entered is a Integer

				int amount = 1;

				try {
					amount = Integer.parseInt(args[1]);
				} catch(NumberFormatException e) {
					sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.COMMAND_KEY_INVALID_AMOUNT));
				}

				this.main.getOperationsManager().addKeyGiver(new ACPlayer(player), null, true, amount);

				KeyMenu keyMenu = new KeyMenu(this.main);
				keyMenu.openMenu(player, Menu.KEY_TYPE);

			} else {
				// If they don't have permission
				sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.ERROR_NO_PERMISSION));
			}

		} else {
			// Give key to a single player
			if(Bukkit.getPlayer(args[0]) == null) {
				sender.sendMessage("no");
			} else {

				// Check if the sender has permission to give keys to all players
				if(player.hasPermission(Permissions.COMMAND_KEY_PLAYER.value())) {

					// If they have permission
					// Check if the amount they entered is a Integer

					int amount = 1;

					try {
						amount = Integer.parseInt(args[1]);
					} catch(NumberFormatException e) {
						sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.COMMAND_KEY_INVALID_AMOUNT));
					}

					this.main.getOperationsManager().addKeyGiver(new ACPlayer(player), Bukkit.getPlayer(args[0]), false, amount);

					KeyMenu keyMenu = new KeyMenu(this.main);
					keyMenu.openMenu(player, Menu.KEY_TYPE);

				} else {
					// If they don't have permission
					sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.ERROR_NO_PERMISSION));
				}

			}
		}
	}

}
