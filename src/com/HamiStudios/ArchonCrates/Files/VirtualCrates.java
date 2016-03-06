package com.HamiStudios.ArchonCrates.Files;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class VirtualCrates implements FileInterface {

	private File file;
	private FileConfiguration fileconfig;
	
	public VirtualCrates() {
		this.file = new File("plugins/ArchonCrates/Crate/virtual crates.yml");
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
		this.file = new File("plugins/ArchonCrates/Crate/virtual crates.yml");
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
		if(new File("plugins/ArchonCrates/Crate/virtual crates.yml").exists()) return true;
		return false;
	}
	
	@Override
	public void create() {
		fileconfig.set("Virtual Crates.title", "&a           Mystery Crate");
		fileconfig.set("Virtual Crates.blockID", 54);
		fileconfig.set("Virtual Crates.blockData", 0);
		fileconfig.set("Virtual Crates.openSound", "BLOCK_CHEST_OPEN");
		fileconfig.set("Virtual Crates.winSound", "ENTITY_PLAYER_LEVELUP");
		fileconfig.set("Virtual Crates.scrollSound", "BLOCK_NOTE_BASS");
		fileconfig.set("Virtual Crates.firework", true);
		fileconfig.set("Virtual Crates.openDuration", 8);
		fileconfig.set("Virtual Crates.prizeDisplayDuration", 10);
		fileconfig.set("Virtual Crates.disableColouredGlass", false);

		getFile().set("file version", "0.0.0.1");
		
		save();
		setHeader();
	}
	
	@Override
	public void setHeader() {
		fileconfig.options().header(""
				+ "######################################################################################################################################## #\n"
				+ "                                                                                                                                         #\n"
				+ "                                      Virtual Crate Type Configuration File                                                              #\n"
				+ "                                                                                                                                         #\n"
				+ " Virtual Crates:                                                                                                                         #\n"
				+ "     title: '&a        Mystery Crate'       - The title of the GUI when the crate is opened                                              #\n"
				+ "     blockID: 54                            - The ID of the block you want the crate to be eg: 54 would be a chest block                 #\n"
				+ "     blockData: 0                           - The data value of the block you want the crate to be eg: 5:2 would be birch planks         #\n"
				+ "     openSound: CHEST_OPEN                  - The sound played to the player when they open a crate                                      #\n"
				+ "     winSound: LEVEL_UP                     - The sound played to the player when they win a prize                                       #\n"
				+ "     scrollSound: NOTE_BASS                 - The sound played as the prizes are scrolling in the crate GUI                              #\n"
				+ "     openDuration: 8                        - The duration of how long the crate GUI is open for until the player wins (seconds)         #\n"
				+ "     prizeDisplayDuration: 10               - The duration of which every prize is displayed (ticks 20 ticks = 1 second)                 #\n"
				+ "                                                                                                                                         #\n"
				+ "                                                                                                                                         #\n"
				+ "                                                                                                                                         #\n"
				+ "######################################################################################################################################## #\n");
		save();
	}
	
}
