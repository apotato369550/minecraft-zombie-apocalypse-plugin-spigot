package com.zimmer.zombieapocalypse.events;

import com.zimmer.zombieapocalypse.creatures.DroneZombie;
import com.zimmer.zombieapocalypse.creatures.HellspawnZombie;
import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Random;

public class ZombieDamageEvent implements Listener {
    @EventHandler
    public void onZombieDamage(EntityDamageEvent event){
        // Work on this
        String entityName = "";

        try{
            entityName = event.getEntity().getCustomName().toLowerCase();
        } catch (Exception e){
            //
        }

        if(entityName.contains("birther")){
            int random = new Random().nextInt(2) + 1;
            if(random > 1){
                WorldServer world = ((CraftWorld) event.getEntity().getWorld()).getHandle();
                DroneZombie zombie = new DroneZombie(event.getEntity().getLocation());
                try {
                    world.addEntity(zombie);
                } catch (Exception e){
                    //
                }
            }
        } else if(entityName.contains("boomer")) {
            try {
                if (event.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)) {
                    event.setCancelled(true);
                    return;
                }
            } catch (Exception e){
                //
            }

            int random = new Random().nextInt(3) + 1;
            if(random > 2){
                event.getEntity().getWorld().createExplosion(event.getEntity().getLocation(), 2f);
            }
        } else if(entityName.contains("matriarch")){

            int random = new Random().nextInt(2) + 1;
            if(random > 1){
                WorldServer world = ((CraftWorld) event.getEntity().getWorld()).getHandle();
                HellspawnZombie zombie = new HellspawnZombie(event.getEntity().getLocation());
                try {
                    world.addEntity(zombie);
                } catch (Exception e){
                    //
                }
            }
        } else if(entityName.contains("grenadier") || entityName.contains("grenadier mount")){
            try {
                if (event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)) {
                    event.setCancelled(true);
                    return;
                }
            } catch (Exception e){
                //
            }
        }
    }
}
