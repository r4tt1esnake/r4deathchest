package r4tl.r4deathchest.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import r4tl.r4deathchest.ChestHandler;

public class ChestListener implements Listener {
	
	ChestHandler cHand;
	
	public ChestListener(ChestHandler c) {
		cHand = c;
	}
	
	public void onChestOpen(PlayerInteractEvent event) {
		Block b = event.getClickedBlock();
		if(b.getType() == Material.CHEST && cHand.contains(b.getLocation())) {
			Chest chest = (Chest) b;
			// Drop the bloody items
			
			
		}
	}

}
