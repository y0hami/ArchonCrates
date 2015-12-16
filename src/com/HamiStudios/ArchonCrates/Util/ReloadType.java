package com.HamiStudios.ArchonCrates.Util;

import com.HamiStudios.ArchonCrates.Files.BlockDrop;
import com.HamiStudios.ArchonCrates.Files.BuySign;
import com.HamiStudios.ArchonCrates.Files.Config;
import com.HamiStudios.ArchonCrates.Files.CrateLoot;
import com.HamiStudios.ArchonCrates.Files.Crates;
import com.HamiStudios.ArchonCrates.Files.CustomGUI;
import com.HamiStudios.ArchonCrates.Files.Data;
import com.HamiStudios.ArchonCrates.Files.Keys;
import com.HamiStudios.ArchonCrates.Files.Language;
import com.HamiStudios.ArchonCrates.Files.Locations;
import com.HamiStudios.ArchonCrates.Files.MobDrop;
import com.HamiStudios.ArchonCrates.Files.Permissions;
import com.HamiStudios.ArchonCrates.Files.PlayerLog;
import com.HamiStudios.ArchonCrates.Files.PrizeLog;
import com.HamiStudios.ArchonCrates.Files.VirtualKeys;

public enum ReloadType {
	
	ALL (0),
	CONFIG (1),
	CRATE_LOOT (2),
	KEYS (3),
	CRATES (4),
	DATA (5),
	LOCATIONS (6),
	MOB_DROPS (7),
	LANGUAGE (8),
	PERMISSIONS (9),
	PLAYER_LOG (10),
	PRIZE_LOG (11),
	BUY_SIGN (12),
	VIRTUAL_KEY (13),
	CUSTOM_GUI (14),
	BLOCK_DROP (15);
	
	private int id;
	ReloadType(int id) {
		this.id = id;
	}
	
	public void reload() {
		if(this.id == 0) {
			Config config = new Config();
			config.reload();
			CrateLoot crateLoot = new CrateLoot();
			crateLoot.reload();
			Keys keys = new Keys();
			keys.reload();
			Crates crates = new Crates();
			crates.reload();
			Data data = new Data();
			data.reload();
			Locations locations = new Locations();
			locations.reload();
			MobDrop mobDrops = new MobDrop();
			mobDrops.reload();
			Language language = new Language();
			language.reload();
			Permissions permissions = new Permissions();
			permissions.reload();
			PlayerLog playerLog = new PlayerLog();
			playerLog.reload();
			PrizeLog prizeLog = new PrizeLog();
			prizeLog.reload();
			BuySign buySign = new BuySign();
			buySign.reload();
			VirtualKeys vKeys = new VirtualKeys();
			vKeys.reload();
			CustomGUI cgui = new CustomGUI();
			cgui.reload();
			BlockDrop blockDrop = new BlockDrop();
			blockDrop.reload();
		}
		else if(this.id == 1) {
			Config config = new Config();
			config.reload();
		}
		else if(this.id == 2) {
			CrateLoot crateLoot = new CrateLoot();
			crateLoot.reload();
		}
		else if(this.id == 3) {
			Keys keys = new Keys();
			keys.reload();
		}
		else if(this.id == 4) {
			Crates crates = new Crates();
			crates.reload();
		}
		else if(this.id == 5) {
			Data data = new Data();
			data.reload();
		}
		else if(this.id == 6) {
			Locations locations = new Locations();
			locations.reload();
		}
		else if(this.id == 7) {
			MobDrop mobDrops = new MobDrop();
			mobDrops.reload();
		}
		else if(this.id == 8) {
			Language language = new Language();
			language.reload();
		}
		else if(this.id == 9) {
			Permissions permissions = new Permissions();
			permissions.reload();
		}
		else if(this.id == 10) {
			PlayerLog playerLog = new PlayerLog();
			playerLog.reload();
		}
		else if(this.id == 11) {
			PrizeLog prizeLog = new PrizeLog();
			prizeLog.reload();
		}
		else if(this.id == 12) {
			BuySign buySign = new BuySign();
			buySign.reload();
		}
		else if(this.id == 13) {
			VirtualKeys vKeys = new VirtualKeys();
			vKeys.reload();
		}
		else if(this.id == 14) {
			CustomGUI cgui = new CustomGUI();
			cgui.reload();
		}
		else if(this.id == 15) {
			BlockDrop blockDrop = new BlockDrop();
			blockDrop.reload();
		}
	}
	
}
