package com.HamiStudios.ArchonCrates.API.Operations;

import com.HamiStudios.ArchonCrates.API.Objects.ACPlayer;
import org.bukkit.entity.Player;

public class GiveKeyOperation {

	private ACPlayer player;
	private Player giveTo;
	private boolean giveAll;
	private int amount;

	public GiveKeyOperation(ACPlayer player, Player giveTo, boolean giveAll, int amount) {
		this.player = player;
		this.giveTo = giveTo;
		this.giveAll = giveAll;
		this.amount = amount;
	}

	public ACPlayer getPlayer() { return this.player; }

	public Player getGiveTo() { return this.giveTo; }

	public boolean giveAll() { return this.giveAll; }

	public int getAmount() { return this.amount; }

}
