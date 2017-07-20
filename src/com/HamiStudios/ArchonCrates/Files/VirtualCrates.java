package com.HamiStudios.ArchonCrates.Files;

import com.HamiStudios.ArchonCrates.API.Enums.Files;
import com.HamiStudios.ArchonCrates.API.Exceptions.NoValueException;
import com.HamiStudios.ArchonCrates.API.Libs.FileInterface;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class VirtualCrates implements FileInterface {

	// Instances of File and FileConfiguration
	private File file;
	private FileConfiguration fileconfig;
	private String filePath = "plugins/ArchonCrates/virtual crate.yml";


	// Creates file constructor
	public VirtualCrates() {
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
			throw new NoValueException(path, Files.VIRTUAL_CRATES);
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
			// Virtual Crate
			this.set("Virtual Crate.title", "&5Virtual Crate");
			this.set("Virtual Crate.block.ID", 130);
			this.set("Virtual Crate.block.data", 0);
			this.set("Virtual Crate.sounds.open", "BLOCK_CHEST_OPEN");
			this.set("Virtual Crate.sounds.scroll", "BLOCK_NOTE_BASS");
			this.set("Virtual Crate.sounds.win", "ENTITY_PLAYER_LEVELUP");
			this.set("Virtual Crate.win.firework", true);
			this.set("Virtual Crate.win.broadcast", false);
			this.set("Virtual Crate.win.messagePlayer", true);
			this.set("Virtual Crate.config.scrollDuration", -1);
			this.set("Virtual Crate.config.showcaseDuration", 10);
			this.set("Virtual Crate.config.colouredGlass", true);
			this.set("Virtual Crate.config.crateEffects", false);

			ArrayList<String> prizes = new ArrayList<>();
			prizes.add("Diamonds");
			prizes.add("Food");
			prizes.add("Sword");
			prizes.add("Gold");
			prizes.add("Tools");
			prizes.add("DevHead");

			this.set("Virtual Crate.keys.default", prizes);

			prizes = new ArrayList<>();
			prizes.add("Diamonds");
			prizes.add("Food");
			prizes.add("Sword");
			prizes.add("Gold");
			prizes.add("Tools");
			prizes.add("DevHead");
			prizes.add("CrateKey");
			prizes.add("NetherStar");
			prizes.add("GodApple");
			prizes.add("Emeralds");

			this.set("Virtual Crate.keys.golden", prizes);

			prizes = new ArrayList<>();
			prizes.add("Diamonds");
			prizes.add("Food");
			prizes.add("Sword");
			prizes.add("Gold");
			prizes.add("Tools");
			prizes.add("DevHead");
			prizes.add("CrateKey");
			prizes.add("NetherStar");
			prizes.add("GodApple");
			prizes.add("Emeralds");
			prizes.add("Beacon");

			this.set("Virtual Crate.keys.unique", prizes);

			this.set("Virtual Crate.messages.broadcast", "&5<player> &fjust won &5<prize_name> &fin a &5<crate_name>&f crate!");
			this.set("Virtual Crate.messages.player", "&fYou just won &5<prize_name>&f!");

			this.save();

			this.setHeader();

			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean setHeader() {
		this.fileconfig.options().header(
				"\n " +
				"Need help configuring? Visit the ArchonCrates documentation https://archoncrates.com/docs/" +
				Bukkit.getPluginManager().getPlugin("ArchonCrates").getDescription().getVersion().replaceAll("\\.", "-") +
				"/files/virtual-crate" +
				"\n \n"
			);

		this.save();

		return true;
	}

}
