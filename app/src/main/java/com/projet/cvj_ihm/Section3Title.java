package com.projet.cvj_ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Section3Title extends AppCompatActivity {
    PageHandler ph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section3_title);

        ph = new PageHandler(
                this,
                R.id.section3_title_screen_next_btn,
                R.id.section3_title_screen_previous_btn,
                Section3Page1.class
        );
    }
}