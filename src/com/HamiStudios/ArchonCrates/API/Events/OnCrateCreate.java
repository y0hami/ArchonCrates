package com.HamiStudios.ArchonCrates.API.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.HamiStudios.ArchonCrates.API.Objects.Crate;
import com.HamiStudios.ArchonCrates.API.Objects.VirtualCrate;

public class OnCrateCreate extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private Crate crate;
    private VirtualCrate vcrate;
    private boolean virtual;
    
    public OnCrateCreate(Player player, Crate crate, boolean virtual) {
    	this.player = player;
    	this.crate = crate;
    }
 
    public OnCrateCreate(Player player, VirtualCrate vcrate, boolean virtual) {
    	this.player = player;
    	this.vcrate = vcrate;
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
