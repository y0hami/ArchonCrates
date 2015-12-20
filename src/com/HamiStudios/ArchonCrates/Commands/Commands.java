package com.HamiStudios.ArchonCrates.Commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.HamiStudios.ArchonCrates.API.Objects.VirtualCrate;
import com.HamiStudios.ArchonCrates.API.Objects.Exceptions.InvalidCrateInput;
import com.HamiStudios.ArchonCrates.API.Objects.Exceptions.InvalidVirtualCrateInput;
import com.HamiStudios.ArchonCrates.Files.FileHandler;
import com.HamiStudios.ArchonCrates.Util.ACPermission;
import com.HamiStudios.ArchonCrates.Util.CrateFinder;
import com.HamiStudios.ArchonCrates.Util.FileType;
import com.HamiStudios.ArchonCrates.Util.KeyFinder;
import com.HamiStudios.ArchonCrates.Util.LanguageType;
import com.HamiStudios.ArchonCrates.Util.PlayerData;
import com.HamiStudios.ArchonCrates.Util.ReloadType;

public class Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("archoncrates")) {
			
			if(args.length == 0) {
				if(sender.hasPermission(ACPermission.COMMAND_HELP.toString())) {
					Help.run(sender);
					return true;
				}
				else{
					sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.NO_PERMISSION.toString(true));
					return true;
				}
			}
			else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("remove")) {
					if(sender.hasPermission(ACPermission.COMMAND_REMOVE.toString())) {
						if(sender instanceof Player) {
							Player player = (Player) sender;
							Remove.run(player);
						}
						else{
							sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.PLAYER_ONLY_COMMAND.toString(true));
							return true;
						}
						return true;
					}
					else{
						sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.NO_PERMISSION.toString(true));
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("info")) {
					if(sender.hasPermission(ACPermission.COMMAND_INFO.toString())) {
						Info.run(sender);
						return true;
					}
					else{
						sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.NO_PERMISSION.toString(true));
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("keys")) {
					if(sender.hasPermission(ACPermission.COMMAND_KEYS.toString())) {
						ListKeys.run(sender);
						return true;
					}
					else{
						sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.NO_PERMISSION.toString(true));
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("vkeys")) {
					if(sender.hasPermission(ACPermission.COMMAND_KEYS.toString())) {
						ListVKeys.run(sender);
						return true;
					}
					else{
						sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.NO_PERMISSION.toString(true));
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("crates")) {
					if(sender.hasPermission(ACPermission.COMMAND_CRATES.toString())) {
						ListCrates.run(sender);
						return true;
					}
					else{
						sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.NO_PERMISSION.toString(true));
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("files")) {
					if(sender.hasPermission(ACPermission.COMMAND_FILES.toString())) {
						ListFiles.run(sender);
						return true;
					}
					else{
						sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.NO_PERMISSION.toString(true));
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("update")) {
					CheckUpdates.run(sender);
					return true;
				}
				if(args[0].equalsIgnoreCase("createv")) {
					if(sender.hasPermission(ACPermission.COMMAND_CREATE_VIRTUAL.toString())) {
						if(sender instanceof Player) {
							try {
								CreateVirtual.run((Player) sender, new VirtualCrate());
							} catch (InvalidVirtualCrateInput e) {
								e.log();
								e.writeToFile();
							}
						}
						else{
							sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.PLAYER_ONLY_COMMAND.toString(true));
							return true;
						}
						return true;
					}
					else{
						sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.NO_PERMISSION.toString(true));
						return true;
					}
				}
				else{
					sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.ERROR.toString(true));
					return true;
				}
			}
			else if(args.length == 2) {
				if(args[0].equalsIgnoreCase("create")) {
					if(sender.hasPermission(ACPermission.COMMAND_CREATE.toString())) {
						if(sender instanceof Player) {
							String crateType = args[1];
							ArrayList<String> crateTypes = new ArrayList<>();
							for(String s : FileHandler.getSection(FileType.CRATES, "Crates").getKeys(false)) crateTypes.add(s.toUpperCase());
							if(crateTypes.contains(crateType.toUpperCase())) {
								String realCrateType = "";
								for(String s : FileHandler.getSection(FileType.CRATES, "Crates").getKeys(false)) {
									if(s.toUpperCase().equals(crateType.toUpperCase())) {
										realCrateType = s;
										break;
									}
								}
								try {
									Create.run((Player) sender, new com.HamiStudios.ArchonCrates.API.Objects.Crate(realCrateType));
								} catch (InvalidCrateInput e) {
									e.log(realCrateType);
									e.writeToFile(realCrateType);
								}
							}
							else{
								sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_CREATE_NOT_CRATE_TYPE.toString(true));
							}
						}
						else{
							sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.PLAYER_ONLY_COMMAND.toString(true));
							return true;
						}
						return true;
					}
					else{
						sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.NO_PERMISSION.toString(true));
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("reload")) {
					if(sender.hasPermission(ACPermission.COMMAND_RELOAD.toString())) {
						String fileinput = args[1];
						if(fileinput.equalsIgnoreCase("all")) {
							Reload.run(ReloadType.ALL, sender);
						}
						else if(Reload.contains(fileinput)) {
							Reload.run(Reload.getReloadType(fileinput), sender);
						}
						else{
							sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_RELOAD_INVALID_INPUT.toString(true));
							return true;
						}
						return true;
					}
					else{
						sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.NO_PERMISSION.toString(true));
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("history")) {
					if(sender.hasPermission(ACPermission.COMMAND_HISTORY.toString())) {
						PrizeHistory.run(sender, args[1]);
						return true;
					}
					else{
						sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.NO_PERMISSION.toString(true));
						return true;
					}
				}
				else{
					sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.ERROR.toString(true));
					return true;
				}
			}
			else if(args.length == 4) {
				if(args[0].equalsIgnoreCase("crate")) {
					if(sender.hasPermission(ACPermission.COMMAND_CRATE.toString())) {
						String name = args[1];
						Player target = Bukkit.getPlayer(name);
						if(target != null) {
							String crateType = args[2];
							if(CrateFinder.isCrateType(crateType)) {
								String keyType = args[3];
								if(KeyFinder.isKeyType(keyType)) {
									Crate.run(target, crateType, keyType);
									return true;
								}
								else{
									sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_CRATE_INVALID_KEY_TYPE.toString(true));
									return true;
								}
							}
							else{
								sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_CRATE_INVALID_CRATE_TYPE.toString(true));
								return true;
							}
						}
						else{
							sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.PLAYER_OFFLINE.toString(true));
							return true;
						}
					}
					else{
						sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.NO_PERMISSION.toString(true));
						return true;
					}
				}
				if(args[0].equalsIgnoreCase("key")) {
					if(sender.hasPermission(ACPermission.COMMAND_KEY.toString())) {
						String person = args[1];
						if(person.equalsIgnoreCase("all")) {
							if(sender.hasPermission(ACPermission.COMMAND_KEY_ALL.toString())) {
								String keyType = args[2];
								ArrayList<String> keyTypes = new ArrayList<>();
								for(String s : FileHandler.getSection(FileType.KEYS, "Keys").getKeys(false)) keyTypes.add(s.toUpperCase());
								if(keyTypes.contains(keyType.toUpperCase())) {
									String realKeyType = "";
									for(String s : FileHandler.getSection(FileType.KEYS, "Keys").getKeys(false)) {
										if(s.toUpperCase().equalsIgnoreCase(keyType.toUpperCase())) {
											realKeyType = s;
											break;
										}
									}
									try {
										int amount = Integer.parseInt(args[3]);
										GiveKey.runForAll(sender, realKeyType, amount);
									} catch(NumberFormatException e) {
										sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_KEY_NOT_NUMBER.toString(true));
										return true;
									}
									
								}
								else{
									sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_KEY_NOT_KEY_TYPE.toString(true));
									return true;
								}
							}
							else{
								sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.NO_PERMISSION.toString(true));
								return true;
							}
						}
						else{
							String name = args[1];
							Player target = Bukkit.getPlayer(name);
							if(target == null) {
								sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.PLAYER_OFFLINE.toString(true));
								return true;
							}
							else{
								String keyType = args[2];
								ArrayList<String> keyTypes = new ArrayList<>();
								for(String s : FileHandler.getSection(FileType.KEYS, "Keys").getKeys(false)) keyTypes.add(s.toUpperCase());
								if(keyTypes.contains(keyType.toUpperCase())) {
									String realKeyType = "";
									for(String s : FileHandler.getSection(FileType.KEYS, "Keys").getKeys(false)) {
										if(s.toUpperCase().equalsIgnoreCase(keyType.toUpperCase())) {
											realKeyType = s;
											break;
										}
									}
									try {
										int amount = Integer.parseInt(args[3]);
										GiveKey.runForPlayer(target, sender, realKeyType, amount);
									} catch(NumberFormatException e) {
										sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_KEY_NOT_NUMBER.toString(true));
										return true;
									}
									
								}
								else{
									sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_KEY_NOT_KEY_TYPE.toString(true));
									return true;
								}
							}
 						}
						
						return true;
					}
					else{
						sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.NO_PERMISSION.toString(true));
						return true;
					}
				}
				else if(args[0].equalsIgnoreCase("vkey")) {
					if(sender.hasPermission(ACPermission.COMMAND_VIRTUAL_KEYS.toString())) {
						String person = args[1];
						if(person.equalsIgnoreCase("all")) {
							if(sender.hasPermission(ACPermission.COMMAND_VIRTUAL_KEYS_ALL.toString())) {
								String keyType = args[2];
								ArrayList<String> keyTypes = new ArrayList<>();
								for(String s : FileHandler.getSection(FileType.VIRTUAL_KEYS, "Virtual Keys").getKeys(false)) keyTypes.add(s.toUpperCase());
								if(keyTypes.contains(keyType.toUpperCase())) {
									String realKeyType = "";
									for(String s : FileHandler.getSection(FileType.VIRTUAL_KEYS, "Virtual Keys").getKeys(false)) {
										if(s.toUpperCase().equalsIgnoreCase(keyType.toUpperCase())) {
											realKeyType = s;
											break;
										}
									}
									try {
										int amount = Integer.parseInt(args[3]);
										GiveVirtualKey.runForAll(sender, realKeyType, amount);
									} catch(NumberFormatException e) {
										sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_KEY_NOT_NUMBER.toString(true));
										return true;
									}
									
								}
								else{
									sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_KEY_NOT_VKEY_TYPE.toString(true));
									return true;
								}
							}
							else{
								sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.NO_PERMISSION.toString(true));
								return true;
							}
						}
						else{
							String name = args[1];
							String targetUUID = PlayerData.getUUID(name);
							if(targetUUID == null) {
								sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.INVALID_PLAYER_NAME.toString(true));
								return true;
							}
							String targetName = PlayerData.getName(targetUUID);
							
							String keyType = args[2];
							ArrayList<String> keyTypes = new ArrayList<>();
							for(String s : FileHandler.getSection(FileType.VIRTUAL_KEYS, "Virtual Keys").getKeys(false)) keyTypes.add(s.toUpperCase());
							if(keyTypes.contains(keyType.toUpperCase())) {
								String realKeyType = "";
								for(String s : FileHandler.getSection(FileType.VIRTUAL_KEYS, "Virtual Keys").getKeys(false)) {
									if(s.toUpperCase().equalsIgnoreCase(keyType.toUpperCase())) {
										realKeyType = s;
										break;
									}
								}
								try {
									int amount = Integer.parseInt(args[3]);
									GiveVirtualKey.runForPlayer(targetName, sender, realKeyType, amount);
								} catch(NumberFormatException e) {
									sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_KEY_NOT_NUMBER.toString(true));
									return true;
								}
								
							}
							else{
								sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_KEY_NOT_VKEY_TYPE.toString(true));
								return true;
							}
						}
						
						return true;
					}
					else{
						sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.NO_PERMISSION.toString(true));
						return true;
					}
				}
				else if(args[0].equalsIgnoreCase("vkeyr")) {
					if(sender.hasPermission(ACPermission.COMMAND_REMOVE_VIRTUAL_KEY.toString())) {
						String name = args[1];
						String targetUUID = PlayerData.getUUID(name);
						if(targetUUID == null) {
							sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.INVALID_PLAYER_NAME.toString(true));
							return true;
						}
						String targetName = PlayerData.getName(targetUUID);
						
						String keyType = args[2];
						ArrayList<String> keyTypes = new ArrayList<>();
						for(String s : FileHandler.getSection(FileType.VIRTUAL_KEYS, "Virtual Keys").getKeys(false)) keyTypes.add(s.toUpperCase());
						if(keyTypes.contains(keyType.toUpperCase())) {
							String realKeyType = "";
							for(String s : FileHandler.getSection(FileType.VIRTUAL_KEYS, "Virtual Keys").getKeys(false)) {
								if(s.toUpperCase().equalsIgnoreCase(keyType.toUpperCase())) {
									realKeyType = s;
									break;
								}
							}
							try {
								int amount = Integer.parseInt(args[3]);
								RemoveVirtualKey.runForPlayer(targetName, sender, realKeyType, amount);
							} catch(NumberFormatException e) {
								sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_KEY_NOT_NUMBER.toString(true));
								return true;
							}
							
						}
						else{
							sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_KEY_NOT_VKEY_TYPE.toString(true));
							return true;
						}
						return true;
					}
					else{
						sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.NO_PERMISSION.toString(true));
						return true;
					}
				}
				else{
					sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.ERROR.toString(true));
					return true;
				}
			}
			else if(args.length == 5) {
				if(args[0].equalsIgnoreCase("nvouch")) {
					if(sender.hasPermission(ACPermission.COMMAND_VOUCHER_NEW.toString())) {
						if(args[4].equalsIgnoreCase("true") || args[4].equalsIgnoreCase("false") || 
								args[4].equalsIgnoreCase("yes") || args[4].equalsIgnoreCase("no") ||
								args[4].equalsIgnoreCase("y") || args[4].equalsIgnoreCase("n")) {
							
							String value = args[4].toLowerCase();
							if(args[0].equalsIgnoreCase("yes") || args[0].equalsIgnoreCase("y")) {
								value = "true";
							}
							if(args[0].equalsIgnoreCase("no") || args[0].equalsIgnoreCase("n")) {
								value = "false";
							}
							boolean virtual = Boolean.parseBoolean(value);
							
							int amount = 0;
							try {
								amount = Integer.parseInt(args[3]);
							} catch(NumberFormatException e) {
								sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_VOUCHER_INVALID_AMOUNT.toString(true));
								return true;
							}
							NewVoucher.run(sender, args[1], args[2], amount, virtual);
							return true;
						}
						else{
							sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_VOUCHER_INVALID_VIRTUAL.toString(true));
							return true;
						}
					}
					else{
						sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.NO_PERMISSION.toString(true));
						return true;
					}
				}
				else{
					sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.ERROR.toString(true));
					return true;
				}
			}
			else if(args.length > 5) {
				sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.TOO_MANY_ARGUMENTS.toString(true));
				return true;
			}
			else{
				sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.ERROR.toString(true));
				return true;
			}
			
		}
		else if(cmd.getName().equalsIgnoreCase("ac?")) {
			ArchonCratesQuestion.run(sender);
			return true;
		}
		
		return false;
	}
	
}
