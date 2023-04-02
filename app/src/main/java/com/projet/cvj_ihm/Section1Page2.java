package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SeekBar;

public class Section1Page2 extends AppCompatActivity {
    PageHandler ph;

    private final int currentStep = 4;
    private SeekBar angerLevelSlider;

    private RadioButton angerManagementNotGoodRb;
    private RadioButton angerManagementNeutralRb;
    private RadioButton angerManagementVeryGoodRb;

    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageManager.setLanguage(this);
        setContentView(R.layout.activity_section1_page2);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        if(!getIntent().hasExtra("person")) {
            Utils.error(this, R.string.global_missing_inputs);
            finish();
            return;
        }

        Person person = getIntent().getParcelableExtra("person");

        angerLevelSlider = (SeekBar) findViewById(R.id.section1_page2_screen_angry_sl);

        angerManagementNotGoodRb = (RadioButton) findViewById(R.id.section1_page2_screen_anger_management_not_good_option_rb);
        angerManagementNeutralRb = (RadioButton) findViewById(R.id.section1_page2_screen_anger_management_neutral_option_rb);
        angerManagementVeryGoodRb = (RadioButton) findViewById(R.id.section1_page2_screen_anger_management_very_good_option_rb);

        reloadData();
        events();

        ph = new PageHandler(
                sharedPref,
                currentStep,
                this,
                R.id.section1_page2_screen_next_btn,
                R.id.section1_page2_screen_previous_btn,
                Section2Title.class,
                () -> {
                    if(
                        !angerManagementNotGoodRb.isChecked() &&
                            !angerManagementNeutralRb.isChecked() &&
                            !angerManagementVeryGoodRb.isChecked()
                    ) {
                        Utils.error(this, R.string.global_missing_inputs);
                        return null;
                    }

                    person.setAnger(angerLevelSlider.getProgress());

                    if(angerManagementNotGoodRb.isChecked()) person.setAnger(0);
                    else if(angerManagementNeutralRb.isChecked()) person.setAnger(1);
                    else person.setAnger(2);

                    return person;
                }
        );
    }

    private void reloadData()
    {
        int anger = sharedPref.getInt("anger", -1);
        angerLevelSlider.setProgress(anger == -1 ? 5 : anger);

        int angerM = sharedPref.getInt("angerManagement", -1);
        switch (angerM) {
            case 0: angerManagementNotGoodRb.setChecked(true); break;
            case 1: angerManagementNeutralRb.setChecked(true); break;
            case 2: angerManagementVeryGoodRb.setChecked(true); break;
        }
    }

    private void events()
    {
        angerManagementNotGoodRb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref.edit().putInt("angerManagement", 0).commit();
            }
        });

        angerManagementNeutralRb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref.edit().putInt("angerManagement", 1).commit();
            }
        });

        angerManagementVeryGoodRb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref.edit().putInt("angerManagement", 2).commit();
            }
        });

        angerLevelSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                sharedPref.edit().putInt("anger", i).commit();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPref.edit().putInt("step", currentStep).commit();
    }
}