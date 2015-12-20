package com.HamiStudios.ArchonCrates.Tasks;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

import com.HamiStudios.ArchonCrates.Main;
import com.HamiStudios.ArchonCrates.API.Events.OnPlayerCrateOpen;
import com.HamiStudios.ArchonCrates.API.Events.OnPlayerCrateWin;
import com.HamiStudios.ArchonCrates.API.Events.OnPlayerVirtualCrateOpen;
import com.HamiStudios.ArchonCrates.API.Events.OnPlayerVirtualCrateWin;
import com.HamiStudios.ArchonCrates.API.Objects.Key;
import com.HamiStudios.ArchonCrates.API.Objects.Prize;
import com.HamiStudios.ArchonCrates.API.Objects.VirtualCrate;
import com.HamiStudios.ArchonCrates.API.Objects.VirtualKey;
import com.HamiStudios.ArchonCrates.API.Objects.Exceptions.InvalidCrateInput;
import com.HamiStudios.ArchonCrates.API.Objects.Exceptions.InvalidKeyInput;
import com.HamiStudios.ArchonCrates.API.Objects.Exceptions.InvalidVirtualCrateInput;
import com.HamiStudios.ArchonCrates.API.Objects.Exceptions.InvalidVirtualKeyInput;
import com.HamiStudios.ArchonCrates.Files.Data;
import com.HamiStudios.ArchonCrates.Files.FileHandler;
import com.HamiStudios.ArchonCrates.Util.ACPermission;
import com.HamiStudios.ArchonCrates.Util.FileType;
import com.HamiStudios.ArchonCrates.Util.LanguageType;
import com.HamiStudios.ArchonCrates.Util.LogType;
import com.HamiStudios.ArchonCrates.Util.MainGetter;
import com.HamiStudios.ArchonCrates.Util.VaultAPI;

public class Crate {

	private ArrayList<Prize> prizeList;
	private Key key;
	private VirtualKey vkey;
	private com.HamiStudios.ArchonCrates.API.Objects.Crate crate;
	private VirtualCrate vcrate;
	private Inventory crateGUI;
	private Main main;
	private Player player;
	
	private int lastRandom = -1;
	
	// Task IDs
	private int randomGlassTask;
	private int randomPrizeTask;
	private int effectTask;
	@SuppressWarnings("unused")
	private int crateDurationTask;
	@SuppressWarnings("unused")
	private int winDurationTask;
	
