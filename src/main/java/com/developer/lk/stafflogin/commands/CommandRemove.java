package com.developer.lk.stafflogin.commands;

import com.developer.lk.api.utils.MessegeAPI;
import com.developer.lk.api.utils.PermissionAPI;
import com.developer.lk.stafflogin.database.Database;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Calendar;

public class CommandRemove implements CommandExecutor {

    Calendar c = Calendar.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(MessegeAPI.COMMAND_CONSOLE_CANNOT_EXECUTE);
            return true;
        }

        Player jogador = (Player)sender;
        if (command.getName().equalsIgnoreCase("lgremove")) {
            if (!jogador.hasPermission(PermissionAPI.PERMISSION_DIRECTOR)) {
                MessegeAPI.send(jogador, MessegeAPI.COMMAND_NOT_PERMISSION);
                return true;
            }

            if (args.length != 1) {
                sender.sendMessage("§2§m-----------------------------------------------------");
                sender.sendMessage("");
                sender.sendMessage("§7- §a/lgremove <jogador> - §7Remove o admin do jogador");
                sender.sendMessage("");
                sender.sendMessage("§2§m-----------------------------------------------------");
                return true;
            }

            if (!(Database.fc.contains(args[0]))) {
                MessegeAPI.send(jogador, "O jogador " + args[0] + " não foi encontrado na database.");
                return true;
            }

            sender.sendMessage("§2§m-----------------------------------------------------");
            sender.sendMessage("");
            MessegeAPI.send(jogador, " - §6Usuário: §f" + jogador.getName());
            MessegeAPI.send(jogador, " - §6Data: §f" + String.valueOf(c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH) + Integer.valueOf(2)) + "/"
                    + c.get(Calendar.YEAR) + " às " + c.get(Calendar.HOUR_OF_DAY) + ":"
                    + c.get(Calendar.MINUTE)));
            MessegeAPI.send(jogador, " - §6Token: §f" + Database.fc.getString(jogador.getName() + ".Token"));
            sender.sendMessage("");
            sender.sendMessage("§2§m-----------------------------------------------------");

            Database.fc.set(args[0] + ".ID", null);
            Database.fc.set(args[0] + ".Token", null);
            Database.fc.set(args[0] + ".Status", false);
            Database.SaveConfig();
            MessegeAPI.send(jogador, "O jogador " + args[0] + " foi removido da database.");
            return true;
        }
        return false;
    }

}
