package com.HamiStudios.ArchonCrates.Commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.HamiStudios.ArchonCrates.Util.LanguageType;

public class Create {

	@SuppressWarnings("deprecation")
	public static void run(Player player, com.HamiStudios.ArchonCrates.API.Objects.Crate crate) {
		
		ItemStack crateBlock = new ItemStack(Material.getMaterial(crate.getBlockID()));
		ItemMeta crateBlockMeta = crateBlock.getItemMeta();
		crateBlockMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aCrate Block"));
		ArrayList<String> lore = new ArrayList<>();
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7Place down the crate block to"));
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7create a new &b'" + crate.getCrateID() + "' &7crate"));
		crateBlockMeta.setLore(lore);
		crateBlock.setItemMeta(crateBlockMeta);
		crateBlock.setDurability((short)crate.getBlockData());

		if(player.getInventory().firstEmpty() != -1) {
			player.getInventory().addItem(crateBlock);
		}
		else{
			player.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_CREATE_NO_SPACE.toString(true));
		}
	}
	
}