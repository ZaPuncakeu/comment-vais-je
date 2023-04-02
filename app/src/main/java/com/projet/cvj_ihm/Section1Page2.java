package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.SeekBar;

public class Section1Page2 extends AppCompatActivity {
    PageHandler ph;

    private SeekBar angerLevelSlider;

    private RadioButton angerManagementNotGoodRb;
    private RadioButton angerManagementNeutralRb;
    private RadioButton angerManagementVeryGoodRb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section1_page2);

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

        ph = new PageHandler(
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
}