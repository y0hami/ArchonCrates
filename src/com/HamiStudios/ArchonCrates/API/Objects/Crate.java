package com.HamiStudios.ArchonCrates.API.Objects;

import com.HamiStudios.ArchonCrates.API.Enums.Files;
import com.HamiStudios.ArchonCrates.API.Exceptions.InvalidSoundValue;
import com.HamiStudios.ArchonCrates.API.Exceptions.NoValueException;
import com.HamiStudios.ArchonCrates.API.libs.Find;
import com.HamiStudios.ArchonCrates.API.libs.GetSound;
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
	private boolean displayPlayerEffects;
	private boolean displayFireworks;
	private boolean broadcastWin;
	private boolean sendPlayerMessage;
	private int scrollDuration;
	private int showcaseDuration;
	private boolean displayColouredGlass;
	private ArrayList<Prize> prizes;
	
	private Crates crateFile;
	private boolean updateFilesOnSet = true;
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
			this.displayPlayerEffects = (boolean) this.crateFile.get("Crates." + ID + ".win.playerEffects");
			this.displayFireworks = (boolean) this.crateFile.get("Crates." + ID + ".win.firework");
			this.broadcastWin = (boolean) this.crateFile.get("Crates." + ID + ".win.broadcast");
			this.sendPlayerMessage = (boolean) this.crateFile.get("Crates." + ID + ".win.messagePlayer");
			this.scrollDuration = (int) this.crateFile.get("Crates." + ID + ".config.scrollDuration");
			this.showcaseDuration = (int) this.crateFile.get("Crates." + ID + ".config.showcaseDuration");
			this.displayColouredGlass = (boolean) this.crateFile.get("Crates." + ID + ".config.colouredGlass");
			
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
		} catch (NoValueException|InvalidSoundValue e) {
			this.crateIsValid = false;
		}
	}
	
	
	// Set the updateFilesOnSet value
	// if true when you use a setter it changes the file value to the value given
	public void updateFilesOnSet(boolean value) {
		this.updateFilesOnSet = value;
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
	
	// Should the crate display effects around the player
	public boolean displayEffects() {
		return this.displayPlayerEffects;
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
	
}
