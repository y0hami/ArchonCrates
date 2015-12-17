package com.HamiStudios.ArchonCrates.Events;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import com.HamiStudios.ArchonCrates.Main;
import com.HamiStudios.ArchonCrates.API.Events.OnCrateCreate;
import com.HamiStudios.ArchonCrates.API.Objects.Crate;
import com.HamiStudios.ArchonCrates.API.Objects.VirtualCrate;
import com.HamiStudios.ArchonCrates.Files.FileHandler;
import com.HamiStudios.ArchonCrates.Util.FileType;
import com.HamiStudios.ArchonCrates.Util.LanguageType;

public class BlockPlace implements Listener {

	private Main main;
	public BlockPlace(Main main) {
		this.main = main;
		this.main.getServer().getPluginManager().registerEvents(this, this.main);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		
		if(event.getItemInHand().hasItemMeta()) {
			
			ItemStack block = event.getItemInHand();

			// Normal
			ArrayList<String> blockIDs = new ArrayList<>();
			for(String s : FileHandler.getSection(FileType.CRATES, "Crates").getKeys(false)) {
				blockIDs.add(FileHandler.getFile(FileType.CRATES).getInt("Crates." + s + ".blockID")+"");
			}
			
			if(blockIDs.contains(block.getType().getId()+"")) {
				if(block.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Crate Block")) {
					if(!event.isCancelled()) {
						String crateType = block.getItemMeta().getLore().toString().split("'")[1];
						Crate crate = new Crate(crateType);
						crate.createNew(event.getBlock().getLocation());
						this.main.getServer().getPluginManager().callEvent(new OnCrateCreate(event.getPlayer(), crate, false));
						event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.CREATE_CRATE.toString(true).replaceAll("<crateType>", crate.getCrateID()));
					}
				}
			}
			
			// Virtual
			int vblockID = FileHandler.getFile(FileType.VIRTUAL_CRATES).getInt("Virtual Crates.blockID");
			
			if(vblockID == block.getType().getId()) {
				if(block.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Virtual Crate Block")) {
					if(!event.isCancelled()) {
						VirtualCrate vcrate = new VirtualCrate();
						vcrate.createNew(event.getBlock().getLocation());
						this.main.getServer().getPluginManager().callEvent(new OnCrateCreate(event.getPlayer(), vcrate, true));
						event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.CREATE_VIRTUAL_CRATE.toString(true));
					}
				}
			}
			
		}
		
	}
	
}