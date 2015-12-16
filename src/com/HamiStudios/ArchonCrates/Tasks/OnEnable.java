package com.HamiStudios.ArchonCrates.Tasks;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.HamiStudios.ArchonCrates.Main;
import com.HamiStudios.ArchonCrates.API.Objects.Glow;
import com.HamiStudios.ArchonCrates.Commands.Commands;
import com.HamiStudios.ArchonCrates.Commands.TabComplete;
import com.HamiStudios.ArchonCrates.Events.BlockBreakEvent;
import com.HamiStudios.ArchonCrates.Events.BlockClickEvent;
import com.HamiStudios.ArchonCrates.Events.BlockDrop;
import com.HamiStudios.ArchonCrates.Events.BlockPlace;
import com.HamiStudios.ArchonCrates.Events.DevJoin;
import com.HamiStudios.ArchonCrates.Events.EntityDeath;
import com.HamiStudios.ArchonCrates.Events.InventoryClick;
import com.HamiStudios.ArchonCrates.Events.InventoryClose;
import com.HamiStudios.ArchonCrates.Events.OnJoin;
import com.HamiStudios.ArchonCrates.Events.SignBreak;
import com.HamiStudios.ArchonCrates.Events.SignClick;
import com.HamiStudios.ArchonCrates.Events.SignCreate;
import com.HamiStudios.ArchonCrates.Events.onCommand;
import com.HamiStudios.ArchonCrates.Files.BuySign;
import com.HamiStudios.ArchonCrates.Files.Config;
import com.HamiStudios.ArchonCrates.Files.CrateLoot;
import com.HamiStudios.ArchonCrates.Files.Crates;
import com.HamiStudios.ArchonCrates.Files.CustomGUI;
import com.HamiStudios.ArchonCrates.Files.Data;
import com.HamiStudios.ArchonCrates.Files.FileHandler;
import com.HamiStudios.ArchonCrates.Files.Keys;
import com.HamiStudios.ArchonCrates.Files.Language;
import com.HamiStudios.ArchonCrates.Files.Locations;
import com.HamiStudios.ArchonCrates.Files.MobDrop;
import com.HamiStudios.ArchonCrates.Files.Permissions;
import com.HamiStudios.ArchonCrates.Files.PlayerLog;
import com.HamiStudios.ArchonCrates.Files.PrizeLog;
import com.HamiStudios.ArchonCrates.Files.VirtualCrates;
import com.HamiStudios.ArchonCrates.Files.VirtualKeys;
import com.HamiStudios.ArchonCrates.Files.VoucherData;
import com.HamiStudios.ArchonCrates.Util.DataLogger;
import com.HamiStudios.ArchonCrates.Util.FileType;
import com.HamiStudios.ArchonCrates.Util.MainGetter;
import com.HamiStudios.ArchonCrates.Util.PlayerData;
import com.HamiStudios.ArchonCrates.Util.UpdateChecker;

public class OnEnable {

