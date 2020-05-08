package com.developer.lk.stafflogin.database;

import java.io.File;
import java.io.IOException;

import com.developer.lk.stafflogin.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Database {

    static File f;
    public static FileConfiguration fc;
    static Database m;

    public static void create() {
        f = new File(Main.getInstance().getDataFolder() + "/usuarios.db");
        fc = YamlConfiguration.loadConfiguration(f);
    }

    public static void SaveConfig() {
        try {
            fc.save(f);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Database config() {
        return m;
    }

}
