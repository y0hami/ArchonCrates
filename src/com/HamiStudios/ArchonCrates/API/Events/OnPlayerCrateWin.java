package com.HamiStudios.ArchonCrates.API.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.HamiStudios.ArchonCrates.API.Objects.Crate;
import com.HamiStudios.ArchonCrates.API.Objects.Key;
import com.HamiStudios.ArchonCrates.API.Objects.Prize;

public class OnPlayerCrateWin extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private Crate crate;
    private Key key;
    private Prize prize;
    
    public OnPlayerCrateWin(Player player, Crate crate, Key key, Prize prize) {
    	this.player = player;
    	this.crate = crate;
    	this.key = key;
    	this.prize = prize;
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
