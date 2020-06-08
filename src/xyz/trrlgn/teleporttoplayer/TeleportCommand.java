package xyz.trrlgn.teleporttoplayer;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor {

	public PlayerTP plugin;

	public TeleportCommand(PlayerTP pl) {
		plugin = pl;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("teleport")) {
			if(sender instanceof Player) {
				if(sender.hasPermission("playertp.tp")) {
					plugin.tg.openGUI((Player) sender);
				} else {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.pmessages.getString("noPermission")));
				}
			} else {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.pmessages.getString("mustBePlayer")));
			}
		}
		return true;
	}

}
