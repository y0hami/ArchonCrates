package com.HamiStudios.ArchonCrates.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

import com.HamiStudios.ArchonCrates.Files.Config;
import com.HamiStudios.ArchonCrates.Files.FileHandler;

public class DataLogger {

	private ConsoleCommandSender console;
	
	private String username;
	private String password;
	private String databaseHost;
	private String databaseName;
	private int port;
	private String query;

	
	// Username: ArchonCratesAcc
	// Password: ,DHId~esXD3s
	
	
	@SuppressWarnings("deprecation")
	public DataLogger(ConsoleCommandSender console) {
		this.console = console;
		this.username = "ArchonCratesAcc";
		this.password = ",DHId~esXD3s";
		
		this.databaseHost = "107.180.2.20";
		this.databaseName = "ArchonCratesData";
		this.port = 3306;
		
		Date date = new Date(System.currentTimeMillis());
		this.query = "INSERT INTO `server_data`(`server_name`, `server_ip`, `server_port`, `server_version`, `server_motd`, `archoncrates_version`, `date_logged`, `time_logged`) VALUES "
				+ "('" + Bukkit.getServerName() + "','" + Bukkit.getIp() + "','" + Bukkit.getPort() + "','" + Bukkit.getVersion() + "','" + Bukkit.getMotd() + "','" + Bukkit.getPluginManager().getPlugin("ArchonCrates").getDescription().getVersion() + "','" + 
				(date.getDay()-1) + "/" + (date.getMonth()+1) + "/" + date.getYear() + "','" + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "')";
	}
	
	public void log() {
		
		if(isEnabled()) {
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &eData Logger:"));
			console.sendMessage(" ");
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &eLogging data..."));
			console.sendMessage(" ");
		}
		else{
			return;
		}
		
		Connection connection;
		String url = "jdbc:mysql://" + this.databaseHost + ":" + this.port + "/" + this.databaseName;
		try {
			connection = DriverManager.getConnection(url, this.username, this.password);
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			connection.close();
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &aData was successfully logged!"));
			console.sendMessage(" ");
			setLogging(false);
		} catch (SQLException e) {
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &cThere was a problem logging the data!"));
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &7JUST IGNORE, THIS IS FOR DEVELOPERS ONLY!"));
			console.sendMessage(" ");
			e.printStackTrace();
			console.sendMessage(" ");
		}
	}
	
	public boolean isEnabled() {
		return FileHandler.getFile(FileType.CONFIG).getBoolean("Data Logging");
	}
	
	public void setLogging(boolean value) {
		Config config = new Config();
		config.set("Data Logging", value);
		config.save();
	}
	
}
