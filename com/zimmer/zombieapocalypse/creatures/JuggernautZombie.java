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

public class JuggernautZombie extends EntityZombie {
    public JuggernautZombie(Location location){
        super(EntityTypes.ZOMBIE, ((CraftWorld) location.getWorld()).getHandle());

        this.setPosition(location.getX(), location.getY(), location.getZ());
        this.setCustomName(new ChatComponentText(ChatColor.RED + "" + ChatColor.BOLD + "Juggernaut"));


        long currentTime = System.currentTimeMillis()/1000;
        long sessionStart = CustomConfiguration.get().getLong("session-start");
        long sessionTotal = CustomConfiguration.get().getLong("session-total");

        long timeElapsed = currentTime - sessionStart + sessionTotal;
        int days = Math.round(timeElapsed/1200);

        double zombieHealth = ((days - 3) * 2) + 25;
        ((LivingEntity) this.getBukkitEntity()).setMaxHealth(zombieHealth);
        ((LivingEntity) this.getBukkitEntity()).setHealth(zombieHealth);


        PotionEffect potionEffect = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100000,  2, false);
        ((LivingEntity) this.getBukkitEntity()).addPotionEffect(potionEffect);


        if(days - 3 <= 3){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHand(new ItemStack(Material.WOODEN_SWORD));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));

        } else if(days - 3 < 9){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHand(new ItemStack(Material.STONE_SWORD));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
        } else if(days - 3 < 18){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
        } else if(days - 3 < 54){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_SWORD));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
        } else if(days - 3 < 108){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHand(new ItemStack(Material.NETHERITE_SWORD));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setHelmet(new ItemStack(Material.NETHERITE_HELMET));
        }


        ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHandDropChance(0.0f);
        ((LivingEntity) this.getBukkitEntity()).getEquipment().setHelmetDropChance(0.0f);

        this.goalSelector.a(0, new PathfinderGoalMeleeAttack(this, .75f, true));
        this.goalSelector.a(1, new PathfinderGoalMoveTowardsTarget(this, .75f, .5f));
        this.goalSelector.a(2, new PathfinderGoalRandomStrollLand(this, .5D));
        this.goalSelector.a(3, new PathfinderGoalRandomLookaround(this));
    }
}
