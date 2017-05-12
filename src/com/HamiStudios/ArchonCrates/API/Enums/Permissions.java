package com.HamiStudios.ArchonCrates.API.Enums;

public enum Permissions {

	// List of Permissions

	// Commands
	COMMAND_USE ("archoncrates.command.use"),
	COMMAND_HELP ("archoncrates.command.help");

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
