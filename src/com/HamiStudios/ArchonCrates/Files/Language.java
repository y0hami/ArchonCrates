package com.HamiStudios.ArchonCrates.Files;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Language implements FileInterface {

	private File file;
	private FileConfiguration fileconfig;
	
	public Language() {
		file = new File("plugins/ArchonCrates/Extra/language.yml");
		fileconfig = YamlConfiguration.loadConfiguration(file);
	}
	
	@Override
	public FileConfiguration getFile() {
		return fileconfig;
	}
	@Override
	public void save() {
		try {
			fileconfig.save(file);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void reload() {
		file = new File("plugins/ArchonCrates/Extra/language.yml");
		fileconfig = YamlConfiguration.loadConfiguration(file);
	}
	@Override
	public void set(String path, Object value) {
		fileconfig.set(path, value);
		save();
	}
	@Override
	public Object get(String path) {
		return fileconfig.get(path);
	}
	@Override
	public boolean exists() {
		if(new File("plugins/ArchonCrates/Extra/language.yml").exists()) return true;
		return false;
	}
	@Override
	public void create() {
		fileconfig.set("PREFIX", "&7[&aArchonCrates&7] ");
		fileconfig.set("NO_PERMISSION", "&cYou don't have permission to do that!");
		fileconfig.set("TOO_MANY_ARGUMENTS", "&cYou have entered too many arguemtns!");
		fileconfig.set("TOO_FEW_ARGUMENTS", "&cYou have entered too few arguments!");
		fileconfig.set("ERROR", "&cError, try /archoncrates");
		fileconfig.set("PLAYER_ONLY_COMMAND", "&cThis command is for players only!");
		fileconfig.set("PLAYER_OFFLINE", "&cThat player is currently offline!");
		fileconfig.set("INVALID_PLAYER_NAME", "&cYou have entered an invalid player name!");
		fileconfig.set("COMMAND_CREATE_NO_SPACE", "&cYou don't have any space in your inventory!");
		fileconfig.set("COMMAND_CREATE_NOT_CRATE_TYPE", "&cThat crate type does not exist!");
		fileconfig.set("CREATE_CRATE", "&7You have created a new &e<crateType> &7crate!");
		fileconfig.set("CREATE_VIRTUAL_CRATE", "&7You have created a new virtural crate!");
		fileconfig.set("COMMAND_RELOAD_INVALID_INPUT", "&cYou have entered an invalid &7fileType&c!");
		fileconfig.set("COMMAND_RELOAD_ERROR", "&cThere was a error reloading the file(s)!");
		fileconfig.set("COMMAND_RELOAD_DONE", "&aReloaded the file(s) you entered!");
		fileconfig.set("COMMAND_KEY_NOT_KEY_TYPE", "&cYou have entered an invalid &7keyType&c!");
		fileconfig.set("COMMAND_KEY_NOT_VKEY_TYPE", "&cYou have entered an invalid &7vkeyType&c!");
		fileconfig.set("COMMAND_KEY_NOT_NUMBER", "&cYou have entered an invalid &7amount&c!");
		fileconfig.set("COMMAND_KEY_GIVEN", "&aGiven &7<amount> &akey(s) to &7<player>&a!");
		fileconfig.set("COMMAND_KEY_NO_SPACE", "&cThat player does not have any space in their inventory!");
		fileconfig.set("COMMAND_VKEY_GIVEN", "&aGiven &7<amount> &avirtual key(s) to &7<player>&a!");
		fileconfig.set("COMMAND_VKEY_REMOVED", "&cRemoved &7<amount> &cvirtual key(s) from &7<player>&c!");
		fileconfig.set("COMMAND_REMOVE_NOT_CRATE", "&cThat block is not a crate!");
		fileconfig.set("COMMAND_REMOVE_DONE", "&aCrate removed!");
		fileconfig.set("COMMAND_CRATE_INVALID_CRATE_TYPE", "&cYou have entered an invalid &7crateType&c!");
		fileconfig.set("COMMAND_CRATE_INVALID_KEY_TYPE", "&cYou have entered an invalid &7keyType&c!");
		fileconfig.set("COMMAND_HISTORY_INVALID_PLAYER", "&cThere is no data for that player!");
		
		fileconfig.set("COMMAND_VOUCHER_USE", "&aYou have successfully redeemed your voucher for your crate keys!");
		fileconfig.set("COMMAND_VOUCHER_NO_SPACE", "&cYou don't have enough space in your inventory!");
		fileconfig.set("COMMAND_VOUCHER_NEW", "&aSuccessfully created a new voucher for &7<player>&a!");
		fileconfig.set("COMMAND_VOUCHER_INVALID_VIRTUAL", "&cYou have entered an invalid argument for &7<virtual>&c! You must use &7true&c, &7false&c, &7yes&c, &7no&c, &7y&c, &7n");
		fileconfig.set("COMMAND_VOUCHER_INVALID_AMOUNT", "&cYou have entered an invalid &7amount&c! You need to enter an integer!");
		fileconfig.set("COMMAND_VOUCHER_INVALID", "&cYou have entered an invalid voucher!");
		fileconfig.set("COMMAND_VOUCHER_REDEEMED", "&aYou have successfully redeemed your voucher: &f<voucher>");
		
		fileconfig.set("BREAK_CRATE_DENY", "&cYou can't break a crate block!");
		fileconfig.set("BREAK_CRATE_ALLOW", "&aCrate removed!");
		fileconfig.set("CANT_OPEN_CRATE", "&cYou need a crate key to use a crate!");
		fileconfig.set("OPEN_CRATE_NO_PERMISSION", "&cYou don't have permission to open a crate!");
		fileconfig.set("OPEN_CRATE_NO_PERMISSION_VIRTUAL", "&cYou don't have permission to open a virtual crate!");
		
		fileconfig.set("SIGN_INVALID_AMOUNT", "&cYou have entered an invalid &7amount&c!");
		fileconfig.set("SIGN_INVALID_KEYTYPE", "&cYou have entered an invalid &7keyType&c!");
		fileconfig.set("SIGN_INVALID_PRICE", "&cYou have entered an invalid &7price&c!");
		fileconfig.set("SIGN_CREATED", "&aCreated a new buy sign!");
		fileconfig.set("SIGN_REMOVED", "&aRemoved buy sign!");
		fileconfig.set("SIGN_NOT_ENOUGH_MONEY", "&cYou don't have enough money to buy a key!");
		fileconfig.set("SIGN_BOUGHT_KEY", "&a£<price> has been taken from your account.");
		fileconfig.set("SIGN_CANT_BREAK", "&cYou can't break a buy sign!");

		getFile().set("file version", "0.0.0.1");
		
		save();
	}
	@Override
	public void setHeader() {
	}
	
}