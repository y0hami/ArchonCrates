package com.HamiStudios.ArchonCrates.API.Events;

import com.HamiStudios.ArchonCrates.API.Objects.ACPlayer;
import com.HamiStudios.ArchonCrates.API.Objects.Crate;
import com.HamiStudios.ArchonCrates.API.Objects.Key;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class OnCrateCreated extends Event {

	private static final HandlerList handlers = new HandlerList();

	private ACPlayer player;
	private Crate crate;
	private Key key;


	public OnCrateCreated(ACPlayer player, Crate crate, Key key) {
		this.player = player;
		this.crate = crate;
		this.key = key;
	}

	public ACPlayer getCreator() { return this.player; }

	public Crate getCrate() { return this.crate; }

	public Key getKey() { return this.key; }


	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

}
