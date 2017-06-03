package com.HamiStudios.ArchonCrates.Files;

import com.HamiStudios.ArchonCrates.API.Enums.Files;
import com.HamiStudios.ArchonCrates.API.Exceptions.NoValueException;
import com.HamiStudios.ArchonCrates.API.Libs.FileInterface;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class KeyDrop implements FileInterface {

	// Instances of File and FileConfiguration
	private File file;
	private FileConfiguration fileconfig;
	private String filePath = "plugins/ArchonCrates/key drops.yml";


	// Crates file constructor
	public KeyDrop() {
		// Creates the instances of the File and FileConfiguration
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
			throw new NoValueException(path, Files.KEY_DROPS);
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
			// Set all default key drop values

			// Enable Block Drops (default is false)
			this.set("Blocks.enabled", false);

			// Coal Ore
			this.set("Blocks.drops.16.default", 3.0);

			// Redstone Ore
			this.set("Blocks.drops.73.default", 5.0);

			// Lapis Ore
			this.set("Blocks.drops.21.default", 5.0);
			this.set("Blocks.drops.21.unique", 0.3);

			// Nether Quartz Ore
			this.set("Blocks.drops.153.default", 6.0);

			// Diamond Ore
			this.set("Blocks.drops.56.default", 6.0);
			this.set("Blocks.drops.56.golden", 2.3);

			// Emerald Ore
			this.set("Blocks.drops.129.default", 8.0);
			this.set("Blocks.drops.129.golden", 3.5);
			this.set("Blocks.drops.129.unique", 2.0);



			// Enable Mob Drops (default is false)
			this.set("Mobs.enabled", false);

			// Zombie
			this.set("Mobs.drops.ZOMBIE.default", 2.0);

			// Creeper
			this.set("Mobs.drops.CREEPER.default", 2.0);

			// Witch
			this.set("Mobs.drops.WITCH.default", 7.0);
			this.set("Mobs.drops.WITCH.golden", 5.0);

			// Wither Skeleton
			this.set("Mobs.drops.WITHER_SKELETON.default", 7.0);
			this.set("Mobs.drops.WITHER_SKELETON.golden", 4.5);
			this.set("Mobs.drops.WITHER_SKELETON.unique", 3.0);

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
