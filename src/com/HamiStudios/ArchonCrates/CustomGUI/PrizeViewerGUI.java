package com.HamiStudios.ArchonCrates.CustomGUI;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.HamiStudios.ArchonCrates.API.Objects.Key;
import com.HamiStudios.ArchonCrates.API.Objects.Prize;
import com.HamiStudios.ArchonCrates.API.Objects.VirtualKey;
import com.HamiStudios.ArchonCrates.Files.FileHandler;
import com.HamiStudios.ArchonCrates.Util.FileType;

public class PrizeViewerGUI {

	private Inventory gui;
	private Player player;
	
	public PrizeViewerGUI(Player player) {
		this.player = player;
	
		gui = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', FileHandler.getFile(FileType.CUSTOM_GUI).getString("Chance GUI.title")));
		
		@SuppressWarnings("deprecation")
		ItemStack borderItem = new ItemStack(Material.valueOf(Material.getMaterial(FileHandler.getFile(FileType.CUSTOM_GUI).getInt("Chance GUI.border.itemID"))+""));
		ItemMeta borderMeta = borderItem.getItemMeta();
		borderMeta.setDisplayName(ChatColor.GRAY + "");
		borderItem.setDurability((short)FileHandler.getFile(FileType.CUSTOM_GUI).getInt("Chance GUI.border.itemData"));
		borderItem.setItemMeta(borderMeta);
		
		int x = 0;
		while(x != 9) {
			gui.setItem(x, borderItem);
			x++;
		}
		x = 45;
		while(x != 54) {
			gui.setItem(x, borderItem);
			x++;
		}	
		
		
		ArrayList<Prize> prizes = new ArrayList<>();
		for(String s : FileHandler.getFile(FileType.CRATE_LOOT).getConfigurationSection("Crate Loot").getKeys(false)) prizes.add(new Prize(s));
		ArrayList<Key> keys = new ArrayList<>();
		for(String s : FileHandler.getFile(FileType.KEYS).getConfigurationSection("Keys").getKeys(false)) keys.add(new Key(s));
		ArrayList<VirtualKey> vkeys = new ArrayList<>();
		for(String s : FileHandler.getFile(FileType.VIRTUAL_KEYS).getConfigurationSection("Virtual Keys").getKeys(false)) vkeys.add(new VirtualKey(s));
		
		for(Prize p : prizes) {
			ItemStack prize = p.getItem();
			ItemMeta prizeMeta = prize.getItemMeta();
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(ChatColor.GRAY + "");
			lore.add(ChatColor.GRAY + "Chance: " + ChatColor.AQUA + p.getChance() + "%");
			prizeMeta.setLore(lore);
			prize.setItemMeta(prizeMeta);
			
			gui.addItem(prize);
		}
		
	}
	
	public void open() {
		player.openInventory(this.gui);
	}
	
}
