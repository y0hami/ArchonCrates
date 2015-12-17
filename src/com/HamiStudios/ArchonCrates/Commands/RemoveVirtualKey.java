package com.HamiStudios.ArchonCrates.Commands;

import org.bukkit.command.CommandSender;

import com.HamiStudios.ArchonCrates.Util.LanguageType;
import com.HamiStudios.ArchonCrates.Util.PlayerData;

public class RemoveVirtualKey {

	@SuppressWarnings("static-access")
	public static void runForPlayer(String playername, CommandSender sender, String vKeyType, int amount) {
		PlayerData playerData = new PlayerData();
		playerData.takeVKey(playerData.getUUID(playername), vKeyType, amount);
		sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_VKEY_REMOVED.toString(true).replaceAll("<amount>", amount+"").replaceAll("<player>", playername));
	}
	
}
