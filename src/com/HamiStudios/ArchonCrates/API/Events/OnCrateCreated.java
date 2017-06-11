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

	/**
	 * Ran when a player creates a new physical crate.
	 *
	 * @param player an ACPlayer object of the player who created the crate.
	 * @param crate an Crate object of the crate the player created.
	 */
	public OnCrateCreated(ACPlayer player, Crate crate) {
		this.player = player;
		this.crate = crate;
		this.isVirtual = false;
	}

	/**
	 * Ran when a player creates a new virtual crate.
	 *
	 * @param player an ACPlayer object of the player who created the crate.
	 * @param virtualCrate an VirtualCrate object of the virtual crate created.
	 */
	public OnCrateCreated(ACPlayer player, VirtualCrate virtualCrate) {
		this.player = player;
		this.virtualCrate = virtualCrate;
		this.isVirtual = true;
	}

	/**
	 * Gets the creator of the crate.
	 *
	 * @return ACPlayer who created the crate.
	 */
	public ACPlayer getCreator() { return this.player; }

	/**
	 * Gets the crate.
	 *
	 * @return Created Crate.
	 */
	public Crate getCrate() { return this.crate; }

	/**
	 * Gets the virtual crate.
	 *
	 * @return Created VirtualCrate.
	 */
	public VirtualCrate getVirtualCrate() { return this.virtualCrate; }

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
