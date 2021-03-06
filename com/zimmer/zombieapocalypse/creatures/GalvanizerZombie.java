package com.zimmer.zombieapocalypse.creatures;

import com.zimmer.zombieapocalypse.files.CustomConfiguration;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Spider;
import org.bukkit.inventory.ItemStack;

public class GalvanizerZombie extends EntityPigZombie {
    public GalvanizerZombie(Location location){
        super(EntityTypes.ZOMBIFIED_PIGLIN, ((CraftWorld) location.getWorld()).getHandle());

        this.setPosition(location.getX(), location.getY(), location.getZ());
        this.setCustomName(new ChatComponentText(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Galvanizer"));


        long currentTime = System.currentTimeMillis()/1000;
        long sessionStart = CustomConfiguration.get().getLong("session-start");
        long sessionTotal = CustomConfiguration.get().getLong("session-total");

        long timeElapsed = currentTime - sessionStart + sessionTotal;
        int days = Math.round(timeElapsed/1200);

        double zombieHealth = ((days - 3) * 2)+ 25;
        ((LivingEntity) this.getBukkitEntity()).setMaxHealth(zombieHealth);
        ((LivingEntity) this.getBukkitEntity()).setHealth(zombieHealth);

        ItemStack rod = new ItemStack(Material.BLAZE_ROD);
        rod.getItemMeta().addEnchant(Enchantment.FIRE_ASPECT, 2, true);
        rod.getItemMeta().addEnchant(Enchantment.DAMAGE_ALL, 4, true);
        rod.getItemMeta().addEnchant(Enchantment.KNOCKBACK, 1, true);

        rod.setItemMeta(rod.getItemMeta());

        ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHand(rod);

        if(days - 3 <= 3){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        } else if(days - 3 < 18){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        }  else if(days - 3 < 108){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        }

        ((LivingEntity) this.getBukkitEntity()).getEquipment().setHelmetDropChance(0.0f);
        ((LivingEntity) this.getBukkitEntity()).getEquipment().setChestplateDropChance(0.0f);
        ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHandDropChance(0.0f);

        this.goalSelector.a(0, new PathfinderGoalMeleeAttack(this, 1f, true));
        this.goalSelector.a(1, new PathfinderGoalMoveTowardsTarget(this, 1f, .75f));
        this.goalSelector.a(2, new PathfinderGoalRandomStrollLand(this, .75D));
        this.goalSelector.a(3, new PathfinderGoalRandomLookaround(this));
    }
}
