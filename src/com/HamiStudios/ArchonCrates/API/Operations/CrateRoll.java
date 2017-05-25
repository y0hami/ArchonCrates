package com.HamiStudios.ArchonCrates.API.Operations;

import com.HamiStudios.ArchonCrates.API.Enums.Database;
import com.HamiStudios.ArchonCrates.API.Enums.LanguageType;
import com.HamiStudios.ArchonCrates.API.Enums.Tables;
import com.HamiStudios.ArchonCrates.API.Exceptions.InvalidWinAmount;
import com.HamiStudios.ArchonCrates.API.Libs.DatabaseHandler;
import com.HamiStudios.ArchonCrates.API.Libs.ItemBuilder;
import com.HamiStudios.ArchonCrates.API.Libs.LanguageManager;
import com.HamiStudios.ArchonCrates.API.Objects.*;
import com.HamiStudios.ArchonCrates.Main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.FireworkMeta;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class CrateRoll {

	private Main main;

	private ACPlayer player;
	private Key usedKey;
	private ArrayList<Prize> prizes;
	private Prize lastPrize;
	private Inventory crateMenu;
	private boolean canExit = false;

	// Crate Variables
	private int scrollDuration;
	private Sound scrollSound;
	private Sound winSound;
	private boolean firework;
	private boolean broadcast;
	private boolean sendPlayerMessage;
	private String playerMessage;
	private String broadcastMessage;
	private String crateID;
	private int showcaseDuration;


	// Tasks
	private int glassTask = -1;
	private int effectTask = -1;
	private int scrollTask1 = -1;
	private int scrollTask2 = -1;
	private int scrollTask3 = -1;
	private int scrollTask4 = -1;
	private int scrollTask5 = -1;
	private int stopTask = -1;
	private int autoMenuCloser = -1;


	public CrateRoll(Main main) {
		this.main = main;
	}


	public void roll(ACPlayer player, Crate crate, Key usedKey, Block block) {

		player.getPlayer().closeInventory();

		// Set instances of the object passed to the roll method
		this.player = player;
		this.usedKey = usedKey;
		this.prizes = crate.getPrizes();


		// Set Crate Variables
		this.scrollDuration = crate.getScrollDuration();
		this.scrollSound = crate.getScrollSound();
		this.winSound = crate.getWinSound();
		this.firework = crate.firework();
		this.broadcast = crate.broadcast();
		this.sendPlayerMessage = crate.playerMessage();
		this.playerMessage = crate.getPlayerMessage();
		this.broadcastMessage = crate.getBroadcastMessage();
		this.crateID = crate.getID();
		this.showcaseDuration = crate.getShowcaseDuration();

		// Create the crate menu
		this.crateMenu = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', crate.getTitle()));

		// For all slots but the middle one, set it to a black glass pane
		fillBlankPanes();

		// If the crate should use random coloured glass, run the random glass method
		if(crate.showColouredGlass()) { runRandomGlass(); }

		// If the crate should display effects
		if(crate.displayEffects()) { runEffects(block.getLocation()); }

		// Add the new Crate Roller
		this.main.getOperationsManager().addCrateRoller(this);

		// Rotate the prizes to display a random prize
		rotatePrizes(block.getLocation());

		// Play the crate open sound
		playSound(player.getPlayer().getLocation(), crate.getOpenSound());

		// Open the crate menu
		player.getPlayer().openInventory(this.crateMenu);
	}

	public void roll(ACPlayer player, VirtualCrate crate, Key usedKey, Block block) {

		player.getPlayer().closeInventory();

		// Set instances of the object passed to the roll method
		this.player = player;
		this.usedKey = usedKey;

		for (HashMap.Entry<Key, ArrayList<Prize>> key : crate.getKeys().entrySet()) {
			if(key.getKey().getID().equals(usedKey.getID())) {
				this.prizes = key.getValue();
				break;
			}
		}


		// Set Crate Variables
		this.scrollDuration = crate.getScrollDuration();
		this.scrollSound = crate.getScrollSound();
		this.winSound = crate.getWinSound();
		this.firework = crate.firework();
		this.broadcast = crate.broadcast();
		this.sendPlayerMessage = crate.playerMessage();
		this.playerMessage = crate.getPlayerMessage();
		this.broadcastMessage = crate.getBroadcastMessage();
		this.crateID = "Virtual";
		this.showcaseDuration = crate.getShowcaseDuration();


		// Create the crate menu
		this.crateMenu = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', crate.getTitle()));

		// For all slots but the middle one, set it to a black glass pane
		fillBlankPanes();

		// If the crate should use random coloured glass, run the random glass method
		if(crate.showColouredGlass()) { runRandomGlass(); }

		// If the crate should display effects
		if(crate.displayEffects()) { runEffects(block.getLocation()); }

		// Add the new Crate Roller
		this.main.getOperationsManager().addCrateRoller(this);

		// Rotate the prizes to display a random prize
		rotatePrizes(block.getLocation());

		// Play the crate open sound
		playSound(player.getPlayer().getLocation(), crate.getOpenSound());

		// Open the crate menu
		player.getPlayer().openInventory(this.crateMenu);
	}


	// Get the Crate Roller player
	public ACPlayer getPlayer() { return this.player; }

	// If the player can exit the menu
	public boolean canExit() { return this.canExit; }

	// Set if the player can exit the menu
	public void setCanExit(boolean value) { this.canExit = value; }

	// Cancel the auto menu closer
	public void cancelAutoClose() { Bukkit.getServer().getScheduler().cancelTask(this.autoMenuCloser); }


	// Common Functions used in the crates

	// Fill in the blank spaces in the crate menu
	private void fillBlankPanes() {
		int slot = 0;
		while(slot != 27) {
			if(slot == 13) { slot++; continue; }
			this.crateMenu.setItem(slot, new ItemBuilder()
					.setMaterial(Material.STAINED_GLASS_PANE)
					.setData((short) 15)
					.setName("&f")
					.build());

			slot++;
		}
	}

	// Random coloured glass panes
	private void runRandomGlass() {
		this.glassTask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.main, new Runnable() {
			@Override
			public void run() {
				int slot = 0;
				while(slot != 27) {
					if(slot == 13) { slot++; continue; }
					crateMenu.setItem(slot, new ItemBuilder()
							.setMaterial(Material.STAINED_GLASS_PANE)
							.setData((short) new Random().nextInt(15))
							.setName("&f")
							.build());

					slot++;
				}
			}
		}, 0, 6L);
	}

	// Block effects
	private void runEffects(final Location location) {
		this.effectTask = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.main, new Runnable() {
			@Override
			public void run() {
				player.getPlayer().getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 1);
			}
		}, 0, 5L);
	}

	// For the possible prizes rotate them in a random order
	private void rotatePrizes(Location location) {

		if(this.scrollDuration == -1) {

			this.stopTask = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.main, new Runnable() {
				@Override
				public void run() {
					if(glassTask != -1) { Bukkit.getServer().getScheduler().cancelTask(glassTask); }
					if(effectTask != -1) { Bukkit.getServer().getScheduler().cancelTask(effectTask); }
					if(scrollTask5 != -1) { Bukkit.getServer().getScheduler().cancelTask(scrollTask5); }

					generateWinningPrize();

					setCanExit(true);
				}
			}, 6*20L);

			scroll(true, location);

		} else {

			this.stopTask = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.main, new Runnable() {
				@Override
				public void run() {
					if(glassTask != -1) { Bukkit.getServer().getScheduler().cancelTask(glassTask); }
					if(effectTask != -1) { Bukkit.getServer().getScheduler().cancelTask(effectTask); }
					if(scrollTask5 != -1) { Bukkit.getServer().getScheduler().cancelTask(scrollTask5); }

					generateWinningPrize();

					setCanExit(true);
				}
			}, this.scrollDuration*20L);

			scroll(false, location);

		}

	}

	// Run the prize scroll display task
	private void scroll(boolean faster, final Location location) {

		if(faster) {
			// Random prizes display faster an fast as the crate goes on

			this.scrollTask1 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.main, new Runnable() {
				@Override
				public void run() {
					displayPrize(getRandomPrize());
					playSound(location, scrollSound);
				}
			}, 0, 10L);

			this.scrollTask2 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.main, new Runnable() {
				@Override
				public void run() {
					Bukkit.getServer().getScheduler().cancelTask(scrollTask1);

					displayPrize(getRandomPrize());
					playSound(location, scrollSound);
				}
			}, 2*20L, 8L);

			this.scrollTask3 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.main, new Runnable() {
				@Override
				public void run() {
					Bukkit.getServer().getScheduler().cancelTask(scrollTask2);

					displayPrize(getRandomPrize());
					playSound(location, scrollSound);
				}
			}, 3*20L, 6L);

			this.scrollTask4 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.main, new Runnable() {
				@Override
				public void run() {
					Bukkit.getServer().getScheduler().cancelTask(scrollTask3);

					displayPrize(getRandomPrize());
					playSound(location, scrollSound);
				}
			}, 4*20L, 4L);

			this.scrollTask5 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.main, new Runnable() {
				@Override
				public void run() {
					Bukkit.getServer().getScheduler().cancelTask(scrollTask4);

					displayPrize(getRandomPrize());
					playSound(location, scrollSound);
				}
			}, 5*20L, 2L);

		} else {
			// Random prizes display randomly evenly

			this.scrollTask5 = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.main, new Runnable() {
				@Override
				public void run() {
					displayPrize(getRandomPrize());
					playSound(location, scrollSound);
				}
			}, 0, 8L);
		}

	}

	// Get a random prize which is not the last prize
	private Prize getRandomPrize() {
		if(this.lastPrize == null) {
			Prize prize = this.prizes.get(new Random().nextInt(this.prizes.size()));
			this.lastPrize = prize;
			return prize;
		} else {
			Prize prize = this.prizes.get(new Random().nextInt(this.prizes.size()));
			if(this.prizes.size() > 1) {
				while(prize == this.lastPrize) {
					prize = this.prizes.get(new Random().nextInt(this.prizes.size()));
				}
			}

			this.lastPrize = prize;

			return prize;
		}
	}

	// Play the scroll sound for the crate
	private void playSound(Location location, Sound sound) {
		this.player.getPlayer().playSound(location, sound, 1, 1);
	}

	// Display a random prize in the middle of the crate
	private void displayPrize(Prize prize) {
		this.crateMenu.setItem(13, new ItemBuilder()
				.setMaterial(Material.getMaterial(prize.getItemID()))
				.setData((short) prize.getItemData())
				.setName(prize.getName())
				.setAmount(prize.getItemStackSize())
			.build());
	}

	// Get a valid global win amount or throw the invalid win amount exception
	private int getGlobalWinAmount(Prize prize) throws InvalidWinAmount {
		if(!prize.getGlobalWinAmount().equalsIgnoreCase("*")) {
			try {
				return Integer.parseInt(prize.getGlobalWinAmount());
			} catch (NumberFormatException e) {
				throw new InvalidWinAmount(prize.getID(), prize.getGlobalWinAmount());
			}
		}
		return -1;
	}

	// Get a valid player win amount or throw the invalid win amount exception
	private int getPlayerWinAmount(Prize prize) throws InvalidWinAmount {
		if(!prize.getPlayerWinAmount().equalsIgnoreCase("*")) {
			try {
				return Integer.parseInt(prize.getPlayerWinAmount());
			} catch (NumberFormatException e) {
				throw new InvalidWinAmount(prize.getID(), prize.getPlayerWinAmount());
			}
		}
		return -1;
	}

	// Get a winning prize
	private void generateWinningPrize() {

		/*

			--------------------------
			|  The chance algorithm  |
			--------------------------

			Get the highest possible chance from all the prizes in the crate opened.
			Get a random double between 0 and the highest chance. (chanceEqualizer)
			For all the possible prizes if the prize chance is larger or equal to the chanceEqualizer add it to a possiblePrizes array.
			Get a random integer between 0 and the possiblePrizes array size and get the prize at that index.

			The prize a that index is the players winning prize.

		 */

		// Get the highest chance in all of the winnable prizes
		double highestChance = 0.01;
		for (Prize prize : this.prizes) {
			// Only checks if the chance is higher if the player has permission to win that prize
			if(prize.usePermission()) {
				if(!this.player.hasPermission(prize.getPermission())) {
					continue;
				}
			}

			// Test for global & player win amounts
			try {
				// Test for global prize win amounts
				if(getGlobalWinAmount(prize) != -1) {
					ArrayList<HashMap<String, Object>> rows = DatabaseHandler.executeQuery(Database.PLAYERS_DATA, Tables.WIN_DATA, "SELECT COUNT(*) AS `TOTAL` FROM `wins` WHERE `PRIZE_ID`='" + prize.getID() + "'", new String[]{"TOTAL"});
					if(getGlobalWinAmount(prize) <= Integer.parseInt(rows.get(0).get("TOTAL") + "")) {
						continue;
					}
				}

				// Test for player prize win amounts
				if(getPlayerWinAmount(prize) != -1) {
					ArrayList<HashMap<String, Object>> rows = DatabaseHandler.executeQuery(Database.PLAYERS_DATA, Tables.WIN_DATA, "SELECT COUNT(*) AS `TOTAL` FROM `wins` WHERE `PRIZE_ID`='" + prize.getID() + "' AND `UUID`='" + this.player.getUUID() + "'", new String[]{"TOTAL"});
					if(getPlayerWinAmount(prize) <= Integer.parseInt(rows.get(0).get("TOTAL") + "")) {
						continue;
					}
				}
			} catch (InvalidWinAmount e) { continue; }

			if(prize.getChance() > highestChance) {
				highestChance = prize.getChance();
			}
		}

		// Get an instance of Random
		Random random = new Random();

		// Generate the chanceEqualizer
		double chanceEqualizer = 0 + (highestChance - 0) * random.nextDouble();

		// Create the possiblePrizes array
		ArrayList<Prize> possiblePrizes = new ArrayList<>();

		// All the prizes where the chance is larger or equal to the chanceEqualizer
		for (Prize prize : this.prizes) {
			// Only adds the prize to the possible winnable prizes if the player has permission to win the prize
			if(prize.usePermission()) {
				if(!this.player.hasPermission(prize.getPermission())) {
					continue;
				}
			}

			// Test for global & player win amounts
			try {
				// Test for global prize win amounts
				if(getGlobalWinAmount(prize) != -1) {
					ArrayList<HashMap<String, Object>> rows = DatabaseHandler.executeQuery(Database.PLAYERS_DATA, Tables.WIN_DATA, "SELECT COUNT(*) AS `TOTAL` FROM `wins` WHERE `PRIZE_ID`='" + prize.getID() + "'", new String[]{"TOTAL"});
					if(getGlobalWinAmount(prize) <= Integer.parseInt(rows.get(0).get("TOTAL") + "")) {
						continue;
					}
				}

				// Test for player prize win amounts
				if(getPlayerWinAmount(prize) != -1) {
					ArrayList<HashMap<String, Object>> rows = DatabaseHandler.executeQuery(Database.PLAYERS_DATA, Tables.WIN_DATA, "SELECT COUNT(*) AS `TOTAL` FROM `wins` WHERE `PRIZE_ID`='" + prize.getID() + "' AND `UUID`='" + this.player.getUUID() + "'", new String[]{"TOTAL"});
					if(getPlayerWinAmount(prize) <= Integer.parseInt(rows.get(0).get("TOTAL") + "")) {
						continue;
					}
				}
			} catch (InvalidWinAmount e) { continue; }

			if(prize.getChance() >= chanceEqualizer) {
				possiblePrizes.add(prize);
			}
		}

		// Get the random winning prize index
		int prizeIndex = random.nextInt(possiblePrizes.size());

		// Get the prize from the index
		Prize winningPrize = possiblePrizes.get(prizeIndex);

		// Display the winning prize
		displayPrize(winningPrize);
		fillBlankPanes();


		// For all the commands for the winning prize
		for (String command : winningPrize.getCommands()) {
			// Execute them as a ConsoleSender
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command.replaceAll("<player>", this.player.getName()));
		}


		// If the crate should spawn a firework
		if(this.firework) {
			// Spawn a firework at the players location
			Firework firework = this.player.getPlayer().getWorld().spawn(this.player.getPlayer().getLocation(), Firework.class);

			// Create an instance of the fireworks meta
			FireworkMeta fireworkMeta = firework.getFireworkMeta();

			// Create the firework effects
			fireworkMeta.addEffect(FireworkEffect.builder()
					.flicker(true)
					.trail(true)
					.with(FireworkEffect.Type.BALL)
					.withColor(Color.YELLOW)
					.withColor(Color.AQUA)
					.withColor(Color.RED)
				.build());

			// Set the firework power
			fireworkMeta.setPower(1);

			// Sets the firework meta
			firework.setFireworkMeta(fireworkMeta);
		}


		// Play the win sound
		playSound(this.player.getPlayer().getLocation(), this.winSound);


		// If the crate allows broadcasting on win
		if(this.broadcast) {
			// If the server should broadcast a win when a player wins
			if(winningPrize.broadcast()) {
				Bukkit.getServer().broadcastMessage(LanguageManager.get(LanguageType.PREFIX) +
						ChatColor.translateAlternateColorCodes('&', this.broadcastMessage
								.replaceAll("<player>", this.player.getName())
								.replaceAll("<prize_name>", ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', winningPrize.getName())))
								.replaceAll("<crate_id>", ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', this.crateID)))
						)
				);
			}
		}

		// If the crate allows player messaging
		if(this.sendPlayerMessage) {
			this.player.sendMessage(LanguageManager.get(LanguageType.PREFIX) +
					ChatColor.translateAlternateColorCodes('&', this.playerMessage
							.replaceAll("<prize_name>", ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', winningPrize.getName())))
							.replaceAll("<crate_id>", ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', this.crateID)))
					)
			);
		}


		// Create a delayed task to close the showcase menu after the showcase duration (Only will execute if the player hasn't already closed the menu)
		this.autoMenuCloser = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.main, new Runnable() {
			@Override
			public void run() {
				player.getPlayer().closeInventory();
			}
		}, this.showcaseDuration*20L);


		// Insert win into players database

		// Get ISO date
		TimeZone timeZone = TimeZone.getTimeZone("UTC");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		dateFormat.setTimeZone(timeZone);
		String date = dateFormat.format(new Date());

		// Insert into database
		DatabaseHandler.execute(Database.PLAYERS_DATA, Tables.WIN_DATA, "INSERT INTO `wins` (`UUID`, `KEY_ID`, `CRATE_TYPE`, `CHANCE_EQUALIZER`, `PRIZE_ID`, `WIN_TIME`)" +
				"VALUES" +
				"('" + this.player.getUUID() + "', '" + this.usedKey.getID() + "', '" + this.crateID + "', '" + chanceEqualizer + "', '" + winningPrize.getID() + "', '" + date + "')");

	}

}
