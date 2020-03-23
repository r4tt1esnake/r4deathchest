package r4tl.r4deathchest.listeners;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import r4tl.r4deathchest.ChestHandler;

public class ChestListener implements Listener {
	
	private ChestHandler chand;
	
	public ChestListener(ChestHandler chand) {
		this.chand = chand;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getPlayer().getGameMode().equals(GameMode.SPECTATOR) || event.getPlayer().getGameMode().equals(GameMode.ADVENTURE)) return;
		Block b = event.getClickedBlock();
		if(b != null && b.getType().equals(Material.CHEST)) {
			Chest c = (Chest) b.getState();
			if(chand.isDeathChest(c)) {
				Inventory drop = c.getInventory();
				World world = b.getWorld();
				Location loc = b.getLocation();
				for(ItemStack i : drop.getContents()) {
					if(i != null) world.dropItemNaturally(loc, i);
				}
				chand.remove(c);
				drop.clear();
				b.setType(Material.AIR);
				world.spawnParticle(Particle.EXPLOSION_HUGE, b.getLocation(), 1);
				event.setCancelled(true);
			}
		}
	}

}
