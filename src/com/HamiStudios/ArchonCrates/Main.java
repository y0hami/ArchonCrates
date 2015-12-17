package com.HamiStudios.ArchonCrates;

import org.bukkit.plugin.java.JavaPlugin;

import com.HamiStudios.ArchonCrates.Tasks.OnEnable;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		new OnEnable(this);
	}
	@Override
	public void onDisable() {
	}
	
}
