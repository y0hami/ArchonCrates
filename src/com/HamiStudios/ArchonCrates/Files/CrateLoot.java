package com.HamiStudios.ArchonCrates.Files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class CrateLoot implements FileInterface {

	private File file;
	private FileConfiguration fileconfig;
	
	public CrateLoot() {
		file = new File("plugins/ArchonCrates/Crate/crate loot.yml");
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
		file = new File("plugins/ArchonCrates/Crate/crate loot.yml");
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
		// Diamond Prize
		getFile().set("Crate Loot.Diamonds.Item ID", 264);
		getFile().set("Crate Loot.Diamonds.Item Data", 0);
		getFile().set("Crate Loot.Diamonds.StackAmount", 10);
		getFile().set("Crate Loot.Diamonds.Name", "&3Diamonds");
		getFile().set("Crate Loot.Diamonds.Broadcast Win", true);
		getFile().set("Crate Loot.Diamonds.Player Message", true);
		getFile().set("Crate Loot.Diamonds.Chance", 5.0);
		getFile().set("Crate Loot.Diamonds.Prize Name", "10 diamonds");
		getFile().set("Crate Loot.Diamonds.Glow", true);
		getFile().set("Crate Loot.Diamonds.Use Permission", false);
		getFile().set("Crate Loot.Diamonds.Permission", "archoncrates.prize.diamonds");
		getFile().set("Crate Loot.Diamonds.Global Win Amount", "*");
		getFile().set("Crate Loot.Diamonds.Player Win Amount", "*");
		ArrayList<String> commands = new ArrayList<>();
		commands.add("minecraft:give <player> diamond 10");
		getFile().set("Crate Loot.Diamonds.Commands", commands);

		// Food Prize
		getFile().set("Crate Loot.Food.Item ID", 364);
		getFile().set("Crate Loot.Food.Item Data", 0);
		getFile().set("Crate Loot.Food.StackAmount", 16);
		getFile().set("Crate Loot.Food.Name", "&dFood");
		getFile().set("Crate Loot.Food.Broadcast Win", false);
		getFile().set("Crate Loot.Food.Player Message", true);
		getFile().set("Crate Loot.Food.Chance", 40.0);
		getFile().set("Crate Loot.Food.Prize Name", "food set");
		getFile().set("Crate Loot.Food.Glow", false);
		getFile().set("Crate Loot.Food.Use Permission", false);
		getFile().set("Crate Loot.Food.Permission", "archoncrates.prize.food");
		getFile().set("Crate Loot.Food.Global Win Amount", "*");
		getFile().set("Crate Loot.Food.Player Win Amount", "*");
		commands = new ArrayList<>();
		commands.add("minecraft:give <player> cooked_beef 16");
		commands.add("minecraft:give <player> cooked_chicken 16");
		commands.add("minecraft:give <player> cooked_porkchop 16");
		getFile().set("Crate Loot.Food.Commands", commands);
		
		// Sword Prize 
		getFile().set("Crate Loot.Sword.Item ID", 276);
		getFile().set("Crate Loot.Sword.Item Data", 0);
		getFile().set("Crate Loot.Sword.StackAmount", 1);
		getFile().set("Crate Loot.Sword.Name", "&bSword");
		getFile().set("Crate Loot.Sword.Broadcast Win", false);
		getFile().set("Crate Loot.Sword.Player Message", true);
		getFile().set("Crate Loot.Sword.Chance", 10.0);
		getFile().set("Crate Loot.Sword.Prize Name", "diamond sword");
		getFile().set("Crate Loot.Sword.Glow", true);
		getFile().set("Crate Loot.Sword.Use Permission", false);
		getFile().set("Crate Loot.Sword.Permission", "archoncrates.prize.sword");
		getFile().set("Crate Loot.Sword.Global Win Amount", "*");
		getFile().set("Crate Loot.Sword.Player Win Amount", "*");
		commands = new ArrayList<>();
		commands.add("minecraft:give <player> diamond_sword 1");
		getFile().set("Crate Loot.Sword.Commands", commands);
		
		// Gold Prize
		getFile().set("Crate Loot.Gold.Item ID", 266);
		getFile().set("Crate Loot.Gold.Item Data", 0);
		getFile().set("Crate Loot.Gold.StackAmount", 32);
		getFile().set("Crate Loot.Gold.Name", "&6Gold");
		getFile().set("Crate Loot.Gold.Broadcast Win", false);
		getFile().set("Crate Loot.Gold.Player Message", true);
		getFile().set("Crate Loot.Gold.Chance", 15.0);
		getFile().set("Crate Loot.Gold.Prize Name", "32 gold");
		getFile().set("Crate Loot.Gold.Glow", false);
		getFile().set("Crate Loot.Gold.Use Permission", false);
		getFile().set("Crate Loot.Gold.Permission", "archoncrates.prize.gold");
		getFile().set("Crate Loot.Gold.Global Win Amount", "*");
		getFile().set("Crate Loot.Gold.Player Win Amount", "*");
		commands = new ArrayList<>();
		commands.add("minecraft:give <player> gold_ingot 32");
		getFile().set("Crate Loot.Gold.Commands", commands);
		
		// Tools Prize
		getFile().set("Crate Loot.Tools.Item ID", 257);
		getFile().set("Crate Loot.Tools.Item Data", 0);
		getFile().set("Crate Loot.Tools.StackAmount", 1);
		getFile().set("Crate Loot.Tools.Name", "&7Tools");
		getFile().set("Crate Loot.Tools.Broadcast Win", false);
		getFile().set("Crate Loot.Tools.Player Message", true);
		getFile().set("Crate Loot.Tools.Chance", 20.0);
		getFile().set("Crate Loot.Tools.Prize Name", "Iron tools");
		getFile().set("Crate Loot.Tools.Glow", false);
		getFile().set("Crate Loot.Tools.Use Permission", false);
		getFile().set("Crate Loot.Tools.Permission", "archoncrates.prize.tools");
		getFile().set("Crate Loot.Tools.Global Win Amount", "*");
		getFile().set("Crate Loot.Tools.Player Win Amount", "*");
		commands = new ArrayList<>();
		commands.add("minecraft:give <player> iron_pickaxe 1");
		commands.add("minecraft:give <player> iron_axe 1");
		commands.add("minecraft:give <player> iron_shovel 1");
		getFile().set("Crate Loot.Tools.Commands", commands);
		
		// GodApple Prize
		getFile().set("Crate Loot.GodApple.Item ID", 322);
		getFile().set("Crate Loot.GodApple.Item Data", 1);
		getFile().set("Crate Loot.GodApple.StackAmount", 1);
		getFile().set("Crate Loot.GodApple.Name", "&6God Apple");
		getFile().set("Crate Loot.GodApple.Broadcast Win", true);
		getFile().set("Crate Loot.GodApple.Player Message", true);
		getFile().set("Crate Loot.GodApple.Chance", 3.0);
		getFile().set("Crate Loot.GodApple.Prize Name", "GOD APPLE");
		getFile().set("Crate Loot.GodApple.Glow", true);
		getFile().set("Crate Loot.GodApple.Use Permission", false);
		getFile().set("Crate Loot.GodApple.Permission", "archoncrates.prize.godapple");
		getFile().set("Crate Loot.GodApple.Global Win Amount", "*");
		getFile().set("Crate Loot.GodApple.Player Win Amount", "*");
		commands = new ArrayList<>();
		commands.add("minecraft:give <player> golden_apple 1 1");
		getFile().set("Crate Loot.GodApple.Commands", commands);
		
		// CrateKey Prize
		getFile().set("Crate Loot.CrateKey.Item ID", 131);
		getFile().set("Crate Loot.CrateKey.Item Data", 0);
		getFile().set("Crate Loot.CrateKey.StackAmount", 1);
		getFile().set("Crate Loot.CrateKey.Name", "&aCrate Key");
		getFile().set("Crate Loot.CrateKey.Broadcast Win", true);
		getFile().set("Crate Loot.CrateKey.Player Message", true);
		getFile().set("Crate Loot.CrateKey.Chance", 10.0);
		getFile().set("Crate Loot.CrateKey.Prize Name", "crate key");
		getFile().set("Crate Loot.CrateKey.Glow", true);
		getFile().set("Crate Loot.CrateKey.Use Permission", false);
		getFile().set("Crate Loot.CrateKey.Permission", "archoncrates.prize.cratekey");
		getFile().set("Crate Loot.CrateKey.Global Win Amount", "*");
		getFile().set("Crate Loot.CrateKey.Player Win Amount", "*");
		commands = new ArrayList<>();
		commands.add("archoncrates key <player> default 1");
		getFile().set("Crate Loot.CrateKey.Commands", commands);
		
		// DevHead Prize
		getFile().set("Crate Loot.DevHead.Item ID", 397);
		getFile().set("Crate Loot.DevHead.Item Data", 3);
		getFile().set("Crate Loot.DevHead.StackAmount", 1);
		getFile().set("Crate Loot.DevHead.Name", "&cDeveloper Head");
		getFile().set("Crate Loot.DevHead.Broadcast Win", false);
		getFile().set("Crate Loot.DevHead.Player Message", true);
		getFile().set("Crate Loot.DevHead.Chance", 30.0);
		getFile().set("Crate Loot.DevHead.Prize Name", "developers head");
		getFile().set("Crate Loot.DevHead.Glow", false);
		getFile().set("Crate Loot.DevHead.Use Permission", false);
		getFile().set("Crate Loot.DevHead.Permission", "archoncrates.prize.devhead");
		getFile().set("Crate Loot.DevHead.Global Win Amount", "*");
		getFile().set("Crate Loot.DevHead.Player Win Amount", "*");
		commands = new ArrayList<>();
		commands.add("minecraft:give <player> skull 1 3 {SkullOwner:\"Hamiii\",Unbreakable:1}");
		getFile().set("Crate Loot.DevHead.Commands", commands);
		
		// Nether Star Prize
		getFile().set("Crate Loot.NetherStar.Item ID", 399);
		getFile().set("Crate Loot.NetherStar.Item Data", 0);
		getFile().set("Crate Loot.NetherStar.StackAmount", 1);
		getFile().set("Crate Loot.NetherStar.Name", "&fNether Star");
		getFile().set("Crate Loot.NetherStar.Broadcast Win", true);
		getFile().set("Crate Loot.NetherStar.Player Message", true);
		getFile().set("Crate Loot.NetherStar.Chance", 3.0);
		getFile().set("Crate Loot.NetherStar.Prize Name", "nether star");
		getFile().set("Crate Loot.NetherStar.Glow", true);
		getFile().set("Crate Loot.NetherStar.Use Permission", false);
		getFile().set("Crate Loot.NetherStar.Permission", "archoncrates.prize.devhead");
		getFile().set("Crate Loot.NetherStar.Global Win Amount", "*");
		getFile().set("Crate Loot.NetherStar.Player Win Amount", "*");
		commands = new ArrayList<>();
		commands.add("minecraft:give <player> nether_star 1");
		getFile().set("Crate Loot.NetherStar.Commands", commands);
		
		// Emeralds Prize
		getFile().set("Crate Loot.Emeralds.Item ID", 388);
		getFile().set("Crate Loot.Emeralds.Item Data", 0);
		getFile().set("Crate Loot.Emeralds.StackAmount", 3);
		getFile().set("Crate Loot.Emeralds.Name", "&2Emeralds");
		getFile().set("Crate Loot.Emeralds.Broadcast Win", true);
		getFile().set("Crate Loot.Emeralds.Player Message", true);
		getFile().set("Crate Loot.Emeralds.Chance", 5.0);
		getFile().set("Crate Loot.Emeralds.Prize Name", "emeralds");
		getFile().set("Crate Loot.Emeralds.Glow", true);
		getFile().set("Crate Loot.Emeralds.Use Permission", false);
		getFile().set("Crate Loot.Emeralds.Permission", "archoncrates.prize.devhead");
		getFile().set("Crate Loot.Emeralds.Global Win Amount", "*");
		getFile().set("Crate Loot.Emeralds.Player Win Amount", "*");
		commands = new ArrayList<>();
		commands.add("minecraft:give <player> emerald 3");
		getFile().set("Crate Loot.Emeralds.Commands", commands);
		
		ArrayList<String> plugins = new ArrayList<>();
		for(Plugin p : Bukkit.getServer().getPluginManager().getPlugins()) plugins.add(p.getName());
		
		if(plugins.contains("GroupManager")) {
			 	// Rank Prize
				getFile().set("Crate Loot.Rank.Item ID", 340);
				getFile().set("Crate Loot.Rank.Item Data", 0);
				getFile().set("Crate Loot.Rank.StackAmount", 1);
				getFile().set("Crate Loot.Rank.Name", "&aRank");
				getFile().set("Crate Loot.Rank.Broadcast Win", true);
				getFile().set("Crate Loot.Rank.Player Message", true);
				getFile().set("Crate Loot.Rank.Chance", 0.02);
				getFile().set("Crate Loot.Rank.Prize Name", "RANK");
				getFile().set("Crate Loot.Rank.Glow", true);
				getFile().set("Crate Loot.Rank.Use Permission", false);
				getFile().set("Crate Loot.Rank.Permission", "archoncrates.prize.rank");
				getFile().set("Crate Loot.Rank.Global Win Amount", "*");
				getFile().set("Crate Loot.Rank.Player Win Amount", "*");
				commands = new ArrayList<>();
				commands.add("manuadd <player> VIP");
				getFile().set("Crate Loot.Rank.Commands", commands);
				getFile().set("Crate Loot.Rank.Use Sub Commands", true);
				ArrayList<String> groups = new ArrayList<>();
				groups.add("Owner");
				groups.add("Admin");
				getFile().set("Crate Loot.Rank.Ranks To Sub", groups);
				ArrayList<String> subCommands = new ArrayList<>();
				subCommands.add("manuaddsub <player> VIP");
				getFile().set("Crate Loot.Rank.Sub-Commands", subCommands);
		}
		if(plugins.contains("PermissionsEx")) {
		 	// Rank Prize
			getFile().set("Crate Loot.Rank.Item ID", 340);
			getFile().set("Crate Loot.Rank.Item Data", 0);
			getFile().set("Crate Loot.Rank.StackAmount", 1);
			getFile().set("Crate Loot.Rank.Name", "&aRank");
			getFile().set("Crate Loot.Rank.Broadcast Win", true);
			getFile().set("Crate Loot.Rank.Player Message", true);
			getFile().set("Crate Loot.Rank.Chance", 0.02);
			getFile().set("Crate Loot.Rank.Prize Name", "RANK");
			getFile().set("Crate Loot.Rank.Glow", true);
			getFile().set("Crate Loot.Rank.Use Permission", false);
			getFile().set("Crate Loot.Rank.Permission", "archoncrates.prize.rank");
			getFile().set("Crate Loot.Rank.Global Win Amount", "*");
			getFile().set("Crate Loot.Rank.Player Win Amount", "*");
			commands = new ArrayList<>();
			commands.add("pex user <player> group set VIP");
			getFile().set("Crate Loot.Rank.Commands", commands);
			getFile().set("Crate Loot.Rank.Use Sub Commands", true);
			ArrayList<String> groups = new ArrayList<>();
			groups.add("Owner");
			groups.add("Admin");
			getFile().set("Crate Loot.Rank.Ranks To Sub", groups);
			ArrayList<String> subCommands = new ArrayList<>();
			subCommands.add("pex user <player> group add VIP");
			getFile().set("Crate Loot.Rank.Sub-Commands", subCommands);
		}

		getFile().set("file version", "0.0.0.1");
		
		save();
		setHeader();
	}

	@Override
	public void setHeader() {
		fileconfig.options().header(""
				+ "##################################################################################################################################################################### #\n"
				+ "                                                                                                                                                                      #\n"
				+ "                                                               ArchonCrates Crate Loot Configuration                                                                  #\n"
				+ "                                                                                                                                                                      #\n"
				+ " Crate Loot:                                                                                                                                                          #\n"
				+ "   Diamonds:                                          - Loot id which is used to define the loot for the keys (Can't have any the same)                               #\n"
				+ "     Item ID: 264                                     - The id of the display item which will display in the crate GUI                                                #\n"
				+ "     Item Data: 0                                     - The data value of the item in the display of the crate GUI                                                    #\n"
				+ "     StackAmount: 10                                  - The stack size of the display item in the crate GUI                                                           #\n"
				+ "     Name: '&3Diamonds'                               - The display name of the item in the crate GUI                                                                 #\n"
				+ "     Broadcast Win: true                              - If true it will broadcast the win message to the whole server                                                 #\n"
				+ "     Player Message: true                             - If true it will send the player a message                                                                     #\n"
				+ "     Chance: 5.0                                      - The chance of the prize being won (MIN value: 0.01 - MAX value: 9999.99)                                      #\n"
				+ "     Prize Name: 10 diamonds                          - The name used in the win messages                                                                             #\n"
				+ "     Glow: true                                       - If true the display item will glow in the crate GUI                                                           #\n"
				+ "     Use Permission: false                            - If true it will use the permission for players to win the prize                                               #\n"
				+ "     Permission: archoncrates.prize.diamonds          - The permission used to check if players can win the prize                                                     #\n"
				+ "     Win Amount: '*'                                  - The amount of times a prize can be won ('*' means unlimited times)                                            #\n"
				+ "     Commands:                                        - A list of commands which will be run when a player wins the prize                                             #\n"
				+ "     - minecraft:give <player> diamond 10                                                                                                                             #\n"
				+ "                                                                                                                                                                      #\n"
				+ "                                                                                                                                                                      #\n"
				+ "                                                                                                                                                                      #\n"
				+ "##################################################################################################################################################################### #\n");
		save();
	}

}
