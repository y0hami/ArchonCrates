package com.HamiStudios.ArchonCrates.API.Events;

import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.HamiStudios.ArchonCrates.API.Objects.Key;

public class OnBlockDropKey extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Block block;
    private Key key;
    private double chance;
    
    public OnBlockDropKey(Block block, Key key, double chance) {
    	this.block = block;
    	this.key = key;
    	this.chance = chance;
    }
    
    public Block getBlock() {
    	return this.block;
    }
    
    public Key getKey() {
    	return this.key;
    }

    public double getChance() {
    	return this.chance;
    }
    
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }

}
