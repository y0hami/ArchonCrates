package com.HamiStudios.ArchonCrates.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.HamiStudios.ArchonCrates.API.Events.OnPlayerVirtualKeyGiven;
import com.HamiStudios.ArchonCrates.API.Objects.VirtualKey;
import com.HamiStudios.ArchonCrates.API.Objects.Exceptions.InvalidVirtualKeyInput;
import com.HamiStudios.ArchonCrates.Util.LanguageType;
import com.HamiStudios.ArchonCrates.Util.PlayerData;

public class GiveVirtualKey {

	@SuppressWarnings("static-access")
	public static void runForPlayer(String playername, CommandSender sender, String vKeyType, int amount) {
		PlayerData playerData = new PlayerData();
		playerData.addVKey(playerData.getUUID(playername), vKeyType, amount);
		try {
			Bukkit.getServer().getPluginManager().callEvent(new OnPlayerVirtualKeyGiven(playername, new VirtualKey(vKeyType)));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (InvalidVirtualKeyInput e) {
			e.log(vKeyType);
			e.writeToFile(vKeyType);
		}
		sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_VKEY_GIVEN.toString(true).replaceAll("<amount>", amount+"").replaceAll("<player>", playername));
	}
	
	@SuppressWarnings("static-access")
	public static void runForAll(CommandSender sender, String vKeyType, int amount) {
		PlayerData playerData = new PlayerData();
		for(Player p : Bukkit.getOnlinePlayers()) {
			playerData.addVKey(p.getUniqueId().toString(), vKeyType, amount);
			try {
				Bukkit.getServer().getPluginManager().callEvent(new OnPlayerVirtualKeyGiven(p.getName(), new VirtualKey(vKeyType)));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (InvalidVirtualKeyInput e) {
				e.log(vKeyType);
				e.writeToFile(vKeyType);
			}
		}
		sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_VKEY_GIVEN.toString(true).replaceAll("<amount>", amount+"").replaceAll("<player>", "ALL"));
	}
	
}
