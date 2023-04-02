package com.projet.cvj_ihm;

import android.content.res.Configuration;
import android.os.Build;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class LanguageManager {
    private static final String[] langKeys = {"en", "fr"};
    private static int selected = 0;

    public static void selectLanguage(int selection) {
        selected = selection;
    }

    public static void setLanguage(AppCompatActivity app) {
        Log.v("bruh", "Here!!!!");
        String lang = langKeys[selected];
        Log.v("language", lang);
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale);
        }
        else {
            config.locale = locale;
        }
        app.getBaseContext().getResources().updateConfiguration(config, app.getBaseContext().getResources().getDisplayMetrics());
    }

    public static int getSelection() {
        return selected;
    }
}
