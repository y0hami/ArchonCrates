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
	@SuppressWarnings("unchecked")
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
	public void updateFilesOnSet(boolean value) {
		this.updateFilesOnSet = value;
	}

	// Check if the key is valid and not missing any fields in the configuration files
	public boolean valid() {
		return this.keyIsValid;
	}


	// Getters & Setters


	// Get the key ID
	public String getID() {
		return this.ID;
	}

	// Get the key Name
	public String getName() {
		return this.name;
	}

	// Get the key lore
	public ArrayList<String> getLore() {
		return this.lore;
	}

	// Get the key Item ID
	public int getItemID() {
		return this.itemID;
	}

	// Get the key Item Data
	public int getItemData() {
		return this.itemData;
	}

	// Should the key Glow
	public boolean glow() {
		return this.glow;
	}


	// Set the key Name
	public void setName(String value) {
		this.name = value;
		if(this.updateFilesOnSet) {
			this.keyFile.set("Keys." + getID() + ".name", getName());
			this.keyFile.save();
		}
	}

	// Set key lore
	public void setLore(ArrayList<String> value) {
		this.lore = value;
		if(this.updateFilesOnSet) {
			this.keyFile.set("Keys." + getID() + ".lore", getLore());
			this.keyFile.save();
		}
	}

	// Set the key Item ID
	public void setItemID(int value) {
		this.itemID = value;
		if(this.updateFilesOnSet) {
			this.keyFile.set("Keys." + getID() + ".item.ID", getItemID());
			this.keyFile.save();
		}
	}

	// Set the key Item Data
	public void setItemData(int value) {
		this.itemData = value;
		if(this.updateFilesOnSet) {
			this.keyFile.set("Keys." + getID() + ".item.data", getItemData());
			this.keyFile.save();
		}
	}

	// Set the key Glow
	public void setGlow(boolean value) {
		this.glow = value;
		if(this.updateFilesOnSet) {
			this.keyFile.set("Keys." + getID() + ".glow", glow());
			this.keyFile.save();
		}
	}

}
