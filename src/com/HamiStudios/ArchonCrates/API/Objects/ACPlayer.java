package com.HamiStudios.ArchonCrates.API.Objects;

import com.HamiStudios.ArchonCrates.API.Enums.PlayerDataType;
import com.HamiStudios.ArchonCrates.Files.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ACPlayer {

	// Variables for the player
	private String uuid;
	private String name;
	private transient Player player;
	private ArrayList<String> passedNames = null;
	private Map<String, Integer> virtualKeys;


	public ACPlayer(Player player) {
		// Set the variables for the player

		this.uuid = player.getUniqueId().toString();
		this.name = player.getName();
		this.player = player;

		this.passedNames = new ArrayList<>();
		this.virtualKeys = new HashMap<>();

		ArrayList<String> passedNames = (ArrayList<String>) PlayerData.get(this, PlayerDataType.PASSED_NAMES);
		if(passedNames.size() > 0) {
			this.passedNames = passedNames;
		}

		HashMap<String, Integer> keys = (HashMap<String, Integer>) PlayerData.get(this, PlayerDataType.VIRTUAL_KEYS);
		if(keys.size() > 0) {
			this.virtualKeys = keys;
		}
	}


	// Getters

	// Get the players name
	public String getName() {
		return this.name;
	}

	// Get the players UUID
	public String getUUID() {
		return this.uuid;
	}

	// Get the Bukkit Player object
	public Player getPlayer() {
		return this.player;
	}

	// Get a list of all passed names of a player
	public ArrayList<String> getPassedNames() {
		if(this.passedNames == null) { return new ArrayList<>(); }
		return this.passedNames;
	}

	// Get the all the virtual keys a player has
	public Map<String, Integer> getVirtualKeys() { return this.virtualKeys; }

	// Get the amount of a virtual key a player has
	public int getVirtualKey(Key key) {
		if(key.valid()) {
			if(this.virtualKeys.get(key.getID()) != null) {
				return this.virtualKeys.get(key.getID());
			}
		}
		return 0;
	}


	// Setters

	// Sets the player name
	public void setName(String value) { this.name = name; }

	// Sets the player UUID
	public void setUUID(String value) { this.uuid = value; }

	// Sets the player
	public void setPlayer(Player value) { this.player = value; }

	// Sets the passed usernames
	public void setPassedNames(ArrayList<String> value) { this.passedNames = value; }

	// Adds a username to the passed usernames
	public void addToPassedUsernames(String value) { this.passedNames.add(value); }

	// Add virtual keys to a player
	public void addVirtualKey(Key key, int amount) {
		// Check if the player currently has a number of keys
		if(this.virtualKeys.get(key.getID()) != null) {
			// If they have a number of keys get the amount they have and add the amount given to it
			this.virtualKeys.put(key.getID(), this.virtualKeys.get(key.getID()) + amount);
		} else {
			// Else set the key amount to the amount given
			this.virtualKeys.put(key.getID(), amount);
		}

		// Add the new value to the players.db
		PlayerData.addVirtualKey(this, key, amount);
	}

	// Remove virtual keys from a player
	public void removeVirtualKey(Key key, int amount) {
		// Check if the player currently has a number of keys
		if(this.virtualKeys.get(key.getID()) != null) {
			// If they have a number if the number removing the amount given is less or equal to 0
			if((this.virtualKeys.get(key.getID()) - amount) <= 0) {
				// Set it to 0
				this.virtualKeys.put(key.getID(), 0);
			} else {
				// If the number is larger then 0 set it to the number minus the amount given
				this.virtualKeys.put(key.getID(), this.virtualKeys.get(key.getID()) - amount);
			}
		} else {
			// If there is no number just set it to 0
			this.virtualKeys.put(key.getID(), 0);
		}

		// Add the new value to the players file
//		playerData.create();
	}


	// Other useful methods

	public void sendMessage(String message) {
		this.player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}

	public void sendSpace() {
		this.player.sendMessage(" ");
	}

	public boolean hasPermission(String permission) {
		if(this.player.hasPermission(permission)) return true;
		return false;
	}


}
