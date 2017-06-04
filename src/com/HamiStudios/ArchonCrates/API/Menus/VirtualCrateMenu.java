package com.HamiStudios.ArchonCrates.API.Menus;

import com.HamiStudios.ArchonCrates.API.Enums.PlayerDataType;
import com.HamiStudios.ArchonCrates.API.Libs.Glow;
import com.HamiStudios.ArchonCrates.API.Libs.ItemBuilder;
import com.HamiStudios.ArchonCrates.API.Objects.*;
import com.HamiStudios.ArchonCrates.Files.PlayerData;
import com.HamiStudios.ArchonCrates.Files.VCLayout;
import com.google.gson.Gson;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VirtualCrateMenu {

	private HashMap<String, Integer> keys;
	private int rows;
	private ArrayList<VirtualCrateMenuItem> layout;
	private ACPlayer player;

	public VirtualCrateMenu(ACPlayer player) {
		String content = VCLayout.getContent();
		if(content != null) {
			Gson gson = new Gson();
			VCLayoutFile file = gson.fromJson(content, VCLayoutFile.class);

			this.keys = file.getKeys();
			this.rows = file.getRows();
			this.layout = file.getLayout();
		} else {
			this.keys = new HashMap<>();
			this.rows = 0;
			this.layout = new ArrayList<>();
		}

		this.player = player;
	}

	public Inventory getMenu() {
		VirtualCrate crate = new VirtualCrate();

		int invSize = this.rows*9;
		Inventory menu = Bukkit.createInventory(null, invSize, ChatColor.translateAlternateColorCodes('&', crate.getTitle()));

		for (VirtualCrateMenuItem item : this.layout) {
			menu.setItem(item.getSlot(), new ItemBuilder()
					.setMaterial(Material.getMaterial(item.getItemID()))
					.setData((short) item.getItemData())
					.setName(item.getName())
				.build());
		}

		for (Map.Entry<String, Integer> keyItem : this.keys.entrySet()) {
			Key key = new Key(keyItem.getKey());
			if(key.valid()) {

				int keyCount = 0;

				HashMap<String, Integer> keys = ((HashMap<String, Integer>) PlayerData.get(this.player, PlayerDataType.VIRTUAL_KEYS));

				if(keys.get(key.getID()) != null) {
					keyCount = keys.get(key.getID());
				}

				ItemBuilder item = new ItemBuilder()
						.setMaterial(Material.getMaterial(key.getItemID()))
						.setData((short) key.getItemData())
						.setName(key.getName())
						.setLore(new ItemLore()
									.add("&7Keys: &b" + keyCount)
								.build());

				if(key.glow()) {
					item.addEnchantment(new Glow(99), 0, true);
				}

				menu.setItem(keyItem.getValue(), item.build());
			}
		}

		return menu;
	}

}