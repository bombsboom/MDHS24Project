package io.github.bombsboom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
public class lobbyEvents implements Listener {
	
	JavaPlugin plugin;
	
	public lobbyEvents(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	BukkitRunnable countdownTask = new BukkitRunnable() {
		int time = 10;
	    public void run() {
	    	for(Player p: Bukkit.getOnlinePlayers()) {
		    	   p.sendTitle(Integer.toString(time), "", 0, 20, 0);
		       }
	    	time--;
	    }
	};
	
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
		
		if(mainPlugin.playerRoles.size() < 5) {
			for(Player pl: Bukkit.getOnlinePlayers()) {
				countdownTask.cancel();
		    	pl.sendTitle(ChatColor.RED + "Cancelled", "Not Enough Players", 0, 20, 0);
		    }
		}
		
	}
	
	@EventHandler
	public void onGameCountDown(GameCountDownEvent e) {
		// role logic
		Random rand = new Random();
		
		ArrayList<Player> players = new ArrayList<Player>(Bukkit.getOnlinePlayers());
		
		// select sherrif
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
		for(Player p: Bukkit.getOnlinePlayers()) {
	    	   p.sendTitle(ChatColor.GREEN + "Game Starting!", "", 0, 20, 0);
	    }
		
		countdownTask.runTaskTimer(plugin, 0, 20);
				
		//start game
		GameStartEvent event = new GameStartEvent();
		Bukkit.getPluginManager().callEvent(event);
	}
	
	@EventHandler
	public void onGameStart(GameStartEvent e) {
		//on game start
	}
}
