package r4tl.r4deathchest.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import r4tl.r4deathchest.ChestHandler;

public class BreakListener implements Listener {
	
	private ChestHandler chand;
	
	public BreakListener(ChestHandler chand) {
		this.chand = chand;
	}
	
	@EventHandler
	public void onBlockDamage(BlockDamageEvent e) {
		if(!e.getBlock().getType().equals(Material.CHEST) || !chand.isDeathChest((Chest) e.getBlock().getState())) return;
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockIgnite(BlockIgniteEvent e) {
		if(!e.getBlock().getType().equals(Material.CHEST) || !chand.isDeathChest((Chest) e.getBlock().getState())) return;
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockBurn(BlockBurnEvent e) {
		if(!e.getBlock().getType().equals(Material.CHEST) || !chand.isDeathChest((Chest) e.getBlock().getState())) return;
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockExplode(BlockExplodeEvent e) {
		for(Block b : e.blockList()) {
			if(b.getType().equals(Material.CHEST) && chand.isDeathChest((Chest) b.getState())) e.blockList().remove(b);
		}
	}
	
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent e) {
		for(Block b : e.blockList()) {
			if(b.getType().equals(Material.CHEST) && chand.isDeathChest((Chest) b.getState())) e.blockList().remove(b);
		}
	}

}
