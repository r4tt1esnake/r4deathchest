package r4tl.r4deathchest;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import r4tl.r4deathchest.listeners.BreakListener;
import r4tl.r4deathchest.listeners.ChestListener;
import r4tl.r4deathchest.listeners.DeathListener;

public class R4DeathChest extends JavaPlugin {
	
	ChestHandler chand;
	
	@Override
	public void onEnable() {
		chand = new ChestHandler();
		chand.read();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new DeathListener(chand), this);
		pm.registerEvents(new ChestListener(chand), this);
		pm.registerEvents(new BreakListener(chand), this);
	}
	
	@Override
	public void onDisable() {
		chand.write();
	}

}
