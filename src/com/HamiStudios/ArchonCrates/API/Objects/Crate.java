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
	private int scrollSpeed;
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

	/**
	 * Class constructor.
	 *
	 * @param ID of the crate.
	 */
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
			this.scrollSpeed = (int) this.crateFile.get("Crates." + ID + ".config.scrollSpeed");
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

	/**
	 * Check if the crate is valid.
	 *
	 * @return true if its valid and false if not.
	 */
	public boolean valid() {
		return this.crateIsValid;
	}


	
	
	// Getters & Setters
	
	
	// Get the crate ID

	/**
	 * Get the ID of the crate.
	 *
	 * @return the crate ID.
	 */
	public String getID() {
		return this.ID;
	}
	
	// Get the crate Title

	/**
	 * Get the crate title.
	 *
	 * @return the crate title.
	 */
	public String getTitle() {
		return this.title;
	}
	
	// Get the crate Block ID

	/**
	 * Get the ID of the crate block.
	 *
	 * @return crate block ID.
	 */
	public int getBlockID() {
		return this.blockID;
	}
	
	// Get the crate Block Data

	/**
	 * Get the data value of the crate block.
	 *
	 * @return crate block data value;
	 */
	public int getBlockData() {
		return this.blockData;
	}
	
	// Get the crate Open Sound

	/**
	 * Get the crate opening sound.
	 *
	 * @return crate opening sound.
	 */
	public Sound getOpenSound() {
		return this.openSound;
	}
	
	// Get the crate Scroll Sound

	/**
	 * Get the crate scroll sound.
	 *
	 * @return crate scroll sound.
	 */
	public Sound getScrollSound() {
		return this.scrollSound;
	}
	
	// Get the crate Win Sound

	/**
	 * Get the crate win sound.
	 *
	 * @return crate win sound.
	 */
	public Sound getWinSound() {
		return this.winSound;
	}
	
	// Should a firework go off when a player wins

	/**
	 * Should a firework be set off when the player wins a prize.
	 *
	 * @return true if firework should be set off and false if not.
	 */
	public boolean firework() {
		return this.displayFireworks;
	}
	
	// Should the crate allow prizes to broadcast

	/**
	 * Should the players win be broadcasted.
	 *
	 * @return true if it should and false if not.
	 */
	public boolean broadcast() {
		return this.broadcastWin;
	}
	
	// Should the crate allow the player to receive messages

	/**
	 * Should the player be sent a message when they win.
	 *
	 * @return true if they should and false if not.
	 */
	public boolean playerMessage() {
		return this.sendPlayerMessage;
	}
	
	// Get the crate Scroll Duration

	/**
	 * Get the duration the scrolling of the prizes should last for.
	 *
	 * @return crate scroll duration.
	 */
	public int getScrollDuration() {
		return this.scrollDuration;
	}

	// Get the crate scroll speed

	/**
	 * Get the crates scroll speed
	 *
	 * @return crate scroll speed
	 */
	public int getScrollSpeed() { return this.scrollSpeed; }

	// Get the crate Showcase Duration

	/**
	 * Get the duration the crate should showcase the winning prize for.
	 *
	 * @return crate showcase duration.
	 */
	public int getShowcaseDuration() {
		return this.showcaseDuration;
	}

	// Should display coloured glass

	/**
	 * Should the crate be random coloured glass or standard black glass.
	 *
	 * @return true if it should be coloured and false if not.
	 */
	public boolean showColouredGlass() { return this.displayColouredGlass; }

	// Should display effects around the crate

	/**
	 * Should the crate block have an effect when a player is using it.
	 *
	 * @return true if it should have an effect and false if not.
	 */
	public boolean displayEffects() {
		return this.displayEffects;
	}

	// Get an array of Prize objects

	/**
	 * Get a list of all possible winnable prizes for the crate.
	 *
	 * @return an ArrayList of all winnable prizes.
	 */
	public ArrayList<Prize> getPrizes() { return this.prizes; }

	// Get an array of Key objects

	/**
	 * Get a list of all keys which can open this crate.
	 *
	 * @return an ArrayList of all keys which can open the crate.
	 */
	public ArrayList<Key> getKeys() { return this.keys; }

	// Get the player message

	/**
	 * Get the message which should be sent to the player.
	 *
	 * @return the player message.
	 */
	public String getPlayerMessage() { return this.playerMessage; }

	// Get the broadcast message

	/**
	 * Get the message which should be broadcasted when the player wins.
	 *
	 * @return the broadcast message.
	 */
	public String getBroadcastMessage() { return this.broadcastMessage; }

	// Get the invalid key message

	/**
	 * Get the message which will be sent to a player when they use an invalid key on the crate.
	 *
	 * @return invalid key message.
	 */
	public String getInvalidKeyMessage() { return this.invalidKeyMessage; }
	
}
