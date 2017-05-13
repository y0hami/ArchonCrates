package com.HamiStudios.ArchonCrates.Files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class CrateData {

	// Instances of File and FileConfiguration
	private File file;
	private String filePath = "plugins/ArchonCrates/data/crates.json";


	// Creates file constructor
	public CrateData() {
		// Crates the instances of the File and FileConfiguration
		this.file = new File(this.filePath);
	}

	// Get the File instance
	public File getFile() {
		return this.file;
	}


	public boolean exists() {
		if(new File(this.filePath).exists()) return true;
		return false;
	}

	public boolean create() {
		try {
			PrintWriter writter = new PrintWriter(new FileOutputStream(this.filePath), true);
			writter.write("{ \"crates\": [] }");
			writter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

}
