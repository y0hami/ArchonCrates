package com.HamiStudios.ArchonCrates.API.Libs;

import com.HamiStudios.ArchonCrates.API.Objects.Crate;
import com.HamiStudios.ArchonCrates.API.Objects.Key;
import com.HamiStudios.ArchonCrates.API.Objects.Prize;

public class Find {

	// Find a crate via the ID given
	public static Crate crate(String ID) {
		return new Crate(ID);
	}
	
	// Find a key via the ID given
	public static Key key(String ID) {
		return new Key(ID);
	}
	
	// Find a prize via the ID given
	public static Prize prize(String ID) {
		return new Prize(ID);
	}
	
}
