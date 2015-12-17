package com.HamiStudios.ArchonCrates.Events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.HamiStudios.ArchonCrates.Main;
import com.HamiStudios.ArchonCrates.API.Objects.BuySign;
import com.HamiStudios.ArchonCrates.API.Objects.Key;
import com.HamiStudios.ArchonCrates.Util.ACPermission;
import com.HamiStudios.ArchonCrates.Util.LanguageType;
import com.HamiStudios.ArchonCrates.Util.PlayerData;
import com.HamiStudios.ArchonCrates.Util.VaultAPI;

public class SignClick implements Listener {
	
	private Main main;
	public SignClick(Main main) {
		this.main = main;
		this.main.getServer().getPluginManager().registerEvents(this, this.main);
	}
	
	@EventHandler
	public void onSignClick(PlayerInteractEvent event) {
		
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(event.getClickedBlock() != null && !event.getClickedBlock().getType().equals(Material.AIR)) {
				if(event.getClickedBlock().getType().equals(Material.SIGN) 
						|| event.getClickedBlock().getType().equals(Material.SIGN_POST) 
						|| event.getClickedBlock().getType().equals(Material.WALL_SIGN)) {
					
					Sign sign = (Sign) event.getClickedBlock().getState();
					BuySign buySign = new BuySign();
					if(sign.getLine(0).equals(ChatColor.translateAlternateColorCodes('&', buySign.getLine1()))) {
						if(buySign.isSign(sign.getLocation())) {
							BuySign bsign = new BuySign(buySign.getSignId(sign.getLocation()));
							if(VaultAPI.isEconomyEnabled()) {
								
								if(!event.getPlayer().hasPermission(ACPermission.SIGN_USE_BUY.toString())) {
									event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.NO_PERMISSION.toString(true));
									return;
								}
								
								if(VaultAPI.hasEnough(event.getPlayer(), bsign.getPrice())) {
									
									VaultAPI.takeMoney(event.getPlayer(), bsign.getPrice());
									if(bsign.isVirtual()) {
										PlayerData.addVKey(event.getPlayer().getUniqueId().toString(), bsign.getVKeyType(), bsign.getAmount());
										event.getPlayer().sendMessage(LanguageType.SIGN_BOUGHT_KEY.toString(true).replaceAll("<price>", bsign.getPrice()+""));
										return;
									}
									else{
										event.getPlayer().getInventory().addItem(new Key(bsign.getKeyType()).getItem());
										event.getPlayer().updateInventory();
										event.getPlayer().sendMessage(LanguageType.SIGN_BOUGHT_KEY.toString(true).replaceAll("<price>", bsign.getPrice()+""));
										return;
									}
									
								}
								else{
									event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.SIGN_NOT_ENOUGH_MONEY.toString(true));
									return;
								}
							}
						}
					}
					
				}
			}
		}
		
	}

}
