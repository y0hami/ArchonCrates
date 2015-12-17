package com.HamiStudios.ArchonCrates.Events;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.HamiStudios.ArchonCrates.Main;
import com.HamiStudios.ArchonCrates.API.Events.OnCrateRemove;
import com.HamiStudios.ArchonCrates.API.Objects.Crate;
import com.HamiStudios.ArchonCrates.API.Objects.VirtualCrate;
import com.HamiStudios.ArchonCrates.Util.ACPermission;
import com.HamiStudios.ArchonCrates.Util.CrateFinder;
import com.HamiStudios.ArchonCrates.Util.LanguageType;

public class BlockBreakEvent implements Listener {

	private Main main;
	public BlockBreakEvent(Main main) {
		this.main = main;
		this.main.getServer().getPluginManager().registerEvents(this, this.main);
	}
	
	@EventHandler
	public void onBlockBreak(org.bukkit.event.block.BlockBreakEvent event) {
		
		@SuppressWarnings("deprecation")
		int blockId = event.getBlock().getTypeId();
		double x = event.getBlock().getX();
		double y = event.getBlock().getY();
		double z = event.getBlock().getZ();
		World world = event.getBlock().getWorld();
		String crateType = CrateFinder.findCrateType(blockId, x, y, z, world);
		String crateId = CrateFinder.findVCrateLocationId(blockId, x, y, z, world);
		
		if(crateType != null) {
			event.setCancelled(true);
			if(!event.getPlayer().isSneaking()) {
				event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.BREAK_CRATE_DENY.toString(true));
			}
			else{
				if(event.getPlayer().hasPermission(ACPermission.BREAK_CRATE.toString())) {
					event.setCancelled(false);
					Crate crate = new Crate(crateType);
					crate.remove(blockId, x, y, z, world);
					this.main.getServer().getPluginManager().callEvent(new OnCrateRemove(event.getPlayer(), crate, false));
					event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.BREAK_CRATE_ALLOW.toString(true));
				}
				else{
					event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.NO_PERMISSION.toString(true));
				}
			}
		}
		
		if(crateId != null) {
			event.setCancelled(true);
			if(!event.getPlayer().isSneaking()) {
				event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.BREAK_CRATE_DENY.toString(true));
			}
			else{
				if(event.getPlayer().hasPermission(ACPermission.BREAK_CRATE.toString())) {
					event.setCancelled(false);
					VirtualCrate vcrate = new VirtualCrate();
					vcrate.remove(blockId, x, y, z, world);
					this.main.getServer().getPluginManager().callEvent(new OnCrateRemove(event.getPlayer(), vcrate, true));
					event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.BREAK_CRATE_ALLOW.toString(true));
				}
				else{
					event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.NO_PERMISSION.toString(true));
				}
			}
		}
		
	}
	
}
