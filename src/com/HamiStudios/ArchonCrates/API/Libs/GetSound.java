package com.HamiStudios.ArchonCrates.API.Libs;

import org.bukkit.Sound;

import com.HamiStudios.ArchonCrates.API.Enums.Files;
import com.HamiStudios.ArchonCrates.API.Exceptions.InvalidSoundValue;

public class GetSound {

	/**
	 * Get a minecraft sound instance from a string value.
	 *
	 * @param value of the sound you want.
	 * @param ID of the element the sound is from in a file.
	 * @param file where the element is in.
	 * @return Sound instance (Minecraft aka Bukkit object).
	 * @throws InvalidSoundValue throws InvalidSoundValue when the sound is an invalid Minecraft sound
	 */
	public static Sound get(String value, String ID, Files file) throws InvalidSoundValue {
		try {
			return Sound.valueOf((String) value);
		} catch(IllegalArgumentException e) {
			throw new InvalidSoundValue(ID, value, file);
		}
	}

}
