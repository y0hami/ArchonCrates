package com.HamiStudios.ArchonCrates.API.Events;

import com.HamiStudios.ArchonCrates.API.Objects.ACPlayer;
import com.HamiStudios.ArchonCrates.API.Objects.Crate;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class OnCrateRemoved extends Event {

	private static final HandlerList handlers = new HandlerList();

	private ACPlayer player;
	private Crate crate;

	public OnCrateRemoved(ACPlayer player, Crate crate) {
		this.player = player;
		this.crate = crate;
	}

	public ACPlayer getRemover() { return this.player; }

	public Crate getCrate() { return this.crate; }


	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

}