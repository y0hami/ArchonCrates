package com.HamiStudios.ArchonCrates.Files;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class BuySign implements FileInterface {

	private File file;
	private FileConfiguration fileconfig;
	
	public BuySign() {
		file = new File("plugins/ArchonCrates/Signs/buy sign.yml");
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
		file = new File("plugins/ArchonCrates/Signs/buy sign.yml");
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
		getFile().set("Buy Sign.Line 1", "&f[&aBuy&f]");
		getFile().set("Buy Sign.Line 2", "&a<amount>");
		getFile().set("Buy Sign.Line 3", "&a<keyType> &fkey(s)");
		getFile().set("Buy Sign.Line 4", "&fPrice: &a£<price>");
		
		getFile().set("file version", "0.0.0.1");
		
		save();
	}

	@Override
	public void setHeader() {
	}

}
