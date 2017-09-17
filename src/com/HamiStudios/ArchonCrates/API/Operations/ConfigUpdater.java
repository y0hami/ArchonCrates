package com.HamiStudios.ArchonCrates.API.Operations;

import com.HamiStudios.ArchonCrates.Files.Crates;
import com.HamiStudios.ArchonCrates.Files.VirtualCrates;

public class ConfigUpdater {

	private boolean madeChanges = false;

	public ConfigUpdater() {
		// Update "crates.yml"
		Crates cratesFile = new Crates();

		for (String crateId : cratesFile.getFileConfiguration().getConfigurationSection("Crates").getKeys(false)) {
			if(cratesFile.getFileConfiguration().getInt("Crates." + crateId + ".config.scrollSpeed") == 0) {
				cratesFile.getFileConfiguration().set("Crates." + crateId + ".config.scrollSpeed", 8);
				this.madeChanges = true;
			}
		}

		cratesFile.save();



		// Update "virtual crate.yml"
		VirtualCrates virtualCrates = new VirtualCrates();

		if(virtualCrates.getFileConfiguration().getInt("Virtual Crate.config.scrollSpeed") == 0) {
			virtualCrates.getFileConfiguration().set("Virtual Crate.config.scrollSpeed", 8);
			this.madeChanges = true;
		}

		virtualCrates.save();
	}

	public boolean changesMade() { return this.madeChanges; }

}
