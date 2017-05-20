package com.HamiStudios.ArchonCrates.API.Enums;

public enum LanguageType {

	// Errors
	ERROR_PREFIX("Language.Prefix"),
	ERROR_NO_PERMISSION("Language.NoPermission"),
	ERROR_INVALID_COMMAND("Language.InvalidCommand"),
	ERROR_PLAYER_ONLY_COMMAND ("Language.PlayerOnlyCommand"),

	// Commands
	COMMAND_CREATE_ADDED_TO_INV ("Language.Commands.Create.AddedToInv"),
	COMMAND_CREATE_NO_SPACE ("Language.Commands.Create.NoSpace"),
	COMMAND_KEY_INVALID_AMOUNT ("Language.Commands.Key.InvalidAmount"),
	COMMAND_KEY_PLAYER_OFFLINE ("Language.Commands.Key.OfflinePlayer"),
	COMMAND_KEY_INVALID_FORMAT ("Language.Commands.Key.InvalidFormat"),
	COMMAND_KEY_INVALID_KEY ("Language.Commands.Key.InvalidKey"),
	COMMAND_KEY_INVALID_KEY_TYPE ("Language.Commands.Key.InvalidKeyType"),

	// Events
	EVENT_CRATE_CREATED ("Language.Events.Crate.Created"),
	EVENT_CRATE_REMOVED ("Language.Events.Crate.Removed"),
	EVENT_CRATE_SNEAK_TO_REMOVE ("Language.Events.Crate.SneakToRemove"),
	EVENT_KEY_NO_PERMISSION ("Language.Events.Key.NoPermission"),
	EVENT_KEY_GIVEN_ALL ("Language.Events.Key.GivenAll"),
	EVENT_KEY_GIVEN_PLAYER ("Language.Events.Key.GivenPlayer"),
	EVENT_CRATES_NO_WORLD ("Language.Events.Crates.NoWorld");


	private String value;
	
	LanguageType(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
	
}
