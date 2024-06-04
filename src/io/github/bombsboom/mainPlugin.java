package io.github.bombsboom;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

public class mainPlugin extends JavaPlugin{
	
	public static HashMap<UUID, Integer> playerRoles = new HashMap<>();
	
	@Override
	public void onLoad() {
		getServer().getLogger().info("[Murder Mystery] Loaded"); 
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("leaderBoard.txt"));
			String line; 
			while ((line = br.readLine()) != null) {
				
			}
		} catch (IOException e) {
			getServer().getLogger().info("[Murder Mystery] leaderboard txt io error"); 
		}
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
