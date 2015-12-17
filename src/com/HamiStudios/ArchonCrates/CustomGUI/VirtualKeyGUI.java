package com.HamiStudios.ArchonCrates.CustomGUI;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.HamiStudios.ArchonCrates.API.Objects.Glow;
import com.HamiStudios.ArchonCrates.API.Objects.VirtualCrate;
import com.HamiStudios.ArchonCrates.API.Objects.VirtualKey;
import com.HamiStudios.ArchonCrates.Files.FileHandler;
import com.HamiStudios.ArchonCrates.Util.FileType;
import com.HamiStudios.ArchonCrates.Util.KeyFinder;
import com.HamiStudios.ArchonCrates.Util.PlayerData;

public class VirtualKeyGUI {

	private Inventory gui;
	private Player player;
	
	public VirtualKeyGUI(Player player) {
		
		this.player = player;
		
		ArrayList<VirtualKey> virtualKeys = new ArrayList<>();
		for(String s : FileHandler.getFile(FileType.VIRTUAL_KEYS).getConfigurationSection("Virtual Keys").getKeys(false)) virtualKeys.add(new VirtualKey(s));
		
		int rows = FileHandler.getFile(FileType.CUSTOM_GUI).getInt("Virtual Keys GUI.rows");
		String title = FileHandler.getFile(FileType.CUSTOM_GUI).getString("Virtual Keys GUI.title");
		
		gui = Bukkit.createInventory(null, rows*9, ChatColor.translateAlternateColorCodes('&', title));
		
		ArrayList<String> slots = new ArrayList<>(); 
		for(String s : FileHandler.getFile(FileType.CUSTOM_GUI).getConfigurationSection("Virtual Keys GUI.slots").getKeys(false)) {
			slots.add(s);
		}
		for(String s : slots) {
			@SuppressWarnings("deprecation")
			ItemStack item = new ItemStack(Material.valueOf(Material.getMaterial(FileHandler.getFile(FileType.CUSTOM_GUI).getInt("Virtual Keys GUI.slots." + s + ".itemID")).toString()));
			item.setAmount(FileHandler.getFile(FileType.CUSTOM_GUI).getInt("Virtual Keys GUI.slots." + s + ".stackSize"));
			item.setDurability((short) FileHandler.getFile(FileType.CUSTOM_GUI).getInt("Virtual Keys GUI.slots." + s + ".itemData"));
			ItemMeta itemMeta = item.getItemMeta();
			itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', FileHandler.getFile(FileType.CUSTOM_GUI).getString("Virtual Keys GUI.slots." + s + ".displayname")));
			ArrayList<String> lore = new ArrayList<>();
			if(!FileHandler.getFile(FileType.CUSTOM_GUI).getString("Virtual Keys GUI.slots." + s + ".lore").equalsIgnoreCase("[]")) {
				for(String st : FileHandler.getFile(FileType.CUSTOM_GUI).getStringList("Virtual Keys GUI.slots." + s + ".lore")) lore.add(ChatColor.translateAlternateColorCodes('&', st));
				itemMeta.setLore(lore);
			}
			item.setItemMeta(itemMeta);

			gui.setItem(Integer.parseInt(s), item);
		}
  		
		ArrayList<VirtualKey> vkeys = new ArrayList<>();
		for(String s : FileHandler.getFile(FileType.CUSTOM_GUI).getConfigurationSection("Virtual Keys GUI.keys").getKeys(false)) {
			if(KeyFinder.isKeyType(s)) {
				vkeys.add(new VirtualKey(s));
			}
		}
		
		for(VirtualKey k : vkeys) {
			@SuppressWarnings("deprecation")
			ItemStack item = new ItemStack(Material.valueOf(Material.getMaterial(k.getItemId())+""));
			ItemMeta itemMeta = item.getItemMeta();
			item.setDurability((short) k.getItemData());
			itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', k.getDisplayName()));

			ArrayList<String> lore = new ArrayList<>();
			int keyAmount = PlayerData.getVKeyCount(player.getUniqueId().toString(), k.getVKeyType());
			lore.add(ChatColor.GRAY + "Keys: " + ChatColor.AQUA + keyAmount);
			itemMeta.setLore(lore);
			if(k.glow()) {
				Glow glow = new Glow(70);
				itemMeta.addEnchant(glow, 1, true);
			}
			item.setItemMeta(itemMeta);
			gui.setItem(FileHandler.getFile(FileType.CUSTOM_GUI).getInt("Virtual Keys GUI.keys." + k.getVKeyType() + ".slot"), item);
		}
		
	}
	
	public void open() {
		VirtualCrate vcrate = new VirtualCrate();
		player.playSound(player.getLocation(), Sound.valueOf(vcrate.getOpenSound()), 1, 1);
		player.openInventory(gui);
	}
	
}
