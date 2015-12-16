package com.HamiStudios.ArchonCrates.Tasks;

import com.HamiStudios.ArchonCrates.Files.FileHandler;
import com.HamiStudios.ArchonCrates.Util.FileType;

public class FileUpdater {

	public static void update() {
		
		for(String s : FileHandler.getFile(FileType.CRATES).getConfigurationSection("Crates").getKeys(false)) {
			if(!FileHandler.getFile(FileType.CRATES).contains("Crates." + s + ".firework")) {
				FileHandler.getFile(FileType.CRATES).set("Crates." + s + ".firework", true);
				FileHandler.save(FileType.CRATES);
				FileHandler.reloadAll();
			}
		}
		
		if(!FileHandler.getFile(FileType.VIRTUAL_CRATES).contains("Virtual Crates.firework")) {
			FileHandler.getFile(FileType.VIRTUAL_CRATES).set("Virtual Crates.firework", true);
			FileHandler.save(FileType.VIRTUAL_CRATES);
			FileHandler.reloadAll();
		}
		
	}
	
}