	public void open(final Player player, String crateType, String keyType, boolean virtual, final Location crateLocation) {
		
		this.prizeList = new ArrayList<>();
		try {
			this.key = new Key(keyType);
		} catch(InvalidKeyInput e) {
			e.log(keyType);
			e.writeToFile(keyType);
		}
		for(Prize p : key.getLoot()) {
			if(p.usePermission()) {
				if(player.hasPermission(p.getPermission())) prizeList.add(p);
				continue;
			}
			if(!p.getGlobalWinAmount().equalsIgnoreCase("*")) {
				int winAmount = Integer.parseInt(p.getGlobalWinAmount());
				if(Logger.getWinAmount(LogType.PRIZE, player, p) >= winAmount) continue;
			}
			if(!p.getPlayerWinAmount().equalsIgnoreCase("*")) {
				int winAmount = Integer.parseInt(p.getPlayerWinAmount());
				if(Logger.getWinAmount(LogType.PLAYER, player, p) >= winAmount) continue;
			}
			prizeList.add(p);
		}
		try {
			this.crate = new com.HamiStudios.ArchonCrates.API.Objects.Crate(crateType);
		} catch (InvalidCrateInput e) {
			e.log(crateType);
			e.writeToFile(crateType);
		}
		this.main = MainGetter.getMain();
		this.player = player;
		
		
		ArrayList<String> useableKeys = new ArrayList<>();
		for(Key k : crate.getUseableKeys()) useableKeys.add(k.getKeyType().toUpperCase());
		if(!useableKeys.contains(keyType.toUpperCase())) {
			if(!player.hasPermission(ACPermission.OPEN_CRATE_WITH_ANY_KEY.toString())) {
				player.sendMessage(LanguageType.PREFIX.toString(true) + ChatColor.translateAlternateColorCodes('&', crate.getWrongKeyMessage()));
				return;
			}
		}
		
		
		// ADD PLAYER TO DATA
		Data dataFile = new Data();
		dataFile.set("data.inCrate." + player.getUniqueId(), true);
		dataFile.save();
		
		
		// RUN OPEN EVENT
		Bukkit.getServer().getPluginManager().callEvent(new OnPlayerCrateOpen(player, crate, key));
		
		
		String GUITitle = ChatColor.GREEN + "Virtual Crate";
		if(!virtual) GUITitle = ChatColor.translateAlternateColorCodes('&', crate.getTitle());
		crateGUI = Bukkit.createInventory(player, 27, GUITitle);
		
		int x = 0;
		while(x != 27) {
			if(x == 13) {x++; continue;}
			ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
			ItemMeta glassMeta = glass.getItemMeta();
			Random random = new Random();
			int data = random.nextInt(15);
			glass.setDurability((short)data);
			glassMeta.setDisplayName(ChatColor.GREEN+"");
			glass.setItemMeta(glassMeta);
			crateGUI.setItem(x, glass);
			x++;
		}
		
		if(!crate.disableColouredGlass()) {
			randomGlassTask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
				@Override
				public void run() {
					int x = 0;
					while(x != 27) {
						if(x == 13) {x++; continue;}
						ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
						ItemMeta glassMeta = glass.getItemMeta();
						Random random = new Random();
						int data = random.nextInt(15);
						glass.setDurability((short)data);
						glassMeta.setDisplayName(ChatColor.GREEN+"");
						glass.setItemMeta(glassMeta);
						crateGUI.setItem(x, glass);
						x++;
					}
				}
			}, 0, 8L);
		}
		
		if(crate.effects()) {
			if(!virtual) {
				effectTask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
					@Override
					public void run() {
						Bukkit.getWorld(crateLocation.getWorld().getName()).playEffect(crateLocation, Effect.MOBSPAWNER_FLAMES, 1);
					}
				}, 0, 3L);
			}
			else{
				effectTask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
					@Override
					public void run() {
						Bukkit.getWorld(player.getWorld().getName()).playEffect(player.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
					}
				}, 0, 3L);
			}
		}
		
		randomPrizeTask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
			@Override
			public void run() {
				Random random = new Random();
				if(lastRandom == -1) {
					lastRandom = random.nextInt(prizeList.size());
					crateGUI.setItem(13, prizeList.get(lastRandom).getItem());
				}
				else { 
					int newRandom = lastRandom;
					while(lastRandom == newRandom) newRandom = random.nextInt(prizeList.size());
					crateGUI.setItem(13, prizeList.get(newRandom).getItem());
					lastRandom = newRandom;
				}
				player.playSound(player.getLocation(), Sound.valueOf(crate.getScrollSound()), 1, 1);
			}
		}, 0, crate.getPrizeDisplayDuration());
		
		crateDurationTask = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
			@Override
			public void run() {
				Bukkit.getServer().getScheduler().cancelTask(randomGlassTask);
				Bukkit.getServer().getScheduler().cancelTask(randomPrizeTask);
				Bukkit.getServer().getScheduler().cancelTask(effectTask);
				runWinTask();
			}
		}, crate.getOpenDuration()*20L);
		
		player.openInventory(crateGUI);
	}
	
	private void runWinTask() {
		
		// Closes the menu after 3 seconds
		winDurationTask = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
			@Override
			public void run() {
				if(crateGUI.getViewers().contains(player)) player.closeInventory();
			}
		}, 3*20L);
		
		
		// REMOVE PLAYER TO DATA
		Data dataFile = new Data();
		dataFile.set("data.inCrate." + player.getUniqueId(), false);
		dataFile.save();
		
		
		// Chance system
		Random random = new Random();
		double max = 0;
		for(Prize p : prizeList) if(p.getChance() > max) max = p.getChance();
		DecimalFormat df = new DecimalFormat("####.##");
		String randomChanceNum = df.format(0+max*random.nextDouble());
		double chanceNum = Double.parseDouble(randomChanceNum);
		
		ArrayList<Prize> winablePrizes = new ArrayList<>();
		for(Prize p : prizeList) {
			double chance = p.getChance();
			if(chanceNum <= chance) winablePrizes.add(p);
		}
		
		Prize wonPrize;
		if(winablePrizes.size() > 1) {
			int prizeToPick = random.nextInt(winablePrizes.size());
			wonPrize = winablePrizes.get(prizeToPick);
		}
		else{wonPrize = winablePrizes.get(0);}
	
		
		// RUN WIN EVENT
		Bukkit.getServer().getPluginManager().callEvent(new OnPlayerCrateWin(player, crate, key, wonPrize));
		
		
		// Changes background glass
		int x = 0;
		while(x != 27) {
			if(x == 13) {x++; continue;}
			ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
			ItemMeta glassMeta = glass.getItemMeta();
			glass.setDurability((short)15);
			glassMeta.setDisplayName(ChatColor.GREEN+"");
			glass.setItemMeta(glassMeta);
			crateGUI.setItem(x, glass);
			x++;
		}
		crateGUI.setItem(13, wonPrize.getItem());
		
		// Messages/Sound
		player.playSound(player.getLocation(), Sound.valueOf(crate.getWinSound()), 1, 1);
		if(wonPrize.broadcastWin()) Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', LanguageType.PREFIX.toString(true) + 
				key.getWinMessage().replaceAll("<player>", player.getName()).replaceAll("<prize>", wonPrize.getPrizeName())));
		if(wonPrize.playerMessage()) player.sendMessage(ChatColor.translateAlternateColorCodes('&', LanguageType.PREFIX.toString(true) + 
				key.getPlayerMessage().replaceAll("<player>", player.getName()).replaceAll("<prize>", wonPrize.getPrizeName())));
		
		// Commands
		if(FileHandler.getFile(FileType.CRATE_LOOT).contains("Crate Loot." + wonPrize.getPrizeID() + ".Use Sub Commands")) {
			if(VaultAPI.useSubCommands(wonPrize, player)) {
				for(String s : wonPrize.getSubCommands()) Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), s.replaceAll("<player>", player.getName()));
			}
			else {
				for(String s : wonPrize.getCommands()) Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), s.replaceAll("<player>", player.getName()));
			}
		}
		else{
			for(String s : wonPrize.getCommands()) Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), s.replaceAll("<player>", player.getName()));
		}
		
		// Fireworks
		if(crate.firework()) {
			Firework firework = (Firework) player.getWorld().spawn(player.getLocation(), Firework.class);
			FireworkMeta fireworkMeta = firework.getFireworkMeta();
			fireworkMeta.addEffect(FireworkEffect.builder().flicker(true).trail(true).with(Type.BALL).withColor(Color.YELLOW).withColor(Color.AQUA).withColor(Color.RED).build());
			fireworkMeta.setPower(1);
			firework.setFireworkMeta(fireworkMeta);
		}
		
		// Logs data
		Logger.log(LogType.PLAYER, player, wonPrize, crate, key);
		Logger.log(LogType.PRIZE, player, wonPrize, crate, key);
		
	}
	
	public void openVirtual(final Player player, String vkeyType) {
		
		this.prizeList = new ArrayList<>();
		try {
			this.vkey = new VirtualKey(vkeyType);
		} catch (InvalidVirtualKeyInput e) {
			e.log(vkeyType);
			e.writeToFile(vkeyType);
		}
		for(Prize p : vkey.getLoot()) {
			if(p.usePermission()) {
				if(player.hasPermission(p.getPermission())) prizeList.add(p);
				continue;
			}
			if(!p.getGlobalWinAmount().equalsIgnoreCase("*")) {
				int winAmount = Integer.parseInt(p.getGlobalWinAmount());
				if(Logger.getWinAmount(LogType.PRIZE, player, p) >= winAmount) continue;
			}
			if(!p.getPlayerWinAmount().equalsIgnoreCase("*")) {
				int winAmount = Integer.parseInt(p.getPlayerWinAmount());
				if(Logger.getWinAmount(LogType.PLAYER, player, p) >= winAmount) continue;
			}
			prizeList.add(p);
		}
		this.main = MainGetter.getMain();
		this.player = player;
		try {
			this.vcrate = new VirtualCrate();
		} catch (InvalidVirtualCrateInput e) {
			e.log();
			e.writeToFile();
		}
		
		
		// ADD PLAYER TO DATA
		Data dataFile = new Data();
		dataFile.set("data.inCrate." + player.getUniqueId(), true);
		dataFile.save();
		
		
		// RUN OPEN EVENT
		Bukkit.getServer().getPluginManager().callEvent(new OnPlayerVirtualCrateOpen(player, vcrate, key));
		
		
		crateGUI = Bukkit.createInventory(player, 27, ChatColor.translateAlternateColorCodes('&', vcrate.getTitle()));
		
		int x = 0;
		while(x != 27) {
			if(x == 13) {x++; continue;}
			ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
			ItemMeta glassMeta = glass.getItemMeta();
			Random random = new Random();
			int data = random.nextInt(15);
			glass.setDurability((short)data);
			glassMeta.setDisplayName(ChatColor.GREEN+"");
			glass.setItemMeta(glassMeta);
			crateGUI.setItem(x, glass);
			x++;
		}
		
		if(!vcrate.disableColouredGlass()) {
			randomGlassTask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
				@Override
				public void run() {
					int x = 0;
					while(x != 27) {
						if(x == 13) {x++; continue;}
						ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
						ItemMeta glassMeta = glass.getItemMeta();
						Random random = new Random();
						int data = random.nextInt(15);
						glass.setDurability((short)data);
						glassMeta.setDisplayName(ChatColor.GREEN+"");
						glass.setItemMeta(glassMeta);
						crateGUI.setItem(x, glass);
						x++;
					}
				}
			}, 0, 8L);
		}
		
		randomPrizeTask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
			@Override
			public void run() {
				Random random = new Random();
				if(lastRandom == -1) {
					lastRandom = random.nextInt(prizeList.size());
					crateGUI.setItem(13, prizeList.get(lastRandom).getItem());
				}
				else { 
					int newRandom = lastRandom;
					while(lastRandom == newRandom) newRandom = random.nextInt(prizeList.size());
					crateGUI.setItem(13, prizeList.get(newRandom).getItem());
					lastRandom = newRandom;
				}
				player.playSound(player.getLocation(), Sound.valueOf(vcrate.getScrollSound()), 1, 1);
			}
		}, 0, vcrate.getPrizeDisplayDuration());
		
		crateDurationTask = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
			@Override
			public void run() {
				Bukkit.getServer().getScheduler().cancelTask(randomGlassTask);
				Bukkit.getServer().getScheduler().cancelTask(randomPrizeTask);
				Bukkit.getServer().getScheduler().cancelTask(effectTask);
				runVirtualWinTask();
			}
		}, vcrate.getOpenDuration()*20L);
		
		player.openInventory(crateGUI);
	}
	
	private void runVirtualWinTask() {
		
		// Closes the menu after 3 seconds
		winDurationTask = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
			@Override
			public void run() {
				if(crateGUI.getViewers().contains(player)) player.closeInventory();
			}
		}, 3*20L);
		
		
		// REMOVE PLAYER TO DATA
		Data dataFile = new Data();
		dataFile.set("data.inCrate." + player.getUniqueId(), false);
		dataFile.save();
		
		
		// Chance system
		Random random = new Random();
		double max = 0;
		for(Prize p : prizeList) if(p.getChance() > max) max = p.getChance();
		DecimalFormat df = new DecimalFormat("####.##");
		String randomChanceNum = df.format(0+max*random.nextDouble());
		double chanceNum = Double.parseDouble(randomChanceNum);
		
		ArrayList<Prize> winablePrizes = new ArrayList<>();
		for(Prize p : prizeList) {
			double chance = p.getChance();
			if(chanceNum <= chance) winablePrizes.add(p);
		}
		
		Prize wonPrize = null;
		while(wonPrize == null) {
			if(winablePrizes.size() > 1) {
				int prizeToPick = random.nextInt(winablePrizes.size());
				wonPrize = winablePrizes.get(prizeToPick);
			}
			else{wonPrize = winablePrizes.get(0);}
			
			if(wonPrize.useSubCommands()) {
				if(Logger.hasWon(player, wonPrize)) {
					wonPrize = null;
				}
			}
		}
	
		
		// RUN WIN EVENT
		Bukkit.getServer().getPluginManager().callEvent(new OnPlayerVirtualCrateWin(player, vcrate, key, wonPrize));
		
		
		// Changes background glass
		int x = 0;
		while(x != 27) {
			if(x == 13) {x++; continue;}
			ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
			ItemMeta glassMeta = glass.getItemMeta();
			glass.setDurability((short)15);
			glassMeta.setDisplayName(ChatColor.GREEN+"");
			glass.setItemMeta(glassMeta);
			crateGUI.setItem(x, glass);
			x++;
		}
		crateGUI.setItem(13, wonPrize.getItem());
		
		// Messages/Sound
		player.playSound(player.getLocation(), Sound.valueOf(vcrate.getWinSound()), 1, 1);
		if(wonPrize.broadcastWin()) Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', LanguageType.PREFIX.toString(true) + 
				vkey.getWinMessage().replaceAll("<player>", player.getName()).replaceAll("<prize>", wonPrize.getPrizeName())));
		if(wonPrize.playerMessage()) player.sendMessage(ChatColor.translateAlternateColorCodes('&', LanguageType.PREFIX.toString(true) + 
				vkey.getPlayerMessage().replaceAll("<player>", player.getName()).replaceAll("<prize>", wonPrize.getPrizeName())));
		
		// Commands
		if(FileHandler.getFile(FileType.CRATE_LOOT).contains("Crate Loot." + wonPrize.getPrizeID() + ".Use Sub Commands")) {
			if(VaultAPI.useSubCommands(wonPrize, player)) {
				for(String s : wonPrize.getSubCommands()) Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), s.replaceAll("<player>", player.getName()));
			}
			else {
				for(String s : wonPrize.getCommands()) Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), s.replaceAll("<player>", player.getName()));
			}
		}
		else{
			for(String s : wonPrize.getCommands()) Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), s.replaceAll("<player>", player.getName()));
		}
		
		// Fireworks
		if(vcrate.firework()) {
			Firework firework = (Firework) player.getWorld().spawn(player.getLocation(), Firework.class);
			FireworkMeta fireworkMeta = firework.getFireworkMeta();
			fireworkMeta.addEffect(FireworkEffect.builder().flicker(true).trail(true).with(Type.BALL).withColor(Color.YELLOW).withColor(Color.AQUA).withColor(Color.RED).build());
			fireworkMeta.setPower(1);
			firework.setFireworkMeta(fireworkMeta);
		}
		
		// Logs data
		Logger.logV(LogType.PLAYER, player, wonPrize, vcrate, vkey);
		Logger.logV(LogType.PRIZE, player, wonPrize, vcrate, vkey);
		
	}
	
}
