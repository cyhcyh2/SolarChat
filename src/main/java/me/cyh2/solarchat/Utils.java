package me.cyh2.solarchat;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Utils {
    public static String ReColor (String text) {
        return text.replace("&" ,"§");
    }
    public static void ps (Plugin plugin, Player player, Sound sound) {
        player.playSound(player.getLocation(), sound, 100.0f, 1.0f);
    }
    public static Boolean getSolarBan () {
        try{
            Class.forName("me.cyh2.solarban.SolarBan");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
    public static Boolean getSolarWebSocket () {
        try{
            Class.forName("me.cyh2.solarwebsocket.SolarWebSocket");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
    public static Boolean getPlaceHolderAPI () {
        try{
            Class.forName("me.clip.placeholderapi.PlaceholderAPI");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
    public static String getPlaceHolders (String str) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        // 获取类文件
        Class<?> PlaceHolderAPIClass = Class.forName("me.clip.placeholderapi.PlaceholderAPI");

        // 获取类中的方法
        Method[] methods = PlaceHolderAPIClass.getDeclaredMethods();

        // 遍历方法并打印方法名
        for (Method method : methods) {
            System.out.println("Method: " + method.getName());
        }

        // 获取类中的函数
        Constructor<?>[] constructors = PlaceHolderAPIClass.getConstructors();

        // 遍历函数并打印函数名
        for (Constructor<?> constructor : constructors) {
            System.out.println("Constructor: " + constructor.getName());
        }

        // 通过反射实例化类对象
        Object placeHolderAPIInstance = PlaceHolderAPIClass.newInstance();

        // 调用类中的方法
        Method setPlaceHoldersMethod = PlaceHolderAPIClass.getMethod("setPlaceHolders", String.class);
        return (String) setPlaceHoldersMethod.invoke(placeHolderAPIInstance, str);
    }
    public static Boolean UpdateChecker () {
        return false;
    }
}
