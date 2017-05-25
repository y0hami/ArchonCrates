package com.HamiStudios.ArchonCrates.API.Enums;


public enum Files {

	// File Enums
	CRATES ("Crates", "crates.yml", "plugins/ArchonCrates/"),
	VIRTUAL_CRATES ("Virtual Crates", "virtual crates.yml", "plugins/ArchonCrates/"),
	KEYS ("Keys", "keys.yml", "plugins/ArchonCrates/"),
	PRIZES ("Prizes", "prizes.yml", "plugins/ArchonCrates/"),
	LANGUAGE ("Language", "language.yml", "plugins/ArchonCrates/"),
	CRATE_DATA ("CrateData", "crates.db", "plugins/ArchonCrates/data/"),
	PLAYER_DATA ("PlayersData", "players.db", "plugins/ArchonCrates/data/"),
	VIRTUAL_CRATE_LAYOUT ("VC Layout", "vc layout.json", "plugins/ArchonCrates/");

	// Variables for file values
	private String name;
	private String fileName;
	private String filePath;
	
	// Files Enum constructor
	Files(String name, String fileName, String filePath) {
		this.name = name;
		this.fileName = fileName;
		this.filePath = filePath;
	}
	
	// Get the given name for the file
	public String getName() {
		return this.name;
	}
	
	// Get the filename for the file
	public String getFileName() {
		return this.fileName;
	}
	
	// Get the path to the file directory
	public String getFilePath() {
		return this.filePath;
	}
	
}
