package com.zimmer.zombieapocalypse.tasks;

import com.zimmer.zombieapocalypse.creatures.*;
import com.zimmer.zombieapocalypse.files.CustomConfiguration;
import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class ZombieSpawnerThread extends BukkitRunnable {
    private JavaPlugin plugin;

    public ZombieSpawnerThread(JavaPlugin plugin){
        this.plugin = plugin;
    }

    private Location generateRandomLocationNearPlayer(Player player, int xRadius, int zRadius){
        Random random = new Random();
        int randomX = random.nextInt(xRadius) * (random.nextBoolean() ? 1: -1);
        int randomZ = random.nextInt(zRadius) * (random.nextBoolean() ? 1: -1);
        Location location = player.getLocation().add(randomX, 2, randomZ);
        int counter = 0;
        while(location.getBlock().getType() != Material.AIR && counter < 10) {
            randomX = random.nextInt(40) * (random.nextBoolean() ? 1 : -1);
            randomZ = random.nextInt(40) * (random.nextBoolean() ? 1 : -1);
            location = player.getLocation().add(randomX, 2, randomZ);
            counter++;
        }
        if(location.getBlock().getType() != Material.AIR){
            return null;
        }
        return location;
    }

    @Override
    public void run(){
        if(!(CustomConfiguration.get().getBoolean("zombie-spawn-allowed"))){ return; }
        for(Player player: plugin.getServer().getOnlinePlayers()) {
            int xRadius = 25;
            int zRadius = 25;
            Random random = new Random();

            Location location = generateRandomLocationNearPlayer(player, xRadius, zRadius);

            long currentTime = System.currentTimeMillis()/1000;
            long sessionStart = CustomConfiguration.get().getLong("session-start");
            long sessionTotal = CustomConfiguration.get().getLong("session-total");

            long timeElapsed = currentTime - sessionStart + sessionTotal;
            int days = Math.round(timeElapsed/1200);

            if(player.getWorld().getEnvironment() == World.Environment.NORMAL){
                if(days >= 3){
                    if(location == null || location.getBlock().getLightLevel() >= 11){ return; }
                    int zombieRandom = random.nextInt(10) + 1;
                    WorldServer world = ((CraftWorld) player.getWorld()).getHandle();
                    if(zombieRandom <= 2){
                        RunnerZombie zombie = new RunnerZombie(location);
                        world.addEntity(zombie);
                        player.getServer().broadcastMessage(ChatColor.GREEN + "An runner zombie has spawned near the player. ");
                    } else if(zombieRandom <= 4){
                        InfectorZombie zombie = new InfectorZombie(location);
                        world.addEntity(zombie);
                        player.getServer().broadcastMessage(ChatColor.GREEN + "A infector zombie has spawned near the player. ");
                    } else if(zombieRandom == 5){
                        JuggernautZombie zombie = new JuggernautZombie(location);
                        world.addEntity(zombie);
                        player.getServer().broadcastMessage(ChatColor.GREEN + "A juggernaut zombie has spawned near the player. ");
                    } else if(zombieRandom == 6){
                        TankZombie zombie = new TankZombie(location);
                        world.addEntity(zombie);
                        player.getServer().broadcastMessage(ChatColor.GREEN + "A tank zombie has spawned near the player. ");
                    } else {
                        return;
                    }
                }

                if(days >= 9){
                    location = generateRandomLocationNearPlayer(player, xRadius, zRadius);

                    if(location == null || location.getBlock().getLightLevel() >= 11){ return; }

                    int zombieRandom = random.nextInt(10) + 1;
                    WorldServer world = ((CraftWorld) player.getWorld()).getHandle();
                    if(zombieRandom == 1){
                        RangerZombie zombie = new RangerZombie(location);
                        world.addEntity(zombie);
                        player.getServer().broadcastMessage(ChatColor.GREEN + "A ranger zombie has spawned near the player. ");
                    } else if(zombieRandom == 2){
                        NinjaZombie zombie = new NinjaZombie(location);
                        world.addEntity(zombie);
                        player.getServer().broadcastMessage(ChatColor.GREEN + "A ninja zombie has spawned near the player. ");
                    } else if(zombieRandom == 3){
                        BirtherZombie zombie = new BirtherZombie(location);
                        world.addEntity(zombie);
                        player.getServer().broadcastMessage(ChatColor.GREEN + "A birther zombie has spawned near the player. ");
                    } else if(zombieRandom == 4){
                        BoomerZombie zombie = new BoomerZombie(location);
                        world.addEntity(zombie);
                        player.getServer().broadcastMessage(ChatColor.GREEN + "A boomer zombie has spawned near the player. ");
                    } else {
                        return;
                    }
                }

                if(days >= 14){
                    // location = generateRandomLocationNearPlayer(player, xRadius, zRadius);

                    location = player.getLocation();

                    if(location == null || location.getBlock().getLightLevel() >= 11){ return; }

                    int zombieRandom = random.nextInt(10) + 1;
                    WorldServer world = ((CraftWorld) player.getWorld()).getHandle();
                    if(zombieRandom == 1){
                        GrenadierZombie zombie = new GrenadierZombie(location);
                        world.addEntity(zombie);
                        player.getServer().broadcastMessage(ChatColor.GREEN + "A grenadier zombie has spawned near the player ");
                    } else if(zombieRandom == 2){
                        ShadeZombie zombie = new ShadeZombie(location);
                        world.addEntity(zombie);
                        player.getServer().broadcastMessage(ChatColor.GREEN + "A shade zombie has spawned near the player. ");
                    } else {
                        return;
                    }
                }
            } else if(player.getWorld().getEnvironment() == World.Environment.NETHER){
                for(int i = 0; i < new Random().nextInt(3) + 1; i++) {
                    location = generateRandomLocationNearPlayer(player, 10, 10);
                    int zombieRandom = random.nextInt(10) + 1;
                    WorldServer world = ((CraftWorld) player.getWorld()).getHandle();
                    if (zombieRandom <= 2) {
                        ScorcherZombie zombie = new ScorcherZombie(location);
                        world.addEntity(zombie);
                        player.getServer().broadcastMessage(ChatColor.GREEN + "A scorcher zombie has spawned near the player. ");
                    } else if (zombieRandom <= 4) {
                        GalvanizerZombie zombie = new GalvanizerZombie(location);
                        world.addEntity(zombie);
                        player.getServer().broadcastMessage(ChatColor.GREEN + "A galvanizer zombie has spawned near the player. ");
                    } else if (zombieRandom == 5) {
                        LechonZombie zombie = new LechonZombie(location);
                        world.addEntity(zombie);
                        player.getServer().broadcastMessage(ChatColor.GREEN + "A lechon zombie has spawned near the player. ");
                    } else if (zombieRandom == 6) {
                        MatriarchZombie zombie = new MatriarchZombie(location);
                        world.addEntity(zombie);
                        player.getServer().broadcastMessage(ChatColor.GREEN + "A matriarch zombie has spawned near the player. ");
                    } else {
                        return;
                    }
                }
            } else if(player.getWorld().getEnvironment() == World.Environment.THE_END){
                for(int i = 0; i < new Random().nextInt(3) + 1; i++) {
                    location = generateRandomLocationNearPlayer(player, 15, 15);
                    int zombieRandom = random.nextInt(10) + 1;
                    WorldServer world = ((CraftWorld) player.getWorld()).getHandle();
                    if (zombieRandom < 5) {
                        ShadeZombie zombie = new ShadeZombie(location);
                        world.addEntity(zombie);
                        player.getServer().broadcastMessage(ChatColor.GREEN + "A shade zombie has spawned near the player. ");
                    }
                }
            }
        }
    }
}
