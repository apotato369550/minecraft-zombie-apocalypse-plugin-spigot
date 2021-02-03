package com.zimmer.zombieapocalypse.creatures;

import com.zimmer.zombieapocalypse.files.CustomConfiguration;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RunnerZombie extends EntityZombie {
    public RunnerZombie(Location location){
        super(EntityTypes.ZOMBIE, ((CraftWorld) location.getWorld()).getHandle());

        this.setPosition(location.getX(), location.getY(), location.getZ());
        this.setCustomName(new ChatComponentText(ChatColor.BLUE + "" + ChatColor.BOLD + "Runner"));


        long currentTime = System.currentTimeMillis()/1000;
        long sessionStart = CustomConfiguration.get().getLong("session-start");
        long sessionTotal = CustomConfiguration.get().getLong("session-total");

        long timeElapsed = currentTime - sessionStart + sessionTotal;
        int days = Math.round(timeElapsed/1200);

        int additionalHealth = (days - 3) * 2;
        this.setHealth(20 + additionalHealth);


        double zombieHealth = ((days - 3) * 2) + 20;
        ((LivingEntity) this.getBukkitEntity()).setMaxHealth(zombieHealth);
        ((LivingEntity) this.getBukkitEntity()).setHealth(zombieHealth);


        PotionEffect potionEffect = new PotionEffect(PotionEffectType.SPEED, 100000,  2, false);
        ((LivingEntity) this.getBukkitEntity()).addPotionEffect(potionEffect);



        if(days - 3 <= 3){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHand(new ItemStack(Material.WOODEN_PICKAXE));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));

        } else if(days - 3 < 9){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHand(new ItemStack(Material.STONE_PICKAXE));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
        } else if(days - 3 < 18){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHand(new ItemStack(Material.IRON_PICKAXE));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS));
        } else if(days - 3 < 54){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_PICKAXE));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
        } else if(days - 3 < 108){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHand(new ItemStack(Material.NETHERITE_PICKAXE));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setBoots(new ItemStack(Material.NETHERITE_BOOTS));
        }


        ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHandDropChance(0.0f);
        ((LivingEntity) this.getBukkitEntity()).getEquipment().setBootsDropChance(0.0f);

        this.goalSelector.a(0, new PathfinderGoalMeleeAttack(this, 1f, true));
        this.goalSelector.a(1, new PathfinderGoalMoveTowardsTarget(this, 1f, 1f));
        this.goalSelector.a(2, new PathfinderGoalRandomStrollLand(this, 1D));
        this.goalSelector.a(3, new PathfinderGoalRandomLookaround(this));
    }
}
