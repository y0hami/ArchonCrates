package com.HamiStudios.ArchonCrates.API.Objects;

import com.HamiStudios.ArchonCrates.API.Exceptions.NoValueException;
import com.HamiStudios.ArchonCrates.Files.Keys;

import java.util.ArrayList;

public class Key {

	// Creates variables for all values for the key
	private String ID;
	private String name;
	private ArrayList<String> lore;
	private int itemID;
	private int itemData;
	private boolean glow;

	private Keys keyFile;
	private boolean updateFilesOnSet = true;
	private boolean keyIsValid = true;


	// Key Object Constructor

	/**
	 * Class constructor
	 *
	 * @param ID of the key.
	 */
	public Key(String ID) {
		this.ID = ID;

		this.keyFile = new Keys();

		try {
			this.name = (String) this.keyFile.get("Keys." + ID + ".name");
			this.lore = (ArrayList<String>) this.keyFile.get("Keys." + ID + ".lore");
			this.itemID = (int) this.keyFile.get("Keys." + ID + ".item.ID");
			this.itemData = (int) this.keyFile.get("Keys." + ID + ".item.data");
			this.glow = (boolean) this.keyFile.get("Keys." + ID + ".glow");
		} catch (NoValueException e) {
			this.keyIsValid = false;
		}
	}


	// Set the updateFilesOnSet value
	// if true when you use a setter it changes the file value to the value given

	/**
	 * When setting a value should the file be updated to that value.
	 *
	 * @param value of true to set the file values and false to not set.
	 */
	public void updateFilesOnSet(boolean value) {
		this.updateFilesOnSet = value;
	}

	// Check if the key is valid and not missing any fields in the configuration files

	/**
	 * Check if the key is valid.
	 *
	 * @return true if valid and false if not.
	 */
	public boolean valid() {
		return this.keyIsValid;
	}


	// Getters & Setters


	// Get the key ID

	/**
	 * Get the key ID.
	 *
	 * @return the key ID.
	 */
	public String getID() {
		return this.ID;
	}

	// Get the key Name

	/**
	 * Get the key name.
	 *
	 * @return the key name.
	 */
	public String getName() {
		return this.name;
	}

	// Get the key lore

	/**
	 * Get the key lore.
	 *
	 * @return the key lore.
	 */
	public ArrayList<String> getLore() {
		return this.lore;
	}

	// Get the key Item ID

	/**
	 * Get the key item ID.
	 *
	 * @return the kye item ID.
	 */
	public int getItemID() {
		return this.itemID;
	}

	// Get the key Item Data

	/**
	 * Get the key item data value.
	 *
	 * @return the key item data value.
	 */
	public int getItemData() {
		return this.itemData;
	}

	// Should the key Glow

	/**
	 * Should the key glow like an enchantment.
	 *
	 * @return true if it should and false if not.
	 */
	public boolean glow() {
		return this.glow;
	}


	// Set the key Name

	/**
	 * Set the key item name.
	 *
	 * @param value of the name.
	 */
	public void setName(String value) {
		this.name = value;
		if(this.updateFilesOnSet) {
			this.keyFile.set("Keys." + getID() + ".name", getName());
			this.keyFile.save();
		}
	}

	// Set key lore

	/**
	 * Set the key item lore.
	 *
	 * @param value of the lore.
	 */
	public void setLore(ArrayList<String> value) {
		this.lore = value;
		if(this.updateFilesOnSet) {
			this.keyFile.set("Keys." + getID() + ".lore", getLore());
			this.keyFile.save();
		}
	}

	// Set the key Item ID

	/**
	 * Set key item ID.
	 *
	 * @param value of the ID.
	 */
	public void setItemID(int value) {
		this.itemID = value;
		if(this.updateFilesOnSet) {
			this.keyFile.set("Keys." + getID() + ".item.ID", getItemID());
			this.keyFile.save();
		}
	}

	// Set the key Item Data

	/**
	 * Set the key item date.
	 *
	 * @param value of the item data.
	 */
	public void setItemData(int value) {
		this.itemData = value;
		if(this.updateFilesOnSet) {
			this.keyFile.set("Keys." + getID() + ".item.data", getItemData());
			this.keyFile.save();
		}
	}

	// Set the key Glow

	/**
	 * Set the key item to glow specified by the value.
	 *
	 * @param value of true if it should glow and false if not.
	 */
	public void setGlow(boolean value) {
		this.glow = value;
		if(this.updateFilesOnSet) {
			this.keyFile.set("Keys." + getID() + ".glow", glow());
			this.keyFile.save();
		}
	}

}
