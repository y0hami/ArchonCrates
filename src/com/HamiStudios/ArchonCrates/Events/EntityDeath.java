package com.HamiStudios.ArchonCrates.Events;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.HamiStudios.ArchonCrates.Main;
import com.HamiStudios.ArchonCrates.API.Events.OnMobKeyDrop;
import com.HamiStudios.ArchonCrates.API.Objects.Key;
import com.HamiStudios.ArchonCrates.Files.FileHandler;
import com.HamiStudios.ArchonCrates.Util.FileType;

public class EntityDeath implements Listener {

	private Main main;
	public EntityDeath(Main main) {
		this.main = main;
		this.main.getServer().getPluginManager().registerEvents(this, this.main);
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		if((event.getEntity() instanceof LivingEntity) && !(event.getEntity() instanceof Player)) {
			if(FileHandler.getFile(FileType.MOB_DROP).getBoolean("Enabled")) {
				Entity entity = event.getEntity();
				if(FileHandler.getFile(FileType.MOB_DROP).contains("Mobs that drop." + entity.getType().toString())) {
					if(FileHandler.getFile(FileType.MOB_DROP).getBoolean("Mobs that drop." + entity.getType().toString() + ".enabled")) {
						LivingEntity liveEnt = (LivingEntity) entity;
						if(liveEnt.getKiller() instanceof Player) {
							double chance = FileHandler.getFile(FileType.MOB_DROP).getDouble("Mobs that drop." + entity.getType().toString() + ".chance");
							Random random = new Random();
							double randomDouble = 0+100*random.nextDouble();
							DecimalFormat df = new DecimalFormat("####.##");
							String value = df.format(randomDouble);
							double chanceValue = Double.parseDouble(value);
							if(chance >= chanceValue) {
								ArrayList<String> keys = new ArrayList<>();
								for(String s : FileHandler.getFile(FileType.MOB_DROP).getStringList("Mobs that drop." + entity.getType().toString() + ".keysThatDrop")) keys.add(s);
								int max = keys.size();
								int randomNum = (int) (Math.random() * (max));
								String keyType = keys.get(randomNum);
								Key key = new Key(keyType);
								entity.getLocation().getWorld().dropItemNaturally(entity.getLocation(), key.getItem());
								this.main.getServer().getPluginManager().callEvent(new OnMobKeyDrop(entity, key, chanceValue));
							}
						}
					}
				}
			}
		}
	}
	
}
