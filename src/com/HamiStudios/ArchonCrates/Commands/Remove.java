package com.HamiStudios.ArchonCrates.Commands;

import java.util.HashSet;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.HamiStudios.ArchonCrates.API.Objects.Exceptions.InvalidCrateInput;
import com.HamiStudios.ArchonCrates.Util.CrateFinder;
import com.HamiStudios.ArchonCrates.Util.LanguageType;

public class Remove {

	@SuppressWarnings("deprecation")
	public static void run(Player player) {
		
		Block block = (Block) player.getTargetBlock((HashSet<Byte>) null, 100);
		Location loc = block.getLocation();
		String crateid = CrateFinder.findCrateLocationId(block.getTypeId(), loc.getX(), loc.getY(), block.getZ(), block.getWorld());
		if(crateid != null) {
			String crateType = CrateFinder.findCrateType(block.getTypeId(), loc.getX(), loc.getY(), block.getZ(), block.getWorld());
			com.HamiStudios.ArchonCrates.API.Objects.Crate crate = null;
			try {
				crate = new com.HamiStudios.ArchonCrates.API.Objects.Crate(crateType);
			} catch (InvalidCrateInput e) {
				e.log(crateType);
				e.writeToFile(crateType);
			}
			if(crate == null) return;
			crate.remove(block.getTypeId(), loc.getX(), loc.getY(), block.getZ(), block.getWorld());
			player.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_REMOVE_DONE.toString(true));
		}
		else{
			player.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_REMOVE_NOT_CRATE.toString(true));
		}
	}
	
}
