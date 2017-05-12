package com.HamiStudios.ArchonCrates.API.libs;

import com.HamiStudios.ArchonCrates.API.Enums.Files;
import com.HamiStudios.ArchonCrates.API.Objects.Crate;
import com.HamiStudios.ArchonCrates.API.Objects.Prize;

import java.util.ArrayList;

public class Fetcher {

	public static ArrayList<Crate> getCrates() {

		FileHandler fileHandler = new FileHandler(Files.CRATES);
		ArrayList<Crate> crates = new ArrayList<>();

		for (String crateID : fileHandler.getFileConfiguration().getConfigurationSection("Crates").getKeys(false)) {
			Crate crate = new Crate(crateID);
			if(crate.valid()) { crates.add(crate); }
		}

		return crates;
	}

	public static ArrayList<Prize> getPrizes() {

		FileHandler fileHandler = new FileHandler(Files.PRIZES);
		ArrayList<Prize> prizes = new ArrayList<>();

		for (String prizeID : fileHandler.getFileConfiguration().getConfigurationSection("Prizes").getKeys(false)) {
			Prize prize = new Prize(prizeID);
			if(prize.valid()) { prizes.add(prize); }
		}

		return prizes;
	}

}
