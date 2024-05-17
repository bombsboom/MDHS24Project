package io.github.bombsboom;

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
public class lobbyEvents implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		mainPlugin.playerRoles.put(p.getUniqueId(),-1);
		
		if(mainPlugin.playerRoles.size() >= 5) {
			GameCountDownEvent event = new GameCountDownEvent();
			Bukkit.getPluginManager().callEvent(event);
			
		}
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		mainPlugin.playerRoles.remove(p);
	}
	
	@EventHandler
	public void onGameCountDown(GameCountDownEvent e) {
		// role logic
		Random rand = new Random();
		
		Object[] players = Bukkit.getOnlinePlayers().toArray();
		
		int next = rand.nextInt(players.length);
		
		do {
			
		} while()
		
	}
}
