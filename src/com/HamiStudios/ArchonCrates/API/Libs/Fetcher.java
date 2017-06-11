package com.HamiStudios.ArchonCrates.API.Libs;

import com.HamiStudios.ArchonCrates.API.Enums.Files;
import com.HamiStudios.ArchonCrates.API.Objects.Crate;
import com.HamiStudios.ArchonCrates.API.Objects.Key;
import com.HamiStudios.ArchonCrates.API.Objects.Prize;
import com.HamiStudios.ArchonCrates.Files.VirtualCrates;

import java.util.ArrayList;

public class Fetcher {

	// Get all crates

	/**
	 * Get all valid crates from the crates file.
	 *
	 * @return an ArrayList containing all valid crates.
	 */
	public static ArrayList<Crate> getCrates() {

		// Get the crates file
		FileHandler fileHandler = new FileHandler(Files.CRATES);
		// Create an empty array to store the crates
		ArrayList<Crate> crates = new ArrayList<>();

		// For every crate ID in the crate file
		for (String crateID : fileHandler.getFileConfiguration().getConfigurationSection("Crates").getKeys(false)) {
			// Create a new instance of the crate from the ID
			Crate crate = new Crate(crateID);
			// If the crate is valid add it to the crates array
			if(crate.valid()) { crates.add(crate); }
		}

		// Return the array of crates
		return crates;
	}

	// Get all prizes

	/**
	 * Get all valid prizes from the prize file.
	 *
	 * @return an ArrayList containing all valid prizes.
	 */
	public static ArrayList<Prize> getPrizes() {

		// Get the prizes file
		FileHandler fileHandler = new FileHandler(Files.PRIZES);
		// Creates an empty array to store the prizes
		ArrayList<Prize> prizes = new ArrayList<>();

		// For every prize ID in the prize file
		for (String prizeID : fileHandler.getFileConfiguration().getConfigurationSection("Prizes").getKeys(false)) {
			// Create a new instance of the prize
			Prize prize = new Prize(prizeID);
			// If the prize is valid add it to the prize array
			if(prize.valid()) { prizes.add(prize); }
		}

		// Return the array of prizes
		return prizes;
	}

	// Get all keys

	/**
	 * Get all valid keys from the key file.
	 *
	 * @return an ArrayList containing all valid keys.
	 */
	public static ArrayList<Key> getKeys() {

		// Get the keys file
		FileHandler fileHandler = new FileHandler(Files.KEYS);
		// Create an empty array to store the keys
		ArrayList<Key> keys = new ArrayList<>();

		// For every key ID in the key file
		for (String keyID : fileHandler.getFileConfiguration().getConfigurationSection("Keys").getKeys(false)) {
			// Create a new instance of the key
			Key key = new Key(keyID);
			// If the key is valid add it to the key array
			if(key.valid()) { keys.add(key); }
		}

		// Return the array of keys
		return keys;
	}

	// Get all virtual keys

	/**
	 * Get all valid keys used as virtual keys in the virtual crate file.
	 *
	 * @return an ArrayList containing all valid virtual keys.
	 */
	public static ArrayList<Key> getVirtualKeys() {

		// Get the keys file
		FileHandler fileHandler = new FileHandler(Files.VIRTUAL_CRATES);
		VirtualCrates virtualCrates = new VirtualCrates();

		// Create an empty array to store the keys
		ArrayList<Key> keys = new ArrayList<>();

		for (String keyName : virtualCrates.getFileConfiguration().getConfigurationSection("Virtual Crate.keys").getKeys(false)) {
			Key key = new Key(keyName);
			if(key.valid()) { keys.add(key); }
		}

		// Return the array of keys
		return keys;
	}

}
