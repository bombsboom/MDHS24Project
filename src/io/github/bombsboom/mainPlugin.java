package io.github.bombsboom;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

public class mainPlugin extends JavaPlugin{
	
	public static HashMap<UUID, Integer> playerRoles = new HashMap<>();
	
	@Override
	public void onLoad() {
		getServer().getLogger().info("[Murder Mystery] Loaded");
		
	}
	
	@Override
	public void onEnable() {
		getServer().getLogger().info("[Murder Mystery] Enabled");
	}
	
	@Override
	public void onDisable() {
		getServer().getLogger().info("[Murder Mystery] Disabled");
	}
}
