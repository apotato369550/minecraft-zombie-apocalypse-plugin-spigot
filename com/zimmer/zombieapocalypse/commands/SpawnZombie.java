package com.zimmer.zombieapocalypse.commands;

import com.zimmer.zombieapocalypse.creatures.*;
import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Player;

public class SpawnZombie implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) { return false; }
        if(!(commandSender.isOp())){ return false; }
        if(strings.length == 0){ return false; }

        // work on this and then test it
        Player player = (Player) commandSender;
        String mainArgument = strings[0].toLowerCase();


        if(mainArgument.equals("infector")){
            InfectorZombie zombie = new InfectorZombie(player.getLocation());
            WorldServer world = ((CraftWorld) player.getWorld()).getHandle();

            world.addEntity(zombie);
        } else if(mainArgument.equals("juggernaut")) {
            JuggernautZombie zombie = new JuggernautZombie(player.getLocation());
            WorldServer world = ((CraftWorld) player.getWorld()).getHandle();

            world.addEntity(zombie);
        } else if(mainArgument.equals("tank")) {
            TankZombie zombie = new TankZombie(player.getLocation());
            WorldServer world = ((CraftWorld) player.getWorld()).getHandle();

            world.addEntity(zombie);
        } else if(mainArgument.equals("runner")) {
            RunnerZombie zombie = new RunnerZombie(player.getLocation());
            WorldServer world = ((CraftWorld) player.getWorld()).getHandle();

            world.addEntity(zombie);
        } else if(mainArgument.equals("drone")) {
            DroneZombie zombie = new DroneZombie(player.getLocation());
            WorldServer world = ((CraftWorld) player.getWorld()).getHandle();

            world.addEntity(zombie);
        } else if(mainArgument.equals("birther")) {
            BirtherZombie zombie = new BirtherZombie(player.getLocation());
            WorldServer world = ((CraftWorld) player.getWorld()).getHandle();

            world.addEntity(zombie);
        } else if(mainArgument.equals("ninja")) {
            NinjaZombie zombie = new NinjaZombie(player.getLocation());
            WorldServer world = ((CraftWorld) player.getWorld()).getHandle();

            world.addEntity(zombie);
        } else if(mainArgument.equals("boomer")) {
            BoomerZombie zombie = new BoomerZombie(player.getLocation());
            WorldServer world = ((CraftWorld) player.getWorld()).getHandle();

            world.addEntity(zombie);
        }  else if(mainArgument.equals("ranger")) {
            RangerZombie zombie = new RangerZombie(player.getLocation());
            WorldServer world = ((CraftWorld) player.getWorld()).getHandle();

            world.addEntity(zombie);
        }  else if(mainArgument.equals("shade")) {
            ShadeZombie zombie = new ShadeZombie(player.getLocation());
            WorldServer world = ((CraftWorld) player.getWorld()).getHandle();

            world.addEntity(zombie);
        }  else if(mainArgument.equals("grenadier")) {
            GrenadierZombie zombie = new GrenadierZombie(player.getLocation());
            WorldServer world = ((CraftWorld) player.getWorld()).getHandle();

            world.addEntity(zombie);
        }   else if(mainArgument.equals("galvanizer")) {
            GalvanizerZombie zombie = new GalvanizerZombie(player.getLocation());
            WorldServer world = ((CraftWorld) player.getWorld()).getHandle();

            world.addEntity(zombie);
        }  else if(mainArgument.equals("scorcher")) {
            ScorcherZombie zombie = new ScorcherZombie(player.getLocation());
            WorldServer world = ((CraftWorld) player.getWorld()).getHandle();

            world.addEntity(zombie);
        }  else if(mainArgument.equals("lechon")) {
            LechonZombie zombie = new LechonZombie(player.getLocation());
            WorldServer world = ((CraftWorld) player.getWorld()).getHandle();

            world.addEntity(zombie);
        }  else if(mainArgument.equals("matriarch")) {
            MatriarchZombie zombie = new MatriarchZombie(player.getLocation());
            WorldServer world = ((CraftWorld) player.getWorld()).getHandle();

            world.addEntity(zombie);
        }  else if(mainArgument.equals("hellspawn")) {
            HellspawnZombie zombie = new HellspawnZombie(player.getLocation());
            WorldServer world = ((CraftWorld) player.getWorld()).getHandle();

            world.addEntity(zombie);
        } else {
            player.sendMessage(ChatColor.RED + "(!) That is not a valid zombie to spawn");
            return false;
        }

        return true;
    }
}
