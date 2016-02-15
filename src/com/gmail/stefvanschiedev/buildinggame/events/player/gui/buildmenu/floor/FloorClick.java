package com.gmail.stefvanschiedev.buildinggame.events.player.gui.buildmenu.floor;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.gmail.stefvanschiedev.buildinggame.managers.arenas.ArenaManager;
import com.gmail.stefvanschiedev.buildinggame.managers.files.SettingsManager;
import com.gmail.stefvanschiedev.buildinggame.managers.id.IDDecompiler;
import com.gmail.stefvanschiedev.buildinggame.utils.CustomBlock;
import com.gmail.stefvanschiedev.buildinggame.utils.plot.Plot;

public class FloorClick implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInventoryClick (InventoryClickEvent e) {
		YamlConfiguration config = SettingsManager.getInstance().getConfig();
		YamlConfiguration messages = SettingsManager.getInstance().getMessages();
		
		Player player = (Player) e.getWhoClicked();
		Inventory inventory = e.getInventory();
		
		if (ArenaManager.getInstance().getArena(player) == null) {
			return;
		}
		
		Plot plot = ArenaManager.getInstance().getArena(player).getPlot(player);
		
		if (!inventory.getName().equals(messages.getString("gui.options-title")
				.replace("%:a%", "�")
				.replace("%:e%", "�")
				.replace("%:i%", "�")
				.replace("%:o%", "�")
				.replace("%:u%", "�")
				.replace("%ss%", "�")
				.replaceAll("&", "�"))) {
			return;
		}
		
		if (e.getCurrentItem() == null || !e.getCurrentItem().getType().isBlock()) {
			return;
		}
		
		ItemStack currentItem = e.getCurrentItem();
		
		CustomBlock cblock = IDDecompiler.getInstance().decompile(config.getString("gui.floor-id"));
		
		if (currentItem.getType() != cblock.getMaterial()) {
			return;
		}
		if (currentItem.getDurability() != cblock.getData()) {
			return;
		}
		
		if (!currentItem.hasItemMeta()) {
			return;
		}
		if (!currentItem.getItemMeta().getDisplayName().equals(messages.getString("gui.floor.name")
				.replace("%:a%", "�")
				.replace("%:e%", "�")
				.replace("%:i%", "�")
				.replace("%:o%", "�")
				.replace("%:u%", "�")
				.replace("%ss%", "�")
				.replaceAll("&", "�"))) {
			return;
		}
		
		for (String material : config.getStringList("blocks.blocked")) {
			CustomBlock cb = IDDecompiler.getInstance().decompile(material.toUpperCase());
			if (currentItem.getType() == cb.getMaterial() && currentItem.getDurability() == cb.getData()) {
				e.setCancelled(true);
				return;
			}
		}
		
		if (e.getCursor().getType() == Material.WATER_BUCKET) {
			for (Block block : plot.getFloor().getAllBlocks()) {
				block.setType(Material.WATER);
			}
			e.setCancelled(true);
			return;
		}
		
		if (e.getCursor().getType() == Material.LAVA_BUCKET) {
			for (Block block : plot.getFloor().getAllBlocks()) {
				block.setType(Material.LAVA);
			}
			e.setCancelled(true);
			return;
		}
		
		if (!e.getCursor().getType().isBlock())
			return;
		
		for (Block block : plot.getFloor().getAllBlocks()) {
			if (block.getType() == e.getCursor().getType() && block.getData() == e.getCursor().getData().getData())
				return;
			block.setType(e.getCursor().getType());
			block.setData(e.getCursor().getData().getData());
		}
		
		e.setCancelled(true);
	}
}