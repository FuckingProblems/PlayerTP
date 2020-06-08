package xyz.trrlgn.teleporttoplayer;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class JoinAndLeaveListener implements Listener {

	public PlayerTP plugin;
	
	public JoinAndLeaveListener(PlayerTP pl) {
		plugin = pl;
	}
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		plugin.tg.pNames.add(p.getName());
		
		@SuppressWarnings("deprecation")
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
		SkullMeta hm = (SkullMeta) item.getItemMeta();
		hm.setDisplayName(ChatColor.YELLOW + p.getName());
		hm.setOwningPlayer(p);
		item.setItemMeta(hm);
		plugin.tg.heads.put(p.getName(), item);
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		for(int i = 0; i < plugin.tg.pNames.size(); i++) {
			if(e.getPlayer().getName().equals(plugin.tg.pNames.get(i))) {
				plugin.tg.pNames.remove(i);
				break;
			}
		}
		plugin.tg.heads.remove(e.getPlayer().getName());
	}
	
}
