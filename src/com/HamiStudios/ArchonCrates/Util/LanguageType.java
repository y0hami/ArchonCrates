package com.HamiStudios.ArchonCrates.Util;

import org.bukkit.ChatColor;

import com.HamiStudios.ArchonCrates.Files.FileHandler;

public enum LanguageType {
	
	PREFIX (FileHandler.getFile(FileType.LANGUAGE).getString("PREFIX")),
	NO_PERMISSION (FileHandler.getFile(FileType.LANGUAGE).getString("NO_PERMISSION")),
	TOO_FEW_ARGUMENTS (FileHandler.getFile(FileType.LANGUAGE).getString("TOO_FEW_ARGUMENTS")),
	TOO_MANY_ARGUMENTS (FileHandler.getFile(FileType.LANGUAGE).getString("TOO_MANY_ARGUMENTS")),
	ERROR (FileHandler.getFile(FileType.LANGUAGE).getString("ERROR")),
	PLAYER_ONLY_COMMAND (FileHandler.getFile(FileType.LANGUAGE).getString("PLAYER_ONLY_COMMAND")),
	PLAYER_OFFLINE (FileHandler.getFile(FileType.LANGUAGE).getString("PLAYER_OFFLINE")),
	INVALID_PLAYER_NAME (FileHandler.getFile(FileType.LANGUAGE).getString("INVALID_PLAYER_NAME")),
	
	COMMAND_CREATE_NO_SPACE (FileHandler.getFile(FileType.LANGUAGE).getString("COMMAND_CREATE_NO_SPACE")),
	COMMAND_CREATE_NOT_CRATE_TYPE (FileHandler.getFile(FileType.LANGUAGE).getString("COMMAND_CREATE_NOT_CRATE_TYPE")),
	COMMAND_RELOAD_INVALID_INPUT (FileHandler.getFile(FileType.LANGUAGE).getString("COMMAND_RELOAD_INVALID_INPUT")),
	COMMAND_RELOAD_ERROR (FileHandler.getFile(FileType.LANGUAGE).getString("COMMAND_RELOAD_ERROR")),
	COMMAND_RELOAD_DONE (FileHandler.getFile(FileType.LANGUAGE).getString("COMMAND_RELOAD_DONE")),
	COMMAND_KEY_NOT_KEY_TYPE (FileHandler.getFile(FileType.LANGUAGE).getString("COMMAND_KEY_NOT_KEY_TYPE")),
	COMMAND_KEY_NOT_VKEY_TYPE (FileHandler.getFile(FileType.LANGUAGE).getString("COMMAND_KEY_NOT_VKEY_TYPE")),
	COMMAND_KEY_NOT_NUMBER (FileHandler.getFile(FileType.LANGUAGE).getString("COMMAND_KEY_NOT_NUMBER")),
	COMMAND_KEY_GIVEN (FileHandler.getFile(FileType.LANGUAGE).getString("COMMAND_KEY_GIVEN")),
	COMMAND_KEY_NO_SPACE (FileHandler.getFile(FileType.LANGUAGE).getString("COMMAND_KEY_NO_SPACE")),
	COMMAND_VKEY_GIVEN (FileHandler.getFile(FileType.LANGUAGE).getString("COMMAND_VKEY_GIVEN")),
	COMMAND_VKEY_REMOVED (FileHandler.getFile(FileType.LANGUAGE).getString("COMMAND_VKEY_REMOVED")),
	COMMAND_REMOVE_NOT_CRATE (FileHandler.getFile(FileType.LANGUAGE).getString("COMMAND_REMOVE_NOT_CRATE")),
	COMMAND_REMOVE_DONE (FileHandler.getFile(FileType.LANGUAGE).getString("COMMAND_REMOVE_DONE")),
	COMMAND_CRATE_INVALID_CRATE_TYPE (FileHandler.getFile(FileType.LANGUAGE).getString("COMMAND_CRATE_INVALID_CRATE_TYPE")),
	COMMAND_CRATE_INVALID_KEY_TYPE (FileHandler.getFile(FileType.LANGUAGE).getString("COMMAND_CRATE_INVALID_KEY_TYPE")),
	COMMAND_HISTORY_INVALID_PLAYER (FileHandler.getFile(FileType.LANGUAGE).getString("COMMAND_HISTORY_INVALID_PLAYER")),
	
