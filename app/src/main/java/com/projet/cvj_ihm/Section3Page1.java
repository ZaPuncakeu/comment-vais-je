package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section3_page1);
        if(!getIntent().hasExtra("person")) {
            Utils.error(this, R.string.global_missing_inputs);
            finish();
            return;
        }

        Person person = getIntent().getParcelableExtra("person");

        knowBloodPressureCb = (CheckBox) findViewById(R.id.section3_page1_screen_know_blood_pressure_cb);
        bloodPressureTv = (TextView) findViewById(R.id.section3_page1_screen_blood_pressure_label_tv);
        bloodPressureEt = (EditText) findViewById(R.id.section3_page1_screen_blood_pressure_input_et);
        if(knowBloodPressureCb.isChecked()) {
            bloodPressureEt.setVisibility(View.VISIBLE);
            bloodPressureTv.setVisibility(View.VISIBLE);
            return;
        }

        bloodPressureTv.setVisibility(View.GONE);
        bloodPressureEt.setVisibility(View.GONE);

        knowCholesterolCb = (CheckBox) findViewById(R.id.section3_page1_screen_know_cholesterol_cb);
        cholesterolTv = (TextView) findViewById(R.id.section3_page1_screen_cholesterol_label_tv);
        cholesterolEt = (EditText) findViewById(R.id.section3_page1_screen_cholesterol_input_et);
        if(knowCholesterolCb.isChecked()) {
            cholesterolEt.setVisibility(View.VISIBLE);
            cholesterolTv.setVisibility(View.VISIBLE);
            return;
        }

        cholesterolEt.setVisibility(View.GONE);
        cholesterolTv.setVisibility(View.GONE);

        knowBloodSugarCb = (CheckBox) findViewById(R.id.section3_page1_screen_know_blood_sugar_cb);
        bloodSugarTv = (TextView) findViewById(R.id.section3_page1_screen_blood_sugar_label_tv);
        bloodSugarEt = (EditText) findViewById(R.id.section3_page1_screen_blood_sugar_input_et);
        if(knowBloodSugarCb.isChecked()) {
            bloodSugarTv.setVisibility(View.VISIBLE);
            bloodSugarEt.setVisibility(View.VISIBLE);
            return;
        }

        bloodSugarTv.setVisibility(View.GONE);
        bloodSugarEt.setVisibility(View.GONE);

        knowBloodPressureCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(knowBloodPressureCb.isChecked()) {
                    bloodPressureEt.setVisibility(View.VISIBLE);
                    bloodPressureTv.setVisibility(View.VISIBLE);
                    return;
                }

                bloodPressureTv.setVisibility(View.GONE);
                bloodPressureEt.setVisibility(View.GONE);
            }
        });

        knowCholesterolCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(knowCholesterolCb.isChecked()) {
                    cholesterolEt.setVisibility(View.VISIBLE);
                    cholesterolTv.setVisibility(View.VISIBLE);
                    return;
                }

                cholesterolEt.setVisibility(View.GONE);
                cholesterolTv.setVisibility(View.GONE);
            }
        });

        knowBloodSugarCb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(knowBloodSugarCb.isChecked()) {
                    bloodSugarTv.setVisibility(View.VISIBLE);
                    bloodSugarEt.setVisibility(View.VISIBLE);
                    return;
                }

                bloodSugarTv.setVisibility(View.GONE);
                bloodSugarEt.setVisibility(View.GONE);
            }
        });

        ph = new PageHandler(
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

                        float bloodPressure = Float.parseFloat(bloodPressureEt.getText().toString());
                        person.setBlood_pressure(bloodPressure);
                    }

                    if(knowCholesterolCb.isChecked()) {
                        if(cholesterolEt.getText().toString().isEmpty()) {
                            Utils.error(this, R.string.global_missing_inputs);
                            return null;
                        }

                        float cholesterol = Float.parseFloat(cholesterolEt.getText().toString());
                        person.setCholesterol(cholesterol);
                    }

                    if(knowBloodSugarCb.isChecked()) {
                        if(bloodSugarEt.getText().toString().isEmpty()) {
                            Utils.error(this, R.string.global_missing_inputs);
                            return null;
                        }

                        float bloodSugar = Float.parseFloat(bloodSugarEt.getText().toString());
                        person.setBlood_sugar(bloodSugar);
                    }

                    return person;
                }
        );
    }
}