package com.developer.lk.stafflogin.commands;

import com.developer.lk.api.utils.MessegeAPI;
import com.developer.lk.api.utils.PermissionAPI;
import com.developer.lk.stafflogin.database.Database;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandCheck implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(MessegeAPI.COMMAND_CONSOLE_CANNOT_EXECUTE);
            return true;
        }

        Player jogador = (Player)sender;

        if (command.getName().equalsIgnoreCase("lgcheck")) {

            if (!jogador.hasPermission(PermissionAPI.PERMISSION_DIRECTOR)) {
                MessegeAPI.send(jogador, MessegeAPI.COMMAND_NOT_PERMISSION);
                return true;
            }

            if (args.length != 1) {
                sender.sendMessage("§f§m-----------------------------------------------------");
                sender.sendMessage("");
                sender.sendMessage("§7- §a/lgcheck <jogador> - §7Altera o token do jogador.");
                sender.sendMessage("");
                sender.sendMessage("§f§m-----------------------------------------------------");
                return true;
            }

            sender.sendMessage("§f§m-----------------------------------------------------");
            sender.sendMessage("");
            sender.sendMessage("§7- §aNome: " + args[1]);
            sender.sendMessage("§7- §aID: " + Database.fc.getString(args[1] + ".ID"));
            sender.sendMessage("§7- §aToken: " + Database.fc.getString(args[1] + ".Token"));
            sender.sendMessage("§7- §aData de Adicição: " + Database.fc.getString(args[1] + ".Adicionado"));
            sender.sendMessage("§7- §aSituação: " + Database.fc.getString(args[1] + ".Status"));
            sender.sendMessage("§7- §aUltimo Login: " + Database.fc.getString(args[1] + ".UltimoLogin"));
            sender.sendMessage("");
            sender.sendMessage("§f§m-----------------------------------------------------");

            return true;
        }

        return false;
    }

}
