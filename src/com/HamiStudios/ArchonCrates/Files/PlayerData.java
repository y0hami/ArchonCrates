package com.HamiStudios.ArchonCrates.Files;

import com.HamiStudios.ArchonCrates.API.Enums.PlayerDataType;
import com.HamiStudios.ArchonCrates.API.Exceptions.DataFileException;
import com.HamiStudios.ArchonCrates.API.Objects.ACPlayer;
import com.google.gson.Gson;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PlayerData {

	// Instances of File, Player and File Path
	private File file;
	private ACPlayer player;
	private String filePath;


	// PlayerData file constructor
	public PlayerData(Player player) {
		// Crates the instances of the File and FileConfiguration
		this.player = new ACPlayer(player);
		this.filePath = "plugins/ArchonCrates/data/players/" + this.player.getUUID() + ".json";
		this.file = new File(this.filePath);
	}

	public PlayerData(ACPlayer player) {
		// Crates the instances of the File and FileConfiguration
		this.player = player;
		this.filePath = "plugins/ArchonCrates/data/players/" + this.player.getUUID() + ".json";
		this.file = new File(this.filePath);
	}

	// Get the File instance
	public File getFile() {
		return this.file;
	}

	public Object get(PlayerDataType type) throws DataFileException {
		Gson gson = new Gson();
		ACPlayer player = null;

		try {
			player = gson.fromJson(new String(Files.readAllBytes(Paths.get(this.filePath))), ACPlayer.class);
		} catch (IOException e) {
			throw new DataFileException(this.filePath);
		}

		switch (type) {
			case UUID:
				return player.getUUID();
			case USERNAME:
				return player.getName();
			case PASSED_NAMES:
				return player.getPassedNames();
			case VIRTUAL_KEYS:
				return player.getVirtualKeys();
		}

		return null;
	}

	public boolean exists() {
		if(new File(this.filePath).exists()) return true;
		return false;
	}

	public boolean create() {
		try {
			PrintWriter writter = new PrintWriter(new FileOutputStream(this.filePath), true);

			Gson gson = new Gson();
			writter.write(gson.toJson(this.player));

			writter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

}
