package com.developer.lk.stafflogin.commands;

import com.developer.lk.api.utils.MessegeAPI;
import com.developer.lk.api.utils.PermissionAPI;
import com.developer.lk.stafflogin.api.RandomAPI;
import com.developer.lk.stafflogin.database.Database;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.xml.crypto.Data;

public class CommandReplace implements CommandExecutor {

    String randomString = RandomAPI.getString(15, RandomAPI.Mode.APLHANUMERICSYMBOLIC);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(MessegeAPI.COMMAND_CONSOLE_CANNOT_EXECUTE);
            return true;
        }

        Player jogador = (Player)sender;

        if (command.getName().equalsIgnoreCase("lgreplace")) {

            if (!jogador.hasPermission(PermissionAPI.PERMISSION_DIRECTOR)) {
                MessegeAPI.send(jogador, MessegeAPI.COMMAND_NOT_PERMISSION);
                return true;
            }

            if (args.length != 1) {
                sender.sendMessage("§2§m-----------------------------------------------------");
                sender.sendMessage("");
                sender.sendMessage("§7- §a/lgreplace <jogador> - §7Altera o token do jogador.");
                sender.sendMessage("");
                sender.sendMessage("§2§m-----------------------------------------------------");
                return true;
            }

            Database.fc.set(args[0] + ".Token", randomString);
            Database.SaveConfig();

            MessegeAPI.send(jogador, "O token do jogador agora é: " + Database.fc.getString(jogador.getName() + ".Token"));

            return true;
        }

        return false;
    }
}
