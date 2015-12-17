package com.HamiStudios.ArchonCrates.API.Objects;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.World;

import com.HamiStudios.ArchonCrates.Files.FileHandler;
import com.HamiStudios.ArchonCrates.Files.Locations;
import com.HamiStudios.ArchonCrates.Util.CrateFinder;
import com.HamiStudios.ArchonCrates.Util.FileType;

public class VirtualCrate {

	private String title;
	private int blockID;
	private int blockData;
	private String openSound;
	private String winSound;
	private String scrollSound;
	private boolean firework;
	private int openDuration;
	private int prizeDisplayDuration;
	private boolean disableColouredGlass;
	private ArrayList<String> useableKeys;
	
	@SuppressWarnings("unchecked")
	public VirtualCrate() {
		this.title = (String) FileHandler.get(FileType.VIRTUAL_CRATES, "Virtual Crates.title");
		this.blockID = (int) FileHandler.get(FileType.VIRTUAL_CRATES, "Virtual Crates.blockID");
		this.blockData = (int) FileHandler.get(FileType.VIRTUAL_CRATES, "Virtual Crates.blockData");
		this.openSound = (String) FileHandler.get(FileType.VIRTUAL_CRATES, "Virtual Crates.openSound");
		this.winSound = (String) FileHandler.get(FileType.VIRTUAL_CRATES, "Virtual Crates.winSound");
		this.scrollSound = (String) FileHandler.get(FileType.VIRTUAL_CRATES, "Virtual Crates.scrollSound");
		this.firework = (boolean) FileHandler.get(FileType.VIRTUAL_CRATES, "Virtual Crates.firework");
		this.openDuration = (int) FileHandler.get(FileType.VIRTUAL_CRATES, "Virtual Crates.openDuration");
		this.prizeDisplayDuration = (int) FileHandler.get(FileType.VIRTUAL_CRATES, "Virtual Crates.prizeDisplayDuration");
		this.disableColouredGlass = (boolean) FileHandler.get(FileType.VIRTUAL_CRATES, "Virtual Crates.disableColouredGlass");
		this.useableKeys = (ArrayList<String>) FileHandler.get(FileType.VIRTUAL_CRATES, "Virtual Crates.useableKeys");
	}
	
	public String getTitle() {
		return this.title;
	}

	public int getBlockID() {
		return this.blockID;
	}
	
	public int getBlockData() {
		return this.blockData;
	}
	
	public String getOpenSound() {
		return this.openSound;
	}
	
	public String getWinSound() {
		return this.winSound;
	}
	
	public String getScrollSound() {
		return this.scrollSound;
	}
	
	public boolean firework() {
		return this.firework;
	}
	
	public int getOpenDuration() {
		return this.openDuration;
	}
	
	public int getPrizeDisplayDuration() {
		return this.prizeDisplayDuration;
	}
	
	public boolean disableColouredGlass() {
		return this.disableColouredGlass;
	}
	
	public ArrayList<String> getRawUseableKeys() {
		return this.useableKeys;
	}
	
	public ArrayList<Key> getUseableKeys() {
		ArrayList<Key> useable = new ArrayList<>();
		for(String s : this.useableKeys) {
			Key key = new Key(s);
			useable.add(key);
		}
		return useable;
	}
	
	public void createNew(Location location) {
		Locations locations = new Locations();
		int crateId = locations.getFile().getInt("virtual crate id");
		locations.getFile().set("virtual crate id", crateId+1);
		locations.getFile().set("virtual crates." + crateId + ".x", location.getX());
		locations.getFile().set("virtual crates." + crateId + ".y", location.getY());
		locations.getFile().set("virtual crates." + crateId + ".z", location.getZ());
		locations.getFile().set("virtual crates." + crateId + ".world", location.getWorld().getName());
		locations.save();
	}
	
	public void remove(int blockId, double x, double y, double z, World world) {
		String crateId = CrateFinder.findVCrateLocationId(blockId, x, y, z, world);
		Locations locations = new Locations();
		locations.getFile().set("virtual crates." + crateId + ".x", null);
		locations.getFile().set("virtual crates." + crateId + ".y", null);
		locations.getFile().set("virtual crates." + crateId + ".z", null);
		locations.getFile().set("virtual crates." + crateId + ".world", null);
		locations.getFile().set("virtual crates." + crateId, null);
		locations.save();
	}
	
}
