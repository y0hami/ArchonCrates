package com.HamiStudios.ArchonCrates.API.Exceptions;

import org.bukkit.Bukkit;

import com.HamiStudios.ArchonCrates.API.Enums.Files;
import com.HamiStudios.ArchonCrates.API.libs.Console;

public class InvalidSoundValue extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidSoundValue(String ID, String soundUsed, Files file) {
		// Log to the server console to make the administrator aware of the error
		Console console = new Console(Bukkit.getConsoleSender());
		console.space();
		console.error("&cThere was an error trying to parse '" + soundUsed + "' into a Minecraft sound.");
		console.log("                      &7File: " + file.getFileName(), false);
		console.log("                      &7Error Element: " + ID, false);
		console.space();
	}
	
}
