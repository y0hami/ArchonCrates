package com.HamiStudios.ArchonCrates.API.Objects;

import com.HamiStudios.ArchonCrates.API.Exceptions.NoValueException;
import com.HamiStudios.ArchonCrates.Files.Prizes;

import java.util.ArrayList;

public class Prize {

	// Creates variables for all values for the prize
	private String ID;
	private String name;
	private int itemID;
	private int itemData;
	private int stackAmount;
	private double chance;
	private boolean broadcast;
	private boolean glow;
	private boolean usePermission;
	private String permission;
	private String globalWinAmount;
	private String playerWinAmount;
	private ArrayList<String> commands;
	
	private Prizes prizesFile;
	private boolean updateFilesOnSet = true;
	private boolean prizeIsValid = true;
	

	// Prize Object Constructor

	/**
	 * Class constructor
	 *
	 * @param ID of the prize.
	 */
	@SuppressWarnings("unchecked")
	public Prize(String ID) {
		this.ID = ID;
		
		this.prizesFile = new Prizes();
		
		try {
			this.name = (String) this.prizesFile.get("Prizes." + ID + ".name");
			this.itemID = (int) this.prizesFile.get("Prizes." + ID + ".item.ID");
			this.itemData = (int) this.prizesFile.get("Prizes." + ID + ".item.data");
			this.stackAmount = (int) this.prizesFile.get("Prizes." + ID + ".item.stackSize");
			this.chance = (double) this.prizesFile.get("Prizes." + ID + ".chance");
			this.broadcast = (boolean) this.prizesFile.get("Prizes." + ID + ".broadcast");
			this.glow = (boolean) this.prizesFile.get("Prizes." + ID + ".glow");
			this.usePermission = (boolean) this.prizesFile.get("Prizes." + ID + ".permission.use");
			this.permission = (String) this.prizesFile.get("Prizes." + ID + ".permission.value");
			this.globalWinAmount = (this.prizesFile.get("Prizes." + ID + ".winAmount.global") + "");
			this.playerWinAmount = (this.prizesFile.get("Prizes." + ID + ".winAmount.player") + "");
			this.commands = (ArrayList<String>) this.prizesFile.get("Prizes." + ID + ".commands");
		} catch (NoValueException e) {
			this.prizeIsValid = false;
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
	
	// Check if the prize is valid and not missing any fields in the configuration files

	/**
	 * Check if the prize is valid.
	 *
	 * @return true if it is valid and false if not.
	 */
	public boolean valid() {
		return this.prizeIsValid;
	}
	
	
	// Getters & Setters
	
	
	// Get the prize ID

	/**
	 * Get the prize ID.
	 *
	 * @return the prize ID.
	 */
	public String getID() {
		return this.ID;
	}
	
	// Get the prize Name

	/**
	 * Get the prize name.
	 *
	 * @return the prize name.
	 */
	public String getName() {
		return this.name;
	}
	
	// Get the prize Item ID

	/**
	 * Get the prize item ID.
	 *
	 * @return the prize item ID.
	 */
	public int getItemID() {
		return this.itemID;
	}

	// Get the prize Item Data

	/**
	 * Get the prize item data value.
	 *
	 * @return the prize item data value.
	 */
	public int getItemData() {
		return this.itemData;
	}
	
	// Get the prize Item Stack Amount

	/**
	 * Get the prize item stack size.
	 *
	 * @return the prize item stack size.
	 */
	public int getItemStackSize() {
		return this.stackAmount;
	}
	
	// Get the prize Chance

	/**
	 * Get the prize winning chance.
	 *
	 * @return the prize winning chance.
	 */
	public double getChance() {
		return this.chance;
	}
	
	// Should the prize broadcast messages

	/**
	 * Should the prize be braodcasted when won.
	 *
	 * @return true of it should be broadcasted and false if not.
	 */
	public boolean broadcast() {
		return this.broadcast;
	}
	
	// Should the prize Glow

	/**
	 * Should the prize glow.
	 *
	 * @return true of it should and false if not.
	 */
	public boolean glow() {
		return this.glow;
	}
	
	// Should the prize Use Permissions

	/**
	 * Should the prize require the player to have the prize permission for them to win the prize.
	 *
	 * @return true if it requires the permission and false if not.
	 */
	public boolean usePermission() {
		return this.usePermission;
	}
	
	// Get the prize Permission

	/**
	 * Get the prize permission.
	 *
	 * @return the prize permission.
	 */
	public String getPermission() {
		return this.permission;
	}
	
	// Get the prize Global Win Amount

	/**
	 * Get the global win amount value.
	 *
	 * @return the prize global win amount.
	 */
	public String getGlobalWinAmount() {
		return this.globalWinAmount;
	}
	
	// Get the prize Player Win Amount

	/**
	 * Get the player win amount value.
	 *
	 * @return the prize player win amount.
	 */
	public String getPlayerWinAmount() {
		return this.playerWinAmount;
	}
	
	// Get prize Commands

	/**
	 * Get all the commands which should be ran when the prize is won.
	 *
	 * @return an ArrayList of all prize commands.
	 */
	public ArrayList<String> getCommands() {
		return this.commands;
	}
	
	
	// Set the prize Name

	/**
	 * Set the prize name.
	 *
	 * @param value to set.
	 */
	public void setName(String value) {
		this.name = value;
		if(this.updateFilesOnSet) {
			this.prizesFile.set("Prizes." + getID() + ".name", getName());
			this.prizesFile.save();
		}
	}
	
	// Set the prize Item ID

	/**
	 * Set the prize item ID.
	 *
	 * @param value to set.
	 */
	public void setItemID(int value) {
		this.itemID = value;
		if(this.updateFilesOnSet) {
			this.prizesFile.set("Prizes." + getID() + ".item.ID", getItemID());
			this.prizesFile.save();
		}
	}

	// Set the prize Item Data

	/**
	 * Set the prize item data value.
	 *
	 * @param value to set.
	 */
	public void setItemData(int value) {
		this.itemData = value;
		if(this.updateFilesOnSet) {
			this.prizesFile.set("Prizes." + getID() + ".item.data", getItemData());
			this.prizesFile.save();
		}
	}
	
	// Set the prize Item Stack Amount

	/**
	 * Set the prize item stack size.
	 *
	 * @param value to set.
	 */
	public void setItemStackSize(int value) {
		this.stackAmount = value;
		if(this.updateFilesOnSet) {
			this.prizesFile.set("Prizes." + getID() + ".item.stackSize", getItemStackSize());
			this.prizesFile.save();
		}
	}
	
	// Set the prize Chance

	/**
	 * Set the prize chance value.
	 *
	 * @param value to set.
	 */
	public void setChance(double value) {
		this.chance = value;
		if(this.updateFilesOnSet) {
			this.prizesFile.set("Prizes." + getID() + ".chance", getChance());
			this.prizesFile.save();
		}
	}

	// Set the prize Broadcast

	/**
	 * Set the prize to broadcast specified by the value.
	 *
	 * @param value to set.
	 */
	public void setBroadcast(boolean value) {
		this.broadcast = value;
		if(this.updateFilesOnSet) {
			this.prizesFile.set("Prizes." + getID() + ".broadcast", broadcast());
			this.prizesFile.save();
		}
	}
	
	// Set the prize Glow

	/**
	 * Set the prize to glow specified by the value.
	 *
	 * @param value to set.
	 */
	public void setGlow(boolean value) {
		this.glow = value;
		if(this.updateFilesOnSet) {
			this.prizesFile.set("Prizes." + getID() + ".glow", glow());
			this.prizesFile.save();
		}
	}
	
	// Set the prize Use Permission

	/**
	 * Set the prize to use the prize permission specified by the value.
	 *
	 * @param value to set.
	 */
	public void setUsePermission(boolean value) {
		this.usePermission = value;
		if(this.updateFilesOnSet) {
			this.prizesFile.set("Prizes." + getID() + ".permission.use", usePermission());
			this.prizesFile.save();
		}
	}
	
	// Set the prize Permission

	/**
	 * Set the prize permissions.
	 *
	 * @param value to set.
	 */
	public void setPermission(String value) {
		this.permission = value;
		if(this.updateFilesOnSet) {
			this.prizesFile.set("Prizes." + getID() + ".permission.value", getPermission());
			this.prizesFile.save();
		}
	}
	
	// Set the prize Global Win Amount

	/**
	 * Set the prize global win amount.
	 *
	 * @param value to set.
	 */
	public void setGlobalWinAmount(String value) {
		this.globalWinAmount = value;
		if(this.updateFilesOnSet) {
			this.prizesFile.set("Prizes." + getID() + ".winAmount.global", getGlobalWinAmount());
			this.prizesFile.save();
		}
	}
	
	// Set the prize Player Win Amount

	/**
	 * Set the player win amount.
	 *
	 * @param value to set.
	 */
	public void setPlayerWinAmount(String value) {
		this.playerWinAmount = value;
		if(this.updateFilesOnSet) {
			this.prizesFile.set("Prizes." + getID() + ".winAmount.player", getPlayerWinAmount());
			this.prizesFile.save();
		}
	}
	
	// Set prize Commands

	/**
	 * Set the prize win commands.
	 *
	 * @param value to set.
	 */
	public void setCommands(ArrayList<String> value) {
		this.commands = value;
		if(this.updateFilesOnSet) {
			this.prizesFile.set("Prizes." + getID() + ".commands", getCommands());
			this.prizesFile.save();
		}
	}
	
}
