package r4tl.r4deathchest;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import r4tl.r4deathchest.listeners.BreakListener;
import r4tl.r4deathchest.listeners.ChestListener;
import r4tl.r4deathchest.listeners.DeathListener;

public class R4DeathChest extends JavaPlugin {
	
	private ChestHandler chand;
	private File locFile;
	private FileConfiguration locConfig;
	
	@Override
	public void onEnable() {
		createLocFile();
		chand = new ChestHandler(locConfig);
		chand.read();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new DeathListener(chand), this);
		pm.registerEvents(new ChestListener(chand), this);
		pm.registerEvents(new BreakListener(chand), this);
	}
	
	@Override
	public void onDisable() {
		chand.write();
		try {
			locConfig.save(locFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createLocFile() {
		locFile = new File("plugins/R4DeathChest", "data.yml");
		if(!locFile.exists()) {
			locFile.getParentFile().mkdirs();
			try {
				locFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		locConfig = new YamlConfiguration();
		try {
			locConfig.load(locFile);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

}
