package io.github.bombsboom;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class protectionEvents implements Listener {

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
			
			if (source.getCausingEntity() instanceof Player) {
				Player victim = (Player) e.getEntity();
				Player attacker = (Player) source.getCausingEntity();
				
				if(!source.isIndirect()) {
					Material item = attacker.getInventory().getItemInMainHand().getType();
					if(item == Material.IRON_SWORD) {
						victim.setHealth(0);
						
					}else{ // random junk or fist
						e.setCancelled(true);
					}
				}else{
					
					 UUID id = victim.getUniqueId();
					
					if(DamageCause.PROJECTILE != null){
						if(mainPlugin.playerRoles.get(id) == 0){
							victim.setHealth(0);
						}
					}
					else if(mainPlugin.playerRoles.get(id) == 0){
						attacker.setHealth(0);
						attacker.dropItem(true);
					}
					
						
			}
			
		} else {
			e.setCancelled(true);
		}
	}

}
}