	COMMAND_VOUCHER_USE (FileHandler.getFile(FileType.LANGUAGE).getString("COMMAND_VOUCHER_USE")),
	COMMAND_VOUCHER_NO_SPACE (FileHandler.getFile(FileType.LANGUAGE).getString("COMMAND_VOUCHER_NO_SPACE")),
	COMMAND_VOUCHER_NEW (FileHandler.getFile(FileType.LANGUAGE).getString("COMMAND_VOUCHER_NEW")),
	COMMAND_VOUCHER_INVALID_VIRTUAL (FileHandler.getFile(FileType.LANGUAGE).getString("COMMAND_VOUCHER_INVALID_VIRTUAL")),
	COMMAND_VOUCHER_INVALID_AMOUNT (FileHandler.getFile(FileType.LANGUAGE).getString("COMMAND_VOUCHER_INVALID_AMOUNT")),
	COMMAND_VOUCHER_INVALID (FileHandler.getFile(FileType.LANGUAGE).getString("COMMAND_VOUCHER_INVALID")),
	COMMAND_VOUCHER_REDEEMED (FileHandler.getFile(FileType.LANGUAGE).getString("COMMAND_VOUCHER_REDEEMED")),
	
	CREATE_CRATE (FileHandler.getFile(FileType.LANGUAGE).getString("CREATE_CRATE")),
	CREATE_VIRTUAL_CRATE (FileHandler.getFile(FileType.LANGUAGE).getString("CREATE_VIRTUAL_CRATE")),
	
	BREAK_CRATE_DENY (FileHandler.getFile(FileType.LANGUAGE).getString("BREAK_CRATE_DENY")),
	BREAK_CRATE_ALLOW (FileHandler.getFile(FileType.LANGUAGE).getString("BREAK_CRATE_ALLOW")),
	CANT_OPEN_CRATE (FileHandler.getFile(FileType.LANGUAGE).getString("CANT_OPEN_CRATE")),
	OPEN_CRATE_NO_PERMISSION (FileHandler.getFile(FileType.LANGUAGE).getString("OPEN_CRATE_NO_PERMISSION")),
	OPEN_CRATE_NO_PERMISSION_VIRTUAL (FileHandler.getFile(FileType.LANGUAGE).getString("OPEN_CRATE_NO_PERMISSION_VIRTUAL")),
	
	SIGN_INVALID_AMOUNT (FileHandler.getFile(FileType.LANGUAGE).getString("SIGN_INVALID_AMOUNT")),
	SIGN_INVALID_KEYTYPE (FileHandler.getFile(FileType.LANGUAGE).getString("SIGN_INVALID_KEYTYPE")),
	SIGN_INVALID_PRICE (FileHandler.getFile(FileType.LANGUAGE).getString("SIGN_INVALID_PRICE")),
	SIGN_CREATED (FileHandler.getFile(FileType.LANGUAGE).getString("SIGN_CREATED")),
	SIGN_REMOVED (FileHandler.getFile(FileType.LANGUAGE).getString("SIGN_REMOVED")),
	SIGN_NOT_ENOUGH_MONEY (FileHandler.getFile(FileType.LANGUAGE).getString("SIGN_NOT_ENOUGH_MONEY")),
	SIGN_BOUGHT_KEY (FileHandler.getFile(FileType.LANGUAGE).getString("SIGN_BOUGHT_KEY")),
	SIGN_CANT_BREAK (FileHandler.getFile(FileType.LANGUAGE).getString("SIGN_CANT_BREAK"));
	
	private final String lang;
	private LanguageType(String lang) {
		this.lang = lang;
	}
	
	public String toString(boolean withColour) {
		if(withColour) return ChatColor.translateAlternateColorCodes('&', this.lang);
		return this.lang;
	}
	
}
