package xyz.trrlgn.teleporttoplayer;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerTP extends JavaPlugin {

	public PlayerTP plugin;
	public TeleportCommand tc = new TeleportCommand(this);
	public TeleportGUI tg = new TeleportGUI(this);
	public JoinAndLeaveListener jll = new JoinAndLeaveListener(this);
	public GUIClickListener gcl = new GUIClickListener(this);
	
	public File messages = new File("plugins/PlayerTP/", "messages.yml");
	public FileConfiguration pmessages = YamlConfiguration.loadConfiguration(messages);
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		saveDefaultMessages();
		reloadMessages();
		getCommand("teleport").setExecutor(new TeleportCommand(this));
		Bukkit.getServer().getPluginManager().registerEvents(new JoinAndLeaveListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new GUIClickListener(this), this);
	}
	
	@Override
	public void onDisable() {
		plugin = null;
		tc = null;
		tg = null;
		jll = null;
		gcl = null;
	}
	
	public void saveDefaultMessages() {
		try {
			saveResource("messages.yml", false);
		} catch (Exception e) {

		}
	}

	/*
	 * The reloadMessages() method just prevents an error stemming from the first use after file generation
	 */
	
	public void reloadMessages() {
		pmessages = YamlConfiguration.loadConfiguration(messages);
	}
	
}
