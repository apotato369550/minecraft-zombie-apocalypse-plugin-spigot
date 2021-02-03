package com.zimmer.zombieapocalypse.creatures;

import com.zimmer.zombieapocalypse.files.CustomConfiguration;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

public class MatriarchZombie extends EntityZombie{
    public MatriarchZombie(Location location){
        super(EntityTypes.ZOMBIE, ((CraftWorld) location.getWorld()).getHandle());

        this.setPosition(location.getX(), location.getY(), location.getZ());
        this.setCustomName(new ChatComponentText(ChatColor.WHITE + "" + ChatColor.BOLD + "Matriarch"));


        long currentTime = System.currentTimeMillis()/1000;
        long sessionStart = CustomConfiguration.get().getLong("session-start");
        long sessionTotal = CustomConfiguration.get().getLong("session-total");

        long timeElapsed = currentTime - sessionStart + sessionTotal;
        int days = Math.round(timeElapsed/1200);

        double zombieHealth = ((days - 3) * 3) + 30;
        ((LivingEntity) this.getBukkitEntity()).setMaxHealth(zombieHealth);
        ((LivingEntity) this.getBukkitEntity()).setHealth(zombieHealth);
        ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHand(new ItemStack(Material.WITHER_SKELETON_SKULL));
        ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInOffHand(new ItemStack(Material.GHAST_TEAR));


        ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHandDropChance(0.0f);
        ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInOffHandDropChance(0.2f);

        this.goalSelector.a(0, new PathfinderGoalMeleeAttack(this, .75f, true));
        this.goalSelector.a(1, new PathfinderGoalMoveTowardsTarget(this, .75f, .75f));
        this.goalSelector.a(2, new PathfinderGoalRandomStrollLand(this, .75D));
        this.goalSelector.a(3, new PathfinderGoalRandomLookaround(this));
    }
}
