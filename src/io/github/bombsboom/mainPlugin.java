package io.github.bombsboom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

public class mainPlugin extends JavaPlugin{
	
	public static HashMap<UUID, Integer> playerRoles = new HashMap<>();
	public static ArrayList<LeaderboardEntry> leaderboard = new ArrayList<>();
	@Override
	public void onLoad() {
		getServer().getLogger().info("[Murder Mystery] Loaded"); 
		
		//load leaderboard from txt
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("leaderboard.txt"));
			String line; 
			while ((line = br.readLine()) != null) {
				LeaderboardEntry le = new LeaderboardEntry(UUID.fromString(line.split(",")[0]),Integer.parseInt(line.split(",")[1]));
				leaderboard.add(le);
			}
		} catch (IOException e) {
			getServer().getLogger().info("[Murder Mystery] leaderboard txt io error"); 
		}
	} 
	
	
	@Override
	public void onEnable() {
		getServer().getLogger().info("[Murder Mystery] Enabled");
	}
	
	@Override
	public void onDisable() {
		getServer().getLogger().info("[Murder Mystery] Disabled");
		
		//save leaderboard to txt
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter("leaderboard.txt"));
			String line; 
			for(LeaderboardEntry le : leaderboard) {
				bw.write(le.id.toString() + "," + le.wins);
			}
		} catch (IOException e) {
			getServer().getLogger().info("[Murder Mystery] leaderboard txt io error"); 
		}
	}
}
