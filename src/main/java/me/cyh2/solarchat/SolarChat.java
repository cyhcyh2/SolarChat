package me.cyh2.solarchat;

import me.cyh2.solarchat.events.OnPlayerChat;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;


public final class SolarChat extends JavaPlugin {
    public static Logger logger;
    public static ConsoleCommandSender clogger;
    public static Server server;
    public static Plugin plugin;
    public static PluginManager pluginManager;
    public static String PluginName;
    public static File configFile;
    public static YamlConfiguration config;
    @Override
    public void onEnable() {
        logger = getLogger();
        clogger = Bukkit.getConsoleSender();
        server = getServer();
        plugin = getPlugin(getClass());
        pluginManager = server.getPluginManager();
        PluginName = "「&b&lSolarChat&r」";
        saveResource("config.yml", false);
        configFile = new File(this.getDataFolder(), "config.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
        pluginManager.registerEvents(new OnPlayerChat(), plugin);
        logger.info("SolarChat启动成功啦！");
    }

    @Override
    public void onDisable() {
        logger.info("SolarChat关闭成功了！");
    }
}
