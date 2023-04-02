package com.projet.cvj_ihm;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Utils {
    public static void vibrate(AppCompatActivity app, long duration_ms) {
        Vibrator v = (Vibrator) app.getSystemService(Context.VIBRATOR_SERVICE);
        if(duration_ms < 1)
            duration_ms = 1;
        if(v != null && v.hasVibrator()) {
            // Attention changement comportement avec API >= 26 (cf doc)
            if(Build.VERSION.SDK_INT >= 26) {
                v.vibrate(VibrationEffect.createOneShot(duration_ms,
                        VibrationEffect.DEFAULT_AMPLITUDE));
            }
            else {
                v.vibrate(duration_ms);
            }
        }
    }

    public static void toast(AppCompatActivity app, int msg) {
        Toast.makeText(app, app.getResources().getString(msg),Toast.LENGTH_SHORT).show();
    }

    public static void error(AppCompatActivity app, int msg) {
        toast(app, msg);
        vibrate(app, 100);
    }

    public static String R2String(AppCompatActivity app, int str) {
        return app.getResources().getString(str);
    }
}
