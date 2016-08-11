package ru.ivansuper.locale;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
import ru.ivansuper.bimoidim.resources;

public class Locale {

    public static final int DEFAULT = 0;
    public static final int ENGLISH = 1;

    private static int mPreparedLanguage;

    public static void prepare() {
        int current = Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(resources.ctx).getString("ms_select_language", "-1"));
        if (current == -1) {
            final String code = resources.ctx.getResources().getConfiguration().locale.getLanguage();
            Log.e("Device lang code", code);
            if (code.equalsIgnoreCase("ru")) {
                prepareInternalRU();
            } else {
                prepareInternalEN();
            }
        } else {
            ArrayList<Language> list = getAvailable();

            if (current > list.size()) current = DEFAULT;

            switch (current) {
                case DEFAULT:
                    prepareInternalRU();
                    break;
                case ENGLISH:
                    prepareInternalEN();
                    break;
            }
        }
    }

    private static void setDefaultLocale(String aLocale) {

        java.util.Locale locale = new java.util.Locale(aLocale);
        java.util.Locale.setDefault(locale);

        Configuration configuration = new Configuration();
        configuration.locale = locale;
        resources.ctx.getResources().updateConfiguration(configuration, null);
    }

    public static void prepareInternalRU() {

        setDefaultLocale("ru");
        mPreparedLanguage = DEFAULT;
    }

    public static void prepareInternalEN() {

        setDefaultLocale("en");
        mPreparedLanguage = ENGLISH;
    }

    public static final int getPreparedLanguageCode() {

        switch (mPreparedLanguage) {
            case ENGLISH:
                return 19;
        }
        return 52;
    }

    public static ArrayList<Language> getAvailable() {
        final ArrayList<Language> list = new ArrayList<Language>();
        list.add(new Language("Русская локализация", "Русский", "Ivansuper", true));
        list.add(new Language("English localization", "English", "Ivansuper", true));
        return list;
    }

    public static String getStringResourceByName(String aString) {
        Resources res = resources.ctx.getResources();
        int resId = res.getIdentifier(aString, "string", resources.ctx.getPackageName());
        if (resId > 0) {
            return res.getString(resId);
        }
        return "";
    }
}
