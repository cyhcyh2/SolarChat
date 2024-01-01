package me.cyh2.solarchat;

import me.cyh2.solarchat.Events.ChatFormat;
import me.cyh2.solarchat.Events.OnUpdateAndCheckCompatible;
import me.cyh2.solarchat.commands.ChatBan;
import me.cyh2.solarchat.commands.GChatBan;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

import static me.cyh2.solarchat.Utils.ReColor;

public final class SolarChat extends JavaPlugin {
    public static Logger logger;
    public static ConsoleCommandSender clogger;
    public static Server server;
    public static Plugin plugin;
    public static String PluginName;
    public static File ChatConfig;
    public static YamlConfiguration ChatConfigCg;
    public static File PlayerData_ChatBan;
    public static YamlConfiguration PlayerData_GetChatBan;
    @Override
    public void onEnable() {
        logger = getLogger();
        clogger = Bukkit.getConsoleSender();
        server = getServer();
        plugin = getPlugin(getClass());
        PluginName = "「&b&lSolarChat&r」";
        server.getPluginManager().registerEvents(new ChatFormat(), plugin);
        server.getPluginManager().registerEvents(new OnUpdateAndCheckCompatible(), plugin);
        server.getPluginCommand("GChatBan").setExecutor(new GChatBan());
        server.getPluginCommand("ChatBan").setExecutor(new ChatBan());
        saveResource("ChatConfig.yml", false);
        saveResource("PlayerDatas/ChatBans.yml", false);
        ChatConfig = new File(this.getDataFolder(), "ChatConfig.yml");
        ChatConfigCg = YamlConfiguration.loadConfiguration(ChatConfig);
        PlayerData_ChatBan = new File(this.getDataFolder(), "PlayerDatas/ChatBans.yml");
        PlayerData_GetChatBan = YamlConfiguration.loadConfiguration(PlayerData_ChatBan);
        logger.info("SolarChat启动成功啦！");
        clogger.sendMessage(ReColor(PluginName + "正在检测可兼容的插件哦~"));
        if (Utils.getSolarBan()) clogger.sendMessage(ReColor(PluginName + "检测到&b&lSolarBan&r插件，已经自动启用&b&lSolarBan&r兼容了哦~"));
        if (Utils.getSolarWebSocket()) clogger.sendMessage("检测到&b&lSolarWebSocket&r插件，已经自动启用&b&lSolarWebSocket&r网页聊天了哦~");
        if (Utils.getPlaceHolderAPI()) clogger.sendMessage(ReColor("&检测到&b&lPlaceHolderAPI&r插件，已经自动启用&b&lPlaceHolderAPI&r连接桥了哦~"));
    }

    @Override
    public void onDisable() {
        logger.info("SolarChat关闭成功了！");
    }
}
