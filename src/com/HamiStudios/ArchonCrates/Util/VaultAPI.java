package com.HamiStudios.ArchonCrates.Util;

import java.util.ArrayList;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.HamiStudios.ArchonCrates.API.Objects.Prize;

public class VaultAPI {

	private static Permission permission = null;
	private static Economy economy = null;
	
	// RANK API
	public static boolean hasRank(Player player, String rankName) {
		setUpPermissions();
		return permission.playerInGroup(player, rankName);
	}
	
	public static String getRank(Player player) {
		setUpPermissions();
		return permission.getPrimaryGroup(player);
	}
	
	public static boolean useSubCommands(Prize prize, Player player) {
		setUpPermissions();
		if(prize.useSubCommands()) {
			ArrayList<String> ranksToSub = new ArrayList<>();
			for(String s : prize.getRanksToSub()) ranksToSub.add(s);
			
			for(String s : ranksToSub) {
				if(hasRank(player, s)) {
					return true;
				}
			}
 		}
		return false;
	}
	
	
	// ECONOMY API
	@SuppressWarnings("deprecation")
	public static double getPlayerBalance(Player player) {
		setupEconomy();
		return economy.getBalance(player.getName());
	}
	
	@SuppressWarnings("deprecation")
	public static void giveMoney(Player player, double amount) {
		setupEconomy();
		economy.depositPlayer(player.getName(), amount);
	}
	
	@SuppressWarnings("deprecation")
	public static void takeMoney(Player player, double amount) {
		setupEconomy();
		economy.withdrawPlayer(player.getName(), amount);
	}
	
	@SuppressWarnings("deprecation")
	public static boolean hasEnough(Player player, double amount) {
		setupEconomy();
		if(economy.getBalance(player.getName()) >= amount) return true;
		return false;
	}
	

	// SET-UP
	private static boolean setUpPermissions() {
        RegisteredServiceProvider<Permission> rsp = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);
        if(rsp != null) permission = rsp.getProvider();
        return (permission != null);
	}
	
	private static boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) economy = economyProvider.getProvider();
        return (economy != null);
    }
	
	
	// ENABLED
	public static boolean isPermissionsEnabled() {
		setUpPermissions();
		if(permission == null) return false;
		return true;
	}
	
	public static boolean isEconomyEnabled() {
		setupEconomy();
		if(economy == null) return false;
		return true;
	}
	
}
