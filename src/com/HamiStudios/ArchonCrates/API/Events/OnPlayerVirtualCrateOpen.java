package com.HamiStudios.ArchonCrates.API.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.HamiStudios.ArchonCrates.API.Objects.Key;
import com.HamiStudios.ArchonCrates.API.Objects.VirtualCrate;

public class OnPlayerVirtualCrateOpen extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private VirtualCrate vcrate;
    private Key key;
    
    public OnPlayerVirtualCrateOpen(Player player, VirtualCrate vcrate, Key key) {
    	this.player = player;
    	this.vcrate = vcrate;
    	this.key = key;
    }
    
    public Player getPlayer() {
    	return this.player;
    }
    
    public VirtualCrate getCrate() {
    	return this.vcrate;
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
