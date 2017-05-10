package com.HamiStudios.ArchonCrates.Files;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.HamiStudios.ArchonCrates.API.Enums.Files;
import com.HamiStudios.ArchonCrates.API.Exceptions.NoValueException;
import com.HamiStudios.ArchonCrates.API.libs.FileInterface;

public class Crates implements FileInterface {
	
	// Instances of File and FileConfiguration
	private File file;
	private FileConfiguration fileconfig;
	private String filePath = "plugins/ArchonCrates/crates.yml";
	
	
	// Creates file constructor
	public Crates() {
		// Crates the instances of the File and FileConfiguration
		this.file = new File(this.filePath);
		this.fileconfig = YamlConfiguration.loadConfiguration(file);
	}
	
	// Get the File instance
	@Override
	public File getFile() {
		return this.file;
	}
	
	// Get the FileConfiguration instance
	@Override
	public FileConfiguration getFileConfiguration() {
		return this.fileconfig;
	}

	@Override
	public boolean save() {
		try {
			this.fileconfig.save(this.file);
			return true;
		} catch(IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean reload() {
		try {
			this.file = new File(this.filePath);
			this.fileconfig = YamlConfiguration.loadConfiguration(this.file);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean set(String path, Object value) {
		try {
			this.fileconfig.set(path, value);
			this.save();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Object get(String path) throws NoValueException {
		Object returnValue = this.fileconfig.get(path);
		if(returnValue == null) {
			throw new NoValueException(path, Files.CRATES);
		}
		return returnValue;
	}

	@Override
	public boolean exists() {
		if(new File(this.filePath).exists()) return true;
		return false;
	}

	@Override
	public boolean create() {
		try {
			// Default Crate
			this.set("Crates.default.title", "&aDefault Crate");
			this.set("Crates.default.block.ID", 54);
			this.set("Crates.default.block.data", 0);
			this.set("Crates.default.sounds.open", "BLOCK_CHEST_OPEN");
			this.set("Crates.default.sounds.scroll", "BLOCK_NOTE_BASS");
			this.set("Crates.default.sounds.win", "ENTITY_PLAYER_LEVELUP");
			this.set("Crates.default.win.playerEffects", false);
			this.set("Crates.default.win.firework", true);
			this.set("Crates.default.win.broadcast", false);
			this.set("Crates.default.win.messagePlayer", true);
			this.set("Crates.default.config.scrollDuration", 8);
			this.set("Crates.default.config.showcaseDuration", 10);
			this.set("Crates.default.config.colouredGlass", true);
			
			List<String> prizes = Arrays.asList(
					"Diamonds",
					"Food",
					"Sword",
					"Gold",
					"Tools",
					"DevHead"
					);
			
			this.set("Crates.default.prizes", prizes);

			List<String> keys = Arrays.asList(
					"default"
					);
			
			this.set("Crates.default.keys", keys);
			
			this.set("Crates.default.messages.broadcast", "&6<player> &fjust won &6<price.name> &fin a &6<crate.name>&f crate!");
			this.set("Crates.default.messages.player", "&fYou just won &6<price.name>&f!");
			this.set("Crates.default.messages.wrongKey", "&cWrong key, you can't use that key on this crate.");
			
			
			
			// Golden Crate
			this.set("Crates.golden.title", "&6Golden Crate");
			this.set("Crates.golden.block.ID", 130);
			this.set("Crates.golden.block.data", 0);
			this.set("Crates.golden.sounds.open", "BLOCK_CHEST_OPEN");
			this.set("Crates.golden.sounds.scroll", "BLOCK_NOTE_BASS");
			this.set("Crates.golden.sounds.win", "ENTITY_PLAYER_LEVELUP");
			this.set("Crates.golden.win.playerEffects", true);
			this.set("Crates.golden.win.firework", true);
			this.set("Crates.golden.win.broadcast", true);
			this.set("Crates.golden.win.messagePlayer", true);
			this.set("Crates.golden.config.scrollDuration", 8);
			this.set("Crates.golden.config.showcaseDuration", 10);
			this.set("Crates.golden.config.colouredGlass", true);
			
			prizes = Arrays.asList(
					"Diamonds",
					"Food",
					"Sword",
					"Gold",
					"Tools",
					"DevHead",
					"CrateKey",
					"NetherStar",
					"GodApple",
					"Emeralds"
					);
			
			this.set("Crates.golden.prizes", prizes);

			keys = Arrays.asList(
					"golden"
					);
			
			this.set("Crates.golden.keys", keys);
			
			this.set("Crates.golden.messages.broadcast", "&6<player> &fjust won &6<price.name> &fin a &6<crate.name>&f crate!");
			this.set("Crates.golden.messages.player", "&fYou just won &6<price.name>&f!");
			this.set("Crates.golden.messages.wrongKey", "&cWrong key, you can't use that key on this crate.");
			
			this.save();
			
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean setHeader() {
		return true;
	}

	

}
