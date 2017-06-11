package com.HamiStudios.ArchonCrates.API.Exceptions;

import com.HamiStudios.ArchonCrates.API.Libs.Console;
import org.bukkit.Bukkit;

public class InvalidWinAmount extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Thrown when an invalid win amount is parsed.
	 *
	 * @param ID of the element in which it was trying to parse.
	 * @param charUsed of which its trying to parse.
	 */
	public InvalidWinAmount(String ID, String charUsed) {
		// Log to the server console to make the administrator aware of the error
		Console console = new Console(Bukkit.getConsoleSender());
		console.space();
		console.error("&cThere was an error trying to parse '" + charUsed + "' as a win amount.");
		console.log("                      &Prize: " + ID, false);
		console.space();
	}

}
