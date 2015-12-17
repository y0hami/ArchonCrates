package com.HamiStudios.ArchonCrates.Tasks;

import com.HamiStudios.ArchonCrates.Files.FileHandler;
import com.HamiStudios.ArchonCrates.Util.FileType;

public class FileUpdater {

	public static void update() {
		
		for(String s : FileHandler.getFile(FileType.CRATES).getConfigurationSection("Crates").getKeys(false)) {
			if(!FileHandler.getFile(FileType.CRATES).contains("Crates." + s + ".firework")) {
				FileHandler.getFile(FileType.CRATES).set("Crates." + s + ".firework", true);
				FileHandler.save(FileType.CRATES);
				FileHandler.reloadAll();
			}
		}
		
		if(!FileHandler.getFile(FileType.VIRTUAL_CRATES).contains("Virtual Crates.firework")) {
			FileHandler.getFile(FileType.VIRTUAL_CRATES).set("Virtual Crates.firework", true);
			FileHandler.save(FileType.VIRTUAL_CRATES);
			FileHandler.reloadAll();
		}
		
		if(!FileHandler.getFile(FileType.LANGUAGE).contains("COMMAND_KEY_NO_SPACE")) {
			FileHandler.getFile(FileType.LANGUAGE).set("COMMAND_KEY_NO_SPACE", "&cThat player does not have any space in their inventory!");
			FileHandler.save(FileType.LANGUAGE);
			FileHandler.reloadAll();
		}
		if(!FileHandler.getFile(FileType.LANGUAGE).contains("COMMAND_VOUCHER_USE")) {
			FileHandler.getFile(FileType.LANGUAGE).set("COMMAND_VOUCHER_USE", "&aYou have successfully redeemed your voucher for your crate keys!");
			FileHandler.save(FileType.LANGUAGE);
			FileHandler.reloadAll();
		}
		if(!FileHandler.getFile(FileType.LANGUAGE).contains("COMMAND_VOUCHER_NO_SPACE")) {
			FileHandler.getFile(FileType.LANGUAGE).set("COMMAND_VOUCHER_NO_SPACE", "&cYou don't have enough space in your inventory!");
			FileHandler.save(FileType.LANGUAGE);
			FileHandler.reloadAll();
		}
		if(!FileHandler.getFile(FileType.LANGUAGE).contains("COMMAND_VOUCHER_NEW")) {
			FileHandler.getFile(FileType.LANGUAGE).set("COMMAND_VOUCHER_NEW", "&aSuccessfully created a new voucher for &7<player>&a!");
			FileHandler.save(FileType.LANGUAGE);
			FileHandler.reloadAll();
		}
		if(!FileHandler.getFile(FileType.LANGUAGE).contains("COMMAND_VOUCHER_INVALID_VIRTUAL")) {
			FileHandler.getFile(FileType.LANGUAGE).set("COMMAND_VOUCHER_INVALID_VIRTUAL", "&cYou have entered an invalid argument for &7<virtual>&c! You must use &7true&c, &7false&c, &7yes&c, &7no&c, &7y&c, &7n");
			FileHandler.save(FileType.LANGUAGE);
			FileHandler.reloadAll();
		}
		if(!FileHandler.getFile(FileType.LANGUAGE).contains("COMMAND_VOUCHER_INVALID_AMOUNT")) {
			FileHandler.getFile(FileType.LANGUAGE).set("COMMAND_VOUCHER_INVALID_AMOUNT", "&cYou have entered an invalid &7amount&c! You need to enter an integer!");
			FileHandler.save(FileType.LANGUAGE);
			FileHandler.reloadAll();
		}
		if(!FileHandler.getFile(FileType.LANGUAGE).contains("COMMAND_VOUCHER_INVALID")) {
			FileHandler.getFile(FileType.LANGUAGE).set("COMMAND_VOUCHER_INVALID", "&cYou have entered an invalid voucher!");
			FileHandler.save(FileType.LANGUAGE);
			FileHandler.reloadAll();
		}
		if(!FileHandler.getFile(FileType.LANGUAGE).contains("COMMAND_VOUCHER_REDEEMED")) {
			FileHandler.getFile(FileType.LANGUAGE).set("COMMAND_VOUCHER_REDEEMED", "&aYou have successfully redeemed your voucher: &f<voucher>");
			FileHandler.save(FileType.LANGUAGE);
			FileHandler.reloadAll();
		}
		
		
		
		if(!FileHandler.getFile(FileType.PERMISSIONS).contains("COMMAND_VOUCHER_NEW")) {
			FileHandler.getFile(FileType.PERMISSIONS).set("COMMAND_VOUCHER_NEW", "archoncrates.command.voucher.new");
			FileHandler.save(FileType.PERMISSIONS);
			FileHandler.reloadAll();
		}
		if(!FileHandler.getFile(FileType.PERMISSIONS).contains("COMMAND_VOUCHER_REDEEM")) {
			FileHandler.getFile(FileType.PERMISSIONS).set("COMMAND_VOUCHER_REDEEM", "archoncrates.command.voucher.redeem");
			FileHandler.save(FileType.PERMISSIONS);
			FileHandler.reloadAll();
		}
		
	}
	
}
