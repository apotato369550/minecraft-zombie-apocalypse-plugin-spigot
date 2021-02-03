package com.zimmer.zombieapocalypse.events.unused_events;

import com.zimmer.zombieapocalypse.creatures.BoomerZombie;
import org.bukkit.Material;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class BoomerDeathEvent implements Listener {
    @EventHandler
    public void onBoomerDeath(EntityDeathEvent event){
        if(!(event.getEntity() instanceof Zombie)){ return; }
        if(!(event.getEntity().getCustomName().toLowerCase().contains("boomer"))){ return; }

        int random = new Random().nextInt(3) + 1;

        if(random > 2){
            event.getEntity().getWorld().createExplosion(event.getEntity().getLocation(), 3f);
        } else {
            event.getEntity().getWorld().dropItemNaturally(
                    event.getEntity().getLocation(),
                    new ItemStack(Material.GUNPOWDER, new Random().nextInt(2))
            );
        }
    }
}
