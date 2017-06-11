package com.HamiStudios.ArchonCrates.API.Objects;

import java.util.ArrayList;
import java.util.HashMap;

public class VCLayoutFile {

	private HashMap<String, Integer> keys;
	private int rows;
	private ArrayList<VirtualCrateMenuItem> layout;


	/**
	 * Class constructor
	 *
	 * @param keys of which should be added to the menu and their position in the menu.
	 * @param rows count of the menu.
	 * @param layout ArrayList of all items in the menu apart from keys.
	 */
	public VCLayoutFile(HashMap<String, Integer> keys, int rows, ArrayList<VirtualCrateMenuItem> layout) {
		this.keys = keys;
		this.rows = rows;
		this.layout = layout;
	}

	/**
	 * Get all the keys in the menu.
	 *
	 * @return an HashMap of all keys in the menu and their position in the menu.
	 */
	public HashMap<String, Integer> getKeys() { return this.keys; }

	/**
	 * Get the menu layout.
	 *
	 * @return an ArrayList of VirtualCrateMenuItem's
	 */
	public ArrayList<VirtualCrateMenuItem> getLayout() { return this.layout; }

	/**
	 * Get the menu row count.
	 *
	 * @return menu row count.
	 */
	public int getRows() { return this.rows; }

}
