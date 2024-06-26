package io.github.bombsboom;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class playerEvents implements Listener {

	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onPlayerDropItemEvent(PlayerDropItemEvent e) {
		e.setCancelled(true);
	}
	@EventHandler
	public void EntityDamageEvent(EntityDamageEvent e) {

		if (e.getEntity() instanceof Player) { 
			
			DamageSource source = e.getDamageSource();
			
			if (source.getCausingEntity() instanceof Player) { //player attacks player
				Player victim = (Player) e.getEntity();
				Player attacker = (Player) source.getCausingEntity();
				
				if(!source.isIndirect()) { //melee
					Material item = attacker.getInventory().getItemInMainHand().getType();
					
					if(item == Material.IRON_SWORD) {
						victim.setHealth(0);
						
					}else{ // random junk or fist
						e.setCancelled(true);
					}
					
				}else{ //ranged
					UUID id = victim.getUniqueId();
					
					if(DamageCause.PROJECTILE != null){//bow
						if(mainPlugin.playerRoles.get(id) == 2){ //killed murd
							victim.setHealth(0);
							
						}else if(mainPlugin.playerRoles.get(id) == 1 || mainPlugin.playerRoles.get(id) == 0){ //hit inno
							
							attacker.setHealth(0);
						}
					}
				}
			}
		}	

	}
	
	@EventHandler  
	public void onDeath(PlayerDeathEvent event) {
		/*
		 * TODO: IMPLEMENT CORPSE
		 */
		
		
		Player player = event.getEntity();
		
		Location location = player.getLocation();
	    ArmorStand stand = player.getWorld().spawn(location, ArmorStand.class);
	}
}
