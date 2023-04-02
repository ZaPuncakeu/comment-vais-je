package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

public class PersonalActivity extends AppCompatActivity {
    PageHandler ph;

    private RadioButton manRb;
    private RadioButton womanRb;

    private EditText bdayEt;
    private EditText weightEt;
    private EditText heightEt;

    private Switch marriedSw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        if(!getIntent().hasExtra("person")) {
            Utils.error(this, R.string.global_missing_inputs);
            finish();
            return;
        }

        Person person = getIntent().getParcelableExtra("person");

        manRb = (RadioButton)  findViewById(R.id.personal_screen_gender_man_option_rb);
        womanRb = (RadioButton)  findViewById(R.id.personal_screen_gender_woman_option_rb);

        bdayEt = (EditText) findViewById(R.id.personal_screen_age_input_et);
        weightEt = (EditText) findViewById(R.id.personal_screen_weight_input_et);
        heightEt = (EditText) findViewById(R.id.personal_screen_height_input_et);

        marriedSw = (Switch) findViewById(R.id.personal_screen_married_input_sw);

        ph = new PageHandler(
                this,
                R.id.personal_screen_next_btn,
                R.id.personal_screen_previous_btn,
                Section1Title.class,
                () -> {
                    String bday = bdayEt.getText().toString();
                    boolean married = marriedSw.isActivated();

                    if((
                            !manRb.isChecked() && !womanRb.isChecked())
                            ||
                            bday.isEmpty()
                            ||
                            weightEt.getText().toString().isEmpty()
                            ||
                            heightEt.getText().toString().isEmpty()
                    ) {
                        Utils.error(this, R.string.global_missing_inputs);
                        return null;
                    }

                    if(manRb.isChecked()) person.setGender(1);
                    else person.setGender(0);

                    person.setAge(Integer.parseInt(bday));
                    float weight = Float.parseFloat(weightEt.getText().toString());
                    person.setWeight(weight);

                    int height = Integer.parseInt(heightEt.getText().toString());
                    person.setHeight(height);

                    person.setMarried(married);


                    return person;
                }
        );

    }
}