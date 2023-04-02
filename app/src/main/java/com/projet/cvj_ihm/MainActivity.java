package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEt;
    private Spinner languageSp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageManager.setLanguage(this);

        setContentView(R.layout.activity_main);
        usernameEt = (EditText) findViewById(R.id.main_screen_username_et);
        languageSp = (Spinner) findViewById(R.id.main_screen_lang_sp);

        languageSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(LanguageManager.getSelection() != languageSp.getSelectedItemPosition()) {
                    LanguageManager.selectLanguage(languageSp.getSelectedItemPosition());
                    recreate();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void start(View v) {
        String username = usernameEt.getText().toString();

        if(username.isEmpty()) {
            Utils.error(this, R.string.main_screen_username_missing);
            return;
        }

        Person person = new Person();
        person.setUsername(username);

        Intent intent = new Intent(this, PersonalActivity.class);
        intent.putExtra("person", person);
        startActivity(intent);
    }
}