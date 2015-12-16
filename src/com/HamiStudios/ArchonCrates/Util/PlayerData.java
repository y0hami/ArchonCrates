package com.HamiStudios.ArchonCrates.Util;

import org.bukkit.entity.Player;

import com.HamiStudios.ArchonCrates.Files.Data;

public class PlayerData {

	public static void addName(Player player) {
		Data data = new Data();
		data.set("data.players." + player.getUniqueId().toString() + ".name", player.getName());
		data.save();
	}
	
	public static void addVKey(String uuid, String vkeyType, int amount) {
		Data data = new Data();
		int currentAmount = 0;
		if(data.getFile().contains("data.players." + uuid + ".vkeys." + vkeyType)) currentAmount = data.getFile().getInt("data.players." + uuid + ".vkeys." + vkeyType);
		data.set("data.players." + uuid + ".vkeys." + vkeyType, currentAmount+amount);
		data.save();
	}

	public static void takeVKey(String uuid, String vkeyType, int amount) {
		Data data = new Data();
		int currentAmount = 0;
		if(data.getFile().contains("data.players." + uuid + ".vkeys." + vkeyType)) currentAmount = data.getFile().getInt("data.players." + uuid + ".vkeys." + vkeyType);
		if((currentAmount-amount) <= 0) {
			data.set("data.players." + uuid + ".vkeys." + vkeyType, null);
		}
		else {
			data.set("data.players." + uuid + ".vkeys." + vkeyType, currentAmount-amount);
		}
		data.save();
	}
	
	public static int getVKeyCount(String uuid, String vkeyType) {
		Data data = new Data();
		if(data.getFile().contains("data.players." + uuid + ".vkeys." + vkeyType)) {
			return data.getFile().getInt("data.players." + uuid + ".vkeys." + vkeyType);
		}
		return 0;
	}
	
	public static String getUUID(String playername) {
		Data data = new Data();
		for(String s : data.getFile().getConfigurationSection("data.players").getKeys(false)) {
			if(data.getFile().getString("data.players." + s + ".name").toUpperCase().equalsIgnoreCase(playername.toUpperCase())) return s;
		}
		return null;
	}
	
	public static String getName(String uuid) {
		Data data = new Data();
		return data.getFile().getString("data.players." + uuid + ".name");
	}
	
}
