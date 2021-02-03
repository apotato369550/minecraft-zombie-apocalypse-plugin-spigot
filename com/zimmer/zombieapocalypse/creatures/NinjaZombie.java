package com.zimmer.zombieapocalypse.creatures;

import com.zimmer.zombieapocalypse.files.CustomConfiguration;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NinjaZombie extends EntityZombie{
    public NinjaZombie(Location location){
        super(EntityTypes.ZOMBIE, ((CraftWorld) location.getWorld()).getHandle());

        this.setPosition(location.getX(), location.getY(), location.getZ());
        this.setCustomName(new ChatComponentText(ChatColor.GREEN + "" + ChatColor.BOLD + "Ninja"));


        long currentTime = System.currentTimeMillis()/1000;
        long sessionStart = CustomConfiguration.get().getLong("session-start");
        long sessionTotal = CustomConfiguration.get().getLong("session-total");

        long timeElapsed = currentTime - sessionStart + sessionTotal;
        int days = Math.round(timeElapsed/1200);

        double zombieHealth = ((days - 3) * 5) + 20;
        ((LivingEntity) this.getBukkitEntity()).setMaxHealth(zombieHealth);
        ((LivingEntity) this.getBukkitEntity()).setHealth(zombieHealth);


        ((LivingEntity) this.getBukkitEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000,  2, false));
        ((LivingEntity) this.getBukkitEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000,  2, false));
        ((LivingEntity) this.getBukkitEntity()).addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100000,  2, false));


        this.goalSelector.a(0, new PathfinderGoalMeleeAttack(this, 1f, true));
        this.goalSelector.a(1, new PathfinderGoalMoveTowardsTarget(this, 1f, .75f));
        this.goalSelector.a(2, new PathfinderGoalRandomStrollLand(this, 1f));
        this.goalSelector.a(3, new PathfinderGoalRandomLookaround(this));
    }
}
