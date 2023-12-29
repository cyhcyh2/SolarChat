package me.cyh2.solarchat.commands;

import me.cyh2.solarchat.SolarChat;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

import static me.cyh2.solarchat.SolarChat.*;
import static org.bukkit.Bukkit.getServer;

public class ChatBan implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("SolarChat.ChatBan")) {
            if (strings.length >= 2) {
                Player p = Bukkit.getPlayer(strings[0]);
                if (p != null) {
                    if (ChatConfigCg.getBoolean("YAML.Enable")) {
                        if (PlayerData_GetChatBan.getBoolean(p.getName())) {
                            ComponentBuilder builder = new ComponentBuilder("§c已关闭" + p.getName() + "的禁言！原因：" + strings[1]);
                            PlayerData_GetChatBan.set(p.getName(), false);
                            try {
                                ChatConfigCg.save(ChatConfig);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            getServer().spigot().broadcast(builder.create());
                            getServer().getConsoleSender().spigot().sendMessage(builder.create());
                            return true;
                        } else {
                            ComponentBuilder builder = new ComponentBuilder("§c已开启" + p.getName() +"的禁言！原因：" + strings[1]);
                            PlayerData_GetChatBan.set(p.getName(), true);
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
                }
            }
            commandSender.sendMessage("§c请输入你开启该玩家禁言的理由！");
            return false;
        }
        return false;
    }
}
