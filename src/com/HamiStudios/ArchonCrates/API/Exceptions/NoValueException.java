package com.HamiStudios.ArchonCrates.API.Exceptions;

import org.bukkit.Bukkit;

import com.HamiStudios.ArchonCrates.API.Enums.Files;
import com.HamiStudios.ArchonCrates.API.Libs.Console;

public class NoValueException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoValueException(String pathToAttemptValue, Files file) {
		// Log to the server console to make the administrator aware of the error
		Console console = new Console(Bukkit.getConsoleSender());
		console.space();
		console.error("&cThere was a request to a value which did not exist.");
		console.log("                      &7File: " + file.getFileName(), false);
		console.log("                      &7Path: " + pathToAttemptValue, false);
		console.space();
	}
	
}
