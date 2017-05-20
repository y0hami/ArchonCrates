package com.HamiStudios.ArchonCrates.Commands;

import com.HamiStudios.ArchonCrates.API.Enums.LanguageType;
import com.HamiStudios.ArchonCrates.API.Enums.Menu;
import com.HamiStudios.ArchonCrates.API.Enums.Permissions;
import com.HamiStudios.ArchonCrates.API.Libs.HelpPageBuilder;
import com.HamiStudios.ArchonCrates.API.Libs.LanguageManager;
import com.HamiStudios.ArchonCrates.API.Menus.CratesMenu;
import com.HamiStudios.ArchonCrates.API.Objects.ACSender;
import org.bukkit.entity.Player;

public class CratesCommand implements Command {

	@Override
	public void displayHelp(ACSender sender) {
		new HelpPageBuilder("crates")
				.setCommandDescription("This command opens a GUI where you can select a crate to teleport to.")
				.setHelpURL("commands/crates")
				.send(sender);
	}

	@Override
	public void execCommand(String[] args, ACSender sender) {
		if(!sender.isConsole()) {

			// Check if the sender has permission
			if(sender.hasPermission(Permissions.COMMAND_CRATES.value())) {

				CratesMenu cratesMenu = new CratesMenu();
				cratesMenu.openMenu((Player) sender.getSender(), Menu.CRATES_WORLDS);

			} else {
				sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.ERROR_NO_PERMISSION));
			}

		} else {
			sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.ERROR_PLAYER_ONLY_COMMAND));
		}
	}

}
