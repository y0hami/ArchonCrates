package com.HamiStudios.ArchonCrates.API.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.HamiStudios.ArchonCrates.API.Objects.Crate;
import com.HamiStudios.ArchonCrates.API.Objects.VirtualCrate;

public class OnCrateRemove extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private Crate crate;
    private VirtualCrate vcrate;
    private boolean virtual;
    
    public OnCrateRemove(Player player, Crate crate, boolean virtual) {
    	this.player = player;
    	this.crate = crate;
    	this.virtual = virtual;
    }
    
    public OnCrateRemove(Player player, VirtualCrate vcrate, boolean virtual) {
    	this.player = player;
    	this.vcrate = vcrate;
    	this.virtual = virtual;
    }
    
    public Player getPlayer() {
    	return this.player;
    }
    
    public Crate getCrate() {
    	return this.crate;
    }
    
    public VirtualCrate getVirtualCrate() {
    	return this.vcrate;
    }
    
    public boolean isVirtual() {
    	return this.virtual;
    }
    
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }

}
