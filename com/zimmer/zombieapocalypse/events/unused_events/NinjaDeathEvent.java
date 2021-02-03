package com.zimmer.zombieapocalypse.events.unused_events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class NinjaDeathEvent implements Listener {
    @EventHandler
    public void onNinjaDeath(EntityDeathEvent event){
        if(!(event.getEntity().getCustomName().toLowerCase().contains("ninja"))){ return; }
        int random = new Random().nextInt(3) + 1;

        event.getEntity().getWorld().dropItemNaturally(
                event.getEntity().getLocation(),
                new ItemStack(Material.SPIDER_EYE, new Random().nextInt(2))
        );
    }
}
