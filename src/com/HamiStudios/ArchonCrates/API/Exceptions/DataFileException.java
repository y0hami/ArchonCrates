package com.HamiStudios.ArchonCrates.API.Exceptions;

import com.HamiStudios.ArchonCrates.API.Libs.Console;
import org.bukkit.Bukkit;

public class DataFileException extends Exception {

	private static final long serialVersionUID = 1L;

	public DataFileException(String filePath) {
		// Log to the server console to make the administrator aware of the error
		Console console = new Console(Bukkit.getConsoleSender());
		console.space();
		console.error("&cThere was an error reading a data file.");
		console.log("                      &7File Path: " + filePath, false);
		console.space();
	}

}
