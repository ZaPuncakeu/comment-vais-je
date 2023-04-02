package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class Section2Page4 extends AppCompatActivity {
    private PageHandler ph;
    EditText descriptionEt;
    private final int currentStep = 9;
    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageManager.setLanguage(this);
        setContentView(R.layout.activity_section2_page4);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        if(!getIntent().hasExtra("person")) {
            Utils.error(this, R.string.global_missing_inputs);
            finish();
            return;
        }

        Person person = getIntent().getParcelableExtra("person");

        descriptionEt = (EditText) findViewById(R.id.section2_page4_screen_description_et);
        descriptionEt.setText(sharedPref.getString("descriptionTest2", ""));


        descriptionEt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                sharedPref.edit().putString("descriptionTest2", descriptionEt.getText().toString()).commit();
                return false;
            }
        });

        ph = new PageHandler(
                sharedPref,
                currentStep,
                this,
                R.id.section2_page4_screen_next_btn,
                R.id.section2_page4_screen_previous_btn,
                Section2Page5.class,
                ()-> {
                    String description = descriptionEt.getText().toString();
                    if(description.isEmpty()) {
                        Utils.error(this, R.string.global_missing_inputs);
                        return null;
                    }

                    person.setDescription(description, 1);
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