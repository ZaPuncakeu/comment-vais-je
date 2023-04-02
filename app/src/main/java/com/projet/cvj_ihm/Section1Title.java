package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Section1Title extends AppCompatActivity {

    PageHandler ph;
    private final int currentStep = 2;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageManager.setLanguage(this);
        setContentView(R.layout.activity_section1_title);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        if(!getIntent().hasExtra("person")) {
            Utils.error(this, R.string.global_missing_inputs);
            finish();
            return;
        }

        Log.d("step", "Section 1 Title Step : " + sharedPref.getInt("step", 0));
        Log.d("step", "currentStep : " + currentStep);

        Person person = getIntent().getParcelableExtra("person");
        ph = new PageHandler(
                sharedPref,
                currentStep,
                this,
                R.id.section1_title_next_btn,
                R.id.section1_title_previous_btn,
                Section1Page1.class,
                () -> {
                    return person;
                }
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPref.edit().putInt("step", currentStep).commit();
    }
}