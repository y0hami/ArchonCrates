package com.HamiStudios.ArchonCrates.API.Events;

import com.HamiStudios.ArchonCrates.API.Objects.ACPlayer;
import com.HamiStudios.ArchonCrates.API.Objects.Crate;
import com.HamiStudios.ArchonCrates.API.Objects.VirtualCrate;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class OnCrateCreated extends Event {

	private static final HandlerList handlers = new HandlerList();

	private ACPlayer player;
	private Crate crate;
	private VirtualCrate virtualCrate;
	private boolean isVirtual;


	public OnCrateCreated(ACPlayer player, Crate crate) {
		this.player = player;
		this.crate = crate;
		this.isVirtual = false;
	}

	public OnCrateCreated(ACPlayer player, VirtualCrate virtualCrate) {
		this.player = player;
		this.virtualCrate = virtualCrate;
		this.isVirtual = true;
	}

	public ACPlayer getCreator() { return this.player; }

	public Crate getCrate() { return this.crate; }

	public VirtualCrate getVirtualCrate() { return this.virtualCrate; }

	public boolean isVirtual() { return this.isVirtual; }


	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

}
