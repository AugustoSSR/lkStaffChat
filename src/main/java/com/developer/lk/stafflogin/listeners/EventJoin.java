package com.developer.lk.stafflogin.listeners;

import java.util.Calendar;
import java.util.List;

import com.developer.lk.api.utils.MessegeAPI;
import com.developer.lk.stafflogin.Main;
import com.developer.lk.stafflogin.api.RandomAPI;
import com.developer.lk.stafflogin.database.Database;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventJoin implements Listener {

    Calendar c = Calendar.getInstance();

    @EventHandler
    public void entrar(PlayerJoinEvent e) {
        Player jogador = e.getPlayer();
        if (Database.fc.contains(jogador.getName())) {
            Main.Blocked.add(jogador.getName());

            jogador.sendMessage("§2§m-----------------------------------------------------");
            jogador.sendMessage("");
            MessegeAPI.send(jogador, " - §6Usuário: §f" + jogador.getName());
            MessegeAPI.send(jogador, " - §6Data: §f" + String.valueOf(c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH) + Integer.valueOf(2)) + "/"
                    + c.get(Calendar.YEAR) + " às " + c.get(Calendar.HOUR_OF_DAY) + ":"
                    + c.get(Calendar.MINUTE)));
            MessegeAPI.send(jogador, " - §6Token: §fUtilize o seu token para logar.");
            MessegeAPI.send(jogador, " - Por favor utilize §f/lg <token> para logar.");
            jogador.sendMessage("");
            jogador.sendMessage("§2§m-----------------------------------------------------");

            Database.fc.set(jogador.getName() + ".UltimoLogin", String.valueOf(c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH) + Integer.valueOf(2)) + "/"
                    + c.get(Calendar.YEAR) + " às " + c.get(Calendar.HOUR_OF_DAY) + ":"
                    + c.get(Calendar.MINUTE)));
            Database.SaveConfig();
        }
    }

}
