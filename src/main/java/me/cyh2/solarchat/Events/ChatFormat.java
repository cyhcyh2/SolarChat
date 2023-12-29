package me.cyh2.solarchat.Events;

import me.cyh2.solarchat.Utils;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.Collection;

import static me.cyh2.solarchat.SolarChat.*;
import static org.bukkit.Bukkit.getServer;

public class ChatFormat implements Listener {
    static Collection<? extends Player> onlinePlayers = Bukkit.getServer().getOnlinePlayers();
    @EventHandler
    public static void SetChatFormat (PlayerChatEvent e) {
        Player p = e.getPlayer();
        if (!e.getMessage().contains(ChatConfigCg.getString("StopMessage"))) {
            if (ChatConfigCg.getBoolean("ChatFormat.setFormat.Enable")) {
                if (ChatConfigCg.getString("ChatFormat.setFormat.Format") == null) {
                    p.sendMessage(Utils.ReColor("&c服务器配置文件出现严重问题！"));
                } else if (ChatConfigCg.getString("Prefix.op") == null) {
                    p.sendMessage(Utils.ReColor("&c服务器配置文件出现严重问题！"));
                } else if (ChatConfigCg.getString("Prefix.player") == null) {
                    p.sendMessage(Utils.ReColor("&c服务器配置文件出现严重问题！"));
                } else {
                    for (Player oplayer : onlinePlayers) {
                        Utils.ps(plugin, oplayer, Sound.BLOCK_STONE_BUTTON_CLICK_ON);
                    }
                    String a = Utils.ReColor(ChatConfigCg.getString("ChatFormat.setFormat.Format").replace("{SolarChat.Player.Level}", Integer.toString(p.getLevel())));
                    String b;
                    if (p.isOp()) {
                        b = a.replace("{SolarChat.Player.Prefix}", ChatConfigCg.getString("Prefix.op"));
                        e.setFormat(b);
                    } else {
                        if (ChatConfigCg.getBoolean("GChatBan")) {
                            e.setCancelled(true);
                        } else {
                            b = a.replace("{SolarChat.Player.Prefix}", ChatConfigCg.getString("Prefix.player"));
                            e.setFormat(b);
                        }
                    }
                }
            }
            if (ChatConfigCg.getBoolean("ChatFormat.CalledEventFormat.Enable")){
                ComponentBuilder builder = new ComponentBuilder("");
                if (ChatConfigCg.getString("ChatFormat.CalledEventFormat.Format") == null) {
                    p.sendMessage(Utils.ReColor("&c服务器配置文件出现严重问题！"));
                } else if (ChatConfigCg.getString("Prefix.op") == null) {
                    p.sendMessage(Utils.ReColor("&c服务器配置文件出现严重问题！"));
                } else if (ChatConfigCg.getString("Prefix.player") == null) {
                    p.sendMessage(Utils.ReColor("&c服务器配置文件出现严重问题！"));
                } else {
                    for (Player oplayer : onlinePlayers) {
                        Utils.ps(plugin, oplayer, Sound.BLOCK_STONE_BUTTON_CLICK_ON);
                    }
                    String a = Utils.ReColor(ChatConfigCg.getString("ChatFormat.CalledEventFormat.Format").replace("{SolarChat.Player.Level}", Integer.toString(p.getLevel())));
                    String b;
                    if (p.isOp()) {
                        b = a.replace("{SolarChat.Player.Prefix}", ChatConfigCg.getString("Prefix.op"));
                    } else
                        b = a.replace("{SolarChat.Player.Prefix}", ChatConfigCg.getString("Prefix.player"));
                    String c = b.replace("{SolarChat.Player.Name}", p.getName());
                    String d = c.replace("{SolarChat.Player.msg}",e.getMessage());
                    builder.append(d);
                    if (ChatConfigCg.getBoolean("GChatBan")) {
                        if (p.isOp()) {
                            getServer().spigot().broadcast(builder.create());
                            getServer().getConsoleSender().spigot().sendMessage(builder.create());
                            e.setCancelled(true);
                        } else {
                            e.setCancelled(true);
                        }
                    } else {
                        if (PlayerData_GetChatBan.getBoolean(p.getName())) {
                            e.setCancelled(true);
                        } else {
                            getServer().spigot().broadcast(builder.create());
                            getServer().getConsoleSender().spigot().sendMessage(builder.create());
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }
        e.setCancelled(true);
    }
}
