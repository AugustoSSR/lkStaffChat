package com.developer.lk.stafflogin;

import com.developer.lk.api.server.ServerAPI;
import com.developer.lk.stafflogin.commands.CommandAdd;
import com.developer.lk.stafflogin.commands.CommandLogin;
import com.developer.lk.stafflogin.commands.CommandRemove;
import com.developer.lk.stafflogin.commands.CommandReplace;
import com.developer.lk.stafflogin.listeners.EventAll;
import com.developer.lk.stafflogin.listeners.EventJoin;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

public class Register {

    public Register() {
        Comandos("lgadd", new CommandAdd());
        Comandos("lgremove", new CommandRemove());
        Comandos("lg", new CommandLogin());
        Comandos("ls", new CommandLogin());
        Comandos("lgreplace", new CommandReplace());

        Eventos(new EventAll());
        Eventos(new EventJoin());
    }


    void Comandos(String comando, CommandExecutor classe){
        Main.getInstance().getCommand(comando).setExecutor(classe);
    }

    void Eventos(Listener classe){
        Bukkit.getPluginManager().registerEvents(classe, Main.getInstance());
    }



}
