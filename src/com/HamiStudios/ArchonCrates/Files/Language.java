package com.HamiStudios.ArchonCrates.Files;

import com.HamiStudios.ArchonCrates.API.Enums.Files;
import com.HamiStudios.ArchonCrates.API.Exceptions.NoValueException;
import com.HamiStudios.ArchonCrates.API.Libs.FileInterface;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Language implements FileInterface {

	// Instances of File and FileConfiguration
		private File file;
		private FileConfiguration fileconfig;
		private String filePath = "plugins/ArchonCrates/language.yml";
		
		
		// Crates file constructor
		public Language() {
			// Creates the instances of the File and FileConfiguration
			this.file = new File(this.filePath);
			this.fileconfig = YamlConfiguration.loadConfiguration(file);
		}
		
		// Get the File instance
		@Override
		public File getFile() {
			return this.file;
		}
		
		// Get the FileConfiguration instance
		@Override
		public FileConfiguration getFileConfiguration() {
			return this.fileconfig;
		}

		@Override
		public boolean save() {
			try {
				this.fileconfig.save(this.file);
				return true;
			} catch(IOException e) {
				e.printStackTrace();
				return false;
			}
		}

		@Override
		public boolean reload() {
			try {
				this.file = new File(this.filePath);
				this.fileconfig = YamlConfiguration.loadConfiguration(this.file);
				return true;
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		@Override
		public boolean set(String path, Object value) {
			try {
				this.fileconfig.set(path, value);
				this.save();
				return true;
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		@Override
		public Object get(String path) throws NoValueException {
			Object returnValue = this.fileconfig.get(path);
			if(returnValue == null) {
				throw new NoValueException(path, Files.LANGUAGE);
			}
			return returnValue;
		}

		@Override
		public boolean exists() {
			if(new File(this.filePath).exists()) return true;
			return false;
		}

		@Override
		public boolean create() {
			try {
				// Set all default language values

				// Global Errors
				this.set("Language.Prefix", "&7[&5ArchonCrates&7] ");
				this.set("Language.NoPermission", "&cYou don't have permission to do that command.");
				this.set("Language.InvalidCommand", "&cThe command you entered is invalid, try archoncrates help &cto view a list of all commands.");
				this.set("Language.PlayerOnlyCommand", "&cThis command is only for players.");

				// Commands
				this.set("Language.Commands.Create.AddedToInv", "&fCrate added to your inventory.");
				this.set("Language.Commands.Create.NoSpace", "&cCan't open crate selection menu because your inventory is full.");
				this.set("Language.Commands.Key.InvalidAmount", "&cYou must enter a number for the amount.");
				this.set("Language.Commands.Key.OfflinePlayer", "&cThe player you entered is offline, players must be online to give keys.");
				this.set("Language.Commands.Key.InvalidFormat", "&cInvalid format of command, try visiting the help page. &f/archoncrates help key");
				this.set("Language.Commands.Key.InvalidKey", "&cThe key you entered is invalid.");
				this.set("Language.Commands.Key.InvalidKeyType", "&cThe key type can only be &5\"physical\" &cor &f\"virtual\"&c.");

				// Events
				this.set("Language.Events.Crate.Created", "&fYou successfully created a &5<crate> &fcrate!.");
				this.set("Language.Events.Crate.Removed", "&fCrate successfully removed.");
				this.set("Language.Events.Crate.SneakToRemove", "&cYou must be sneaking to remove crates.");
				this.set("Language.Events.Key.NoPermission", "&cYou don't have permission to give players keys.");
				this.set("Language.Events.Key.GivenAll", "&fGiven &5<amount> <key> &fkey(s) to all players.");
				this.set("Language.Events.Key.GivenPlayer", "&fGiven &5<amount> <key> &fkey(s) to &5<player>&f.");

				this.save();

				return true;
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		@Override
		public boolean setHeader() {
			return true;
		}
	
}
