package com.HamiStudios.ArchonCrates.Files;

import com.HamiStudios.ArchonCrates.API.libs.FileInterface;
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
		public Object get(String path) {
			return this.fileconfig.get(path);
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
				this.set("Language.Prefix", "&7[&5ArchonCrates&7] ");
				this.set("Language.NoPermission", "&cYou don't have permission to do that command.");
				this.set("Language.InvalidCommand", "&cThe command you entered is invalid, try archoncrates help &cto view a list of all commands.");
				
				this.set("Language.Commands.Create.InvalidType", "&cThe crate type you entered is invalid, try looking at the create command help page.");
				
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
