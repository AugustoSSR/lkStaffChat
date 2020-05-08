package com.developer.lk.stafflogin.api;

import com.developer.lk.stafflogin.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class RandomAPI implements Listener {

    public static boolean isLogged(Player p) {
        if (Main.Blocked.contains(p.getName())) {
            return false;
        } else {
            return true;
        }
    }

    public enum Mode{
        APLHANUMERICSYMBOLIC;
    }

    public static String getString(int lenght, Mode mode){
        StringBuilder builder = new StringBuilder();
        String s = "";
        switch(mode){
            case APLHANUMERICSYMBOLIC:
                s = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%&*-_+=></";
                break;
        }
        for(int i = 0; i<lenght; i++){
            double index = Math.random() * s.length();
            builder.append(s.charAt((int)index));
        }
        return builder.toString();
    }

}
