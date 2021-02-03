package com.zimmer.zombieapocalypse.creatures;

import com.zimmer.zombieapocalypse.files.CustomConfiguration;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

public class ShadeZombie extends EntitySkeletonWither {
    public ShadeZombie(Location location){
        super(EntityTypes.WITHER_SKELETON, ((CraftWorld) location.getWorld()).getHandle());

        this.setPosition(location.getX(), location.getY(), location.getZ());
        this.setCustomName(new ChatComponentText(ChatColor.GRAY + "" + ChatColor.BOLD + "Shade"));


        long currentTime = System.currentTimeMillis()/1000;
        long sessionStart = CustomConfiguration.get().getLong("session-start");
        long sessionTotal = CustomConfiguration.get().getLong("session-total");

        long timeElapsed = currentTime - sessionStart + sessionTotal;
        int days = Math.round(timeElapsed/1200);

        double zombieHealth = ((days - 3) * 4) + 40;
        ((LivingEntity) this.getBukkitEntity()).setMaxHealth(zombieHealth);
        ((LivingEntity) this.getBukkitEntity()).setHealth(zombieHealth);

        ItemStack zombieHead = new ItemStack(Material.ZOMBIE_HEAD);
        ((LivingEntity) this.getBukkitEntity()).getEquipment().setHelmet(zombieHead);


        if(days - 3 <= 3){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHand(new ItemStack(Material.WOODEN_SWORD));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
        } else if(days - 3 < 9){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHand(new ItemStack(Material.STONE_SWORD));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
        } else if(days - 3 < 18){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS));
        } else if(days - 3 < 54){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_SWORD));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
        } else if(days - 3 < 108){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHand(new ItemStack(Material.NETHERITE_SWORD));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setLeggings(new ItemStack(Material.NETHERITE_LEGGINGS));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setBoots(new ItemStack(Material.NETHERITE_BOOTS));
        }

        ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHandDropChance(0.0f);
        ((LivingEntity) this.getBukkitEntity()).getEquipment().setHelmetDropChance(0.0f);
        ((LivingEntity) this.getBukkitEntity()).getEquipment().setChestplateDropChance(0.0f);
        ((LivingEntity) this.getBukkitEntity()).getEquipment().setLeggingsDropChance(0.0f);
        ((LivingEntity) this.getBukkitEntity()).getEquipment().setBootsDropChance(0.0f);

        this.goalSelector.a(0, new PathfinderGoalMeleeAttack(this, 1f, true));
        this.goalSelector.a(1, new PathfinderGoalMoveTowardsTarget(this, 1f, .75f));
        this.goalSelector.a(2, new PathfinderGoalRandomStrollLand(this, 1f));
        this.goalSelector.a(3, new PathfinderGoalRandomLookaround(this));
    }
}
