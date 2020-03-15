package r4tl.r4deathchest;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import r4tl.r4deathchest.listeners.DeathListener;

public class R4DeathChest extends JavaPlugin {
	
	DChestHandler cHand;
	
	@Override
	public void onEnable() {
		cHand = new DChestHandler();
		getServer().getPluginManager().registerEvents(new DeathListener(cHand), this);
	}
	
	@Override
	public void onDisable() {
		
	}

}
