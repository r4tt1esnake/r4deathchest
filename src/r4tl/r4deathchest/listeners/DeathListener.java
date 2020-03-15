package r4tl.r4deathchest.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import r4tl.r4deathchest.DChestHandler;

public class DeathListener implements Listener {
	
	DChestHandler cHand;
	
	public DeathListener(DChestHandler c) {
		cHand = c;
	}
	
	public void onPlayerDeath(PlayerDeathEvent event) {
		
	}

}
