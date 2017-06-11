package com.HamiStudios.ArchonCrates.API.Objects;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ACSender {

	private CommandSender sender;

	/**
	 * Class constructor.
	 *
	 * @param sender of which to use.
	 */
	public ACSender(CommandSender sender) {
		this.sender = sender;
	}

	/**
	 * Send a message to the sender.
	 *
	 * @param message to send.
	 */
	public void sendMessage(String message) {
		this.sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}

	/**
	 * Send a space to the sender.
	 * Used to separate messages.
	 */
	public void sendSpace() {
		this.sender.sendMessage(" ");
	}

	/**
	 * Check if the sender has a permission.
	 *
	 * @param permission of which to check.
	 * @return true if they have it and false if not.
	 */
	public boolean hasPermission(String permission) {
		if(this.sender.hasPermission(permission)) return true;
		return false;
	}

	/**
	 * Check if the sender is the console.
	 *
	 * @return true if they are and false if not.
	 */
	public boolean isConsole() {
		if(this.sender instanceof Player) return false;
		return true;
	}

	/**
	 * Get the CommandSender instance.
	 *
	 * @return CommandSender instance.
	 */
	public CommandSender getSender() {
		return this.sender;
	}
	
}
