package r4tl.r4deathchest;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.block.Chest;

public class ChestHandler {
	
	private Set<Location> dc = new HashSet<Location>();
	private final String file = "config.txt";

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
		
	}

	public void write() {

	}

}
