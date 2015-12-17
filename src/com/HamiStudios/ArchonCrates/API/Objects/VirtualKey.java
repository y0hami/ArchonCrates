package com.HamiStudios.ArchonCrates.API.Objects;

import java.util.ArrayList;

import com.HamiStudios.ArchonCrates.Files.FileHandler;
import com.HamiStudios.ArchonCrates.Util.FileType;

public class VirtualKey {

	private String vKeyType;
	private String displayName;
	private int itemId;
	private int itemData;
	private boolean glow;
	private String winMessage;
	private String playerMessage;
	private ArrayList<String> rawLoot;
	private ArrayList<Prize> loot;
	
	public VirtualKey(String vKeyType) {
		this.vKeyType = vKeyType;
		this.displayName = FileHandler.getFile(FileType.VIRTUAL_KEYS).getString("Virtual Keys." + this.vKeyType + ".name");
		this.itemId = FileHandler.getFile(FileType.VIRTUAL_KEYS).getInt("Virtual Keys." + this.vKeyType + ".itemID");
		this.itemData = FileHandler.getFile(FileType.VIRTUAL_KEYS).getInt("Virtual Keys." + this.vKeyType + ".itemData");
		this.glow = FileHandler.getFile(FileType.VIRTUAL_KEYS).getBoolean("Virtual Keys." + this.vKeyType + ".glow");
		this.winMessage = FileHandler.getFile(FileType.VIRTUAL_KEYS).getString("Virtual Keys." + this.vKeyType + ".winMessage");
		this.playerMessage = FileHandler.getFile(FileType.VIRTUAL_KEYS).getString("Virtual Keys." + this.vKeyType + ".playerMessage");
		
		this.rawLoot = new ArrayList<>();
		this.loot = new ArrayList<>();
		for(String s : FileHandler.getFile(FileType.VIRTUAL_KEYS).getStringList("Virtual Keys." + this.vKeyType + ".loot")) {
			rawLoot.add(s);
			this.loot.add(new Prize(s));
		}
	}

	
	public String getVKeyType() {
		return this.vKeyType;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
	
	public int getItemId() {
		return this.itemId;
	}
	
	public int getItemData() {
		return this.itemData;
	}
	
	public boolean glow() {
		return glow;
	}
	
	public String getWinMessage() {
		return this.winMessage;
	}
	
	public String getPlayerMessage() {
		return this.playerMessage;
	}
	
	public ArrayList<String> getRawLoot() {
		return this.rawLoot;
	}
	
	public ArrayList<Prize> getLoot() {
		return this.loot;
	}
	
}
