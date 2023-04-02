package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class Section3Page1 extends AppCompatActivity {
    private PageHandler ph;

    private CheckBox knowBloodPressureCb;
    private TextView bloodPressureTv;
    private EditText bloodPressureEt;

    private CheckBox knowCholesterolCb;
    private TextView cholesterolTv;
    private EditText cholesterolEt;

    private CheckBox knowBloodSugarCb;
    private TextView bloodSugarTv;
    private EditText bloodSugarEt;

    private final int currentStep = 12;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageManager.setLanguage(this);
        setContentView(R.layout.activity_section3_page1);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        if(!getIntent().hasExtra("person")) {
            Utils.error(this, R.string.global_missing_inputs);
            finish();
            return;
        }

        Person person = getIntent().getParcelableExtra("person");

        knowBloodPressureCb = (CheckBox) findViewById(R.id.section3_page1_screen_know_blood_pressure_cb);
        bloodPressureTv = (TextView) findViewById(R.id.section3_page1_screen_blood_pressure_label_tv);
        bloodPressureEt = (EditText) findViewById(R.id.section3_page1_screen_blood_pressure_input_et);

        knowCholesterolCb = (CheckBox) findViewById(R.id.section3_page1_screen_know_cholesterol_cb);
        cholesterolTv = (TextView) findViewById(R.id.section3_page1_screen_cholesterol_label_tv);
        cholesterolEt = (EditText) findViewById(R.id.section3_page1_screen_cholesterol_input_et);

        knowBloodSugarCb = (CheckBox) findViewById(R.id.section3_page1_screen_know_blood_sugar_cb);
        bloodSugarTv = (TextView) findViewById(R.id.section3_page1_screen_blood_sugar_label_tv);
        bloodSugarEt = (EditText) findViewById(R.id.section3_page1_screen_blood_sugar_input_et);

        ph = new PageHandler(
                sharedPref,
                currentStep,
                this,
                R.id.section3_page1_screen_view_summary_btn,
                R.id.section3_page1_screen_previous_btn,
                Summary.class,
                () -> {
                    if(knowBloodPressureCb.isChecked()) {
                        if(bloodPressureEt.getText().toString().isEmpty()) {
                            Utils.error(this, R.string.global_missing_inputs);
                            return null;
                        }

                        person.setKnows_blood_pressure(true);
                        float bloodPressure = Float.parseFloat(bloodPressureEt.getText().toString());
                        person.setBlood_pressure(bloodPressure);
                    }

                    if(knowCholesterolCb.isChecked()) {
                        if(cholesterolEt.getText().toString().isEmpty()) {
                            Utils.error(this, R.string.global_missing_inputs);
                            return null;
                        }

                        person.setKnows_cholesterol(true);
                        float cholesterol = Float.parseFloat(cholesterolEt.getText().toString());
                        person.setCholesterol(cholesterol);
                    }

                    if(knowBloodSugarCb.isChecked()) {
                        if(bloodSugarEt.getText().toString().isEmpty()) {
                            Utils.error(this, R.string.global_missing_inputs);
                            return null;
                        }

                        person.setKnows_blood_sugar(true);
                        float bloodSugar = Float.parseFloat(bloodSugarEt.getText().toString());
                        person.setBlood_sugar(bloodSugar);
                    }

                    return person;
                }
        );
    }

    private void reloadData() {
        boolean knowBloodPressure = sharedPref.getBoolean("knowBloodPressure", false);
        bloodPressureEt.setText(sharedPref.getString("bloodPressure", ""));
        if(knowBloodPressure) {
            bloodPressureEt.setVisibility(View.VISIBLE);
            bloodPressureTv.setVisibility(View.VISIBLE);
        }
        else {
            bloodPressureTv.setVisibility(View.INVISIBLE);
            bloodPressureEt.setVisibility(View.INVISIBLE);
        }

        boolean knowCholesterol = sharedPref.getBoolean("knowCholesterol", false);
        cholesterolEt.setText(sharedPref.getString("cholesterol", ""));
        if(knowCholesterol) {
            cholesterolEt.setVisibility(View.VISIBLE);
            cholesterolTv.setVisibility(View.VISIBLE);
        }
        else {
            cholesterolTv.setVisibility(View.INVISIBLE);
            cholesterolEt.setVisibility(View.INVISIBLE);
        }

        boolean knowBloodSugar = sharedPref.getBoolean("knowBloodSugar", false);
        bloodSugarEt.setText(sharedPref.getString("bloodSugar", ""));
        if(knowCholesterol) {
            bloodSugarEt.setVisibility(View.VISIBLE);
            bloodSugarTv.setVisibility(View.VISIBLE);
        }
        else {
            bloodSugarTv.setVisibility(View.INVISIBLE);
            bloodSugarEt.setVisibility(View.INVISIBLE);
        }
    }

    private void events() {
        knowBloodPressureCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref.edit().putBoolean("knowBloodPressure", knowBloodPressureCb.isChecked()).commit();
                if(knowBloodPressureCb.isChecked()) {
                    bloodPressureEt.setVisibility(View.VISIBLE);
                    bloodPressureTv.setVisibility(View.VISIBLE);
                    return;
                }

                bloodPressureTv.setVisibility(View.INVISIBLE);
                bloodPressureEt.setVisibility(View.INVISIBLE);
            }
        });

        bloodPressureEt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                sharedPref.edit().putString("bloodPressure", bloodPressureEt.getText().toString()).commit();
                return false;
            }
        });

        knowCholesterolCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(knowCholesterolCb.isChecked()) {
                    sharedPref.edit().putBoolean("knowCholesterol", knowCholesterolCb.isChecked()).commit();
                    cholesterolEt.setVisibility(View.VISIBLE);
                    cholesterolTv.setVisibility(View.VISIBLE);
                    return;
                }

                cholesterolEt.setVisibility(View.INVISIBLE);
                cholesterolTv.setVisibility(View.INVISIBLE);
            }
        });

        cholesterolEt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                sharedPref.edit().putString("cholesterol", cholesterolEt.getText().toString()).commit();
                return false;
            }
        });

        knowBloodSugarCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref.edit().putBoolean("knowBloodSugar", knowBloodSugarCb.isChecked()).commit();
                if(knowBloodSugarCb.isChecked()) {
                    bloodSugarTv.setVisibility(View.VISIBLE);
                    bloodSugarEt.setVisibility(View.VISIBLE);
                    return;
                }

                bloodSugarTv.setVisibility(View.INVISIBLE);
                bloodSugarEt.setVisibility(View.INVISIBLE);
            }
        });

        bloodSugarEt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                sharedPref.edit().putString("bloodSugar", bloodSugarEt.getText().toString()).commit();
                return false;
            }
        });

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        sharedPref.edit().putInt("step", currentStep).commit();
//    }
}