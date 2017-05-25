package com.HamiStudios.ArchonCrates.API.Objects;

import java.util.ArrayList;
import java.util.HashMap;

public class VCLayoutFile {

	private HashMap<String, Integer> keys;
	private int rows;
	private ArrayList<VirtualCrateMenuItem> layout;


	public VCLayoutFile(HashMap<String, Integer> keys, int rows, ArrayList<VirtualCrateMenuItem> layout) {
		this.keys = keys;
		this.rows = rows;
		this.layout = layout;
	}


	public HashMap<String, Integer> getKeys() { return this.keys; }

	public ArrayList<VirtualCrateMenuItem> getLayout() { return this.layout; }

	public int getRows() { return this.rows; }

}
