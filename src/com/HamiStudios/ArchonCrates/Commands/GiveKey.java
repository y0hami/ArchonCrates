package com.HamiStudios.ArchonCrates.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.HamiStudios.ArchonCrates.API.Events.OnPlayerKeyGiven;
import com.HamiStudios.ArchonCrates.API.Objects.Key;
import com.HamiStudios.ArchonCrates.Util.LanguageType;

public class GiveKey {

	public static void runForPlayer(Player player, CommandSender sender, String keyType, int amount) {
		Key key = new Key(keyType);
		ItemStack keyitem = key.getItem();
		keyitem.setAmount(amount);
		if(player.getInventory().firstEmpty() == -1) {
			sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_KEY_NO_SPACE.toString(true));
			return;
		}
		player.getInventory().addItem(keyitem);
		Bukkit.getServer().getPluginManager().callEvent(new OnPlayerKeyGiven(player, key));
		sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_KEY_GIVEN.toString(true).replaceAll("<amount>", amount+"").replaceAll("<player>", player.getName()));
	}
	
	@SuppressWarnings("deprecation")
	public static void runForAll(CommandSender sender, String keyType, int amount) {
		Key key = new Key(keyType);
		ItemStack keyitem = key.getItem();
		keyitem.setAmount(amount);
		for(Player player : Bukkit.getOnlinePlayers()) {
			if(player.getInventory().firstEmpty() == -1) {
				player.getWorld().dropItem(player.getLocation(), keyitem);
			}
			else{
				player.getInventory().addItem(keyitem);
			}
			Bukkit.getServer().getPluginManager().callEvent(new OnPlayerKeyGiven(player, key));
		}
		sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_KEY_GIVEN.toString(true).replaceAll("<amount>", amount+"").replaceAll("<player>", "ALL"));
	}
 
}
