package com.HamiStudios.ArchonCrates.Util;

import com.HamiStudios.ArchonCrates.Files.FileHandler;

public enum ACPermission {
	
	COMMAND_HELP (FileHandler.getFile(FileType.PERMISSIONS).getString("COMMAND_HELP")),
	COMMAND_REMOVE (FileHandler.getFile(FileType.PERMISSIONS).getString("COMMAND_REMOVE")),
	COMMAND_INFO (FileHandler.getFile(FileType.PERMISSIONS).getString("COMMAND_INFO")),
	COMMAND_KEYS (FileHandler.getFile(FileType.PERMISSIONS).getString("COMMAND_KEYS")),
	COMMAND_VKEYS (FileHandler.getFile(FileType.PERMISSIONS).getString("COMMAND_VKEYS")),
	COMMAND_CRATES (FileHandler.getFile(FileType.PERMISSIONS).getString("COMMAND_CRATES")),
	COMMAND_FILES (FileHandler.getFile(FileType.PERMISSIONS).getString("COMMAND_FILES")),
	COMMAND_UPDATE (FileHandler.getFile(FileType.PERMISSIONS).getString("COMMAND_UPDATE")),
	COMMAND_CREATE (FileHandler.getFile(FileType.PERMISSIONS).getString("COMMAND_CREATE")),
	COMMAND_CREATE_VIRTUAL (FileHandler.getFile(FileType.PERMISSIONS).getString("COMMAND_CREATE_VIRTUAL")),
	COMMAND_RELOAD (FileHandler.getFile(FileType.PERMISSIONS).getString("COMMAND_RELOAD")),
	COMMAND_CRATE (FileHandler.getFile(FileType.PERMISSIONS).getString("COMMAND_CRATE")),
	COMMAND_KEY (FileHandler.getFile(FileType.PERMISSIONS).getString("COMMAND_KEY")),
	COMMAND_KEY_ALL (FileHandler.getFile(FileType.PERMISSIONS).getString("COMMAND_KEY_ALL")),
	COMMAND_VIRTUAL_KEYS (FileHandler.getFile(FileType.PERMISSIONS).getString("COMMAND_VIRTUAL_KEYS")),
	COMMAND_VIRTUAL_KEYS_ALL (FileHandler.getFile(FileType.PERMISSIONS).getString("COMMAND_VIRTUAL_KEYS_ALL")),
	COMMAND_REMOVE_VIRTUAL_KEY (FileHandler.getFile(FileType.PERMISSIONS).getString("COMMAND_REMOVE_VIRTUAL_KEY")),
	COMMAND_HISTORY (FileHandler.getFile(FileType.PERMISSIONS).getString("COMMAND_HISTORY")),
	
	COMMAND_VOUCHER_NEW (FileHandler.getFile(FileType.PERMISSIONS).getString("COMMAND_VOUCHER_NEW")),
	COMMAND_VOUCHER_REDEEM (FileHandler.getFile(FileType.PERMISSIONS).getString("COMMAND_VOUCHER_REDEEM")),
	
	COMPLETE_TAB (FileHandler.getFile(FileType.PERMISSIONS).getString("COMPLETE_TAB")),
	OPEN_CRATE_WITH_ANY_KEY (FileHandler.getFile(FileType.PERMISSIONS).getString("OPEN_CRATE_WITH_ANY_KEY")),
	OPEN_CRATE (FileHandler.getFile(FileType.PERMISSIONS).getString("OPEN_CRATE")),
	OPEN_VIRTUAL_CRATE (FileHandler.getFile(FileType.PERMISSIONS).getString("OPEN_VIRTUAL_CRATE")),
	VIEW_PRIZES (FileHandler.getFile(FileType.PERMISSIONS).getString("VIEW_PRIZES")),
	BREAK_CRATE (FileHandler.getFile(FileType.PERMISSIONS).getString("BREAK_CRATE")),

	SIGN_USE_BUY (FileHandler.getFile(FileType.PERMISSIONS).getString("SIGN_USE_BUY")),
	SIGN_CREATE_NORAML (FileHandler.getFile(FileType.PERMISSIONS).getString("SIGN_CREATE_NORAML")),
	SIGN_CREATE_VIRTUAL (FileHandler.getFile(FileType.PERMISSIONS).getString("SIGN_CREATE_VIRTUAL")),
	SIGN_BREAK (FileHandler.getFile(FileType.PERMISSIONS).getString("SIGN_BREAK"));
	
	private final String perm;
	private ACPermission(String perm) {
		this.perm = perm;
	}
	
	public String toString() {
		return this.perm;
	}
	
}
