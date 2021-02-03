package com.zimmer.zombieapocalypse.commands;

import com.zimmer.zombieapocalypse.files.CustomConfiguration;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;


public class Apocalypse implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length == 0){ return false; }

        Player player = (Player) commandSender;
        String mainArgument = strings[0].toLowerCase();

        if(mainArgument.equals("start")){
            if(strings.length > 1){ return false; }
            if(CustomConfiguration.get().getBoolean("apocalypse")){
                player.sendMessage(ChatColor.YELLOW + "(!) The apocalypse has already started.");
            }

            long time = System.currentTimeMillis()/1000;

            CustomConfiguration.get().set("apocalypse", true);
            CustomConfiguration.get().set("began", time);
            CustomConfiguration.get().set("session-start", time);
            CustomConfiguration.get().set("session-total", 0);
            CustomConfiguration.get().set("zombie-spawn-allowed", false);

            CustomConfiguration.save();
            CustomConfiguration.reload();

            for(World world: Bukkit.getServer().getWorlds()){
                world.setTime(0);
                world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
                world.setDifficulty(Difficulty.PEACEFUL);
            }

            Bukkit.broadcastMessage(ChatColor.DARK_GREEN + "(!) Attention all players, the apocalypse has begun. You have three days to prepare. Spend your time wisely");

        } else if(mainArgument.equals("restart")){
            if(strings.length == 1){
                player.sendMessage(ChatColor.RED + "(?) Are you sure? If so, do '/apocalypse restart confirm' instead");
                return true;
            }

            String confirm = strings[1].toLowerCase();

            if(!(confirm.equals("confirm"))){ return false; }

            long time = System.currentTimeMillis()/1000;

            CustomConfiguration.get().set("apocalypse", true);
            CustomConfiguration.get().set("began", time);
            CustomConfiguration.get().set("session-start", time);
            CustomConfiguration.get().set("session-total", 0);
            CustomConfiguration.get().set("zombie-spawn-allowed", false);

            CustomConfiguration.save();
            CustomConfiguration.reload();

            for(World world: Bukkit.getServer().getWorlds()){
                world.setTime(0);
                world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
                world.setDifficulty(Difficulty.PEACEFUL);
            }

            Bukkit.broadcastMessage(ChatColor.GREEN + "(!) Apocalypse restart successful.");
            Bukkit.broadcastMessage(ChatColor.DARK_GREEN + "(!) The apocalypse has begun. You have three days to prepare. Spend your time wisely");

        } else if(mainArgument.equals("status")){
            // Fix this
            if(strings.length > 1){ return false; }
            if(!(CustomConfiguration.get().getBoolean("apocalypse"))){
                player.sendMessage(ChatColor.YELLOW + "(!) An apocalypse needs to be ongoing in order for you to see its status");
                return true;
            }

            if(!(CustomConfiguration.get().contains("session-start")) || !(CustomConfiguration.get().contains("session-total"))){
                player.sendMessage(ChatColor.YELLOW + "(!) You haven't started an apocalypse yet");
                return true;
            }

            long currentTime = System.currentTimeMillis()/1000;
            long sessionStart = CustomConfiguration.get().getLong("session-start");
            long sessionTotal = CustomConfiguration.get().getLong("session-total");

            long timeElapsed = currentTime - sessionStart + sessionTotal;
            int days = Math.round(timeElapsed/1200);

            player.sendMessage(ChatColor.YELLOW + "(!) So far, " + days + " days (" + timeElapsed + " seconds) have elapsed since the start of the apocalypse.");

        } else if(mainArgument.equals("halt")){
            if(!(CustomConfiguration.get().getBoolean("apocalypse"))){
                player.sendMessage(ChatColor.YELLOW + "(!) The apocalypse is already halted");
                return true;
            }

            CustomConfiguration.get().set("session-end", System.currentTimeMillis()/1000);

            long sessionStart = CustomConfiguration.get().getLong("session-start");
            long sessionEnd = CustomConfiguration.get().getLong("session-end");

            long totalTimeElapsed = CustomConfiguration.get().getLong("session-total") + (sessionEnd - sessionStart);

            CustomConfiguration.get().set("session-total", totalTimeElapsed);
            CustomConfiguration.get().set("apocalypse", false);
            CustomConfiguration.get().set("zombie-spawn-allowed", false);

            CustomConfiguration.save();
            CustomConfiguration.reload();
            for(World world: Bukkit.getWorlds()){
                for(Entity entity : world.getEntities()){
                    if(entity instanceof Monster){
                        entity.remove();
                    }
                }
                world.setTime(0);
                world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
                world.setDifficulty(Difficulty.PEACEFUL);
            }


            Bukkit.broadcastMessage(ChatColor.GREEN + "(!) Apocalypse halted successfully.");

        } else if(mainArgument.equals("resume")){
            if(CustomConfiguration.get().getBoolean("apocalypse")){
                player.sendMessage(ChatColor.YELLOW + "(!) The apocalypse is currently ongoing");
                return true;
            }

            if(!(CustomConfiguration.get().contains("began") || CustomConfiguration.get().contains("session-total"))){
                player.sendMessage(ChatColor.YELLOW + "(!) You have not started any apocalypse");
                return true;
            }

            CustomConfiguration.get().set("apocalypse", true);
            CustomConfiguration.get().set("zombie-spawn-allowed", true);
            CustomConfiguration.get().set("session-start", System.currentTimeMillis()/1000);
;
            CustomConfiguration.save();
            CustomConfiguration.reload();

            for(World world: Bukkit.getServer().getWorlds()){
                world.setTime(18000);
                world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                world.setDifficulty(Difficulty.HARD);
            }

            player.sendMessage(ChatColor.GREEN + "(!) Apocalypse resumed successfully");
        } else if(mainArgument.equals("delete")){
            // Fix this as well
            if(strings.length == 1){
                player.sendMessage(ChatColor.RED + "(?) Are you sure you want to delete your current progress for this apocalypse? Do '/apocalypse delete confirm' instead");
                return true;
            }

            String confirm = strings[1].toLowerCase();

            if(!(confirm.equals("confirm"))){ return false; }

            CustomConfiguration.get().set("apocalypse", false);
            CustomConfiguration.get().set("zombie-spawn-allowed", null);
            CustomConfiguration.get().set("session-start", null);
            CustomConfiguration.get().set("session-end", null);
            CustomConfiguration.get().set("session-total", null);
            CustomConfiguration.get().set("began", null);

            CustomConfiguration.save();
            CustomConfiguration.reload();

            for(World world: Bukkit.getWorlds()){
                for(Entity entity : world.getEntities()){
                    if(entity instanceof Monster){
                        entity.remove();
                    }
                }
                world.setTime(0);
                world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);
                world.setDifficulty(Difficulty.PEACEFUL);
            }

            player.sendMessage(ChatColor.GREEN + "(!) Apocalypse deletion successful");
        }

        return true;
    }
}
