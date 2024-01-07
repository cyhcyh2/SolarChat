package me.cyh2.solarchat.Update;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static me.cyh2.solarchat.SolarChat.*;
import static me.cyh2.solarchat.Utils.ReColor;

public class UpdateChecker {

    private static boolean updateAvailable;

    private static int versionsBehind;

    private static String spigotVersion;

    public UpdateChecker() {
    }

    public static void init() {
        String currentVersion = plugin.getDescription().getVersion();
        spigotVersion = getSpigotVersion();
        versionsBehind = calculate(spigotVersion, currentVersion);
        if (versionsBehind >= 1) {
            updateAvailable = true;
            if (Update == null) {
                clogger.sendMessage(ReColor("检查&b&l更新&r中！"));
                clogger.sendMessage(ReColor("Checking &b&lUpdate&r！"));
                clogger.sendMessage("You are " + versionsBehind + " version" + ((versionsBehind == 1) ? "" : "s") + " behind! Get SolarChat v" + spigotVersion + " at:");
                clogger.sendMessage("https://www.spigotmc.org/resources/114180");
            }
            for (Player player : Bukkit.getOnlinePlayers())
                if (player.isOp()) {
                    player.sendMessage(ReColor("嘿，管理员！&bSolarChat&r有一个&b&l更新&r可以使用！"));
                    player.sendMessage(ReColor("Hey，OP！&bSolarChat&r has an &b&lUpdate&r to use！"));
                    TextComponent text = new TextComponent(ReColor("发现了新版本 &b&l" + versionsBehind + " version" + ((versionsBehind == 1) ? "" : "s") + "&r 哦~ &b&l点击这里&r 来下载&b&lSolarChat v" + spigotVersion + " &r吧~"));
                    text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("&7点击&b&l打开&7下载页面哦~"))

                            .color(ChatColor.AQUA)
                            .create()));
                    text.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/114180"));
                    player.spigot().sendMessage(text);
                    TextComponent text1 = new TextComponent(ReColor("You are &b&l" + versionsBehind + " version" + ((versionsBehind == 1) ? "" : "s") + "&r behind! &b&lClick here&r to get &b&lSolarChat v" + spigotVersion + " &r!"));
                    text1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("&7Click &b&lhere&r to go to the download page!"))

                            .color(ChatColor.AQUA)
                            .create()));
                    text1.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/114180"));
                    player.spigot().sendMessage(text1);
                }
        } else {
            if (Update == null) {
                clogger.sendMessage(ReColor(PluginName + " 没有可用的&b&l更新&r哦~"));
                clogger.sendMessage(ReColor(PluginName + " No&b&l new versions&r found for the plugin!"));
            }
        }
    }



    private static int getVersion(String version) {
        version = version.replace(".", "");
        return Integer.parseInt(version);
    }

    private static int calculate(String current, String old) {
        return getVersion(current) - getVersion(old);
    }

    private static String getSpigotVersion() {
        try {
            URLConnection connection = (new URL("https://api.spigotmc.org/legacy/update.php?resource=114180")).openConnection();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String version = reader.readLine();
                if (version.length() <= 7)
                    return version;
            }
        } catch (Exception exception) {}
        return "0";
    }
}