	@SuppressWarnings({ "unused", "deprecation" })
	public OnEnable(Main main) {
		
		MainGetter maingetter = new MainGetter(main);
		
		int fileCount = 0;
		Config config = new Config();
		if(!config.exists()) fileCount++;
		CrateLoot crateLoot = new CrateLoot();
		if(!crateLoot.exists()) fileCount++;
		Keys keys = new Keys();
		if(!keys.exists()) fileCount++;
		Crates crates = new Crates();
		if(!crates.exists()) fileCount++;
		Locations locations = new Locations();
		if(!locations.exists()) fileCount++;
		PlayerLog playerlog = new PlayerLog();
		if(!playerlog.exists()) fileCount++;
		PrizeLog prizelog = new PrizeLog();
		if(!prizelog.exists()) fileCount++;
		BuySign buysign = new BuySign();
		if(!buysign.exists()) fileCount++;
		MobDrop mobDrop = new MobDrop();
		if(!mobDrop.exists()) fileCount++;
		Language language = new Language();
		if(!language.exists()) fileCount++;
		Permissions permissions = new Permissions();
		if(!permissions.exists()) fileCount++;
		Data data = new Data();
		if(!data.exists()) fileCount++;
		VirtualKeys vKeys = new VirtualKeys();
		if(!vKeys.exists()) fileCount++;
		CustomGUI cgui = new CustomGUI();
		if(!cgui.exists()) fileCount++;
		VirtualCrates vcrates = new VirtualCrates();
		if(!vcrates.exists()) fileCount++;
		com.HamiStudios.ArchonCrates.Files.BlockDrop blockDrop = new com.HamiStudios.ArchonCrates.Files.BlockDrop();
		if(!blockDrop.exists()) fileCount++;
		VoucherData voucher = new VoucherData();
		if(!voucher.exists()) fileCount++;
		
		ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
		console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&m[&6&m==================================================================================================&e&m]"));
		console.sendMessage(" ");
		console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&aArchonCrates&7] &ahas been enabled!"));
		console.sendMessage(" ");
		
		if(fileCount != 0) {
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &eFile Handling:"));
			console.sendMessage(" ");
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &cSome files are missing!"));
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &eCreating missing files..."));
			console.sendMessage(" ");
			int created = 0;
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &eCreation of files &7" + created + "/" + fileCount + " &ecomplete..."));
			if(!config.exists()) {
				config.create(); created++;
				console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &eCreation of files &7" + created + "/" + fileCount + " &ecomplete..."));
			}
			if(!crateLoot.exists()) {
				crateLoot.create(); created++;
				console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &eCreation of files &7" + created + "/" + fileCount + " &ecomplete..."));
			}
			if(!keys.exists()) {
				keys.create(); created++;
				console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &eCreation of files &7" + created + "/" + fileCount + " &ecomplete..."));
			}
			if(!crates.exists()) {
				crates.create(); created++;
				console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &eCreation of files &7" + created + "/" + fileCount + " &ecomplete..."));
			}
			if(!locations.exists()) {
				locations.create(); created++;
				console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &eCreation of files &7" + created + "/" + fileCount + " &ecomplete..."));
			}
			if(!playerlog.exists()) {
				playerlog.create(); created++;
				console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &eCreation of files &7" + created + "/" + fileCount + " &ecomplete..."));
			}
			if(!prizelog.exists()) {
				prizelog.create(); created++;
				console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &eCreation of files &7" + created + "/" + fileCount + " &ecomplete..."));
			}
			if(!buysign.exists()) {
				buysign.create(); created++;
				console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &eCreation of files &7" + created + "/" + fileCount + " &ecomplete..."));
			}
			if(!mobDrop.exists()) {
				mobDrop.create(); created++;
				console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &eCreation of files &7" + created + "/" + fileCount + " &ecomplete..."));
			}
			if(!language.exists()) {
				language.create(); created++;
				console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &eCreation of files &7" + created + "/" + fileCount + " &ecomplete..."));
			}
			if(!permissions.exists()) {
				permissions.create(); created++;
				console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &eCreation of files &7" + created + "/" + fileCount + " &ecomplete..."));
			}
			if(!data.exists()) {
				data.create(); created++;
				console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &eCreation of files &7" + created + "/" + fileCount + " &ecomplete..."));
			}
			if(!vKeys.exists()) {
				vKeys.create(); created++;
				console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &eCreation of files &7" + created + "/" + fileCount + " &ecomplete..."));
			}
			if(!cgui.exists()) {
				cgui.create(); created++;
				console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &eCreation of files &7" + created + "/" + fileCount + " &ecomplete..."));
			}
			if(!vcrates.exists()) {
				vcrates.create(); created++;
				console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &eCreation of files &7" + created + "/" + fileCount + " &ecomplete..."));
			}
			if(!blockDrop.exists()) {
				blockDrop.create(); created++;
				console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &eCreation of files &7" + created + "/" + fileCount + " &ecomplete..."));
			}
			if(!voucher.exists()) {
				voucher.create(); created++;
				console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &eCreation of files &7" + created + "/" + fileCount + " &ecomplete..."));
			}
			console.sendMessage(" ");
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &aFiles where created successfully"));
			console.sendMessage(" ");
		}
		
