package com.HamiStudios.ArchonCrates.Files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class CustomGUI implements FileInterface {
	
	private File file;
	private FileConfiguration fileconfig;
	
	public CustomGUI() {
		file = new File("plugins/ArchonCrates/Crate/custom GUI.yml");
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
		file = new File("plugins/ArchonCrates/Crate/custom GUI.yml");
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
		if(new File("plugins/ArchonCrates/Crate/custom GUI.yml").exists()) return true;
		return false;
	}

	@Override
	public void create() {
		fileconfig.set("Virtual Keys GUI.rows", 3);
		fileconfig.set("Virtual Keys GUI.title", "&8Select your key: ");
		fileconfig.set("Virtual Keys GUI.keys.default.slot", 13);
		fileconfig.set("Virtual Keys GUI.keys.golden.slot", 15);
		fileconfig.set("Virtual Keys GUI.keys.unique.slot", 11);
		
		int x = 0;
		
		while(x != 27) {
			if(x == 11 || x == 13 || x == 15) {x++; continue;}
			fileconfig.set("Virtual Keys GUI.slots." + x +".itemID", 160);
			fileconfig.set("Virtual Keys GUI.slots." + x +".itemData", 15);
			fileconfig.set("Virtual Keys GUI.slots." + x +".stackSize", 1);
			fileconfig.set("Virtual Keys GUI.slots." + x +".displayname", "&0");
			fileconfig.set("Virtual Keys GUI.slots." + x +".lore", new ArrayList<>());
			x++;
		}
		
		
		fileconfig.set("Chance GUI.border.itemID", 160);
		fileconfig.set("Chance GUI.border.itemData", 15);
		fileconfig.set("Chance GUI.title", "&a               Prize Chances");
		fileconfig.set("Chance GUI.use GUI", true);

		getFile().set("file version", "0.0.0.1");
		
		save();
	}

	@Override
	public void setHeader() {
		
	}

}
