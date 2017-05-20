package com.HamiStudios.ArchonCrates.API.Events;

import com.HamiStudios.ArchonCrates.API.Objects.ACPlayer;
import com.HamiStudios.ArchonCrates.API.Objects.Key;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class OnKeyGiven extends Event {

	private static final HandlerList handlers = new HandlerList();

	private ACPlayer giver;
	private ACPlayer gainer;
	private Key key;
	private boolean isVirtual;

	public OnKeyGiven(ACPlayer giver, ACPlayer gainer, Key key, boolean isVirtual) {
		this.giver = giver;
		this.gainer = gainer;
		this.key = key;
		this.isVirtual = isVirtual;
	}

	public ACPlayer getGiver() { return this.giver; }

	public ACPlayer getGainer() { return this.gainer; }

	public Key getKey() { return this.key; }

	public boolean isVirtual() { return this.isVirtual; }


	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

}
