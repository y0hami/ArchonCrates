package com.HamiStudios.ArchonCrates.API.Enums;

public enum LanguageType {

	// Errors
	ERROR_PREFIX("Language.Prefix"),
	ERROR_NO_PERMISSION("Language.NoPermission"),
	ERROR_INVALID_COMMAND("Language.InvalidCommand"),

	// Commands
	COMMAND_CREATE_ADDED_TO_INV ("Language.Commands.Create.AddedToInv"),

	// Events
	EVENT_CRATE_CREATED ("Language.Events.Crate.Created"),
	EVENT_CRATE_REMOVED ("Language.Events.Crate.Removed"),
	EVENT_CRATE_SNEAK_TO_REMOVE ("Language.Events.Crate.SneakToRemove");


	private String value;
	
	LanguageType(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
	
}
