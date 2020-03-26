package r4tl.r4deathchest.listeners;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import r4tl.r4deathchest.ChestHandler;

public class DeathListener implements Listener {
	
	private final int SEARCH_LIMIT = 33510;
	
	private ChestHandler chand;
	
	public DeathListener(ChestHandler chand) {
		this.chand = chand;
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player p = (Player)event.getEntity();
		List<ItemStack> drops = event.getDrops();
		
		if(event.getKeepInventory()) {
			return;
		}
		
		if(drops == null || drops.size() == 0) {
			return;
		}
		
		if(p.getLocation().getY() < 0) {
			return;
		}
		
		boolean second = drops.size() > 27;
		Location[] chests = findNearestEmpty(p.getLocation(), second);
		World world;
		
		if(chests.length == 1) {
			world = chests[0].getWorld();
			Block b = chests[0].getBlock();
			b.setType(Material.CHEST);
			Chest c = (Chest)(b.getState());
			chand.add(c);
			for(int i = 0; i < drops.size(); i++) {
				c.getInventory().addItem(drops.get(i));
			}
			world.spawnParticle(Particle.EXPLOSION_HUGE, b.getLocation(), 1);
		} else if(chests.length == 2) {
			world = chests[0].getWorld();
			Block b1 = chests[0].getBlock();
			Block b2 = chests[1].getBlock();
			b1.setType(Material.CHEST);
			b2.setType(Material.CHEST);
			Chest c1 = (Chest)(b1.getState());
			Chest c2 = (Chest)(b2.getState());
			chand.add(c1);
			chand.add(c2);
			for(int i = 0; i < 27; i++) {
				c1.getInventory().addItem(drops.get(i));
			}
			for(int i = 27; i < drops.size(); i++) {
				c2.getInventory().addItem(drops.get(i));
			}
			world.spawnParticle(Particle.EXPLOSION_HUGE, b1.getLocation(), 1);
			world.spawnParticle(Particle.EXPLOSION_HUGE, b2.getLocation(), 1);
		}
		if(chests.length != 0) drops.clear();
	}
	
	public Location[] findNearestEmpty(Location l, boolean second) {
		Set<Location> s = new HashSet<Location>();
		Queue<Location> q = new LinkedList<>();
		s.add(l);
		q.add(l);
		
		int count = 0;
		while(count <= SEARCH_LIMIT) {
			Location curr = q.poll();
			// Bukkit.getConsoleSender().sendMessage("(X:" + curr.getBlockX() + ", Y:" + curr.getBlockY() + ", Z:" + curr.getBlockZ() + ')');
			// Do something if the block at the current location is air.
			if(curr.getBlock().getType().equals(Material.AIR)) {
				// Do we need a second chest?
				if(second) {
					for(int i = -1; i <= 1; i += 2) {
						Location nx = new Location(curr.getWorld(), curr.getX() + i, curr.getY(), curr.getZ());
						if(nx.getBlock().getType().equals(Material.AIR)) {
							Location[] set = {curr, nx};
							return set;
						}
						Location ny = new Location(curr.getWorld(), curr.getX(), curr.getY() + i, curr.getZ());
						if(ny.getBlock().getType().equals(Material.AIR)) {
							Location[] set = {curr, ny};
							return set;
						}
						Location nz = new Location(curr.getWorld(), curr.getX(), curr.getY(), curr.getZ() + i);
						if(nz.getBlock().getType().equals(Material.AIR)) {
							Location[] set = {curr, nz};
							return set;
						}
					}
					// We don't need a second chest.
				} else {
					Location[] set = {curr};
					return set;
				}
			}
			// Add neighboring locations of the current location.
			for(int i = -1; i <= 1; i += 2) {
				Location nx = new Location(curr.getWorld(), curr.getX() + i, curr.getY(), curr.getZ());
				Location ny = new Location(curr.getWorld(), curr.getX(), curr.getY() + i, curr.getZ());
				Location nz = new Location(curr.getWorld(), curr.getX(), curr.getY(), curr.getZ() + i);
				if(!s.contains(nx)) {
					s.add(nx);
					q.add(nx);
				}
				if(!s.contains(ny)) {
					s.add(ny);
					q.add(ny);
				}
				if(!s.contains(nz)) {
					s.add(nz);
					q.add(nz);
				}
			}
			count++;
		}
		// Abort! We've searched for too long here.
		Bukkit.getConsoleSender().sendMessage("Search limit reached! Player death at (" + l.getBlockX() + ", " +
				l.getBlockY() + ", " + l.getBlockZ() + ") was not deposited within a deathchest.");
		return null;
	}

}
