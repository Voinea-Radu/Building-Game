package com.gmail.stefvanschiedev.buildinggame.utils;

import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static ArrayList<String> color(List<String> str) {
        ArrayList<String> output = new ArrayList<>();
        for (String line : str) {
            output.add(color(line));
        }
        return output;
    }

}
