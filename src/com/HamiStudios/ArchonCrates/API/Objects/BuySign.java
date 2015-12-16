package com.HamiStudios.ArchonCrates.API.Objects;


import org.bukkit.Location;

import com.HamiStudios.ArchonCrates.Files.FileHandler;
import com.HamiStudios.ArchonCrates.Util.FileType;

public class BuySign {

	private String line1;
	private String line2;
	private String line3;
	private String line4;
	
	
	private double x;
	private double y;
	private double z;
	private String worldName;
	
	private int amount;
	private double price;
	private String keyType;
	private String vkeyType;
	private boolean virtual;
	
	
	public BuySign() {
		com.HamiStudios.ArchonCrates.Files.BuySign file = new com.HamiStudios.ArchonCrates.Files.BuySign();
		this.line1 = file.getFile().getString("Buy Sign.Line 1");
		this.line2 = file.getFile().getString("Buy Sign.Line 2");
		this.line3 = file.getFile().getString("Buy Sign.Line 3");
		this.line4 = file.getFile().getString("Buy Sign.Line 4");
	}
	
	public BuySign(String signId) {
		com.HamiStudios.ArchonCrates.Files.BuySign file = new com.HamiStudios.ArchonCrates.Files.BuySign();
		this.line1 = file.getFile().getString("Buy Sign.Line 1");
		this.line2 = file.getFile().getString("Buy Sign.Line 2");
		this.line3 = file.getFile().getString("Buy Sign.Line 3");
		this.line4 = file.getFile().getString("Buy Sign.Line 4");
		
		this.amount = FileHandler.getFile(FileType.LOCATIONS).getInt("signs." + signId + ".amount");
		this.price = FileHandler.getFile(FileType.LOCATIONS).getDouble("signs." + signId + ".price");
		this.vkeyType = FileHandler.getFile(FileType.LOCATIONS).getString("signs." + signId + ".keyType");
		this.keyType = FileHandler.getFile(FileType.LOCATIONS).getString("signs." + signId + ".keyType");
		this.x = FileHandler.getFile(FileType.LOCATIONS).getDouble("signs." + signId + ".x");
		this.y = FileHandler.getFile(FileType.LOCATIONS).getDouble("signs." + signId + ".y");
		this.z = FileHandler.getFile(FileType.LOCATIONS).getDouble("signs." + signId + ".z");
		this.worldName = FileHandler.getFile(FileType.LOCATIONS).getString("signs." + signId + ".world");
		this.virtual = FileHandler.getFile(FileType.LOCATIONS).getBoolean("signs." + signId + ".virtual");		
		
	}
	
	public String getLine1() {
		return this.line1;
	}
	public String getLine2() {
		return this.line2;
	}
	public String getLine3() {
		return this.line3;
	}
	public String getLine4() {
		return this.line4;
	}
	
	
	public int getAmount() {
		return this.amount;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public String getKeyType() {
		return this.keyType;
	}
	
	public String getVKeyType() {
		return this.vkeyType;
	}
	
	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public double getZ() {
		return this.z;
	}
	
	public String getWorldName() {
		return this.worldName;
	}
	
	public boolean isVirtual() {
		return this.virtual;
	}
	

	public void createNew(Location signLocation, int amount, double price, String keyType, boolean virtualKey) {
		
		int id = FileHandler.getFile(FileType.LOCATIONS).getInt("sign id");
		
		FileHandler.getFile(FileType.LOCATIONS).set("signs." + id + ".x", signLocation.getX());
		FileHandler.getFile(FileType.LOCATIONS).set("signs." + id + ".y", signLocation.getY());
		FileHandler.getFile(FileType.LOCATIONS).set("signs." + id + ".z", signLocation.getZ());
		FileHandler.getFile(FileType.LOCATIONS).set("signs." + id + ".world", signLocation.getWorld().getName());
		FileHandler.getFile(FileType.LOCATIONS).set("signs." + id + ".virtual", virtualKey);
		FileHandler.getFile(FileType.LOCATIONS).set("signs." + id + ".keyType", keyType);
		FileHandler.getFile(FileType.LOCATIONS).set("signs." + id + ".price", price);
		FileHandler.getFile(FileType.LOCATIONS).set("signs." + id + ".amount", amount);
		
		FileHandler.getFile(FileType.LOCATIONS).set("sign id", id+1);
		FileHandler.save(FileType.LOCATIONS);
	
	}
	
	public void remove(String signId) {
		FileHandler.getFile(FileType.LOCATIONS).set("signs." + signId, null);
		FileHandler.save(FileType.LOCATIONS);
	}
	
	public boolean isSign(Location signLocation) {
		if(!FileHandler.getFile(FileType.LOCATIONS).getString("signs").equalsIgnoreCase("[]") && !FileHandler.getFile(FileType.LOCATIONS).getString("signs").equalsIgnoreCase("{}")) {
			for(String s : FileHandler.getFile(FileType.LOCATIONS).getConfigurationSection("signs").getKeys(false)) {		
				if(FileHandler.getFile(FileType.LOCATIONS).getDouble("signs." + s + ".x") == signLocation.getX()) {
					if(FileHandler.getFile(FileType.LOCATIONS).getDouble("signs." + s + ".y") == signLocation.getY()) {
						if(FileHandler.getFile(FileType.LOCATIONS).getDouble("signs." + s + ".z") == signLocation.getZ()) {
							if(FileHandler.getFile(FileType.LOCATIONS).getString("signs." + s + ".world").equals(signLocation.getWorld().getName())) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public String getSignId(Location signLocation) {
		String id = null;
		for(String s : FileHandler.getFile(FileType.LOCATIONS).getConfigurationSection("signs").getKeys(false)) {			
			if(FileHandler.getFile(FileType.LOCATIONS).getDouble("signs." + s + ".x") == signLocation.getX()) {
				if(FileHandler.getFile(FileType.LOCATIONS).getDouble("signs." + s + ".y") == signLocation.getY()) {
					if(FileHandler.getFile(FileType.LOCATIONS).getDouble("signs." + s + ".z") == signLocation.getZ()) {
						if(FileHandler.getFile(FileType.LOCATIONS).getString("signs." + s + ".world").equals(signLocation.getWorld().getName())) {
							id = s;
							break;
						}
					}
				}
			}
		}
		return id;
	}
	
}
