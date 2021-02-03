package com.zimmer.zombieapocalypse.events.unused_events;

import com.zimmer.zombieapocalypse.creatures.DroneZombie;
import com.zimmer.zombieapocalypse.creatures.ShadeZombie;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class ShadeDeathEvent implements Listener {
    @EventHandler
    public void onShadeDeath(EntityDeathEvent event){
        if(!(event.getEntity().getCustomName().toLowerCase().contains("shade"))){ return; }
        int random = new Random().nextInt(3) + 1;

        if(random > 2){
            for(int i = 0; i < 3; i++){
                DroneZombie drone = new DroneZombie(event.getEntity().getLocation());
            }
        } else {
            event.getEntity().getWorld().dropItemNaturally(
                    event.getEntity().getLocation(),
                    new ItemStack(Material.GUNPOWDER, new Random().nextInt(2))
            );
        }
    }
}
