package com.HamiStudios.ArchonCrates.API.Objects;

import com.HamiStudios.ArchonCrates.Files.PlayerData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ACPlayer {

	// Variables for the player
	private String uuid;
	private String name;
	private transient Player player;
	private ArrayList<String> passedNames = null;
	private Map<String, Double> virtualKeys;


	public ACPlayer(Player player) {
		// Set the variables for the player

		try {

			Gson gson = new Gson();
			Type type = new TypeToken<Map<String, Object>>(){}.getType();
			Map<String, Object> data = gson.fromJson(new String(Files.readAllBytes(Paths.get("plugins/ArchonCrates/data/players/" + player.getUniqueId().toString() + ".json"))), type);

			this.uuid = (String) data.get("uuid");
			this.name = (String) data.get("name");
			this.player = player;

			this.passedNames = (ArrayList<String>) data.get("passedNames");
			this.virtualKeys = (Map<String, Double>) data.get("virtualKeys");

		} catch (IOException e) {

			this.uuid = player.getUniqueId().toString();
			this.name = player.getName();
			this.player = player;

			this.passedNames = new ArrayList<>();
			this.virtualKeys = new HashMap<>();

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
	public Map<String, Double> getVirtualKeys() { return this.virtualKeys; }

	// Get the amount of a virtual key a player has
	public int getVirtualKey(Key key) {
		if(key.valid()) {
			if(this.virtualKeys.get(key.getID()) != null) {
				return this.virtualKeys.get(key.getID()).intValue();
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
			this.virtualKeys.put(key.getID(), (double) amount);
		}

		// Get a instance of the player data
		PlayerData playerData = new PlayerData(this);
		// Add the new value to the players file
		playerData.create();
	}

	// Remove virtual keys from a player
	public void removeVirtualKey(Key key, int amount) {
		// Check if the player currently has a number of keys
		if(this.virtualKeys.get(key.getID()) != null) {
			// If they have a number if the number removing the amount given is less or equal to 0
			if((this.virtualKeys.get(key.getID()) - amount) <= 0) {
				// Set it to 0
				this.virtualKeys.put(key.getID(), (double) 0);
			} else {
				// If the number is larger then 0 set it to the number minus the amount given
				this.virtualKeys.put(key.getID(), this.virtualKeys.get(key.getID()) - amount);
			}
		} else {
			// If there is no number just set it to 0
			this.virtualKeys.put(key.getID(), (double) 0);
		}

		// Get an instance of the player data
		PlayerData playerData = new PlayerData(this);
		// Add the new value to the players file
		playerData.create();
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
