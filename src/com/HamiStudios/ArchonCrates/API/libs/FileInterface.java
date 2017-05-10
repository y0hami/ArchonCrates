package com.HamiStudios.ArchonCrates.API.libs;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;

import com.HamiStudios.ArchonCrates.API.Exceptions.NoValueException;

public interface FileInterface {
	public File getFile();
	public FileConfiguration getFileConfiguration();
	public boolean save();
	public boolean reload();
	public boolean set(String path, Object value);
	public Object get(String path) throws NoValueException;
	public boolean exists();
	public boolean create();
	public boolean setHeader();
}