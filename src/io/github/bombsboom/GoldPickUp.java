package io.github.bombsboom;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import com.sun.istack.internal.NotNull;

public class GoldPickUp extends Event implements Cancellable{

	private boolean isCancelled;
	private static final HandlerList HANDLERS_LIST = new HandlerList();
	
	@Override
	public boolean isCancelled() {
		// TODO Auto-generated method stub
		return isCancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		// TODO Auto-generated method stub
		this.isCancelled = cancelled;
	}

	@Override
	public HandlerList getHandlers() {
		// TODO Auto-generated method stub
		return HANDLERS_LIST;
	}

}
