package com.HamiStudios.ArchonCrates.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.HamiStudios.ArchonCrates.Util.FileType;

public class FileHandler {
	
	private static File crateLootFile;
	private static FileConfiguration crateLootConfig;
	private static File cratesFile;
	private static FileConfiguration cratesConfig;
	private static File keysFile;
	private static FileConfiguration keysConfig;
	private static File virtualKeysFile;
	private static FileConfiguration virtualKeysConfig;
	private static File dataFile;
	private static FileConfiguration dataConfig;
	private static File locationsFile;
	private static FileConfiguration locationsConfig;
	private static File mobDropsFile;
	private static FileConfiguration mobDropsConfig;
	private static File blockDropsFile;
	private static FileConfiguration blockDropsConfig;
	private static File languageFile;
	private static FileConfiguration languageConfig;
	private static File permissionsFile;
	private static FileConfiguration permissionsConfig;
	private static File playerLogFile;
	private static FileConfiguration playerLogConfig;
	private static File prizeLogFile;
	private static FileConfiguration prizeLogConfig;
	private static File customGUIFile;
	private static FileConfiguration customGUIConfig;
	private static File virtualCratesFile;
	private static FileConfiguration virtualCratesConfig;
	private static File voucherFile;
	private static FileConfiguration voucherConfig;
	
	public void loadFiles() {
		crateLootFile = new File("plugins/ArchonCrates/Crate/crate loot.yml");
		crateLootConfig = YamlConfiguration.loadConfiguration(crateLootFile);
		
		cratesFile = new File("plugins/ArchonCrates/Crate/crates.yml");
		cratesConfig = YamlConfiguration.loadConfiguration(cratesFile);

		keysFile = new File("plugins/ArchonCrates/Crate/keys.yml");
		keysConfig = YamlConfiguration.loadConfiguration(keysFile);
		
		virtualKeysFile = new File("plugins/ArchonCrates/Crate/virtual keys.yml");
		virtualKeysConfig = YamlConfiguration.loadConfiguration(virtualKeysFile);
		
		dataFile = new File("plugins/ArchonCrates/Dont edit/data.yml");
		dataConfig = YamlConfiguration.loadConfiguration(dataFile);
		
		locationsFile = new File("plugins/ArchonCrates/Dont edit/locations.yml");
		locationsConfig = YamlConfiguration.loadConfiguration(locationsFile);
		
		mobDropsFile = new File("plugins/ArchonCrates/Drops/mob drops.yml");
		mobDropsConfig = YamlConfiguration.loadConfiguration(mobDropsFile);
		
		blockDropsFile = new File("plugins/ArchonCrates/Drops/block drops.yml");
		blockDropsConfig = YamlConfiguration.loadConfiguration(blockDropsFile);
		
		languageFile = new File("plugins/ArchonCrates/Extra/language.yml");
		languageConfig = YamlConfiguration.loadConfiguration(languageFile);
		
		permissionsFile = new File("plugins/ArchonCrates/Extra/permissions.yml");
		permissionsConfig = YamlConfiguration.loadConfiguration(permissionsFile);
		
		playerLogFile = new File("plugins/ArchonCrates/Logs/player_log.yml");
		playerLogConfig = YamlConfiguration.loadConfiguration(playerLogFile);
		
		prizeLogFile = new File("plugins/ArchonCrates/Logs/prize_log.yml");
		prizeLogConfig = YamlConfiguration.loadConfiguration(prizeLogFile);
		
		customGUIFile = new File("plugins/ArchonCrates/Crate/custom GUI.yml");
		customGUIConfig = YamlConfiguration.loadConfiguration(customGUIFile);

		virtualCratesFile = new File("plugins/ArchonCrates/Crate/virtual crates.yml");
		virtualCratesConfig = YamlConfiguration.loadConfiguration(virtualCratesFile);
		
		voucherFile = new File("plugins/ArchonCrates/logs/voucher data.yml");
		voucherConfig = YamlConfiguration.loadConfiguration(voucherFile);
		
	}
	
