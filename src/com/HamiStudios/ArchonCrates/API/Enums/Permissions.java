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

	// Events
	CREATE_CRATE ("archoncrates.events.crate.create"),
	OPEN_PHYSICAL_CRATE ("archoncrates.events.crate.physical.open"),
	OPEN_VIRTUAL_CRATE ("archoncrates.events.crate.virtual.open"),
	MOB_DROP_KEY ("archoncrates.events.keydrop.mob"),
	BLOCK_DROP_KEY ("archoncrates.events.keydrop.block");


	// Permission value
	private String value;
	
	Permissions(String value) { this.value = value; }

	// Return the permission value
	public String value() {
		return this.value;
	}
	
}
