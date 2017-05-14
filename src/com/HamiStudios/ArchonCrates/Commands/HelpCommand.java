package com.HamiStudios.ArchonCrates.Commands;

import com.HamiStudios.ArchonCrates.API.Enums.LanguageType;
import com.HamiStudios.ArchonCrates.API.Objects.ACSender;
import com.HamiStudios.ArchonCrates.API.libs.LanguageManager;

public class HelpCommand implements Command {

	@Override
	public void displayHelp(ACSender sender) {
		sender.sendSpace();
		sender.sendMessage(LanguageManager.getHelpPrefix());
		sender.sendSpace();
		sender.sendMessage("&7Command Layout:\n&f/archoncrates help <command>");
		sender.sendSpace();
		sender.sendMessage("&7Commands:\n&fcreate, key, list, crates");
		sender.sendSpace();
		sender.sendSpace();
		sender.sendMessage("&7For more help visit:\n&e" + LanguageManager.getHelpURL() + "docs/commands/help");
		sender.sendSpace();
	}

	@Override
	public void execCommand(String[] args, ACSender sender) {
		switch (args[0].toLowerCase()) {
			case "help":
				sender.sendSpace();
				sender.sendMessage("Please... just use /archoncrates help I don't think you need help help only help");
				sender.sendSpace();
				break;
			case "create":
				CreateCommand createCommand = new CreateCommand();
				createCommand.displayHelp(sender);
				break;
			case "key":
				break;
			case "list":
				break;
			case "crates":
				break;
			default:
				sender.sendMessage(LanguageManager.get(LanguageType.ERROR_INVALID_COMMAND));
				break;
		}
	}

}
