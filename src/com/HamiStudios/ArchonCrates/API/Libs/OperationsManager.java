package com.HamiStudios.ArchonCrates.API.Libs;

import com.HamiStudios.ArchonCrates.API.Objects.ACPlayer;
import com.HamiStudios.ArchonCrates.API.Operations.CrateRoll;
import com.HamiStudios.ArchonCrates.API.Operations.GiveKeyOperation;
import com.HamiStudios.ArchonCrates.API.Operations.VirtualKeySelector;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class OperationsManager {

	// Create array of current Key Givers
	private ArrayList<GiveKeyOperation> currentKeyGivers;
	// Create array of current Crate Rollers
	private ArrayList<CrateRoll> currentCrateRollers;
	// Create array of current virtual key selectors
	private ArrayList<VirtualKeySelector> currentVirtualKeySelectors;

	// OperationsManager constructor

	/**
	 * Class constructor.
	 */
	public OperationsManager() {
		this.currentKeyGivers = new ArrayList<>();
		this.currentCrateRollers = new ArrayList<>();
		this.currentVirtualKeySelectors = new ArrayList<>();
	}



	// Key Givers


	// Add a new key giver

	/**
	 * Add a player to the key givers.
	 * Used to keep track of players who are in the giving key GUI menu.
	 *
	 * @param player an ACPlayer of who is giving the key.
	 * @param giveTo a Player of who is getting the key.
	 * @param giveAll boolean indicating if to give it to all players.
	 * @param amount of keys to give.
	 */
	public void addKeyGiver(ACPlayer player, Player giveTo, boolean giveAll, int amount) { this.currentKeyGivers.add(new GiveKeyOperation(player, giveTo, giveAll, amount)); }

	// Check if a player is a key giver

	/**
	 * Check if a player is a key giver.
	 *
	 * @param player an ACPlayer of who to check.
	 * @return true if they are and false if not.
	 */
	public boolean isKeyGiver(ACPlayer player) {
		for (GiveKeyOperation operation : this.currentKeyGivers) {
			if(operation.getPlayer().getPlayer() == player.getPlayer()) {
				return true;
			}
		}
		return false;
	}

	// Remove a player from the key giver array

	/**
	 * Remove a player from key givers.
	 *
	 * @param player an ACPlayer of who to remove.
	 */
	public void removeKeyGiver(ACPlayer player) {
		for (GiveKeyOperation operation : this.currentKeyGivers) {
			if(operation.getPlayer().getPlayer() == player.getPlayer()) {
				this.currentKeyGivers.remove(this.currentKeyGivers.indexOf(operation));
				break;
			}
		}
	}

	/**
	 * Get the GiveKeyOperation of a specific player.
	 *
	 * @param player an ACPlayer of who to get the operation of.
	 * @return an GiveKeyOperation for the given player.
	 */
	public GiveKeyOperation getGiveKeyOperation(ACPlayer player) {
		for (GiveKeyOperation operation : this.currentKeyGivers) {
			if(operation.getPlayer().getPlayer() == player.getPlayer()) {
				return operation;
			}
		}

		return null;
	}

	/**
	 * Get all key givers.
	 *
	 * @return an ArrayList of all GiveKeyOperations.
	 */
	public ArrayList<GiveKeyOperation> getCurrentKeyGivers() {
		return this.currentKeyGivers;
	}




	// Crate Rollers


	// Add a new Crate Roller

	/**
	 * Add a crate roll to the crate rollers.
	 *
	 * @param crateRoll the CrateRoll to add a s a crate roller.
	 */
	public void addCrateRoller(CrateRoll crateRoll) { this.currentCrateRollers.add(crateRoll); }

	// Check if a player is a Crate Roller

	/**
	 * Check if a player is a crate roller.
	 *
	 * @param player an ACPlayer of which to check.
	 * @return true if they are and false if not.
	 */
	public boolean isCrateRoller(ACPlayer player) {
		for (CrateRoll crateRoll : this.currentCrateRollers) {
			if(crateRoll.getPlayer().getPlayer() == player.getPlayer()) {
				return true;
			}
		}
		return false;
	}

	// Remove a player from the Crate Roller array

	/**
	 * Remove a player from the crate rollers.
	 *
	 * @param player an ACPlayer of which to remove.
	 */
	public void removeCrateRoller(ACPlayer player) {
		for (CrateRoll crateRoll : this.currentCrateRollers) {
			if(crateRoll.getPlayer().getPlayer() == player.getPlayer()) {
				this.currentCrateRollers.remove(this.currentCrateRollers.indexOf(crateRoll));
				break;
			}
		}
	}

	// Get the Crate Roller if the player given is a Crate Roller

	/**
	 * Get a crate roller.
	 *
	 * @param player of which to get the CrateRoll.
	 * @return an CrateRoll if the player is a crate roller.
	 */
	public CrateRoll getCrateRoller(ACPlayer player) {
		for (CrateRoll crateRoll : this.currentCrateRollers) {
			if(crateRoll.getPlayer().getPlayer() == player.getPlayer()) {
				return crateRoll;
			}
		}

		return null;
	}

	// Get all Crate Rollers

	/**
	 * Get all crate rollers.
	 *
	 * @return an ArrayList of all crate rollers.
	 */
	public ArrayList<CrateRoll> getCurrentCrateRollers() {
		return this.currentCrateRollers;
	}




	// Virtual Key Selectors

	// Add a new key selector

	/**
	 * Add a VirtualKeySelector.
	 *
	 * @param keySelector the VirtualKeySelector to add.
	 */
	public void addVirtualKeySelector(VirtualKeySelector keySelector) { this.currentVirtualKeySelectors.add(keySelector); }

	// Check if a player is a key selector

	/**
	 * Check if a player is a VirtualKeySelector.
	 *
	 * @param player the ACPlayer of which to check.
	 * @return true of they are and false if not.
	 */
	public boolean isVirtualKeySelector(ACPlayer player) {
		for (VirtualKeySelector keySelector : this.currentVirtualKeySelectors) {
			if(keySelector.getPlayer().getPlayer() == player.getPlayer()) {
				return true;
			}
		}
		return false;
	}

	// Remove a player from the key selector array

	/**
	 * Remove a player from the VirtualKeySelector's
	 *
	 * @param player of which to remove.
	 */
	public void removeVirtualKeySelector(ACPlayer player) {
		for (VirtualKeySelector keySelector : this.currentVirtualKeySelectors) {
			if(keySelector.getPlayer().getPlayer() == player.getPlayer()) {
				this.currentVirtualKeySelectors.remove(this.currentVirtualKeySelectors.indexOf(keySelector));
				break;
			}
		}
	}

	// Get the key selector if the player given is a key selector

	/**
	 * Get the VirtualKeySelector of a player.
	 *
	 * @param player of which to get the VirtualKeySelector.
	 * @return an VirtualKeySelector.
	 */
	public VirtualKeySelector getVirtualKeySelector(ACPlayer player) {
		for (VirtualKeySelector keySelector: this.currentVirtualKeySelectors) {
			if(keySelector.getPlayer().getPlayer() == player.getPlayer()) {
				return keySelector;
			}
		}

		return null;
	}

	// Get all key selector

	/**
	 * Get all VirtualKeySelector's
	 *
	 * @return an ArrayList of all VirtualKeySelector's
	 */
	public ArrayList<VirtualKeySelector> getCurrentVirtualKeySelectors() {
		return this.currentVirtualKeySelectors;
	}

}

