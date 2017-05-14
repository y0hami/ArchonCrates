package com.HamiStudios.ArchonCrates.Files;

import com.HamiStudios.ArchonCrates.API.Enums.Files;
import com.HamiStudios.ArchonCrates.API.Exceptions.NoValueException;
import com.HamiStudios.ArchonCrates.API.Objects.Crate;
import com.HamiStudios.ArchonCrates.API.libs.FileInterface;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CrateData implements FileInterface {

	// Instances of File and FileConfiguration
	private File file;
	private FileConfiguration fileconfig;
	private String filePath = "plugins/ArchonCrates/data/crates.yml";


	// Creates file constructor
	public CrateData() {
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

	// Get a crate from the location given
	public Crate getCrate(float x, float y, float z, World world) {
		// Get the crate ID
		String crateID = (String) this.fileconfig.get("Crates." + world.getName() + "." + ((int) x) + "." + ((int) y) + "." + ((int) z));

		// If the crate does not exist return null
		if(crateID == null) { return null; }

		// Try and create the crate from the ID
		Crate crate = new Crate(crateID);

		// If the crate is valid return the crate else return null
		if(crate.valid()) { return crate; }
		else { return null; }
	}

	// Create a new crate
	public boolean createCrate(float x, float y, float z, World world, Crate crate) {
		if(getCrate(x, y, z, world) == null) {
			this.fileconfig.set("Crates." + world.getName() + "." + ((int) x) + "." + ((int) y) + "." + ((int) z), crate.getID());

			// Save the changes
			this.save();
			return true;
		} else {
			return false;
		}
	}

	// Remove a crate
	public boolean removeCrate(float x, float y, float z, World world) {

		// Check if the crate exists
		if(this.getCrate(x, y, z, world) != null) {
			// Remove the crate ID
			this.fileconfig.set("Crates." + world.getName() + "." + ((int) x) + "." + ((int) y) + "." + ((int) z), null);

			// If the crate is the only crate in that world on that x, y and z
			if(this.fileconfig.getConfigurationSection("Crates." + world.getName() + "." + ((int) x) + "." + ((int) y)).getKeys(false).size() == 0) {
				// Remove the z
				this.fileconfig.set("Crates." + world.getName() + "." + ((int) x) + "." + ((int) y), null);

				// If the crate is the only crate in that world on that z and y
				if(this.fileconfig.getConfigurationSection("Crates." + world.getName() + "." + ((int) x)).getKeys(false).size() == 0) {
					// Remove the y
					this.fileconfig.set("Crates." + world.getName() + "." + ((int) x), null);

					// If the crate is the only crate in that world on that z
					if(this.fileconfig.getConfigurationSection("Crates." + world.getName()).getKeys(false).size() == 0) {
						// Remove the z
						this.fileconfig.set("Crates." + world.getName(), null);
					}
				}
			}

			// Save the changes
			this.save();

			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean exists() {
		if(new File(this.filePath).exists()) return true;
		return false;
	}

	@Override
	public boolean create() {
		try {
			// Create file with empty crates list
			this.set("Crates", new ArrayList<String>());

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
