package com.HamiStudios.ArchonCrates.API.Objects;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.World;

import com.HamiStudios.ArchonCrates.API.Objects.Exceptions.InvalidCrateInput;
import com.HamiStudios.ArchonCrates.API.Objects.Exceptions.InvalidKeyInput;
import com.HamiStudios.ArchonCrates.Files.FileHandler;
import com.HamiStudios.ArchonCrates.Files.Locations;
import com.HamiStudios.ArchonCrates.Util.CrateFinder;
import com.HamiStudios.ArchonCrates.Util.FileType;

public class Crate {

	private String crateID;
	private String title;
	private int blockID;
	private int blockData;
	private String openSound;
	private String winSound;
	private String scrollSound;
	private boolean effects;
	private boolean firework;
	private int openDuration;
	private int prizeDisplayDuration;
	private boolean disableColouredGlass;
	private ArrayList<String> useableKeys;
	private String wrongKeyMessage;
	
	@SuppressWarnings("unchecked")
	public Crate(String crateID) throws InvalidCrateInput {
		try {
			this.crateID = crateID;
			this.title = (String) FileHandler.get(FileType.CRATES, "Crates." + crateID + ".title");
			this.blockID = (int) FileHandler.get(FileType.CRATES, "Crates." + crateID + ".blockID");
			this.blockData = (int) FileHandler.get(FileType.CRATES, "Crates." + crateID + ".blockData");
			this.openSound = (String) FileHandler.get(FileType.CRATES, "Crates." + crateID + ".openSound");
			this.winSound = (String) FileHandler.get(FileType.CRATES, "Crates." + crateID + ".winSound");
			this.scrollSound = (String) FileHandler.get(FileType.CRATES, "Crates." + crateID + ".scrollSound");
			this.effects = (boolean) FileHandler.get(FileType.CRATES, "Crates." + crateID + ".effects");
			this.firework = (boolean) FileHandler.get(FileType.CRATES, "Crates." + crateID + ".firework");
			this.openDuration = (int) FileHandler.get(FileType.CRATES, "Crates." + crateID + ".openDuration");
			this.prizeDisplayDuration = (int) FileHandler.get(FileType.CRATES, "Crates." + crateID + ".prizeDisplayDuration");
			this.disableColouredGlass = (boolean) FileHandler.get(FileType.CRATES, "Crates." + crateID + ".disableColouredGlass");
			this.useableKeys = (ArrayList<String>) FileHandler.get(FileType.CRATES, "Crates." + crateID + ".useableKeys");
			this.wrongKeyMessage = (String) FileHandler.get(FileType.CRATES, "Crates." + crateID + ".wrongKeyMessage");	
		} catch(Exception e) {
			throw new InvalidCrateInput();
		}
	}
	
	public String getCrateID() {
		return this.crateID;
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
	
	public boolean effects() {
		return this.effects;
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
			Key key = null;
			try {
				key = new Key(s);
			} catch (InvalidKeyInput e) {
				e.log(s);
				e.writeToFile(s);
			}
			if(key == null) continue;
			useable.add(key);
		}
		return useable;
	}
	
	public String getWrongKeyMessage() {
		return this.wrongKeyMessage;
	}
 	
	public void createNew(Location location) {
		Locations locations = new Locations();
		int crateId = locations.getFile().getInt("crate id");
		locations.getFile().set("crate id", crateId+1);
		locations.getFile().set("crates." + this.crateID + "." + crateId + ".x", location.getX());
		locations.getFile().set("crates." + this.crateID + "." + crateId + ".y", location.getY());
		locations.getFile().set("crates." + this.crateID + "." + crateId + ".z", location.getZ());
		locations.getFile().set("crates." + this.crateID + "." + crateId + ".world", location.getWorld().getName());
		locations.save();
	}
	
	public void remove(int blockId, double x, double y, double z, World world) {
		String crateId = CrateFinder.findCrateLocationId(blockId, x, y, z, world);	
		Locations locations = new Locations();
		locations.getFile().set("crates." + this.crateID + "." + crateId + ".x", null);
		locations.getFile().set("crates." + this.crateID + "." + crateId + ".y", null);
		locations.getFile().set("crates." + this.crateID + "." + crateId + ".z", null);
		locations.getFile().set("crates." + this.crateID + "." + crateId + ".world", null);
		locations.getFile().set("crates." + this.crateID + "." + crateId, null);
		locations.save();
	}
	
}
