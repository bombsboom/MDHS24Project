package io.github.bombsboom;

import org.bukkit.plugin.java.JavaPlugin;

public class mainPlugin extends JavaPlugin{
	@Override
	public void onLoad() {
		getServer().getLogger().info("[Murder Myster] Loaded");
	}
	
	@Override
	public void onEnable() {
		getServer().getLogger().info("[Murder Myster] Enabled");
	}
	
	@Override
	public void onDisable() {
		getServer().getLogger().info("[Murder Myster] Disabled");
	}
}
