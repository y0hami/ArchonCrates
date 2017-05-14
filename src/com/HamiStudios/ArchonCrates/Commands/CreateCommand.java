package com.HamiStudios.ArchonCrates.Commands;

import com.HamiStudios.ArchonCrates.API.Enums.Menu;
import com.HamiStudios.ArchonCrates.API.Menus.CreateMenu;
import com.HamiStudios.ArchonCrates.API.Objects.ACSender;
import com.HamiStudios.ArchonCrates.API.libs.LanguageManager;
import org.bukkit.entity.Player;

public class CreateCommand implements Command {

	@Override
	public void displayHelp(ACSender sender) {
		sender.sendSpace();
		sender.sendMessage(LanguageManager.getHelpPrefix());
		sender.sendSpace();
		sender.sendMessage("&7Command Layout:\n&f/archoncrates create <crate type> <crate name>");
		sender.sendSpace();
		sender.sendMessage("&7Crate Types:\n&fphysical, virtual");
		sender.sendSpace();
		sender.sendMessage("&7Crate Name:\n&fThe name of the crate in your crates.yml");
		sender.sendSpace();
		sender.sendSpace();
		sender.sendMessage("&7For more info visit:\n&e" + LanguageManager.getHelpURL() + "configs/crates");
		sender.sendSpace();
	}

	@Override
	public void execCommand(String[] args, ACSender sender) {
		Player player = (Player) sender.getSender();

		CreateMenu createMenu = new CreateMenu();
		createMenu.openMenu(player, Menu.CRATE_TYPE);
	}

}
