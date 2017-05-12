package com.HamiStudios.ArchonCrates.API.Enums;

public enum Database {

	CRATES ("crates");

	private String name;

	Database(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
