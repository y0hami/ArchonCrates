package com.HamiStudios.ArchonCrates.API.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.HamiStudios.ArchonCrates.API.Objects.Crate;
import com.HamiStudios.ArchonCrates.API.Objects.Key;

public class OnPlayerCrateOpen extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private Crate crate;
    private Key key;
    
    public OnPlayerCrateOpen(Player player, Crate crate, Key key) {
    	this.player = player;
    	this.crate = crate;
    	this.key = key;
    }
    
    public Player getPlayer() {
    	return this.player;
    }
    
    public Crate getCrate() {
    	return this.crate;
    }
    
    public Key getKey() {
    	return this.key;
    }
    
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }
	
}
