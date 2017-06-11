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

	/**
	 * Ran when a player is given a key
	 *
	 * @param giver an ACPlayer object of the player who is given the key.
	 * @param gainer an ACPlayer object of the player who is getting the key.
	 * @param key a Key object of the key being given.
	 * @param isVirtual a boolean with the value true if the key is a virtual key.
	 */
	public OnKeyGiven(ACPlayer giver, ACPlayer gainer, Key key, boolean isVirtual) {
		this.giver = giver;
		this.gainer = gainer;
		this.key = key;
		this.isVirtual = isVirtual;
	}

	/**
	 * Get the key giver.
	 *
	 * @return ACPlayer of who is given the key.
	 */
	public ACPlayer getGiver() { return this.giver; }

	/**
	 * Get the player who is getting the key.
	 *
	 * @return ACPlayer of who is getting the key.
	 */
	public ACPlayer getGainer() { return this.gainer; }

	/**
	 * Get the key of which is being given.
	 *
	 * @return Key of which is being given.
	 */
	public Key getKey() { return this.key; }

	/**
	 * Returns true if the key is a virtual key and false if physical.
	 *
	 * @return Boolean depending on the key type.
	 */
	public boolean isVirtual() { return this.isVirtual; }


	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

}
