package com.HamiStudios.ArchonCrates.Commands;

import com.HamiStudios.ArchonCrates.API.Enums.LanguageType;
import com.HamiStudios.ArchonCrates.API.Enums.Menu;
import com.HamiStudios.ArchonCrates.API.Enums.Permissions;
import com.HamiStudios.ArchonCrates.API.Libs.HelpPageBuilder;
import com.HamiStudios.ArchonCrates.API.Libs.LanguageManager;
import com.HamiStudios.ArchonCrates.API.Menus.CreateMenu;
import com.HamiStudios.ArchonCrates.API.Objects.ACSender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CreateCommand implements Command {

	@Override
	public void displayHelp(ACSender sender) {
		new HelpPageBuilder("create")
				.setCommandDescription("This command opens a GUI where you select what crate type you want to make and what crate you want to create.")
				.setHelpURL(Bukkit.getPluginManager().getPlugin("ArchonCrates").getDescription().getVersion().replaceAll("\\.", "-") + "/commands")
				.send(sender);
	}

	@Override
	public void execCommand(String[] args, ACSender sender) {
		if(!sender.isConsole()) {

			if(sender.hasPermission(Permissions.COMMAND_CREATE.value())) {
				Player player = (Player) sender.getSender();

				int invSize = 36;

				boolean space = false;
				while(invSize != 1) {
					invSize--;
					if(player.getInventory().getItem(invSize-1) == null) { space = true; }
				}

				if(space) {
					CreateMenu createMenu = new CreateMenu();
					createMenu.openMenu(player, Menu.CRATE_TYPE);
				} else {
					player.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.COMMAND_CREATE_NO_SPACE));
				}
			} else {
				sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.ERROR_NO_PERMISSION));
			}

		} else {
			sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.ERROR_PLAYER_ONLY_COMMAND));
		}
	}

}
