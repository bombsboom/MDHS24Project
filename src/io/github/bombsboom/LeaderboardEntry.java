package io.github.bombsboom;

/**
 * class to contain entries for leaderboard arraylist
 * 
 */

import java.util.UUID;

public class LeaderboardEntry {
	
	public UUID id;
	public int wins;
	
	public LeaderboardEntry(UUID id, int wins) {
		this.id = id;
		this.wins = wins;
	}
}
