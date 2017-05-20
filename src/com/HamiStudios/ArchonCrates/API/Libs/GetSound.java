package com.HamiStudios.ArchonCrates.API.Libs;

import org.bukkit.Sound;

import com.HamiStudios.ArchonCrates.API.Enums.Files;
import com.HamiStudios.ArchonCrates.API.Exceptions.InvalidSoundValue;

public class GetSound {

	public static Sound get(String value, String ID, Files file) throws InvalidSoundValue {
		try {
			return Sound.valueOf((String) value);
		} catch(IllegalArgumentException e) {
			throw new InvalidSoundValue(ID, value, file);
		}
	}

}
