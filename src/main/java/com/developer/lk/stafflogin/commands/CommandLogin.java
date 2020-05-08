package com.developer.lk.stafflogin.commands;

import com.developer.lk.api.manager.TittleAPI;
import com.developer.lk.api.utils.MessegeAPI;
import com.developer.lk.api.utils.PermissionAPI;
import com.developer.lk.stafflogin.Main;
import com.developer.lk.stafflogin.database.Database;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Calendar;
import java.util.List;

public class CommandLogin implements CommandExecutor {

    Calendar c = Calendar.getInstance();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player jogador = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("lg") || cmd.getName().equalsIgnoreCase("ls")) {
                if (args.length != 1) {
                    sender.sendMessage("§2§m-----------------------------------------------------");
                    sender.sendMessage("");
                    sender.sendMessage("§7- §a/ls <token> - §7Remove o admin do jogador");
                    sender.sendMessage("");
                    sender.sendMessage("§2§m-----------------------------------------------------");
                    return true;
                }
                if (!(Database.fc.contains(jogador.getName()))) {
                    MessegeAPI.send(jogador, PermissionAPI.PERMISSION_DIRECTOR);
                    return true;
                } else {
                    if (!Main.Blocked.contains(jogador.getName())) {
                        MessegeAPI.send(jogador, "Você já está logado.");
                        return true;
                    }
                    if (args[0].equalsIgnoreCase(Database.fc.getString(jogador.getName() + ".Token"))
                            && !args[0].equalsIgnoreCase("Nenhum")) {
                        Main.Blocked.remove(jogador.getName());
                        sender.sendMessage("§2§m-----------------------------------------------------");
                        sender.sendMessage("");
                        MessegeAPI.send(jogador, " - §6Usuário: §f" + jogador.getName());
                        MessegeAPI.send(jogador, " - §6Data: §f" + String.valueOf(c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH) + Integer.valueOf(2)) + "/"
                                + c.get(Calendar.YEAR) + " às " + c.get(Calendar.HOUR_OF_DAY) + ":"
                                + c.get(Calendar.MINUTE)));
                        sender.sendMessage("");
                        sender.sendMessage("§2§m-----------------------------------------------------");

                        jogador.playSound(jogador.getLocation(), Sound.LEVEL_UP, 10, 10);
                        TittleAPI.sendTitle(jogador, "§c§lAUTENTICAÇÃO", "§7Seu token foi aceito, autenticação efetuada com sucesso.", 40);

                    } else {
                        Main.Blocked.remove(jogador.getName());

                        jogador.kickPlayer("§cFalha na verificação do token \n§cToken Incorreto");


                        if (jogador.hasPermission(PermissionAPI.PERMISSION_DIRECTOR)) {
                            sender.sendMessage("§2§m-----------------------------------------------------");
                            sender.sendMessage("");
                            sender.sendMessage("   §6§l ERRO DE TOKEN ADMINITRADOR");
                            sender.sendMessage("");
                            MessegeAPI.send(jogador, " - §6Usuário: §f" + jogador.getName());
                            MessegeAPI.send(jogador, " - §6Data: §f" + String.valueOf(c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH) + Integer.valueOf(2)) + "/"
                                    + c.get(Calendar.YEAR) + " às " + c.get(Calendar.HOUR_OF_DAY) + ":"
                                    + c.get(Calendar.MINUTE)));
                            sender.sendMessage("");
                            sender.sendMessage("§2§m-----------------------------------------------------");
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
}