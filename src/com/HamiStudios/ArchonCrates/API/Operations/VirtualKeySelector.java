package com.HamiStudios.ArchonCrates.API.Operations;

import com.HamiStudios.ArchonCrates.API.Objects.ACPlayer;
import org.bukkit.block.Block;

public class VirtualKeySelector {

	private ACPlayer player;
	private Block block;

	public VirtualKeySelector(ACPlayer player, Block block) {
		this.player = player;
		this.block = block;
	}

	public ACPlayer getPlayer() { return this.player; }

	public Block getBlock() { return this.block; }

}