	public static void reload(FileType file) {
		if(file.equals(FileType.CRATE_LOOT)) {
			crateLootFile = new File("plugins/ArchonCrates/Crate/crate loot.yml");
			crateLootConfig = YamlConfiguration.loadConfiguration(crateLootFile);
		}
		if(file.equals(FileType.CRATES)) {
			cratesFile = new File("plugins/ArchonCrates/Crate/crates.yml");
			cratesConfig = YamlConfiguration.loadConfiguration(cratesFile);
		}
		if(file.equals(FileType.KEYS)) {
			keysFile = new File("plugins/ArchonCrates/Crate/keys.yml");
			keysConfig = YamlConfiguration.loadConfiguration(keysFile);
		}
		if(file.equals(FileType.VIRTUAL_KEYS)) {
			virtualKeysFile = new File("plugins/ArchonCrates/Crate/virtual keys.yml");
			virtualKeysConfig = YamlConfiguration.loadConfiguration(virtualKeysFile);
		}
		if(file.equals(FileType.DATA)) {
			dataFile = new File("plugins/ArchonCrates/Dont edit/data.yml");
			dataConfig = YamlConfiguration.loadConfiguration(dataFile);
		}
		if(file.equals(FileType.LOCATIONS)) {
			locationsFile = new File("plugins/ArchonCrates/Dont edit/locations.yml");
			locationsConfig = YamlConfiguration.loadConfiguration(locationsFile);
		}
		if(file.equals(FileType.MOB_DROP)) {
			mobDropsFile = new File("plugins/ArchonCrates/Drops/mob drops.yml");
			mobDropsConfig = YamlConfiguration.loadConfiguration(mobDropsFile);
		}
		if(file.equals(FileType.BLOCK_DROP)) {
			blockDropsFile = new File("plugins/ArchonCrates/Drops/block drops.yml");
			blockDropsConfig = YamlConfiguration.loadConfiguration(blockDropsFile);
		}
		if(file.equals(FileType.LANGUAGE)) {
			languageFile = new File("plugins/ArchonCrates/Extra/language.yml");
			languageConfig = YamlConfiguration.loadConfiguration(languageFile);
		}
		if(file.equals(FileType.PERMISSIONS)) {
			permissionsFile = new File("plugins/ArchonCrates/Extra/permissions.yml");
			permissionsConfig = YamlConfiguration.loadConfiguration(permissionsFile);
		}
		if(file.equals(FileType.PLAYER_LOG)) {
			playerLogFile = new File("plugins/ArchonCrates/Logs/player_log.yml");
			playerLogConfig = YamlConfiguration.loadConfiguration(playerLogFile);
		}
		if(file.equals(FileType.PRIZE_LOG)) {
			prizeLogFile = new File("plugins/ArchonCrates/Logs/prize_log.yml");
			prizeLogConfig = YamlConfiguration.loadConfiguration(prizeLogFile);
		}
		if(file.equals(FileType.CUSTOM_GUI)) {
			customGUIFile = new File("plugins/ArchonCrates/Crate/custom GUI.yml");
			customGUIConfig = YamlConfiguration.loadConfiguration(customGUIFile);
		}
		if(file.equals(FileType.VIRTUAL_CRATES)) {
			virtualCratesFile = new File("plugins/ArchonCrates/Crate/virtual crates.yml");
			virtualCratesConfig = YamlConfiguration.loadConfiguration(virtualCratesFile);
		}
		if(file.equals(FileType.VOUCHER)) {
			voucherFile = new File("plugins/ArchonCrates/logs/voucher data.yml");
			voucherConfig = YamlConfiguration.loadConfiguration(voucherFile);
		}
	}
	public static void reloadAll() {
		crateLootFile = new File("plugins/ArchonCrates/Crate/crate loot.yml");
		crateLootConfig = YamlConfiguration.loadConfiguration(crateLootFile);
		
		cratesFile = new File("plugins/ArchonCrates/Crate/crates.yml");
		cratesConfig = YamlConfiguration.loadConfiguration(cratesFile);

		keysFile = new File("plugins/ArchonCrates/Crate/keys.yml");
		keysConfig = YamlConfiguration.loadConfiguration(keysFile);
		
		virtualKeysFile = new File("plugins/ArchonCrates/Crate/virtual keys.yml");
		virtualKeysConfig = YamlConfiguration.loadConfiguration(virtualKeysFile);
		
		dataFile = new File("plugins/ArchonCrates/Dont edit/data.yml");
		dataConfig = YamlConfiguration.loadConfiguration(dataFile);
		
		locationsFile = new File("plugins/ArchonCrates/Dont edit/locations.yml");
		locationsConfig = YamlConfiguration.loadConfiguration(locationsFile);
		
		mobDropsFile = new File("plugins/ArchonCrates/Drops/mob drops.yml");
		mobDropsConfig = YamlConfiguration.loadConfiguration(mobDropsFile);
		
		blockDropsFile = new File("plugins/ArchonCrates/Drops/block drops.yml");
		blockDropsConfig = YamlConfiguration.loadConfiguration(blockDropsFile);
		
		languageFile = new File("plugins/ArchonCrates/Extra/language.yml");
		languageConfig = YamlConfiguration.loadConfiguration(languageFile);
		
		permissionsFile = new File("plugins/ArchonCrates/Extra/permissions.yml");
		permissionsConfig = YamlConfiguration.loadConfiguration(permissionsFile);
		
		playerLogFile = new File("plugins/ArchonCrates/Logs/player_log.yml");
		playerLogConfig = YamlConfiguration.loadConfiguration(playerLogFile);
		
		prizeLogFile = new File("plugins/ArchonCrates/Logs/prize_log.yml");
		prizeLogConfig = YamlConfiguration.loadConfiguration(prizeLogFile);
		
		customGUIFile = new File("plugins/ArchonCrates/Crate/custom GUI.yml");
		customGUIConfig = YamlConfiguration.loadConfiguration(customGUIFile);
		
		virtualCratesFile = new File("plugins/ArchonCrates/Crate/virtual crates.yml");
		virtualCratesConfig = YamlConfiguration.loadConfiguration(virtualCratesFile);
		
		voucherFile = new File("plugins/ArchonCrates/logs/voucher data.yml");
		voucherConfig = YamlConfiguration.loadConfiguration(voucherFile);
	}
	
