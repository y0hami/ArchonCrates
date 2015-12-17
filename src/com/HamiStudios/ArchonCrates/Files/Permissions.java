package com.HamiStudios.ArchonCrates.Files;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Permissions implements FileInterface {

	private File file;
	private FileConfiguration fileconfig;
	
	public Permissions() {
		file = new File("plugins/ArchonCrates/Extra/permissions.yml");
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void reload() {
		file = new File("plugins/ArchonCrates/Extra/permissions.yml");
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
		if(new File("plugins/ArchonCrates/Extra/permissions.yml").exists()) return true;
		return false;
	}
	@Override
	public void create() {
		fileconfig.set("COMMAND_HELP", "archoncrates.command.help");
		fileconfig.set("COMMAND_REMOVE", "archoncrates.command.remove");
		fileconfig.set("COMMAND_INFO", "archoncrates.command.info");
		fileconfig.set("COMMAND_KEYS", "archoncrates.command.keys");
		fileconfig.set("COMMAND_VKEYS", "archoncrates.command.vkeys");
		fileconfig.set("COMMAND_CRATES", "archoncrates.command.crates");
		fileconfig.set("COMMAND_FILES", "archoncrates.command.files");
		fileconfig.set("COMMAND_UPDATE", "archoncrates.command.update");
		fileconfig.set("COMMAND_CREATE", "archoncrates.command.create");
		fileconfig.set("COMMAND_CREATE_VIRTUAL", "archoncrates.command.create.virtual");
		fileconfig.set("COMMAND_RELOAD", "archoncrates.command.reload");
		fileconfig.set("COMMAND_CRATE", "archoncrates.command.crate");
		fileconfig.set("COMMAND_KEY", "archoncrates.command.key");
		fileconfig.set("COMMAND_KEY_ALL", "archoncrates.command.key.all");
		fileconfig.set("COMMAND_VIRTUAL_KEYS", "archoncrates.command.vkey");
		fileconfig.set("COMMAND_VIRTUAL_KEYS_ALL", "archoncrates.command.vkey.all");
		fileconfig.set("COMMAND_REMOVE_VIRTUAL_KEY", "archoncrates.command.remove.vkey");
		fileconfig.set("COMMAND_HISTORY", "archoncrates.command.history");
		
		fileconfig.set("COMMAND_VOUCHER_NEW", "archoncrates.command.voucher.new");
		fileconfig.set("COMMAND_VOUCHER_REDEEM", "archoncrates.command.voucher.redeem");
		
		fileconfig.set("COMPLETE_TAB", "archoncrates.completetab");
		fileconfig.set("OPEN_CRATE_WITH_ANY_KEY", "archoncrates.crate.open.any");
		fileconfig.set("OPEN_CRATE", "archoncrates.crate.open");
		fileconfig.set("OPEN_VIRTUAL_CRATE", "archoncrates.crate.open.virtual");
		fileconfig.set("VIEW_PRIZES", "archoncrates.view.priszes");
		fileconfig.set("BREAK_CRATE", "archoncrates.event.break.crate");
		fileconfig.set("SIGN_USE_BUY", "archoncrates.sign.use.buy");
		fileconfig.set("SIGN_CREATE_NORAML", "archoncrates.sign.create");
		fileconfig.set("SIGN_CREATE_VIRTUAL", "archoncrates.sign.create.virtual");
		fileconfig.set("SIGN_BREAK", "archoncrates.sign.break");
		
		getFile().set("file version", "0.0.0.1");
		
		save();
	}
	@Override
	public void setHeader() {
	}
	
}
