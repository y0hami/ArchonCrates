package com.HamiStudios.ArchonCrates.API.Objects;

import org.bukkit.ChatColor;

import java.util.ArrayList;

public class ItemLore {

	// Crate the ArrayList instance
	private ArrayList<String> lore = new ArrayList<>();
	private boolean translateColours = true;
	
	// Add lines to the lore

	/**
	 * Add a line to the lore.
	 *
	 * @param content to be added.
	 * @return ItemLore (self)
	 */
	public ItemLore add(String content) {
		if(translateColours) { this.lore.add(ChatColor.translateAlternateColorCodes('&', content)); }
		else { this.lore.add(content); }
		return this;
	}
	
	// Sets the variable translateColours to the value given

	/**
	 * If the lore should translate colour codes.
	 *
	 * @param value of which to translate or not.
	 * @return ItemLore (self)
	 */
	public ItemLore translateColours(boolean value) {
		this.translateColours = value;
		return this;
	}
	
	// Return the ArrayList

	/**
	 * Build the lore into a list to use on ItemStacks or the ItemBuilder.
	 *
	 * @return an ArrayList of all lines.
	 */
	public ArrayList<String> build() {
		return this.lore;
	}

}
