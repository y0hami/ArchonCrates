package com.HamiStudios.ArchonCrates.Commands;

import org.bukkit.command.CommandSender;

import com.HamiStudios.ArchonCrates.Util.KeyFinder;
import com.HamiStudios.ArchonCrates.Util.LanguageType;
import com.HamiStudios.ArchonCrates.Util.PlayerData;
import com.HamiStudios.ArchonCrates.Util.VoucherUtil;


public class NewVoucher {

	public static void run(CommandSender sender, String targetname, String keyType, int amount, boolean virtual) {
		String playerUUID = PlayerData.getUUID(targetname);
		if(playerUUID != null) {
			
			if(virtual) {
				if(KeyFinder.isVKeyType(keyType)) {
					String voucher = VoucherUtil.newVoucher();
					while(VoucherUtil.voucherExists(voucher)) {
						voucher = VoucherUtil.newVoucher();
					}
					VoucherUtil.addVoucher(playerUUID, voucher, keyType, amount, virtual);
					sender.sendMessage(LanguageType.COMMAND_VOUCHER_NEW.toString(true).replaceAll("<player>", PlayerData.getName(playerUUID)));
					return;
				}
				else{
					sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_KEY_NOT_VKEY_TYPE.toString(true));
					return;
				}
			}
			else{
				if(KeyFinder.isKeyType(keyType)) {
					String voucher = VoucherUtil.newVoucher();
					while(VoucherUtil.voucherExists(voucher)) {
						voucher = VoucherUtil.newVoucher();
					}
					VoucherUtil.addVoucher(playerUUID, voucher, keyType, amount, virtual);
					sender.sendMessage(LanguageType.COMMAND_VOUCHER_NEW.toString(true).replaceAll("<player>", PlayerData.getName(playerUUID)));
					return;
				}
				else{
					sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.COMMAND_KEY_NOT_KEY_TYPE.toString(true));
					return;
				}
			}
			
		}
		else {
			sender.sendMessage(LanguageType.PREFIX.toString(true) + LanguageType.INVALID_PLAYER_NAME.toString(true));
			return;
		}
	}
	
}