	public static FileConfiguration getFile(FileType file) {
		if(file.equals(FileType.BUY_SIGN)) {
			BuySign buysign = new BuySign();
			return buysign.getFile();
		}
		if(file.equals(FileType.CONFIG)) {
			Config config = new Config();
			return config.getFile();
		}
		if(file.equals(FileType.CRATE_LOOT)) {
			return crateLootConfig;
		}
		if(file.equals(FileType.CRATES)) {
			return cratesConfig;
		}
		if(file.equals(FileType.KEYS)) {
			return keysConfig;
		}
		if(file.equals(FileType.VIRTUAL_KEYS)) {
			return virtualKeysConfig;
		}
		if(file.equals(FileType.LANGUAGE)) {
			return languageConfig;
		}
		if(file.equals(FileType.MOB_DROP)) {
			return mobDropsConfig;
		}
		if(file.equals(FileType.BLOCK_DROP)) {
			return blockDropsConfig;
		}
		if(file.equals(FileType.PLAYER_LOG)) {
			return playerLogConfig;
		}
		if(file.equals(FileType.PRIZE_LOG)) {
			return prizeLogConfig;
		}
		if(file.equals(FileType.LOCATIONS)) {
			return locationsConfig;
		}
		if(file.equals(FileType.PERMISSIONS)) {
			return permissionsConfig;
		}
		if(file.equals(FileType.DATA)) {
			return dataConfig;
		}
		if(file.equals(FileType.CUSTOM_GUI)) {
			return customGUIConfig;
		}
		if(file.equals(FileType.VIRTUAL_CRATES)) {
			return virtualCratesConfig;
		}
		if(file.equals(FileType.VOUCHER)) {
			return voucherConfig;
		}
		return null;
	}

