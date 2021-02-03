package com.zimmer.zombieapocalypse.events;

import net.minecraft.server.v1_16_R3.EntityZombie;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class PlayerDamagedByZombieEvent implements Listener {
    @EventHandler
    public static void onHitByUndead(EntityDamageByEntityEvent event){
        if(!(event.getEntity() instanceof Player)){ return; }

        Player player = ((Player) event.getEntity()).getPlayer();
        Entity damager = event.getDamager();
        String damagerName = "";

        try {
            damagerName = damager.getCustomName();
        } catch (Exception e){
            //
        }

        if(damagerName.toLowerCase().contains("infector")){
            player.sendMessage(ChatColor.RED + "(!) A nasty zombie bite! Ooh that's gotta hurt");
            player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 1));
        } else if(damager instanceof EntityZombie || damager instanceof Zombie){
            int random = new Random().nextInt(4) + 1;
            if(random > 3) {
                player.sendMessage(ChatColor.RED + "(!) A zombie bit you!!!");
                player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 10, 1));
            }
        } else if(damagerName.toLowerCase().contains("shade")){
            player.sendMessage(ChatColor.RED + "(!) You feel your flesh begin to rot...");
        }

    }
}