		if(FileHandler.getFile(FileType.CONFIG).getBoolean("Check for updates")) {
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &eUpdate Check:"));
			console.sendMessage(" ");
			UpdateChecker updateChecker = new UpdateChecker("http://dev.bukkit.org/bukkit-plugins/archoncrates/files.rss");
			
			String currentVersion = Bukkit.getPluginManager().getPlugin("ArchonCrates").getDescription().getVersion();
			
			if(updateChecker.isBetaVersion()) {
				console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &cYou are running a BETA version of ArchonCrates! This may cause errors. BE WARNED!"));
			}
			if(!(updateChecker.isBetaVersion()) && updateChecker.needsUpdated()) {
				String newVersion = updateChecker.getLatestVersion();
				String newLink = updateChecker.getLatestLink();
				console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &cThere is a new version of ArchonCrates out! (" + newVersion + ")"));
				console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &7You can download it from here: " + newLink));
			}
			if(!(updateChecker.isBetaVersion()) && !(updateChecker.needsUpdated())) {
				console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &aYou are running the newest version of ArchonCrates! (" + currentVersion + ")"));
			}
			console.sendMessage(" ");
		}
		
		// DATA LOGGING
		DataLogger dataLogger = new DataLogger(console);
		if(dataLogger.isEnabled()) {
			dataLogger.log();
		}
		
		
		// Set up VaultAPI
		ArrayList<String> plugins = new ArrayList<>();
		for(Plugin p : Bukkit.getServer().getPluginManager().getPlugins()) plugins.add(p.getName());
		
		if(plugins.contains("Vault")) {
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &eVault API:"));
			console.sendMessage(" ");
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &eYou have Vault installed this will allow ArchonCrates to use the Economy system for"));
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &ethe buy signs and also use the sub commands feature for the crate loot!"));
			console.sendMessage(" ");
		}
		
		if(Bukkit.getServer().getOnlinePlayers().length > 0) {
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &ePlayer Data Handler:"));
			console.sendMessage(" ");
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &eThere are players currently online!"));
			console.sendMessage(" ");
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &eUpdating their player data..."));
			
			for(Player p : Bukkit.getServer().getOnlinePlayers()) {
				PlayerData.addName(p);
			}
				
			console.sendMessage(ChatColor.translateAlternateColorCodes('&', "     &aSuccessfully updated their player data!"));
			
			console.sendMessage(" ");
		}
		
		console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&m[&6&m==================================================================================================&e&m]"));
		
		
		// Setup commands
		main.getCommand("archoncrates").setExecutor(new Commands());
		main.getCommand("archoncrates").setTabCompleter(new TabComplete());
		main.getCommand("ac?").setExecutor(new Commands());
		
		// Register Glow enchant
		Glow glow = new Glow(70);
		glow.registerGlow();
	
		// Register Events
		new InventoryClick(main);
		new InventoryClose(main);
		new BlockPlace(main);
		new BlockClickEvent(main);
		new BlockBreakEvent(main);
		new EntityDeath(main);
		new BlockDrop(main);
		new OnJoin(main);
		new SignCreate(main);
		new SignClick(main);
		new SignBreak(main);
		new DevJoin(main);
		new onCommand(main);
		
		// LOAD MAJOR FILES
		FileHandler fileHandler = new FileHandler();
		fileHandler.loadFiles();
		
		// UPDATES THE FILES FOR NEW VERSIONS
		FileUpdater.update();
		
	}
	
}
