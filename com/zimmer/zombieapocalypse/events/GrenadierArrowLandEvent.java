package com.zimmer.zombieapocalypse.events;

import com.zimmer.zombieapocalypse.creatures.GrenadierZombie;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class GrenadierArrowLandEvent implements Listener {
    @EventHandler
    public void onArrowLand(ProjectileHitEvent event){
        if(!(event.getEntity() instanceof Arrow)){ return;}
        if(!(((Entity) event.getEntity().getShooter()).getCustomName().toLowerCase().contains("grenadier"))){ return; }

        Projectile arrow = event.getEntity();
        Location location = event.getEntity().getLocation();

        arrow.getWorld().spawnEntity(location, EntityType.PRIMED_TNT);
        arrow.remove();
    }
}
