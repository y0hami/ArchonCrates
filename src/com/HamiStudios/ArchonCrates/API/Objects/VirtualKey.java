package com.HamiStudios.ArchonCrates.API.Objects;

import java.util.ArrayList;

import com.HamiStudios.ArchonCrates.API.Objects.Exceptions.InvalidPrizeInput;
import com.HamiStudios.ArchonCrates.API.Objects.Exceptions.InvalidVirtualKeyInput;
import com.HamiStudios.ArchonCrates.Files.FileHandler;
import com.HamiStudios.ArchonCrates.Util.FileType;
import com.HamiStudios.ArchonCrates.Util.KeyFinder;

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
	
	public VirtualKey(String vKeyType) throws InvalidVirtualKeyInput {
		try {
			this.vKeyType = KeyFinder.getVKeyTypeToCase(vKeyType);
			if(this.vKeyType == null) {
				throw new InvalidVirtualKeyInput();
			}
			this.displayName = (String) FileHandler.getFile(FileType.VIRTUAL_KEYS).getString("Virtual Keys." + this.vKeyType + ".name");
			String testId = FileHandler.getFile(FileType.VIRTUAL_KEYS).getString("Virtual Keys." + this.vKeyType + ".itemID");
			try {
				this.itemId = Integer.parseInt(testId);
			} catch(NumberFormatException e) {
				throw new InvalidVirtualKeyInput();
			}
			String testData = FileHandler.getFile(FileType.VIRTUAL_KEYS).getString("Virtual Keys." + this.vKeyType + ".itemData");
			try {
				this.itemData = Integer.parseInt(testData);
			} catch(NumberFormatException e) {
				throw new InvalidVirtualKeyInput();
			}
			this.glow = (boolean) FileHandler.getFile(FileType.VIRTUAL_KEYS).getBoolean("Virtual Keys." + this.vKeyType + ".glow");
			this.winMessage = (String) FileHandler.getFile(FileType.VIRTUAL_KEYS).getString("Virtual Keys." + this.vKeyType + ".winMessage");
			this.playerMessage = (String) FileHandler.getFile(FileType.VIRTUAL_KEYS).getString("Virtual Keys." + this.vKeyType + ".playerMessage");
			this.rawLoot = new ArrayList<>();
			for(String s : FileHandler.getFile(FileType.VIRTUAL_KEYS).getStringList("Virtual Keys." + this.vKeyType + ".loot")) rawLoot.add(s);
		} catch(Exception e) {
			throw new InvalidVirtualKeyInput();
		}
		this.loot = new ArrayList<>();
		for(String s : this.rawLoot) {
			try {
				this.loot.add(new Prize(s));
			} catch (InvalidPrizeInput e) {
				e.log(s);
				e.writeToFile(s);
				continue;
			}
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
