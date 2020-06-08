package xyz.trrlgn.teleporttoplayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUIClickListener implements Listener {

	public PlayerTP plugin;

	public GUIClickListener(PlayerTP pl) {
		plugin = pl;
	}

	@EventHandler
	public void onGUIClick(InventoryClickEvent e) {
		if(!e.getInventory().equals(plugin.tg.gui)) {
			return;
		}
		
		try {
			if(e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
				e.setCancelled(true);
				e.getWhoClicked().closeInventory();
				plugin.tg.tpToPlayer((Player) e.getWhoClicked(), Bukkit.getPlayer(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName())));
			}
		} catch (Exception e2) {
			return;
		}

	}

}
