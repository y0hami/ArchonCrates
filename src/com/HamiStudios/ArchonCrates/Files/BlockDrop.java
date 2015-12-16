package com.HamiStudios.ArchonCrates.Files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class BlockDrop implements FileInterface {

	private File file;
	private FileConfiguration fileconfig;
	
	public BlockDrop() {
		file = new File("plugins/ArchonCrates/Drops/block drops.yml");
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
		file = new File("plugins/ArchonCrates/Drops/block drops.yml");
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
		if(new File("plugins/ArchonCrates/Drops/block drops.yml").exists()) return true;
		return false;
	}

	@Override
	public void create() {
		
		fileconfig.set("Enabled", false);
		fileconfig.set("Blocks that drop.DIAMOND_ORE.enabled", true);
		fileconfig.set("Blocks that drop.DIAMOND_ORE.chance", 0.5);
		ArrayList<String> keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		fileconfig.set("Blocks that drop.DIAMOND_ORE.keysThatDrop", keysThatDrop);
		fileconfig.set("Blocks that drop.EMERALD_ORE.enabled", true);
		fileconfig.set("Blocks that drop.EMERALD_ORE.chance", 0.2);
		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		fileconfig.set("Blocks that drop.EMERALD_ORE.keysThatDrop", keysThatDrop);
		
		save();
	}

	@Override
	public void setHeader() {
		
	}
	
}
