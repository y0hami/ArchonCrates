package com.HamiStudios.ArchonCrates.API.Enums;

public enum Permissions {

	// List of Permissions

	// Commands
	COMMAND_USE ("archoncrates.command.use"),
	COMMAND_HELP ("archoncrates.command.help"),

	// Create
	CREATE_PHYSICAL ("archoncrates.create.physical"),
	CREATE_VIRTUAL ("archoncrates.create.virtual");

	// Permission value
	private String value;
	
	Permissions(String value) {
		this.value = value;
	}

	// Return the permission value
	public String value() {
		return this.value;
	}
	
}
