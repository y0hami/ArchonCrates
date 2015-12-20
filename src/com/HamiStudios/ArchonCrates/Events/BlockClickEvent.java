package com.HamiStudios.ArchonCrates.Events;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.HamiStudios.ArchonCrates.Main;
import com.HamiStudios.ArchonCrates.API.Objects.Key;
import com.HamiStudios.ArchonCrates.API.Objects.Exceptions.InvalidCrateInput;
import com.HamiStudios.ArchonCrates.CustomGUI.PrizeViewerGUI;
import com.HamiStudios.ArchonCrates.CustomGUI.VirtualKeyGUI;
import com.HamiStudios.ArchonCrates.Files.FileHandler;
import com.HamiStudios.ArchonCrates.Tasks.Crate;
import com.HamiStudios.ArchonCrates.Util.ACPermission;
import com.HamiStudios.ArchonCrates.Util.CrateFinder;
import com.HamiStudios.ArchonCrates.Util.FileType;
import com.HamiStudios.ArchonCrates.Util.KeyFinder;
import com.HamiStudios.ArchonCrates.Util.LanguageType;

public class BlockClickEvent implements Listener {

	private Main main;
	public BlockClickEvent(Main main) {
		this.main = main;
		this.main.getServer().getPluginManager().registerEvents(this, this.main);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockClick(PlayerInteractEvent event) {
		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if(event.getClickedBlock() != null) {
				if(!event.getClickedBlock().equals(Material.AIR)) {
					
					int blockId = event.getClickedBlock().getTypeId();
					double blockX = event.getClickedBlock().getX();
					double blockY = event.getClickedBlock().getY();
					double blockZ = event.getClickedBlock().getZ();
					World world = event.getClickedBlock().getWorld();
					
					String crateType = CrateFinder.findCrateType(blockId, blockX, blockY, blockZ, world);
					String crateId = CrateFinder.findVCrateLocationId(blockId, blockX, blockY, blockZ, world);
					
					// Virtual
					if(crateId != null) {
						event.setCancelled(true);
						if(event.getPlayer().hasPermission(ACPermission.OPEN_VIRTUAL_CRATE.toString())) {
							VirtualKeyGUI vgui = new VirtualKeyGUI(event.getPlayer());
							vgui.open();
						}
						else{
							event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.OPEN_CRATE_NO_PERMISSION_VIRTUAL.toString(true));
							return;
						}
					}
					
					// Normal
					if(crateType != null) {
						event.setCancelled(true);
						if(event.getPlayer().hasPermission(ACPermission.OPEN_CRATE.toString())) {
							if(event.getPlayer().getItemInHand() == null) {
								event.setCancelled(true);
								event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.CANT_OPEN_CRATE.toString(true));
								return;
							}
							if(event.getItem() == null) {
								event.setCancelled(true);
								event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.CANT_OPEN_CRATE.toString(true));
								return;
							}
							int amount = event.getPlayer().getItemInHand().getAmount();
							ItemStack item = event.getItem();
							item.setAmount(1);
							String keyType = KeyFinder.findKeyType(item);
							if(keyType != null) {
								Crate crateGUI = new Crate();
								com.HamiStudios.ArchonCrates.API.Objects.Crate crate = null;
								try {
									crate = new com.HamiStudios.ArchonCrates.API.Objects.Crate(crateType);
								} catch (InvalidCrateInput e) {
									e.log(crateType);
									e.writeToFile(crateType);
								}
								if(crate == null) return;
								
								ArrayList<String> useableKeys = new ArrayList<>();
								for(Key k : crate.getUseableKeys()) useableKeys.add(k.getKeyType().toUpperCase());
								if(!useableKeys.contains(keyType.toUpperCase())) {
									if(!event.getPlayer().hasPermission(ACPermission.OPEN_CRATE_WITH_ANY_KEY.toString())) {
										event.setCancelled(true);
										event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + ChatColor.translateAlternateColorCodes('&', crate.getWrongKeyMessage()));
										return;
									}
								}
								
								Player player = event.getPlayer();
								if(amount == 1) {
									event.getPlayer().getInventory().removeItem(event.getPlayer().getItemInHand());
								}
								else{
									event.getPlayer().getItemInHand().setAmount(amount-1);
								}
								crateGUI.open(player, crateType, keyType, false, event.getClickedBlock().getLocation());
							}
							else{
								event.setCancelled(true);
								event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.CANT_OPEN_CRATE.toString(true));
								return;
							}
						}
						else{
							event.getPlayer().sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.OPEN_CRATE_NO_PERMISSION.toString(true));
							return;
						}
					}
					
				}
			}
		}
		else if(event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			
			int blockId = event.getClickedBlock().getTypeId();
			double blockX = event.getClickedBlock().getX();
			double blockY = event.getClickedBlock().getY();
			double blockZ = event.getClickedBlock().getZ();
			World world = event.getClickedBlock().getWorld();
			
			String crateType = CrateFinder.findCrateType(blockId, blockX, blockY, blockZ, world);
			
			if(FileHandler.getFile(FileType.CUSTOM_GUI).getBoolean("Chance GUI.use GUI")) {
				if(!event.getPlayer().isSneaking()) {
					if(crateType != null) {
						if(event.getPlayer().hasPermission(ACPermission.VIEW_PRIZES.toString())) {
							event.setCancelled(true);
							PrizeViewerGUI prizeGUI = new PrizeViewerGUI(event.getPlayer());
							prizeGUI.open();
						}
					}
				}
			}
			
		}
	}
	
}
