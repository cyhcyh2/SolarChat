package me.cyh2.solarchat.Events;

import me.cyh2.solarchat.Update.UpdateChecker;
import me.cyh2.solarchat.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static me.cyh2.solarchat.SolarChat.plugin;

public class OnUpdateAndCheckCompatible implements Listener {
    @EventHandler
    public static void UpdateChecker (PlayerJoinEvent e) {
        UpdateChecker.init();
    }
    @EventHandler
    public static void SolarBanChecker (PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (Utils.getSolarBan()) {
            if (p.isOp()) {
                p.sendMessage(Utils.ReColor("嘿，管理员！&bSolarChat&r可以启用&b&lSolarBan&r兼容哦！"));
                p.sendMessage(Utils.ReColor("Hey，OP！&bSolarChat&r can Enable &b&lSolarBan&r Compatible！"));
            }
        }
    }
    @EventHandler
    public static void SolarWebSocketChecker (PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (Utils.getSolarWebSocket()) {
            if (p.isOp()) {
                p.sendMessage(Utils.ReColor("嘿，管理员！&bSolarChat&r可以启用&b&lSolarWebSocket&r兼容哦！"));
                p.sendMessage(Utils.ReColor("Hey，OP！&bSolarChat&r can Enable &b&lSolarWebSocket&r Compatible！"));
            }
        }
    }
}
