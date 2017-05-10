package com.HamiStudios.ArchonCrates.API.libs;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ACSender {

	private CommandSender sender;
	
	public ACSender(CommandSender sender) {
		this.sender = sender;
	}
	
	public void sendMessage(String message) {
		this.sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}
	
	public void sendSpace() {
		this.sender.sendMessage(" ");
	}
	
	public boolean hasPermission(String permission) {
		if(this.sender.hasPermission(permission)) return true;
		return false;
	}
	
	public boolean isConsole() {
		if(this.sender instanceof Player) return false;
		return true;
	}
	
	public CommandSender getSender() {
		return this.sender;
	}
	
}
