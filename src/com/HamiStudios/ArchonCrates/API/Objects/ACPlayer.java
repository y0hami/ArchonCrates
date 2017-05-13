package com.HamiStudios.ArchonCrates.API.Objects;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ACPlayer {

	// Variables for the player
	private String uuid;
	private String name;
	private transient Player player;
	private ArrayList<String> passedNames = null;

	public ACPlayer(Player player) {
		// Set the variables for the player
		this.uuid = player.getUniqueId().toString();
		this.name = player.getName();
		this.player = player;

		this.passedNames = new ArrayList<>();
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
		if(this.passedNames == null) {
			return new ArrayList<>();
		} else {
			return this.passedNames;
		}
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

}
