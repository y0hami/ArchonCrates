package com.HamiStudios.ArchonCrates.API.Enums;

public enum Table {

	// Crates DB Tables
	CRATES_LOCATIONS ("locations", "CREATE TABLE IF NOT EXISTS `locations` (ID INTEGER PRIMARY KEY AUTOINCREMENT, CRATE_ID VARCHAR(256) NOT NULL, CREATOR VARCHAR(256) NOT NULL," +
			"X FLOAT NOT NULL, Y FLOAT NOT NULL, Z FLOAT NOT NULL, TIME_CREATED VARCHAR(26) NOT NULL);");

	private String name;
	private String createStatement;

	Table(String name, String createStatement) {
		this.name = name;
		this.createStatement = createStatement;
	}

	public String getName() {
		return this.name;
	}

	public String getCreateStatement() {
		return this.createStatement;
	}

}
