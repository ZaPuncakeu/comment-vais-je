package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

public class Section2Title extends AppCompatActivity {

    private PageHandler ph;

    private final int currentStep = 5;
    private SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageManager.setLanguage(this);
        setContentView(R.layout.activity_section2_title);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        ph = new PageHandler(
                sharedPref,
                currentStep,
                this,
                R.id.section2_title_screen_next_btn,
                R.id.section2_title_screen_previous_btn,
                Section2Page1.class,
                R.id.section2_title_screen_skip_btn,
                Section3Title.class,
                11
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPref.edit().putInt("step", currentStep).commit();
    }
}