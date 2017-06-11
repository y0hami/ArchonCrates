package com.HamiStudios.ArchonCrates.API.Enums;

public enum Database {

	CRATES_DATA ("crates"),
	PLAYERS_DATA ("players");

	private String dbName;

	Database(String dbName) {
		this.dbName = dbName;
	}

	/**
	 * Returns the name of the database.
	 *
	 * @return  The database name.
	 */
	public String getDatabaseName() {
		return this.dbName;
	}

}
