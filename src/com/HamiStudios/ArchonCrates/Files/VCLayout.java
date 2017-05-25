package com.HamiStudios.ArchonCrates.Files;

import com.HamiStudios.ArchonCrates.API.Objects.VCLayoutFile;
import com.HamiStudios.ArchonCrates.API.Objects.VirtualCrateMenuItem;
import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class VCLayout {

	// Check if the file exists
	public static boolean exists() { return new File("plugins/ArchonCrates/vc layout.json").exists(); }

	// Get file content
	public static String getContent() {
		try {
			return new String(Files.readAllBytes(Paths.get("plugins/ArchonCrates/vc layout.json")));
		} catch (IOException e) {
			return null;
		}
	}

	// Create the file
	public static void createFile() {
		File file = new File("plugins/ArchonCrates/vc layout.json");
		if(!exists()) {
			try {
				file.createNewFile();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

		try {
			PrintWriter writer = new PrintWriter(file, "UTF-8");
			Gson gson = new Gson();

			HashMap<String, Integer> keys = new HashMap<>();
			keys.put("default", 11);
			keys.put("golden", 13);
			keys.put("unique", 15);

			ArrayList<VirtualCrateMenuItem> layout = new ArrayList<>();
			layout.add(new VirtualCrateMenuItem(0, 160, 15, "&f"));
			layout.add(new VirtualCrateMenuItem(1, 160, 15, "&f"));
			layout.add(new VirtualCrateMenuItem(2, 160, 15, "&f"));
			layout.add(new VirtualCrateMenuItem(3, 160, 15, "&f"));
			layout.add(new VirtualCrateMenuItem(4, 160, 15, "&f"));
			layout.add(new VirtualCrateMenuItem(5, 160, 15, "&f"));
			layout.add(new VirtualCrateMenuItem(6, 160, 15, "&f"));
			layout.add(new VirtualCrateMenuItem(7, 160, 15, "&f"));
			layout.add(new VirtualCrateMenuItem(8, 160, 15, "&f"));
			layout.add(new VirtualCrateMenuItem(9, 160, 15, "&f"));
			layout.add(new VirtualCrateMenuItem(10, 160, 15, "&f"));
			layout.add(new VirtualCrateMenuItem(12, 160, 15, "&f"));
			layout.add(new VirtualCrateMenuItem(14, 160, 15, "&f"));
			layout.add(new VirtualCrateMenuItem(16, 160, 15, "&f"));
			layout.add(new VirtualCrateMenuItem(17, 160, 15, "&f"));
			layout.add(new VirtualCrateMenuItem(18, 160, 15, "&f"));
			layout.add(new VirtualCrateMenuItem(19, 160, 15, "&f"));
			layout.add(new VirtualCrateMenuItem(20, 160, 15, "&f"));
			layout.add(new VirtualCrateMenuItem(21, 160, 15, "&f"));
			layout.add(new VirtualCrateMenuItem(22, 160, 15, "&f"));
			layout.add(new VirtualCrateMenuItem(23, 160, 15, "&f"));
			layout.add(new VirtualCrateMenuItem(24, 160, 15, "&f"));
			layout.add(new VirtualCrateMenuItem(25, 160, 15, "&f"));
			layout.add(new VirtualCrateMenuItem(26, 160, 15, "&f"));

			VCLayoutFile layoutFile = new VCLayoutFile(keys, 3, layout);
			writer.write(gson.toJson(layoutFile));

			writer.close();

		} catch (FileNotFoundException|UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}