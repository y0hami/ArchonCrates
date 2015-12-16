package com.HamiStudios.ArchonCrates.API.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.HamiStudios.ArchonCrates.API.Objects.Key;

public class OnPlayerKeyGiven extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private Key key;
    
    public OnPlayerKeyGiven(Player player, Key key) {
    	this.player = player;
    	this.key = key;
    }
    
    public Player getPlayer() {
    	return this.player;
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
