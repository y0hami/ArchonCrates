package com.HamiStudios.ArchonCrates.API.Objects;

import com.HamiStudios.ArchonCrates.API.Enums.Files;
import com.HamiStudios.ArchonCrates.API.Exceptions.InvalidSoundValue;
import com.HamiStudios.ArchonCrates.API.Exceptions.NoValueException;
import com.HamiStudios.ArchonCrates.API.Libs.Find;
import com.HamiStudios.ArchonCrates.API.Libs.GetSound;
import com.HamiStudios.ArchonCrates.Files.Crates;
import org.bukkit.Sound;

import java.util.ArrayList;

public class Crate {

	// Creates variables for all values for the crate
	private String ID;
	private String title;
	private int blockID;
	private int blockData;
	private Sound openSound;
	private Sound scrollSound;
	private Sound winSound;
	private boolean displayEffects;
	private boolean displayFireworks;
	private boolean broadcastWin;
	private boolean sendPlayerMessage;
	private int scrollDuration;
	private int showcaseDuration;
	private boolean displayColouredGlass;
	private ArrayList<Prize> prizes;
	private ArrayList<Key> keys;
	private String playerMessage;
	private String broadcastMessage;
	private String invalidKeyMessage;
	
	private Crates crateFile;
	private boolean crateIsValid = true;
	
	
	// Crate Object Constructor
	public Crate(String ID) {
		this.ID = ID;
		
		this.crateFile = new Crates();
		
		try {
			this.title = (String) this.crateFile.get("Crates." + ID + ".title");
			this.blockID = (int) this.crateFile.get("Crates." + ID + ".block.ID");
			this.blockData = (int) this.crateFile.get("Crates." + ID + ".block.data");
			this.openSound = GetSound.get((String) this.crateFile.get("Crates." + ID + ".sounds.open"), ID, Files.CRATES);
			this.scrollSound = GetSound.get((String) this.crateFile.get("Crates." + ID + ".sounds.scroll"), ID, Files.CRATES);
			this.winSound = GetSound.get((String) this.crateFile.get("Crates." + ID + ".sounds.win"), ID, Files.CRATES);
			this.displayFireworks = (boolean) this.crateFile.get("Crates." + ID + ".win.firework");
			this.broadcastWin = (boolean) this.crateFile.get("Crates." + ID + ".win.broadcast");
			this.sendPlayerMessage = (boolean) this.crateFile.get("Crates." + ID + ".win.messagePlayer");
			this.scrollDuration = (int) this.crateFile.get("Crates." + ID + ".config.scrollDuration");
			this.showcaseDuration = (int) this.crateFile.get("Crates." + ID + ".config.showcaseDuration");
			this.displayColouredGlass = (boolean) this.crateFile.get("Crates." + ID + ".config.colouredGlass");
			this.displayEffects = (boolean) this.crateFile.get("Crates." + ID + ".config.crateEffects");

			this.playerMessage = (String) this.crateFile.get("Crates." + ID + ".messages.player");
			this.broadcastMessage = (String) this.crateFile.get("Crates." + ID + ".messages.broadcast");
			this.invalidKeyMessage = (String) this.crateFile.get("Crates." + ID + ".messages.wrongKey");

			this.prizes = new ArrayList<>();
			
			/*
			 * Get the list of prize IDs and go through each and create a Prize object, if it is valid add
			 * it to the array else set the crate to not valid
			*/
			for(String prizeID : this.crateFile.getFileConfiguration().getStringList("Crates." + ID + ".prizes")) {
				Prize prize = Find.prize(prizeID);
				if(prize.valid()) { this.prizes.add(prize); }
				else {
					this.crateIsValid = false;
					break;
				}
			}

			this.keys = new ArrayList<>();

			/*
			 * Get the list of prize IDs and go through each and create a Prize object, if it is valid add
			 * it to the array else set the crate to not valid
			*/
			for(String keyID : this.crateFile.getFileConfiguration().getStringList("Crates." + ID + ".keys")) {
				Key key = Find.key(keyID);
				if(key.valid()) { this.keys.add(key); }
				else {
					this.crateIsValid = false;
					break;
				}
			}
		} catch (NoValueException|InvalidSoundValue e) {
			this.crateIsValid = false;
		}
	}
	
	
	// Check if the crate is valid and not missing any fields in the configuration files
	public boolean valid() {
		return this.crateIsValid;
	}


	
	
	// Getters & Setters
	
	
	// Get the crate ID
	public String getID() {
		return this.ID;
	}
	
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

	// Should display effects around the crate
	public boolean displayEffects() {
		return this.displayEffects;
	}

	// Get an array of Prize objects
	public ArrayList<Prize> getPrizes() { return this.prizes; }

	// Get an array of Key objects
	public ArrayList<Key> getKeys() { return this.keys; }

	// Get the player message
	public String getPlayerMessage() { return this.playerMessage; }

	// Get the broadcast message
	public String getBroadcastMessage() { return this.broadcastMessage; }

	// Get the invalid key message
	public String getInvalidKeyMessage() { return this.invalidKeyMessage; }
	
}
