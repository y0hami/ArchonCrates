package com.HamiStudios.ArchonCrates.Files;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config implements FileInterface {

	private File file;
	private FileConfiguration fileconfig;
	
	public Config() {
		file = new File("plugins/ArchonCrates/config.yml");
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
		file = new File("plugins/ArchonCrates/config.yml");
		fileconfig = YamlConfiguration.loadConfiguration(file);
	}

	@Override
	public void set(String path, Object value) {
		getFile().set(path, value);
		save();
	}
	
	@Override
	public Object get(String path) {
		return getFile().get(path);
	}
	
	@Override
	public boolean exists() {
		if(file.exists()) return true;
		return false;
	}

	@Override
	public void create() {
		getFile().set("Check for updates", true);
		getFile().set("Data Logging", true);
		
		getFile().set("file version.config", "0.0.0.1");
		getFile().set("file version.crate loot", "0.0.0.1");
		getFile().set("file version.crates", "0.0.0.1");
		getFile().set("file version.custom gui", "0.0.0.1");
		getFile().set("file version.keys", "0.0.0.1");
		getFile().set("file version.virtual crates", "0.0.0.1");
		getFile().set("file version.virtual keys", "0.0.0.1");
		getFile().set("file version.data", "0.0.0.1");
		getFile().set("file version.locations", "0.0.0.1");
		getFile().set("file version.mob drops", "0.0.0.1");
		getFile().set("file version.language", "0.0.0.1");
		getFile().set("file version.permissions", "0.0.0.1");
		getFile().set("file version.player log", "0.0.0.1");
		getFile().set("file version.prize log", "0.0.0.1");
		getFile().set("file version.buy sign", "0.0.0.1");
		
		save();
		setHeader();
	}

	@Override
	public void setHeader() {
		getFile().options().header(""
				+ "############################################################################################################################## #\n"
				+ "                                                                                                                               #\n"
				+ "                                           ArchonCrates Main Configuration File                                                #\n"
				+ "                                                                                                                               #\n"
				+ "                                                                                                                               #\n"
				+ "  Don't change anything in this file apart from \"Check for updates\" which if true will alert you if there is updates for       #\n"
				+ "  ArchonCrates. It is recommend to keep this feature on!                                                                       #\n"
				+ "                                                                                                                               #\n"
				+ "                                                                                                                               #\n"
				+ "                                                                                                                               #\n"
				+ "############################################################################################################################## #\n");
		save();
	}

}