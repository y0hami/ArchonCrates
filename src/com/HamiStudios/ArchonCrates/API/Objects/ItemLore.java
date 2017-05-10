package com.HamiStudios.ArchonCrates.API.Objects;

import java.util.ArrayList;

import org.bukkit.ChatColor;

public class ItemLore {

	// Crate the ArrayList instance
	private ArrayList<String> lore = new ArrayList<>();
	private boolean translateColours = true;
	
	// Add lines to the lore
	public ItemLore add(String content) {
		if(translateColours) { this.lore.add(ChatColor.translateAlternateColorCodes('&', content)); }
		else { this.lore.add(content); }
		return this;
	}
	
	// Sets the variable translateColours to the value given
	public ItemLore translateColours(boolean value) {
		this.translateColours = value;
		return this;
	}
	
	// Return the ArrayList
	public ArrayList<String> build() {
		return this.lore;
	}

}
