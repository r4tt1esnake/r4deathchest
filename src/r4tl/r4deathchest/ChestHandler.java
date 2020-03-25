package r4tl.r4deathchest;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;

public class ChestHandler {
	
	private Set<Location> dc = new HashSet<Location>();
	private FileConfiguration locConfig;

	public ChestHandler(FileConfiguration locConfig) {
		this.locConfig = locConfig;
	}

	public void add(Chest c) {
		dc.add(c.getLocation());
	}
	
	public void remove(Chest c) {
		dc.remove(c.getLocation());
	}
	
	public boolean isDeathChest(Chest c) {
		return dc.contains(c.getLocation());
	}
	
	public void read() {
		int size = locConfig.getInt("size");
		for(int i = 0; i < size; i++) {
			dc.add(locConfig.getLocation("location." + i));
			locConfig.set("location." + i, null);
		}
		locConfig.set("size", 0);
	}

	public void write() {
		locConfig.set("size", dc.size());
		int i = 0;
		for(Location l : dc) {
			locConfig.set("location." + i, l);
			i++;
		}
	}

}
