package com.gmail.stefvanschiedev.buildinggame.utils.guis.moboptions.color;

import com.gmail.stefvanschiedev.buildinggame.utils.guis.moboptions.BabyMenu;
import com.gmail.stefvanschiedev.buildinggame.utils.plot.Plot;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * A menu for changing the color of an entity
 *
 * @since 5.3.0
 */
public class ColorMenu extends BabyMenu {

    /**
     * {@inheritDoc}
     */
    public ColorMenu(Plot plot, Animals entity) {
        super(plot, entity);

        ItemStack color = new ItemStack(Material.CONCRETE_POWDER, 1, (short) 1);
        ItemMeta colorMeta = color.getItemMeta();
        colorMeta.setDisplayName(ChatColor.GREEN + "Change the color of the entity");
        color.setItemMeta(colorMeta);

        insertItem(color, new GuiAction() {

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean actionPerformed(GuiActionType type, InventoryEvent event) {
                if (type != GuiActionType.CLICK)
                    return false;

                new ColorSelectionMenu(entity).open((Player) ((InventoryInteractEvent) event).getWhoClicked());

                return true;
            }
        }, 0);
    }
}