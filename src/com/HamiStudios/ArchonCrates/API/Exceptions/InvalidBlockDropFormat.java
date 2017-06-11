package com.HamiStudios.ArchonCrates.API.Exceptions;

import com.HamiStudios.ArchonCrates.API.Enums.Files;
import com.HamiStudios.ArchonCrates.API.Libs.Console;
import org.bukkit.Bukkit;

public class InvalidBlockDropFormat extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Thrown when there is an invalid block drop format (Invalid format in the key drops file)
	 *
	 * @param data the data in which is invalid.
	 * @param file the file in which the Exception occurred.
	 */
	public InvalidBlockDropFormat(String data, Files file) {
		// Log to the server console to make the administrator aware of the error
		Console console = new Console(Bukkit.getConsoleSender());
		console.space();
		console.error("&cThere was an error trying to parse '" + data + "' into a Minecraft block ID.");
		console.log("                      &7File: " + file.getFileName(), false);

		console.space();
	}

}
