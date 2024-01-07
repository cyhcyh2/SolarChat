package me.cyh2.solarchat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

import static me.cyh2.solarchat.SolarChat.*;
import static me.cyh2.solarchat.Utils.ReColor;

public class ShowIP implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (strings.length >=1) {
                if (strings[0].equals("con")) {
                    if (Show_IP_Data.getBoolean(p.getName() + ".show")) {
                        Show_IP_Data.set(p.getName() + ".show", false);
                        p.sendMessage(ReColor(ChatConfigCg.getString("Message.IPShowDisable")));
                    } else {
                        Show_IP_Data.set(p.getName() + ".show", true);
                        p.sendMessage(ReColor(ChatConfigCg.getString("Message.IPShowEnable")));
                    }
                    try {
                        Show_IP_Data.save(Show_IP_Data_File);
                    } catch (IOException e) {
                        p.sendMessage(ReColor("保存配置文件时发生&c&l错误&r了，请稍后&b&l重试&r哦~"));
                    }
                    return true;
                }
                if (strings[0].equals("p")) {
                    if (Show_IP_Data.getBoolean(p.getName() + ".p")) {
                        Show_IP_Data.set(p.getName() + ".p", false);
                        p.sendMessage(ReColor(ChatConfigCg.getString("Message.IPPDisable")));
                    } else {
                        Show_IP_Data.set(p.getName() + ".p", true);
                        p.sendMessage(ReColor(ChatConfigCg.getString("Message.IPPEnable")));
                    }
                    try {
                        Show_IP_Data.save(Show_IP_Data_File);
                    } catch (IOException e) {
                        p.sendMessage(ReColor("保存配置文件时发生&c&l错误&r了，请稍后&b&l重试&r哦~"));
                    }
                }
            }
            if (!Show_IP_Data.getBoolean(p.getName() + ".show")) {
                p.sendMessage(ReColor(ChatConfigCg.getString("Message.IPShowEnableWarn")));
            } else {
                p.sendMessage(ReColor(ChatConfigCg.getString("Message.IPShowDisableWarn")));
            }
            return true;
        }
        return false;
    }
}
