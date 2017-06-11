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

	/**
	 * Ran when a player removes a physical crate.
	 *
	 * @param player an ACPlayer object of who removed the crate.
	 * @param crate an Crate object of the removed crate.
	 */
	public OnCrateRemoved(ACPlayer player, Crate crate) {
		this.player = player;
		this.crate = crate;
		this.isVirtual = false;
	}

	/**
	 * Ran when a player removes a virtual crate.
	 *
	 * @param player an ACPlayer object of who removed the crate.
	 * @param virtualCrate an VirtualCrate object who the removed crate.
	 */
	public OnCrateRemoved(ACPlayer player, VirtualCrate virtualCrate) {
		this.player = player;
		this.virtualCrate = virtualCrate;
		this.isVirtual = true;
	}

	/**
	 * Get the player who removed the crate.
	 *
	 * @return ACPlayer who removed the crate.
	 */
	public ACPlayer getRemover() { return this.player; }

	/**
	 * Get the removed crate.
	 *
	 * @return Removed crate.
	 */
	public Crate getCrate() { return this.crate; }

	/**
	 * Returns true if the crate is a virtual crate and false if physical.
	 *
	 * @return Boolean depending on the crate type.
	 */
	public boolean isVirtual() { return this.isVirtual; }


	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

}