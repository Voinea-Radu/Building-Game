package me.stefvanschie.buildinggame.timers;

import me.stefvanschie.buildinggame.managers.arenas.ArenaManager;
import me.stefvanschie.buildinggame.managers.files.SettingsManager;
import me.stefvanschie.buildinggame.utils.Arena;
import me.stefvanschie.buildinggame.utils.GameState;
import me.stefvanschie.buildinggame.utils.plot.Plot;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

public class ScoreboardUpdater extends BukkitRunnable {

	@Override
	public void run() {
		YamlConfiguration config = SettingsManager.getInstance().getConfig();
		
		for (Arena arena : ArenaManager.getInstance().getArenas()) {
			if (arena.getState() == GameState.VOTING && !config.getBoolean("names-after-voting")) {
				for (Plot plot : arena.getUsedPlots()) {
					arena.getScoreboard().show(plot.getGamePlayer().getPlayer());
				}
				return;
			}
			
			if (arena.getState() == GameState.RESETING && config.getBoolean("names-after-voting")) {
				for (Plot plot : arena.getUsedPlots()) {
					arena.getScoreboard().show(plot.getGamePlayer().getPlayer());
				}
				return;
			}
			
			for (Plot plot : arena.getUsedPlots()) {
				arena.getBuildScoreboard().update(plot.getGamePlayer().getPlayer());
			}
		}
	}
}
