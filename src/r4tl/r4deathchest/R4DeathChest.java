package r4tl.r4deathchest;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import r4tl.r4deathchest.listeners.DeathListener;

public class R4DeathChest extends JavaPlugin {
	
	ChestHandler cHand;
	
	@Override
	public void onEnable() {
		cHand = new ChestHandler();
		getServer().getPluginManager().registerEvents(new DeathListener(), this);
	}
	
	@Override
	public void onDisable() {
		
	}

}
