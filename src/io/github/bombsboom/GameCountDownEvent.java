package io.github.bombsboom;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameCountDownEvent extends Event implements Cancellable{

	private static final HandlerList HANDLERS_LIST = new HandlerList();
	private boolean isCancelled;
	
	public GameCountDownEvent(){
        this.isCancelled = false;
    }
	
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
