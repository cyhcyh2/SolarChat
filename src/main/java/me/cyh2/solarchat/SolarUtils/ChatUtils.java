package me.cyh2.solarchat.SolarUtils;

import me.clip.placeholderapi.PlaceholderAPI;
import me.cyh2.solarchat.Utils;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.io.IOException;

import static me.cyh2.solarchat.SolarChat.*;
import static me.cyh2.solarchat.SolarChat.plugin;
import static me.cyh2.solarchat.Utils.ReColor;
import static org.bukkit.Bukkit.getServer;

public class ChatUtils {
    public static BaseComponent[] ChatComponent (Player p, String msg) {
        ComponentBuilder builder = new ComponentBuilder("");
        if (ChatConfigCg.getBoolean("ChatFormat.CalledEventFormat.Enable")){
            if (ChatConfigCg.getString("ChatFormat.CalledEventFormat.Format") == null || ChatConfigCg.getString("Prefix.op") == null || ChatConfigCg.getString("Prefix.player") == null) {
                p.sendMessage(ReColor("&c服务器配置文件出现严重问题！"));
            } else {
                if (Show_IP_Data.getBoolean(p.getName() + ".show") && Show_IP_Data.getBoolean(p.getName() + ".p")) {
                    try {
                        IPInfo ip_info = IPInfo.requestIPInfo(p.getAddress().getHostString());
                        TextComponent ip = new TextComponent(ReColor("「&b&l" + ip_info.getCountry() + " " + ip_info.getRegionName() + "&r」"));
                        ip.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ReColor("「&b&l" + ip_info.getCountry() + " " + ip_info.getRegionName() + " " + ip_info.getCity() + " " + ip_info.getIsp() + "&r」"))));
                        builder.append(ip);
                    } catch (IOException e) {
                        p.sendMessage(e.getMessage());
                    }
                }
                String a = ReColor(ChatConfigCg.getString("ChatFormat.CalledEventFormat.Format").replace("{SolarChat.Player.Level}", Integer.toString(p.getLevel())));
                String b;
                if (p.isOp()) {
                    b = a.replace("{SolarChat.Player.Prefix}", ChatConfigCg.getString("Prefix.op"));
                } else
                    b = a.replace("{SolarChat.Player.Prefix}", ChatConfigCg.getString("Prefix.player"));
                String c = b.replace("{SolarChat.Player.Name}", p.getName());
                String d = c.replace("{SolarChat.Player.msg}",msg);
                String e;
                if (Utils.getPlaceHolderAPI()) {
                    e = ReColor(PlaceholderAPI.setPlaceholders(p, d));
                } else {
                    e = d;
                }
                builder.append(e);
            }
        }return builder.create();
    }
}
