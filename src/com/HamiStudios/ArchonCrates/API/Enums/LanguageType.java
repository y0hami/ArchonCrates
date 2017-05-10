package com.HamiStudios.ArchonCrates.API.Enums;

public enum LanguageType {

	PREFIX ("Language.Prefix"),
	NO_PERMISSION ("Language.NoPermission"),
	INVALID_COMMAND ("Language.InvalidCommand"),
	COMMANDS_CREATE_INVALID_TYPE ("Language.Commands.Create.InvalidType");
	
	private String value;
	
	LanguageType(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
	
}
