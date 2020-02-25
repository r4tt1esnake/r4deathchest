package r4tl.r4deathchest.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import r4tl.r4deathchest.ChestHandler;

public class DeathListener implements Listener {
	
	ChestHandler cHand;
	
	public DeathListener(ChestHandler c) {
		cHand = c;
	}
	
	public void onPlayerDeath(PlayerDeathEvent event) {
		
	}

}