	public static void create(FileType file) {
		if(file.equals(FileType.BUY_SIGN)) {
			BuySign buysign = new BuySign();
			buysign.create();
		}
		if(file.equals(FileType.CONFIG)) {
			Config config = new Config();
			config.create();
		}
		if(file.equals(FileType.CRATE_LOOT)) {
			CrateLoot crateLoot = new CrateLoot();
			crateLoot.create();
		}
		if(file.equals(FileType.CRATES)) {
			Crates crate = new Crates();
			crate.create();
		}
		if(file.equals(FileType.KEYS)) {
			Keys keys = new Keys();
			keys.create();
		}
		if(file.equals(FileType.VIRTUAL_KEYS)) {
			VirtualKeys vkeys = new VirtualKeys();
			vkeys.create();
		}
		if(file.equals(FileType.LANGUAGE)) {
			Language lang = new Language();
			lang.create();
		}
		if(file.equals(FileType.MOB_DROP)) {
			MobDrop mobDrop = new MobDrop();
			mobDrop.create();
		}
		if(file.equals(FileType.BLOCK_DROP)) {
			BlockDrop blockDrop = new BlockDrop();
			blockDrop.create();
		}
		if(file.equals(FileType.PLAYER_LOG)) {
			PlayerLog playerLog = new PlayerLog();
			playerLog.create();
		}
		if(file.equals(FileType.PRIZE_LOG)) {
			PrizeLog prizeLog = new PrizeLog();
			prizeLog.create(); 
		}
		if(file.equals(FileType.LOCATIONS)) {
			Locations loc = new Locations();
			loc.create();
		}
		if(file.equals(FileType.PERMISSIONS)) {
			Permissions perms = new Permissions();
			perms.create();
		}
		if(file.equals(FileType.DATA)) {
			Data data = new Data();
			data.create();
		}
		if(file.equals(FileType.CUSTOM_GUI)) {
			CustomGUI customGui = new CustomGUI();
			customGui.create();
		}
		if(file.equals(FileType.VIRTUAL_CRATES)) {
			VirtualCrates vcrates = new VirtualCrates();
			vcrates.create();
		}
		if(file.equals(FileType.VOUCHER)) {
			VoucherData voucher = new VoucherData();
			voucher.create();
		}
	}
	
	
	public static Object get(FileType file, String path) {
		if(file.equals(FileType.BUY_SIGN)) {
			BuySign buysign = new BuySign();
			return buysign.get(path);
		}
		if(file.equals(FileType.CONFIG)) {
			Config config = new Config();
			return config.get(path);
		}
		if(file.equals(FileType.CRATE_LOOT)) {
			return crateLootConfig.get(path);
		}
		if(file.equals(FileType.CRATES)) {
			return cratesConfig.get(path);
		}
		if(file.equals(FileType.KEYS)) {
			return keysConfig.get(path);
		}
		if(file.equals(FileType.VIRTUAL_KEYS)) {
			return virtualKeysConfig.get(path);
		}
		if(file.equals(FileType.LANGUAGE)) {
			return languageConfig.get(path);
		}
		if(file.equals(FileType.MOB_DROP)) {
			return mobDropsConfig.get(path);
		}
		if(file.equals(FileType.BLOCK_DROP)) {
			return blockDropsConfig.get(path);
		}
		if(file.equals(FileType.PLAYER_LOG)) {
			return playerLogConfig.get(path);
		}
		if(file.equals(FileType.PRIZE_LOG)) {
			return prizeLogConfig.get(path);
		}
		if(file.equals(FileType.LOCATIONS)) {
			return locationsConfig.get(path);
		}
		if(file.equals(FileType.PERMISSIONS)) {
			return permissionsConfig.get(path);
		}
		if(file.equals(FileType.DATA)) {
			return dataConfig.get(path);
		}
		if(file.equals(FileType.CUSTOM_GUI)) {
			return customGUIConfig.get(path);
		}
		if(file.equals(FileType.VIRTUAL_CRATES)) {
			return virtualCratesConfig.get(path);
		}
		if(file.equals(FileType.VOUCHER)) {
			return voucherConfig.get(path);
		}
		return null;
	}
	
