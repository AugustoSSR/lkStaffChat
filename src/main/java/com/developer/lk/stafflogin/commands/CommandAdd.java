package com.developer.lk.stafflogin.commands;

import com.developer.lk.api.utils.MessegeAPI;
import com.developer.lk.api.utils.PermissionAPI;
import com.developer.lk.stafflogin.Main;
import com.developer.lk.stafflogin.api.RandomAPI;
import com.developer.lk.stafflogin.database.Database;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Calendar;

public class CommandAdd implements CommandExecutor {

    Calendar c = Calendar.getInstance();

    String randomString = RandomAPI.getString(15, RandomAPI.Mode.APLHANUMERICSYMBOLIC);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(MessegeAPI.COMMAND_CONSOLE_CANNOT_EXECUTE);
            return true;
        }

        Player jogador = (Player)sender;
        if (command.getName().equalsIgnoreCase("lgadd")) {
            if (!jogador.hasPermission(PermissionAPI.PERMISSION_DIRECTOR)) {
                MessegeAPI.send(jogador, MessegeAPI.COMMAND_NOT_PERMISSION);
                return true;
            }

            if (args.length != 2) {
                sender.sendMessage("§2§m-----------------------------------------------------");
                sender.sendMessage("");
                sender.sendMessage("§7- §a/lgadd <jogador> <id> - §7Setar o admin do jogador");
                sender.sendMessage("");
                sender.sendMessage("§2§m-----------------------------------------------------");
                return true;
            }
            if (args[1].length() != 4) {
                MessegeAPI.send(jogador, "ID deve possuir mais de 4 digitos.");
                return true;
            }
            if (!(Database.fc.contains(args[0]))) {
                Database.fc.set(args[0] + ".ID", args[1]);
                Database.fc.set(args[0] + ".Token", randomString);
                Database.fc.set(args[0] + ".Status", true);
                Database.fc.set(args[0] + ".Adicionado", String.valueOf(c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH) + Integer.valueOf(2)) + "/"
                        + c.get(Calendar.YEAR) + " às " + c.get(Calendar.HOUR_OF_DAY) + ":"
                        + c.get(Calendar.MINUTE)));


                Database.SaveConfig();
                MessegeAPI.send(jogador, "O jogador " + args[0] + " foi adicionado a database.");

                sender.sendMessage("§2§m-----------------------------------------------------");
                sender.sendMessage("");
                MessegeAPI.send(jogador, " - §6Usuário: §f" + args[0]);
                MessegeAPI.send(jogador, " - §6Data: §f" + String.valueOf(c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH) + Integer.valueOf(2)) + "/"
                        + c.get(Calendar.YEAR) + " às " + c.get(Calendar.HOUR_OF_DAY) + ":"
                        + c.get(Calendar.MINUTE)));

                // Token e fornecido ao jogador apenas uma vez, após isto somente o DIRETOR do servidor para alterar o mesmo.
                MessegeAPI.send(jogador, " - §6Token: §f" + Database.fc.getString(args[0] + ".Token"));
                sender.sendMessage("");
                sender.sendMessage("§2§m-----------------------------------------------------");

            } else {
                MessegeAPI.send(jogador, "O jogador " + args[0] + " foi encontrado na database.");
            }
            return true;
        }
        return false;
    }

}
