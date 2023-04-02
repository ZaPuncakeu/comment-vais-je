package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Section3Title extends AppCompatActivity {
    PageHandler ph;

    private SharedPreferences sharedPref;
    private int currentStep = 11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageManager.setLanguage(this);
        setContentView(R.layout.activity_section3_title);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        if(!getIntent().hasExtra("person")) {
            Utils.error(this, R.string.global_missing_inputs);
            finish();
            return;
        }

        Person person = getIntent().getParcelableExtra("person");

        ph = new PageHandler(
                sharedPref,
                currentStep,
                this,
                R.id.section3_title_screen_next_btn,
                R.id.section3_title_screen_previous_btn,
                Section3Page1.class,
                () -> {
                    return person;
                }
        );
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        sharedPref.edit().putInt("step", currentStep).commit();
//    }
}