	public static void set(FileType file, String path, Object value) {
		if(file.equals(FileType.BUY_SIGN)) {
			BuySign buysign = new BuySign();
			buysign.set(path, value);
			buysign.save();
		}
		if(file.equals(FileType.CONFIG)) {
			Config config = new Config();
			config.set(path, value);
			config.save();
		}
		if(file.equals(FileType.CRATE_LOOT)) {
			crateLootConfig.set(path, value);
			save(FileType.CRATE_LOOT);
		}
		if(file.equals(FileType.CRATES)) {
			cratesConfig.set(path, value);
			save(FileType.CRATES);
		}
		if(file.equals(FileType.KEYS)) {
			keysConfig.set(path, value);
			save(FileType.KEYS);
		}
		if(file.equals(FileType.VIRTUAL_KEYS)) {
			virtualKeysConfig.set(path, value);
			save(FileType.VIRTUAL_KEYS);
		}
		if(file.equals(FileType.LANGUAGE)) {
			languageConfig.set(path, value);
			save(FileType.LANGUAGE);
		}
		if(file.equals(FileType.MOB_DROP)) {
			mobDropsConfig.set(path, value);
			save(FileType.MOB_DROP);
		}
		if(file.equals(FileType.BLOCK_DROP)) {
			blockDropsConfig.set(path, value);
			save(FileType.BLOCK_DROP);
		}
		if(file.equals(FileType.PLAYER_LOG)) {
			playerLogConfig.set(path, value);
			save(FileType.PLAYER_LOG);
		}
		if(file.equals(FileType.PRIZE_LOG)) {
			prizeLogConfig.set(path, value);
			save(FileType.PRIZE_LOG);
		}
		if(file.equals(FileType.LOCATIONS)) {
			locationsConfig.set(path, value);
			save(FileType.LOCATIONS);
		}
		if(file.equals(FileType.PERMISSIONS)) {
			permissionsConfig.set(path, value);
			save(FileType.PERMISSIONS);
		}
		if(file.equals(FileType.DATA)) {
			dataConfig.set(path, value);
			save(FileType.DATA);
		}
		if(file.equals(FileType.CUSTOM_GUI)) {
			customGUIConfig.set(path, value);
			save(FileType.CUSTOM_GUI);
		}
		if(file.equals(FileType.VIRTUAL_CRATES)) {
			virtualCratesConfig.set(path, value);
			save(FileType.VIRTUAL_CRATES);
		}
		if(file.equals(FileType.VOUCHER)) {
			voucherConfig.set(path, value);
			save(FileType.VOUCHER);
		}
	}

