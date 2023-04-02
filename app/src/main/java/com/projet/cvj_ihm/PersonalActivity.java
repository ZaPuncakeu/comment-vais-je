package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PersonalActivity extends AppCompatActivity {
    PageHandler ph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        ph = new PageHandler(
                this,
                R.id.personal_screen_next_btn,
                R.id.personal_screen_previous_btn,
                Section1Title.class
        );
    }
}