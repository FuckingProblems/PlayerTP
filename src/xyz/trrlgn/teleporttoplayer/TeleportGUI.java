package xyz.trrlgn.teleporttoplayer;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class TeleportGUI {

	public PlayerTP plugin;
	
	public TeleportGUI(PlayerTP pl) {
		plugin = pl;
	}
	
	public ArrayList<String> pNames = new ArrayList<String>();
	public HashMap<String, ItemStack> heads = new HashMap<String, ItemStack>();
	
	public Inventory gui;
	
	public void openGUI(Player p) {
		int playerCount = pNames.size();
		gui = Bukkit.createInventory(null, sizeGUI(playerCount), ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("guiTitle")));
		
		for(int i = 0; i < playerCount; i++) {
			gui.setItem(i, heads.get(pNames.get(i)));
		}
		p.openInventory(gui);
	}
	
	public int sizeGUI(int count) {
		if(count <= 9) {
			return 9;
		} else if(count <= 18) {
			return 18;
		} else if(count <= 27) {
			return 27;
		} else if(count <= 36) {
			return 36;
		} else if(count <= 45) {
			return 45;
		} else {
			return 54;
		}
	}
	
	public void tpToPlayer(Player p, Player target) {
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.pmessages.getString("teleporting")).replaceAll("%player%", target.getName()).replaceAll("%time%", String.valueOf(plugin.getConfig().getInt("teleportTime"))));
		new BukkitRunnable() {
			
			Location loc = p.getLocation();
			int timer = plugin.getConfig().getInt("teleportTime");
			
			
			@Override
			public void run() {
				if(timer > 0) {
					timer--;
					if(loc.distance(p.getLocation()) > 0.5) {
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.pmessages.getString("youMoved")));
						cancel();
						return;
					}
				} else {
					p.teleport(target);
					cancel();
					return;
				}
				
			}
		}.runTaskTimer(plugin, 0L, (long) 19.99);
	}
	
}
