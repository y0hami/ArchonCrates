package com.HamiStudios.ArchonCrates.API.Objects;

import com.HamiStudios.ArchonCrates.API.Enums.Files;
import com.HamiStudios.ArchonCrates.API.Exceptions.InvalidSoundValue;
import com.HamiStudios.ArchonCrates.API.Exceptions.NoValueException;
import com.HamiStudios.ArchonCrates.API.Libs.GetSound;
import com.HamiStudios.ArchonCrates.Files.VirtualCrates;
import org.bukkit.Sound;

import java.util.ArrayList;
import java.util.HashMap;

public class VirtualCrate {

	// Creates variables for all values for the crate
	private String title;
	private int blockID;
	private int blockData;
	private Sound openSound;
	private Sound scrollSound;
	private Sound winSound;
	private boolean displayCrateEffects;
	private boolean displayFireworks;
	private boolean broadcastWin;
	private boolean sendPlayerMessage;
	private int scrollDuration;
	private int showcaseDuration;
	private boolean displayColouredGlass;
	private HashMap<Key, ArrayList<Prize>> keys;
	private String broadcastMessage;
	private String playerMessage;


	private VirtualCrates crateFile;
	private boolean crateIsValid = true;


	// Crate Object Constructor
	public VirtualCrate() {
		this.crateFile = new VirtualCrates();

		try {
			this.title = (String) this.crateFile.get("Virtual Crate.title");
			this.blockID = (int) this.crateFile.get("Virtual Crate.block.ID");
			this.blockData = (int) this.crateFile.get("Virtual Crate.block.data");
			this.openSound = GetSound.get((String) this.crateFile.get("Virtual Crate.sounds.open"), null, Files.VIRTUAL_CRATES);
			this.scrollSound = GetSound.get((String) this.crateFile.get("Virtual Crate.sounds.scroll"), null, Files.VIRTUAL_CRATES);
			this.winSound = GetSound.get((String) this.crateFile.get("Virtual Crate.sounds.win"), null, Files.VIRTUAL_CRATES);
			this.displayCrateEffects = (boolean) this.crateFile.get("Virtual Crate.win.crateEffects");
			this.displayFireworks = (boolean) this.crateFile.get("Virtual Crate.win.firework");
			this.broadcastWin = (boolean) this.crateFile.get("Virtual Crate.win.broadcast");
			this.sendPlayerMessage = (boolean) this.crateFile.get("Virtual Crate.win.messagePlayer");
			this.scrollDuration = (int) this.crateFile.get("Virtual Crate.config.scrollDuration");
			this.showcaseDuration = (int) this.crateFile.get("Virtual Crate.config.showcaseDuration");
			this.displayColouredGlass = (boolean) this.crateFile.get("Virtual Crate.config.colouredGlass");

			this.keys = new HashMap<>();

			/*
			 * Get the list of key IDs and go through each and create a Key object, if it is valid add
			 * it to the array else set the crate to not valid
			*/
			for(String keyID : this.crateFile.getFileConfiguration().getConfigurationSection("Virtual Crate.keys").getKeys(false)) {
				Key key = new Key(keyID);
				if(key.valid()) {
					ArrayList<Prize> prizes = new ArrayList<>();
					for (String prizeName : this.crateFile.getFileConfiguration().getStringList("Virtual Crate.keys." + keyID)) {
						Prize prize = new Prize(prizeName);
						if(prize.valid()) { prizes.add(prize); }
						else {
							this.crateIsValid = false;
							break;
						}
					}

					this.keys.put(key, prizes);
				}
				else {
					this.crateIsValid = false;
					break;
				}
			}

			this.broadcastMessage = (String) this.crateFile.get("Virtual Crate.messages.broadcast");
			this.playerMessage = (String) this.crateFile.get("Virtual Crate.messages.player");
		} catch (NoValueException|InvalidSoundValue e) {
			this.crateIsValid = false;
		}
	}


	// Check if the crate is valid and not missing any fields in the configuration files
	public boolean valid() {
		return this.crateIsValid;
	}



	// Getters & Setters


	// Get the crate Title
	public String getTitle() {
		return this.title;
	}

	// Get the crate Block ID
	public int getBlockID() {
		return this.blockID;
	}

	// Get the crate Block Data
	public int getBlockData() {
		return this.blockData;
	}

	// Get the crate Open Sound
	public Sound getOpenSound() {
		return this.openSound;
	}

	// Get the crate Scroll Sound
	public Sound getScrollSound() {
		return this.scrollSound;
	}

	// Get the crate Win Sound
	public Sound getWinSound() {
		return this.winSound;
	}

	// Should the crate display effects around the player
	public boolean displayEffects() {
		return this.displayCrateEffects;
	}

	// Should a firework go off when a player wins
	public boolean firework() {
		return this.displayFireworks;
	}

	// Should the crate allow prizes to broadcast
	public boolean broadcast() {
		return this.broadcastWin;
	}

	// Should the crate allow the player to receive messages
	public boolean playerMessage() {
		return this.sendPlayerMessage;
	}

	// Get the crate Scroll Duration
	public int getScrollDuration() {
		return this.scrollDuration;
	}

	// Get the crate Showcase Duration
	public int getShowcaseDuration() {
		return this.showcaseDuration;
	}

	// Should display coloured glass
	public boolean showColouredGlass() { return this.displayColouredGlass; }

	// Get an array of Key objects
	public HashMap<Key, ArrayList<Prize>> getKeys() { return this.keys; }

	// Get the broadcast message
	public String getBroadcastMessage() { return this.broadcastMessage; }

	// Get the player message
	public String getPlayerMessage() { return this.playerMessage; }

}
