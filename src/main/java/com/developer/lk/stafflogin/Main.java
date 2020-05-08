package com.developer.lk.stafflogin;

import com.developer.lk.api.manager.ConfigAPI;
import com.developer.lk.api.server.ServerAPI;
import com.developer.lk.stafflogin.database.Database;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin {

    public static ArrayList<String> Blocked = new ArrayList<>();

    @Override
    public void onEnable() {
        ServerAPI.getConsole().sendMessage("");
        ServerAPI.getConsole().sendMessage("    §a§l[lkStaffLogin] §f Plugin sendo inicializado.");
        ServerAPI.getConsole().sendMessage("    §a§l[lkStaffLogin] §f Versão " + getServer().getBukkitVersion());
        ServerAPI.getConsole().sendMessage("");

        // Ativando classe de registros.
        new Register();
        Database.create();
        Database.SaveConfig();
    }

    @Override
    public void onDisable() {
        ServerAPI.getConsole().sendMessage("");
        ServerAPI.getConsole().sendMessage("    §a§l[lkStaffLogin] §f Plugin sendo finalizado.");
        ServerAPI.getConsole().sendMessage("    §a§l[lkStaffLogin] §f Versão " + getServer().getBukkitVersion());
        ServerAPI.getConsole().sendMessage("");
    }

    public static Main getInstance() {
        return getPlugin(Main.class);
    }
}
