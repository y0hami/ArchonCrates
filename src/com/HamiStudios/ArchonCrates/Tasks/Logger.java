package com.HamiStudios.ArchonCrates.Tasks;

import java.util.Date;

import org.bukkit.entity.Player;

import com.HamiStudios.ArchonCrates.API.Objects.Key;
import com.HamiStudios.ArchonCrates.API.Objects.Prize;
import com.HamiStudios.ArchonCrates.API.Objects.VirtualCrate;
import com.HamiStudios.ArchonCrates.API.Objects.VirtualKey;
import com.HamiStudios.ArchonCrates.Files.FileHandler;
import com.HamiStudios.ArchonCrates.Util.FileType;
import com.HamiStudios.ArchonCrates.Util.LogType;

public class Logger {

	@SuppressWarnings("deprecation")
	public static void log(LogType type, Player player, Prize prize, com.HamiStudios.ArchonCrates.API.Objects.Crate crate, Key key) {
		if(type.equals(LogType.PLAYER)) {
			if(!FileHandler.getFile(FileType.PLAYER_LOG).contains("logs." + player.getUniqueId() + "." + prize.getPrizeID())) {
				FileHandler.set(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + ".winAmount", 1);
				FileHandler.set(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + ".id", 2);
				FileHandler.set(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + ".1.world", player.getWorld().getName());
				FileHandler.set(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + ".1.time", new Date().getHours() + ":" + new Date().getMinutes() + ":" + new Date().getSeconds());
				FileHandler.set(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + ".1.date", new Date().getDate() + "/" + new Date().getMonth() + "/" + new Date().getYear());
				FileHandler.set(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + ".1.key", key.getKeyType());
				FileHandler.set(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + ".1.crate", crate.getCrateID());
				
			}
			else{
				int winAmount = (int) FileHandler.get(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + ".winAmount");
				int id = (int) FileHandler.get(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + ".id");
				FileHandler.set(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + ".winAmount", winAmount+1);
				FileHandler.set(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + ".id", id+1);
				FileHandler.set(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + "." + id + ".world", player.getWorld().getName());
				FileHandler.set(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + "." + id + ".time", new Date().getHours() + ":" + new Date().getMinutes() + ":" + new Date().getSeconds());
				FileHandler.set(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + "." + id + ".date", new Date().getDate() + "/" + new Date().getMonth() + "/" + new Date().getYear());
				FileHandler.set(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + "." + id + ".key", key.getKeyType());
				FileHandler.set(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + "." + id + ".crate", crate.getCrateID());
			}
			return;
		}
		if(type.equals(LogType.PRIZE)) {
			if(!FileHandler.getFile(FileType.PRIZE_LOG).contains("logs." + prize.getPrizeID())) {
				FileHandler.set(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + ".winAmount", 1);
				FileHandler.set(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + ".id", 2);
				FileHandler.set(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + ".1.world", player.getWorld().getName());
				FileHandler.set(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + ".1.time", new Date().getHours() + ":" + new Date().getMinutes() + ":" + new Date().getSeconds());
				FileHandler.set(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + ".1.date", new Date().getDate() + "/" + new Date().getMonth() + "/" + new Date().getYear());
				FileHandler.set(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + ".1.key", key.getKeyType());
				FileHandler.set(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + ".1.crate", crate.getCrateID());
			}
			else{
				int winAmount = (int) FileHandler.get(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + ".winAmount");
				int id = (int) FileHandler.get(FileType.PRIZE_LOG, "logs." + "." + prize.getPrizeID() + ".id");
				FileHandler.set(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + ".winAmount", winAmount+1);
				FileHandler.set(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + ".id", id+1);
				FileHandler.set(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + "." + id + ".world", player.getWorld().getName());
				FileHandler.set(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + "." + id + ".time", new Date().getHours() + ":" + new Date().getMinutes() + ":" + new Date().getSeconds());
				FileHandler.set(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + "." + id + ".date", new Date().getDate() + "/" + new Date().getMonth() + "/" + new Date().getYear());
				FileHandler.set(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + "." + id + ".key", key.getKeyType());
				FileHandler.set(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + "." + id + ".crate", crate.getCrateID());
			}
			return;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void logV(LogType type, Player player, Prize prize, VirtualCrate vcrate, VirtualKey vkey) {
		if(type.equals(LogType.PLAYER)) {
			if(!FileHandler.getFile(FileType.PLAYER_LOG).contains("logs." + player.getUniqueId() + "." + prize.getPrizeID())) {
				FileHandler.set(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + ".winAmount", 1);
				FileHandler.set(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + ".id", 2);
				FileHandler.set(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + ".1.world", player.getWorld().getName());
				FileHandler.set(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + ".1.time", new Date().getHours() + ":" + new Date().getMinutes() + ":" + new Date().getSeconds());
				FileHandler.set(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + ".1.date", new Date().getDate() + "/" + new Date().getMonth() + "/" + new Date().getYear());
				FileHandler.set(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + ".1.key", vkey.getVKeyType());
				
			}
			else{
				int winAmount = (int) FileHandler.get(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + ".winAmount");
				int id = (int) FileHandler.get(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + ".id");
				FileHandler.set(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + ".winAmount", winAmount+1);
				FileHandler.set(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + ".id", id+1);
				FileHandler.set(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + "." + id + ".world", player.getWorld().getName());
				FileHandler.set(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + "." + id + ".time", new Date().getHours() + ":" + new Date().getMinutes() + ":" + new Date().getSeconds());
				FileHandler.set(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + "." + id + ".date", new Date().getDate() + "/" + new Date().getMonth() + "/" + new Date().getYear());
				FileHandler.set(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + "." + id + ".key", vkey.getVKeyType());
			}
			return;
		}
		if(type.equals(LogType.PRIZE)) {
			if(!FileHandler.getFile(FileType.PRIZE_LOG).contains("logs." + prize.getPrizeID())) {
				FileHandler.set(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + ".winAmount", 1);
				FileHandler.set(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + ".id", 2);
				FileHandler.set(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + ".1.world", player.getWorld().getName());
				FileHandler.set(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + ".1.time", new Date().getHours() + ":" + new Date().getMinutes() + ":" + new Date().getSeconds());
				FileHandler.set(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + ".1.date", new Date().getDate() + "/" + new Date().getMonth() + "/" + new Date().getYear());
				FileHandler.set(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + ".1.key", vkey.getVKeyType());
			}
			else{
				int winAmount = (int) FileHandler.get(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + ".winAmount");
				int id = (int) FileHandler.get(FileType.PRIZE_LOG, "logs." + "." + prize.getPrizeID() + ".id");
				FileHandler.set(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + ".winAmount", winAmount+1);
				FileHandler.set(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + ".id", id+1);
				FileHandler.set(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + "." + id + ".world", player.getWorld().getName());
				FileHandler.set(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + "." + id + ".time", new Date().getHours() + ":" + new Date().getMinutes() + ":" + new Date().getSeconds());
				FileHandler.set(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + "." + id + ".date", new Date().getDate() + "/" + new Date().getMonth() + "/" + new Date().getYear());
				FileHandler.set(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + "." + id + ".key", vkey.getVKeyType());
			}
			return;
		}
	}

	public static boolean hasWon(Player player, Prize prize) {
		boolean hasWon = false;
		if(FileHandler.getFile(FileType.PLAYER_LOG).contains("logs." + player.getUniqueId().toString() + "." + prize.getPrizeID())) hasWon = true;
		return hasWon;
	}
	
	public static int getWinAmount(LogType type, Player player, Prize prize) {
		if(type.equals(LogType.PLAYER)) {
			if(FileHandler.getFile(FileType.PLAYER_LOG).contains("logs." + player.getUniqueId() + "." + prize.getPrizeID() + ".winAmount")) return (int) FileHandler.get(FileType.PLAYER_LOG, "logs." + player.getUniqueId() + "." + prize.getPrizeID() + ".winAmount");
		}
		if(type.equals(LogType.PRIZE)) {
			if(FileHandler.getFile(FileType.PRIZE_LOG).contains("logs." + prize.getPrizeID() + ".winAmount")) return (int) FileHandler.get(FileType.PRIZE_LOG, "logs." + prize.getPrizeID() + ".winAmount");
		}
		return 0;
	}
	
}
