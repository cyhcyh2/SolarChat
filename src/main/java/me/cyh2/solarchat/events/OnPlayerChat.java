package me.cyh2.solarchat.events;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static me.cyh2.solarchat.SolarChat.config;
import static me.cyh2.solarchat.SolarChat.server;
import static me.cyh2.solarchat.Utils.reColor;

public class OnPlayerChat implements Listener {
    public String ATPlayer(AsyncPlayerChatEvent event) {
        int index, index1;
        Player sender = event.getPlayer();
        String msg = event.getMessage();

        for (Player p : Bukkit.getOnlinePlayers()) {
            // 将消息和玩家名都转换为小写进行不区分大小写的比较
            String lowerCaseMsg = msg.toLowerCase();
            String lowerCasePlayerName = p.getName().toLowerCase();

            if (lowerCaseMsg.contains(lowerCasePlayerName)) {
                index = lowerCaseMsg.indexOf(lowerCasePlayerName);
                index1 = lowerCaseMsg.indexOf('@');
                if (index1 != index - 1 || index == 0) {
                    // 使用原始的玩家名进行替换
                    msg = msg.replace(p.getName(), reColor("&a@" + p.getName() + "&r"));
                } else {
                    // 使用原始的玩家名进行替换
                    msg = msg.replace('@' + p.getName(), ChatColor.translateAlternateColorCodes('&', "&a@" + p.getName() + "&r"));
                }
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(reColor("&a &k s &r &a " + sender.getName() + "提到了你 快去聊天栏看看  &k b &r ")));
                p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100.0f, 1.0f);
                sender.playSound(sender, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100.0f, 1.0f);
            }
        }
        return msg;
    }

    @EventHandler (priority = EventPriority.LOWEST)
    public void onChat (AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        ComponentBuilder level = new ComponentBuilder(
                reColor(config.getString(getKey("level"))).replace("{level}", String.valueOf(p.getLevel())));
        ComponentBuilder prefix = new ComponentBuilder(
                reColor(config.getString(getKey("prefix"))).replace("{prefix}", p.hasPermission("solarchat-admin") ? config.getString("prefix.op") : config.getString("prefix.default")));
        ComponentBuilder name = new ComponentBuilder(
                reColor(config.getString(getKey("name")).replace("{name}", p.getName())));
        ComponentBuilder msg = new ComponentBuilder(
                reColor(config.getString(getKey("msg"))).replace("{msg}", ATPlayer(e)));
        prefix.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(p.hasPermission("solarchat.admin") ? "管理" : "玩家")));
        name.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(reColor("&r. · &b" + p.getName() + " &r. *\n" + "&7&o点击私信"))));
        name.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tell " + p.getName() + " "));
        level.append(prefix.create()).append(name.create()).append(msg.create());
        e.setCancelled(true);
        server.spigot().broadcast(level.create());
        for (Player p1 : Bukkit.getOnlinePlayers()) p1.playSound(p1, Sound.BLOCK_STONE_BUTTON_CLICK_ON, 100f, 1.0f);
    }

    public static String getKey (String key) {
        return "chat-format.formatter." + key;
    }
}
