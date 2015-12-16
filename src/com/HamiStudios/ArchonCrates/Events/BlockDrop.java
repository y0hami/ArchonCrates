package com.HamiStudios.ArchonCrates.Events;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.HamiStudios.ArchonCrates.Main;
import com.HamiStudios.ArchonCrates.API.Events.OnBlockDropKey;
import com.HamiStudios.ArchonCrates.API.Objects.Key;
import com.HamiStudios.ArchonCrates.Files.FileHandler;
import com.HamiStudios.ArchonCrates.Util.FileType;

public class BlockDrop implements Listener {
	
	private Main main;
	public BlockDrop(Main main) {
		this.main = main;
		this.main.getServer().getPluginManager().registerEvents(this, this.main);
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if(event.getBlock().getType() != null) {
			if(FileHandler.getFile(FileType.BLOCK_DROP).contains("Blocks that drop." + event.getBlock().getType().toString())) {
				
				if(FileHandler.getFile(FileType.BLOCK_DROP).getBoolean("Blocks that drop." + event.getBlock().getType().toString() + ".enabled")) {
					Block block = event.getBlock();
					double chance = FileHandler.getFile(FileType.BLOCK_DROP).getDouble("Blocks that drop." + block.getType().toString() + ".chance");
					Random random = new Random();
					double randomDouble = 0+100*random.nextDouble();
					DecimalFormat df = new DecimalFormat("####.##");
					String value = df.format(randomDouble);
					double chanceValue = Double.parseDouble(value);
					if(chance >= chanceValue) {
						ArrayList<String> keys = new ArrayList<>();
						for(String s : FileHandler.getFile(FileType.BLOCK_DROP).getStringList("Blocks that drop." + block.getType().toString() + ".keysThatDrop")) keys.add(s);
						int max = keys.size();
						int randomNum = (int) (Math.random() * (max));
						String keyType = keys.get(randomNum);
						Key key = new Key(keyType);
						block.getLocation().getWorld().dropItemNaturally(block.getLocation(), key.getItem());
						this.main.getServer().getPluginManager().callEvent(new OnBlockDropKey(block, key, chanceValue));
					}
				}
				
			}
		}
	}

}
