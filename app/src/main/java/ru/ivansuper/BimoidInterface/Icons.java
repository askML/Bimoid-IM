package ru.ivansuper.BimoidInterface;

import android.graphics.drawable.Drawable;

import java.io.File;
import java.util.HashMap;

public class Icons {
    private static HashMap<String, Drawable> icons = new HashMap<String, Drawable>();

    public static void init(File config) {
        icons.clear();
        if (!config.exists()) return;

    }
}
