package com.HamiStudios.ArchonCrates.API.Objects;

import java.util.ArrayList;

import com.HamiStudios.ArchonCrates.API.Exceptions.NoValueException;
import com.HamiStudios.ArchonCrates.Files.Prizes;

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
			this.globalWinAmount = (String) this.prizesFile.get("Prizes." + ID + ".winAmount.global");
			this.playerWinAmount = (String) this.prizesFile.get("Prizes." + ID + ".winAmount.player");
			this.commands = (ArrayList<String>) this.prizesFile.get("Prizes." + ID + ".commands");
		} catch (NoValueException e) {
			this.prizeIsValid = false;
		}
	}
	
	
	// Set the updateFilesOnSet value
	// if true when you use a setter it changes the file value to the value given
	public void updateFilesOnSet(boolean value) {
		this.updateFilesOnSet = value;
	}
	
	// Check if the prize is valid and not missing any fields in the configuration files
	public boolean valid() {
		return this.prizeIsValid;
	}
	
	
	// Getters & Setters
	
	
	// Get the prize ID
	public String getID() {
		return this.ID;
	}
	
	// Get the prize Name
	public String getName() {
		return this.name;
	}
	
	// Get the prize Item ID
	public int getItemID() {
		return this.itemID;
	}

	// Get the prize Item Data
	public int getItemData() {
		return this.itemData;
	}
	
	// Get the prize Item Stack Amount
	public int getItemStackSize() {
		return this.stackAmount;
	}
	
	// Get the prize Chance
	public double getChance() {
		return this.chance;
	}
	
	// Should the prize broadcast messages
	public boolean broadcast() {
		return this.broadcast;
	}
	
	// Should the prize Glow
	public boolean glow() {
		return this.glow;
	}
	
	// Should the prize Use Permissions
	public boolean usePermission() {
		return this.usePermission;
	}
	
	// Get the prize Permission
	public String getPermission() {
		return this.permission;
	}
	
	// Get the prize Global Win Amount
	public String getGlobalWinAmount() {
		return this.globalWinAmount;
	}
	
	// Get the prize Player Win Amount
	public String getPlayerWinAmount() {
		return this.playerWinAmount;
	}
	
	// Get prize Commands
	public ArrayList<String> getCommands() {
		return this.commands;
	}
	
	
	// Set the prize Name
	public void setName(String value) {
		this.name = value;
		if(this.updateFilesOnSet) {
			this.prizesFile.set("Prizes." + getID() + ".name", getName());
			this.prizesFile.save();
		}
	}
	
	// Set the prize Item ID
	public void setItemID(int value) {
		this.itemID = value;
		if(this.updateFilesOnSet) {
			this.prizesFile.set("Prizes." + getID() + ".item.ID", getItemID());
			this.prizesFile.save();
		}
	}

	// Set the prize Item Data
	public void setItemData(int value) {
		this.itemData = value;
		if(this.updateFilesOnSet) {
			this.prizesFile.set("Prizes." + getID() + ".item.data", getItemData());
			this.prizesFile.save();
		}
	}
	
	// Set the prize Item Stack Amount
	public void setItemStackSize(int value) {
		this.stackAmount = value;
		if(this.updateFilesOnSet) {
			this.prizesFile.set("Prizes." + getID() + ".item.stackSize", getItemStackSize());
			this.prizesFile.save();
		}
	}
	
	// Set the prize Chance
	public void setChance(double value) {
		this.chance = value;
		if(this.updateFilesOnSet) {
			this.prizesFile.set("Prizes." + getID() + ".chance", getChance());
			this.prizesFile.save();
		}
	}

	// Set the prize Broadcast
	public void setBroadcast(boolean value) {
		this.broadcast = value;
		if(this.updateFilesOnSet) {
			this.prizesFile.set("Prizes." + getID() + ".broadcast", broadcast());
			this.prizesFile.save();
		}
	}
	
	// Set the prize Glow
	public void setGlow(boolean value) {
		this.glow = value;
		if(this.updateFilesOnSet) {
			this.prizesFile.set("Prizes." + getID() + ".glow", glow());
			this.prizesFile.save();
		}
	}
	
	// Set the prize Use Permission
	public void setUsePermission(boolean value) {
		this.usePermission = value;
		if(this.updateFilesOnSet) {
			this.prizesFile.set("Prizes." + getID() + ".permission.use", usePermission());
			this.prizesFile.save();
		}
	}
	
	// Set the prize Permission
	public void setPermission(String value) {
		this.permission = value;
		if(this.updateFilesOnSet) {
			this.prizesFile.set("Prizes." + getID() + ".permission.value", getPermission());
			this.prizesFile.save();
		}
	}
	
	// Set the prize Global Win Amount
	public void setGlobalWinAmount(String value) {
		this.globalWinAmount = value;
		if(this.updateFilesOnSet) {
			this.prizesFile.set("Prizes." + getID() + ".winAmount.global", getGlobalWinAmount());
			this.prizesFile.save();
		}
	}
	
	// Set the prize Player Win Amount
	public void setPlayerWinAmount(String value) {
		this.playerWinAmount = value;
		if(this.updateFilesOnSet) {
			this.prizesFile.set("Prizes." + getID() + ".winAmount.player", getPlayerWinAmount());
			this.prizesFile.save();
		}
	}
	
	// Set prize Commands
	public void setCommands(ArrayList<String> value) {
		this.commands = value;
		if(this.updateFilesOnSet) {
			this.prizesFile.set("Prizes." + getID() + ".commands", getCommands());
			this.prizesFile.save();
		}
	}
	
}
