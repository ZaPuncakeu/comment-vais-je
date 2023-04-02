package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEt;
    private Spinner languageSp;
    public static final int currentStep = 0;
    SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        LanguageManager.selectLanguage(sharedPref.getInt("language", 0));
        LanguageManager.setLanguage(this);
        String username = sharedPref.getString("username", "");

        Log.d("step", "Step : " + sharedPref.getInt("step", 0));
        Log.d("step", "currentStep : " + currentStep);

        setContentView(R.layout.activity_main);
        usernameEt = (EditText) findViewById(R.id.main_screen_username_et);
        languageSp = (Spinner) findViewById(R.id.main_screen_lang_sp);

        usernameEt.setText(username);

        languageSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(LanguageManager.getSelection() != languageSp.getSelectedItemPosition()) {
                    LanguageManager.selectLanguage(languageSp.getSelectedItemPosition());
                    sharedPref.edit().putInt("language", languageSp.getSelectedItemPosition()).commit();
                    recreate();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        usernameEt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && (i != KeyEvent.KEYCODE_ENTER || i != KeyEvent.KEYCODE_BACK)) {
                    sharedPref.edit().putString("username", usernameEt.getText().toString()).commit();
                    return false;
                }
                return true;
            }
        });

        if(sharedPref.getInt("step", 0) != currentStep) {
            if(username.isEmpty()) {
                sharedPref.edit().putInt("step", currentStep).commit();
            }
            else {
                nextPage(username);
            }
        }
    }

    public void start(View v) {
        String username = usernameEt.getText().toString();

        if(username.isEmpty()) {
            Utils.error(this, R.string.main_screen_username_missing);
            return;
        }

        sharedPref.edit().putString("username", username).commit();
        sharedPref.edit().putInt("step", currentStep+1).commit();
        nextPage(username);
    }

    public void nextPage(String username) {
        Person person = new Person();
        person.setUsername(username);
        Intent intent = new Intent(this, PersonalActivity.class);
        intent.putExtra("person", person);
        startActivity(intent);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        sharedPref.edit().putInt("step", currentStep).commit();
//    }
}