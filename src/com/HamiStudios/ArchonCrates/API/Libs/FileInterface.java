package com.HamiStudios.ArchonCrates.API.Libs;

import com.HamiStudios.ArchonCrates.API.Exceptions.NoValueException;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public interface FileInterface {
	File getFile();
	FileConfiguration getFileConfiguration();
	boolean save();
	boolean reload();
	boolean set(String path, Object value);
	Object get(String path) throws NoValueException;
	boolean exists();
	boolean create();
	boolean setHeader();
}