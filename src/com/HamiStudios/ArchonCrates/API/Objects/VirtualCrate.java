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

	/**
	 * Class constructor
	 */
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

	/**
	 * Check if the VirtualCrate is valid.
	 *
	 * @return true if it is valid and false if not.
	 */
	public boolean valid() {
		return this.crateIsValid;
	}



	// Getters & Setters


	// Get the crate Title

	/**
	 * Get the virtual crate title.
	 *
	 * @return the virtual crate title.
	 */
	public String getTitle() {
		return this.title;
	}

	// Get the crate Block ID

	/**
	 * Get the virtual crate block ID.
	 *
	 * @return the virtual crate block ID.
	 */
	public int getBlockID() {
		return this.blockID;
	}

	// Get the crate Block Data

	/**
	 * Get the virtual crate block data value.
	 *
	 * @return the virtual crate block data value.
	 */
	public int getBlockData() {
		return this.blockData;
	}

	// Get the crate Open Sound

	/**
	 * Get the virtual crate open sound.
	 *
	 * @return the virtual crate open sound.
	 */
	public Sound getOpenSound() {
		return this.openSound;
	}

	// Get the crate Scroll Sound

	/**
	 * Get the virtual crate scroll sound.
	 *
	 * @return the virtual crate scroll sound.
	 */
	public Sound getScrollSound() {
		return this.scrollSound;
	}

	// Get the crate Win Sound

	/**
	 * Get the virtual crate win sound.
	 *
	 * @return the virtual crate win sound.
	 */
	public Sound getWinSound() {
		return this.winSound;
	}

	// Should the crate display effects around the player

	/**
	 * Should the virtual crate display effects when a player is using it.
	 *
	 * @return true if it should and false if not.
	 */
	public boolean displayEffects() {
		return this.displayCrateEffects;
	}

	// Should a firework go off when a player wins

	/**
	 * Should a firework be set off when a player wins.
	 *
	 * @return true if it should and false if not.
	 */
	public boolean firework() {
		return this.displayFireworks;
	}

	// Should the crate allow prizes to broadcast

	/**
	 * Should the prize won be broadcasted when a player wins.
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
	 * Get the virtual crate scroll duration.
	 *
	 * @return the virtual crate scroll duration.
	 */
	public int getScrollDuration() {
		return this.scrollDuration;
	}

	// Get the crate Showcase Duration

	/**
	 * Get the virtual crate showcase duration.
	 *
	 * @return the virtual crate showcase duration.
	 */
	public int getShowcaseDuration() {
		return this.showcaseDuration;
	}

	// Should display coloured glass

	/**
	 * Should the virtual crate use coloured glass or black glass.
	 *
	 * @return true if it should use coloured and false if not.
	 */
	public boolean showColouredGlass() { return this.displayColouredGlass; }

	// Get an array of Key objects

	/**
	 * Get all the keys in the virtual crate menu.
	 *
	 * @return a HashMap containing all the keys in the crate and the prizes a player can win from using the key.
	 */
	public HashMap<Key, ArrayList<Prize>> getKeys() { return this.keys; }

	// Get the broadcast message

	/**
	 * Get the broadcast message.
	 *
	 * @return the virtual crate broadcast message.
	 */
	public String getBroadcastMessage() { return this.broadcastMessage; }

	// Get the player message

	/**
	 * Get the player message.
	 *
	 * @return the virtual crate player message.
	 */
	public String getPlayerMessage() { return this.playerMessage; }

}
