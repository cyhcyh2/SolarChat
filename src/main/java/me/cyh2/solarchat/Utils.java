package me.cyh2.solarchat;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Utils {
    public static String ReColor (String text) {
        return text.replace("&" ,"§");
    }
    public static void ps (Plugin plugin, Player player, Sound sound) {
        player.playSound(player.getLocation(), sound, 100.0f, 1.0f);
    }
    public static Boolean getSolarBan () {
        try{
            Class.forName("me.cyh2.solarban.SolarBan");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
    public static Boolean getSolarWebSocket () {
        try{
            Class.forName("me.cyh2.solarwebsocket.SolarWebSocket");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
    public static Boolean UpdateChecker () {
        return false;
    }
}
