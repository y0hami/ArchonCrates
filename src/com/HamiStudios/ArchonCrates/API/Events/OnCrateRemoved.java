package com.HamiStudios.ArchonCrates.API.Events;

import com.HamiStudios.ArchonCrates.API.Objects.ACPlayer;
import com.HamiStudios.ArchonCrates.API.Objects.Crate;
import com.HamiStudios.ArchonCrates.API.Objects.VirtualCrate;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class OnCrateRemoved extends Event {

	private static final HandlerList handlers = new HandlerList();

	private ACPlayer player;
	private Crate crate;
	private VirtualCrate virtualCrate;
	private boolean isVirtual;

	public OnCrateRemoved(ACPlayer player, Crate crate) {
		this.player = player;
		this.crate = crate;
		this.isVirtual = false;
	}

	public OnCrateRemoved(ACPlayer player, VirtualCrate virtualCrate) {
		this.player = player;
		this.virtualCrate = virtualCrate;
		this.isVirtual = true;
	}

	public ACPlayer getRemover() { return this.player; }

	public Crate getCrate() { return this.crate; }

	public boolean isVirtual() { return this.isVirtual; }


	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

}