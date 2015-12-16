package com.HamiStudios.ArchonCrates.Util;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.HamiStudios.ArchonCrates.API.Objects.Key;
import com.HamiStudios.ArchonCrates.Files.FileHandler;

public class VoucherUtil {

	public static String newVoucher() {
		return UUID.randomUUID().toString();
	}
	
	public static boolean voucherExists(String voucher) {
		if(FileHandler.getFile(FileType.VOUCHER).contains("data." + voucher)) return true;
		return false;
	}
	
	public static String getOwnerUUID(String voucher) {
		return FileHandler.getFile(FileType.VOUCHER).getString("data." + voucher + ".owner");
	}

	public static void addVoucher(String playerUUID, String voucher, String keyType, int amount, boolean virtual) {
		FileHandler.getFile(FileType.VOUCHER).set("data." + voucher + ".owner", playerUUID);
		FileHandler.getFile(FileType.VOUCHER).set("data." + voucher + ".keyType", keyType);
		FileHandler.getFile(FileType.VOUCHER).set("data." + voucher + ".amount", amount);
		FileHandler.getFile(FileType.VOUCHER).set("data." + voucher + ".virtual", virtual);
		FileHandler.save(FileType.VOUCHER);
	}
	
	public static void use(String playerUUID, String voucher) {
		String keyType = FileHandler.getFile(FileType.VOUCHER).getString("data." + voucher + ".keyType");
		int amount = FileHandler.getFile(FileType.VOUCHER).getInt("data." + voucher + ".amount");
		if(FileHandler.getFile(FileType.VOUCHER).getBoolean("data." + voucher + ".virtual")) {
			PlayerData.addVKey(playerUUID, keyType, amount);
			
			FileHandler.getFile(FileType.VOUCHER).set("data." + voucher, null);
			FileHandler.save(FileType.VOUCHER);
			
			return;
		}
		else{
			Player player = Bukkit.getPlayer(PlayerData.getName(playerUUID));
			if(player.getInventory().firstEmpty() == -1) {
				player.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_VOUCHER_NO_SPACE.toString(true));
				return;
			}
			else{
				while(amount != 0) {
					player.getInventory().addItem(new Key(keyType).getItem());
					amount--;
				}
				
				FileHandler.getFile(FileType.VOUCHER).set("data." + voucher, null);
				FileHandler.save(FileType.VOUCHER);
				
				return;
			}
		}
	}
	
	public static String getCommand() {
		return FileHandler.getFile(FileType.VOUCHER).getString("command");
	}
	
	public static boolean hasVoucher(String playerUUID) {
		if(!FileHandler.getFile(FileType.VOUCHER).getString("data").equalsIgnoreCase("[]") && !FileHandler.getFile(FileType.VOUCHER).getString("data").equalsIgnoreCase("{}")) {
			boolean has = false;
			for(String s : FileHandler.getFile(FileType.VOUCHER).getConfigurationSection("data").getKeys(false)) {
				if(FileHandler.getFile(FileType.VOUCHER).getString("data." + s + ".owner").equalsIgnoreCase(playerUUID)) {
					has = true;
				}
			}
			return has;
		}
		return false;
	}
	
	public static int getVoucherAmount(String playerUUID) {
		if(!FileHandler.getFile(FileType.VOUCHER).getString("data").equalsIgnoreCase("[]") && !FileHandler.getFile(FileType.VOUCHER).getString("data").equalsIgnoreCase("{}")) {
			int amount = 0;
			for(String s : FileHandler.getFile(FileType.VOUCHER).getConfigurationSection("data").getKeys(false)) {
				if(FileHandler.getFile(FileType.VOUCHER).getString("data." + s + ".owner").equalsIgnoreCase(playerUUID)) {
					amount++;
				}
			}
			return amount;
		}
		return 0;
	}
	
	public static ArrayList<String> getVouchers(String playerUUID) {
		if(!FileHandler.getFile(FileType.VOUCHER).getString("data").equalsIgnoreCase("[]") && !FileHandler.getFile(FileType.VOUCHER).getString("data").equalsIgnoreCase("{}")) {
			ArrayList<String> vouchers = new ArrayList<>();
			for(String s : FileHandler.getFile(FileType.VOUCHER).getConfigurationSection("data").getKeys(false)) {
				if(FileHandler.getFile(FileType.VOUCHER).getString("data." + s + ".owner").equalsIgnoreCase(playerUUID)) {
					vouchers.add(s);
				}
			}
			return vouchers;
		}
		return new ArrayList<String>();
	}
	
	public static String getkeyType(String voucher) {
		return FileHandler.getFile(FileType.VOUCHER).getString("data." + voucher + ".keyType");
	}
	
	public static int getAmount(String voucher) {
		return FileHandler.getFile(FileType.VOUCHER).getInt("data." + voucher + ".amount");
	}
	
}
