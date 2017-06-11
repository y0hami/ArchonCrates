package com.HamiStudios.ArchonCrates.API.Libs;

import com.HamiStudios.ArchonCrates.API.Exceptions.NoValueException;
import org.bukkit.ChatColor;

import com.HamiStudios.ArchonCrates.API.Enums.LanguageType;
import com.HamiStudios.ArchonCrates.Files.Language;

public class LanguageManager {

	/**
	 * Get the message from the language file from the given LanguageType.
	 *
	 * @param type the LanguageType of which you want to get.
	 * @return The message of which is present in the language file for the given LanguageType.
	 */
	public static String get(LanguageType type) {
		Language lang = new Language();
		try {
			return ChatColor.translateAlternateColorCodes('&', (String) lang.get(type.value()));
		} catch(NoValueException e) { return ""; }
	}

	/**
	 * Get the classic ArchonCrate prefix.
	 *
	 * @return the ArchonCrates prefix.
	 */
	public static String getPrefix() {
		return ChatColor.translateAlternateColorCodes('&', "&7[&5ArchonCrates&7] ");
	}

	/**
	 * Get the help page prefix.
	 *
	 * @return help page prefix.
	 */
	public static String getHelpPrefix() { return ChatColor.translateAlternateColorCodes('&', "&7&m>-----[&r &5ArchonCrates Help &7&m]-----<"); }

	/**
	 * Get the help page suffix.
	 *
	 * @return help page suffix.
	 */
	public static String getHelpSuffix() { return ChatColor.translateAlternateColorCodes('&', "&7&m>----------------------------<"); }

	/**
	 * Get the basic support URL.
	 *
	 * @return the support URL.
	 */
	public static String getHelpURL() { return "https://archoncrates.com/docs/"; }

}
