package com.HamiStudios.ArchonCrates.Commands;

import org.bukkit.entity.Player;

public class Crate {

	public static void run(Player player, String crateType, String keyType) {
		com.HamiStudios.ArchonCrates.Tasks.Crate crate = new com.HamiStudios.ArchonCrates.Tasks.Crate();
		crate.open(player, crateType, keyType, true, null);
	}
 	
}
