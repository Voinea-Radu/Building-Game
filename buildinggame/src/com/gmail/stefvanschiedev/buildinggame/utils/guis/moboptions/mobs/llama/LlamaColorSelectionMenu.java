package com.gmail.stefvanschiedev.buildinggame.utils.guis.moboptions.mobs.llama;

import com.gmail.stefvanschiedev.buildinggame.utils.guis.Gui;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Llama;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;

/**
 * A menu for changing the color of a llama
 *
 * @since 5.3.0
 */
class LlamaColorSelectionMenu extends Gui {

    /**
     * {@inheritDoc}
     */
    LlamaColorSelectionMenu(Llama llama) {
        super(null, 9, ChatColor.GREEN + "Change the llama color", 1);

        setStartingPoint(2);

        //brown
        ItemStack brown = new Wool(DyeColor.BROWN).toItemStack(1);
        ItemMeta brownMeta = brown.getItemMeta();
        brownMeta.setDisplayName(ChatColor.GREEN + "Brown");
        brown.setItemMeta(brownMeta);

        addItem(brown, new GuiAction() {

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean actionPerformed(GuiActionType type, InventoryEvent event) {
                if (type != GuiActionType.CLICK)
                    return false;

                llama.setColor(Llama.Color.BROWN);

                return true;
            }
        });

        //creamy
        ItemStack creamy = new ItemStack(Material.SANDSTONE);
        ItemMeta creamyMeta = creamy.getItemMeta();
        creamyMeta.setDisplayName(ChatColor.GREEN + "Creamy");
        creamy.setItemMeta(creamyMeta);

        addItem(creamy, new GuiAction() {

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean actionPerformed(GuiActionType type, InventoryEvent event) {
                if (type != GuiActionType.CLICK)
                    return false;

                llama.setColor(Llama.Color.CREAMY);

                return true;
            }
        });

        setStartingPoint(5);

        //gray
        ItemStack gray = new Wool(DyeColor.GRAY).toItemStack(1);
        ItemMeta grayMeta = gray.getItemMeta();
        grayMeta.setDisplayName(ChatColor.GREEN + "Gray");
        gray.setItemMeta(grayMeta);

        addItem(gray, new GuiAction() {

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean actionPerformed(GuiActionType type, InventoryEvent event) {
                if (type != GuiActionType.CLICK)
                    return false;

                llama.setColor(Llama.Color.GRAY);

                return true;
            }
        });

        //white
        ItemStack white = new Wool(DyeColor.WHITE).toItemStack(1);
        ItemMeta whiteMeta = white.getItemMeta();
        whiteMeta.setDisplayName(ChatColor.GREEN + "White");
        white.setItemMeta(whiteMeta);

        addItem(white, new GuiAction() {

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean actionPerformed(GuiActionType type, InventoryEvent event) {
                if (type != GuiActionType.CLICK)
                    return false;

                llama.setColor(Llama.Color.WHITE);

                return true;
            }
        });
    }
}