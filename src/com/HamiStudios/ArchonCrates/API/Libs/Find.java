package com.HamiStudios.ArchonCrates.API.Libs;

import com.HamiStudios.ArchonCrates.API.Objects.Crate;
import com.HamiStudios.ArchonCrates.API.Objects.Key;
import com.HamiStudios.ArchonCrates.API.Objects.Prize;

public class Find {

	// Find a crate via the ID given

	/**
	 * Get the Crate matching the given ID.
	 *
	 * @param ID of the Crate you want to find.
	 * @return a Crate instance with the ID of which specified.
	 */
	public static Crate crate(String ID) {
		return new Crate(ID);
	}
	
	// Find a key via the ID given

	/**
	 * Get the Key matching the given ID.
	 *
	 * @param ID of the Key you want to find.
	 * @return a Key instance with the ID of which specified.
	 */
	public static Key key(String ID) {
		return new Key(ID);
	}
	
	// Find a prize via the ID given

	/**
	 * Get the Prize matching the given ID.
	 *
	 * @param ID of the Prize you want to find.
	 * @return a Prize instance with the ID of which specified.
	 */
	public static Prize prize(String ID) {
		return new Prize(ID);
	}
	
}
