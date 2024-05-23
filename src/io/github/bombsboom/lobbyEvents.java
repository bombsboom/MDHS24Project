package io.github.bombsboom;

import java.util.ArrayList;
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
		
		ArrayList<Player> players = new ArrayList<Player>(Bukkit.getOnlinePlayers());
		
		// select sheriff
		int next = rand.nextInt(players.size());
		
		UUID id = players.get(next).getUniqueId();
		
		mainPlugin.playerRoles.replace(id,1);
		players.remove(next);
		
		// select murd
		next = rand.nextInt(players.size());
				
		id = players.get(next).getUniqueId();
				
		mainPlugin.playerRoles.replace(id,2);
		
		// rest of players become inno
		for(Player p: players) {
			mainPlugin.playerRoles.replace(p.getUniqueId(),0);
		}
		
		//start countdown
		
		//start game
		GameStartEvent event = new GameStartEvent();
		Bukkit.getPluginManager().callEvent(event);
	}
	
	@EventHandler
	public void onGameStart(GameStartEvent e) {
		//on game start
	}
}
