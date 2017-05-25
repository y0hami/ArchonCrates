package com.HamiStudios.ArchonCrates.API.Objects;

public class VirtualCrateMenuItem {

	private int slot;
	private int itemID;
	private int itemData;
	private String name;


	public VirtualCrateMenuItem(int slot, int itemID, int itemData, String name) {
		this.slot = slot;
		this.itemID = itemID;
		this.itemData = itemData;
		this.name = name;
	}

	public int getSlot() { return this.slot; }

	public int getItemID() { return this.itemID; }

	public int getItemData() { return this.itemData; }

	public String getName() { return this.name; }

}
