package com.HamiStudios.ArchonCrates.API.Enums;

public enum Permissions {

	// List of Permissions

	// Commands
	COMMAND_USE ("archoncrates.command.use"),
	COMMAND_HELP ("archoncrates.command.help"),
	COMMAND_CREATE ("archoncrates.command.create"),
	COMMAND_KEY ("archoncrates.command.key"),
	COMMAND_KEY_PLAYER ("archoncrates.command.key.player"),
	COMMAND_KEY_ALL ("archoncrates.command.key.all"),
	COMMAND_CRATES ("archoncrates.command.crates"),
	COMMAND_CRATES_TELEPORT ("archoncrates.command.crates.teleport"),

	// Events
	CREATE_CRATE ("archoncrates.events.crate.create");


	// Permission value
	private String value;
	
	Permissions(String value) { this.value = value; }

	// Return the permission value
	public String value() {
		return this.value;
	}
	
}
