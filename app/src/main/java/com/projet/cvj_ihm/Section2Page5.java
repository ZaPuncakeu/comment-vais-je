package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class Section2Page5 extends AppCompatActivity {
    private PageHandler ph;
    EditText descriptionEt;
    private final int currentStep = 10;
    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageManager.setLanguage(this);
        setContentView(R.layout.activity_section2_page5);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        if(!getIntent().hasExtra("person")) {
            Utils.error(this, R.string.global_missing_inputs);
            finish();
            return;
        }

        Person person = getIntent().getParcelableExtra("person");

        descriptionEt = (EditText) findViewById(R.id.section2_page5_screen_description_et);
        descriptionEt.setText(sharedPref.getString("descriptionTest3", ""));
        ph = new PageHandler(
                sharedPref,
                currentStep,
                this,
                R.id.section2_page5_screen_next_btn,
                R.id.section2_page5_screen_previous_btn,
                Section3Title.class,
                ()-> {
                    String description = descriptionEt.getText().toString();
                    if(description.isEmpty()) {
                        Utils.error(this, R.string.global_missing_inputs);
                        return null;
                    }

                    person.setDescription(description, 2);
                    return person;
                }
        );

        descriptionEt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && (i != KeyEvent.KEYCODE_ENTER || i != KeyEvent.KEYCODE_BACK)) {
                    sharedPref.edit().putString("descriptionTest3", descriptionEt.getText().toString()).commit();
                    return false;
                }
                return true;
            }
        });
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        sharedPref.edit().putInt("step", currentStep).commit();
//    }
}