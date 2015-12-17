package com.HamiStudios.ArchonCrates.Files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Crates implements FileInterface {

	private File file;
	private FileConfiguration fileconfig;
	
	public Crates() {
		this.file = new File("plugins/ArchonCrates/Crate/crates.yml");
		this.fileconfig = YamlConfiguration.loadConfiguration(file);
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
		this.file = new File("plugins/ArchonCrates/Crate/crates.yml");
		this.fileconfig = YamlConfiguration.loadConfiguration(file);
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
		if(new File("plugins/ArchonCrates/Crate/crates.yml").exists()) return true;
		return false;
	}
	
	@Override
	public void create() {
		fileconfig.set("Crates.default.title", "&a                Default Crate");
		fileconfig.set("Crates.default.blockID", 54);
		fileconfig.set("Crates.default.blockData", 0);
		fileconfig.set("Crates.default.openSound", "CHEST_OPEN");
		fileconfig.set("Crates.default.winSound", "LEVEL_UP");
		fileconfig.set("Crates.default.scrollSound", "NOTE_BASS");
		fileconfig.set("Crates.default.effects", true);
		fileconfig.set("Crates.default.firework", true);
		fileconfig.set("Crates.default.openDuration", 8);
		fileconfig.set("Crates.default.prizeDisplayDuration", 10);
		fileconfig.set("Crates.default.disableColouredGlass", false);
		ArrayList<String> useableKeys = new ArrayList<>();
		useableKeys.add("default");
		fileconfig.set("Crates.default.useableKeys", useableKeys);
		fileconfig.set("Crates.default.wrongKeyMessage", "&cYou can't use that key for this crate!");
		
		fileconfig.set("Crates.golden.title", "&6                Golden Crate");
		fileconfig.set("Crates.golden.blockID", 130);
		fileconfig.set("Crates.golden.blockData", 0);
		fileconfig.set("Crates.golden.openSound", "CHEST_OPEN");
		fileconfig.set("Crates.golden.winSound", "LEVEL_UP");
		fileconfig.set("Crates.golden.scrollSound", "NOTE_BASS");
		fileconfig.set("Crates.golden.effects", true);
		fileconfig.set("Crates.golden.firework", true);
		fileconfig.set("Crates.golden.openDuration", 8);
		fileconfig.set("Crates.golden.prizeDisplayDuration", 10);
		fileconfig.set("Crates.golden.disableColouredGlass", false);
		useableKeys = new ArrayList<>();
		useableKeys.add("golden");
		fileconfig.set("Crates.golden.useableKeys", useableKeys);
		fileconfig.set("Crates.golden.wrongKeyMessage", "&cYou can't use that key for this crate!");

		getFile().set("file version", "0.0.0.1");
		
		save();
		setHeader();
	}
	
	@Override
	public void setHeader() {
		fileconfig.options().header(""
				+ "######################################################################################################################################## #\n"
				+ "                                                                                                                                         #\n"
				+ "                                      Crate Type Configuration File                                                                      #\n"
				+ "                                                                                                                                         #\n"
				+ " Crates:                                                                                                                                 #\n"
				+ "   default:                                 - Crate type name (Can be anything) used to create crates of this type                       #\n"
				+ "     title: '&a        Default Crate'       - The title of the GUI when the crate is opened                                              #\n"
				+ "     blockID: 54                            - The ID of the block you want the crate to be eg: 54 would be a chest block                 #\n"
				+ "     blockData: 0                           - The data value of the block you want the crate to be eg: 5:2 would be birch planks         #\n"
				+ "     openSound: CHEST_OPEN                  - The sound played to the player when they open a crate                                      #\n"
				+ "     winSound: LEVEL_UP                     - The sound played to the player when they win a prize                                       #\n"
				+ "     scrollSound: NOTE_BASS                 - The sound played as the prizes are scrolling in the crate GUI                              #\n"
				+ "     effects: true                          - If true it will play the effect defined (below) when a player is using a crate             #\n"
				+ "     firework: true                         - If true it will lurch a firework when a player wins                                        #\n"
				+ "     openDuration: 8                        - The duration of how long the crate GUI is open for until the player wins (seconds)         #\n"
				+ "     prizeDisplayDuration: 10               - The duration of which every prize is displayed (ticks 20 ticks = 1 second)                 #\n"
				+ "     useableKeys:                                                                                                                        #\n"
				+ "     - default                                                                                                                           #\n"
				+ "     wrongKeyMessage: '&cYou can't use that key for this crate!'                                                                         #\n"
				+ "                                                                                                                                         #\n"
				+ "                                                                                                                                         #\n"
				+ "                                                                                                                                         #\n"
				+ "######################################################################################################################################## #\n");
		save();
	}
	
}