	public static ConfigurationSection getSection(FileType file, String path) {
		if(file.equals(FileType.BUY_SIGN)) {
			BuySign buysign = new BuySign();
			return buysign.getFile().getConfigurationSection(path);
		}
		if(file.equals(FileType.CONFIG)) {
			Config config = new Config();
			return config.getFile().getConfigurationSection(path);
		}
		if(file.equals(FileType.CRATE_LOOT)) {
			return crateLootConfig.getConfigurationSection(path);
		}
		if(file.equals(FileType.CRATES)) {
			return cratesConfig.getConfigurationSection(path);
		}
		if(file.equals(FileType.KEYS)) {
			return keysConfig.getConfigurationSection(path);
		}
		if(file.equals(FileType.VIRTUAL_KEYS)) {
			return virtualKeysConfig.getConfigurationSection(path);
		}
		if(file.equals(FileType.LANGUAGE)) {
			return languageConfig.getConfigurationSection(path);
		}
		if(file.equals(FileType.MOB_DROP)) {
			return mobDropsConfig.getConfigurationSection(path);
		}
		if(file.equals(FileType.BLOCK_DROP)) {
			return blockDropsConfig.getConfigurationSection(path);
		}
		if(file.equals(FileType.PLAYER_LOG)) {
			return playerLogConfig.getConfigurationSection(path);
		}
		if(file.equals(FileType.PRIZE_LOG)) {
			PrizeLog prizelog = new PrizeLog();
			return prizelog.getFile().getConfigurationSection(path);
		}
		if(file.equals(FileType.LOCATIONS)) {
			return locationsConfig.getConfigurationSection(path);
		}
		if(file.equals(FileType.PERMISSIONS)) {
			return permissionsConfig.getConfigurationSection(path);
		}
		if(file.equals(FileType.DATA)) {
			return dataConfig.getConfigurationSection(path);
		}
		if(file.equals(FileType.CUSTOM_GUI)) {
			return customGUIConfig.getConfigurationSection(path);
		}
		if(file.equals(FileType.VIRTUAL_CRATES)) {
			return virtualCratesConfig.getConfigurationSection(path);
		}
		if(file.equals(FileType.VOUCHER)) {
			return voucherConfig.getConfigurationSection(path);
		}
		return null;
	}
	
	public static List<?> getList(FileType file, String path) {
		if(file.equals(FileType.BUY_SIGN)) {
			BuySign buysign = new BuySign();
			return buysign.getFile().getList(path);
		}
		if(file.equals(FileType.CONFIG)) {
			Config config = new Config();
			return config.getFile().getList(path);
		}
		if(file.equals(FileType.CRATE_LOOT)) {
			CrateLoot crateLoot = new CrateLoot();
			return crateLoot.getFile().getList(path);
		}
		if(file.equals(FileType.CRATES)) {
			return cratesConfig.getList(path);
		}
		if(file.equals(FileType.KEYS)) {
			return keysConfig.getList(path);
		}
		if(file.equals(FileType.VIRTUAL_KEYS)) {
			return virtualKeysConfig.getList(path);
		}
		if(file.equals(FileType.LANGUAGE)) {
			return languageConfig.getList(path);
		}
		if(file.equals(FileType.MOB_DROP)) {
			return mobDropsConfig.getList(path);
		}
		if(file.equals(FileType.BLOCK_DROP)) {
			return blockDropsConfig.getList(path);
		}
		if(file.equals(FileType.PLAYER_LOG)) {
			return playerLogConfig.getList(path);
		}
		if(file.equals(FileType.PRIZE_LOG)) {
			return prizeLogConfig.getList(path);
		}
		if(file.equals(FileType.LOCATIONS)) {
			return locationsConfig.getList(path);
		}
		if(file.equals(FileType.PERMISSIONS)) {
			return permissionsConfig.getList(path);
		}
		if(file.equals(FileType.DATA)) {
			return dataConfig.getList(path);
		}
		if(file.equals(FileType.CUSTOM_GUI)) {
			return customGUIConfig.getList(path);
		}
		if(file.equals(FileType.VIRTUAL_CRATES)) {
			return virtualCratesConfig.getList(path);
		}
		if(file.equals(FileType.VOUCHER)) {
			return voucherConfig.getList(path);
		}
		return null;
	}
	
