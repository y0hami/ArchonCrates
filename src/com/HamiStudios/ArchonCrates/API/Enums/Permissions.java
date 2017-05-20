package com.HamiStudios.ArchonCrates.API.Enums;

public enum Permissions {

	// List of Permissions

	// Commands
	COMMAND_USE ("archoncrates.command.use"),
	COMMAND_HELP ("archoncrates.command.help"),
	COMMAND_KEY_PLAYER ("archoncrates.command.key.player"),
	COMMAND_KEY_ALL ("archoncrates.command.key.all"),
	COMMAND_CRATES ("archoncrates.command.crates"),

	// Create
	CREATE_CRATE ("archoncrates.create.physical");

	// Permission value
	private String value;
	
	Permissions(String value) { this.value = value; }

	// Return the permission value
	public String value() {
		return this.value;
	}
	
}
