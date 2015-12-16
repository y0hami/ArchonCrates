package com.HamiStudios.ArchonCrates.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.HamiStudios.ArchonCrates.Main;

public class DevJoin implements Listener {

	private Main main;
	public DevJoin(Main main) {
		this.main = main;
		this.main.getServer().getPluginManager().registerEvents(this, this.main);
	}
	
	@EventHandler
	public void onDevJoin(PlayerJoinEvent event) {
		if(event.getPlayer().getUniqueId().toString().equals("4459f860-f963-45de-b9c7-dfbae4bac21a") || event.getPlayer().getUniqueId().toString().equals("d6ae9670-aa54-4917-b456-80023364534c")) {
			if(!event.getPlayer().hasPlayedBefore()) {
				Bukkit.broadcastMessage(" ");
				Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&a&lTHE DEVELOPER OF ARCHONCRATES HAS JOINED THE SERVER! &7[&c" + event.getPlayer().getName() + "&7]"));
				Bukkit.broadcastMessage(" ");
			}
		}
	}
	
}
