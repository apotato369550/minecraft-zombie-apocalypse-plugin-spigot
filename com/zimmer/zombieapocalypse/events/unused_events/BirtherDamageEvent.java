package com.zimmer.zombieapocalypse.events.unused_events;

import com.zimmer.zombieapocalypse.creatures.DroneZombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Random;

public class BirtherDamageEvent implements Listener {
    @EventHandler
    public void onBirtherDamage(EntityDamageEvent event){
        if(!(event.getEntity().getCustomName().toLowerCase().contains("birther"))){ return; }

        int random = new Random().nextInt(2) + 1;
        if(random > 1){
            DroneZombie zombie = new DroneZombie(event.getEntity().getLocation());
        }
    }
}
