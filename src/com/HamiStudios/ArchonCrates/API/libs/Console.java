package com.HamiStudios.ArchonCrates.API.libs;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.ConsoleCommandSender;

import com.HamiStudios.ArchonCrates.Main;

public class Console {

	// Server Console Instance
	private ConsoleCommandSender console;
	 
	
	// Class constructor
	public Console(Main main) {
		// Gets ConsoleCommandSender instance from Main class
		this.console = main.getServer().getConsoleSender();
	}
	
	// Class constructor
	public Console(ConsoleCommandSender consoleSender) {
		this.console = consoleSender;
	}
	
	
	// Method used to send a log message to the server console
	public void log(String message) {
		// Sends a message to the server console with colours
		this.console.sendMessage(LanguageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', message));
	}
	
	// Method used to send a log message to the server console with ability to disable prefix
		public void log(String message, boolean prefix) {
			// Sends a message to the server console with colours
			if(!prefix) {
				this.console.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
			} else {
				log(message);
			}
		}
	
	// Method used to send a error message to the server console
	public void error(String message) {
		// Sends a message to the server console with colours
		this.console.sendMessage(LanguageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', "&cError: " + message));
	}
	
	// Method used to send a warning message to the server console
	public void warning(String message) {
		// Sends a message to the server console with colours
		this.console.sendMessage(LanguageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', "&eWarning: " + message));
	}
	
	// Method used to send a notice to the server console
	public void notice(String message) {
		// Sends a message to the server console with colours
		this.console.sendMessage(LanguageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', "&9Notice: " + message));
	}
	
	// Method to send a space to the server console
	public void space() {
		this.console.sendMessage(" ");
	}
	
}
