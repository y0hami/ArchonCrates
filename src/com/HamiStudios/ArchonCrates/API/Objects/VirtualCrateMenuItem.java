package com.HamiStudios.ArchonCrates.API.Objects;

public class VirtualCrateMenuItem {

	private int slot;
	private int itemID;
	private int itemData;
	private String name;

	/**
	 * Class constructor
	 *
	 * @param slot of the item in the menu.
	 * @param itemID of the item which will be displayed
	 * @param itemData of the item which will be displayed
	 * @param name of the item which will be displayed
	 */
	public VirtualCrateMenuItem(int slot, int itemID, int itemData, String name) {
		this.slot = slot;
		this.itemID = itemID;
		this.itemData = itemData;
		this.name = name;
	}

	/**
	 * Get the slot of the item in the menu.
	 *
	 * @return the item slot.
	 */
	public int getSlot() { return this.slot; }

	/**
	 * Get the item ID.
	 *
	 * @return the item ID.
	 */
	public int getItemID() { return this.itemID; }

	/**
	 * Get the item data value.
	 *
	 * @return the item data value.
	 */
	public int getItemData() { return this.itemData; }

	/**
	 * Get the item name.
	 *
	 * @return the item name.
	 */
	public String getName() { return this.name; }

}
