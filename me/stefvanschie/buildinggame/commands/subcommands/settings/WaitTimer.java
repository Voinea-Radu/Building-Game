package me.stefvanschie.buildinggame.commands.subcommands.settings;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.stefvanschie.buildinggame.commands.commandutils.CommandResult;
import me.stefvanschie.buildinggame.commands.commandutils.SubCommand;
import me.stefvanschie.buildinggame.managers.files.SettingsManager;
import me.stefvanschie.buildinggame.managers.messages.MessageManager;

public class WaitTimer extends SubCommand {

	@Override
	public CommandResult onCommand(Player player, String[] args) {
		YamlConfiguration config = SettingsManager.getInstance().getConfig();
		
		if (args.length == 0) {
			MessageManager.getInstance().send(player, ChatColor.RED + "Please specify the time");
			return CommandResult.ARGUMENTEXCEPTION;
		}
		
		try {
			Integer.parseInt(args[0]);
		} catch (NumberFormatException nfe) {
			MessageManager.getInstance().send(player, ChatColor.RED + "That's not a valid number");
			return CommandResult.ERROR;
		}
		
		int time = Integer.parseInt(args[0]);
		
		config.set("votetimer", time);
		SettingsManager.getInstance().save();
		
		MessageManager.getInstance().send(player, ChatColor.GREEN + "Waittimer setting set to " + time);
		
		return CommandResult.SUCCES;
	}

	@Override
	public String getName() {
		return "waittimer";
	}

	@Override
	public String[] getAliases() {
		return null;
	}

	@Override
	public String getInfo() {
		return "Edit the waittimer setting";
	}

	@Override
	public String getPermission() {
		return "bg.setting.waittimer";
	}

}
