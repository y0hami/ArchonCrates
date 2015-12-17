package com.HamiStudios.ArchonCrates.API.Objects;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.HamiStudios.ArchonCrates.Files.FileHandler;
import com.HamiStudios.ArchonCrates.Util.FileType;
import com.HamiStudios.ArchonCrates.Util.KeyFinder;

public class Key {

	private String keyType;
	private String name;
	private ArrayList<String> lore;
	private int itemID;
	private int itemData;
	private boolean glow;
	private String winMessage;
	private String playerMessage;
	private ArrayList<String> lootRaw;
	private ArrayList<Prize> loot;

	@SuppressWarnings("unchecked")
	public Key(String keyType) {
		this.keyType = KeyFinder.getKeyTypeToCase(keyType);
		this.name = (String) FileHandler.get(FileType.KEYS, "Keys." + this.keyType + ".name");
		this.lore = (ArrayList<String>) FileHandler.get(FileType.KEYS, "Keys." + this.keyType + ".lore");
		this.itemID = (int) FileHandler.get(FileType.KEYS, "Keys." + this.keyType + ".itemID");
		this.itemData = (int) FileHandler.get(FileType.KEYS, "Keys." + this.keyType + ".itemData");
		this.glow = (boolean) FileHandler.get(FileType.KEYS, "Keys." + this.keyType + ".glow");
		this.winMessage = (String) FileHandler.get(FileType.KEYS, "Keys." + this.keyType + ".winMessage");
		this.playerMessage = (String) FileHandler.get(FileType.KEYS, "Keys." + this.keyType + ".playerMessage");
		this.lootRaw = (ArrayList<String>) FileHandler.get(FileType.KEYS, "Keys." + this.keyType + ".loot");
		loot = new ArrayList<>();
		for(String s : lootRaw) loot.add(new Prize(s));
	}
	
	public String getDisplayName() {
		return this.name;
	}
	
	public String getKeyType() {
		return this.keyType;
	}
	
	public ArrayList<String> getLore() {
		return this.lore;
	}
	
	public int getItemId() {
		return this.itemID;
	}
	
	public int getItemData() {
		return this.itemData;
	}
	
	public boolean glow() {
		return this.glow;
	}
	
	public String getWinMessage() {
		return this.winMessage;
	}
	
	public String getPlayerMessage() {
		return this.playerMessage;
	}
	
	public ArrayList<String> getRawLoot() {
		return this.lootRaw;
	}
	
	public ArrayList<Prize> getLoot() {
		return this.loot;
	}
	
	public ItemStack getItem() {
		@SuppressWarnings("deprecation")
		ItemStack key = new ItemStack(Material.getMaterial(this.itemID), 1);
		ItemMeta keyMeta = key.getItemMeta();
		keyMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.name));
		ArrayList<String> loreWithColour = new ArrayList<>();
		for(String s : this.lore) loreWithColour.add(ChatColor.translateAlternateColorCodes('&', s));
		keyMeta.setLore(loreWithColour);
		if(this.glow) {
			Glow glow = new Glow(70);
			keyMeta.addEnchant(glow, 1, true);
		}
		key.setItemMeta(keyMeta);
		key.setDurability((short)this.itemData);
		return key;
	}
	
}
