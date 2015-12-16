package com.HamiStudios.ArchonCrates.API.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.HamiStudios.ArchonCrates.API.Objects.Key;
import com.HamiStudios.ArchonCrates.API.Objects.Prize;
import com.HamiStudios.ArchonCrates.API.Objects.VirtualCrate;

public class OnPlayerVirtualCrateWin extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private VirtualCrate vcrate;
    private Key key;
    private Prize prize;
    
    public OnPlayerVirtualCrateWin(Player player, VirtualCrate vcrate, Key key, Prize prize) {
    	this.player = player;
    	this.vcrate = vcrate;
    	this.key = key;
    	this.prize = prize;
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
    
    public Prize getPrize() {
    	return this.prize;
    }
    
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }

}
