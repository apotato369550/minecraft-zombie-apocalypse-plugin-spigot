package com.zimmer.zombieapocalypse.creatures;

import com.zimmer.zombieapocalypse.files.CustomConfiguration;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Spider;
import org.bukkit.inventory.ItemStack;

public class GrenadierZombie extends EntitySkeleton {
    public GrenadierZombie(Location location){
        super(EntityTypes.SKELETON, ((CraftWorld) location.getWorld()).getHandle());

        this.setPosition(location.getX(), location.getY(), location.getZ());
        this.setCustomName(new ChatComponentText(ChatColor.GOLD + "" + ChatColor.BOLD + "Grenadier"));


        long currentTime = System.currentTimeMillis()/1000;
        long sessionStart = CustomConfiguration.get().getLong("session-start");
        long sessionTotal = CustomConfiguration.get().getLong("session-total");

        long timeElapsed = currentTime - sessionStart + sessionTotal;
        int days = Math.round(timeElapsed/1200);

        double zombieHealth = (days - 3) + 20;
        ((LivingEntity) this.getBukkitEntity()).setMaxHealth(zombieHealth);
        ((LivingEntity) this.getBukkitEntity()).setHealth(zombieHealth);

        ItemStack zombieHead = new ItemStack(Material.ZOMBIE_HEAD);
        ((LivingEntity) this.getBukkitEntity()).getEquipment().setHelmet(zombieHead);

        ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHand(new ItemStack(Material.BOW));

        if(days - 3 <= 3){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
        } else if(days - 3 < 18){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
        }  else if(days - 3 < 108){
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setLeggings(new ItemStack(Material.NETHERITE_LEGGINGS));
        }

        /*
        Spider spider = location.getWorld().spawn(location, Spider.class);
        spider.setCustomName(ChatColor.GOLD + "Grenadier Mount");
        spider.setCustomNameVisible(true);
        spider.setPassenger(this.getBukkitEntity());

         */

        EntitySpider spider = new EntitySpider(EntityTypes.SPIDER, ((CraftWorld) location.getWorld()).getHandle());

        ((LivingEntity) spider.getBukkitEntity()).setMaxHealth(zombieHealth);
        ((LivingEntity) spider.getBukkitEntity()).setHealth(zombieHealth);

        spider.setPosition(location.getX(), location.getY(), location.getZ());
        spider.setCustomName(new ChatComponentText(ChatColor.GOLD + "" + ChatColor.BOLD + "Grenadier Mount"));

        ((LivingEntity) spider.getBukkitEntity()).setPassenger(this.getBukkitEntity());

        WorldServer world = ((CraftWorld) location.getWorld()).getHandle();
        world.addEntity(spider);


        ((LivingEntity) this.getBukkitEntity()).getEquipment().setHelmetDropChance(0.0f);
        ((LivingEntity) this.getBukkitEntity()).getEquipment().setLeggingsDropChance(0.0f);
    }
}