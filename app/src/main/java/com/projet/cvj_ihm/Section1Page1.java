package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;

import com.google.android.material.slider.Slider;

public class Section1Page1 extends AppCompatActivity {
    PageHandler ph;

    private final int currentStep = 3;

    private RadioButton feelingNotGoodRb;
    private RadioButton feelingNeutralRb;
    private RadioButton feelingGoodRb;
    private RadioButton feelingVeryGoodRb;

    private SeekBar stressLevelSlider;

    private RadioButton tiredNotMuchRb;
    private RadioButton tiredPrettyOftenRb;
    private RadioButton tiredAllTheTimeRb;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageManager.setLanguage(this);
        setContentView(R.layout.activity_section1_page1);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        if(!getIntent().hasExtra("person")) {
            Utils.toast(this, R.string.global_missing_inputs);
            finish();
            return;
        }

        Person person = getIntent().getParcelableExtra("person");

        feelingNotGoodRb = (RadioButton) findViewById(R.id.section1_page1_screen_feeling_not_good_option_rb);
        feelingNeutralRb = (RadioButton) findViewById(R.id.section1_page1_screen_feeling_neutral_option_rb);
        feelingGoodRb = (RadioButton) findViewById(R.id.section1_page1_screen_feeling_good_option_rb);
        feelingVeryGoodRb = (RadioButton) findViewById(R.id.section1_page1_screen_feeling_very_good_option_rb);

        stressLevelSlider = (SeekBar) findViewById(R.id.section1_page1_screen_stress_level_sl);

        tiredNotMuchRb = (RadioButton) findViewById(R.id.section1_page1_screen_not_much_option_rb);
        tiredPrettyOftenRb = (RadioButton) findViewById(R.id.section1_page1_screen_pretty_often_option_rb);
        tiredAllTheTimeRb = (RadioButton) findViewById(R.id.section1_page1_screen_all_the_time_option_rb);

        reloadData();
        events();

        ph = new PageHandler(
                sharedPref,
                currentStep,
                this,
                R.id.section1_page1_screen_next_btn,
                R.id.section1_page1_screen_previous_btn,
                Section1Page2.class,
                () -> {
                    if(
                            !feelingNotGoodRb.isChecked() &&
                                    !feelingNeutralRb.isChecked() &&
                                    !feelingGoodRb.isChecked() &&
                                    !feelingVeryGoodRb.isChecked()
                            ||
                            !tiredNotMuchRb.isChecked() &&
                                    !tiredPrettyOftenRb.isChecked() &&
                                    !tiredAllTheTimeRb.isChecked()
                    ) {
                        Utils.error(this, R.string.global_missing_inputs);
                        return null;
                    }

                    if(feelingNotGoodRb.isChecked()) person.setFeeling(0);
                    else if(feelingNeutralRb.isChecked()) person.setFeeling(1);
                    else if(feelingGoodRb.isChecked()) person.setFeeling(2);
                    else person.setFeeling(3);

                    person.setStress(stressLevelSlider.getProgress());

                    if(tiredNotMuchRb.isChecked()) person.setTired(0);
                    else if(tiredPrettyOftenRb.isChecked()) person.setTired(1);
                    else person.setTired(2);

                    return person;
                }
        );
    }

    private void reloadData()
    {
        int feeling = sharedPref.getInt("feeling", -1);
        switch (feeling) {
            case 0: feelingNotGoodRb.setChecked(true); break;
            case 1: feelingNeutralRb.setChecked(true); break;
            case 2: feelingGoodRb.setChecked(true); break;
            case 3: feelingVeryGoodRb.setChecked(true); break;
        }

        int stress = sharedPref.getInt("stress", -1);
        stressLevelSlider.setProgress(stress == -1 ? 50 : stress);

        int tired = sharedPref.getInt("tired", -1);
        switch (tired) {
            case 0: tiredNotMuchRb.setChecked(true); break;
            case 1: tiredPrettyOftenRb.setChecked(true); break;
            case 2: tiredAllTheTimeRb.setChecked(true); break;
        }
    }

    private void events()
    {
        feelingNotGoodRb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref.edit().putInt("feeling", 0).commit();
            }
        });

        feelingNeutralRb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref.edit().putInt("feeling", 1).commit();
            }
        });

        feelingGoodRb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref.edit().putInt("feeling", 2).commit();
            }
        });

        feelingVeryGoodRb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref.edit().putInt("feeling", 3).commit();
            }
        });

        stressLevelSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                sharedPref.edit().putInt("stress", i).commit();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        tiredNotMuchRb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref.edit().putInt("tired", 0).commit();
            }
        });

        tiredPrettyOftenRb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref.edit().putInt("tired", 1).commit();
            }
        });

        tiredAllTheTimeRb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref.edit().putInt("tired", 2).commit();
            }
        });


    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        sharedPref.edit().putInt("step", currentStep).commit();
//    }
}