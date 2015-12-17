package com.HamiStudios.ArchonCrates.Events;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.HamiStudios.ArchonCrates.Main;
import com.HamiStudios.ArchonCrates.Util.ACPermission;
import com.HamiStudios.ArchonCrates.Util.LanguageType;
import com.HamiStudios.ArchonCrates.Util.VoucherUtil;

public class onCommand implements Listener {

	private Main main;
	public onCommand(Main main) {
		this.main = main;
		this.main.getServer().getPluginManager().registerEvents(this, this.main);
	}

	@EventHandler
	public void onCmd(PlayerCommandPreprocessEvent event) {
		
		ArrayList<String> args = new ArrayList<>();
		for(String s : event.getMessage().split(" ")) args.add(s);
		
		if(args.get(0).equalsIgnoreCase("/" + VoucherUtil.getCommand())) {
			event.setCancelled(true);
			if(event.getPlayer().hasPermission(ACPermission.COMMAND_VOUCHER_REDEEM.toString())) {
				
				if(args.size() > 1) {
					String voucher = "";
					for(String s : VoucherUtil.getVouchers(event.getPlayer().getUniqueId().toString())) {
						if(args.get(1).equalsIgnoreCase(s)) {
							voucher = s;
							break;
						}
					}
					if(!voucher.equalsIgnoreCase("")) {
						VoucherUtil.use(event.getPlayer().getUniqueId().toString(), voucher);
						event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_VOUCHER_REDEEMED.toString(true).replaceAll("<voucher>", voucher));
						return;
					}
					else{
						event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_VOUCHER_INVALID.toString(true));
						return;
					}
				}
				else{
					event.getPlayer().sendMessage("");
					event.getPlayer().sendMessage(ChatColor.GREEN + "" + ChatColor.UNDERLINE + "Your vouchers: ");
					event.getPlayer().sendMessage("");
					for(String s : VoucherUtil.getVouchers(event.getPlayer().getUniqueId().toString())) {
						event.getPlayer().sendMessage("   " + ChatColor.WHITE + s + ChatColor.DARK_GRAY + 
								"\n" + "   " + "  " + " - " + ChatColor.GRAY + "KeyType: "+ ChatColor.YELLOW + VoucherUtil.getkeyType(s) +
								ChatColor.DARK_GRAY + "\n" + "   " + "  " + " - " + ChatColor.GRAY + "Amount: "+ ChatColor.YELLOW + VoucherUtil.getAmount(s));
					}
					event.getPlayer().sendMessage("");
				}
				
			}
			else{
				event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.NO_PERMISSION.toString(true));
				return;
			}
		}
		
	}
	
}
