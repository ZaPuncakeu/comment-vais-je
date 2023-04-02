package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

public class PersonalActivity extends AppCompatActivity {
    PageHandler ph;

    public static final int currentStep = 1;
    private RadioButton manRb;
    private RadioButton womanRb;

    private EditText bdayEt;
    private EditText weightEt;
    private EditText heightEt;

    private Switch marriedSw;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageManager.setLanguage(this);
        setContentView(R.layout.activity_personal);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        if(!getIntent().hasExtra("person")) {
            Utils.error(this, R.string.global_missing_inputs);
            finish();
            return;
        }

        Person person = getIntent().getParcelableExtra("person");

        Log.d("step", "Personal Step : " + sharedPref.getInt("step", 0));
        Log.d("step", "Personal currentStep : " + currentStep);

        manRb = (RadioButton)  findViewById(R.id.personal_screen_gender_man_option_rb);
        womanRb = (RadioButton)  findViewById(R.id.personal_screen_gender_woman_option_rb);

        bdayEt = (EditText) findViewById(R.id.personal_screen_age_input_et);
        weightEt = (EditText) findViewById(R.id.personal_screen_weight_input_et);
        heightEt = (EditText) findViewById(R.id.personal_screen_height_input_et);

        marriedSw = (Switch) findViewById(R.id.personal_screen_married_input_sw);

        reloadData();
        events();

        ph = new PageHandler(
                sharedPref,
                currentStep,
                this,
                R.id.personal_screen_next_btn,
                R.id.personal_screen_previous_btn,
                Section1Title.class,
                () -> {
                    String bday = bdayEt.getText().toString();
                    boolean married = marriedSw.isChecked();

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

    private void reloadData()
    {
        int gender = sharedPref.getInt("gender", -1);
        if(gender == 0) womanRb.setChecked(true);
        else if(gender == 1) manRb.setChecked(true);

        bdayEt.setText(sharedPref.getString("age", ""));
        weightEt.setText(sharedPref.getString("weight", ""));
        heightEt.setText(sharedPref.getString("height", ""));
        marriedSw.setChecked(sharedPref.getBoolean("married", false));
    }

    private void events()
    {
        manRb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref.edit().putInt("gender", 1).commit();
            }
        });

        womanRb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref.edit().putInt("gender", 0).commit();
            }
        });

        bdayEt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && (i != KeyEvent.KEYCODE_ENTER || i != KeyEvent.KEYCODE_BACK)) {
                    sharedPref.edit().putString("age", bdayEt.getText().toString()).commit();
                    return false;
                }
                return true;
            }
        });

        weightEt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                sharedPref.edit().putString("weight", weightEt.getText().toString()).commit();
                return false;
            }
        });

        heightEt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                sharedPref.edit().putString("height", heightEt.getText().toString()).commit();
                return false;
            }
        });

        marriedSw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref.edit().putBoolean("married", marriedSw.isChecked()).commit();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPref.edit().putInt("step", currentStep).commit();
    }
}