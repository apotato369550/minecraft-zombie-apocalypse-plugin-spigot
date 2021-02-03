package com.zimmer.zombieapocalypse.tasks;

import com.zimmer.zombieapocalypse.files.CustomConfiguration;
import org.bukkit.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class DayCheckerRunnable extends BukkitRunnable {

    private JavaPlugin plugin;

    public DayCheckerRunnable(JavaPlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public void run(){

        if(!(CustomConfiguration.get().getBoolean("apocalypse"))){ return; }
        if(!(CustomConfiguration.get().contains("session-start")) || !(CustomConfiguration.get().contains("session-total"))){ return; }

        long currentTime = System.currentTimeMillis()/1000;
        long sessionStart = CustomConfiguration.get().getLong("session-start");
        long sessionTotal = CustomConfiguration.get().getLong("session-total");

        long timeElapsed = currentTime - sessionStart + sessionTotal;
        int days = Math.round(timeElapsed/1200);


        if(days >= 3 && !(CustomConfiguration.get().getBoolean("zombie-spawn-allowed"))){
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "(!) More than three days have elapsed. There will be no more daytime and mobs will now be allowed to spawn.");
            Bukkit.getServer().broadcastMessage(ChatColor.RED + "(!) Beware, heroes. Darkness comes. The sun will no longer rise and monsters will appear left and right. This is your last stand.");
            Bukkit.getServer().broadcastMessage(ChatColor.RED + "(!) The monsters grow stronger by the hour. Adventurers beware.");

            CustomConfiguration.get().set("zombie-spawn-allowed", true);
            CustomConfiguration.save();
            CustomConfiguration.reload();

            for(World world: Bukkit.getServer().getWorlds()){
                world.setTime(18000);
                world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                world.setDifficulty(Difficulty.HARD);
            }
        }

        if(timeElapsed % 1200 < 20 && CustomConfiguration.get().getBoolean("zombie-spawn-allowed")){
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "(!) Another day has gone by. The monsters continue to grow stronger");
            Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + "(!) Another day has gone by. The monsters continue to grow stronger");
        }
    }
}
