package com.HamiStudios.ArchonCrates.Commands;

import com.HamiStudios.ArchonCrates.API.Objects.ACSender;

public interface Command {

	void displayHelp(ACSender sender);
	void execCommand(String[] args, ACSender sender);

}
