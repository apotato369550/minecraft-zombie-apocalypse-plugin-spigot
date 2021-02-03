package com.zimmer.zombieapocalypse.events;

import com.zimmer.zombieapocalypse.creatures.DroneZombie;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class ZombieDeathEvent implements Listener {
    @EventHandler
    public void onZombieDeath(EntityDeathEvent event){
        String entityName = "";

        try{
            entityName = event.getEntity().getCustomName().toLowerCase();
        } catch (Exception e){
            //
        }

        if(entityName.contains("ninja")){
            int random = new Random().nextInt(3) + 1;

            event.getEntity().getWorld().dropItemNaturally(
                    event.getEntity().getLocation(),
                    new ItemStack(Material.SPIDER_EYE, new Random().nextInt(2))
            );
        } else if(entityName.contains("boomer")) {
            int random = new Random().nextInt(3) + 1;

            if(random > 2){
                event.getEntity().getWorld().createExplosion(event.getEntity().getLocation(), 3f);
            } else {
                event.getEntity().getWorld().dropItemNaturally(
                        event.getEntity().getLocation(),
                        new ItemStack(Material.GUNPOWDER, new Random().nextInt(2) + 1)
                );
            }
        } else if(entityName.contains("shade")){
                event.getEntity().getWorld().dropItemNaturally(
                        event.getEntity().getLocation(),
                        new ItemStack(Material.ENDER_PEARL, new Random().nextInt(2))
                );
        } else if(entityName.contains("scorcher")){
            event.getEntity().getWorld().dropItemNaturally(
                    event.getEntity().getLocation(),
                    new ItemStack(Material.BLAZE_ROD, new Random().nextInt(2))
            );
        }
    }
}
