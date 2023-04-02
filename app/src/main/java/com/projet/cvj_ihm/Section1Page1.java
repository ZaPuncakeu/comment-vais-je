package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;

import com.google.android.material.slider.Slider;

public class Section1Page1 extends AppCompatActivity {
    PageHandler ph;

    private RadioButton feelingNotGoodRb;
    private RadioButton feelingNeutralRb;
    private RadioButton feelingGoodRb;
    private RadioButton feelingVeryGoodRb;

    private SeekBar stressLevelSlider;

    private RadioButton tiredNotMuchRb;
    private RadioButton tiredPrettyOftenRb;
    private RadioButton tiredAllTheTimeRb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section1_page1);

        if(!getIntent().hasExtra("person")) {
            Utils.toast(this, R.string.global_missing_inputs);
            finish();
            return;
        }

        Person person = getIntent().getParcelableExtra("person");

        RadioButton feelingNotGoodRb = (RadioButton) findViewById(R.id.section1_page1_screen_feeling_not_good_option_rb);
        RadioButton feelingNeutralRb = (RadioButton) findViewById(R.id.section1_page1_screen_feeling_neutral_option_rb);
        RadioButton feelingGoodRb = (RadioButton) findViewById(R.id.section1_page1_screen_feeling_good_option_rb);
        RadioButton feelingVeryGoodRb = (RadioButton) findViewById(R.id.section1_page1_screen_feeling_very_good_option_rb);

        stressLevelSlider = (SeekBar) findViewById(R.id.section1_page1_screen_stress_level_sl);

        RadioButton tiredNotMuchRb = (RadioButton) findViewById(R.id.section1_page1_screen_not_much_option_rb);
        RadioButton tiredPrettyOftenRb = (RadioButton) findViewById(R.id.section1_page1_screen_pretty_often_option_rb);
        RadioButton tiredAllTheTimeRb = (RadioButton) findViewById(R.id.section1_page1_screen_all_the_time_option_rb);

        ph = new PageHandler(
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
}