package com.HamiStudios.ArchonCrates.Files;

import com.HamiStudios.ArchonCrates.API.Enums.Files;
import com.HamiStudios.ArchonCrates.API.Exceptions.NoValueException;
import com.HamiStudios.ArchonCrates.API.Libs.FileInterface;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Prizes implements FileInterface {
	
	// Instances of File and FileConfiguration
	private File file;
	private FileConfiguration fileconfig;
	private String filePath = "plugins/ArchonCrates/prizes.yml";
	
	
	// Crates file constructor
	public Prizes() {
		// Crates the instances of the File and FileConfiguration
		this.file = new File(this.filePath);
		this.fileconfig = YamlConfiguration.loadConfiguration(file);
	}
	
	// Get the File instance
	@Override
	public File getFile() {
		return this.file;
	}
	
	// Get the FileConfiguration instance
	@Override
	public FileConfiguration getFileConfiguration() {
		return this.fileconfig;
	}

	@Override
	public boolean save() {
		try {
			this.fileconfig.save(this.file);
			return true;
		} catch(IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean reload() {
		try {
			this.file = new File(this.filePath);
			this.fileconfig = YamlConfiguration.loadConfiguration(this.file);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean set(String path, Object value) {
		try {
			this.fileconfig.set(path, value);
			this.save();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Object get(String path) throws NoValueException {
		Object returnValue = this.fileconfig.get(path);
		if(returnValue == null) {
			throw new NoValueException(path, Files.PRIZES);
		}
		return returnValue;
	}

	@Override
	public boolean exists() {
		if(new File(this.filePath).exists()) return true;
		return false;
	}

	@Override
	public boolean create() {
		try {
			// Diamonds Prize
			this.set("Prizes.Diamonds.name", "&3Diamonds");
			this.set("Prizes.Diamonds.item.ID", 264);
			this.set("Prizes.Diamonds.item.data", 0);
			this.set("Prizes.Diamonds.item.stackSize", 10);
			this.set("Prizes.Diamonds.chance", 3.0);
			this.set("Prizes.Diamonds.broadcast", true);
			this.set("Prizes.Diamonds.glow", true);
			this.set("Prizes.Diamonds.permission.use", false);
			this.set("Prizes.Diamonds.permission.value", "archoncrates.prize.diamonds");
			this.set("Prizes.Diamonds.winAmount.global", "*");
			this.set("Prizes.Diamonds.winAmount.player", "*");
			
			ArrayList<String> commands = new ArrayList<>();
			commands.add("minecraft:give <player> diamond 10");
			
			this.set("Prizes.Diamonds.commands", commands);
			
			
			// Food Prize
			this.set("Prizes.Food.name", "&dFood");
			this.set("Prizes.Food.item.ID", 364);
			this.set("Prizes.Food.item.data", 0);
			this.set("Prizes.Food.item.stackSize", 16);
			this.set("Prizes.Food.chance", 30.0);
			this.set("Prizes.Food.broadcast", false);
			this.set("Prizes.Food.glow", false);
			this.set("Prizes.Food.permission.use", false);
			this.set("Prizes.Food.permission.value", "archoncrates.prize.food");
			this.set("Prizes.Food.winAmount.global", "*");
			this.set("Prizes.Food.winAmount.player", "*");
			
			commands = new ArrayList<>();
			commands.add("minecraft:give <player> cooked_beef 16");
			commands.add("minecraft:give <player> cooked_chicken 16");
			commands.add("minecraft:give <player> cooked_porkchop 16");
			
			this.set("Prizes.Food.commands", commands);
			
			
			// Sword Prize
			this.set("Prizes.Sword.name", "&bSword");
			this.set("Prizes.Sword.item.ID", 276);
			this.set("Prizes.Sword.item.data", 0);
			this.set("Prizes.Sword.item.stackSize", 1);
			this.set("Prizes.Sword.chance", 12.0);
			this.set("Prizes.Sword.broadcast", false);
			this.set("Prizes.Sword.glow", true);
			this.set("Prizes.Sword.permission.use", false);
			this.set("Prizes.Sword.permission.value", "archoncrates.prize.sword");
			this.set("Prizes.Sword.winAmount.global", "*");
			this.set("Prizes.Sword.winAmount.player", "*");
			
			commands = new ArrayList<>();
			commands.add("minecraft:give <player> diamond_sword 1");
			
			this.set("Prizes.Sword.commands", commands);
			
			
			// Gold Prize
			this.set("Prizes.Gold.name", "&6Gold");
			this.set("Prizes.Gold.item.ID", 266);
			this.set("Prizes.Gold.item.data", 0);
			this.set("Prizes.Gold.item.stackSize", 32);
			this.set("Prizes.Gold.chance", 15.0);
			this.set("Prizes.Gold.broadcast", true);
			this.set("Prizes.Gold.glow", true);
			this.set("Prizes.Gold.permission.use", false);
			this.set("Prizes.Gold.permission.value", "archoncrates.prize.gold");
			this.set("Prizes.Gold.winAmount.global", "*");
			this.set("Prizes.Gold.winAmount.player", "*");
			
			commands = new ArrayList<>();
			commands.add("minecraft:give <player> gold_ingot 32");
			
			this.set("Prizes.Gold.commands", commands);
			
			
			// Tools Prize
			this.set("Prizes.Tools.name", "&7Tools");
			this.set("Prizes.Tools.item.ID", 257);
			this.set("Prizes.Tools.item.data", 0);
			this.set("Prizes.Tools.item.stackSize", 1);
			this.set("Prizes.Tools.chance", 20.0);
			this.set("Prizes.Tools.broadcast", false);
			this.set("Prizes.Tools.glow", false);
			this.set("Prizes.Tools.permission.use", false);
			this.set("Prizes.Tools.permission.value", "archoncrates.prize.tools");
			this.set("Prizes.Tools.winAmount.global", "*");
			this.set("Prizes.Tools.winAmount.player", "*");
			
			commands = new ArrayList<>();
			commands.add("minecraft:give <player> iron_pickaxe 1");
			commands.add("minecraft:give <player> iron_axe 1");
			commands.add("minecraft:give <player> iron_shovel 1");
			
			this.set("Prizes.Tools.commands", commands);
			
			
			// God Apple Prize
			this.set("Prizes.GodApple.name", "&6God Apple");
			this.set("Prizes.GodApple.item.ID", 322);
			this.set("Prizes.GodApple.item.data", 1);
			this.set("Prizes.GodApple.item.stackSize", 1);
			this.set("Prizes.GodApple.chance", 0.8);
			this.set("Prizes.GodApple.broadcast", true);
			this.set("Prizes.GodApple.glow", true);
			this.set("Prizes.GodApple.permission.use", false);
			this.set("Prizes.GodApple.permission.value", "archoncrates.prize.godapple");
			this.set("Prizes.GodApple.winAmount.global", "*");
			this.set("Prizes.GodApple.winAmount.player", "*");
			
			commands = new ArrayList<>();
			commands.add("minecraft:give <player> golden_apple 1 1");
			
			this.set("Prizes.GodApple.commands", commands);
			
			
			// Crate Key Prize
			this.set("Prizes.CrateKey.name", "&aCrate Key");
			this.set("Prizes.CrateKey.item.ID", 131);
			this.set("Prizes.CrateKey.item.data", 0);
			this.set("Prizes.CrateKey.item.stackSize", 1);
			this.set("Prizes.CrateKey.chance", 5.0);
			this.set("Prizes.CrateKey.broadcast", true);
			this.set("Prizes.CrateKey.glow", true);
			this.set("Prizes.CrateKey.permission.use", false);
			this.set("Prizes.CrateKey.permission.value", "archoncrates.prize.cratekey");
			this.set("Prizes.CrateKey.winAmount.global", "*");
			this.set("Prizes.CrateKey.winAmount.player", "*");
			
			commands = new ArrayList<>();
			commands.add("archoncrates key <player> 1 default physical");
			
			this.set("Prizes.CrateKey.commands", commands);
			
			
			// Developer Head Prize
			this.set("Prizes.DevHead.name", "&cDeveloper Head");
			this.set("Prizes.DevHead.item.ID", 397);
			this.set("Prizes.DevHead.item.data", 3);
			this.set("Prizes.DevHead.item.stackSize", 1);
			this.set("Prizes.DevHead.chance", 10.0);
			this.set("Prizes.DevHead.broadcast", false);
			this.set("Prizes.DevHead.glow", true);
			this.set("Prizes.DevHead.permission.use", false);
			this.set("Prizes.DevHead.permission.value", "archoncrates.prize.devhead");
			this.set("Prizes.DevHead.winAmount.global", "*");
			this.set("Prizes.DevHead.winAmount.player", "*");
			
			commands = new ArrayList<>();
			commands.add("minecraft:give <player> skull 1 3 {SkullOwner:\"hammy2899\",Unbreakable:1}");
			
			this.set("Prizes.DevHead.commands", commands);
			
			
			// Nether Star Prize
			this.set("Prizes.NetherStar.name", "&fNether Star");
			this.set("Prizes.NetherStar.item.ID", 399);
			this.set("Prizes.NetherStar.item.data", 0);
			this.set("Prizes.NetherStar.item.stackSize", 1);
			this.set("Prizes.NetherStar.chance", 6.0);
			this.set("Prizes.NetherStar.broadcast", true);
			this.set("Prizes.NetherStar.glow", true);
			this.set("Prizes.NetherStar.permission.use", false);
			this.set("Prizes.NetherStar.permission.value", "archoncrates.prize.netherstar");
			this.set("Prizes.NetherStar.winAmount.global", "*");
			this.set("Prizes.NetherStar.winAmount.player", "*");
			
			commands = new ArrayList<>();
			commands.add("minecraft:give <player> nether_star 1");
			
			this.set("Prizes.NetherStar.commands", commands);
			
			
			// Emeralds Prize
			this.set("Prizes.Emeralds.name", "&aEmeralds");
			this.set("Prizes.Emeralds.item.ID", 388);
			this.set("Prizes.Emeralds.item.data", 0);
			this.set("Prizes.Emeralds.item.stackSize", 3);
			this.set("Prizes.Emeralds.chance", 4.0);
			this.set("Prizes.Emeralds.broadcast", true);
			this.set("Prizes.Emeralds.glow", true);
			this.set("Prizes.Emeralds.permission.use", false);
			this.set("Prizes.Emeralds.permission.value", "archoncrates.prize.emeralds");
			this.set("Prizes.Emeralds.winAmount.global", "*");
			this.set("Prizes.Emeralds.winAmount.player", "*");
			
			commands = new ArrayList<>();
			commands.add("minecraft:give <player> emerald 3");
			
			this.set("Prizes.Emeralds.commands", commands);


			// Beacon Prize
			this.set("Prizes.Beacon.name", "&3Beacon");
			this.set("Prizes.Beacon.item.ID", 138);
			this.set("Prizes.Beacon.item.data", 0);
			this.set("Prizes.Beacon.item.stackSize", 1);
			this.set("Prizes.Beacon.chance", 1.0);
			this.set("Prizes.Beacon.broadcast", true);
			this.set("Prizes.Beacon.glow", true);
			this.set("Prizes.Beacon.permission.use", false);
			this.set("Prizes.Beacon.permission.value", "archoncrates.prize.beacon");
			this.set("Prizes.Beacon.winAmount.global", "*");
			this.set("Prizes.Beacon.winAmount.player", "*");

			commands = new ArrayList<>();
			commands.add("minecraft:give <player> beacon 3");

			this.set("Prizes.Beacon.commands", commands);
			
			this.save();

			this.setHeader();

			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean setHeader() {
		this.fileconfig.options().header(
				"\n " +
				"Need help configuring? Visit the ArchonCrates documentation https://archoncrates.com/docs/" +
				Bukkit.getPluginManager().getPlugin("ArchonCrates").getDescription().getVersion().replaceAll("\\.", "-") +
				"/files/prizes" +
				"\n \n"
			);

		this.save();

		return true;
	}

}
