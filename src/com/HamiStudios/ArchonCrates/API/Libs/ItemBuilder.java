package com.HamiStudios.ArchonCrates.API.Libs;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemBuilder {

	// Create instances of the ItemStack & ItemMeta
	private ItemStack item;
	private ItemMeta itemMeta;

	// Creates a new ItemStack from the given Material and creates
	// a new instance of the ItemMeta from the new ItemStack

	/**
	 * Set the material of the item.
	 *
	 * @param value of the material.
	 * @return ItemBuilder (self).
	 */
	public ItemBuilder setMaterial(Material value) {
		this.item = new ItemStack(value);
		this.itemMeta = this.item.getItemMeta();
		return this;
	}

	// Set the name of the item

	/**
	 * Set the name of the item.
	 *
	 * @param value of the title.
	 * @return ItemBuilder (self).
	 */
	public ItemBuilder setName(String value) {
		this.itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', value));
		return this;
	}

	// Set the lore of the item

	/**
	 * Set the lore of the item.
	 *
	 * @param value of the ArrayList for the lore.
	 * @return ItemBuilder (self).
	 */
	public ItemBuilder setLore(ArrayList<String> value) {
		ArrayList<String> lore = new ArrayList<>();
		for (String line : value) {
			lore.add(ChatColor.translateAlternateColorCodes('&', line));
		}

		this.itemMeta.setLore(lore);
		return this;
	}

	// Set the data value of the item

	/**
	 * Set the data value of the item.
	 *
	 * @param value of the data.
	 * @return ItemBuilder (self).
	 */
	public ItemBuilder setData(short value) {
		this.item.setDurability(value);
		return this;
	}

	// Add an enchantment to the item

	/**
	 * Add an enchantment to the item.
	 *
	 * @param enchantment the enchantment to add.
	 * @param level the level of the enchantment.
	 * @param ignoreRestriction should it ignore enchantment restrictions.
	 * @return ItemBuilder (self).
	 */
	public ItemBuilder addEnchantment(Enchantment enchantment, int level, boolean ignoreRestriction) {
		this.itemMeta.addEnchant(enchantment, level, ignoreRestriction);
		return this;
	}

	// Set stack amount to the item

	/**
	 * Set the stack amount of the item.
	 *
	 * @param value of the amount.
	 * @return ItemBuilder (self).
	 */
	public ItemBuilder setAmount(int value) {
		this.item.setAmount(value);
		return this;
	}

	// Build the item and return the ItemStack

	/**
	 * Build the item.
	 *
	 * @return ItemStack to the specification provided by setters.
	 */
	public ItemStack build() {
		this.item.setItemMeta(this.itemMeta);
		return this.item;
	}

}
