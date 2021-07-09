package com.gmail.stefvanschiedev.buildinggame.events.player;

import com.gmail.stefvanschiedev.buildinggame.api.events.PlayerWinEvent;
import com.gmail.stefvanschiedev.buildinggame.managers.files.SettingsManager;
import com.gmail.stefvanschiedev.buildinggame.managers.messages.MessageManager;
import com.gmail.stefvanschiedev.buildinggame.managers.stats.StatManager;
import com.gmail.stefvanschiedev.buildinggame.utils.gameplayer.GamePlayer;
import com.gmail.stefvanschiedev.buildinggame.utils.stats.StatType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Win implements Listener {

    @EventHandler
    public void onPlayerWin(PlayerWinEvent event) {
        if (event.getArena().getName().equals(SettingsManager.getInstance().getConfig().getString("vip-arena.arena"))) {
            StatManager stats = StatManager.getInstance();
            Collection<GamePlayer> players = event.getPlayers();
            for (GamePlayer player : players) {
                int points = 0;
                if (stats.containsUUID(player.getPlayer().getUniqueId())) {
                    points += stats.getStat(player.getPlayer(), StatType.VIP_POINTS).getValue();
                }
                if (event.getWin().equals(com.gmail.stefvanschiedev.buildinggame.api.Win.FIRST)) {
                    points += SettingsManager.getInstance().getConfig().getInt("vip-arena.1st-place-reward");
                }
                if (event.getWin().equals(com.gmail.stefvanschiedev.buildinggame.api.Win.SECOND)) {
                    points += SettingsManager.getInstance().getConfig().getInt("vip-arena.2nd-place-reward");
                }
                if (event.getWin().equals(com.gmail.stefvanschiedev.buildinggame.api.Win.THIRD)) {
                    points += SettingsManager.getInstance().getConfig().getInt("vip-arena.3rd-place-reward");
                }

                List<String> rawMessages = SettingsManager.getInstance().getMessages().getStringList("win-points");
                List<String> parsedMessages = new ArrayList<>();

                for (String line : rawMessages) {
                    line = line.replace("%needed_points%", String.valueOf(SettingsManager.getInstance().getConfig().getInt("vip-arena.code-price")));
                    line = line.replace("%points%", String.valueOf(points));
                    parsedMessages.add(line);
                }

                MessageManager.getInstance().send(player.getPlayer(), parsedMessages);
                stats.registerStat(player.getPlayer(), StatType.VIP_POINTS, points);
            }
        }
    }

}
