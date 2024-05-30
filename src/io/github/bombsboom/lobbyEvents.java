package io.github.bombsboom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
public class lobbyEvents implements Listener {
	
	JavaPlugin plugin;
	
	public lobbyEvents(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	BukkitRunnable lobbyCountdownTask = new BukkitRunnable() {
		int time = 10;
	    public void run() {
	    	for(Player p: Bukkit.getOnlinePlayers()) {
	    		p.sendTitle("Lobby Starting!", "" + time, 0, 20, 0);
		    }
	    	time--;
	    }
	};
	
	BukkitRunnable gameCountdownTask = new BukkitRunnable() {
		int time = 8;
	    public void run() {
	    	if(time <=5) {
	    		for(Player p: Bukkit.getOnlinePlayers()) {
	    			p.sendTitle("Game Starting!", "" + time, 0, 20, 0);
	    		}
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
			
		}else {
			for(Player pl: Bukkit.getOnlinePlayers()) {
		    	   pl.sendTitle(ChatColor.YELLOW + "Waiting... " + Bukkit.getOnlinePlayers().size() + "/5", "", 0, 20, 0);
		    }
		}
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		mainPlugin.playerRoles.remove(p);
		
		if(mainPlugin.playerRoles.size() < 5) {
			for(Player pl: Bukkit.getOnlinePlayers()) {
				lobbyCountdownTask.cancel();
		    	pl.sendTitle(ChatColor.RED + "Cancelled", "Not Enough Players", 0, 20, 0);
		    }
		}
		
	}
	
	@EventHandler
	public void onGameCountDown(GameCountDownEvent e) {
		
		//start countdown
		for(Player p: Bukkit.getOnlinePlayers()) {
	    	   p.sendTitle(ChatColor.GREEN + "Game Starting!", "", 0, 20, 0);
	    }
		
		lobbyCountdownTask.runTaskTimer(plugin, 0, 20);
				
		//start game
		GameStartEvent event = new GameStartEvent();
		Bukkit.getPluginManager().callEvent(event);
	}
	
	@EventHandler
	public void onGameStart(GameStartEvent e) {
		//roles
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
		
		//announce roles
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(mainPlugin.playerRoles.get(p.getUniqueId()) == 0) p.sendTitle(ChatColor.GREEN + "Innocent", "Collect gold to gain a bow", 0, 20, 3);
			if(mainPlugin.playerRoles.get(p.getUniqueId()) == 1) p.sendTitle(ChatColor.BLUE + "Sheriff", "Take down the murderer", 0, 20, 3);
			if(mainPlugin.playerRoles.get(p.getUniqueId()) == 2) p.sendTitle(ChatColor.RED + "Murderer", "Eliminate everyone", 0, 20, 3);
		}
		
		//count down to start
		
		//give items
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(mainPlugin.playerRoles.get(p.getUniqueId()) == 1) { // sheriff
				ItemStack bow = new ItemStack(Material.BOW);
				ItemStack arrow = new ItemStack(Material.ARROW);
				p.getInventory().addItem(bow);
				p.getInventory().addItem(arrow);
			}
			
			if(mainPlugin.playerRoles.get(p.getUniqueId()) == 2) { // murd
				ItemStack sword = new ItemStack(Material.IRON_SWORD);
				p.getInventory().addItem(sword);
			}
		}
	}
}
