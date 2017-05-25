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
	public OperationsManager() {
		this.currentKeyGivers = new ArrayList<>();
		this.currentCrateRollers = new ArrayList<>();
		this.currentVirtualKeySelectors = new ArrayList<>();
	}



	// Key Givers


	// Add a new key giver
	public void addKeyGiver(ACPlayer player, Player giveTo, boolean giveAll, int amount) { this.currentKeyGivers.add(new GiveKeyOperation(player, giveTo, giveAll, amount)); }

	// Check if a player is a key giver
	public boolean isKeyGiver(ACPlayer player) {
		for (GiveKeyOperation operation : this.currentKeyGivers) {
			if(operation.getPlayer().getPlayer() == player.getPlayer()) {
				return true;
			}
		}
		return false;
	}

	// Remove a player from the key giver array
	public void removeKeyGiver(ACPlayer player) {
		for (GiveKeyOperation operation : this.currentKeyGivers) {
			if(operation.getPlayer().getPlayer() == player.getPlayer()) {
				this.currentKeyGivers.remove(operation);
				break;
			}
		}
	}

	public GiveKeyOperation getGiveKeyOperation(ACPlayer player) {
		for (GiveKeyOperation operation : this.currentKeyGivers) {
			if(operation.getPlayer().getPlayer() == player.getPlayer()) {
				return operation;
			}
		}

		return null;
	}

	public ArrayList<GiveKeyOperation> getCurrentKeyGivers() {
		return this.currentKeyGivers;
	}




	// Crate Rollers


	// Add a new Crate Roller
	public void addCrateRoller(CrateRoll crateRoll) { this.currentCrateRollers.add(crateRoll); }

	// Check if a player is a Crate Roller
	public boolean isCrateRoller(ACPlayer player) {
		for (CrateRoll crateRoll : this.currentCrateRollers) {
			if(crateRoll.getPlayer().getPlayer() == player.getPlayer()) {
				return true;
			}
		}
		return false;
	}

	// Remove a player from the Crate Roller array
	public void removeCrateRoller(ACPlayer player) {
		for (CrateRoll crateRoll : this.currentCrateRollers) {
			if(crateRoll.getPlayer().getPlayer() == player.getPlayer()) {
				this.currentCrateRollers.remove(crateRoll);
				break;
			}
		}
	}

	// Get the Crate Roller if the player given is a Crate Roller
	public CrateRoll getCrateRoller(ACPlayer player) {
		for (CrateRoll crateRoll : this.currentCrateRollers) {
			if(crateRoll.getPlayer().getPlayer() == player.getPlayer()) {
				return crateRoll;
			}
		}

		return null;
	}

	// Get all Crate Rollers
	public ArrayList<CrateRoll> getCurrentCrateRollers() {
		return this.currentCrateRollers;
	}




	// Virtual Key Selectors

	// Add a new key selector
	public void addVirtualKeySelector(VirtualKeySelector keySelector) { this.currentVirtualKeySelectors.add(keySelector); }

	// Check if a player is a key selector
	public boolean isVirtualKeySelector(ACPlayer player) {
		for (VirtualKeySelector keySelector : this.currentVirtualKeySelectors) {
			if(keySelector.getPlayer().getPlayer() == player.getPlayer()) {
				return true;
			}
		}
		return false;
	}

	// Remove a player from the key selector array
	public void removeVirtualKeySelector(ACPlayer player) {
		for (VirtualKeySelector keySelector : this.currentVirtualKeySelectors) {
			if(keySelector.getPlayer().getPlayer() == player.getPlayer()) {
				this.currentVirtualKeySelectors.remove(keySelector);
				break;
			}
		}
	}

	// Get the key selector if the player given is a key selector
	public VirtualKeySelector getVirtualKeySelector(ACPlayer player) {
		for (VirtualKeySelector keySelector: this.currentVirtualKeySelectors) {
			if(keySelector.getPlayer().getPlayer() == player.getPlayer()) {
				return keySelector;
			}
		}

		return null;
	}

	// Get all key selector
	public ArrayList<VirtualKeySelector> getCurrentVirtualKeySelectors() {
		return this.currentVirtualKeySelectors;
	}

}

