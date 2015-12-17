package com.HamiStudios.ArchonCrates.Events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import com.HamiStudios.ArchonCrates.Main;
import com.HamiStudios.ArchonCrates.API.Objects.BuySign;
import com.HamiStudios.ArchonCrates.Util.ACPermission;
import com.HamiStudios.ArchonCrates.Util.KeyFinder;
import com.HamiStudios.ArchonCrates.Util.LanguageType;

public class SignCreate implements Listener {
	
	private Main main;
	public SignCreate(Main main) {
		this.main = main;
		this.main.getServer().getPluginManager().registerEvents(this, this.main);
	}
	
	@EventHandler
	public void onSignCreate(SignChangeEvent event) {
		BuySign buySign = new BuySign();
		
		if(event.getLine(0).equalsIgnoreCase("acBuy")) {
		
			if(!event.getPlayer().hasPermission(ACPermission.SIGN_CREATE_NORAML.toString())) {
				return;
			}
			
			int amount;
			try{
				amount = Integer.parseInt(event.getLine(1));
			} catch(NumberFormatException e) {
				event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.SIGN_INVALID_AMOUNT.toString(true));
				return;
			}
			
			String keyType;
			if(KeyFinder.isKeyType(event.getLine(2))) {
				keyType = KeyFinder.getKeyTypeToCase(event.getLine(2));
			}
			else{
				event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.SIGN_INVALID_KEYTYPE.toString(true));
				return;
			}
			
			double price;
			try{
				price = Double.parseDouble(event.getLine(3));
			} catch(NumberFormatException e) {
				event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.SIGN_INVALID_PRICE.toString(true));
				return;
			}
			
			buySign.createNew(event.getBlock().getLocation(), amount, price, keyType, false);
			event.setLine(0, ChatColor.translateAlternateColorCodes('&', buySign.getLine1().replaceAll("<amount>", amount+"").replaceAll("<keyType>", keyType).replaceAll("<price>", price+"")));
			event.setLine(1, ChatColor.translateAlternateColorCodes('&', buySign.getLine2().replaceAll("<amount>", amount+"").replaceAll("<keyType>", keyType).replaceAll("<price>", price+"")));
			event.setLine(2, ChatColor.translateAlternateColorCodes('&', buySign.getLine3().replaceAll("<amount>", amount+"").replaceAll("<keyType>", keyType).replaceAll("<price>", price+"")));
			event.setLine(3, ChatColor.translateAlternateColorCodes('&', buySign.getLine4().replaceAll("<amount>", amount+"").replaceAll("<keyType>", keyType).replaceAll("<price>", price+"")));
			event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.SIGN_CREATED.toString(true));
			return;
			
		}
		if(event.getLine(0).equalsIgnoreCase("acBuyV")) {
		
			if(!event.getPlayer().hasPermission(ACPermission.SIGN_CREATE_VIRTUAL.toString())) {
				return;
			}
			
			int amount;
			try{
				amount = Integer.parseInt(event.getLine(1));
			} catch(NumberFormatException e) {
				event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.SIGN_INVALID_AMOUNT.toString(true));
				return;
			}
			
			String vkeyType;
			if(KeyFinder.isVKeyType(event.getLine(2))) {
				vkeyType = KeyFinder.getVKeyTypeToCase(event.getLine(2));
			}
			else{
				event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.SIGN_INVALID_KEYTYPE.toString(true));
				return;
			}
			
			double price;
			try{
				price = Double.parseDouble(event.getLine(3));
			} catch(NumberFormatException e) {
				event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.SIGN_INVALID_PRICE.toString(true));
				return;
			}
			
			buySign.createNew(event.getBlock().getLocation(), amount, price, vkeyType, true);
			event.setLine(0, ChatColor.translateAlternateColorCodes('&', buySign.getLine1().replaceAll("<amount>", amount+"").replaceAll("<keyType>", vkeyType).replaceAll("<price>", price+"")));
			event.setLine(1, ChatColor.translateAlternateColorCodes('&', buySign.getLine2().replaceAll("<amount>", amount+"").replaceAll("<keyType>", vkeyType).replaceAll("<price>", price+"")));
			event.setLine(2, ChatColor.translateAlternateColorCodes('&', buySign.getLine3().replaceAll("<amount>", amount+"").replaceAll("<keyType>", vkeyType).replaceAll("<price>", price+"")));
			event.setLine(3, ChatColor.translateAlternateColorCodes('&', buySign.getLine4().replaceAll("<amount>", amount+"").replaceAll("<keyType>", vkeyType).replaceAll("<price>", price+"")));
			event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.SIGN_CREATED.toString(true));
			return;
			
		}
	}

}
