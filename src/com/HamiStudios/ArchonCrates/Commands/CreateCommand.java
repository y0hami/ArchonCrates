package com.HamiStudios.ArchonCrates.Commands;

import com.HamiStudios.ArchonCrates.API.Enums.LanguageType;
import com.HamiStudios.ArchonCrates.API.Objects.ACSender;
import com.HamiStudios.ArchonCrates.API.libs.LanguageManager;

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
		if(args[0].equalsIgnoreCase("physical") || args[0].equalsIgnoreCase("p")) {
			sender.sendMessage("Physical");
		} else if(args[0].equalsIgnoreCase("virtual") || args[0].equalsIgnoreCase("v")) {
			sender.sendMessage("Virtual");
		} else {
			sender.sendMessage(LanguageManager.get(LanguageType.PREFIX) + LanguageManager.get(LanguageType.COMMANDS_CREATE_INVALID_TYPE));
		}
	}

}
