package com.HamiStudios.ArchonCrates.API.libs;

import org.bukkit.ChatColor;

import com.HamiStudios.ArchonCrates.API.Enums.LanguageType;
import com.HamiStudios.ArchonCrates.Files.Language;

public class LanguageManager {

	public static String get(LanguageType type) {
		Language lang = new Language();
		return ChatColor.translateAlternateColorCodes('&', (String) lang.get(type.value()));
	}

	public static String getPrefix() {
		return ChatColor.translateAlternateColorCodes('&', "&7[&5ArchonCrates&7] ");
	}

	public static String getHelpPrefix() { return ChatColor.translateAlternateColorCodes('&', "&7====[ &5ArchonCrates Help &7]===="); }

	public static String getHelpURL() { return "https://archoncrates.net/docs/"; }

}
