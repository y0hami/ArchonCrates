package com.HamiStudios.ArchonCrates.API.Objects.Exceptions;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

public class InvalidCrateInput extends Exception {

	private static final long serialVersionUID = 1L;

	public void log(String crateId) {
		ConsoleCommandSender console = Bukkit.getConsoleSender();
		console.sendMessage(" ");
		console.sendMessage(" ");
		console.sendMessage(ChatColor.RED + "ArchonCrates CRATE ERROR: ");
		console.sendMessage(ChatColor.RED + "THERE IS A PROBLEM IN YOUR \"crates.yml\" file!");
		console.sendMessage(ChatColor.RED + "    Crate with error: " + crateId);
		console.sendMessage(" ");
		console.sendMessage(" ");
	}
	
	public void writeToFile(String crateId) {
		
		Logger logger = Logger.getLogger("errorLog");
		try {
			FileHandler fh = new FileHandler("plugins/ArchonCrates/error log.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			logger.setUseParentHandlers(false);
			logger.info("Crate Error - Crate ID: " + crateId);
		} catch(SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
