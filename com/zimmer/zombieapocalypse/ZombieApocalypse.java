package com.zimmer.zombieapocalypse;

import com.zimmer.zombieapocalypse.commands.Apocalypse;
import com.zimmer.zombieapocalypse.commands.SpawnZombie;
import com.zimmer.zombieapocalypse.events.*;
import com.zimmer.zombieapocalypse.files.CustomConfiguration;
import com.zimmer.zombieapocalypse.tasks.DayCheckerRunnable;
import com.zimmer.zombieapocalypse.tasks.ZombieSpawnerThread;
import org.bukkit.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class ZombieApocalypse extends JavaPlugin {
    @Override
    public void onEnable(){
        BukkitTask dayCheckerTask = new DayCheckerRunnable(this).runTaskTimer(this, 0,400);
        BukkitTask zombieSpawnerTask = new ZombieSpawnerThread(this).runTaskTimer(this, 0,100);


        getServer().getPluginManager().registerEvents(new MobSpawnEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerDamagedByZombieEvent(), this);
        getServer().getPluginManager().registerEvents(new UndeadInSunEvent(), this);
        getServer().getPluginManager().registerEvents(new GrenadierArrowLandEvent(), this);
        getServer().getPluginManager().registerEvents(new ZombieDamageEvent(), this);
        getServer().getPluginManager().registerEvents(new ZombieDeathEvent(), this);


        getCommand("apocalypse").setExecutor(new Apocalypse());
        getCommand("spawn").setExecutor(new SpawnZombie());


        getConfig().options().copyDefaults();
        saveDefaultConfig();

        CustomConfiguration.setup();
        CustomConfiguration.get().addDefault("apocalypse", false);
        CustomConfiguration.get().options().copyDefaults(true);
        CustomConfiguration.save();

        if(CustomConfiguration.get().getBoolean("apocalypse") && CustomConfiguration.get().contains("session-start")){
            CustomConfiguration.get().set("session-start", System.currentTimeMillis()/1000);
            CustomConfiguration.save();
            CustomConfiguration.reload();
            if(CustomConfiguration.get().getBoolean("zombie-spawn-allowed")){
                for(World world: Bukkit.getServer().getWorlds()){
                    world.setTime(18000);
                    world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
                    world.setDifficulty(Difficulty.HARD);
                }
            }
        }

        getServer().getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "[Zombie Apocalypse Plugin] Plugin enabled. Prepare for the apocalypse");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "[Zombie Apocalypse Plugin] Plugin Disabled. This isn't over.");

        CustomConfiguration.get().set("session-end", System.currentTimeMillis()/1000);

        long sessionStart = CustomConfiguration.get().getLong("session-start");
        long sessionEnd = CustomConfiguration.get().getLong("session-end");

        long totalTimeElapsed = CustomConfiguration.get().getLong("session-total") + (sessionEnd - sessionStart);

        CustomConfiguration.get().set("session-total", totalTimeElapsed);
        CustomConfiguration.save();
    }
}

// After day 3, make it always nighttime
// That way, monsters will constantly spawn
