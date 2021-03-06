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

public class TankZombie extends EntityZombie {
    public TankZombie(Location location){
        super(EntityTypes.ZOMBIE, ((CraftWorld) location.getWorld()).getHandle());

        this.setPosition(location.getX(), location.getY(), location.getZ());
        this.setCustomName(new ChatComponentText(ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "Tank"));

        // test this

        long currentTime = System.currentTimeMillis()/1000;
        long sessionStart = CustomConfiguration.get().getLong("session-start");
        long sessionTotal = CustomConfiguration.get().getLong("session-total");

        long timeElapsed = currentTime - sessionStart + sessionTotal;
        int days = Math.round(timeElapsed/1200);

        double zombieHealth = ((days - 3) * 3) + 40;
        ((LivingEntity) this.getBukkitEntity()).setMaxHealth(zombieHealth);
        ((LivingEntity) this.getBukkitEntity()).setHealth(zombieHealth);

        int absorptionLevel = (days-3) / 6;

        PotionEffect potionEffect = new PotionEffect(PotionEffectType.ABSORPTION, 100000,  absorptionLevel, false);
       ((LivingEntity) this.getBukkitEntity()).addPotionEffect(potionEffect);


        if(days - 3 <= 3){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHand(new ItemStack(Material.WOODEN_AXE));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));

        } else if(days - 3 < 9){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHand(new ItemStack(Material.STONE_AXE));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
        } else if(days - 3 < 18){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHand(new ItemStack(Material.IRON_AXE));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        } else if(days - 3 < 54){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_AXE));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
        } else if(days - 3 < 108){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHand(new ItemStack(Material.NETHERITE_AXE));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE));
        }


        ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHandDropChance(0.0f);
        ((LivingEntity) this.getBukkitEntity()).getEquipment().setChestplateDropChance(0.0f);

        this.goalSelector.a(0, new PathfinderGoalMeleeAttack(this, .8f, true));
        this.goalSelector.a(1, new PathfinderGoalMoveTowardsTarget(this, .8f, .25f));
        this.goalSelector.a(2, new PathfinderGoalRandomStrollLand(this, .5D));
        this.goalSelector.a(3, new PathfinderGoalRandomLookaround(this));
    }
}
