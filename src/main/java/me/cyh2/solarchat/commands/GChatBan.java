package me.cyh2.solarchat.commands;

import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.IOException;

import static me.cyh2.solarchat.SolarChat.ChatConfig;
import static me.cyh2.solarchat.SolarChat.ChatConfigCg;
import static org.bukkit.Bukkit.getServer;

public class GChatBan implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.isOp()) {
            if (strings.length >= 1) {
                if (ChatConfigCg.getBoolean("GChatBan")) {
                    ComponentBuilder builder = new ComponentBuilder("§c已关闭全体禁言！原因：" + strings[0]);
                    ChatConfigCg.set("GChatBan", false);
                    try {
                        ChatConfigCg.save(ChatConfig);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    getServer().spigot().broadcast(builder.create());
                    getServer().getConsoleSender().spigot().sendMessage(builder.create());
                    return true;
                } else {
                    ComponentBuilder builder = new ComponentBuilder("§c已开启全体禁言！原因：" + strings[0]);
                    ChatConfigCg.set("GChatBan", true);
                    try {
                        ChatConfigCg.save(ChatConfig);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    getServer().spigot().broadcast(builder.create());
                    getServer().getConsoleSender().spigot().sendMessage(builder.create());
                    return true;
                }
            }
            commandSender.sendMessage("§c请输入你开启全体禁言的理由！");
            return false;
        }
        return false;
    }
}
