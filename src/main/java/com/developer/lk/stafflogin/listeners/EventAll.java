package com.developer.lk.stafflogin.listeners;

import com.developer.lk.api.manager.TittleAPI;
import com.developer.lk.api.utils.MessegeAPI;
import com.developer.lk.stafflogin.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class EventAll implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void Move(PlayerMoveEvent e) {
        if (e.getTo().getX() == e.getFrom().getX() && e.getTo().getBlockY() == e.getFrom().getBlockY() && e.getTo().getZ() == e.getFrom().getZ()) return;
        Player jogador = e.getPlayer();
        if (Main.Blocked.contains(jogador.getName())) {
            TittleAPI.sendTitle(jogador, "§c§lAUTENTICAÇÃO", "§7Você so pode fazer isto após logar.", 40);
            e.setTo(e.getFrom());
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void Interact(PlayerInteractEvent e) {
        Player jogador = e.getPlayer();
        if (Main.Blocked.contains(jogador.getName())) {
            MessegeAPI.send(jogador, "Você so pode fazer isto após logar.");
            TittleAPI.sendTitle(jogador, "§c§lAUTENTICAÇÃO", "§7Você so pode fazer isto após logar.", 40);
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void chat(PlayerChatEvent e) {
        Player jogador = e.getPlayer();
        if (Main.Blocked.contains(jogador.getName())) {
            MessegeAPI.send(jogador, "Você so pode fazer isto após logar.");
            TittleAPI.sendTitle(jogador, "§c§lAUTENTICAÇÃO", "§7Você so pode fazer isto após logar.", 40);
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void Command(PlayerCommandPreprocessEvent e) {
        Player jogador = e.getPlayer();
        if (!(e.getMessage().contains("/login") || e.getMessage().contains("/register")
                || e.getMessage().contains("/logar") || e.getMessage().contains("/registrar")
                || e.getMessage().contains("/pin") || e.getMessage().contains("/ls")
                || e.getMessage().contains("/loginstaff") || e.getMessage().contains("/lg"))) {
            if (Main.Blocked.contains(jogador.getName())) {
                MessegeAPI.send(jogador, "Você so pode fazer isto após logar.");
                TittleAPI.sendTitle(jogador, "§c§lAUTENTICAÇÃO", "§7Você so pode fazer isto após logar.", 40);
                e.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void Quit(PlayerQuitEvent e) {
        Player jogador = e.getPlayer();
        if (Main.Blocked.contains(jogador.getName())) {
            MessegeAPI.send(jogador, "Você so pode fazer isto após logar.");
            Main.Blocked.remove(jogador.getName());
        }

    }

}
