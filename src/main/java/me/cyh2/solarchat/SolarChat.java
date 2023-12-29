package me.cyh2.solarchat;

import me.cyh2.solarchat.Events.ChatFormat;
import me.cyh2.solarchat.Events.OnUpdateAndCheckCompatible;
import me.cyh2.solarchat.commands.GChatBan;
import org.bukkit.Server;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public final class SolarChat extends JavaPlugin {
    public static Logger logger;
    public static Server server;
    public static Plugin plugin;
    public static File ChatConfig;
    public static YamlConfiguration ChatConfigCg;
    @Override
    public void onEnable() {
        logger = getLogger();
        server = getServer();
        plugin = getPlugin(getClass());
        server.getPluginManager().registerEvents(new ChatFormat(), plugin);
        server.getPluginManager().registerEvents(new OnUpdateAndCheckCompatible(), plugin);
        server.getPluginCommand("GChatBan").setExecutor(new GChatBan());
        saveResource("ChatConfig.yml", false);
        ChatConfig = new File(this.getDataFolder(), "ChatConfig.yml");
        ChatConfigCg = YamlConfiguration.loadConfiguration(ChatConfig);
        logger.info("SolarChat启动成功！");
        if (Utils.getSolarBan()) logger.info("检测到SolarBan插件，使用“/solarchat solarban on”来启用SolarBan兼容！");
        if (Utils.getSolarWebSocket()) logger.info("检测到SolarWebSocket插件，使用“/solarchat solarwebsocket on”来启用SolarWebSocket网页聊天！");
    }

    @Override
    public void onDisable() {
        logger.info("SolarChat关闭成功！");
    }
}
