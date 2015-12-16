package com.HamiStudios.ArchonCrates.Files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Data implements FileInterface {

	private File file;
	private FileConfiguration fileconfig;
	
	public Data() {
		file = new File("plugins/ArchonCrates/Dont edit/data.yml");
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
		file = new File("plugins/ArchonCrates/Dont edit/data.yml");
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
		if(new File("plugins/ArchonCrates/Dont edit/data.yml").exists()) return true;
		return false;
	}
	@Override
	public void create() {
		fileconfig.set("data", new ArrayList<>());

		getFile().set("file version", "0.0.0.1");
		
		save();
	}
	@Override
	public void setHeader() {
	}
	
}
