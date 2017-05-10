package com.HamiStudios.ArchonCrates.API.Enums;

public enum Permissions {

	COMMAND_HELP ("archoncrates.command.help");
	
	private String value;
	
	Permissions(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
	
}
