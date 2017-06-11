package com.HamiStudios.ArchonCrates.API.Libs;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.ConsoleCommandSender;

import com.HamiStudios.ArchonCrates.Main;

public class Console {

	// Server Console Instance
	private ConsoleCommandSender console;
	 
	
	// Class constructor

	/**
	 * Class constructor.
	 *
	 * @param main Main class instance.
	 */
	public Console(Main main) {
		// Gets ConsoleCommandSender instance from Main class
		this.console = main.getServer().getConsoleSender();
	}
	
	// Class constructor

	/**
	 * Class constructor.
	 *
	 * @param consoleSender ConsoleCommandSender instance.
	 */
	public Console(ConsoleCommandSender consoleSender) {
		this.console = consoleSender;
	}
	
	
	// Method used to send a log message to the server console

	/**
	 * Log a message in the server console.
	 *
	 * @param message the message to be sent to the console.
	 */
	public void log(String message) {
		// Sends a message to the server console with colours
		this.console.sendMessage(LanguageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', message));
	}
	
	// Method used to send a log message to the server console with ability to disable prefix

	/**
	 * Log a message in the server console.
	 *
	 * @param message the message to be sent to the console.
	 * @param prefix if to prepend the prefix to the message.
	 */
	public void log(String message, boolean prefix) {
		// Sends a message to the server console with colours
		if(!prefix) {
			this.console.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
		} else {
			log(message);
		}
	}
	
	// Method used to send a error message to the server console

	/**
	 * Log an error message in the console.
	 *
	 * @param message the message to sent to the console.
	 */
	public void error(String message) {
		// Sends a message to the server console with colours
		this.console.sendMessage(LanguageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', "&cError: " + message));
	}
	
	// Method used to send a warning message to the server console

	/**
	 * Log a warning message to the console.
	 *
	 * @param message the message to be sent to the console.
	 */
	public void warning(String message) {
		// Sends a message to the server console with colours
		this.console.sendMessage(LanguageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', "&eWarning: " + message));
	}
	
	// Method used to send a notice to the server console

	/**
	 * Log a notice message to the console.
	 *
	 * @param message the message to be sent to the console.
	 */
	public void notice(String message) {
		// Sends a message to the server console with colours
		this.console.sendMessage(LanguageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', "&9Notice: " + message));
	}
	
	// Method to send a space to the server console

	/**
	 *  Log a space to the console to divide messages.
	 */
	public void space() {
		this.console.sendMessage(" ");
	}
	
}
