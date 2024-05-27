package io.github.bombsboom;


import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;


public class GameEvents {
	
	public class EventListener implements Listener{
		@EventHandler  
		public void onDeath(PlayerDeathEvent event) {
			Player player = event.getEntity();
			
			Location location = player.getLocation();
		    ArmorStand stand = player.getWorld().spawn(location, ArmorStand.class);
		    
		}
	}
}