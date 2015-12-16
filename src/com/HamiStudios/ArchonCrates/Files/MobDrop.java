package com.HamiStudios.ArchonCrates.Files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class MobDrop implements FileInterface {
	
	private File file;
	private FileConfiguration fileconfig;
	
	public MobDrop() {
		file = new File("plugins/ArchonCrates/Drops/mob drops.yml");
		fileconfig = YamlConfiguration.loadConfiguration(file);
	}

	@Override
	public FileConfiguration getFile() {
		return fileconfig;
	}

	@Override
	public void save() {
		try{ 
			fileconfig.save(file);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void reload() {
		file = new File("plugins/ArchonCrates/Drops/mob drops.yml");
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
		if(new File("plugins/ArchonCrates/Drops/mob drops.yml").exists()) return true;
		return false;
	}

	@Override
	public void create() {
		ArrayList<String> keysThatDrop = new ArrayList<>();
		
		fileconfig.set("Enabled", false);
		
		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// BAT
		fileconfig.set("Mobs that drop.BAT.enabled", true);
		fileconfig.set("Mobs that drop.BAT.chance", 10.0);
		fileconfig.set("Mobs that drop.BAT.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// CHICKEN 
		fileconfig.set("Mobs that drop.CHICKEN.enabled", true);
		fileconfig.set("Mobs that drop.CHICKEN.chance", 10.0);
		fileconfig.set("Mobs that drop.CHICKEN.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// COW
		fileconfig.set("Mobs that drop.COW.enabled", true);
		fileconfig.set("Mobs that drop.COW.chance", 10.0);
		fileconfig.set("Mobs that drop.COW.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// MOOSHROOM_COW
		fileconfig.set("Mobs that drop.MOOSHROOM_COW.enabled", true);
		fileconfig.set("Mobs that drop.MOOSHROOM_COW.chance", 10.0);
		fileconfig.set("Mobs that drop.MOOSHROOM_COW.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// PIG
		fileconfig.set("Mobs that drop.PIG.enabled", true);
		fileconfig.set("Mobs that drop.PIG.chance", 10.0);
		fileconfig.set("Mobs that drop.PIG.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// RABBIT
		fileconfig.set("Mobs that drop.RABBIT.enabled", true);
		fileconfig.set("Mobs that drop.RABBIT.chance", 10.0);
		fileconfig.set("Mobs that drop.RABBIT.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// SHEEP
		fileconfig.set("Mobs that drop.SHEEP.enabled", true);
		fileconfig.set("Mobs that drop.SHEEP.chance", 10.0);
		fileconfig.set("Mobs that drop.SHEEP.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// SQUID
		fileconfig.set("Mobs that drop.SQUID.enabled", true);
		fileconfig.set("Mobs that drop.SQUID.chance", 10.0);
		fileconfig.set("Mobs that drop.SQUID.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// VILLAGER
		fileconfig.set("Mobs that drop.VILLAGER.enabled", true);
		fileconfig.set("Mobs that drop.VILLAGER.chance", 10.0);
		fileconfig.set("Mobs that drop.VILLAGER.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// CAVE_SPIDER
		fileconfig.set("Mobs that drop.CAVE_SPIDER.enabled", true);
		fileconfig.set("Mobs that drop.CAVE_SPIDER.chance", 10.0);
		fileconfig.set("Mobs that drop.CAVE_SPIDER.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// ENDERMAN
		fileconfig.set("Mobs that drop.ENDERMAN.enabled", true);
		fileconfig.set("Mobs that drop.ENDERMAN.chance", 10.0);
		fileconfig.set("Mobs that drop.ENDERMAN.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// SPIDER
		fileconfig.set("Mobs that drop.SPIDER.enabled", true);
		fileconfig.set("Mobs that drop.SPIDER.chance", 10.0);
		fileconfig.set("Mobs that drop.SPIDER.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// PIG_ZOMBIE
		fileconfig.set("Mobs that drop.PIG_ZOMBIE.enabled", true);
		fileconfig.set("Mobs that drop.PIG_ZOMBIE.chance", 10.0);
		fileconfig.set("Mobs that drop.PIG_ZOMBIE.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// BLAZE
		fileconfig.set("Mobs that drop.BLAZE.enabled", true);
		fileconfig.set("Mobs that drop.BLAZE.chance", 10.0);
		fileconfig.set("Mobs that drop.BLAZE.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// CREEPER
		fileconfig.set("Mobs that drop.CREEPER.enabled", true);
		fileconfig.set("Mobs that drop.CREEPER.chance", 10.0);
		fileconfig.set("Mobs that drop.CREEPER.keysThatDrop", keysThatDrop);
		
		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// ENDERMITE
		fileconfig.set("Mobs that drop.ENDERMITE.enabled", true);
		fileconfig.set("Mobs that drop.ENDERMITE.chance", 10.0);
		fileconfig.set("Mobs that drop.ENDERMITE.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// GHAST
		fileconfig.set("Mobs that drop.GHAST.enabled", true);
		fileconfig.set("Mobs that drop.GHAST.chance", 10.0);
		fileconfig.set("Mobs that drop.GHAST.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// GUARDIAN
		fileconfig.set("Mobs that drop.GUARDIAN.enabled", true);
		fileconfig.set("Mobs that drop.GUARDIAN.chance", 10.0);
		fileconfig.set("Mobs that drop.GUARDIAN.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// MAGMA_CUBE
		fileconfig.set("Mobs that drop.MAGMA_CUBE.enabled", true);
		fileconfig.set("Mobs that drop.MAGMA_CUBE.chance", 10.0);
		fileconfig.set("Mobs that drop.MAGMA_CUBE.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// SILVERFISH
		fileconfig.set("Mobs that drop.SILVERFISH.enabled", true);
		fileconfig.set("Mobs that drop.SILVERFISH.chance", 10.0);
		fileconfig.set("Mobs that drop.SILVERFISH.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// SKELETON
		fileconfig.set("Mobs that drop.SKELETON.enabled", true);
		fileconfig.set("Mobs that drop.SKELETON.chance", 10.0);
		fileconfig.set("Mobs that drop.SKELETON.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// SLIME
		fileconfig.set("Mobs that drop.SLIME.enabled", true);
		fileconfig.set("Mobs that drop.SLIME.chance", 10.0);
		fileconfig.set("Mobs that drop.SLIME.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// WITCH
		fileconfig.set("Mobs that drop.WITCH.enabled", true);
		fileconfig.set("Mobs that drop.WITCH.chance", 10.0);
		fileconfig.set("Mobs that drop.WITCH.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// ZOMBIE
		fileconfig.set("Mobs that drop.ZOMBIE.enabled", true);
		fileconfig.set("Mobs that drop.ZOMBIE.chance", 10.0);
		fileconfig.set("Mobs that drop.ZOMBIE.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// HORSE
		fileconfig.set("Mobs that drop.HORSE.enabled", true);
		fileconfig.set("Mobs that drop.HORSE.chance", 10.0);
		fileconfig.set("Mobs that drop.HORSE.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// OCELOT
		fileconfig.set("Mobs that drop.OCELOT.enabled", true);
		fileconfig.set("Mobs that drop.OCELOT.chance", 10.0);
		fileconfig.set("Mobs that drop.OCELOT.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// WOLF
		fileconfig.set("Mobs that drop.WOLF.enabled", true);
		fileconfig.set("Mobs that drop.WOLF.chance", 10.0);
		fileconfig.set("Mobs that drop.WOLF.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// IRON_GOLEM
		fileconfig.set("Mobs that drop.IRON_GOLEM.enabled", true);
		fileconfig.set("Mobs that drop.IRON_GOLEM.chance", 10.0);
		fileconfig.set("Mobs that drop.IRON_GOLEM.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// SNOWMAN
		fileconfig.set("Mobs that drop.SNOWMAN.enabled", true);
		fileconfig.set("Mobs that drop.SNOWMAN.chance", 10.0);
		fileconfig.set("Mobs that drop.SNOWMAN.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// ENDER_DRAGON
		fileconfig.set("Mobs that drop.ENDER_DRAGON.enabled", true);
		fileconfig.set("Mobs that drop.ENDER_DRAGON.chance", 10.0);
		fileconfig.set("Mobs that drop.ENDER_DRAGON.keysThatDrop", keysThatDrop);

		keysThatDrop = new ArrayList<>();
		keysThatDrop.add("default");
		keysThatDrop.add("golden");
		// WITHER
		fileconfig.set("Mobs that drop.WITHER.enabled", true);
		fileconfig.set("Mobs that drop.WITHER.chance", 10.0);
		fileconfig.set("Mobs that drop.WITHER.keysThatDrop", keysThatDrop);

		getFile().set("file version", "0.0.0.1");
		
		save();
		setHeader();
	}

	@Override
	public void setHeader() {
		fileconfig.options().header(""
				+ "############################################################################################################## #\n"
				+ "                                                                                                               #\n"
				+ "                                        Mob Drop Configuration File                                            #\n"
				+ "                                                                                                               #\n"
				+ " Enabled: true               - If true it will drop keys on entity deaths                                      #\n"
				+ " Mobs that drop:             - List of mobs in Minecraft                                                       #\n"
				+ "   BAT:                      - Entity name                                                                     #\n"
				+ "     enabled: true           - If true this entity will drop keys                                              #\n"
				+ "     chance: 10.0            - The chance a key will drop   (MAX 100.0 MIN 0.01)                               #\n"
				+ "     keysThatDrop:           - A list of keys that can drop                                                    #\n"
				+ "     - default                                                                                                 #\n"
				+ "     - golden                                                                                                  #\n"
				+ "                                                                                                               #\n"
				+ "                                                                                                               #\n"
				+ "                                                                                                               #\n"
				+ "############################################################################################################## #\n");
		save();
	}
	
}
