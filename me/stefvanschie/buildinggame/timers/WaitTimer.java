package me.stefvanschie.buildinggame.timers;

import me.stefvanschie.buildinggame.managers.files.SettingsManager;
import me.stefvanschie.buildinggame.managers.messages.MessageManager;
import me.stefvanschie.buildinggame.timers.utils.Timer;
import me.stefvanschie.buildinggame.utils.Arena;
import me.stefvanschie.buildinggame.utils.plot.Plot;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class WaitTimer extends Timer {

	private int seconds;
	private Arena arena;
	private boolean running = false;
	
	public WaitTimer(int seconds, Arena arena) {
		this.seconds = seconds;
		this.arena = arena;
	}
	
	@Override
	public void run() {
		YamlConfiguration messages = SettingsManager.getInstance().getMessages();
		
		running = true;
		if (seconds <= 0) {
			arena.start();
			running = false;
			this.cancel();
			return;
		} else if (seconds % 15 == 0 || (seconds <= 10 && seconds >= 1)) {
			for (Plot plot : arena.getUsedPlots()) {
				Player player = plot.getGamePlayer().getPlayer();
				MessageManager.getInstance().send(player, messages.getString("lobbyCountdown.message")
						.replace("%seconds%", seconds + "")
						.replace("%minutes", getMinutes() + "")
						.replace("%time%", getMinutes() + ":" + getSecondsFromMinute())
						.replace("%seconds_from_minute%", getSecondsFromMinute() + ""));
			}
		}
		for (Plot plot : arena.getUsedPlots()) {
			Player player = plot.getGamePlayer().getPlayer();
				
			player.setLevel(seconds);
		}
		seconds--;
	}
	
	@Override
	public int getSeconds() {
		return seconds;
	}
	
	@Override
	public boolean isActive() {
		return running;
	}
	
	@Override
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
}
