package com.HamiStudios.ArchonCrates.API.Events;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.HamiStudios.ArchonCrates.API.Objects.Key;

public class OnMobKeyDrop extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Entity mob;
    private Key key;
    private double chance;
    
    public OnMobKeyDrop(Entity mob, Key key, double chance) {
    	this.mob = mob;
    	this.key = key;
    	this.chance = chance;
    }
    
    public Entity getEntity() {
    	return this.mob;
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
