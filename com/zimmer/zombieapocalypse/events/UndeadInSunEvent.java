package com.zimmer.zombieapocalypse.events;

import org.bukkit.entity.Drowned;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;

public class UndeadInSunEvent implements Listener {
    @EventHandler
    public static void onUndeadInSunlight(EntityCombustEvent event){
        if(event.getEntity() instanceof Zombie || event.getEntity() instanceof Skeleton ||
        event.getEntity() instanceof Drowned || event.getEntity() instanceof Phantom){
            event.setCancelled(true);
        }
    }
}
