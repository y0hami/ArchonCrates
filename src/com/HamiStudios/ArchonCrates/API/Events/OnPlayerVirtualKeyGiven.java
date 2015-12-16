package com.HamiStudios.ArchonCrates.API.Events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.HamiStudios.ArchonCrates.API.Objects.VirtualKey;

public class OnPlayerVirtualKeyGiven extends Event {

    private static final HandlerList handlers = new HandlerList();
    private String player;
    private VirtualKey vkey;
    
    public OnPlayerVirtualKeyGiven(String playername, VirtualKey vkey) {
    	this.player = playername;
    	this.vkey = vkey;
    }
    
    public String getPlayername() {
    	return this.player;
    }
    
    public VirtualKey getKey() {
    	return this.vkey;
    }
    
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }


}
