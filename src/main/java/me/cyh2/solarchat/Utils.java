package me.cyh2.solarchat;

import org.jetbrains.annotations.NotNull;

public class Utils {
    public static @NotNull String reColor (String text) {
        return text == null ? reColor("&bNULL") : text.replace("&" ,"§").replace("§§", "&");
    }
}