	public static boolean exists(FileType file) {
		if(file.equals(FileType.BUY_SIGN)) {
			BuySign buysign = new BuySign();
			return buysign.exists();
		}
		if(file.equals(FileType.CONFIG)) {
			Config config = new Config();
			return config.exists();
		}
		if(file.equals(FileType.CRATE_LOOT)) {
			CrateLoot crateLoot = new CrateLoot();
			return crateLoot.exists();
		}
		if(file.equals(FileType.CRATES)) {
			Crates crates = new Crates();
			return crates.exists();
		}
		if(file.equals(FileType.KEYS)) {
			Keys keys = new Keys();
			return keys.exists();
		}
		if(file.equals(FileType.VIRTUAL_KEYS)) {
			VirtualKeys vKeys = new VirtualKeys();
			return vKeys.exists();
		}
		if(file.equals(FileType.LANGUAGE)) {
			Language lang = new Language();
			return lang.exists();
		}
		if(file.equals(FileType.MOB_DROP)) {
			MobDrop mobDrop = new MobDrop();
			return mobDrop.exists();
		}
		if(file.equals(FileType.BLOCK_DROP)) {
			BlockDrop blockDrop = new BlockDrop();
			return blockDrop.exists();
		}
		if(file.equals(FileType.PLAYER_LOG)) {
			PlayerLog playerlog = new PlayerLog();
			return playerlog.exists();
		}
		if(file.equals(FileType.PRIZE_LOG)) {
			PrizeLog prizelog = new PrizeLog();
			return prizelog.exists();
		}
		if(file.equals(FileType.LOCATIONS)) {
			Locations locations = new Locations();
			return locations.exists();
		}
		if(file.equals(FileType.PERMISSIONS)) {
			Permissions permissions = new Permissions();
			return permissions.exists();
		}
		if(file.equals(FileType.DATA)) {
			Data data = new Data();
			return data.exists();
		}
		if(file.equals(FileType.CUSTOM_GUI)) {
			CustomGUI cgui = new CustomGUI();
			return cgui.exists();
		}
		if(file.equals(FileType.VIRTUAL_CRATES)) {
			VirtualCrates vcrates = new VirtualCrates();
			return vcrates.exists();
		}
		if(file.equals(FileType.VOUCHER)) {
			VoucherData voucher = new VoucherData();
			return voucher.exists();
		}
		return false;
	}
	
	public static void save(FileType file) {
		if(file.equals(FileType.BUY_SIGN)) {
			BuySign buysign = new BuySign();
			buysign.save();
		}
		if(file.equals(FileType.CONFIG)) {
			Config config = new Config();
			config.save();
		}
		if(file.equals(FileType.CRATE_LOOT)) {
			try {
				crateLootConfig.save(crateLootFile);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		if(file.equals(FileType.CRATES)) {
			try {
				cratesConfig.save(cratesFile);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		if(file.equals(FileType.KEYS)) {
			try {
				keysConfig.save(keysFile);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		if(file.equals(FileType.VIRTUAL_KEYS)) {
			try {
				virtualKeysConfig.save(virtualKeysFile);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		if(file.equals(FileType.LANGUAGE)) {
			try {
				languageConfig.save(languageFile);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		if(file.equals(FileType.MOB_DROP)) {
			try {
				mobDropsConfig.save(mobDropsFile);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		if(file.equals(FileType.BLOCK_DROP)) {
			try {
				blockDropsConfig.save(blockDropsFile);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		if(file.equals(FileType.PLAYER_LOG)) {
			try {
				playerLogConfig.save(playerLogFile);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		if(file.equals(FileType.PRIZE_LOG)) {
			try {
				prizeLogConfig.save(prizeLogFile);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		if(file.equals(FileType.LOCATIONS)) {
			try {
				locationsConfig.save(locationsFile);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		if(file.equals(FileType.PERMISSIONS)) {
			try {
				permissionsConfig.save(permissionsFile);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		if(file.equals(FileType.DATA)) {
			try {
				dataConfig.save(dataFile);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

		if(file.equals(FileType.CUSTOM_GUI)) {
			try {
				customGUIConfig.save(customGUIFile);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		if(file.equals(FileType.VIRTUAL_CRATES)) {
			try {
				virtualCratesConfig.save(virtualCratesFile);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

		if(file.equals(FileType.VOUCHER)) {
			try {
				voucherConfig.save(voucherFile);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
