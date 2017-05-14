package com.HamiStudios.ArchonCrates.API.Enums;

public enum Menu {

	CRATE_TYPE ("Pick a crate type..."),
	CREATE_CRATE ("Pick a crate to create..."),
	KEY_TYPE ("Pick a key type..."),
	GIVE_KEY ("Pick a key to give..."),
	GIVE_VIRTUAL_KEY ("Pick a virtual key to give...");

	private String title;

	Menu(String title) {
		this.title = title;
	}

	public String getTitle() { return this.title; }

}
