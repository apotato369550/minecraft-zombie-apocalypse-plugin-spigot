package com.zimmer.zombieapocalypse.events;

import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;


public class MobSpawnEvent implements Listener {
    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent event){
        if(event.getEntity() instanceof Husk || event.getEntity() instanceof Creeper
        || event.getEntity() instanceof Slime || event.getEntity() instanceof Silverfish) {
            event.setCancelled(true);
        }
        if((event.getEntity() instanceof Zombie && event.getEntity().getCustomName() == null)
        || (event.getEntity() instanceof Skeleton && event.getEntity().getCustomName() == null)
        || (event.getEntity() instanceof Stray && event.getEntity().getCustomName() == null)
        || (event.getEntity() instanceof WitherSkeleton && event.getEntity().getCustomName() == null)){
            event.setCancelled(true);
        }
        if(event.getEntity() instanceof Enderman && event.getEntity().getWorld().getEnvironment() != World.Environment.THE_END){
            event.setCancelled(true);
        }
    }
}
