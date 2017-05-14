package com.HamiStudios.ArchonCrates.API.libs;

import com.HamiStudios.ArchonCrates.API.Objects.ACSender;

import java.util.ArrayList;

public class HelpPageBuilder {

	// Variables for the help page elements
	private String command;
	private String commandDescription;
	private ArrayList<CommandArgument> arguments;
	private String example;
	private String helpURL;

	// HelpPageBuilder constructor
	public HelpPageBuilder(String command) {
		// Sets the basic elements of the help page
		this.command = command;
		this.arguments = new ArrayList<>();
	}

	// Set the description
	public HelpPageBuilder setCommandDescription(String description) {
		this.commandDescription = description;

		return this;
	}

	// Add a command argument
	public HelpPageBuilder addArgument(String name, boolean optional, ArrayList<String> variables) {
		this.arguments.add(new CommandArgument(name, optional, variables));

		return this;
	}

	// Set the example command
	public HelpPageBuilder setExample(String example) {
		this.example = example;

		return this;
	}

	// Set the help page url
	public HelpPageBuilder setHelpURL(String url) {
		this.helpURL = url;

		return this;
	}

	// Send the help page to the sender
	public void send(ACSender sender) {
		// Start it with a space
		sender.sendSpace();

		// Send the help page prefix
		sender.sendMessage(LanguageManager.getHelpPrefix());
		sender.sendSpace();
		sender.sendSpace();

		// Build the basic command layout
		String command = "&7Command Layout:\n&f/archoncrates " + this.command + " ";

		// For all the command arguments add to the command layout the argument name surrounding it with <required> or [optional] tags
		for (CommandArgument arg : this.arguments) {
			if(arg.isOptional()) {
				// Optional argument tags
				command += "[" + arg.getName() + "] ";
			} else {
				// Required argument tags
				command += "<" + arg.getName() + "> ";
			}
		}

		// Send the command layout
		sender.sendMessage(command);

		sender.sendSpace();
		sender.sendSpace();

		// Send the command description
		sender.sendMessage("&7Command Description:\n&f" + this.commandDescription);

		sender.sendSpace();
		sender.sendSpace();

		// If the command has arguments
		if(this.arguments.size() > 0) {
			sender.sendMessage("&7Command Variables:");

			// For each argument display its name and the variables it accepts
			for (CommandArgument arg : this.arguments) {
				sender.sendSpace();
				String variables = "&7" + arg.getName().substring(0, 1).toUpperCase() + arg.getName().substring(1) + ":\n&f";
				for (String name : arg.getVariables()) {
					variables += name + ", ";
				}

				// Send the argument
				sender.sendMessage(variables.substring(0, variables.length() - 2));
			}

			sender.sendSpace();
			sender.sendSpace();
		}

		// If an example command exists
		if(this.example != null) {
			// Send the example command
			sender.sendMessage("&7Usage Example:\n&f/archoncrates " + this.command + " " + this.example);

			sender.sendSpace();
			sender.sendSpace();
		}

		// Send the help page url
		sender.sendMessage("&7For more info visit:\n&e" + LanguageManager.getHelpURL() + this.helpURL);

		sender.sendSpace();
		sender.sendSpace();
		// Send the suffix
		sender.sendMessage(LanguageManager.getHelpSuffix());
		sender.sendSpace();
	}

}


// Command Argument Class
class CommandArgument {

	private String name;
	private boolean optional;
	private ArrayList<String> variables;

	public CommandArgument(String name, boolean optional, ArrayList<String> variables) {
		this.name = name;
		this.optional = optional;
		this.variables = variables;
	}

	public String getName() { return this.name; }

	public boolean isOptional() { return this.optional; }

	public ArrayList<String> getVariables() { return this.variables; }

}