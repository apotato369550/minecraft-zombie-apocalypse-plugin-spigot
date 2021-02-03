package com.zimmer.zombieapocalypse.files;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CustomConfiguration {
    private static File file;
    private static FileConfiguration customFile;

    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("ZombieApocalypse").getDataFolder(), "customConfiguration.yaml");

        if(!file.exists()){
            try{
                file.createNewFile();
            } catch (IOException e){
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Zombie Apocalypse Plugin] Unable to create configuration file.");
            }
        }

        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get(){
        return customFile;
    }

    public static void save(){
        try {
            customFile.save(file);
        } catch (IOException e){
            Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Zombie Apocalypse Plugin] Unable to save configuration file.");
        }
    }

    public static void reload(){
        customFile = YamlConfiguration.loadConfiguration(file);
    }
}
