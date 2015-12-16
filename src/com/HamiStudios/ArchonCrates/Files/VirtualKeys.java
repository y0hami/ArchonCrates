package com.HamiStudios.ArchonCrates.Files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class VirtualKeys implements FileInterface {

	private File file;
	private FileConfiguration fileconfig;
	
	public VirtualKeys() {
		file = new File("plugins/ArchonCrates/Crate/virtual keys.yml");
		fileconfig = YamlConfiguration.loadConfiguration(file);
	}
	
	
	@Override
	public FileConfiguration getFile() {
		return fileconfig;
	}

	@Override
	public void save() {
		try {
			fileconfig.save(file);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void reload() {
		file = new File("plugins/ArchonCrates/Crate/virtual keys.yml");
		fileconfig = YamlConfiguration.loadConfiguration(file);
	}

	@Override
	public void set(String path, Object value) {
		fileconfig.set(path, value);
		save();
	}

	@Override
	public Object get(String path) {
		return fileconfig.get(path);
	}

	@Override
	public boolean exists() {
		if(new File("plugins/ArchonCrates/Crate/virtual keys.yml").exists()) return true;
		return false;
	}

	@Override
	public void create() {
		// Default Key
		getFile().set("Virtual Keys.default.name", "&aCrate Key");
		getFile().set("Virtual Keys.default.itemID", 131);
		getFile().set("Virtual Keys.default.itemData", 0);
		getFile().set("Virtual Keys.default.glow", true);
		getFile().set("Virtual Keys.default.winMessage", "&e<player> &7has won &e<prize> &7in a crate!");
		getFile().set("Virtual Keys.default.playerMessage", "&7Congratulations you won &e<prize>&7!");
		ArrayList<String> loot = new ArrayList<>();
		loot.add("Diamonds");
		loot.add("Food");
		loot.add("Sword");
		loot.add("Gold");
		loot.add("Tools");
		loot.add("CrateKey");
		getFile().set("Virtual Keys.default.loot", loot);

		// Golden Key
		getFile().set("Virtual Keys.golden.name", "&6Golden Key");
		getFile().set("Virtual Keys.golden.itemID", 396);
		getFile().set("Virtual Keys.golden.itemData", 0);
		getFile().set("Virtual Keys.golden.glow", true);
		getFile().set("Virtual Keys.golden.winMessage", "&e<player> &6has won &e<prize> &7in a crate!");
		getFile().set("Virtual Keys.golden.playerMessage", "&7Congratulations you won &e<prize>&7!");
		loot = new ArrayList<>();
		loot.add("Diamonds");
		loot.add("Food");
		loot.add("Sword");
		loot.add("Gold");
		loot.add("Tools");
		loot.add("CrateKey");
		loot.add("GodApple");
		loot.add("DevHead");
		
		// If VaultAPI is enabled
		ArrayList<String> plugins = new ArrayList<>();
		for(Plugin p : Bukkit.getServer().getPluginManager().getPlugins()) plugins.add(p.getName());
		
		if(plugins.contains("GroupManager")) {
			loot.add("Rank");
		}
		if(plugins.contains("PermissionsEx")) {
			loot.add("Rank");
		}
		
		getFile().set("Virtual Keys.golden.loot", loot);
		
		// Unique Key
		getFile().set("Virtual Keys.unique.name", "&6Unique Key");
		getFile().set("Virtual Keys.unique.itemID", 371);
		getFile().set("Virtual Keys.unique.itemData", 0);
		getFile().set("Virtual Keys.unique.glow", true);
		getFile().set("Virtual Keys.unique.winMessage", "&6<player> &6has won &e<prize> &6in a crate!");
		getFile().set("Virtual Keys.unique.playerMessage", "&6Congratulations you won &e<prize>&6!");
		loot = new ArrayList<>();
		loot.add("Diamonds");
		loot.add("Food");
		loot.add("Sword");
		loot.add("Gold");
		loot.add("Tools");
		loot.add("CrateKey");
		loot.add("GodApple");
		loot.add("DevHead");
		loot.add("NetherStar");
		loot.add("Emeralds");
		
		if(plugins.contains("GroupManager")) {
			loot.add("Rank");
		}
		if(plugins.contains("PermissionsEx")) {
			loot.add("Rank");
		}
		
		getFile().set("Virtual Keys.unique.loot", loot);

		getFile().set("file version", "0.0.0.1");
		
		save();
		setHeader();
	}

	@Override
	public void setHeader() {
		fileconfig.options().header(""
				+ "######################################################################################################################################################################### #\n"
				+ "                                                                                                                                                                          #\n"
				+ "                                                               ArchonCrates Virtual Keys Configuration                                                                    #\n"
				+ "                                                                                                                                                                          #\n"
				+ " Virtual Keys:                                                                                                                                                            #\n"
				+ "   default:                                                            - The vkeyType used to allow keys to be used on crates and to give/open crates with commands       #\n"
				+ "     name: '&aCrate Key'                                               - The display name of the key item                                                                 #\n"
				+ "     itemID: 131                                                       - The id of the key item                                                                           #\n"
				+ "     itemData: 0                                                       - The data value of the key item                                                                   #\n"
				+ "     glow: true                                                        - If true the key item will glow                                                                   #\n"
				+ "     winMessage: '&e<player> &7has won &e<prize> &7in a crate!'        - The message which will be broadcast if 'Broadcast Win' is true for the prize                     #\n"
				+ "     playerMessage: '&7Congratulations you won &e<prize>&7!'           - The message which will be sent to the player if 'Player Message' is true for the prize           #\n"
				+ "     loot:                                                             - A list of loot which can be won by the key                                                       #\n"
				+ "     - Diamonds                                                                                                                                                           #\n"
				+ "     - Food                                                                                                                                                               #\n"
				+ "     - Sword                                                                                                                                                              #\n"
				+ "     - Gold                                                                                                                                                               #\n"
				+ "     - Tools                                                                                                                                                              #\n"
				+ "     - CrateKey                                                                                                                                                           #\n"
				+ "                                                                                                                                                                          #\n"
				+ "                                                                                                                                                                          #\n"
				+ "                                                                                                                                                                          #\n"
				+ "######################################################################################################################################################################### #\n"
				+ "");
		save();
	}
	
}
