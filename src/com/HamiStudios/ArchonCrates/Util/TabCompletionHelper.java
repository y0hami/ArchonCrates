package com.HamiStudios.ArchonCrates.Util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TabCompletionHelper {

	public static List<String> getPossibleCompletionsForGivenArgs(String[] args, String[] strings, boolean addPlayers) {
		
		String arg = args[args.length-1];
		ArrayList<String> possableComplete = new ArrayList<>();
		for(String s : strings) {
			if(s.startsWith(arg)) {
				possableComplete.add(s);
			}
		}
		if(addPlayers) {
			for(Player p : Bukkit.getOnlinePlayers()) {
				if(p.getName().startsWith(arg)) {
					possableComplete.add(p.getName());
				}
				else{
					possableComplete.add(p.getName());
				}
			}
		}
		
		return possableComplete;
	}
	
	public static List<String> getPossibleCompletionsForGivenArgs(String[] args, ArrayList<String> strings, boolean addPlayers) {
		
		String arg = args[args.length-1];
		ArrayList<String> possableComplete = new ArrayList<>();
		for(String s : strings) {
			if(s.startsWith(arg)) {
				possableComplete.add(s);
			}
		}
		if(addPlayers) {
			for(Player p : Bukkit.getOnlinePlayers()) {
				if(p.getName().startsWith(arg)) {
					possableComplete.add(p.getName());
				}
				else{
					possableComplete.add(p.getName());
				}
			}
		}
		
		return possableComplete;
	}
	 
	public static ArrayList<String> getOnlinePlayerNames(String[] args) {
		ArrayList<String> onlinePlayerNames = new ArrayList<>();
		for(Player p : Bukkit.getOnlinePlayers()) {
			onlinePlayerNames.add(p.getName());
		}
		String arg = args[args.length-1];
		ArrayList<String> possableComplete = new ArrayList<>();
		for(String s : onlinePlayerNames) {
			if(s.startsWith(arg)) {
				possableComplete.add(s);
			}
		}
		return possableComplete;
	}
	
}
