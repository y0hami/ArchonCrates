package com.HamiStudios.ArchonCrates.Files;

import com.HamiStudios.ArchonCrates.API.Enums.Files;
import com.HamiStudios.ArchonCrates.API.Exceptions.NoValueException;
import com.HamiStudios.ArchonCrates.API.Libs.FileInterface;
import com.HamiStudios.ArchonCrates.API.Objects.ItemLore;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Keys implements FileInterface {

	// Instances of File and FileConfiguration
		private File file;
		private FileConfiguration fileconfig;
		private String filePath = "plugins/ArchonCrates/keys.yml";
		
		
		// Crates file constructor
		public Keys() {
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
				throw new NoValueException(path, Files.KEYS);
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
				// Default Key
				this.set("Keys.default.name", "&aDefault Key");
				
				ArrayList<String> lore = new ItemLore()
					.translateColours(false)
					.add("&7Right click a crate")
					.add("&7to use this key")
					.build();
				
				this.set("Keys.default.lore", lore);
				this.set("Keys.default.item.ID", 131);
				this.set("Keys.default.item.data", 0);
				this.set("Keys.default.glow", true);
				
				
				// Golden Key
				this.set("Keys.golden.name", "&6Golden Key");
				
				lore = new ItemLore()
					.translateColours(false)
					.add("&7Right click a crate")
					.add("&7to use this key")
					.build();
				
				this.set("Keys.golden.lore", lore);
				this.set("Keys.golden.item.ID", 396);
				this.set("Keys.golden.item.data", 0);
				this.set("Keys.golden.glow", true);


				// Unique Key
				this.set("Keys.unique.name", "&6Unique Key");

				lore = new ItemLore()
						.translateColours(false)
						.add("&7Right click a crate")
						.add("&7to use this key")
						.build();

				this.set("Keys.unique.lore", lore);
				this.set("Keys.unique.item.ID", 371);
				this.set("Keys.unique.item.data", 0);
				this.set("Keys.unique.glow", true);


				this.save();

				this.setHeader();

				return true;
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}

		@Override
		public boolean setHeader() {
			this.fileconfig.options().header(
					"\n " +
					"Need help configuring? Visit the ArchonCrates documentation https://archoncrates.com/docs/" +
					Bukkit.getPluginManager().getPlugin("ArchonCrates").getDescription().getVersion().replaceAll("\\.", "-") +
					"/files/keys" +
					"\n \n"
				);

			this.save();

			return true;
		}
	
}
