package com.HamiStudios.ArchonCrates.Commands;

import com.HamiStudios.ArchonCrates.API.Enums.LanguageType;
import com.HamiStudios.ArchonCrates.API.Libs.HelpPageBuilder;
import com.HamiStudios.ArchonCrates.API.Libs.LanguageManager;
import com.HamiStudios.ArchonCrates.API.Objects.ACSender;
import org.bukkit.Bukkit;

import java.util.ArrayList;

public class HelpCommand implements Command {

	@Override
	public void displayHelp(ACSender sender) {
		ArrayList<String> args = new ArrayList<String>();
		args.add("help");
		args.add("create");
		args.add("key");
		args.add("crates");

		new HelpPageBuilder("help")
				.setCommandDescription("Shows help on how to use commands.")
				.setHelpURL(Bukkit.getPluginManager().getPlugin("ArchonCrates").getDescription().getVersion().replaceAll("\\.", "-") + "/commands")
				.setExample("create")
				.addArgument("command", false, args)
			.send(sender);
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

				KeyCommand keyCommand = new KeyCommand(null);
				keyCommand.displayHelp(sender);

				break;
			case "crates":

				CratesCommand cratesCommand = new CratesCommand();
				cratesCommand.displayHelp(sender);

				break;
			default:
				sender.sendMessage(LanguageManager.get(LanguageType.ERROR_INVALID_COMMAND));
				break;
		}
	}

}
