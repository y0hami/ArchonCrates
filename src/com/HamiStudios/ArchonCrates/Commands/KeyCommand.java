package com.HamiStudios.ArchonCrates.Commands;

import com.HamiStudios.ArchonCrates.API.Enums.LanguageType;
import com.HamiStudios.ArchonCrates.API.Enums.Menu;
import com.HamiStudios.ArchonCrates.API.Enums.Permissions;
import com.HamiStudios.ArchonCrates.API.Events.OnKeyGiven;
import com.HamiStudios.ArchonCrates.API.Libs.Glow;
import com.HamiStudios.ArchonCrates.API.Libs.HelpPageBuilder;
import com.HamiStudios.ArchonCrates.API.Libs.ItemBuilder;
import com.HamiStudios.ArchonCrates.API.Libs.LanguageManager;
import com.HamiStudios.ArchonCrates.API.Menus.KeyMenu;
import com.HamiStudios.ArchonCrates.API.Objects.ACPlayer;
import com.HamiStudios.ArchonCrates.API.Objects.ACSender;
import com.HamiStudios.ArchonCrates.API.Objects.Key;
import com.HamiStudios.ArchonCrates.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class KeyCommand implements Command {

	private Main main;

	public KeyCommand(Main main) {
		this.main = main;
	}


	@Override
	public void displayHelp(ACSender sender) {

		ArrayList<String> playerArgs = new ArrayList<>();
		playerArgs.add("Player name");
		playerArgs.add("all");

		ArrayList<String> amountArgs = new ArrayList<>();
		amountArgs.add("Amount of keys to give (no input will give 1)");

		new HelpPageBuilder("key")
				.setCommandDescription("This command allows you to give keys to one or all players.")
				.addArgument("player", false, playerArgs)
				.addArgument("amount", true, amountArgs)
				.setHelpURL("commands/key")
				.setExample("hammy2899 3")
				.send(sender);
	}

	@Override
	public void execCommand(String[] args, ACSender sender) {

		if(sender.hasPermission(Permissions.COMMAND_KEY.value())) {
			if(args.length == 0) {
				sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.COMMAND_KEY_INVALID_FORMAT));
			} else if(args.length == 1 || args.length == 2) {

				if(!sender.isConsole()) {

					Player player = (Player) sender.getSender();

					// Give key to all players
					if(args[0].equalsIgnoreCase("all")) {

						// Check if the sender has permission to give keys to all players
						if(player.hasPermission(Permissions.COMMAND_KEY_ALL.value())) {

							// If they have permission
							// Check if the amount they entered is a Integer

							int amount = 1;

							try {
								amount = Integer.parseInt(args[1]);
							} catch(NumberFormatException e) {
								sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.COMMAND_KEY_INVALID_AMOUNT));
							}

							this.main.getOperationsManager().addKeyGiver(new ACPlayer(player), null, true, amount);

							KeyMenu keyMenu = new KeyMenu(this.main);
							keyMenu.openMenu(player, Menu.KEY_TYPE);

						} else {
							// If they don't have permission
							sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.ERROR_NO_PERMISSION));
						}

					} else {
						// Give key to a single player
						if(Bukkit.getPlayer(args[0]) == null) {
							sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.COMMAND_KEY_PLAYER_OFFLINE));
						} else {

							// Check if the sender has permission to give keys to all players
							if(player.hasPermission(Permissions.COMMAND_KEY_PLAYER.value())) {

								// If they have permission
								// Check if the amount they entered is a Integer

								int amount = 1;

								try {
									amount = Integer.parseInt(args[1]);
								} catch(NumberFormatException e) {
									sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.COMMAND_KEY_INVALID_AMOUNT));
								}

								this.main.getOperationsManager().addKeyGiver(new ACPlayer(player), Bukkit.getPlayer(args[0]), false, amount);

								KeyMenu keyMenu = new KeyMenu(this.main);
								keyMenu.openMenu(player, Menu.KEY_TYPE);

							} else {
								// If they don't have permission
								sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.ERROR_NO_PERMISSION));
							}

						}
					}

				} else {
					sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.ERROR_PLAYER_ONLY_COMMAND));
				}

			} else if(args.length == 3) {
				sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.COMMAND_KEY_INVALID_FORMAT));
			} else if(args.length == 4) {

				//                      0		 1		 2	  3
				// /archoncrates key <player> [amount] [key <type>]

				boolean giveAll = false;
				Player givePlayer = null;

				if (args[0].equalsIgnoreCase("all")) {
					giveAll = true;
				} else {
					givePlayer = Bukkit.getPlayer(args[0]);
				}


				if (!args[3].equalsIgnoreCase("physical")
						&& !args[3].equalsIgnoreCase("p")
						&& !args[3].equalsIgnoreCase("virtual")
						&& !args[3].equalsIgnoreCase("v")) {
					sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.COMMAND_KEY_INVALID_KEY_TYPE));
				} else {

					if(givePlayer == null) {
						sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.COMMAND_KEY_PLAYER_OFFLINE));
					} else {

						try {
							int amount = Integer.parseInt(args[1]);

							// Get the key at the index of the slot they player clicks
							Key key = new Key(args[2]);

							if(key.valid()) {

								if(giveAll) {
									// Check if the player has permission to give keys to all players
									if (sender.hasPermission(Permissions.COMMAND_KEY_ALL.value())) {

										if (args[3].equalsIgnoreCase("physical") || args[3].equalsIgnoreCase("p")) {

											for (Player player : Bukkit.getOnlinePlayers()) {
												ItemBuilder keyItem = new ItemBuilder()
														.setMaterial(Material.getMaterial(key.getItemID()))
														.setName(key.getName())
														.setData((short) key.getItemData())
														.setLore(key.getLore())
														.setAmount(amount);

												if(key.glow()) {
													keyItem.addEnchantment(new Glow(99), 1, true);
												}

												player.getInventory().addItem(keyItem.build());

												// Call KeyGiven Event
												this.main.getServer().getPluginManager().callEvent(new OnKeyGiven(null, new ACPlayer(player), key, false));
											}

											// Send the player a message so they know the key has been added to their inventory
											sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.EVENT_KEY_GIVEN_ALL)
													.replaceAll("<key>", key.getID())
													.replaceAll("<amount>", amount + ""));

										} else if(args[3].equalsIgnoreCase("virtual") || args[3].equalsIgnoreCase("v")) {

											// For all the online players add a key to there inventory
											for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {

												ACPlayer acPlayer = new ACPlayer(onlinePlayer);
												acPlayer.addVirtualKey(key, amount);

												// Cal KeyGiven Event
												this.main.getServer().getPluginManager().callEvent(new OnKeyGiven(null, acPlayer, key, true));

											}

											// Send the player a message so they know the key has been added to their inventory
											sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.EVENT_KEY_GIVEN_ALL)
													.replaceAll("<key>", key.getID())
													.replaceAll("<amount>", amount + ""));

										}

									} else {
										// No permission
										sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.EVENT_KEY_NO_PERMISSION));
									}
								} else {
									// Check if the player has permission to give keys to all players
									if (sender.hasPermission(Permissions.COMMAND_KEY_PLAYER.value())) {

										if (args[3].equalsIgnoreCase("physical") || args[3].equalsIgnoreCase("p")) {

											ItemBuilder keyItem = new ItemBuilder()
													.setMaterial(Material.getMaterial(key.getItemID()))
													.setName(key.getName())
													.setData((short) key.getItemData())
													.setLore(key.getLore())
													.setAmount(amount);

											if(key.glow()) {
												keyItem.addEnchantment(new Glow(99), 1, true);
											}

											givePlayer.getInventory().addItem(keyItem.build());

											// Send the player a message so they know the key has been added to their inventory
											sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.EVENT_KEY_GIVEN_PLAYER)
													.replaceAll("<key>", key.getID())
													.replaceAll("<amount>", amount + "")
													.replaceAll("<player>", givePlayer.getName()));

											// Call KeyGiven Event
											this.main.getServer().getPluginManager().callEvent(new OnKeyGiven(null, new ACPlayer(givePlayer), key, false));

										} else if(args[3].equalsIgnoreCase("virtual") || args[3].equalsIgnoreCase("v")) {

											// For all the online players add a key to there inventory
											ACPlayer acPlayer = new ACPlayer(givePlayer);
											acPlayer.addVirtualKey(key, amount);

											// Send the player a message so they know the key has been added to their inventory
											sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.EVENT_KEY_GIVEN_PLAYER)
													.replaceAll("<key>", key.getID())
													.replaceAll("<amount>", amount + "")
													.replaceAll("<player>", givePlayer.getName()));

											// Call KeyGiven Event
											this.main.getServer().getPluginManager().callEvent(new OnKeyGiven(null, new ACPlayer(givePlayer), key, true));

										}

									} else {
										// No permission
										sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.EVENT_KEY_NO_PERMISSION));
									}
								}

							} else {
								sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.COMMAND_KEY_INVALID_KEY));
							}

						} catch(NumberFormatException e) {
							sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.COMMAND_KEY_INVALID_AMOUNT));
						}

					}

				}

			}
		} else {
			sender.sendMessage(LanguageManager.getPrefix() + LanguageManager.get(LanguageType.EVENT_KEY_NO_PERMISSION));
		}

	}

}
