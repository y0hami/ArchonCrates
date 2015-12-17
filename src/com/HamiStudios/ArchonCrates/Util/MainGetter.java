package com.HamiStudios.ArchonCrates.Util;

import com.HamiStudios.ArchonCrates.Main;

public class MainGetter {

	private static Main mainclass;
	
	public MainGetter(Main main) {
		mainclass = main;
	}
	
	public static Main getMain() {
		return mainclass;
	}
	
}
