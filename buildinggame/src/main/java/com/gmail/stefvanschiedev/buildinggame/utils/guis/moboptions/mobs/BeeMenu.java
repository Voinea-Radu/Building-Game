package com.gmail.stefvanschiedev.buildinggame.utils.guis.moboptions.mobs;

import com.github.stefvanschie.inventoryframework.GuiItem;
import com.gmail.stefvanschiedev.buildinggame.utils.guis.moboptions.BabyMenu;
import com.gmail.stefvanschiedev.buildinggame.utils.plot.Plot;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

/**
 * A menu for changing the options of a bee.
 *
 * @since 8.0.0
 */
public class BeeMenu extends BabyMenu {

    /**
     * {@inheritDoc}
     */
    //TODO change entity to bee
    public BeeMenu(@NotNull Plot plot, @NotNull Entity entity) {
        super(plot, entity);

        //angry
        ItemStack angry = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta angryMeta = angry.getItemMeta();
        angryMeta.setDisplayName(ChatColor.GREEN + "Change whether this bee is angry");
        angry.setItemMeta(angryMeta);

        pane.insertItem(new GuiItem(angry, event -> {
            //TODO change whether bee is angry

            event.setCancelled(true);
        }), 0);

        //pollen
        ItemStack pollen = new ItemStack(Material.AZURE_BLUET);
        ItemMeta pollenMeta = pollen.getItemMeta();
        pollenMeta.setDisplayName(ChatColor.GREEN + "Change whether this bee has pollen on it");
        pollen.setItemMeta(pollenMeta);

        pane.insertItem(new GuiItem(pollen, event -> {
            //TODO change whether bee has pollen on it

            event.setCancelled(true);
        }), 1);

        //stinger
        ItemStack stinger = new ItemStack(Material.STICK);
        ItemMeta stingerMeta = stinger.getItemMeta();
        stingerMeta.setDisplayName(ChatColor.GREEN + "Change whether this bee has a stinger");
        stinger.setItemMeta(stingerMeta);

        pane.insertItem(new GuiItem(stinger, event -> {
            //TODO change whether bee has stinger

            event.setCancelled(true);
        }), 2);
    }
}
