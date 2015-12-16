package com.HamiStudios.ArchonCrates.API.Objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.HamiStudios.ArchonCrates.Files.FileHandler;
import com.HamiStudios.ArchonCrates.Util.FileType;

public class Prize {

	private String prizeID;
	private int itemID;
	private int itemData;
	private int stackAmount;
	private String displayName;
	private boolean broadcast;
	private boolean playerMessage;
	private double chance;
	private String prizeName;
	private boolean glow;
	private boolean usePermission;
	private String permission;
	private String globalWinAmount;
	private String playerWinAmount;
	private List<String> commands;
	
	private boolean useSubCommands = false;
	private ArrayList<String> ranksToSub;
	private ArrayList<String> subCommands;
	
	@SuppressWarnings("unchecked")
	public Prize(String prizeName) {
		this.prizeID = prizeName;
		this.itemID = (int) FileHandler.get(FileType.CRATE_LOOT, "Crate Loot." + prizeName + ".Item ID");
		this.itemData = (int) FileHandler.get(FileType.CRATE_LOOT, "Crate Loot." + prizeName + ".Item Data");
		this.stackAmount = (int) FileHandler.get(FileType.CRATE_LOOT, "Crate Loot." + prizeName + ".StackAmount");
		this.displayName = (String) FileHandler.get(FileType.CRATE_LOOT, "Crate Loot." + prizeName + ".Name");
		this.broadcast = (boolean) FileHandler.get(FileType.CRATE_LOOT, "Crate Loot." + prizeName + ".Broadcast Win");
		this.playerMessage = (boolean) FileHandler.get(FileType.CRATE_LOOT, "Crate Loot." + prizeName + ".Player Message");
		this.chance = (double) FileHandler.get(FileType.CRATE_LOOT, "Crate Loot." + prizeName + ".Chance");
		this.prizeName = (String) FileHandler.get(FileType.CRATE_LOOT, "Crate Loot." + prizeName + ".Prize Name");
		this.glow = (boolean) FileHandler.get(FileType.CRATE_LOOT, "Crate Loot." + prizeName + ".Glow");
		this.usePermission = (boolean) FileHandler.get(FileType.CRATE_LOOT, "Crate Loot." + prizeName + ".Use Permission");
		this.permission = (String) FileHandler.get(FileType.CRATE_LOOT, "Crate Loot." + prizeName + ".Permission");
		this.globalWinAmount = (String) FileHandler.get(FileType.CRATE_LOOT, "Crate Loot." + prizeName + ".Global Win Amount");
		this.playerWinAmount = (String) FileHandler.get(FileType.CRATE_LOOT, "Crate Loot." + prizeName + ".Player Win Amount");
		this.commands = (List<String>) FileHandler.getList(FileType.CRATE_LOOT, "Crate Loot." + prizeName + ".Commands");
		
		if(FileHandler.getFile(FileType.CRATE_LOOT).contains("Crate Loot." + prizeName + ".Use Sub Commands")) this.useSubCommands = (boolean) FileHandler.get(FileType.CRATE_LOOT, "Crate Loot." + prizeName + ".Use Sub Commands");
		if(FileHandler.getFile(FileType.CRATE_LOOT).contains("Crate Loot." + prizeName + ".Ranks To Sub")) this.ranksToSub = (ArrayList<String>) FileHandler.getList(FileType.CRATE_LOOT, "Crate Loot." + prizeName + ".Ranks To Sub");
		if(FileHandler.getFile(FileType.CRATE_LOOT).contains("Crate Loot." + prizeName + ".Sub-Commands")) this.subCommands = (ArrayList<String>) FileHandler.getList(FileType.CRATE_LOOT, "Crate Loot." + prizeName + ".Sub-Commands");
	}
	
	
	public String getPrizeID() {
		return this.prizeID;
	}
	
	public int getItemID() {
		return this.itemID;
	}
	
	public int getItemData() {
		return this.itemData;
	}
	
	public int getStackAmount() {
		return this.stackAmount;
	}

	public String getDisplayName() {
		return this.displayName;
	}
	
	public boolean broadcastWin() {
		return this.broadcast;
	}
	
	public boolean playerMessage() {
		return this.playerMessage;
	}
	
	public double getChance() {
		return this.chance;
	}
	
	public String getPrizeName() {
		return this.prizeName;
	}
	
	public boolean glow() {
		return this.glow;
	}
	
	public boolean usePermission() {
		return this.usePermission;
	}
	
	public String getPermission() {
		return this.permission;
	}
	
	public String getGlobalWinAmount() {
		return this.globalWinAmount;
	}
	
	public String getPlayerWinAmount() {
		return this.playerWinAmount;
	}
	
	public List<String> getCommands() {
		return this.commands;
	}
	
	public boolean useSubCommands() {
		return this.useSubCommands;
	}
	
	public ArrayList<String> getRanksToSub() {
		return this.ranksToSub;
	}
	
	public ArrayList<String> getSubCommands() {
		return this.subCommands;
	}
	
	public ItemStack getItem() {
		@SuppressWarnings("deprecation")
		ItemStack prize = new ItemStack(Material.getMaterial(this.itemID), this.stackAmount);
		prize.setDurability((short)this.itemData);
		ItemMeta prizeMeta = prize.getItemMeta();
		prizeMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.displayName));
		Glow glow = new Glow(70);
		if(this.glow) prizeMeta.addEnchant(glow, 1, true);
		prize.setItemMeta(prizeMeta);
		return prize;
	}

}