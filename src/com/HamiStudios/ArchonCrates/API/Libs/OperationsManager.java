package com.HamiStudios.ArchonCrates.API.Libs;

import com.HamiStudios.ArchonCrates.API.Objects.ACPlayer;
import com.HamiStudios.ArchonCrates.API.Operations.GiveKeyOperation;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class OperationsManager {

	// Create array of current key givers
	private ArrayList<GiveKeyOperation> currentKeyGivers;

	// OperationsManager constructor
	public OperationsManager() { this.currentKeyGivers = new ArrayList<>(); }


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

}

