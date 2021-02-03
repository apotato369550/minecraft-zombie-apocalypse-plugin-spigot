package com.zimmer.zombieapocalypse.events.unused_events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Random;

public class BoomerDamageEvent implements Listener {
    @EventHandler
    public void onBoomerDamage(EntityDamageEvent event){
        if(!(event.getEntity().getCustomName().toLowerCase().contains("boomer"))){ return; }
        if(event.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION || event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION ){ return; }

        int random = new Random().nextInt(2) + 1;
        if(random > 1){
            event.getEntity().getWorld().createExplosion(event.getEntity().getLocation(), 3f);
        }
    }
}
