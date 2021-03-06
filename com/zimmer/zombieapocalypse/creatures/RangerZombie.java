package com.zimmer.zombieapocalypse.creatures;

import com.zimmer.zombieapocalypse.files.CustomConfiguration;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

public class RangerZombie extends EntitySkeleton{
    public RangerZombie(Location location){
        super(EntityTypes.SKELETON, ((CraftWorld) location.getWorld()).getHandle());

        this.setPosition(location.getX(), location.getY(), location.getZ());
        this.setCustomName(new ChatComponentText(ChatColor.YELLOW + "" + ChatColor.BOLD + "Ranger"));


        long currentTime = System.currentTimeMillis()/1000;
        long sessionStart = CustomConfiguration.get().getLong("session-start");
        long sessionTotal = CustomConfiguration.get().getLong("session-total");

        long timeElapsed = currentTime - sessionStart + sessionTotal;
        int days = Math.round(timeElapsed/1200);

        double zombieHealth = ((days - 3) * 2) + 20;
        ((LivingEntity) this.getBukkitEntity()).setMaxHealth(zombieHealth);
        ((LivingEntity) this.getBukkitEntity()).setHealth(zombieHealth);

        ItemStack zombieHead = new ItemStack(Material.ZOMBIE_HEAD);
        ((LivingEntity) this.getBukkitEntity()).getEquipment().setHelmet(zombieHead);

        ItemStack bow = new ItemStack(Material.BOW);

        if(days - 3 <= 3){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
            bow.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
        } else if(days - 3 < 9){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
            bow.addEnchantment(Enchantment.ARROW_DAMAGE, 2);
        } else if(days - 3 < 18){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
            bow.addEnchantment(Enchantment.ARROW_DAMAGE, 3);
        } else if(days - 3 < 54){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
            bow.addEnchantment(Enchantment.ARROW_DAMAGE, 4);
        } else if(days - 3 < 108){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setLeggings(new ItemStack(Material.NETHERITE_LEGGINGS));
            bow.addEnchantment(Enchantment.ARROW_DAMAGE, 5);
        }

        ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHand(bow);


        ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHandDropChance(0.0f);
        ((LivingEntity) this.getBukkitEntity()).getEquipment().setLeggingsDropChance(0.0f);
        ((LivingEntity) this.getBukkitEntity()).getEquipment().setHelmetDropChance(0.0f);
    }

    // Fix the enchant for the bow
    // Add custom behavior
}
