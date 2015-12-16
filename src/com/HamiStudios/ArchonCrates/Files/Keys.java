package com.HamiStudios.ArchonCrates.Files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class Keys implements FileInterface {

	private File file;
	private FileConfiguration fileconfig;
	
	public Keys() {
		file = new File("plugins/ArchonCrates/Crate/keys.yml");
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
		file = new File("plugins/ArchonCrates/Crate/keys.yml");
		fileconfig = YamlConfiguration.loadConfiguration(file);
	}

	@Override
	public void set(String path, Object value) {
		getFile().set(path, value);
		save();
	}

	@Override
	public Object get(String path) {
		return getFile().get(path);
	}

	@Override
	public boolean exists() {
		if(file.exists()) return true;
		return false;
	}

	@Override
	public void create() {
		// Default Key
		getFile().set("Keys.default.name", "&aCrate Key");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("&7Right click a crate");
		lore.add("&7to use the key");
		getFile().set("Keys.default.lore", lore);
		getFile().set("Keys.default.itemID", 131);
		getFile().set("Keys.default.itemData", 0);
		getFile().set("Keys.default.glow", true);
		getFile().set("Keys.default.winMessage", "&e<player> &7has won &e<prize> &7in a crate!");
		getFile().set("Keys.default.playerMessage", "&7Congratulations you won &e<prize>&7!");
		ArrayList<String> loot = new ArrayList<>();
		loot.add("Diamonds");
		loot.add("Food");
		loot.add("Sword");
		loot.add("Gold");
		loot.add("Tools");
		loot.add("CrateKey");
		getFile().set("Keys.default.loot", loot);

		// Golden Key
		getFile().set("Keys.golden.name", "&6Golden Key");
		lore = new ArrayList<>();
		lore.add("&7Right click a crate");
		lore.add("&7to use the key");
		getFile().set("Keys.golden.lore", lore);
		getFile().set("Keys.golden.itemID", 396);
		getFile().set("Keys.golden.itemData", 0);
		getFile().set("Keys.golden.glow", true);
		getFile().set("Keys.golden.winMessage", "&e<player> &6has won &e<prize> &6in a crate!");
		getFile().set("Keys.golden.playerMessage", "&7Congratulations you won &e<prize>&7!");
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
		
		getFile().set("Keys.golden.loot", loot);
		
		getFile().set("file version", "0.0.0.1");
		
		save();
		setHeader();
	}

	@Override
	public void setHeader() {
		fileconfig.options().header(""
				+ "######################################################################################################################################################################### #\n"
				+ "                                                                                                                                                                          #\n"
				+ "                                                               ArchonCrates Keys Configuration                                                                            #\n"
				+ "                                                                                                                                                                          #\n"
				+ " Keys:                                                                                                                                                                    #\n"
				+ "   default:                                                            - The keyType used to allow keys to be used on crates and to give/open crates with commands        #\n"
				+ "     name: '&aCrate Key'                                               - The display name of the key item                                                                 #\n"
				+ "     lore:                                                             - The lore of the key item                                                                         #\n"
				+ "     - '&7Right click a crate'                                                                                                                                            #\n"
				+ "     - '&7to use the key'                                                                                                                                                 #\n"
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
