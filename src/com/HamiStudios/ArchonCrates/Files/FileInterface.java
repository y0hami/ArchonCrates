package com.HamiStudios.ArchonCrates.Files;

import org.bukkit.configuration.file.FileConfiguration;

public interface FileInterface {
	public FileConfiguration getFile();
	public void save();
	public void reload();
	public void set(String path, Object value);
	public Object get(String path);
	public boolean exists();
	public void create();
	public void setHeader();
}
