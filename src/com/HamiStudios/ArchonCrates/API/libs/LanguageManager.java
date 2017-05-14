package com.HamiStudios.ArchonCrates.API.libs;

import com.HamiStudios.ArchonCrates.API.Exceptions.NoValueException;
import org.bukkit.ChatColor;

import com.HamiStudios.ArchonCrates.API.Enums.LanguageType;
import com.HamiStudios.ArchonCrates.Files.Language;

public class LanguageManager {

	public static String get(LanguageType type) {
		Language lang = new Language();
		try {
			return ChatColor.translateAlternateColorCodes('&', (String) lang.get(type.value()));
		} catch(NoValueException e) { return ""; }
	}

	public static String getPrefix() {
		return ChatColor.translateAlternateColorCodes('&', "&7[&5ArchonCrates&7] ");
	}

	public static String getHelpPrefix() { return ChatColor.translateAlternateColorCodes('&', "&7&m>-----[&r &5ArchonCrates Help &7&m]-----<"); }

	public static String getHelpSuffix() { return ChatColor.translateAlternateColorCodes('&', "&7&m>----------------------------<"); }

	public static String getHelpURL() { return "https://archoncrates.net/docs/"; }

}
