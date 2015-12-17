package com.HamiStudios.ArchonCrates.Util;

import org.bukkit.World;

import com.HamiStudios.ArchonCrates.Files.FileHandler;

public class CrateFinder {

	public static String findCrateType(int blockId, double x, double y, double z, World world) {
		FileHandler.reload(FileType.LOCATIONS);
		String crateType = null;
		for(String s : FileHandler.getFile(FileType.CRATES).getConfigurationSection("Crates").getKeys(false)) {
			if(FileHandler.getFile(FileType.CRATES).getInt("Crates." + s + ".blockID") == blockId) {
				if(FileHandler.getFile(FileType.LOCATIONS).contains("crates." + s)) {
					for(String st : FileHandler.getFile(FileType.LOCATIONS).getConfigurationSection("crates." + s).getKeys(false)) {
						if(FileHandler.getFile(FileType.LOCATIONS).getInt("crates." + s + "." + st + ".x") == x) {
							if(FileHandler.getFile(FileType.LOCATIONS).getInt("crates." + s + "." + st + ".y") == y) {
								if(FileHandler.getFile(FileType.LOCATIONS).getInt("crates." + s + "." + st + ".z") == z) {
									if(FileHandler.getFile(FileType.LOCATIONS).getString("crates." + s + "." + st + ".world").equals(world.getName())) {
										crateType = s;
										break;
									}
								}
							}
						}
					}
				}
			}
		}
		return crateType;
	}
	
	public static String findCrateLocationId(int blockId, double x, double y, double z, World world) {
		FileHandler.reload(FileType.LOCATIONS);
		String crateid = null;
		for(String s : FileHandler.getFile(FileType.CRATES).getConfigurationSection("Crates").getKeys(false)) {
			if(FileHandler.getFile(FileType.CRATES).getInt("Crates." + s + ".blockID") == blockId) {
				if(FileHandler.getFile(FileType.LOCATIONS).contains("crates." + s)) {
					for(String st : FileHandler.getFile(FileType.LOCATIONS).getConfigurationSection("crates." + s).getKeys(false)) {
						if(FileHandler.getFile(FileType.LOCATIONS).getInt("crates." + s + "." + st + ".x") == x) {
							if(FileHandler.getFile(FileType.LOCATIONS).getInt("crates." + s + "." + st + ".y") == y) {
								if(FileHandler.getFile(FileType.LOCATIONS).getInt("crates." + s + "." + st + ".z") == z) {
									if(FileHandler.getFile(FileType.LOCATIONS).getString("crates." + s + "." + st + ".world").equals(world.getName())) {
										crateid = st;
										break;
									}
								}
							}
						}
					}
				}
			}
		}
		return crateid;
	}
	
	public static String findVCrateLocationId(int blockId, double x, double y, double z, World world) {
		FileHandler.reload(FileType.LOCATIONS);
		String crateid = null;
		if(FileHandler.getFile(FileType.VIRTUAL_CRATES).getInt("Virtual Crates.blockID") == blockId) {
			if(!FileHandler.getFile(FileType.LOCATIONS).getString("virtual crates").equalsIgnoreCase("[]") && !FileHandler.getFile(FileType.LOCATIONS).getString("virtual crates").equalsIgnoreCase("{}")) {
				for(String s : FileHandler.getFile(FileType.LOCATIONS).getConfigurationSection("virtual crates").getKeys(false)) {
					if(FileHandler.getFile(FileType.LOCATIONS).getInt("virtual crates." + s + ".x") == x) {
						if(FileHandler.getFile(FileType.LOCATIONS).getInt("virtual crates." + s + ".y") == y) {
							if(FileHandler.getFile(FileType.LOCATIONS).getInt("virtual crates." + s + ".z") == z) {
								if(FileHandler.getFile(FileType.LOCATIONS).getString("virtual crates." + s + ".world").equals(world.getName())) {
									crateid = s;
									break;
								}
							}
						}
					}
				}
			}
		}
		return crateid;
	}
	
	public static boolean isCrateType(String possableCrateType) {
		FileHandler.reload(FileType.LOCATIONS);
		String crateType = null;
		for(String s : FileHandler.getSection(FileType.CRATES, "Crates").getKeys(false)) {
			if(s.toUpperCase().equals(possableCrateType.toUpperCase())) {
				crateType = s;
				break;
			}
		}
		if(crateType != null) return true;
		return false;
	}
	
	public static boolean isVCrateType(String possableVCrateType) {
		FileHandler.reload(FileType.LOCATIONS);
		String vcrateType = null;
		for(String s : FileHandler.getSection(FileType.VIRTUAL_CRATES, "Virtual Crates").getKeys(false)) {
			if(s.toUpperCase().equals(possableVCrateType.toUpperCase())) {
				vcrateType = s;
				break;
			}
		}
		if(vcrateType != null) return true;
		return false;
	}
	
}
