package com.HamiStudios.ArchonCrates.Events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.HamiStudios.ArchonCrates.Main;
import com.HamiStudios.ArchonCrates.API.Objects.BuySign;
import com.HamiStudios.ArchonCrates.Util.ACPermission;
import com.HamiStudios.ArchonCrates.Util.LanguageType;

public class SignBreak implements Listener {

	private Main main;
	public SignBreak(Main main) {
		this.main = main;
		this.main.getServer().getPluginManager().registerEvents(this, this.main);
	}
	
	@EventHandler
	public void onSignBreak(BlockBreakEvent event) {
		if(event.getBlock().getType() != null) {
			if(event.getBlock().getType().equals(Material.SIGN) || event.getBlock().getType().equals(Material.SIGN_POST) || event.getBlock().getType().equals(Material.WALL_SIGN)) {
				BuySign buySign = new BuySign();
				if(buySign.isSign(event.getBlock().getLocation())) {
					if(event.getPlayer().hasPermission(ACPermission.SIGN_BREAK.toString())) {
						if(event.getPlayer().isSneaking()) {
							if(event.getPlayer().hasPermission("")) {
								String signId = buySign.getSignId(event.getBlock().getLocation());
								buySign.remove(signId);
								event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.SIGN_REMOVED.toString(true));
								return;
							}
							else{
								event.setCancelled(true);
								event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.NO_PERMISSION.toString(true));
								return;
							}
						}
						else{
							event.setCancelled(true);
							event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.SIGN_CANT_BREAK.toString(true));
							return;
						}
					}
					else{
						event.setCancelled(true);
						event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.NO_PERMISSION.toString(true));
						return;
					}
				}
			}
		}
	}
	
}
