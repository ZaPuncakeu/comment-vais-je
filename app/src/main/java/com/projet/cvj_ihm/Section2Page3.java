package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class Section2Page3 extends AppCompatActivity {
    private PageHandler ph;
    EditText descriptionEt;
    SharedPreferences sharedPref;
    private final int currentStep = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageManager.setLanguage(this);
        setContentView(R.layout.activity_section2_page3);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        if(!getIntent().hasExtra("person")) {
            Utils.error(this, R.string.global_missing_inputs);
            finish();
            return;
        }

        Person person = getIntent().getParcelableExtra("person");

        descriptionEt = (EditText) findViewById(R.id.section2_page3_screen_description_et);

        descriptionEt.setText(sharedPref.getString("descriptionTest1", ""));


        descriptionEt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                sharedPref.edit().putString("descriptionTest1", descriptionEt.getText().toString()).commit();
                Log.d("KHRA", "ZBEL " + sharedPref.getString("descriptionTest1", ""));
                return false;
            }
        });

        ph = new PageHandler(
                sharedPref,
                currentStep,
                this,
                R.id.section2_page3_screen_next_btn,
                R.id.section2_page3_screen_previous_btn,
                Section2Page4.class,
                ()-> {
                    String description = descriptionEt.getText().toString();
                    if(description.isEmpty()) {
                        Utils.error(this, R.string.global_missing_inputs);
                        return null;
                    }

                    person.setDescription(description, 0);
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