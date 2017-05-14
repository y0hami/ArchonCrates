package com.HamiStudios.ArchonCrates.API.Enums;

public enum Menu {

	CREATE_CRATE ("Pick a crate to create..."),
	CRATE_TYPE ("Pick a crate type...");

	private String title;

	Menu(String title) {
		this.title = title;
	}

	public String getTitle() { return this.title; }

}
